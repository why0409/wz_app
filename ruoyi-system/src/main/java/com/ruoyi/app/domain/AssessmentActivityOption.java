package com.ruoyi.app.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 活动选项关联表对象 assessment_activity_option
 * (注意: 这是一个关联表，通常没有自己的BaseEntity)
 * * @author wanghongyu
 *
 * @date 2025-11-04
 */
@Data
@TableName("assessment_activity_option")
public class AssessmentActivityOption {
    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 选项ID
     */
    private Long optionId;

    public AssessmentActivityOption() {
    }

    public AssessmentActivityOption(Long activityId, Long optionId) {
        this.activityId = activityId;
        this.optionId = optionId;
    }
}