package com.ruoyi.safetyHazard.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 安全隐患-用户管理对象 safety_hazard_user
 *
 * @author ruoyi
 * @date 2024-08-08
 */
public class SafetyHazardUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户id */
    private Long userId;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contact;

    /** 微信手机号 */
    @Excel(name = "微信手机号")
    private String wxPhone;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 类型id */
    @Excel(name = "类型id")
    private Long typeId;

    /** 属性 */
    @Excel(name = "属性")
    private String property;

    /** 排序序号 */
    @Excel(name = "排序序号")
    private Long sortNum;

    @Excel(name = "联系人组")
    private String contactGroup;

    @Excel(name = "角色")
    private String role;

    private String typeUuid;

    private String maxManifestUpdateTime;

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setContact(String contact)
    {
        this.contact = contact;
    }

    public String getContact()
    {
        return contact;
    }
    public void setWxPhone(String wxPhone)
    {
        this.wxPhone = wxPhone;
    }

    public String getWxPhone()
    {
        return wxPhone;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }
    public void setTypeId(Long typeId)
    {
        this.typeId = typeId;
    }

    public Long getTypeId()
    {
        return typeId;
    }
    public void setProperty(String property)
    {
        this.property = property;
    }

    public String getProperty()
    {
        return property;
    }
    public void setSortNum(Long sortNum)
    {
        this.sortNum = sortNum;
    }

    public Long getSortNum()
    {
        return sortNum;
    }

    public String getContactGroup() {
        return this.contactGroup;
    }

    public void setContactGroup(String contactGroup) {
        this.contactGroup = contactGroup;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTypeUuid() {
        return this.typeUuid;
    }

    public void setTypeUuid(String typeUuid) {
        this.typeUuid = typeUuid;
    }

    public String getMaxManifestUpdateTime() {
        return this.maxManifestUpdateTime;
    }

    public void setMaxManifestUpdateTime(String maxManifestUpdateTime) {
        this.maxManifestUpdateTime = maxManifestUpdateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("userId", getUserId())
                .append("userName", getUserName())
                .append("contact", getContact())
                .append("wxPhone", getWxPhone())
                .append("address", getAddress())
                .append("typeId", getTypeId())
                .append("property", getProperty())
                .append("sortNum", getSortNum())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
