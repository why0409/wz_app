package com.ruoyi.onePicture.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 小程序---分类对象 xcx_type
 *
 * @author ruoyi
 * @date 2023-10-16
 */
public class XcxCameraType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String typeName;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 分类状态 0--启用  1---停用 */
    @Excel(name = "分类状态 0--启用  1---停用")
    private String typeStatus;

    private List<XcxCameraColumn> columnList;

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    public String getTypeName()
    {
        return typeName;
    }
    public void setSort(Long sort)
    {
        this.sort = sort;
    }

    public Long getSort()
    {
        return sort;
    }
    public void setTypeStatus(String typeStatus)
    {
        this.typeStatus = typeStatus;
    }

    public String getTypeStatus()
    {
        return typeStatus;
    }

    public List<XcxCameraColumn> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<XcxCameraColumn> columnList) {
        this.columnList = columnList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("typeName", getTypeName())
            .append("sort", getSort())
            .append("typeStatus", getTypeStatus())
            .append("createTime", getCreateTime())
            .toString();
    }
}
