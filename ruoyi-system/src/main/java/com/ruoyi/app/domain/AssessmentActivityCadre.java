package com.ruoyi.app.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 活动干部关联表对象 assessment_activity_cadre
 * (注意: 这是一个关联表，通常没有自己的BaseEntity)
 * * @author wanghongyu
 *
 * @date 2025-11-04
 */
@Data
@TableName("assessment_activity_cadre")
public class AssessmentActivityCadre {
    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 干部ID
     */
    private Long cadreId;

    public AssessmentActivityCadre() {
    }

    public AssessmentActivityCadre(Long activityId, Long cadreId) {
        this.activityId = activityId;
        this.cadreId = cadreId;
    }
}