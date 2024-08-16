package com.ruoyi.app.domain.vo;

import cn.hutool.core.util.DesensitizedUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 问卷调查-申领礼品信息对象 questionnaire_gift_info
 *
 * @author ruoyi
 * @date 2024-07-31
 */
public class QuestionnaireGiftInfoVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 申领序号 */
    @Excel(name = "申领序号")
    private String giftNumber;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phone;

    /** 申领地址 */
    @Excel(name = "申领地址")
    private String giftAddress;

    /** 问卷id */
    @Excel(name = "问卷id")
    private String questionnaireId;

    /** 申领时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "申领时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date giftTime;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setGiftNumber(String giftNumber)
    {
        this.giftNumber = giftNumber;
    }

    public String getGiftNumber()
    {
        return giftNumber;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return DesensitizedUtil.mobilePhone(phone);
    }
    public void setGiftAddress(String giftAddress)
    {
        this.giftAddress = giftAddress;
    }

    public String getGiftAddress()
    {
        return giftAddress;
    }
    public void setQuestionnaireId(String questionnaireId)
    {
        this.questionnaireId = questionnaireId;
    }

    public String getQuestionnaireId()
    {
        return questionnaireId;
    }
    public void setGiftTime(Date giftTime)
    {
        this.giftTime = giftTime;
    }

    public Date getGiftTime()
    {
        return giftTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("giftNumber", getGiftNumber())
                .append("phone", getPhone())
                .append("giftAddress", getGiftAddress())
                .append("questionnaireId", getQuestionnaireId())
                .append("giftTime", getGiftTime())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
