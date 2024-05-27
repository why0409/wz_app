package com.ruoyi.app.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 小程序各服务使用日志对象 wx_click_service_log
 *
 * @author ruoyi
 * @date 2024-04-30
 */
public class WxClickServiceLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** openid */
    @Excel(name = "openid")
    private String openId;

    /** 服务id */
    @Excel(name = "服务id")
    private String serviceId;

    /** 服务名称 */
    @Excel(name = "服务名称")
    private String serviceName;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setOpenId(String openId)
    {
        this.openId = openId;
    }

    public String getOpenId()
    {
        return openId;
    }
    public void setServiceId(String serviceId)
    {
        this.serviceId = serviceId;
    }

    public String getServiceId()
    {
        return serviceId;
    }
    public void setServiceName(String serviceName)
    {
        this.serviceName = serviceName;
    }

    public String getServiceName()
    {
        return serviceName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("openId", getOpenId())
                .append("serviceId", getServiceId())
                .append("serviceName", getServiceName())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
