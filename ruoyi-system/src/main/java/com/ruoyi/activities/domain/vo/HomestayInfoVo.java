package com.ruoyi.activities.domain.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 民宿信息对象 homestay_info
 *
 * @author ruoyi
 * @date 2024-06-20
 */
public class HomestayInfoVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 排序序号 */
    @Excel(name = "排序序号")
    private Long sortNum;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 简介 */
    @Excel(name = "简介")
    private String introduction;

    /** 联系人姓名 */
    @Excel(name = "联系人姓名")
    private String contactName;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String contactPhones;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 图片 */
    @Excel(name = "图片")
    private String picture;

    /** 封面 */
    @Excel(name = "封面")
    private String cover;

    /** 核验电话 */
    @Excel(name = "核验电话")
    private String verifyPhones;

    private Integer passengerNum;

    private Integer offlineNum;

    private Integer totalNum;


    public Integer getPassengerNum() {
        return this.passengerNum;
    }

    public void setPassengerNum(Integer passengerNum) {
        this.passengerNum = passengerNum;
    }

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
    public void setIntroduction(String introduction)
    {
        this.introduction = introduction;
    }

    public String getIntroduction()
    {
        return introduction;
    }
    public void setContactName(String contactName)
    {
        this.contactName = contactName;
    }

    public String getContactName()
    {
        return contactName;
    }
    public void setContactPhones(String contactPhones)
    {
        this.contactPhones = contactPhones;
    }

    public String getContactPhones()
    {
        return contactPhones;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }
    public void setPicture(String picture)
    {
        this.picture = picture;
    }

    public String getPicture()
    {
        return picture;
    }
    public void setCover(String cover)
    {
        this.cover = cover;
    }

    public String getCover()
    {
        return cover;
    }
    public void setVerifyPhones(String verifyPhones)
    {
        this.verifyPhones = verifyPhones;
    }

    public String getVerifyPhones()
    {
        return verifyPhones;
    }

    public Integer getOfflineNum() {
        return this.offlineNum;
    }

    public void setOfflineNum(Integer offlineNum) {
        this.offlineNum = offlineNum;
    }

    public Integer getTotalNum() {
        return this.totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("sortNum", getSortNum())
                .append("name", getName())
                .append("introduction", getIntroduction())
                .append("contactName", getContactName())
                .append("contactPhones", getContactPhones())
                .append("address", getAddress())
                .append("picture", getPicture())
                .append("cover", getCover())
                .append("verifyPhones", getVerifyPhones())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
