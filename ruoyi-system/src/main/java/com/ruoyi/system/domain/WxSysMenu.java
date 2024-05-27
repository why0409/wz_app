package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 微信菜单对象 wx_sys_menu
 * 
 * @author lgh
 * @date 2022-11-22
 */
public class WxSysMenu extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 菜单名称 */
    @Excel(name = "菜单名称")
    private String menuName;

    /** 菜单icon */
    @Excel(name = "菜单icon")
    private String menuIcon;

    /** 菜单标识 */
    @Excel(name = "菜单标识")
    private String menuMark;

    /** 排序 */
    @Excel(name = "排序")
    private String menuOrder;

    /** 菜单状态（0 正常，1 停用） */
    @Excel(name = "菜单状态", readConverterExp = "0=,正=常，1,停=用")
    private String menuStatus;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date creatTime;

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
    public void setMenuIcon(String menuIcon) 
    {
        this.menuIcon = menuIcon;
    }

    public String getMenuIcon() 
    {
        return menuIcon;
    }
    public void setMenuMark(String menuMark) 
    {
        this.menuMark = menuMark;
    }

    public String getMenuMark() 
    {
        return menuMark;
    }
    public void setMenuOrder(String menuOrder) 
    {
        this.menuOrder = menuOrder;
    }

    public String getMenuOrder() 
    {
        return menuOrder;
    }
    public void setMenuStatus(String menuStatus) 
    {
        this.menuStatus = menuStatus;
    }

    public String getMenuStatus() 
    {
        return menuStatus;
    }
    public void setCreatTime(Date creatTime) 
    {
        this.creatTime = creatTime;
    }

    public Date getCreatTime() 
    {
        return creatTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("menuName", getMenuName())
            .append("menuIcon", getMenuIcon())
            .append("menuMark", getMenuMark())
            .append("menuOrder", getMenuOrder())
            .append("menuStatus", getMenuStatus())
            .append("creatTime", getCreatTime())
            .toString();
    }

    public WxSysMenu(String menuName) {
        this.menuName = menuName;
    }
    public WxSysMenu() {
    }
}
