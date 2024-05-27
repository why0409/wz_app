package com.ruoyi.system.domain;


import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 文件上传记录信息对象 sys_uploadFile_info
 * 
 * @author admin
 * @date 2021-04-19
 */
public class SysUploadFileInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String fname;

    /** 父id */
    @Excel(name = "父id")
    private Long parentId;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String furl;

    /** 上传类型 */
    @Excel(name = "上传类型")
    private String ftype;

    /** 备注 */
    @Excel(name = "备注")
    private String fremark;

    public SysUploadFileInfo(Long parentId, String ftype) {
        this.parentId = parentId;
        this.ftype = ftype;
    }

    public SysUploadFileInfo() {
    }

    public SysUploadFileInfo(Long parentId) {
        this.parentId = parentId;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setFname(String fame)
    {
        this.fname = fame;
    }

    public String getFname()
    {
        return fname;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }
    public void setFurl(String furl) 
    {
        this.furl = furl;
    }

    public String getFurl() 
    {
        return furl;
    }
    public void setFtype(String ftype) 
    {
        this.ftype = ftype;
    }

    public String getFtype() 
    {
        return ftype;
    }
    public void setFremark(String fremark) 
    {
        this.fremark = fremark;
    }

    public String getFremark() 
    {
        return fremark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("fname", getFname())
            .append("parentId", getParentId())
            .append("furl", getFurl())
            .append("ftype", getFtype())
            .append("fremark", getFremark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
