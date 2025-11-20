package com.ruoyi.app.service;


import com.ruoyi.app.domain.dto.AssessmentStatsDTO;

/**
 * 统计Service接口
 * * @author wanghongyu
 *
 * @date 2025-11-04
 */
public interface IAssessmentStatsService {

    /**
     * 获取统计结果
     *
     * @param activityId 活动ID
     * @return 统计DTO
     */
    public AssessmentStatsDTO getStats(Long activityId);
}