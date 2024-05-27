package com.ruoyi.xcx.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 一张图留言对象 xcx_message
 *
 * @author ruoyi
 * @date 2023-11-08
 */
public class XcxMessage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phone;

    /** 留言 */
    @Excel(name = "留言")
    private String message;

    /** 回复 */
    @Excel(name = "回复")
    private String reply;

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
    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }
    public void setReply(String reply)
    {
        this.reply = reply;
    }

    public String getReply()
    {
        return reply;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("phone", getPhone())
                .append("message", getMessage())
                .append("reply", getReply())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
