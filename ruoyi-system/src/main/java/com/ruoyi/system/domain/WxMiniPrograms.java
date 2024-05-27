package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 小程序模块对象 wx_mini_programs
 *
 * @author ruoyi
 * @date 2023-07-17
 */
public class WxMiniPrograms extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private String uuid;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

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

    /** 链接 */
    @Excel(name = "链接")
    private String link;

    /** 模块类型 */
    @Excel(name = "模块类型")
    private String type;

    @Excel(name = "父id")
    private String pid;

    @Excel(name = "标签")
    private String label;

    @Excel(name = "状态")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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
    public void setIsShow(Long isShow)
    {
        this.isShow = isShow;
    }

    public Long getIsShow()
    {
        return isShow;
    }
    public void setLink(String link)
    {
        this.link = link;
    }

    public String getLink()
    {
        return link;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("uuid", getUuid())
                .append("title", getTitle())
                .append("pictureName", getPictureName())
                .append("publishTime", getPublishTime())
                .append("remark", getRemark())
                .append("sortNum", getSortNum())
                .append("isShow", getIsShow())
                .append("link", getLink())
                .append("updateTime", getUpdateTime())
                .append("type", getType())
                .toString();
    }
}
