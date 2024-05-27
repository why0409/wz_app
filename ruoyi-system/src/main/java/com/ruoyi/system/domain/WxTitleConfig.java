package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 标题配置对象 wx_title_config
 *
 * @author ruoyi
 * @date 2023-04-24
 */
public class WxTitleConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 菜单名称 */
    @Excel(name = "菜单名称")
    private String menuName;

    /** 菜单标识 */
    @Excel(name = "菜单标识")
    private String menuMark;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sortNum;

    /** 菜单状态（0 正常，1 停用） */
    @Excel(name = "菜单状态", readConverterExp = "0=,正=常，1,停=用")
    private String menuStatus;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setMenuName(String menuName)
    {
        this.menuName = menuName;
    }

    public String getMenuName()
    {
        return menuName;
    }
    public void setMenuMark(String menuMark)
    {
        this.menuMark = menuMark;
    }

    public String getMenuMark()
    {
        return menuMark;
    }
    public void setSortNum(Integer sortNum)
    {
        this.sortNum = sortNum;
    }

    public Integer getSortNum()
    {
        return sortNum;
    }
    public void setMenuStatus(String menuStatus)
    {
        this.menuStatus = menuStatus;
    }

    public String getMenuStatus()
    {
        return menuStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("menuName", getMenuName())
                .append("menuMark", getMenuMark())
                .append("sortNum", getSortNum())
                .append("menuStatus", getMenuStatus())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
