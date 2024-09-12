package com.ruoyi.safetyHazard.domain;

import com.alibaba.fastjson2.JSONArray;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 安全隐患-清单-学校对象 safety_hazard_manifest_school
 *
 * @author ruoyi
 * @date 2024-08-12
 */
public class SafetyHazardManifestSchool extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private String id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    @Excel(name = "填报人")
    private String contact;

    /** 微信手机号 */
    @Excel(name = "填报人手机号")
    private String wxPhone;

    private String fileListaqgl;

    private String baysl;

    private String fileListbaysl;

    private String ssygzrs;

    private String jssrs;

    private String fjssrs;

    private String fileListxyaqbzgzgzzd;

    private String fileListwqgd;

    private String wqgd;

    private String fileListfppcs;

    private String fileListfwqx;

    private String fwqx;

    private String checkedyzfczss;

    private String fileListyzfczss;

    private String fileListazfdaqm;

    private String fileListszxfssqc;

    private String fileListbcctssyjzm;

    private String fileListszaqjspd;

    private String fileListafjks;

    private String checkedxyqfg;

    private String fileListspbcsj;

    private String checkedfbhgl;

    private String fileListlsdjjc;

    private String checkedjzxdwxpdw;

    private String checkedhxabll;

    private String fileListdqkzaqjy;

    private String fileListzdyjya;

    private String fileListzxdyylqk;

    private String fzfxzlxfs;

    private String checkedfzfxzdxkzaqgz;

    private String checkedysjsycqk;

    private String checkedzdmdjf;

    private String checkedlshgxzd;

    private String ispart;

    private String fileListfqlgzlsqk;

    private String fileListxsqlzlwyh;

    private String dcReply;

    /** 自查回复 */
    private String zcReply;

    private String status;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setWxPhone(String wxPhone)
    {
        this.wxPhone = wxPhone;
    }

    public String getWxPhone()
    {
        return wxPhone;
    }
    public void setFileListaqgl(String fileListaqgl)
    {
        this.fileListaqgl = fileListaqgl;
    }

    public String getFileListaqgl()
    {
        return fileListaqgl;
    }
    public void setBaysl(String baysl)
    {
        this.baysl = baysl;
    }

    public String getBaysl()
    {
        return baysl;
    }
    public void setFileListbaysl(String fileListbaysl)
    {
        this.fileListbaysl = fileListbaysl;
    }

    public String getFileListbaysl()
    {
        return fileListbaysl;
    }
    public void setSsygzrs(String ssygzrs)
    {
        this.ssygzrs = ssygzrs;
    }

    public String getSsygzrs()
    {
        return ssygzrs;
    }
    public void setJssrs(String jssrs)
    {
        this.jssrs = jssrs;
    }

    public String getJssrs()
    {
        return jssrs;
    }
    public void setFjssrs(String fjssrs)
    {
        this.fjssrs = fjssrs;
    }

    public String getFjssrs()
    {
        return fjssrs;
    }
    public void setFileListxyaqbzgzgzzd(String fileListxyaqbzgzgzzd)
    {
        this.fileListxyaqbzgzgzzd = fileListxyaqbzgzgzzd;
    }

    public String getFileListxyaqbzgzgzzd()
    {
        return fileListxyaqbzgzgzzd;
    }
    public void setFileListwqgd(String fileListwqgd)
    {
        this.fileListwqgd = fileListwqgd;
    }

    public String getFileListwqgd()
    {
        return fileListwqgd;
    }
    public void setWqgd(String wqgd)
    {
        this.wqgd = wqgd;
    }

    public String getWqgd()
    {
        return wqgd;
    }
    public void setFileListfppcs(String fileListfppcs)
    {
        this.fileListfppcs = fileListfppcs;
    }

    public String getFileListfppcs()
    {
        return fileListfppcs;
    }
    public void setFileListfwqx(String fileListfwqx)
    {
        this.fileListfwqx = fileListfwqx;
    }

    public String getFileListfwqx()
    {
        return fileListfwqx;
    }
    public void setFwqx(String fwqx)
    {
        this.fwqx = fwqx;
    }

    public String getFwqx()
    {
        return fwqx;
    }
    public void setCheckedyzfczss(String checkedyzfczss)
    {
        this.checkedyzfczss = checkedyzfczss;
    }

    public String getCheckedyzfczss()
    {
        return checkedyzfczss;
    }
    public void setFileListyzfczss(String fileListyzfczss)
    {
        this.fileListyzfczss = fileListyzfczss;
    }

    public String getFileListyzfczss()
    {
        return fileListyzfczss;
    }
    public void setFileListazfdaqm(String fileListazfdaqm)
    {
        this.fileListazfdaqm = fileListazfdaqm;
    }

    public String getFileListazfdaqm()
    {
        return fileListazfdaqm;
    }
    public void setFileListszxfssqc(String fileListszxfssqc)
    {
        this.fileListszxfssqc = fileListszxfssqc;
    }

    public String getFileListszxfssqc()
    {
        return fileListszxfssqc;
    }
    public void setFileListbcctssyjzm(String fileListbcctssyjzm)
    {
        this.fileListbcctssyjzm = fileListbcctssyjzm;
    }

    public String getFileListbcctssyjzm()
    {
        return fileListbcctssyjzm;
    }
    public void setFileListszaqjspd(String fileListszaqjspd)
    {
        this.fileListszaqjspd = fileListszaqjspd;
    }

    public String getFileListszaqjspd()
    {
        return fileListszaqjspd;
    }
    public void setFileListafjks(String fileListafjks)
    {
        this.fileListafjks = fileListafjks;
    }

    public String getFileListafjks()
    {
        return fileListafjks;
    }
    public void setCheckedxyqfg(String checkedxyqfg)
    {
        this.checkedxyqfg = checkedxyqfg;
    }

    public String getCheckedxyqfg()
    {
        return checkedxyqfg;
    }
    public void setFileListspbcsj(String fileListspbcsj)
    {
        this.fileListspbcsj = fileListspbcsj;
    }

    public String getFileListspbcsj()
    {
        return fileListspbcsj;
    }
    public void setCheckedfbhgl(String checkedfbhgl)
    {
        this.checkedfbhgl = checkedfbhgl;
    }

    public String getCheckedfbhgl()
    {
        return checkedfbhgl;
    }
    public void setFileListlsdjjc(String fileListlsdjjc)
    {
        this.fileListlsdjjc = fileListlsdjjc;
    }

    public String getFileListlsdjjc()
    {
        return fileListlsdjjc;
    }
    public void setCheckedjzxdwxpdw(String checkedjzxdwxpdw)
    {
        this.checkedjzxdwxpdw = checkedjzxdwxpdw;
    }

    public String getCheckedjzxdwxpdw()
    {
        return checkedjzxdwxpdw;
    }
    public void setCheckedhxabll(String checkedhxabll)
    {
        this.checkedhxabll = checkedhxabll;
    }

    public String getCheckedhxabll()
    {
        return checkedhxabll;
    }
    public void setFileListdqkzaqjy(String fileListdqkzaqjy)
    {
        this.fileListdqkzaqjy = fileListdqkzaqjy;
    }

    public String getFileListdqkzaqjy()
    {
        return fileListdqkzaqjy;
    }
    public void setFileListzdyjya(String fileListzdyjya)
    {
        this.fileListzdyjya = fileListzdyjya;
    }

    public String getFileListzdyjya()
    {
        return fileListzdyjya;
    }
    public void setFileListzxdyylqk(String fileListzxdyylqk)
    {
        this.fileListzxdyylqk = fileListzxdyylqk;
    }

    public String getFileListzxdyylqk()
    {
        return fileListzxdyylqk;
    }
    public void setFzfxzlxfs(String fzfxzlxfs)
    {
        this.fzfxzlxfs = fzfxzlxfs;
    }

    public String getFzfxzlxfs()
    {
        return fzfxzlxfs;
    }
    public void setCheckedfzfxzdxkzaqgz(String checkedfzfxzdxkzaqgz)
    {
        this.checkedfzfxzdxkzaqgz = checkedfzfxzdxkzaqgz;
    }

    public String getCheckedfzfxzdxkzaqgz()
    {
        return checkedfzfxzdxkzaqgz;
    }
    public void setCheckedysjsycqk(String checkedysjsycqk)
    {
        this.checkedysjsycqk = checkedysjsycqk;
    }

    public String getCheckedysjsycqk()
    {
        return checkedysjsycqk;
    }
    public void setCheckedzdmdjf(String checkedzdmdjf)
    {
        this.checkedzdmdjf = checkedzdmdjf;
    }

    public String getCheckedzdmdjf()
    {
        return checkedzdmdjf;
    }
    public void setCheckedlshgxzd(String checkedlshgxzd)
    {
        this.checkedlshgxzd = checkedlshgxzd;
    }

    public String getCheckedlshgxzd()
    {
        return checkedlshgxzd;
    }

    public String getFileListfqlgzlsqk() {
        return this.fileListfqlgzlsqk;
    }

    public void setFileListfqlgzlsqk(String fileListfqlgzlsqk) {
        this.fileListfqlgzlsqk = fileListfqlgzlsqk;
    }

    public String getFileListxsqlzlwyh() {
        return this.fileListxsqlzlwyh;
    }

    public void setFileListxsqlzlwyh(String fileListxsqlzlwyh) {
        this.fileListxsqlzlwyh = fileListxsqlzlwyh;
    }

    public void setIspart(String ispart)
    {
        this.ispart = ispart;
    }

    public String getIspart()
    {
        return ispart;
    }

    public String getDcReply() {
        return this.dcReply;
    }

    public void setDcReply(String dcReply) {
        this.dcReply = dcReply;
    }

    public String getZcReply() {
        return this.zcReply;
    }

    public void setZcReply(String zcReply) {
        this.zcReply = zcReply;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("userId", getUserId())
                .append("wxPhone", getWxPhone())
                .append("fileListaqgl", getFileListaqgl())
                .append("baysl", getBaysl())
                .append("fileListbaysl", getFileListbaysl())
                .append("ssygzrs", getSsygzrs())
                .append("jssrs", getJssrs())
                .append("fjssrs", getFjssrs())
                .append("fileListxyaqbzgzgzzd", getFileListxyaqbzgzgzzd())
                .append("fileListwqgd", getFileListwqgd())
                .append("wqgd", getWqgd())
                .append("fileListfppcs", getFileListfppcs())
                .append("fileListfwqx", getFileListfwqx())
                .append("fwqx", getFwqx())
                .append("checkedyzfczss", getCheckedyzfczss())
                .append("fileListyzfczss", getFileListyzfczss())
                .append("fileListazfdaqm", getFileListazfdaqm())
                .append("fileListszxfssqc", getFileListszxfssqc())
                .append("fileListbcctssyjzm", getFileListbcctssyjzm())
                .append("fileListszaqjspd", getFileListszaqjspd())
                .append("fileListafjks", getFileListafjks())
                .append("checkedxyqfg", getCheckedxyqfg())
                .append("fileListspbcsj", getFileListspbcsj())
                .append("checkedfbhgl", getCheckedfbhgl())
                .append("fileListlsdjjc", getFileListlsdjjc())
                .append("checkedjzxdwxpdw", getCheckedjzxdwxpdw())
                .append("checkedhxabll", getCheckedhxabll())
                .append("fileListdqkzaqjy", getFileListdqkzaqjy())
                .append("fileListzdyjya", getFileListzdyjya())
                .append("fileListzxdyylqk", getFileListzxdyylqk())
                .append("fzfxzlxfs", getFzfxzlxfs())
                .append("fileListzxdyylqk", getFileListfqlgzlsqk())
                .append("fzfxzlxfs", getFileListxsqlzlwyh())
                .append("checkedfzfxzdxkzaqgz", getCheckedfzfxzdxkzaqgz())
                .append("checkedysjsycqk", getCheckedysjsycqk())
                .append("checkedzdmdjf", getCheckedzdmdjf())
                .append("checkedlshgxzd", getCheckedlshgxzd())
                .append("ispart", getIspart())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
