package com.ruoyi.app.domain.vo;

import java.util.Arrays;

/**
 * 城市之眼-权限对象 cszy_permissions
 *
 * @author ruoyi
 * @date 2024-05-24
 */
public class CszyPermissionsVo
{
    private String phone;
    /** 菜单组 */
    private Long[] typeIds;

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long[] getTypeIds() {
        return this.typeIds;
    }

    public void setTypeIds(Long[] typeIds) {
        this.typeIds = typeIds;
    }

    @Override
    public String toString() {
        return "CszyPermissionsByPhone{" +
                "phone='" + phone + '\'' +
                ", typeIds=" + Arrays.toString(typeIds) +
                '}';
    }
}
