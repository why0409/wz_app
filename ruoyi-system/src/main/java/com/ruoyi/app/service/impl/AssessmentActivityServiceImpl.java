package com.ruoyi.app.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.ruoyi.app.domain.*;
import com.ruoyi.app.domain.dto.ActivityConfigDetailDTO;
import com.ruoyi.app.domain.dto.AssessmentConfigDTO;
import com.ruoyi.app.domain.dto.AssessmentStartDTO;
import com.ruoyi.app.domain.dto.AssessmentStatsDTO;
import com.ruoyi.app.mapper.AssessmentActivityMapper;
import com.ruoyi.app.service.*;
import com.ruoyi.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * 测评活动Service业务层处理
 * * @author wanghongyu
 * @date 2025-11-04
 */
@Service
public class AssessmentActivityServiceImpl extends ServiceImpl<AssessmentActivityMapper, AssessmentActivity> implements IAssessmentActivityService {

    @Autowired
    private IAssessmentActivityCadreService activityCadreService;

    @Autowired
    private IAssessmentActivityOptionService activityOptionService;

    @Autowired
    private IAssessmentResultService resultService;

    @Autowired
    private IAssessmentLogService logService;

    @Autowired
    private IAssessmentStatsService statsService;

    @Autowired
    private IAssessmentActivityReportService reportService;


