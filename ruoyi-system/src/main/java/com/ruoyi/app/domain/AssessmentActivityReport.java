package com.ruoyi.app.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 测评活动报告表对象 assessment_activity_report
 * * @author wanghongyu
 * @date 2025-12-20
 */
@Data
@TableName("assessment_activity_report")
public class AssessmentActivityReport {
    private static final long serialVersionUID = 1L;

    /**
     * 报告ID
     */
    @TableId(type = IdType.AUTO)
    private Long reportId;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 统计结果JSON
     */
    private String statsJson;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
