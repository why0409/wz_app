package com.ruoyi.gisDb.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 惠企政策对象 wz_benefit_enterprise
 *
 * @author ruoyi
 * @date 2023-04-06
 */
public class WzBenefitEnterprise extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 兑付企业 */
    @Excel(name = "兑付企业")
    private String payEnterprise;

    /** 政策名称 */
    @Excel(name = "政策名称")
    private String policyName;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 兑付时间 */
    @Excel(name = "兑付时间")
    private String payTime;

    /** 兑付次数 */
    @Excel(name = "兑付次数")
    private String payFrequency;

    /** 个人兑付次数 */
    @Excel(name = "个人兑付次数")
    private String payFrequencyPerson;

    /** 企业兑付次数 */
    @Excel(name = "企业兑付次数")
    private String payFrequencyEnterprise;

    /** 兑付金额 */
    @Excel(name = "兑付金额")
    private String payAmount;

    /** 个人兑付金额 */
    @Excel(name = "个人兑付金额")
    private String payAmountPerson;

    /** 企业兑付金额 */
    @Excel(name = "企业兑付金额")
    private String payAmountEnterprise;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setPayEnterprise(String payEnterprise)
    {
        this.payEnterprise = payEnterprise;
    }

    public String getPayEnterprise()
    {
        return payEnterprise;
    }
    public void setPolicyName(String policyName)
    {
        this.policyName = policyName;
    }

    public String getPolicyName()
    {
        return policyName;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setPayTime(String payTime)
    {
        this.payTime = payTime;
    }

    public String getPayTime()
    {
        return payTime;
    }
    public void setPayFrequency(String payFrequency)
    {
        this.payFrequency = payFrequency;
    }

    public String getPayFrequency()
    {
        return payFrequency;
    }
    public void setPayFrequencyPerson(String payFrequencyPerson)
    {
        this.payFrequencyPerson = payFrequencyPerson;
    }

    public String getPayFrequencyPerson()
    {
        return payFrequencyPerson;
    }
    public void setPayFrequencyEnterprise(String payFrequencyEnterprise)
    {
        this.payFrequencyEnterprise = payFrequencyEnterprise;
    }

    public String getPayFrequencyEnterprise()
    {
        return payFrequencyEnterprise;
    }
    public void setPayAmount(String payAmount)
    {
        this.payAmount = payAmount;
    }

    public String getPayAmount()
    {
        return payAmount;
    }
    public void setPayAmountPerson(String payAmountPerson)
    {
        this.payAmountPerson = payAmountPerson;
    }

    public String getPayAmountPerson()
    {
        return payAmountPerson;
    }
    public void setPayAmountEnterprise(String payAmountEnterprise)
    {
        this.payAmountEnterprise = payAmountEnterprise;
    }

    public String getPayAmountEnterprise()
    {
        return payAmountEnterprise;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("payEnterprise", getPayEnterprise())
                .append("policyName", getPolicyName())
                .append("type", getType())
                .append("payTime", getPayTime())
                .append("payFrequency", getPayFrequency())
                .append("payFrequencyPerson", getPayFrequencyPerson())
                .append("payFrequencyEnterprise", getPayFrequencyEnterprise())
                .append("payAmount", getPayAmount())
                .append("payAmountPerson", getPayAmountPerson())
                .append("payAmountEnterprise", getPayAmountEnterprise())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}

