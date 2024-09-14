package com.ruoyi.safetyHazard.domain;

import com.alibaba.fastjson2.JSONArray;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 隐患排查-用户评价对象 safety_hazard_user_evaluate
 *
 * @author ruoyi
 * @date 2024-09-12
 */
@Data
@TableName(value = "safety_hazard_user_evaluate", autoResultMap = true)
public class SafetyHazardUserEvaluate implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 附件 */
    @Excel(name = "附件")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private JSONArray annex;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
