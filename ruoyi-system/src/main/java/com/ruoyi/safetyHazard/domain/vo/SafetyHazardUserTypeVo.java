package com.ruoyi.safetyHazard.domain.vo;

import com.alibaba.fastjson2.JSONArray;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.safetyHazard.domain.SafetyHazardUser;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 安全隐患-角色类型管理对象 safety_hazard_user_type
 *
 * @author ruoyi
 * @date 2024-08-08
 */
public class SafetyHazardUserTypeVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    private String uuid;

    private String name;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 角色（1-自查单位；2-督查单位） */
    @Excel(name = "角色", readConverterExp = "1=-自查单位；2-督查单位")
    private String role;

    private String icon;
    private List<SafetyHazardUserVo> usersList;


    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setRole(String role)
    {
        this.role = role;
    }

    public String getRole()
    {
        return role;
    }

    public List<SafetyHazardUserVo> getUsersList() {
        return this.usersList;
    }

    public void setUsersList(List<SafetyHazardUserVo> usersList) {
        this.usersList = usersList;
    }

    public String getName() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("role", getRole())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
