package com.ruoyi.system.domain.vo;

import java.util.Arrays;

public class WxUserMenuReqVo {
    /** 手机号 */
    private String phone;
    /** 菜单组 */
    private Long[] menuIds;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long[] getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(Long[] menuIds) {
        this.menuIds = menuIds;
    }

    @Override
    public String toString() {
        return "WxUserMenuReqVo{" +
                "phone='" + phone + '\'' +
                ", menuIds=" + Arrays.toString(menuIds) +
                '}';
    }
}
