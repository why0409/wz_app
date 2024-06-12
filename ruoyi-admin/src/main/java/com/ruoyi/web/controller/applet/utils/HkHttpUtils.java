package com.ruoyi.web.controller.applet.utils;

import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Liu.951005
 * @version v1.0
 * @date 2022/4/1 17:03
 * @description
 **/
@Component
@Slf4j
public class HkHttpUtils {

    @Value("${hk.http.host}")
    private String host;

    @Value("${hk.http.wxhost}")
    private String wxhost;

    @Value("${hk.http.appKey}")
    private String appKey;

    @Value("${hk.http.appSecret}")
    private String appSecret;

    @Value("${hk.http.path}")
    private String path;

    @Value("${hk.http.appKeyV2}")
    private String appKeyV2;

    @Value("${hk.http.appSecretV2}")
    private String appSecretV2;

    /**
     * 返回公共开发平台数据
     *
     * @param apiPath
     * @param body
     * @return
     */
    public String getHkUrlResult(String apiPath, String body) {

        /**
         * STEP1：设置平台参数，根据实际情况,设置host appkey appsecret 三个参数.
         */
        ArtemisConfig.host = wxhost; // 平台的ip端口
        ArtemisConfig.appKey = appKey;  // 密钥appkey
        ArtemisConfig.appSecret = appSecret;// 密钥appSecret

        /**
         * STEP2：设置OpenAPI接口的上下文
         */
        final String ARTEMIS_PATH = path;

        /**
         * STEP3：设置接口的URI地址
         */
        final String previewURLsApi = ARTEMIS_PATH + apiPath;
        Map<String, String> path = new HashMap<String, String>(2) {
            {
                put("https://", previewURLsApi);//根据现场环境部署确认是http还是https
            }
        };

        /**
         * STEP4：设置参数提交方式
         */
        String contentType = "application/json";

        /**
         * STEP5：组装请求参数
         */

        /**
         * STEP6：调用接口
         */
        String result = ArtemisHttpUtil.doPostStringArtemis(path, body, null, null, contentType, null);// post请求application/json类型参数
        return result;

    }



    public String getHkUrlResultByV2(String apiPath, String body) {


        /**
         * STEP1：设置平台参数，根据实际情况,设置host appkey appsecret 三个参数.
         */
        ArtemisConfig.host = wxhost; // 平台的ip端口
        ArtemisConfig.appKey = appKeyV2;  // 密钥appkey
        ArtemisConfig.appSecret = appSecretV2;// 密钥appSecret

        /**
         * STEP2：设置OpenAPI接口的上下文
         */
        final String ARTEMIS_PATH = path;

        /**
         * STEP3：设置接口的URI地址
         */
        final String previewURLsApi = ARTEMIS_PATH + apiPath;
        Map<String, String> path = new HashMap<String, String>(2) {
            {
                put("https://", previewURLsApi);//根据现场环境部署确认是http还是https
            }
        };

        /**
         * STEP4：设置参数提交方式
         */
        String contentType = "application/json";

        /**
         * STEP5：组装请求参数
         */

        /**
         * STEP6：调用接口
         */
        String result = ArtemisHttpUtil.doPostStringArtemis(path, body, null, null, contentType, null);// post请求application/json类型参数
        return result;

    }



}
