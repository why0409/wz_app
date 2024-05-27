package com.ruoyi.app.domain.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.app.domain.ServiceConfig;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * @Author: LJW
 * @Date: 2024/4/25 0025 15:00
 */
public class ServiceCategoryConfigVo extends BaseEntity {

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

    private List<ServiceConfig> serviceList;

    public List<ServiceConfig> getServiceList() {
        return this.serviceList;
    }

    public void setServiceList(List<ServiceConfig> serviceList) {
        this.serviceList = serviceList;
    }

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
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("category", getCategory())
                .append("subCategory", getSubCategory())
                .append("sortNum", getSortNum())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
