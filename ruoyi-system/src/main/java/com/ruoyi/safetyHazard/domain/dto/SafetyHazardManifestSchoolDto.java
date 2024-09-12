package com.ruoyi.safetyHazard.domain.dto;

import com.alibaba.fastjson2.JSONArray;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.safetyHazard.domain.SafetyHazardManifestSchool;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 安全隐患-清单-学校对象 safety_hazard_manifest_school
 *
 * @author ruoyi
 * @date 2024-08-12
 */
@Data
public class SafetyHazardManifestSchoolDto extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private String id;

    /** 用户id */
    private Long userId;

    private String contact;

    /** 微信手机号 */
    private String wxPhone;

    private JSONArray fileListaqgl;

    private String baysl;

    private JSONArray fileListbaysl;

    private String ssygzrs;

    private String jssrs;

    private String fjssrs;

    private JSONArray fileListxyaqbzgzgzzd;

    private JSONArray fileListwqgd;

    private String wqgd;

    private JSONArray fileListfppcs;

    private JSONArray fileListfwqx;

    private String fwqx;

    private String checkedyzfczss;

    private JSONArray fileListyzfczss;

    private JSONArray fileListazfdaqm;

    private JSONArray fileListszxfssqc;

    private JSONArray fileListbcctssyjzm;

    private JSONArray fileListszaqjspd;

    private JSONArray fileListafjks;

    private String checkedxyqfg;

    private JSONArray fileListspbcsj;

    private String checkedfbhgl;

    private JSONArray fileListlsdjjc;

    private String checkedjzxdwxpdw;

    private String checkedhxabll;

    private JSONArray fileListdqkzaqjy;

    private JSONArray fileListzdyjya;

    private JSONArray fileListzxdyylqk;

    private String fzfxzlxfs;

    private String checkedfzfxzdxkzaqgz;

    private String checkedysjsycqk;

    private String checkedzdmdjf;

    private String checkedlshgxzd;

    private String ispart;

    private JSONArray fileListfqlgzlsqk;

    private JSONArray fileListxsqlzlwyh;

    private String status;


    public SafetyHazardManifestSchool convertSafetyHazardManifestSchool(){
        SafetyHazardManifestSchool s = new SafetyHazardManifestSchool();
        s.setId(this.getId());
        s.setUserId(this.getUserId());
        s.setId(this.contact);
        s.setWxPhone(this.getWxPhone());
        s.setFileListaqgl(toJSONArrayString(fileListaqgl));
        s.setBaysl(this.getBaysl());
        s.setFileListbaysl(toJSONArrayString(fileListbaysl));
        s.setSsygzrs(this.getSsygzrs());
        s.setJssrs(this.getJssrs());
        s.setFjssrs(this.getFjssrs());
        s.setFileListxyaqbzgzgzzd(toJSONArrayString(fileListxyaqbzgzgzzd));
        s.setFileListwqgd(toJSONArrayString(fileListwqgd));
        s.setWqgd(this.getWqgd());
        s.setFileListfppcs(toJSONArrayString(fileListfppcs));
        s.setFileListfwqx(toJSONArrayString(fileListfwqx));
        s.setFwqx(this.getFwqx());
        s.setCheckedyzfczss(this.getCheckedyzfczss());
        s.setFileListyzfczss(toJSONArrayString(fileListyzfczss));
        s.setFileListazfdaqm(toJSONArrayString(fileListazfdaqm));
        s.setFileListszxfssqc(toJSONArrayString(fileListszxfssqc));
        s.setFileListbcctssyjzm(toJSONArrayString(fileListbcctssyjzm));
        s.setFileListszaqjspd(toJSONArrayString(fileListszaqjspd));
        s.setFileListafjks(toJSONArrayString(fileListafjks));
        s.setCheckedxyqfg(this.getCheckedxyqfg());
        s.setFileListspbcsj(toJSONArrayString(fileListspbcsj));
        s.setCheckedfbhgl(this.getCheckedfbhgl());
        s.setFileListlsdjjc(toJSONArrayString(fileListlsdjjc));
        s.setCheckedjzxdwxpdw(this.getCheckedjzxdwxpdw());
        s.setCheckedhxabll(this.getCheckedhxabll());
        s.setFileListdqkzaqjy(toJSONArrayString(fileListdqkzaqjy));
        s.setFileListzdyjya(toJSONArrayString(fileListzdyjya));
        s.setFileListzxdyylqk(toJSONArrayString(fileListzxdyylqk));
        s.setFzfxzlxfs(this.getFzfxzlxfs());
        s.setCheckedfzfxzdxkzaqgz(this.getCheckedfzfxzdxkzaqgz());
        s.setCheckedysjsycqk(this.getCheckedysjsycqk());
        s.setCheckedzdmdjf(this.getCheckedzdmdjf());
        s.setCheckedlshgxzd(this.getCheckedlshgxzd());
        s.setIspart(this.getIspart());
        s.setFileListfqlgzlsqk(toJSONArrayString(fileListfqlgzlsqk));
        s.setFileListxsqlzlwyh(toJSONArrayString(fileListxsqlzlwyh));
        s.setCreateTime(this.getCreateTime());
        s.setUpdateTime(this.getUpdateTime());
        s.setStatus(this.getStatus());

        return s;
    }

    private String toJSONArrayString(JSONArray array){
        return array == null ? null : array.toJSONString();
    }

}
