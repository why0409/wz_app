package com.ruoyi.web.aspect;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.app.domain.WxClickLog;
import com.ruoyi.app.service.IWxClickLogService;
import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: LJW
 * @Date: 2024/4/28 0028 14:34
 */
@Aspect
@Component
public class ClickLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(ClickLogAspect.class);

    @Autowired
    private IWxClickLogService wxClickLogService;


    /**
     * 设置切面点
     */
    @Pointcut(value = "@annotation(com.ruoyi.web.annotation.ClickLog)")
    public void clickLogPointCut() {

    }

    @Before("clickLogPointCut()")
    public void doBefore(JoinPoint joinPoint) {

    }

    @AfterReturning(pointcut = "clickLogPointCut()", returning = "result")
    public void doAfterReturning(Object result) {
        // TODO
        if (ObjectUtils.isEmpty(result)) {
            return;
        }

        try {
            //获取openid，存入日志表
            JSONObject o = JSONObject.parseObject(JSONUtil.toJsonStr(result));
            String openId = o.getJSONObject("data").getString("openid");
            WxClickLog w = new WxClickLog();
            w.setOpenId(openId);
            wxClickLogService.insertWxClickLog(w);
            logger.info("=====openid:"+openId+"用户进入小程序=====");
        }catch (Exception e) {
            logger.info("获取openId失败!");
        }
    }
}
