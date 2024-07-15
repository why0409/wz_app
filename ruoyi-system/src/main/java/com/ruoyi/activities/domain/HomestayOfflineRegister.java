package com.ruoyi.activities.domain;

import java.util.Date;

import cn.hutool.core.util.DesensitizedUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 民宿活动线下劵注册信息对象 homestay_offline_register
 *
 * @author ruoyi
 * @date 2024-07-10
 */
public class HomestayOfflineRegister extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    /** 身份证号码 */
    @Excel(name = "身份证号码")
    private String idNumber;

    /** 核验劵码民宿id */
    @Excel(name = "核验劵码民宿id")
    private Long homestayId;

    /** 活动id */
    @Excel(name = "活动id")
    private Long activitiesId;

    /** 入住房号 */
    @Excel(name = "入住房号")
    private String roomNumber;

    /** 入住时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入住时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inTime;

    /** 验码手机号 */
    @Excel(name = "验码手机号")
    private String verifyPhone;

    /** 纸质序号 */
    @Excel(name = "纸质序号")
    private String serialNumber;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
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
    public void setIdNumber(String idNumber)
    {
        this.idNumber = idNumber;
    }

    public String getIdNumber()
    {
        return idNumber;
    }
    public void setHomestayId(Long homestayId)
    {
        this.homestayId = homestayId;
    }

    public Long getHomestayId()
    {
        return homestayId;
    }
    public void setActivitiesId(Long activitiesId)
    {
        this.activitiesId = activitiesId;
    }

    public Long getActivitiesId()
    {
        return activitiesId;
    }
    public void setRoomNumber(String roomNumber)
    {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber()
    {
        return roomNumber;
    }
    public void setInTime(Date inTime)
    {
        this.inTime = inTime;
    }

    public Date getInTime()
    {
        return inTime;
    }
    public void setVerifyPhone(String verifyPhone)
    {
        this.verifyPhone = verifyPhone;
    }

    public String getVerifyPhone()
    {
        return verifyPhone;
    }
    public void setSerialNumber(String serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber()
    {
        return serialNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("phone", getPhone())
                .append("idNumber", getIdNumber())
                .append("homestayId", getHomestayId())
                .append("activitiesId", getActivitiesId())
                .append("roomNumber", getRoomNumber())
                .append("inTime", getInTime())
                .append("verifyPhone", getVerifyPhone())
                .append("serialNumber", getSerialNumber())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
