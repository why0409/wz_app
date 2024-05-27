package com.ruoyi.app.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 预警短信配置对象 alert_sms_config
 *
 * @author ruoyi
 * @date 2024-05-06
 */
public class AlertSmsConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 预警等级 */
    @Excel(name = "预警等级")
    private String warningLevel;

    /** 接收人 */
    @Excel(name = "接收人")
    private String recipients;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setWarningLevel(String warningLevel)
    {
        this.warningLevel = warningLevel;
    }

    public String getWarningLevel()
    {
        return warningLevel;
    }
    public void setRecipients(String recipients)
    {
        this.recipients = recipients;
    }

    public String getRecipients()
    {
        return recipients;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("warningLevel", getWarningLevel())
                .append("recipients", getRecipients())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
