package com.ruoyi.electricity.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用电预警阈值管理对象 yd_warning_threshold
 *
 * @author ruoyi
 * @date 2024-11-05
 */
public class YdWarningThreshold extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 阈值 */
    @Excel(name = "阈值")
    private Double threshold;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setThreshold(Double threshold)
    {
        this.threshold = threshold;
    }

    public Double getThreshold()
    {
        return threshold;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("status", getStatus())
            .append("threshold", getThreshold())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
