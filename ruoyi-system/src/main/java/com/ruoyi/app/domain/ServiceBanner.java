package com.ruoyi.app.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 服务banner图轮播对象 service_banner
 *
 * @author ruoyi
 * @date 2024-04-30
 */
public class ServiceBanner extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 排序序号 */
    @Excel(name = "排序序号")
    private Long sortNum;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 类型（0-小程序；1-H5；2-自定义） */
    @Excel(name = "类型", readConverterExp = "0=-小程序；1-H5；2-自定义")
    private Long type;

    /** 小程序id */
    @Excel(name = "小程序id")
    private String appId;

    /** 小程序页面地址 */
    @Excel(name = "小程序页面地址")
    private String appUrl;

    /** h5链接 */
    @Excel(name = "h5链接")
    private String h5Url;

    /** 图片 */
    @Excel(name = "图片")
    private String picture;

    /** 是否停用（0-否；1-是） */
    @Excel(name = "是否停用", readConverterExp = "0=-否；1-是")
    private Long isDeactivated;

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
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
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
    public void setAppUrl(String appUrl)
    {
        this.appUrl = appUrl;
    }

    public String getAppUrl()
    {
        return appUrl;
    }
    public void setH5Url(String h5Url)
    {
        this.h5Url = h5Url;
    }

    public String getH5Url()
    {
        return h5Url;
    }
    public void setPicture(String picture)
    {
        this.picture = picture;
    }

    public String getPicture()
    {
        return picture;
    }
    public void setIsDeactivated(Long isDeactivated)
    {
        this.isDeactivated = isDeactivated;
    }

    public Long getIsDeactivated()
    {
        return isDeactivated;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("sortNum", getSortNum())
                .append("title", getTitle())
                .append("type", getType())
                .append("appId", getAppId())
                .append("appUrl", getAppUrl())
                .append("h5Url", getH5Url())
                .append("picture", getPicture())
                .append("isDeactivated", getIsDeactivated())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
