package com.ruoyi.safetyHazard.domain.vo;

import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.utils.StringUtils;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 安全隐患-用户管理对象 safety_hazard_user
 *
 * @author ruoyi
 * @date 2024-08-08
 */
@Data
public class ExportSafetyHazardUserVo
{
    private static final long serialVersionUID = 1L;

    /** 用户名称 */
    @Excel(name = "单位名称")
    private String userName;

    /** 联系人 */
    @Excel(name = "填报人")
    private String contact;

    /** 微信手机号 */
    @Excel(name = "填报人手机号")
    private String wxPhone;

    @Excel(name = "（最新）填报状态", readConverterExp = "1=部分填报,2=全部填报,3=督察已回复,4=流程结束")
    private String status;

    @Excel(name = "（最新）填报时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date maxManifestUpdateTime;

    @Excel(name = "督查回复")
    private String dcReply;

    /** 自查回复 */
    @Excel(name = "自查回复")
    private String zcReply;

    @Excel(name = "所属辖区派出所")
    private String parentName;

    public void setDcReply(String dcReply) {
        try {
            if (! StringUtils.isEmpty(dcReply)) {
                JSONObject j =  JSONObject.parseObject(dcReply);
                this.dcReply = j.getString("advice");
            }else {
                this.dcReply = dcReply;
            }
        }catch (Exception e){
            this.dcReply = dcReply;
        }
    }

    public void setZcReply(String zcReply) {
        try {
            if (! StringUtils.isEmpty(zcReply)) {
                JSONObject j =  JSONObject.parseObject(zcReply);
                this.zcReply = j.getString("advice");
            }else {
                this.zcReply = zcReply;
            }
        }catch (Exception e) {
            this.zcReply = zcReply;
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userName", getUserName())
            .append("contact", getContact())
            .append("wxPhone", getWxPhone())
            .append("status", getStatus())
            .append("maxManifestUpdateTime", getMaxManifestUpdateTime())
            .append("dcReply", getDcReply())
            .append("zcReply", getZcReply())
            .append("parentName", getParentName())
            .toString();
    }
}
