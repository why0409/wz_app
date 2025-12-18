package com.ruoyi.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.app.domain.AssessmentActivity;
import com.ruoyi.app.domain.dto.ActivityConfigDetailDTO;
import com.ruoyi.app.domain.dto.AssessmentConfigDTO;
import com.ruoyi.app.domain.dto.AssessmentStartDTO;

import java.util.List;

/**
 * 测评活动Service接口
 * * @author wanghongyu
 *
 * @date 2025-11-04
 */
public interface IAssessmentActivityService extends IService<AssessmentActivity> {
    /**
     * 查询测评活动列表
     * * @param assessmentActivity 测评活动
     *
     * @return 测评活动集合
     */
    public List<AssessmentActivity> selectAssessmentActivityList(AssessmentActivity assessmentActivity);

    /**
     * 配置活动
     *
     * @param configDTO 配置信息
     */
    public void configActivity(AssessmentConfigDTO configDTO);

    /**
     * 获取活动配置详情
     *
     * @param activityId 活动ID
     * @return 详情
     */
    public ActivityConfigDetailDTO selectActivityConfigById(Long activityId);

    /**
     * 开通测评
     *
     * @param activityId 活动ID
     * @return Token和过期时间
     */
    public AssessmentStartDTO startAssessment(Long activityId);

    /**
     * (新增) 定时任务调用：关闭所有已过期的测评活动
     * @return 更新的条数
     */
    public int closeExpiredActivities();

    /**
     * 批量删除测评活动 (级联删除关联数据)
     * @param activityIds 需要删除的活动ID
     * @return 结果
     */
    public int deleteAssessmentActivityByIds(Long[] activityIds);
}