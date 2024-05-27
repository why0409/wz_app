package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 微信用户登录信息对象 wx_user_logininfo
 * 
 * @author ruoyi
 * @date 2022-12-11
 */
@Component
public class WxUserLogininfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 手机号 */
    @Excel(name = "微信名称")
    private String phone;

    /** 微信名称 */
    @Excel(name = "微信名称")
    private String wechatName;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idcard;

    /** 真实姓名 */
    @Excel(name = "真实姓名")
    private String realName;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 注册时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "注册时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date registerTime;

    /** 最近一次登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "最近一次登录时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;

    /** 其他号码 */
    @Excel(name = "其他号码")
    private String otherPhone;

    /** 0-未婚、1-已婚 */
    @Excel(name = "0-未婚、1-已婚")
    private String marStatus;

    /** 户籍地址-省 */
    @Excel(name = "户籍地址-省")
    private String hProvince;

    /** 户籍地址-市 */
    @Excel(name = "户籍地址-市")
    private String hCity;

    /** 户籍地址-县 */
    @Excel(name = "户籍地址-县")
    private String hCounty;

    /** 户籍地址-详细地址 */
    @Excel(name = "户籍地址-详细地址")
    private String hFullAddr;

    /** 居住地址-省 */
    @Excel(name = "居住地址-省")
    private String jProvince;

    /** 居住地址-市 */
    @Excel(name = "居住地址-市")
    private String jCity;

    /** 居住地址-县 */
    @Excel(name = "居住地址-县")
    private String jCounty;

    /** 居住地址-详细地址 */
    @Excel(name = "居住地址-详细地址")
    private String jFullAddr;

    /** 毕业学校 */
    @Excel(name = "毕业学校")
    private String school;

    /** 学历 */
    @Excel(name = "学历")
    private String xl;

    /** 技能 */
    @Excel(name = "技能")
    private String jn;

    @Excel(name = "是否在线")
    private String online;

    @Excel(name = "用户头像")
    private String photo;

    @Excel(name = "年龄")
    private String age;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Excel(name = "标签")
    private String label;

    private String openId;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setWechatName(String wechatName) 
    {
        this.wechatName = wechatName;
    }

    public String getWechatName() 
    {
        return wechatName;
    }
    public void setIdcard(String idcard) 
    {
        this.idcard = idcard;
    }

    public String getIdcard() 
    {
        return idcard;
    }
    public void setRealName(String realName) 
    {
        this.realName = realName;
    }

    public String getRealName() 
    {
        return realName;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setRegisterTime(Date registerTime) 
    {
        this.registerTime = registerTime;
    }

    public Date getRegisterTime() 
    {
        return registerTime;
    }
    public void setLoginTime(Date loginTime) 
    {
        this.loginTime = loginTime;
    }

    public Date getLoginTime() 
    {
        return loginTime;
    }
    public void setOtherPhone(String otherPhone) 
    {
        this.otherPhone = otherPhone;
    }

    public String getOtherPhone() 
    {
        return otherPhone;
    }
    public void setMarStatus(String marStatus) 
    {
        this.marStatus = marStatus;
    }

    public String getMarStatus() 
    {
        return marStatus;
    }
    public void sethProvince(String hProvince) 
    {
        this.hProvince = hProvince;
    }

    public String gethProvince() 
    {
        return hProvince;
    }
    public void sethCity(String hCity) 
    {
        this.hCity = hCity;
    }

    public String gethCity() 
    {
        return hCity;
    }
    public void sethCounty(String hCounty) 
    {
        this.hCounty = hCounty;
    }

    public String gethCounty() 
    {
        return hCounty;
    }
    public void sethFullAddr(String hFullAddr) 
    {
        this.hFullAddr = hFullAddr;
    }

    public String gethFullAddr() 
    {
        return hFullAddr;
    }
    public void setjProvince(String jProvince) 
    {
        this.jProvince = jProvince;
    }

    public String getjProvince() 
    {
        return jProvince;
    }
    public void setjCity(String jCity) 
    {
        this.jCity = jCity;
    }

    public String getjCity() 
    {
        return jCity;
    }
    public void setjCounty(String jCounty) 
    {
        this.jCounty = jCounty;
    }

    public String getjCounty() 
    {
        return jCounty;
    }
    public void setjFullAddr(String jFullAddr) 
    {
        this.jFullAddr = jFullAddr;
    }

    public String getjFullAddr() 
    {
        return jFullAddr;
    }
    public void setSchool(String school) 
    {
        this.school = school;
    }

    public String getSchool() 
    {
        return school;
    }
    public void setXl(String xl) 
    {
        this.xl = xl;
    }

    public String getXl() 
    {
        return xl;
    }
    public void setJn(String jn) 
    {
        this.jn = jn;
    }

    public String getJn() 
    {
        return jn;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("phone", getPhone())
            .append("wechatName", getWechatName())
            .append("idcard", getIdcard())
            .append("realName", getRealName())
            .append("sex", getSex())
            .append("registerTime", getRegisterTime())
            .append("loginTime", getLoginTime())
            .append("otherPhone", getOtherPhone())
            .append("marStatus", getMarStatus())
            .append("hProvince", gethProvince())
            .append("hCity", gethCity())
            .append("hCounty", gethCounty())
            .append("hFullAddr", gethFullAddr())
            .append("jProvince", getjProvince())
            .append("jCity", getjCity())
            .append("jCounty", getjCounty())
            .append("jFullAddr", getjFullAddr())
            .append("school", getSchool())
            .append("xl", getXl())
                .append("jn", getJn())
                .append("age", getAge())
                .append("openId", getOpenId())
                .append("label", getLabel())
            .toString();
    }
}
