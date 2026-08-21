package com.ruoyi.web.controller.wx.common.uccp.vo;

import lombok.Data;

import java.io.Serializable;

@Data
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
     * 用户真实姓名
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
     * 居住区编码
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
     * 出生区编码
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
}
