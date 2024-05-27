package com.ruoyi.web.aspect;

import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;

/**
 * @author Adminstrators
 */
@Aspect
@Component
public class LoginInfoAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoginInfoAspect.class);

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 设置切面点
     */
    @Pointcut(value = "@annotation(com.ruoyi.web.annotation.LoginInfo)")
    public void loginInfoPointCut() {

    }

    @Before("loginInfoPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        //获取request对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        StringBuilder sb = new StringBuilder();
        if (request.getMethod().equalsIgnoreCase(RequestMethod.GET.name())) {
            Map<String, String[]> parameterMap = request.getParameterMap();
            Map<String, String> paramMap = new HashMap<>();
            parameterMap.forEach((key, value) -> paramMap.put(key, Arrays.stream(value).collect(joining(","))));
            //
            paramMap.put("login_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            //
            sb.append(JSONUtil.toJsonStr(paramMap));
            logger.info("微信用户登录信息：" + sb);
            try {
                redisTemplate.opsForSet().add("WX_LOGIN_INFO", sb.toString());
            } catch (Exception e) {
                logger.error("Redis连接异常，请确认Redis是否正常连接！");
                e.printStackTrace();
            }
        }
    }

    @AfterReturning(pointcut = "loginInfoPointCut()", returning = "result")
    public void doAfterReturning(Object result) {
        // TODO
        if (ObjectUtils.isEmpty(result)) {
            return;
        }
        Console.log("返回結果:" + JSONUtil.toJsonStr(result));
    }
}
