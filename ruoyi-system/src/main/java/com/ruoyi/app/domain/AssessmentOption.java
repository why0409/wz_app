package com.ruoyi.app.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 测评选项表对象 assessment_option
 * * @author wanghongyu
 *
 * @date 2025-11-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("assessment_option")
public class AssessmentOption extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 选项ID
     */
    @TableId(type = IdType.AUTO)
    private Long optionId;

    /**
     * 选项内容
     */
    @Excel(name = "选项内容")
    private String optionContent;

    /**
     * 选项类型 (1=正面, 2=负面)
     */
    @Excel(name = "选项类型")
    private String optionType;

    /**
     * 状态 (0=正常, 1=停用)
     */
//    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;
}