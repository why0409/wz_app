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
 * 测评日志表对象 assessment_log
 * (防刷用，不继承BaseEntity)
 * * @author wanghongyu
 *
 * @date 2025-11-04
 */
@Data
@TableName("assessment_log")
public class AssessmentLog {
    /**
     * 日志ID
     */
    @TableId(type = IdType.AUTO)
    private Long logId;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 微信OpenID
     */
    private String openId;

    /**
     * 提交时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}