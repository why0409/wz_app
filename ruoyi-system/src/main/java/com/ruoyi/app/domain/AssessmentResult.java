package com.ruoyi.app.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

/**
 * 测评结果表对象 assessment_result
 * (匿名存储，不继承BaseEntity)
 * * @author wanghongyu
 *
 * @date 2025-11-04
 */
@Data
@TableName("assessment_result")
public class AssessmentResult {
    /**
     * 结果ID
     */
    @TableId(type = IdType.AUTO)
    private Long resultId;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 被测评干部ID
     */
    private Long cadreId;

    /**
     * 被选中的选项ID
     */
    private Long optionId;

    /**
     * 选项类型 (1=正面, 2=负面)
     */
    private String optionType;

    /**
     * 提交时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}