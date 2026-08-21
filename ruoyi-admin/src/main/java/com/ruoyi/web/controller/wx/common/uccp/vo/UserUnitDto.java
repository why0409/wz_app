package com.ruoyi.web.controller.wx.common.uccp.vo;

import java.io.Serializable;
import java.util.List;

public class UserUnitDto implements Serializable {

    private static final long serialVersionUID = -8036680455678560333L;

    /**
     * 账号类型0：个人 1：法人 (如果是子账户登录，该字段值为1)
     */
    private String acType;

    /**
     * 01:省平台用户 02:国家个人用户 03:国家法人用户
     */
    private String nationType;
    /**
     * 是否是子账户类型
     */
    private Boolean isSubType = false;
    /**
     * 是否有安全密码
     */
    private Boolean hasSafePwd = false;

    private PerUserDto perUserVo;

    private LegalUserDto legalUserVo;

    private List<LegalUserDto> legalUserList;

    public String getAcType() {
        return acType;
    }

    public void setAcType(String acType) {
        this.acType = acType;
    }

    public PerUserDto getPerUserVo() {
        return perUserVo;
    }

    public void setPerUserVo(PerUserDto perUserVo) {
        this.perUserVo = perUserVo;
    }

    public LegalUserDto getLegalUserVo() {
        return legalUserVo;
    }

    public void setLegalUserVo(LegalUserDto legalUserVo) {
        this.legalUserVo = legalUserVo;
    }

    public List<LegalUserDto> getLegalUserList() {
        return legalUserList;
    }

    public void setLegalUserList(List<LegalUserDto> legalUserList) {
        this.legalUserList = legalUserList;
    }

    public String getNationType() {
        return nationType;
    }

    public void setNationType(String nationType) {
        this.nationType = nationType;
    }

    public Boolean getIsSubType() {
        return isSubType;
    }

    public void setIsSubType(Boolean isSubType) {
        this.isSubType = isSubType;
    }

    public Boolean getHasSafePwd() {
        return hasSafePwd;
    }

    public void setHasSafePwd(Boolean hasSafePwd) {
        this.hasSafePwd = hasSafePwd;
    }
}
