package com.ruoyi.safetyHazard.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

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
    @Excel(name = "名称")
    private String userName;

    /** 联系人 */
    @Excel(name = "填报人")
    private String contact;

    /** 微信手机号 */
    @Excel(name = "填报人手机号")
    private String wxPhone;

    /** 地址 */
    private String address;

    /** 类型id */
    private Long typeId;

    /** 属性 */
    private String property;

    /** 排序序号 */
    private Long sortNum;

    private String contactGroup;

    private String role;

    private String typeUuid;

    @Excel(name = "（最新）填报状态", readConverterExp = "1=部分填报,2=全部填报,3=督察已回复,4=流程结束")
    private String status;

    @Excel(name = "（最新）填报时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date maxManifestUpdateTime;

    @Excel(name = "督查回复")
    private String dcReply;

    /** 自查回复 */
    @Excel(name = "自查回复")
    private String zcReply;

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

    public Date getMaxManifestUpdateTime() {
        return this.maxManifestUpdateTime;
    }

    public void setMaxManifestUpdateTime(Date maxManifestUpdateTime) {
        this.maxManifestUpdateTime = maxManifestUpdateTime;
    }

    public String getDcReply() {
        return this.dcReply;
    }

    public void setDcReply(String dcReply) {
        this.dcReply = dcReply;
    }

    public String getZcReply() {
        return this.zcReply;
    }

    public void setZcReply(String zcReply) {
        this.zcReply = zcReply;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
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
