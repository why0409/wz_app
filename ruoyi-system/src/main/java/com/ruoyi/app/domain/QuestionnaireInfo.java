package com.ruoyi.app.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 问卷调查信息对象 questionnaire_info
 *
 * @author ruoyi
 * @date 2024-07-31
 */
public class QuestionnaireInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private String id;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 窗口号 */
    @Excel(name = "窗口号")
    private String windowNumber;

    /** 满意度选项 */
    @Excel(name = "满意度选项")
    private String satisfactionSelect;

    /** 原因选项 */
    @Excel(name = "原因选项")
    private String reasonSelect;

    /** 建议 */
    @Excel(name = "建议")
    private String suggestion;

    /** 是否申领礼物 */
    @Excel(name = "是否申领礼物")
    private String isGift;

    @Excel(name = "地址")
    private String address;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
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
    public void setWindowNumber(String windowNumber)
    {
        this.windowNumber = windowNumber;
    }

    public String getWindowNumber()
    {
        return windowNumber;
    }
    public void setSatisfactionSelect(String satisfactionSelect)
    {
        this.satisfactionSelect = satisfactionSelect;
    }

    public String getSatisfactionSelect()
    {
        return satisfactionSelect;
    }
    public void setReasonSelect(String reasonSelect)
    {
        this.reasonSelect = reasonSelect;
    }

    public String getReasonSelect()
    {
        return reasonSelect;
    }
    public void setSuggestion(String suggestion)
    {
        this.suggestion = suggestion;
    }

    public String getSuggestion()
    {
        return suggestion;
    }
    public void setIsGift(String isGift)
    {
        this.isGift = isGift;
    }

    public String getIsGift()
    {
        return isGift;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("phone", getPhone())
            .append("windowNumber", getWindowNumber())
            .append("satisfactionSelect", getSatisfactionSelect())
            .append("reasonSelect", getReasonSelect())
            .append("suggestion", getSuggestion())
            .append("isGift", getIsGift())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
