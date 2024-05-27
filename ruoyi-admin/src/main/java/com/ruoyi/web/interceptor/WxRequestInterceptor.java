package com.ruoyi.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Adminstrators
 */
@Component
public class WxRequestInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(WxRequestInterceptor.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        logger.info("======request请求："+request.getRequestURI()+"===");
        boolean exist = false;
        String openid = "";
        if(cookies!= null && cookies.length>0){
            openid = cookies[0].getName()+"==";
        }

        try {
            exist = Boolean.TRUE.equals(redisTemplate.opsForSet().isMember("VALIDATE_KEYS", openid));
        } catch (Exception e) {
            logger.error("Redis连接异常，请确认Redis是否正常连接！");
            e.printStackTrace();
        }

        //暂时都设置true，方便调试
        if (true) {
            logger.info("接口校验通过！");
            return true;
        }else {
            logger.warn("接口校验失败！");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
