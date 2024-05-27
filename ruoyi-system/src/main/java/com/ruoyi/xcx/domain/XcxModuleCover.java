package com.ruoyi.xcx.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 模块封面对象 xcx_module_cover
 *
 * @author ruoyi
 * @date 2023-12-22
 */
public class XcxModuleCover extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 封面图片 */
    @Excel(name = "封面图片")
    private String icon;

    /** 模块 */
    @Excel(name = "模块")
    private String module;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public String getIcon()
    {
        return icon;
    }
    public void setModule(String module)
    {
        this.module = module;
    }

    public String getModule()
    {
        return module;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("icon", getIcon())
                .append("module", getModule())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
