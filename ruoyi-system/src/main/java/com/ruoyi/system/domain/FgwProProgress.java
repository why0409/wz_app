package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目进度对象 fgw_pro_progress
 * 
 * @author ruoyi
 * @date 2022-11-24
 */
public class FgwProProgress extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 归属部门 */
    @Excel(name = "归属部门")
    private Long belongtodept;

    /** 项目大类 */
    @Excel(name = "项目大类")
    private String cateType;

    /** 项目类别 */
    @Excel(name = "项目类别")
    private String proType;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String proName;

    /** 项目内容 */
    @Excel(name = "项目内容")
    private String proContent;

    /** 年度投资 */
    @Excel(name = "年度投资")
    private String yPlanTz;

    /** 年份 */
    @Excel(name = "年份")
    private String year;

    /** 累计完成投资 */
    @Excel(name = "累计完成投资")
    private String sumTz;

    /** 竣工建成 */
    @Excel(name = "竣工建成")
    private String proFinish;

    /** 存在问题 */
    @Excel(name = "存在问题")
    private String existsProblem;

    /** 下一步计划 */
    @Excel(name = "下一步计划")
    private String nextPlan;

    /** 项目是否缺建设用地指标 */
    @Excel(name = "项目是否缺建设用地指标")
    private String qsTarget;

    /** 是否需要区级、市级协调解决的问题 */
    @Excel(name = "是否需要区级、市级协调解决的问题")
    private String djjProblem;

    /** 资金支付来源 */
    @Excel(name = "资金支付来源")
    private String payFrom;

    /** 工作目标 */
    @Excel(name = "工作目标")
    private String workTarget;

    /** 区分管领导 */
    @Excel(name = "区分管领导")
    private String leader;

    /** 红黄绿标识 */
    @Excel(name = "红黄绿标识")
    private String hhlType;

    /** 备用字段1 */
    @Excel(name = "备用字段1")
    private String remark1;

    /** 备用字段2 */
    @Excel(name = "备用字段2")
    private String remark2;

    /** 备用字段3 */
    @Excel(name = "备用字段3")
    private String remark3;

    /** 备用字段4 */
    @Excel(name = "备用字段4")
    private String remark4;

    /** 备用字段5 */
    @Excel(name = "备用字段5")
    private String remark5;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBelongtodept(Long belongtodept) 
    {
        this.belongtodept = belongtodept;
    }

    public Long getBelongtodept() 
    {
        return belongtodept;
    }
    public void setCateType(String cateType) 
    {
        this.cateType = cateType;
    }

    public String getCateType() 
    {
        return cateType;
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
    public void setProContent(String proContent) 
    {
        this.proContent = proContent;
    }

    public String getProContent() 
    {
        return proContent;
    }
    public void setyPlanTz(String yPlanTz) 
    {
        this.yPlanTz = yPlanTz;
    }

    public String getyPlanTz() 
    {
        return yPlanTz;
    }
    public void setYear(String year) 
    {
        this.year = year;
    }

    public String getYear() 
    {
        return year;
    }
    public void setSumTz(String sumTz) 
    {
        this.sumTz = sumTz;
    }

    public String getSumTz() 
    {
        return sumTz;
    }
    public void setProFinish(String proFinish) 
    {
        this.proFinish = proFinish;
    }

    public String getProFinish() 
    {
        return proFinish;
    }
    public void setExistsProblem(String existsProblem) 
    {
        this.existsProblem = existsProblem;
    }

    public String getExistsProblem() 
    {
        return existsProblem;
    }
    public void setNextPlan(String nextPlan) 
    {
        this.nextPlan = nextPlan;
    }

    public String getNextPlan() 
    {
        return nextPlan;
    }
    public void setQsTarget(String qsTarget) 
    {
        this.qsTarget = qsTarget;
    }

    public String getQsTarget() 
    {
        return qsTarget;
    }
    public void setDjjProblem(String djjProblem) 
    {
        this.djjProblem = djjProblem;
    }

    public String getDjjProblem() 
    {
        return djjProblem;
    }
    public void setPayFrom(String payFrom) 
    {
        this.payFrom = payFrom;
    }

    public String getPayFrom() 
    {
        return payFrom;
    }
    public void setWorkTarget(String workTarget) 
    {
        this.workTarget = workTarget;
    }

    public String getWorkTarget() 
    {
        return workTarget;
    }
    public void setLeader(String leader) 
    {
        this.leader = leader;
    }

    public String getLeader() 
    {
        return leader;
    }
    public void setHhlType(String hhlType) 
    {
        this.hhlType = hhlType;
    }

    public String getHhlType() 
    {
        return hhlType;
    }
    public void setRemark1(String remark1) 
    {
        this.remark1 = remark1;
    }

    public String getRemark1() 
    {
        return remark1;
    }
    public void setRemark2(String remark2) 
    {
        this.remark2 = remark2;
    }

    public String getRemark2() 
    {
        return remark2;
    }
    public void setRemark3(String remark3) 
    {
        this.remark3 = remark3;
    }

    public String getRemark3() 
    {
        return remark3;
    }
    public void setRemark4(String remark4) 
    {
        this.remark4 = remark4;
    }

    public String getRemark4() 
    {
        return remark4;
    }
    public void setRemark5(String remark5) 
    {
        this.remark5 = remark5;
    }

    public String getRemark5() 
    {
        return remark5;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("belongtodept", getBelongtodept())
            .append("cateType", getCateType())
            .append("proType", getProType())
            .append("proName", getProName())
            .append("proContent", getProContent())
            .append("yPlanTz", getyPlanTz())
            .append("year", getYear())
            .append("sumTz", getSumTz())
            .append("proFinish", getProFinish())
            .append("existsProblem", getExistsProblem())
            .append("nextPlan", getNextPlan())
            .append("qsTarget", getQsTarget())
            .append("djjProblem", getDjjProblem())
            .append("payFrom", getPayFrom())
            .append("workTarget", getWorkTarget())
            .append("leader", getLeader())
            .append("hhlType", getHhlType())
            .append("remark", getRemark())
            .append("remark1", getRemark1())
            .append("remark2", getRemark2())
            .append("remark3", getRemark3())
            .append("remark4", getRemark4())
            .append("remark5", getRemark5())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
