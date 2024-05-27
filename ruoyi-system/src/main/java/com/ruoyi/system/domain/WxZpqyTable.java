package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 招聘企业信息对象 wx_zpqy_table
 * 
 * @author ruoyi
 * @date 2022-11-23
 */
public class WxZpqyTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 企业名称 */
    @Excel(name = "企业名称")
    private String qymc;

    /** 统一信用代码 */
    @Excel(name = "统一信用代码")
    private String tyxydm;

    /** 企业地址 */
    @Excel(name = "企业地址")
    private String qydz;

    /** 企业性质 */
    @Excel(name = "企业性质")
    private String qyxz;

    /** 企业规模 */
    @Excel(name = "企业规模")
    private String qygm;

    /** 企业风采 */
    @Excel(name = "企业风采")
    private String qyfc;

    /** 招聘id */
    @Excel(name = "招聘id")
    private Long zpid;

    /** 岗位（岗位字典） */
    @Excel(name = "岗位", readConverterExp = "岗=位字典")
    private String gw;

    /** 人数 */
    @Excel(name = "人数")
    private Long rs;

    /** 性别（0 女，1 男） */
    @Excel(name = "性别", readConverterExp = "0=,女=，1,男=")
    private String sex;

    /** 岗位要求 */
    @Excel(name = "岗位要求")
    private String gwyq;

    /** 薪资待遇（上） */
    @Excel(name = "薪资待遇", readConverterExp = "上=")
    private String xzdyUp;

    /** 薪资待遇（下） */
    @Excel(name = "薪资待遇", readConverterExp = "下=")
    private String xzdyDown;

    /** 其他待遇 */
    @Excel(name = "其他待遇")
    private String qtdy;

    /** 联系人 */
    @Excel(name = "联系人")
    private String lxr;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String lxdh;

    /** 是否发布（0 是，1 否） */
    @Excel(name = "是否发布", readConverterExp = "0=,是=，1,否=")
    private String ifPublic;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publicTime;

    /** 求职人电话 */
    @Excel(name = "求职人电话")
    private String qzrPhone;

    /** 受邀人电话 */
    @Excel(name = "受邀人电话")
    private String syrPhone;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setQymc(String qymc) 
    {
        this.qymc = qymc;
    }

    public String getQymc() 
    {
        return qymc;
    }
    public void setTyxydm(String tyxydm) 
    {
        this.tyxydm = tyxydm;
    }

    public String getTyxydm() 
    {
        return tyxydm;
    }
    public void setQydz(String qydz) 
    {
        this.qydz = qydz;
    }

    public String getQydz() 
    {
        return qydz;
    }
    public void setQyxz(String qyxz) 
    {
        this.qyxz = qyxz;
    }

    public String getQyxz() 
    {
        return qyxz;
    }
    public void setQygm(String qygm) 
    {
        this.qygm = qygm;
    }

    public String getQygm() 
    {
        return qygm;
    }
    public void setQyfc(String qyfc) 
    {
        this.qyfc = qyfc;
    }

    public String getQyfc() 
    {
        return qyfc;
    }
    public void setZpid(Long zpid) 
    {
        this.zpid = zpid;
    }

    public Long getZpid() 
    {
        return zpid;
    }
    public void setGw(String gw) 
    {
        this.gw = gw;
    }

    public String getGw() 
    {
        return gw;
    }
    public void setRs(Long rs) 
    {
        this.rs = rs;
    }

    public Long getRs() 
    {
        return rs;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setGwyq(String gwyq) 
    {
        this.gwyq = gwyq;
    }

    public String getGwyq() 
    {
        return gwyq;
    }
    public void setXzdyUp(String xzdyUp) 
    {
        this.xzdyUp = xzdyUp;
    }

    public String getXzdyUp() 
    {
        return xzdyUp;
    }
    public void setXzdyDown(String xzdyDown) 
    {
        this.xzdyDown = xzdyDown;
    }

    public String getXzdyDown() 
    {
        return xzdyDown;
    }
    public void setQtdy(String qtdy) 
    {
        this.qtdy = qtdy;
    }

    public String getQtdy() 
    {
        return qtdy;
    }
    public void setLxr(String lxr) 
    {
        this.lxr = lxr;
    }

    public String getLxr() 
    {
        return lxr;
    }
    public void setLxdh(String lxdh) 
    {
        this.lxdh = lxdh;
    }

    public String getLxdh() 
    {
        return lxdh;
    }
    public void setIfPublic(String ifPublic) 
    {
        this.ifPublic = ifPublic;
    }

    public String getIfPublic() 
    {
        return ifPublic;
    }
    public void setPublicTime(Date publicTime) 
    {
        this.publicTime = publicTime;
    }

    public Date getPublicTime() 
    {
        return publicTime;
    }
    public void setQzrPhone(String qzrPhone) 
    {
        this.qzrPhone = qzrPhone;
    }

    public String getQzrPhone() 
    {
        return qzrPhone;
    }
    public void setSyrPhone(String syrPhone) 
    {
        this.syrPhone = syrPhone;
    }

    public String getSyrPhone() 
    {
        return syrPhone;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("qymc", getQymc())
            .append("tyxydm", getTyxydm())
            .append("qydz", getQydz())
            .append("qyxz", getQyxz())
            .append("qygm", getQygm())
            .append("qyfc", getQyfc())
            .append("zpid", getZpid())
            .append("gw", getGw())
            .append("rs", getRs())
            .append("sex", getSex())
            .append("gwyq", getGwyq())
            .append("xzdyUp", getXzdyUp())
            .append("xzdyDown", getXzdyDown())
            .append("qtdy", getQtdy())
            .append("lxr", getLxr())
            .append("lxdh", getLxdh())
            .append("ifPublic", getIfPublic())
            .append("publicTime", getPublicTime())
            .append("qzrPhone", getQzrPhone())
            .append("syrPhone", getSyrPhone())
            .toString();
    }
}
