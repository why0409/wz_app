package com.ruoyi.web.controller.wx.common.uccp.vo;

import lombok.Data;

import java.io.Serializable;

@Data
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
}
