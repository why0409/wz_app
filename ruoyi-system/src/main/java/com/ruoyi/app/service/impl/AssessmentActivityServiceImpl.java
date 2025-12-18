package com.ruoyi.app.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ruoyi.app.domain.*;
import com.ruoyi.app.domain.dto.ActivityConfigDetailDTO;
import com.ruoyi.app.domain.dto.AssessmentConfigDTO;
import com.ruoyi.app.domain.dto.AssessmentStartDTO;
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

        // --- 逻辑优化：处理结束时间 ---
        if (activity.getQrExpireTime() == null) {
            // 如果新增时未设置结束时间，默认 10 分钟过期
            LocalDateTime expireTime = LocalDateTime.now().plusMinutes(10);
            activity.setQrExpireTime(Date.from(expireTime.atZone(ZoneId.systemDefault()).toInstant()));
        } else {
            // 如果已设置结束时间，校验该时间是否已过期
            if (activity.getQrExpireTime().before(new Date())) {
                throw new ServiceException("预设的结束时间已过期，请在“修改”中调整后再开通");
            }
        }
        // --- 结束 ---

        activity.setStatus("1"); // 设置为进行中
        activity.setQrToken(token);

        this.updateById(activity);

        AssessmentStartDTO startDTO = new AssessmentStartDTO();
        startDTO.setToken(token);
        startDTO.setExpireTime(activity.getQrExpireTime());
        return startDTO;
    }

    /**
     * 定时任务调用：关闭已过期的活动
     */
    @Override
    public int closeExpiredActivities() {
        LambdaUpdateWrapper<AssessmentActivity> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(AssessmentActivity::getStatus, "2") // 2=已结束
                .eq(AssessmentActivity::getStatus, "1")     // 仅处理进行中的
                .le(AssessmentActivity::getQrExpireTime, new Date()); // 过期时间 <= 当前时间
        return baseMapper.update(null, updateWrapper);
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

        return baseMapper.deleteBatchIds(ids);
    }
}