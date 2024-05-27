package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 招资引商对象 wx_zsyz_table
 * 
 * @author lgh
 * @date 2022-11-22
 */
public class WxZsyzTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String xmmc;

    /** 申报单位 */
    @Excel(name = "申报单位")
    private String sbdw;

    /** 项目概况 */
    @Excel(name = "项目概况")
    private String xmgk;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String phone;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date creatTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setXmmc(String xmmc) 
    {
        this.xmmc = xmmc;
    }

    public String getXmmc() 
    {
        return xmmc;
    }
    public void setSbdw(String sbdw) 
    {
        this.sbdw = sbdw;
    }

    public String getSbdw() 
    {
        return sbdw;
    }
    public void setXmgk(String xmgk) 
    {
        this.xmgk = xmgk;
    }

    public String getXmgk() 
    {
        return xmgk;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setCreatTime(Date creatTime) 
    {
        this.creatTime = creatTime;
    }

    public Date getCreatTime() 
    {
        return creatTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("xmmc", getXmmc())
            .append("sbdw", getSbdw())
            .append("xmgk", getXmgk())
            .append("phone", getPhone())
            .append("creatTime", getCreatTime())
            .toString();
    }
}
