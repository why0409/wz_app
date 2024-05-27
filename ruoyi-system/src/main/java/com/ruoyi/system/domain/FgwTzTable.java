package com.ruoyi.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 数据维护对象 fgw_tz_table
 * 
 * @author ruoyi
 * @date 2022-11-17
 */
public class FgwTzTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 序号 */
    @Excel(name = "序号")
    private Integer serialno;

    /** 项目类型  1-调增项目 2-调减项目 */
    @Excel(name = "项目类型")
    private String proType;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String proName;

    /** 项目年份 */
    @Excel(name = "项目年份")
    private String proYear;

    /** 建设规模和内容 */
    @Excel(name = "建设规模和内容")
    private String proContent;

    /** 总投资  小计 */
    @Excel(name = "小计")
    private BigDecimal sumSubtotal;

    /** 总投资  上级补助 */
    @Excel(name = "上级补助")
    private BigDecimal sumSjbz;

    /** 总投资  区本级   */
    @Excel(name = "区本级  ")
    private BigDecimal sumQbj;

    /** 总投资  其他 */
    @Excel(name = "其他")
    private BigDecimal sumOther;

    /** 年份  小计 */
    @Excel(name = "小计")
    private BigDecimal yearSubtotal;

    /** 年份  上级补助 */
    @Excel(name = "上级补助")
    private BigDecimal yearSjbz;

    /** 年份  区本级   */
    @Excel(name = "区本级  ")
    private BigDecimal yearQbj;

    /** 年份  其他 */
    @Excel(name = "其他")
    private BigDecimal yearOther;

    /** 目前进展情况 */
    @Excel(name = "目前进展情况")
    private String curProgress;

    /** 年份建设目标 */
    @Excel(name = "年份建设目标")
    private String yearJsmb;

    /** 下一步工作计划 */
    @Excel(name = "下一步工作计划")
    private String nextPlan;

    /** 区分管领导 */
    @Excel(name = "区分管领导")
    private String areaLeader;

    /** 责任单位 */
    @Excel(name = "责任单位")
    private String dutyOrgan;

    /** 备用字段1 */
    private String extend1;

    /** 备用字段2 */
    private String extend2;

    /** 备用字段3 */
    private String extend3;

    /** 备用字段4 */
    private String extend4;

    /** 备用字段5 */
    private String extend5;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSerialno(Integer serialno) 
    {
        this.serialno = serialno;
    }

    public Integer getSerialno() 
    {
        return serialno;
    }
    public void setProType(String proType) 
    {
        this.proType = proType;
    }

    public String getProType() 
    {
        return proType;
    }
    public void setProName(String proName) 
    {
        this.proName = proName;
    }

    public String getProName() 
    {
        return proName;
    }
    public void setProYear(String proYear) 
    {
        this.proYear = proYear;
    }

    public String getProYear() 
    {
        return proYear;
    }
    public void setProContent(String proContent) 
    {
        this.proContent = proContent;
    }

    public String getProContent() 
    {
        return proContent;
    }
    public void setSumSubtotal(BigDecimal sumSubtotal) 
    {
        this.sumSubtotal = sumSubtotal;
    }

    public BigDecimal getSumSubtotal() 
    {
        return sumSubtotal;
    }
    public void setSumSjbz(BigDecimal sumSjbz) 
    {
        this.sumSjbz = sumSjbz;
    }

    public BigDecimal getSumSjbz() 
    {
        return sumSjbz;
    }
    public void setSumQbj(BigDecimal sumQbj) 
    {
        this.sumQbj = sumQbj;
    }

    public BigDecimal getSumQbj() 
    {
        return sumQbj;
    }
    public void setSumOther(BigDecimal sumOther) 
    {
        this.sumOther = sumOther;
    }

    public BigDecimal getSumOther() 
    {
        return sumOther;
    }
    public void setYearSubtotal(BigDecimal yearSubtotal) 
    {
        this.yearSubtotal = yearSubtotal;
    }

    public BigDecimal getYearSubtotal() 
    {
        return yearSubtotal;
    }
    public void setYearSjbz(BigDecimal yearSjbz) 
    {
        this.yearSjbz = yearSjbz;
    }

    public BigDecimal getYearSjbz() 
    {
        return yearSjbz;
    }
    public void setYearQbj(BigDecimal yearQbj) 
    {
        this.yearQbj = yearQbj;
    }

    public BigDecimal getYearQbj() 
    {
        return yearQbj;
    }
    public void setYearOther(BigDecimal yearOther) 
    {
        this.yearOther = yearOther;
    }

    public BigDecimal getYearOther() 
    {
        return yearOther;
    }
    public void setCurProgress(String curProgress) 
    {
        this.curProgress = curProgress;
    }

    public String getCurProgress() 
    {
        return curProgress;
    }
    public void setYearJsmb(String yearJsmb) 
    {
        this.yearJsmb = yearJsmb;
    }

    public String getYearJsmb() 
    {
        return yearJsmb;
    }
    public void setNextPlan(String nextPlan) 
    {
        this.nextPlan = nextPlan;
    }

    public String getNextPlan() 
    {
        return nextPlan;
    }
    public void setAreaLeader(String areaLeader) 
    {
        this.areaLeader = areaLeader;
    }

    public String getAreaLeader() 
    {
        return areaLeader;
    }
    public void setDutyOrgan(String dutyOrgan) 
    {
        this.dutyOrgan = dutyOrgan;
    }

    public String getDutyOrgan() 
    {
        return dutyOrgan;
    }
    public void setExtend1(String extend1) 
    {
        this.extend1 = extend1;
    }

    public String getExtend1() 
    {
        return extend1;
    }
    public void setExtend2(String extend2) 
    {
        this.extend2 = extend2;
    }

    public String getExtend2() 
    {
        return extend2;
    }
    public void setExtend3(String extend3) 
    {
        this.extend3 = extend3;
    }

    public String getExtend3() 
    {
        return extend3;
    }
    public void setExtend4(String extend4) 
    {
        this.extend4 = extend4;
    }

    public String getExtend4() 
    {
        return extend4;
    }
    public void setExtend5(String extend5) 
    {
        this.extend5 = extend5;
    }

    public String getExtend5() 
    {
        return extend5;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("serialno", getSerialno())
            .append("proType", getProType())
            .append("proName", getProName())
            .append("proYear", getProYear())
            .append("proContent", getProContent())
            .append("sumSubtotal", getSumSubtotal())
            .append("sumSjbz", getSumSjbz())
            .append("sumQbj", getSumQbj())
            .append("sumOther", getSumOther())
            .append("yearSubtotal", getYearSubtotal())
            .append("yearSjbz", getYearSjbz())
            .append("yearQbj", getYearQbj())
            .append("yearOther", getYearOther())
            .append("curProgress", getCurProgress())
            .append("yearJsmb", getYearJsmb())
            .append("nextPlan", getNextPlan())
            .append("areaLeader", getAreaLeader())
            .append("dutyOrgan", getDutyOrgan())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("extend1", getExtend1())
            .append("extend2", getExtend2())
            .append("extend3", getExtend3())
            .append("extend4", getExtend4())
            .append("extend5", getExtend5())
            .toString();
    }
}
