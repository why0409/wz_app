package com.ruoyi.web.controller.applet.utils;

import lombok.Data;

import java.util.Map;

/**
 * @Author: LJW
 * @Date: 2023/7/27 0027 15:16
 */
@Data
public class RequestVo {

    private String targetAddr;

    private String method;

    private String appKey;

    private String appSecret;

    private Map<String, Object> paramMap;
}
