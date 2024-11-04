package com.ruoyi.web.controller.wx.common;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SmsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: LJW
 * @Date: 2024/9/4 0004 14:11
 */
@Slf4j
@Component
public class SmsMsgService {

    private static final int CAPTCHA_EXPIRY_TIME_SECONDS = 60;

    @Value("${zhwz.url}")
    private String url;

    @Value("${zhwz.secretKey}")
    private String secretKey;

    @Value("${zhwz.apId}")
    private String apId;

    @Value("${zhwz.sign}")
    private String sign;

    @Value("${zhwz.ecName}")
    private String ecName;

    @Autowired
    private RedisCache redisCache;

    /**
     * 发送短信验证码
     */
    public JSONObject sendNumberCode(String phone) throws Exception {
        // 生成一个六位数的验证码
        int verificationCode = (int) (Math.random() * 900000) + 100000;
        String content = "您的验证码是：" + verificationCode + "。有效期为1分钟，请不要把验证码泄露给其他人。";
        // 将验证码存入redis
        redisCache.setCacheObject("sms_captcha_" + phone, verificationCode, CAPTCHA_EXPIRY_TIME_SECONDS, TimeUnit.SECONDS);
        // 发送短信
        String base64 = SmsUtils.getBase64(content, phone, ecName, apId, secretKey, sign);
        String msg = SmsUtils.sendMsg(base64, url);

        JSONObject object = JSONObject.parse(msg);

        if ("success".equals(object.get("rspcod"))) {
            log.info(DateUtils.getTime() + "短信发送成功，发送用户："+phone);
        } else {
            log.error(DateUtils.getTime() + "短信发送失败");
        }

        return object;
    }

    /**
     * 单发消息
     */
    public JSONObject sendMsgByPerson(String phone, String content) throws Exception {
        // 发送短信
        String base64 = SmsUtils.getBase64(content, phone, ecName, apId, secretKey, sign);
        String msg = SmsUtils.sendMsg(base64, url);

        JSONObject object = JSONObject.parse(msg);

        if ("success".equals(object.get("rspcod"))) {
            log.info(DateUtils.getTime() + "短信发送成功，发送用户："+phone);
        } else {
            log.error(DateUtils.getTime() + "短信发送失败");
        }

        return object;
    }

    /**
     * 群发消息
     */
    public JSONObject sendMsgByGroup(List<String> phonesList, String content) throws Exception {
        // 发送短信
        String base64 = SmsUtils.getBase64(content, String.join(",", phonesList), ecName, apId, secretKey, sign);
        String msg = SmsUtils.sendMsg(base64, url);

        JSONObject object = JSONObject.parse(msg);

        if ("success".equals(object.get("rspcod"))) {
            log.info(DateUtils.getTime() + "短信发送成功，发送用户："+phonesList);
        } else {
            log.error(DateUtils.getTime() + "短信发送失败");
        }

        return object;
    }
}
