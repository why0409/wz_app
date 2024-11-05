package com.ruoyi.electricity.domain;

import cn.hutool.core.math.MathUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.utils.StringUtils;
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
public class YdWarningData extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
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

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setMeterNumber(String meterNumber)
    {
        this.meterNumber = meterNumber;
    }

    public String getMeterNumber()
    {
        return meterNumber;
    }
    public void setTotalActivePower(String totalActivePower)
    {
        this.totalActivePower = totalActivePower;
    }

    public String getTotalActivePower()
    {
        return totalActivePower;
    }
    public void setMiniActivePower(String miniActivePower)
    {
        if (StringUtils.isEmpty(miniActivePower)){
            this.miniActivePower = miniActivePower;
            return;
        }

        String[] array = miniActivePower.split(",");
        if (array.length < 7){
            this.miniActivePower = null;
        }else {
            DecimalFormat df = new DecimalFormat("0.0000");
            double avgValue = (Double.parseDouble(array[2])+Double.parseDouble(array[3])+Double.parseDouble(array[4]))/3;
            this.miniActivePower = df.format(avgValue);
        }
    }

    public String getMiniActivePower()
    {
        return miniActivePower;
    }

    public void setVolatilityRange(String volatilityRange)
    {
        this.volatilityRange = volatilityRange;
    }

    public String getVolatilityRange()
    {
        return volatilityRange;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setDataTime(Date dataTime)
    {
        this.dataTime = dataTime;
    }

    public Date getDataTime()
    {
        return dataTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("meterNumber", getMeterNumber())
                .append("totalActivePower", getTotalActivePower())
                .append("miniActivePower", getMiniActivePower())
                .append("volatilityRange", getVolatilityRange())
                .append("status", getStatus())
                .append("dataTime", getDataTime())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
