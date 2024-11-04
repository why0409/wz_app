package com.ruoyi.electricity.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 用电企业数据对象 yd_enterprise_data
 *
 * @author ruoyi
 * @date 2024-11-04
 */
public class YdEnterpriseData extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 电表资产编号 */
    @Excel(name = "电表资产编号")
    private String meterNumber;

    /** 电表地址 */
    // @Excel(name = "电表地址")
    private String meterAddress;

    /** 电能表通信规约 */
    // @Excel(name = "电能表通信规约")
    private String meterProtocol;

    /** 终端地址 */
    // @Excel(name = "终端地址")
    private String terminalAddress;

    /** 物联点名称 */
    // @Excel(name = "物联点名称")
    private String iotName;

    /** 物联点编号 */
    // @Excel(name = "物联点编号")
    private String iotNumber;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;

    /** 用户编号 */
    // @Excel(name = "用户编号")
    private String userNumber;

    /** 台区名称 */
    // @Excel(name = "台区名称")
    private String platformName;

    /** 台区编号 */
    // @Excel(name = "台区编号")
    private String platformNumber;

    /** 数据日期 */
    // @Excel(name = "数据日期")
    private Date dataDate;

    @Excel(name = "数据日期")
    private String dataDate1;

    /** 时间 */
    @Excel(name = "时间")
    private String dataTime;

    /** 采样方式 */
    // @Excel(name = "采样方式")
    private String samplingMode;

    /** 入库时间 */
    // @Excel(name = "入库时间")
    private String entryTime;

    /** 总有功功率(kW) */
    @Excel(name = "总有功功率(kW)")
    private String totalActivePower;

    /** A相有功功率(kW) */
    // @Excel(name = "A相有功功率(kW)")
    private Long aActivePower;

    /** B相有功功率(kW) */
    // @Excel(name = "B相有功功率(kW)")
    private Long bActivePower;

    /** C相有功功率(kW) */
    // @Excel(name = "C相有功功率(kW)")
    private Long cActivePower;

    /** 总无功功率(kW) */
    // @Excel(name = "总无功功率(kW)")
    private Long totalReactivePower;

    /** A相无功功率(kW) */
    // @Excel(name = "A相无功功率(kW)")
    private Long aReactivePower;

    /** B相无功功率(kW) */
    // @Excel(name = "B相无功功率(kW)")
    private Long bReactivePower;

    /** C相无功功率(kW) */
    // @Excel(name = "C相无功功率(kW)")
    private Long cReactivePower;

    private Date fullTime;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setMeterNumber(String meterNumber)
    {
        this.meterNumber = meterNumber;
    }

    public String getMeterNumber()
    {
        return meterNumber;
    }
    public void setMeterAddress(String meterAddress)
    {
        this.meterAddress = meterAddress;
    }

    public String getMeterAddress()
    {
        return meterAddress;
    }
    public void setMeterProtocol(String meterProtocol)
    {
        this.meterProtocol = meterProtocol;
    }

    public String getMeterProtocol()
    {
        return meterProtocol;
    }
    public void setTerminalAddress(String terminalAddress)
    {
        this.terminalAddress = terminalAddress;
    }

    public String getTerminalAddress()
    {
        return terminalAddress;
    }
    public void setIotName(String iotName)
    {
        this.iotName = iotName;
    }

    public String getIotName()
    {
        return iotName;
    }
    public void setIotNumber(String iotNumber)
    {
        this.iotNumber = iotNumber;
    }

    public String getIotNumber()
    {
        return iotNumber;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setUserNumber(String userNumber)
    {
        this.userNumber = userNumber;
    }

    public String getUserNumber()
    {
        return userNumber;
    }
    public void setPlatformName(String platformName)
    {
        this.platformName = platformName;
    }

    public String getPlatformName()
    {
        return platformName;
    }
    public void setPlatformNumber(String platformNumber)
    {
        this.platformNumber = platformNumber;
    }

    public String getPlatformNumber()
    {
        return platformNumber;
    }

    public void setDataTime(String dataTime)
    {
        this.dataTime = dataTime;
    }

    public String getDataTime()
    {
        return dataTime;
    }

    public String getDataDate1() {
        return dataDate1;
    }

    public void setDataDate1(String dataDate1) {
        this.dataDate1 = dataDate1;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    public void setSamplingMode(String samplingMode)
    {
        this.samplingMode = samplingMode;
    }

    public String getSamplingMode()
    {
        return samplingMode;
    }
    public void setEntryTime(String entryTime)
    {
        this.entryTime = entryTime;
    }

    public String getEntryTime()
    {
        return entryTime;
    }

    public String getTotalActivePower() {
        return totalActivePower;
    }

    public void setTotalActivePower(String totalActivePower) {
        this.totalActivePower = totalActivePower;
    }

    public void setaActivePower(Long aActivePower)
    {
        this.aActivePower = aActivePower;
    }

    public Long getaActivePower()
    {
        return aActivePower;
    }
    public void setbActivePower(Long bActivePower)
    {
        this.bActivePower = bActivePower;
    }

    public Long getbActivePower()
    {
        return bActivePower;
    }
    public void setcActivePower(Long cActivePower)
    {
        this.cActivePower = cActivePower;
    }

    public Long getcActivePower()
    {
        return cActivePower;
    }
    public void setTotalReactivePower(Long totalReactivePower)
    {
        this.totalReactivePower = totalReactivePower;
    }

    public Long getTotalReactivePower()
    {
        return totalReactivePower;
    }
    public void setaReactivePower(Long aReactivePower)
    {
        this.aReactivePower = aReactivePower;
    }

    public Long getaReactivePower()
    {
        return aReactivePower;
    }
    public void setbReactivePower(Long bReactivePower)
    {
        this.bReactivePower = bReactivePower;
    }

    public Long getbReactivePower()
    {
        return bReactivePower;
    }
    public void setcReactivePower(Long cReactivePower)
    {
        this.cReactivePower = cReactivePower;
    }

    public Long getcReactivePower()
    {
        return cReactivePower;
    }

    public Date getFullTime() {
        return fullTime;
    }

    public void setFullTime(Date fullTime) {
        this.fullTime = fullTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("meterNumber", getMeterNumber())
            .append("meterAddress", getMeterAddress())
            .append("meterProtocol", getMeterProtocol())
            .append("terminalAddress", getTerminalAddress())
            .append("iotName", getIotName())
            .append("iotNumber", getIotNumber())
            .append("userName", getUserName())
            .append("userNumber", getUserNumber())
            .append("platformName", getPlatformName())
            .append("platformNumber", getPlatformNumber())
            .append("dataDate", getDataDate())
            .append("dataTime", getDataTime())
            .append("samplingMode", getSamplingMode())
            .append("entryTime", getEntryTime())
            .append("totalActivePower", getTotalActivePower())
            .append("aActivePower", getaActivePower())
            .append("bActivePower", getbActivePower())
            .append("cActivePower", getcActivePower())
            .append("totalReactivePower", getTotalReactivePower())
            .append("aReactivePower", getaReactivePower())
            .append("bReactivePower", getbReactivePower())
            .append("cReactivePower", getcReactivePower())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("fullTime", getFullTime())
            .toString();
    }
}
