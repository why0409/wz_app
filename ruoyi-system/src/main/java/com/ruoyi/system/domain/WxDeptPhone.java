package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 部门电话对象 wx_dept_phone
 *
 * @author ruoyi
 * @date 2023-02-14
 */
public class WxDeptPhone extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String uuid;

    /** 标题 */
    @Excel(name = "标题")
    private String deptName;

    /** 副标题 */
    @Excel(name = "副标题")
    private String phone;

    /** 排序序号 */
    @Excel(name = "排序序号")
    private Integer sortNum;

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    public String getUuid()
    {
        return uuid;
    }
    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }

    public String getDeptName()
    {
        return deptName;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setSortNum(Integer sortNum)
    {
        this.sortNum = sortNum;
    }

    public Integer getSortNum()
    {
        return sortNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("uuid", getUuid())
                .append("deptName", getDeptName())
                .append("phone", getPhone())
                .append("createTime", getCreateTime())
                .append("sortNum", getSortNum())
                .toString();
    }
}

