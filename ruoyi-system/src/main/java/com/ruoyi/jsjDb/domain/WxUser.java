package com.ruoyi.jsjDb.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 用户对象 WxUser
 *
 * @author lgh
 * @date 2022-11-22
 */
public class WxUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String id;

    /** 用户名 */
    @Excel(name = "用户名")
    private String username;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 真实姓名 */
    @Excel(name = "真实姓名")
    private String realName;

    /** 手机号 */
    @Excel(name = "手机号")
    private String mobile;
    /** 手机号 */
    @Excel(name = "手机号")
    private String wechatMobile;

    /** 头像 */
    @Excel(name = "头像")
    private String avatar;

    /** 帐号状态（0正常 1停用,-1已删除） */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用,-1已删除")
    private Long status;

    /** 是否静音：0否，1是 */
    @Excel(name = "是否静音：0否，1是")
    private Long silence;

    /** 部门id */
    @Excel(name = "部门id")
    private Long deptId;

    /** 最后一次登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后一次登录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastLogin;

    /** 最后登录ip */
    @Excel(name = "最后登录ip")
    private String loginIp;

    /** 账号创建者用户id */
    @Excel(name = "账号创建者用户id")
    private Long creatorId;

    /** 最后修改者用户id */
    @Excel(name = "最后修改者用户id")
    private Long updateId;

    private String videoPermissions;

    public String getVideoPermissions() {
        return videoPermissions;
    }

    public void setVideoPermissions(String videoPermissions) {
        this.videoPermissions = videoPermissions;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Excel(name = "标签")
    private String label;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }
    public void setRealName(String realName)
    {
        this.realName = realName;
    }

    public String getRealName()
    {
        return realName;
    }
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getMobile()
    {
        return mobile;
    }
    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getAvatar()
    {
        return avatar;
    }
    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }
    public void setSilence(Long silence)
    {
        this.silence = silence;
    }

    public Long getSilence()
    {
        return silence;
    }
    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId()
    {
        return deptId;
    }
    public void setLastLogin(Date lastLogin)
    {
        this.lastLogin = lastLogin;
    }

    public Date getLastLogin()
    {
        return lastLogin;
    }
    public void setLoginIp(String loginIp)
    {
        this.loginIp = loginIp;
    }

    public String getLoginIp()
    {
        return loginIp;
    }
    public void setCreatorId(Long creatorId)
    {
        this.creatorId = creatorId;
    }

    public Long getCreatorId()
    {
        return creatorId;
    }
    public void setUpdateId(Long updateId)
    {
        this.updateId = updateId;
    }

    public Long getUpdateId()
    {
        return updateId;
    }

    public String getWechatMobile() {
        return wechatMobile;
    }

    public void setWechatMobile(String wechatMobile) {
        this.wechatMobile = wechatMobile;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("username", getUsername())
            .append("password", getPassword())
            .append("realName", getRealName())
            .append("mobile", getMobile())
            .append("wechatMobile", getWechatMobile())
            .append("avatar", getAvatar())
            .append("status", getStatus())
            .append("silence", getSilence())
            .append("deptId", getDeptId())
            .append("lastLogin", getLastLogin())
            .append("loginIp", getLoginIp())
            .append("createBy", getCreateBy())
            .append("creatorId", getCreatorId())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateId", getUpdateId())
            .append("updateTime", getUpdateTime())
                .append("label", getLabel())
            .toString();
    }
}
