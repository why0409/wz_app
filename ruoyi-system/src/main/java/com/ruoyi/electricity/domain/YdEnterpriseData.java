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

    /**
     * 序号
     */
    private Long id;

    /**
     * 电表资产编号
     */
    @Excel(name = "电表资产编号")
    private String meterNumber;

    /**
     * 用户名称
     */
    @Excel(name = "用户名称")
    private String userName;


    /**
     * 数据日期
     */
    // @Excel(name = "数据日期")
    private Date dataDate;

    @Excel(name = "数据日期")
    private String dataDate1;

    /**
     * 时间
     */
    @Excel(name = "时间")
    private String dataTime;


    /**
     * 总有功功率(kW)
     */
    @Excel(name = "总有功功率(kW)")
    private String totalActivePower;

    private Date fullTime;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
