package com.ruoyi.app.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 被测评干部表对象 assessment_cadre
 * * @author wanghongyu
 *
 * @date 2025-11-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("assessment_cadre")
public class AssessmentCadre extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 干部ID
     */
    @TableId(type = IdType.AUTO)
    private Long cadreId;

    /**
     * 干部姓名
     */
    @Excel(name = "姓名")
    private String cadreName;

    /**
     * 单位名称
     */
    @Excel(name = "单位")
    private String unitName;

    /**
     * 考核职务
     */
    @Excel(name = "考核职务")
    private String postTitle;

    /**
     * 状态 (0=正常, 1=停用)
     */
//    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;
}