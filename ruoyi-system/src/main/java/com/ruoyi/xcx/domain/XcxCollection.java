package com.ruoyi.xcx.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 收藏列对象 xcx_collection
 *
 * @author ruoyi
 * @date 2023-10-31
 */
public class XcxCollection extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phone;

    /** 内容id */
    @Excel(name = "内容id")
    private Integer contentId;

    private Integer collectionStatus = 1;

    public Integer getCollectionStatus() {
        return collectionStatus;
    }

    public void setCollectionStatus(Integer collectionStatus) {
        this.collectionStatus = collectionStatus;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setContentId(Integer contentId)
    {
        this.contentId = contentId;
    }

    public Integer getContentId()
    {
        return contentId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("phone", getPhone())
                .append("contentId", getContentId())
                .toString();
    }
}
