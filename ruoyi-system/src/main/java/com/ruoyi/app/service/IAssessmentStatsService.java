package com.ruoyi.app.service;


import com.ruoyi.app.domain.dto.AssessmentStatsDTO;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 获取统计结果 (年份区间)
     *
     * @param startYear 开始年份
     * @param endYear 结束年份
     * @param cadreName 干部姓名
     * @return 统计DTO
     */
    public AssessmentStatsDTO getStatsByYearRange(String startYear, String endYear, String cadreName);

    /**
     * 导入历史测评数据
     *
     * @param file 导入文件
     * @param year 年份
     * @param type 评价类型 (1=正面, 2=负面)
     * @param operName 操作人
     * @return 结果消息
     */
    public String importHistoryData(MultipartFile file, String year, String type, String operName) throws Exception;
}