package com.ruoyi.web.controller.wx.common.uccp.vo;

import java.io.Serializable;

public class PerUserDto implements Serializable {

    private static final long serialVersionUID = -8036680455678560344L;

    /**
     * 用户ID
     */
    private String userId;
    /**
     * 账户登录名
     */
    private String account;
    /**
     * 支付宝openId
     */
    private String alipayAccount;
    /**
     * 微信openId
     */
    private String weixinAccount;
    /**
     * 当前登录的个人用户绑定的手机号
     */
    private String bindPhone;
    /**
     * 用户真实 姓名
     */
    private String name;
    /**
     * 证件类型
     */
    private String credentType;

    private String credentTypeName;

    /**
     * 证件号码
     */
    private String credentNo;
    /**
     * 证件有效期开始时间
     */
    private String validBeginDate;
    /**
     * 证件有效期结束时间
     */
    private String validEndDate;
    /**
     * 证件是否长期有效
     */
    private String isLongTermValid;
    /**
     * 认证等级
     */
    private String certificationLevel;

    private String certificationLevelName;

    private String nationUserLevel;

    private String nationUserLevelName;
    /**
     * 性别
     */
    private String sex;

    private String sexName;
    /**
     * 民族
     */
    private String nation;

    private String nationName;
    /**
     * 国籍
     */
    private String nationality;

    private String nationalityName;

    /**
     * 居住地省份编码
     */
    private String liveProvinceCode;
    /**
     * 居住地市区编码
     */
    private String liveCityCode;
    /**
     * 居住 区编码
     */
    private String liveAreaCode;
    /**
     * 居住地地址
     */
    private String liveAddress;
    /**
     * 居住地的补充信息
     */
    private String liveNote;
    /**
     * 出生地省份编码
     */
    private String birthProvinceCode;
    /**
     * 出生地市区编码
     */
    private String birthCityCode;
    /**
     * 出生 区编码
     */
    private String birthAreaCode;
    /**
     * 出生地地址
     */
    private String birthAddress;
    /**
     * 出生地的补充信息
     */
    private String birthNote;
    /**
     * 出生日期
     */
    private String birthDat;

    /**
     * 用户头像地址
     */
    private String headImgUrl = "";

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBindPhone() {
        return bindPhone;
    }

    public void setBindPhone(String bindPhone) {
        this.bindPhone = bindPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getLiveProvinceCode() {
        return liveProvinceCode;
    }

    public void setLiveProvinceCode(String liveProvinceCode) {
        this.liveProvinceCode = liveProvinceCode;
    }

    public String getLiveCityCode() {
        return liveCityCode;
    }

    public void setLiveCityCode(String liveCityCode) {
        this.liveCityCode = liveCityCode;
    }

    public String getLiveAreaCode() {
        return liveAreaCode;
    }

    public void setLiveAreaCode(String liveAreaCode) {
        this.liveAreaCode = liveAreaCode;
    }

    public String getLiveAddress() {
        return liveAddress;
    }

    public void setLiveAddress(String liveAddress) {
        this.liveAddress = liveAddress;
    }

    public String getLiveNote() {
        return liveNote;
    }

    public void setLiveNote(String liveNote) {
        this.liveNote = liveNote;
    }

    public String getBirthProvinceCode() {
        return birthProvinceCode;
    }

    public void setBirthProvinceCode(String birthProvinceCode) {
        this.birthProvinceCode = birthProvinceCode;
    }

    public String getBirthCityCode() {
        return birthCityCode;
    }

    public void setBirthCityCode(String birthCityCode) {
        this.birthCityCode = birthCityCode;
    }

    public String getBirthAreaCode() {
        return birthAreaCode;
    }

    public void setBirthAreaCode(String birthAreaCode) {
        this.birthAreaCode = birthAreaCode;
    }

    public String getBirthAddress() {
        return birthAddress;
    }

    public void setBirthAddress(String birthAddress) {
        this.birthAddress = birthAddress;
    }

    public String getBirthNote() {
        return birthNote;
    }

    public void setBirthNote(String birthNote) {
        this.birthNote = birthNote;
    }

    public String getBirthDat() {
        return birthDat;
    }

    public void setBirthDat(String birthDat) {
        this.birthDat = birthDat;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
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

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    public String getNationalityName() {
        return nationalityName;
    }

    public void setNationalityName(String nationalityName) {
        this.nationalityName = nationalityName;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getValidBeginDate() {
        return validBeginDate;
    }

    public void setValidBeginDate(String validBeginDate) {
        this.validBeginDate = validBeginDate;
    }

    public String getValidEndDate() {
        return validEndDate;
    }

    public void setValidEndDate(String validEndDate) {
        this.validEndDate = validEndDate;
    }

    public String getIsLongTermValid() {
        return isLongTermValid;
    }

    public void setIsLongTermValid(String isLongTermValid) {
        this.isLongTermValid = isLongTermValid;
    }

    public String getAlipayAccount() {
        return alipayAccount;
    }

    public void setAlipayAccount(String alipayAccount) {
        this.alipayAccount = alipayAccount;
    }

    public String getWeixinAccount() {
        return weixinAccount;
    }

    public void setWeixinAccount(String weixinAccount) {
        this.weixinAccount = weixinAccount;
    }

    public String getNationUserLevel() {
        return nationUserLevel;
    }

    public void setNationUserLevel(String nationUserLevel) {
        this.nationUserLevel = nationUserLevel;
    }

    public String getNationUserLevelName() {
        return nationUserLevelName;
    }

    public void setNationUserLevelName(String nationUserLevelName) {
        this.nationUserLevelName = nationUserLevelName;
    }
}
