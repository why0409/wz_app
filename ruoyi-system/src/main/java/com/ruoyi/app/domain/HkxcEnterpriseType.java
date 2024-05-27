package com.ruoyi.app.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 航空新城-企业分类对象 hkxc_enterprise_type
 *
 * @author ruoyi
 * @date 2024-05-07
 */
public class HkxcEnterpriseType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    @Excel(name = "副标题")
    private String subTitle;

    /** 图片 */
    @Excel(name = "图片")
    private String picture;

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
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public void setSubTitle(final String subTitle) {
        this.subTitle = subTitle;
    }

    public void setPicture(String picture)
    {
        this.picture = picture;
    }

    public String getPicture()
    {
        return picture;
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
                .append("type", getType())
                .append("picture", getPicture())
                .append("sortNum", getSortNum())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
