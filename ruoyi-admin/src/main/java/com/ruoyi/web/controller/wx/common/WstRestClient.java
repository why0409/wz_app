package com.ruoyi.web.controller.wx.common;

import com.iflytek.wst.gateway.sdk.client.ApacheHttpClient;
import com.iflytek.wst.gateway.sdk.enums.HttpMethod;
import com.iflytek.wst.gateway.sdk.enums.ParamPosition;
import com.iflytek.wst.gateway.sdk.enums.Scheme;
import com.iflytek.wst.gateway.sdk.model.ApiRequest;
import com.iflytek.wst.gateway.sdk.model.ApiResponse;
import com.iflytek.wst.gateway.sdk.model.HttpClientBuilderParams;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

public class WstRestClient extends ApacheHttpClient {
    // 单例模式
    private static WstRestClient instance = new WstRestClient();

    public static WstRestClient getInstance() {
        return instance;
    }

    // 测试环境
//    private String appKey = "8a4ff1954caf4cab82c18f5886e7834e";
//    private String appSecret = "e102667914bb407cb8aa79b82c0b8a0c";
//    private String host = "60.166.52.177:8080";
//    private String serverId = "1";
    //生产环境
    private String appKey = "e2a307503d064215a63b90a440f6f499";
    private String appSecret = "960D6595788173DE66CE0DB8FCA383AE";
    private String host = "www.ahzwfw.gov.cn";
    private String serverId = "1";
    private String contextPath = "/wst-gateway";

    private Scheme scheme = Scheme.HTTP;

    private WstRestClient() {
        // HTTP Client init
        HttpClientBuilderParams httpClientBuilderParams = new HttpClientBuilderParams();
        httpClientBuilderParams.setAppKey(appKey);
        httpClientBuilderParams.setAppSecret(appSecret);
        httpClientBuilderParams.setScheme(scheme);
        httpClientBuilderParams.setHost(host);
        httpClientBuilderParams.setContextPath(contextPath);

        // HTTPS客户端需要单独设置，禁用证书校验
        if (scheme == Scheme.HTTPS) {
            //HTTPS Client init
            /**
             * HTTPS request use DO_NOT_VERIFY mode only for demo
             * Suggest verify for security
             */
            X509TrustManager xtm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    X509Certificate[] x509Certificates = new X509Certificate[0];
                    return x509Certificates;
                }
            };

            SSLContext sslContext = null;
            try {
                sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, new TrustManager[]{xtm}, new SecureRandom());

            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (KeyManagementException e) {
                throw new RuntimeException(e);
            }
            HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            httpClientBuilderParams.setSslSocketFactory(sslContext.getSocketFactory());
            httpClientBuilderParams.setX509TrustManager(xtm);
            httpClientBuilderParams.setHostnameVerifier(DO_NOT_VERIFY);
        }

        super.init(httpClientBuilderParams);
    }


    public ApiResponse getUserInfoByToken(String token) {
        ApiRequest request = new ApiRequest(HttpMethod.GET, "/user/getUserInfoByToken");
        request.addParam("token", token, ParamPosition.QUERY, true);
        request.addParam("serviceId", serverId, ParamPosition.QUERY, true);
        request.addParam("roleCode", "", ParamPosition.QUERY, true);
        return sendSyncRequest(request);
    }

}
