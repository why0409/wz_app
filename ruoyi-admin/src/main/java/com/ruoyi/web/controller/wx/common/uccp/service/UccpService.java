package com.ruoyi.web.controller.wx.common.uccp.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.web.controller.wx.common.uccp.sm4.SM4Utils;
import com.ruoyi.web.controller.wx.common.uccp.util.HttpUtil;
import com.ruoyi.web.controller.wx.common.uccp.util.SignUtil;
import com.ruoyi.web.controller.wx.common.uccp.util.Signature;
import com.ruoyi.web.controller.wx.common.uccp.vo.UserUnitDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * UCCP统一认证及一体化里约网关服务
 */
@Slf4j
@Service
public class UccpService {

    @Value("${uccp.client.appCode:test}")
    private String appCode;

    @Value("${uccp.client.appSecret:1234567812345678}")
    private String appSecret;

    @Value("${uccp.restful.encrypt.switch:true}")
    private boolean encryptSwitch;

    @Value("${ly.service.url:https://api.idpdev.ahzwfw.gov.cn}")
    private String lyServiceUrl;

    @Value("${ly.service.paasId:pxq_szah_hlwtyrz}")
    private String paasId;

    @Value("${ly.service.paasToken:b0ef3c7de6873d984961ec7f67da922d}")
    private String paasToken;

    @Value("${ly.restful.getAuthCode:/ebus/pxq_szah_hlwtyrz/v3/common/getAuthCode}")
    private String lyGetAuthCode;

    @Value("${ly.restful.getUserInfo:/ebus/pxq_szah_hlwtyrz/v3/user/getUserInfo}")
    private String lyGetUserInfoUrl;

    /**
     * 获取授权码 (authCode)
     */
    public String getAuthCode() throws Exception {
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String sign = SignUtil.signAuthCode(appCode, appSecret, time);

        String url = lyServiceUrl + lyGetAuthCode;
        Map<String, Object> paramData = new HashMap<>();
        paramData.put("appKey", appSecret);

        Map<String, String> headers = new HashMap<>();
        headers.put("time", time);
        headers.put("appCode", appCode);
        headers.put("sign", sign);

        fillRioHeaders(headers);

        log.info("获取authCode请求地址: {}, appCode: {}", url, appCode);
        String result = HttpUtil.doPost(url, paramData, headers);
        if (encryptSwitch) {
            SM4Utils sm4Utils = new SM4Utils();
            result = sm4Utils.decryptData_CBC(result, appSecret);
        }
        log.info("获取authCode返回结果: {}", result);

        if (StringUtils.isEmpty(result)) {
            return null;
        }

        JSONObject jsonObject = JSON.parseObject(result);
        Object data = jsonObject.get("data");
        if (data == null) {
            return null;
        }

        if (data instanceof JSONObject) {
            return ((JSONObject) data).getString("authCode");
        } else {
            JSONObject dataObj = JSON.parseObject(data.toString());
            return dataObj != null ? dataObj.getString("authCode") : null;
        }
    }

    /**
     * 根据token获取用户信息
     */
    public UserUnitDto getUserInfo(String token) throws Exception {
        String result = getUserInfoRaw(token);
        if (StringUtils.isEmpty(result)) {
            return null;
        }

        JSONObject jsonObject = JSON.parseObject(result);
        Object data = jsonObject.get("data");
        if (data == null) {
            return JSON.parseObject(result, UserUnitDto.class);
        }

        if (data instanceof JSONObject) {
            return ((JSONObject) data).toJavaObject(UserUnitDto.class);
        } else {
            return JSON.parseObject(data.toString(), UserUnitDto.class);
        }
    }

    /**
     * 根据token获取解密后的原始响应JSON字符串
     */
    public String getUserInfoRaw(String token) throws Exception {
        String authCode = getAuthCode();
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String sign = SignUtil.signUserInfo(appCode, appSecret, token, time, authCode);

        String url = lyServiceUrl + lyGetUserInfoUrl;
        Map<String, Object> paramData = new HashMap<>();
        paramData.put("token", token);

        Map<String, String> headers = new HashMap<>();
        headers.put("authCode", authCode);
        headers.put("time", time);
        headers.put("appCode", appCode);
        headers.put("sign", sign);
        headers.put("cm", "basic,legal,credent,phone");

        fillRioHeaders(headers);

        log.info("获取用户信息请求地址: {}, token: {}", url, token);
        String result = HttpUtil.doPost(url, paramData, headers);
        if (encryptSwitch) {
            SM4Utils sm4Utils = new SM4Utils();
            result = sm4Utils.decryptData_CBC(result, appSecret);
        }
        log.info("获取用户信息返回结果: {}", result);
        return result;
    }

    /**
     * 填充一体化里约网关请求头
     */
    private void fillRioHeaders(Map<String, String> headers) throws Exception {
        long now = System.currentTimeMillis();
        String timestamp = Long.toString(now / 1000);
        String nonce = Long.toHexString(now) + "-" + Long.toHexString((long) Math.floor(Math.random() * 0xFFFFFF));
        String signature = Signature.toSHA256(timestamp + paasToken + nonce + timestamp);

        headers.put("x-tif-paasid", paasId);
        headers.put("x-tif-timestamp", timestamp);
        headers.put("x-tif-signature", signature);
        headers.put("x-tif-nonce", nonce);
    }
}
