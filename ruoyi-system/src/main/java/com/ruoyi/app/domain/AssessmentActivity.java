package com.ruoyi.app.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 测评活动表对象 assessment_activity
 * * @author wanghongyu
 *
 * @date 2025-11-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("assessment_activity")
public class AssessmentActivity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 活动ID
     */
    @TableId(type = IdType.AUTO)
    private Long activityId;

    /**
     * 活动名称
     */
    @Excel(name = "活动名称")
    private String activityName;

    /** 活动年份 */
    @Excel(name = "活动年份")
    private String activityYear;

    /**
     * 状态 (0=未开始, 1=进行中, 2=已结束)
     */
    @Excel(name = "状态", readConverterExp = "0=未开始,1=进行中,2=已结束")
    private String status;

    /**
     * 测评二维码唯一凭证
     */
    private String qrToken;

    /**
     * 二维码失效时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date qrExpireTime;
}