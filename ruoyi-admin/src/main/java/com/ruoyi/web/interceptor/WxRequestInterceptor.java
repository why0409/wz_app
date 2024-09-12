package com.ruoyi.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @author Adminstrators
 */
@Component
public class WxRequestInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(WxRequestInterceptor.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${wxApp.validate}")
    private boolean appValidate;

    private static final List<String> QUESTIONNAIRE_WHITE_LIST = Arrays.asList("localhost");

    @Override
    public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String clientIp = request.getRemoteAddr();
        Cookie[] cookies = request.getCookies();
        logger.info("======request请求："+request.getRequestURI()+"======");
        //boolean exist = false;
        //String openid = "";
        //if(cookies!= null && cookies.length>0){
        //    openid = cookies[0].getName()+"==";
        //}
        //
        //try {
        //    exist = Boolean.TRUE.equals(redisTemplate.opsForSet().isMember("VALIDATE_KEYS", openid));
        //} catch (Exception e) {
        //    logger.error("Redis连接异常，请确认Redis是否正常连接！");
        //    e.printStackTrace();
        //}
        //
        //if (! appValidate) {
        //    return true;
        //}else {
        //    if (exist) {
        //        logger.info("接口校验通过！");
        //        return true;
        //    }else {
        //        // 问卷调查接口ip白名单
        //        if (isQuestionnaireWhiteList(request.getRequestURI(), clientIp)) {
        //            return true;
        //        }
        //
        //        logger.warn("接口校验失败！");
        //        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        //        return false;
        //    }
        //}

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }


    /**
     * 问卷调查接口ip白名单
     */
    public boolean isQuestionnaireWhiteList(String requestUri, String clientIp) {
        if (requestUri.startsWith("/applet/questionnaire") && QUESTIONNAIRE_WHITE_LIST.contains(clientIp)) {
            return true;
        }
        return false;
    }

}
