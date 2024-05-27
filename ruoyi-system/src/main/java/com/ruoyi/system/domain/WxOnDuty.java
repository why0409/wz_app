package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 今日值班对象 wx_on_duty
 * 
 * @author ruoyi
 * @date 2023-02-16
 */
public class WxOnDuty extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private String uuid;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 职位 */
    @Excel(name = "职位")
    private String role;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dutyTime;

    /** 是否今日值班 */
    @Excel(name = "是否今日值班")
    private Integer isToday;

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    public String getUuid()
    {
        return uuid;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setRole(String role)
    {
        this.role = role;
    }

    public String getRole()
    {
        return role;
    }
    public void setDutyTime(Date dutyTime)
    {
        this.dutyTime = dutyTime;
    }

    public Date getDutyTime()
    {
        return dutyTime;
    }
    public void setIsToday(Integer isToday)
    {
        this.isToday = isToday;
    }

    public Integer getIsToday()
    {
        return isToday;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("uuid", getUuid())
                .append("name", getName())
                .append("phone", getPhone())
                .append("role", getRole())
                .append("dutyTime", getDutyTime())
                .append("isToday", getIsToday())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
