package com.ruoyi.electricity.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用电企业数据对象 yd_enterprise_data
 *
 * @author ruoyi
 * @date 2024-11-04
 */
@Data
public class YdEnterpriseData implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @Excel(name = "电表资产编号")
    private String meterNumber;

    @Excel(name = "用户名称")
    private String userName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataDate;

    @Excel(name = "数据日期")
    private String dataDate1;

    @Excel(name = "时间")
    private String dataTime;

    @Excel(name = "总有功功率(kW)")
    private String totalActivePower;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fullTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
