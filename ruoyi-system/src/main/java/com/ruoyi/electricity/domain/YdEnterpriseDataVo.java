package com.ruoyi.electricity.domain;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用电企业数据对象 yd_enterprise_data
 *
 * @author ruoyi
 * @date 2024-11-04
 */
@Data
public class YdEnterpriseDataVo implements Serializable {
    private static final long serialVersionUID = 1L;

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

    private Date dataDate;

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
}
