package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户菜单关系对象 wx_user_menu
 * 
 * @author ruoyi
 * @date 2022-11-22
 */
public class WxUserMenu extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;


    /** 菜单id */
    @Excel(name = "菜单id")
    private Long menuId;


    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setMenuId(Long menuId) 
    {
        this.menuId = menuId;
    }

    public Long getMenuId() 
    {
        return menuId;
    }

    @Override
    public String toString() {
        return "WxUserMenu{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", menuId=" + menuId +
                '}';
    }
}
