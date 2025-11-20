package com.ruoyi.app.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ruoyi.app.domain.AssessmentActivity;
import com.ruoyi.app.domain.AssessmentActivityCadre;
import com.ruoyi.app.domain.AssessmentActivityOption;
import com.ruoyi.app.domain.dto.ActivityConfigDetailDTO;
import com.ruoyi.app.domain.dto.AssessmentConfigDTO;
import com.ruoyi.app.domain.dto.AssessmentStartDTO;
import com.ruoyi.app.mapper.AssessmentActivityMapper;
import com.ruoyi.app.service.IAssessmentActivityCadreService;
import com.ruoyi.app.service.IAssessmentActivityOptionService;
import com.ruoyi.app.service.IAssessmentActivityService;
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
 *
 * @date 2025-11-04
 */
@Service
public class AssessmentActivityServiceImpl extends ServiceImpl<AssessmentActivityMapper, AssessmentActivity> implements IAssessmentActivityService {
    @Autowired
    private IAssessmentActivityCadreService activityCadreService;

    @Autowired
    private IAssessmentActivityOptionService activityOptionService;

    @Override
    public List<AssessmentActivity> selectAssessmentActivityList(AssessmentActivity assessmentActivity) {
        LambdaQueryWrapper<AssessmentActivity> lqw = new LambdaQueryWrapper<AssessmentActivity>();
        lqw.like(StringUtils.isNotEmpty(assessmentActivity.getActivityName()), AssessmentActivity::getActivityName, assessmentActivity.getActivityName());
        lqw.eq(StringUtils.isNotEmpty(assessmentActivity.getStatus()), AssessmentActivity::getStatus, assessmentActivity.getStatus());
        lqw.eq(StringUtils.isNotEmpty(assessmentActivity.getActivityYear()), AssessmentActivity::getActivityYear, assessmentActivity.getActivityYear());
        return this.list(lqw);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void configActivity(AssessmentConfigDTO configDTO) {
        Long activityId = configDTO.getActivityId();

        // 1. 删除旧的干部关联
        activityCadreService.remove(new LambdaQueryWrapper<AssessmentActivityCadre>()
                .eq(AssessmentActivityCadre::getActivityId, activityId));

        // 2. 删除旧的选项关联
        activityOptionService.remove(new LambdaQueryWrapper<AssessmentActivityOption>()
                .eq(AssessmentActivityOption::getActivityId, activityId));

        // 3. 批量插入新的干部关联
        if (configDTO.getCadreIds() != null && !configDTO.getCadreIds().isEmpty()) {
            List<AssessmentActivityCadre> cadreList = configDTO.getCadreIds().stream()
                    .map(cadreId -> new AssessmentActivityCadre(activityId, cadreId))
                    .collect(Collectors.toList());
            activityCadreService.saveBatch(cadreList);
        }

        // 4. 批量插入新的选项关联
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

        // 1. 查询活动信息
        AssessmentActivity activity = this.getById(activityId);
        detailDTO.setActivityInfo(activity);

        // 2. 查询已选干部
        List<Long> cadreIds = activityCadreService.list(new LambdaQueryWrapper<AssessmentActivityCadre>()
                        .eq(AssessmentActivityCadre::getActivityId, activityId))
                .stream()
                .map(AssessmentActivityCadre::getCadreId)
                .collect(Collectors.toList());
        detailDTO.setSelectedCadres(cadreIds);

        // 3. 查询已选选项
        List<Long> optionIds = activityOptionService.list(new LambdaQueryWrapper<AssessmentActivityOption>()
                        .eq(AssessmentActivityOption::getActivityId, activityId))
                .stream()
                .map(AssessmentActivityOption::getOptionId)
                .collect(Collectors.toList());
        detailDTO.setSelectedOptions(optionIds);

        return detailDTO;
    }

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
        LocalDateTime expireTime = LocalDateTime.now().plusMinutes(10);
//        LocalDateTime expireTime = LocalDateTime.now().plusHours(10);

        activity.setStatus("1"); // 1=进行中
        activity.setQrToken(token);
        activity.setQrExpireTime(Date.from(expireTime.atZone(ZoneId.systemDefault()).toInstant()));

        this.updateById(activity);

        AssessmentStartDTO startDTO = new AssessmentStartDTO();
        startDTO.setToken(token);
        startDTO.setExpireTime(activity.getQrExpireTime());
        return startDTO;
    }

    /**
     * (定时任务调用)
     * 关闭已过期的测评活动
     * 查询 状态为 '1' (进行中) 且 过期时间 <= 当前时间 的活动，将其状态改为 '2' (已结束)
     */
    @Override
    public int closeExpiredActivities() {
        LambdaUpdateWrapper<AssessmentActivity> updateWrapper = new LambdaUpdateWrapper<>();

        updateWrapper
                // 目标：将状态设置为 '2' (已结束)
                .set(AssessmentActivity::getStatus, "2")
                // 条件1：状态必须是 '1' (进行中)
                .eq(AssessmentActivity::getStatus, "1")
                // 条件2：过期时间 <= 当前时间
                .le(AssessmentActivity::getQrExpireTime, new Date());

        // 执行批量更新
        // baseMapper.update(null, updateWrapper) 表示不通过实体更新，仅使用 Wrapper 条件更新

        return baseMapper.update(null, updateWrapper);
    }
}