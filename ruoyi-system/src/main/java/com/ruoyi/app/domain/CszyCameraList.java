package com.ruoyi.app.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 城市之眼-视频列对象 cszy_camera_list
 *
 * @author ruoyi
 * @date 2024-05-24
 */
public class CszyCameraList extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 排序序号 */
    @Excel(name = "排序序号")
    private Long sortNum;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 所属分类id */
    @Excel(name = "所属分类id")
    private Long typeId;

    /** 视频ID或者路径 */
    @Excel(name = "视频ID或者路径")
    private String cameraId;

    /** 所属单位 */
    @Excel(name = "所属单位")
    private String dept;

    /** 网络运营商 */
    @Excel(name = "网络运营商")
    private String networkOperators;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contact;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 图片 */
    @Excel(name = "图片")
    private String picture;

    /** 经度 */
    @Excel(name = "经度")
    private Double lon;

    /** 纬度 */
    @Excel(name = "纬度")
    private Double lat;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    /** 视频种类 */
    @Excel(name = "视频种类")
    private String category;

    /** 视频 */
    @Excel(name = "视频")
    private String video;

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
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setTypeId(Long typeId)
    {
        this.typeId = typeId;
    }

    public Long getTypeId()
    {
        return typeId;
    }
    public void setCameraId(String cameraId)
    {
        this.cameraId = cameraId;
    }

    public String getCameraId()
    {
        return cameraId;
    }
    public void setDept(String dept)
    {
        this.dept = dept;
    }

    public String getDept()
    {
        return dept;
    }
    public void setNetworkOperators(String networkOperators)
    {
        this.networkOperators = networkOperators;
    }

    public String getNetworkOperators()
    {
        return networkOperators;
    }
    public void setContact(String contact)
    {
        this.contact = contact;
    }

    public String getContact()
    {
        return contact;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setPicture(String picture)
    {
        this.picture = picture;
    }

    public String getPicture()
    {
        return picture;
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
    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }
    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getCategory()
    {
        return category;
    }
    public void setVideo(String video)
    {
        this.video = video;
    }

    public String getVideo()
    {
        return video;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("sortNum", getSortNum())
                .append("name", getName())
                .append("typeId", getTypeId())
                .append("cameraId", getCameraId())
                .append("dept", getDept())
                .append("networkOperators", getNetworkOperators())
                .append("contact", getContact())
                .append("phone", getPhone())
                .append("picture", getPicture())
                .append("lon", getLon())
                .append("lat", getLat())
                .append("status", getStatus())
                .append("category", getCategory())
                .append("video", getVideo())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
