package com.ruoyi.system.domain.vo;


public class WxUserMenuVo {

    private Long id;

    private Long menuId;

    private String phone;

    private String menuName;

    private String menuMark;

    private String menuOrder;

    private String menuStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuMark() {
        return menuMark;
    }

    public void setMenuMark(String menuMark) {
        this.menuMark = menuMark;
    }

    public String getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(String menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(String menuStatus) {
        this.menuStatus = menuStatus;
    }

    @Override
    public String toString() {
        return "WxUserMenuVo{" +
                "id=" + id +
                ", menuId='" + menuId + '\'' +
                ", phone='" + phone + '\'' +
                ", menuName='" + menuName + '\'' +
                ", menuMark='" + menuMark + '\'' +
                ", menuOrder='" + menuOrder + '\'' +
                ", menuStatus='" + menuStatus + '\'' +
                '}';
    }
}
