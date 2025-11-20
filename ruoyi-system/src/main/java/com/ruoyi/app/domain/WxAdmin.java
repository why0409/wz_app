package com.ruoyi.app.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 微信管理员对象 wx_admin
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("wx_admin") // MP 注解：指定表名
public class WxAdmin extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO) // MP 注解：指定主键自增
    private Long id;

    @Excel(name = "姓名")
    private String name;

    @Excel(name = "手机号")
    private String phone;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("phone", getPhone())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}