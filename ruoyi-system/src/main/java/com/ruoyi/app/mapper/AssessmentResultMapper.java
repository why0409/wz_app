package com.ruoyi.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.app.domain.AssessmentResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 测评结果Mapper接口
 * * @author wanghongyu
 *
 * @date 2025-11-04
 */
public interface AssessmentResultMapper extends BaseMapper<AssessmentResult> {
    /**
     * 按活动ID统计结果
     *
     * @param activityId 活动ID
     * @return 统计列表
     */
    List<Map<String, Object>> selectStatsByActivityId(@Param("activityId") Long activityId);

    /**
     * 按年份区间统计结果（按选项名称聚合）
     *
     * @param startYear 开始年份
     * @param endYear   结束年份
     * @param cadreName 干部姓名（可选模糊查询）
     * @return 统计列表
     */
    List<Map<String, Object>> selectStatsByYearRange(@Param("startYear") String startYear,
                                                     @Param("endYear") String endYear,
                                                     @Param("cadreName") String cadreName);
}