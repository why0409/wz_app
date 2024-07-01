package com.ruoyi.activities.domain;

import cn.hutool.core.util.DesensitizedUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 民宿体验季活动对象 homestay_registered_info
 *
 * @author ruoyi
 * @date 2024-06-19
 */
public class HomestayRegisteredInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 微信号码 */
    @Excel(name = "微信号码")
    private String wxPhone;

    /** 注册号码 */
    @Excel(name = "注册号码")
    private String contactPhone;

    /** 身份证号码 */
    @Excel(name = "身份证号码")
    private String idNumber;

    /** 是否中奖 */
    @Excel(name = "是否中奖")
    private String isWin;

    /** 劵码状态 */
    @Excel(name = "劵码状态")
    private String winStatus;

    /** 活动id */
    @Excel(name = "活动id")
    private Long activitiesId;

    /** 核验劵码民宿id */
    @Excel(name = "核验劵码民宿id")
    private Long homestayId;

    /** 验码手机号 */
    @Excel(name = "验码手机号")
    private String verifyPhone;

    /** 验码时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "验码时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date verifyTime;

    private String smsCode;

    @Excel(name = "摇号时是否可查看")
    private String isLotteryingShow;

    public String getIsLotteryingShow() {
        return this.isLotteryingShow;
    }

    public void setIsLotteryingShow(String isLotteryingShow) {
        this.isLotteryingShow = isLotteryingShow;
    }

    public String getSmsCode() {
        return this.smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

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
    public void setWxPhone(String wxPhone)
    {
        this.wxPhone = wxPhone;
    }

    public String getWxPhone()
    {
        return wxPhone;
    }
    public void setContactPhone(String contactPhone)
    {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone()
    {
        return contactPhone;
    }
    public void setIdNumber(String idNumber)
    {
        this.idNumber = idNumber;
    }

    public String getIdNumber()
    {
        return idNumber;
    }
    public void setIsWin(String isWin)
    {
        this.isWin = isWin;
    }

    public String getIsWin()
    {
        return isWin;
    }
    public void setWinStatus(String winStatus)
    {
        this.winStatus = winStatus;
    }

    public String getWinStatus()
    {
        return winStatus;
    }
    public void setActivitiesId(Long activitiesId)
    {
        this.activitiesId = activitiesId;
    }

    public Long getActivitiesId()
    {
        return activitiesId;
    }
    public void setHomestayId(Long homestayId)
    {
        this.homestayId = homestayId;
    }

    public Long getHomestayId()
    {
        return homestayId;
    }
    public void setVerifyPhone(String verifyPhone)
    {
        this.verifyPhone = verifyPhone;
    }

    public String getVerifyPhone()
    {
        return verifyPhone;
    }
    public void setVerifyTime(Date verifyTime)
    {
        this.verifyTime = verifyTime;
    }

    public Date getVerifyTime()
    {
        return verifyTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.JSON_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("wxPhone", getWxPhone())
                .append("contactPhone", getContactPhone())
                .append("idNumber", getIdNumber())
                .append("isWin", getIsWin())
                .append("winStatus", getWinStatus())
                .append("activitiesId", getActivitiesId())
                .append("homestayId", getHomestayId())
                .append("verifyPhone", getVerifyPhone())
                .append("verifyTime", getVerifyTime())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
