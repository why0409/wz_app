package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 湾沚航空马拉松对象 wx_marathon
 *
 * @author ruoyi
 * @date 2023-03-20
 */
public class WxMarathon extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String uuid;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 副标题 */
    @Excel(name = "副标题")
    private String subtitle;

    /** 标签 */
    @Excel(name = "标签")
    private String label;

    /** 文章链接地址 */
    @Excel(name = "文章链接地址")
    private String link;

    /** 图片名称 */
    @Excel(name = "图片名称")
    private String pictureName;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publishTime;

    /** 排序序号 */
    @Excel(name = "排序序号")
    private Integer sortNum;

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    public String getUuid()
    {
        return uuid;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setSubtitle(String subtitle)
    {
        this.subtitle = subtitle;
    }

    public String getSubtitle()
    {
        return subtitle;
    }
    public void setLabel(String label)
    {
        this.label = label;
    }

    public String getLabel()
    {
        return label;
    }
    public void setLink(String link)
    {
        this.link = link;
    }

    public String getLink()
    {
        return link;
    }
    public void setPictureName(String pictureName)
    {
        this.pictureName = pictureName;
    }

    public String getPictureName()
    {
        return pictureName;
    }
    public void setPublishTime(Date publishTime)
    {
        this.publishTime = publishTime;
    }

    public Date getPublishTime()
    {
        return publishTime;
    }
    public void setSortNum(Integer sortNum)
    {
        this.sortNum = sortNum;
    }

    public Integer getSortNum()
    {
        return sortNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("uuid", getUuid())
                .append("title", getTitle())
                .append("subtitle", getSubtitle())
                .append("label", getLabel())
                .append("link", getLink())
                .append("pictureName", getPictureName())
                .append("publishTime", getPublishTime())
                .append("sortNum", getSortNum())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
