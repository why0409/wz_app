package com.ruoyi.xcx.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 小程序--栏目对象 xcx_column
 *
 * @author ruoyi
 * @date 2023-10-16
 */
public class XcxCameraColumn extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 栏目所属分类，多个用逗号隔开 */
    @Excel(name = "栏目所属分类，多个用逗号隔开")
    private String typeId;

    /** 栏目名称 */
    @Excel(name = "栏目名称")
    private String columnName;

    /** 排序 */
    @Excel(name = "排序")
    private Long columnSort;

    private String typeName;

    private String icon;

    /**
     * 展示类型
     */
    private Integer showType;

    /** 状态 0--启用  1---停用 */
    @Excel(name = "状态 0--启用  1---停用")
    private String couimnStatus;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setTypeId(String typeId)
    {
        this.typeId = typeId;
    }

    public String getTypeId()
    {
        return typeId;
    }
    public void setColumnName(String columnName)
    {
        this.columnName = columnName;
    }

    public String getColumnName()
    {
        return columnName;
    }
    public void setColumnSort(Long columnSort)
    {
        this.columnSort = columnSort;
    }

    public Long getColumnSort()
    {
        return columnSort;
    }
    public void setCouimnStatus(String couimnStatus)
    {
        this.couimnStatus = couimnStatus;
    }

    public String getCouimnStatus()
    {
        return couimnStatus;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getShowType() {
        return showType;
    }

    public void setShowType(Integer showType) {
        this.showType = showType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("typeId", getTypeId())
            .append("columnName", getColumnName())
            .append("columnSort", getColumnSort())
            .append("couimnStatus", getCouimnStatus())
            .append("createTime", getCreateTime())
            .toString();
    }
}
