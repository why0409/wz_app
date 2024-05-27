package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.WxSysMenu;

import java.util.List;

public class WxPermDto {

    private List<Long> ids;

    private List<WxSysMenu> wxSysMenuList;


    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public List<WxSysMenu> getWxSysMenuList() {
        return wxSysMenuList;
    }

    public void setWxSysMenuList(List<WxSysMenu> wxSysMenuList) {
        this.wxSysMenuList = wxSysMenuList;
    }
}
