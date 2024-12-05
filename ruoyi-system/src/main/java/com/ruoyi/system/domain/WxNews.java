package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【最近动态】对象 wx_news
 *
 * @author ruoyi
 * @date 2022-12-01
 */
public class WxNews extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 标识符 */
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
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;

    /** 排序序号 */
    @Excel(name = "排序序号")
    private Integer sortNum;

    /** 是否显示 */
    @Excel(name = "是否显示")
    private Long isShow;

    @Excel(name = "是否轮播")
    private Long isCarousel;

    @Excel(name = "是否和美湾沚")
    private Long isHmwz;

    @Excel(name = "是否推荐阅读")
    private Long isRecommendRead;

    private Integer type;

    private String appId;

    public Long getIsRecommendRead() {
        return this.isRecommendRead;
    }

    public void setIsRecommendRead(final Long isRecommendRead) {
        this.isRecommendRead = isRecommendRead;
    }

    public Long getIsHmwz() {
        return this.isHmwz;
    }

    public void setIsHmwz(Long isHmwz) {
        this.isHmwz = isHmwz;
    }

    public Long getIsCarousel() {
        return this.isCarousel;
    }

    public void setIsCarousel(Long isCarousel) {
        this.isCarousel = isCarousel;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

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

    public Long getIsShow() {
        return isShow;
    }

    public void setIsShow(Long isShow) {
        this.isShow = isShow;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("uuid", getUuid())
            .append("title", getTitle())
            .append("subtitle", getSubtitle())
            .append("label", getLabel())
            .append("link", getLink())
            .append("isShow", getIsShow())
            .append("pictureName", getPictureName())
            .append("publishTime", getPublishTime())
            .append("sortNum", getSortNum())
            .toString();
    }


}