    @Override
    public List<AssessmentActivity> selectAssessmentActivityList(AssessmentActivity assessmentActivity) {
        LambdaQueryWrapper<AssessmentActivity> lqw = new LambdaQueryWrapper<AssessmentActivity>();
        lqw.like(StringUtils.isNotEmpty(assessmentActivity.getActivityName()), AssessmentActivity::getActivityName, assessmentActivity.getActivityName());
        lqw.eq(StringUtils.isNotEmpty(assessmentActivity.getStatus()), AssessmentActivity::getStatus, assessmentActivity.getStatus());
        lqw.eq(StringUtils.isNotEmpty(assessmentActivity.getActivityYear()), AssessmentActivity::getActivityYear, assessmentActivity.getActivityYear());
        lqw.orderByDesc(AssessmentActivity::getActivityYear);
        lqw.orderByDesc(AssessmentActivity::getCreateTime);
        return this.list(lqw);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void configActivity(AssessmentConfigDTO configDTO) {
        Long activityId = configDTO.getActivityId();

        // 1. 删除旧的关联配置
        activityCadreService.remove(new LambdaQueryWrapper<AssessmentActivityCadre>().eq(AssessmentActivityCadre::getActivityId, activityId));
        activityOptionService.remove(new LambdaQueryWrapper<AssessmentActivityOption>().eq(AssessmentActivityOption::getActivityId, activityId));

        // 2. 插入新的干部关联
        if (configDTO.getCadreIds() != null && !configDTO.getCadreIds().isEmpty()) {
            List<AssessmentActivityCadre> cadreList = configDTO.getCadreIds().stream()
                    .map(cadreId -> new AssessmentActivityCadre(activityId, cadreId))
                    .collect(Collectors.toList());
            activityCadreService.saveBatch(cadreList);
        }

        // 3. 插入新的选项关联
        if (configDTO.getOptionIds() != null && !configDTO.getOptionIds().isEmpty()) {
            List<AssessmentActivityOption> optionList = configDTO.getOptionIds().stream()
                    .map(optionId -> new AssessmentActivityOption(activityId, optionId))
                    .collect(Collectors.toList());
            activityOptionService.saveBatch(optionList);
        }
    }

    @Override
    public ActivityConfigDetailDTO selectActivityConfigById(Long activityId) {
        ActivityConfigDetailDTO detailDTO = new ActivityConfigDetailDTO();

        // 查询活动基本信息
        AssessmentActivity activity = this.getById(activityId);
        detailDTO.setActivityInfo(activity);

        // 查询已选干部
        List<Long> cadreIds = activityCadreService.list(new LambdaQueryWrapper<AssessmentActivityCadre>()
                        .eq(AssessmentActivityCadre::getActivityId, activityId))
                .stream().map(AssessmentActivityCadre::getCadreId).collect(Collectors.toList());
        detailDTO.setSelectedCadres(cadreIds);

        // 查询已选选项
        List<Long> optionIds = activityOptionService.list(new LambdaQueryWrapper<AssessmentActivityOption>()
                        .eq(AssessmentActivityOption::getActivityId, activityId))
                .stream().map(AssessmentActivityOption::getOptionId).collect(Collectors.toList());
        detailDTO.setSelectedOptions(optionIds);

        return detailDTO;
    }

    /**
     * 开通测评
     * 逻辑调整：支持用户自定义结束时间，若未设置则默认为 10 分钟后。
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AssessmentStartDTO startAssessment(Long activityId) {
        AssessmentActivity activity = this.getById(activityId);
        if (activity == null) {
            throw new ServiceException("活动不存在");
        }
        if (!"0".equals(activity.getStatus())) {
            throw new ServiceException("活动已开通或已结束，请勿重复操作");
        }

        String token = UUID.randomUUID().toString().replaceAll("-", "");

        // --- 逻辑优化：根据时长计算过期时间 ---
        LocalDateTime expireTime;
        if (activity.getDuration() != null && activity.getDuration() > 0) {
            // 使用用户设置的时长计算：当前时间 + N 分钟
            expireTime = LocalDateTime.now().plusMinutes(activity.getDuration());
        } else {
            // 如果没设置，兜底使用默认 10 分钟
            expireTime = LocalDateTime.now().plusMinutes(10);
        }

        activity.setQrExpireTime(Date.from(expireTime.atZone(ZoneId.systemDefault()).toInstant()));
        // --- 逻辑结束 ---

        activity.setStatus("1"); // 1=进行中
        activity.setQrToken(token);

        this.updateById(activity);

        AssessmentStartDTO startDTO = new AssessmentStartDTO();
        startDTO.setToken(token);
        startDTO.setExpireTime(activity.getQrExpireTime());
        return startDTO;
    }

    @Override
    public boolean updateById(AssessmentActivity entity) {
        // 如果状态变更为已结束，生成并保存报告
        if ("2".equals(entity.getStatus())) {
            generateAndSaveReport(entity.getActivityId());
        }
        return super.updateById(entity);
    }

    /**
     * 定时任务调用：关闭已过期的活动
     */
    @Override
    public int closeExpiredActivities() {
        // 1. 查询所有即将过期的活动
        List<AssessmentActivity> expiredList = this.list(new LambdaQueryWrapper<AssessmentActivity>()
                .eq(AssessmentActivity::getStatus, "1")
                .le(AssessmentActivity::getQrExpireTime, new Date()));

        if (expiredList.isEmpty()) {
            return 0;
        }

        // 2. 逐生成报告并更新状态
        int count = 0;
        for (AssessmentActivity activity : expiredList) {
            try {
                // 生成报告
                generateAndSaveReport(activity.getActivityId());

                // 更新状态
                activity.setStatus("2");
                this.baseMapper.updateById(activity);
                count++;
            } catch (Exception e) {
                log.error("关闭过期活动失败: " + activity.getActivityId(), e);
            }
        }
        return count;
    }

    /**
     * 生成并保存活动报告
     */
    private void generateAndSaveReport(Long activityId) {
        try {
            // 检查是否已存在报告
            long count = reportService.count(new LambdaQueryWrapper<AssessmentActivityReport>()
                    .eq(AssessmentActivityReport::getActivityId, activityId));
            if (count > 0) {
                return;
            }

            // 获取统计数据
            AssessmentStatsDTO stats = statsService.getStats(activityId);

            // 保存报告
            AssessmentActivityReport report = new AssessmentActivityReport();
            report.setActivityId(activityId);
            report.setStatsJson(com.alibaba.fastjson2.JSON.toJSONString(stats));
            report.setCreateTime(new Date());
            reportService.save(report);
        } catch (Exception e) {
            log.error("生成活动报告失败: " + activityId, e);
            throw new ServiceException("生成活动报告失败");
        }
    }

    /**
     * 批量删除活动（级联删除关联业务数据）
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteAssessmentActivityByIds(Long[] activityIds) {
        if (activityIds == null || activityIds.length == 0) {
            return 0;
        }
        List<Long> ids = Arrays.asList(activityIds);

        // 删除结果、日志及配置关联
        resultService.remove(new LambdaQueryWrapper<AssessmentResult>().in(AssessmentResult::getActivityId, ids));
        logService.remove(new LambdaQueryWrapper<AssessmentLog>().in(AssessmentLog::getActivityId, ids));
        activityCadreService.remove(new LambdaQueryWrapper<AssessmentActivityCadre>().in(AssessmentActivityCadre::getActivityId, ids));
        activityOptionService.remove(new LambdaQueryWrapper<AssessmentActivityOption>().in(AssessmentActivityOption::getActivityId, ids));
        reportService.remove(new LambdaQueryWrapper<AssessmentActivityReport>().in(AssessmentActivityReport::getActivityId, ids));

        return baseMapper.deleteBatchIds(ids);
    }
}