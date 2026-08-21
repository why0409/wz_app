package com.ruoyi.web.controller.wx.common.uccp.vo;

import java.io.Serializable;

public class LegalUserDto implements Serializable {

    private static final long serialVersionUID = -8036680455678560355L;

    private String legUserId;

    private String account;

    private String orgName;

    private String orgType;

    private String orgTypeName;

    private String credentType;

    private String credentTypeName;

    private String credentNo;

    private String certificationLevel;

    private String certificationLevelName;

    private String email;

    private String telephone;

    private String bindPhone;

    private String addressDetail;

    // 成立日期
    private String foundTime;

    private String identityNo;

    private String identityType;

    private String identityTypeName;

    private String name;

    private String sex;

    private String sexName;

    private String nation;

    private String nationName;

    // 法定代表人 住址
    private String legalUserAdress;

    // 统一社会信用
    private String uscCode;

    // 注册登记号
    private String registCode;

    /**
     * 用户头像地址
     */
    private String headImgUrl = "";

    public String getLegUserId() {
        return legUserId;
    }

    public void setLegUserId(String legUserId) {
        this.legUserId = legUserId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getCredentType() {
        return credentType;
    }

    public void setCredentType(String credentType) {
        this.credentType = credentType;
    }

    public String getCredentNo() {
        return credentNo;
    }

    public void setCredentNo(String credentNo) {
        this.credentNo = credentNo;
    }

    public String getCertificationLevel() {
        return certificationLevel;
    }

    public void setCertificationLevel(String certificationLevel) {
        this.certificationLevel = certificationLevel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getBindPhone() {
        return bindPhone;
    }

    public void setBindPhone(String bindPhone) {
        this.bindPhone = bindPhone;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getFoundTime() {
        return foundTime;
    }

    public void setFoundTime(String foundTime) {
        this.foundTime = foundTime;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getLegalUserAdress() {
        return legalUserAdress;
    }

    public void setLegalUserAdress(String legalUserAdress) {
        this.legalUserAdress = legalUserAdress;
    }

    public String getUscCode() {
        return uscCode;
    }

    public void setUscCode(String uscCode) {
        this.uscCode = uscCode;
    }

    public String getRegistCode() {
        return registCode;
    }

    public void setRegistCode(String registCode) {
        this.registCode = registCode;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getOrgTypeName() {
        return orgTypeName;
    }

    public void setOrgTypeName(String orgTypeName) {
        this.orgTypeName = orgTypeName;
    }

    public String getCredentTypeName() {
        return credentTypeName;
    }

    public void setCredentTypeName(String credentTypeName) {
        this.credentTypeName = credentTypeName;
    }

    public String getCertificationLevelName() {
        return certificationLevelName;
    }

    public void setCertificationLevelName(String certificationLevelName) {
        this.certificationLevelName = certificationLevelName;
    }

    public String getIdentityTypeName() {
        return identityTypeName;
    }

    public void setIdentityTypeName(String identityTypeName) {
        this.identityTypeName = identityTypeName;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
    }
}
