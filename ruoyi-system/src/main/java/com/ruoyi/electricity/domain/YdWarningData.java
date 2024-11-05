package com.ruoyi.electricity.domain;

import cn.hutool.core.math.MathUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.utils.StringUtils;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.text.DecimalFormat;
import java.util.Date;

/**
 * 用电预警数据对象 yd_warning_data
 *
 * @author ruoyi
 * @date 2024-11-04
 */
@Data
@TableName("yd_warning_data")
public class YdWarningData extends Model
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    @TableId(value = "id",type= IdType.AUTO)
    private Long id;

    /** 电表号 */
    @Excel(name = "电表号")
    private String meterNumber;

    /** 总功率(w) */
    @Excel(name = "总功率(w)")
    private String totalActivePower;

    /** 近三日最小功率(w) */
    @Excel(name = "近三日最小功率(w)")
    private String miniActivePower;

    /** 波动幅度 */
    @Excel(name = "波动幅度")
    private String volatilityRange;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 数据时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "数据时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date dataTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
