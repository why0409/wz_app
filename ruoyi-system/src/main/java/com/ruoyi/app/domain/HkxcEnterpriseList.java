package com.ruoyi.app.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 航空新城-企业列对象 hkxc_enterprise_list
 *
 * @author ruoyi
 * @date 2024-05-14
 */
public class HkxcEnterpriseList extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 图片 */
    @Excel(name = "图片")
    private String picture;

    /** 排序序号 */
    @Excel(name = "排序序号")
    private Long sortNum;

    /** 经度 */
    @Excel(name = "经度")
    private Double lon;

    /** 纬度 */
    @Excel(name = "纬度")
    private Double lat;

    /** 链接 */
    @Excel(name = "链接")
    private String link;

    /** 是否展示 */
    @Excel(name = "是否展示")
    private Long isShow;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setPicture(String picture)
    {
        this.picture = picture;
    }

    public String getPicture()
    {
        return picture;
    }
    public void setSortNum(Long sortNum)
    {
        this.sortNum = sortNum;
    }

    public Long getSortNum()
    {
        return sortNum;
    }
    public void setLon(Double lon)
    {
        this.lon = lon;
    }

    public Double getLon()
    {
        return lon;
    }
    public void setLat(Double lat)
    {
        this.lat = lat;
    }

    public Double getLat()
    {
        return lat;
    }
    public void setLink(String link)
    {
        this.link = link;
    }

    public String getLink()
    {
        return link;
    }
    public void setIsShow(Long isShow)
    {
        this.isShow = isShow;
    }

    public Long getIsShow()
    {
        return isShow;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("title", getTitle())
                .append("type", getType())
                .append("picture", getPicture())
                .append("sortNum", getSortNum())
                .append("lon", getLon())
                .append("lat", getLat())
                .append("link", getLink())
                .append("isShow", getIsShow())
                .append("content", getContent())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
