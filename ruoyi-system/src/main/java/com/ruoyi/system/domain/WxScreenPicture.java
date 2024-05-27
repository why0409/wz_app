package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 大屏图片对象 wx_screen_picture
 * 
 * @author ruoyi
 * @date 2023-02-16
 */
public class WxScreenPicture extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String uuid;

    /** 专题 */
    @Excel(name = "专题")
    private String topic;

    /** 模块 */
    @Excel(name = "模块")
    private String module;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 图片名称 */
    @Excel(name = "图片名称")
    private String pictureName;

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
    public void setTopic(String topic) 
    {
        this.topic = topic;
    }

    public String getTopic() 
    {
        return topic;
    }
    public void setModule(String module) 
    {
        this.module = module;
    }

    public String getModule() 
    {
        return module;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPictureName(String pictureName) 
    {
        this.pictureName = pictureName;
    }

    public String getPictureName() 
    {
        return pictureName;
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
            .append("topic", getTopic())
            .append("module", getModule())
            .append("name", getName())
            .append("pictureName", getPictureName())
            .append("remark", getRemark())
            .append("sortNum", getSortNum())
            .toString();
    }
}
