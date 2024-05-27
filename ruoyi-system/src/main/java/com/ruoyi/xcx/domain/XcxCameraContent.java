package com.ruoyi.xcx.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 小程序_内容详情对象 xcx_content
 *
 * @author ruoyi
 * @date 2023-10-16
 */
public class XcxCameraContent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 分类id */
    @Excel(name = "分类id")
    private Long typeId;

    /** 栏目id */
    @Excel(name = "栏目id")
    private String columnId;

    /** 内容名称 */
    @Excel(name = "内容名称")
    private String contentName;

    /** 内容简介 */
    @Excel(name = "内容简介")
    private String introduction;

    /** 电话号码 */
    @Excel(name = "电话号码")
    private String phone;

    /** 文件id,多个逗号隔开 */
    @Excel(name = "文件id,多个逗号隔开")
    private String fileIds;

    /** 内容排序 */
    @Excel(name = "内容排序")
    private Long contentSort;

    /** 内容状态 0--启用  1---停用 */
    @Excel(name = "内容状态 0--启用  1---停用")
    private String contentStatus;

    private String thumbnail;

    private String linkType;

    private String contact;

    private String dept;

    private String operators;

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getOperators() {
        return operators;
    }

    public void setOperators(String operators) {
        this.operators = operators;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    private String employmentType;

    private String lon;

    private String lat;

    private List<XcxFile> fileList;

    private Integer showType;

    private Integer collectionStatus = 0;

    public Integer getShowType() {
        return showType;
    }

    public void setShowType(Integer showType) {
        this.showType = showType;
    }

    public Integer getCollectionStatus() {
        return collectionStatus;
    }

    public void setCollection_status(Integer collectionStatus) {
        this.collectionStatus = collectionStatus;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setTypeId(Long typeId)
    {
        this.typeId = typeId;
    }

    public Long getTypeId()
    {
        return typeId;
    }
    public void setColumnId(String columnId)
    {
        this.columnId = columnId;
    }

    public String getColumnId()
    {
        return columnId;
    }
    public void setContentName(String contentName)
    {
        this.contentName = contentName;
    }

    public String getContentName()
    {
        return contentName;
    }
    public void setIntroduction(String introduction)
    {
        this.introduction = introduction;
    }

    public String getIntroduction()
    {
        return introduction;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setFileIds(String fileIds)
    {
        this.fileIds = fileIds;
    }

    public String getFileIds()
    {
        return fileIds;
    }
    public void setContentSort(Long contentSort)
    {
        this.contentSort = contentSort;
    }

    public Long getContentSort()
    {
        return contentSort;
    }
    public void setContentStatus(String contentStatus)
    {
        this.contentStatus = contentStatus;
    }

    public String getContentStatus()
    {
        return contentStatus;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public List<XcxFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<XcxFile> fileList) {
        this.fileList = fileList;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("typeId", getTypeId())
            .append("columnId", getColumnId())
            .append("contentName", getContentName())
            .append("introduction", getIntroduction())
            .append("phone", getPhone())
            .append("fileIds", getFileIds())
            .append("contentSort", getContentSort())
            .append("contentStatus", getContentStatus())
            .append("createTime", getCreateTime())
            .toString();
    }
}
