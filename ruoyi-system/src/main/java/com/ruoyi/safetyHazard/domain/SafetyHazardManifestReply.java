package com.ruoyi.safetyHazard.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 安全隐患-清单-回复对象 safety_hazard_manifest_school_reply
 *
 * @author ruoyi
 * @date 2024-08-12
 */
public class SafetyHazardManifestReply extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 清单id */
    @Excel(name = "清单id")
    private String manifestId;

    /** 督查回复 */
    @Excel(name = "督查回复")
    private String dcReply;

    /** 自查回复 */
    @Excel(name = "自查回复")
    private String zcReply;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setManifestId(String manifestId)
    {
        this.manifestId = manifestId;
    }

    public String getManifestId()
    {
        return manifestId;
    }
    public void setDcReply(String dcReply)
    {
        this.dcReply = dcReply;
    }

    public String getDcReply()
    {
        return dcReply;
    }
    public void setZcReply(String zcReply)
    {
        this.zcReply = zcReply;
    }

    public String getZcReply()
    {
        return zcReply;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("manifestId", getManifestId())
            .append("dcReply", getDcReply())
            .append("zcReply", getZcReply())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
