package com.ruoyi.electricity.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用电企业信息对象 yd_enterprise_info
 *
 * @author ruoyi
 * @date 2024-11-04
 */
public class YdEnterpriseInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 排序序号 */
    @Excel(name = "排序序号")
    private Long sortNum;

    /** 企业名称 */
    @Excel(name = "企业名称")
    private String name;

    /** 电表号 */
    @Excel(name = "电表号")
    private String meterNumber;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contact;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String phone;

    /** 监控列表 */
    @Excel(name = "监控列表")
    private String monitors;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setSortNum(Long sortNum)
    {
        this.sortNum = sortNum;
    }

    public Long getSortNum()
    {
        return sortNum;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setMeterNumber(String meterNumber)
    {
        this.meterNumber = meterNumber;
    }

    public String getMeterNumber()
    {
        return meterNumber;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }
    public void setContact(String contact)
    {
        this.contact = contact;
    }

    public String getContact()
    {
        return contact;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setMonitors(String monitors)
    {
        this.monitors = monitors;
    }

    public String getMonitors()
    {
        return monitors;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sortNum", getSortNum())
            .append("name", getName())
            .append("meterNumber", getMeterNumber())
            .append("status", getStatus())
            .append("address", getAddress())
            .append("contact", getContact())
            .append("phone", getPhone())
            .append("monitors", getMonitors())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
