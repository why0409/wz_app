package com.ruoyi.app.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 服务类别配置对象 service_category_config
 *
 * @author ruoyi
 * @date 2024-04-25
 */
public class ServiceCategoryConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 大类别（市民服务；企业服务；政府服务） */
    @Excel(name = "大类别", readConverterExp = "市=民服务；企业服务；政府服务")
    private String category;

    /** 小类别 */
    @Excel(name = "小类别")
    private String subCategory;

    /** 排序序号 */
    @Excel(name = "排序序号")
    private Long sortNum;


    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getCategory()
    {
        return category;
    }
    public void setSubCategory(String subCategory)
    {
        this.subCategory = subCategory;
    }

    public String getSubCategory()
    {
        return subCategory;
    }
    public void setSortNum(Long sortNum)
    {
        this.sortNum = sortNum;
    }

    public Long getSortNum()
    {
        return sortNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("category", getCategory())
                .append("subCategory", getSubCategory())
                .append("sortNum", getSortNum())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
