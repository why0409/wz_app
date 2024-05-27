package com.ruoyi.web.core.config;

import com.ruoyi.web.interceptor.WxRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Adminstrators
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private WxRequestInterceptor wxRequestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(wxRequestInterceptor)
                .addPathPatterns("/applet/**")
                .addPathPatterns("/jsj/**")
                .excludePathPatterns("/jsj/loadPositionJson")
                .excludePathPatterns("/jsj/getBase64ByUrl")
                .excludePathPatterns("/applet/SendHikInfo")
                .excludePathPatterns("/applet/decrypt")
                .excludePathPatterns("/applet/getLatestFive")
                .excludePathPatterns("/applet/getOpenId");
    }
}
