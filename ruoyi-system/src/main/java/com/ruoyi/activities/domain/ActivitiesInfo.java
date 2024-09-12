package com.ruoyi.activities.domain;

import com.alibaba.fastjson2.JSONObject;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 活动信息对象 activities_info
 *
 * @author ruoyi
 * @date 2024-06-20
 */
public class ActivitiesInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 活动名称 */
    @Excel(name = "活动名称")
    private String name;

    /** 活动内容 */
    @Excel(name = "活动内容")
    private String content;

    /** 体验劵总数 */
    @Excel(name = "体验劵总数")
    private Integer securitiesTotalNumber;

    /** 体验劵剩余数 */
    @Excel(name = "体验劵剩余数")
    private Integer securitiesRestNumber;

    /** 体验劵金额 */
    @Excel(name = "体验劵金额")
    private Long securitiesAmount;

    /** 注册时间 */
    @Excel(name = "注册时间")
    private String registrationTime;

    /** 发放时间 */
    @Excel(name = "发放时间")
    private String releaseTime;

    /** 发放周期 */
    @Excel(name = "发放周期")
    private Long releaseCycle;

    /** 消费时间 */
    @Excel(name = "消费时间")
    private String consumptionTime;

    /** 当前阶段 */
    private String currentStatus;

    /** 关联民宿id */
    @Excel(name = "关联民宿id")
    private String homestayIds;

    /** 活动状态 */
    @Excel(name = "活动状态")
    private String status;
    private JSONObject staticInfo;

    public JSONObject getStaticInfo() {
        return this.staticInfo;
    }

    public void setStaticInfo(JSONObject staticInfo) {
        this.staticInfo = staticInfo;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setSecuritiesTotalNumber(Integer securitiesTotalNumber)
    {
        this.securitiesTotalNumber = securitiesTotalNumber;
    }

    public Integer getSecuritiesTotalNumber()
    {
        return securitiesTotalNumber;
    }
    public void setSecuritiesRestNumber(Integer securitiesRestNumber)
    {
        this.securitiesRestNumber = securitiesRestNumber;
    }

    public Integer getSecuritiesRestNumber()
    {
        return securitiesRestNumber;
    }
    public void setSecuritiesAmount(Long securitiesAmount)
    {
        this.securitiesAmount = securitiesAmount;
    }

    public Long getSecuritiesAmount()
    {
        return securitiesAmount;
    }
    public void setRegistrationTime(String registrationTime)
    {
        this.registrationTime = registrationTime;
    }

    public String getRegistrationTime()
    {
        return registrationTime;
    }
    public void setReleaseTime(String releaseTime)
    {
        this.releaseTime = releaseTime;
    }

    public String getReleaseTime()
    {
        return releaseTime;
    }
    public void setReleaseCycle(Long releaseCycle)
    {
        this.releaseCycle = releaseCycle;
    }

    public Long getReleaseCycle()
    {
        return releaseCycle;
    }
    public void setConsumptionTime(String consumptionTime)
    {
        this.consumptionTime = consumptionTime;
    }

    public String getConsumptionTime()
    {
        return consumptionTime;
    }

    public String getHomestayIds() {
        return this.homestayIds;
    }

    public void setHomestayIds(String homestayIds) {
        this.homestayIds = homestayIds;
    }

    public String getCurrentStatus() {
        return this.currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("content", getContent())
            .append("securitiesTotalNumber", getSecuritiesTotalNumber())
            .append("securitiesRestNumber", getSecuritiesRestNumber())
            .append("securitiesAmount", getSecuritiesAmount())
            .append("registrationTime", getRegistrationTime())
            .append("releaseTime", getReleaseTime())
            .append("releaseCycle", getReleaseCycle())
            .append("consumptionTime", getConsumptionTime())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }


}
