package com.ruoyi.app.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 服务配置对象 service_config
 *
 * @author ruoyi
 * @date 2024-04-25
 */
public class ServiceConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 唯一标识 */
    @Excel(name = "唯一标识")
    private String uuid;

    /** 排序序号 */
    @Excel(name = "排序序号")
    private Long sortNum;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 是否需要登录（0-否；1-是） */
    @Excel(name = "是否需要登录", readConverterExp = "0=-否；1-是")
    private Long isLogin;

    /** 是否验证权限（0-否；1-是） */
    @Excel(name = "是否验证权限", readConverterExp = "0=-否；1-是")
    private Long isPermission;

    /** 类型（0-小程序；1-H5；2-自定义） */
    @Excel(name = "类型", readConverterExp = "0=-小程序；1-H5；2-自定义")
    private Long type;

    /** 小程序id */
    @Excel(name = "小程序id")
    private String appId;

    /** h5链接 */
    @Excel(name = "h5链接")
    private String h5Url;

    /** 图标 */
    @Excel(name = "图标")
    private String icon;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 是否停用（0-否；1-是） */
    @Excel(name = "是否停用", readConverterExp = "0=-否；1-是")
    private Long isDeactivated;

    /** 是否个人服务（0-否；1-是） */
    @Excel(name = "是否个人服务", readConverterExp = "0=-否；1-是")
    private Long isPersonal;

    /** 类别（市民服务；企业服务；政府服务） */
    @Excel(name = "类别", readConverterExp = "市=民服务；企业服务；政府服务")
    private String category;

    /** 小类别 */
    @Excel(name = "小类别")
    private String subCategory;

    @Excel(name = "二级标题")
    private String subName;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    public String getUuid()
    {
        return uuid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSortNum(Long sortNum)
    {
        this.sortNum = sortNum;
    }

    public Long getSortNum()
    {
        return sortNum;
    }
    public void setIsLogin(Long isLogin)
    {
        this.isLogin = isLogin;
    }

    public Long getIsLogin()
    {
        return isLogin;
    }
    public void setIsPermission(Long isPermission)
    {
        this.isPermission = isPermission;
    }

    public Long getIsPermission()
    {
        return isPermission;
    }
    public void setType(Long type)
    {
        this.type = type;
    }

    public Long getType()
    {
        return type;
    }
    public void setAppId(String appId)
    {
        this.appId = appId;
    }

    public String getAppId()
    {
        return appId;
    }
    public void setH5Url(String h5Url)
    {
        this.h5Url = h5Url;
    }

    public String getH5Url()
    {
        return h5Url;
    }
    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public String getIcon()
    {
        return icon;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }
    public void setIsDeactivated(Long isDeactivated)
    {
        this.isDeactivated = isDeactivated;
    }

    public Long getIsDeactivated()
    {
        return isDeactivated;
    }
    public void setIsPersonal(Long isPersonal)
    {
        this.isPersonal = isPersonal;
    }

    public Long getIsPersonal()
    {
        return isPersonal;
    }
    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getCategory()
    {
        return category;
    }
    public void setSubCategory(String subCategory)
    {
        this.subCategory = subCategory;
    }

    public String getSubCategory()
    {
        return subCategory;
    }

    public String getSubName() {
        return this.subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("uuid", getUuid())
                .append("name", getName())
                .append("sortNum", getSortNum())
                .append("isLogin", getIsLogin())
                .append("isPermission", getIsPermission())
                .append("type", getType())
                .append("appId", getAppId())
                .append("h5Url", getH5Url())
                .append("icon", getIcon())
                .append("description", getDescription())
                .append("isDeactivated", getIsDeactivated())
                .append("isPersonal", getIsPersonal())
                .append("category", getCategory())
                .append("subCategory", getSubCategory())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("subName", getSubName())
                .toString();
    }
}
