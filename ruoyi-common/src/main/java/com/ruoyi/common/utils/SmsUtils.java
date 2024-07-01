package com.ruoyi.common.utils;

import com.alibaba.fastjson2.JSON;

import com.ruoyi.common.core.domain.SmsSubmit;
import com.ruoyi.common.utils.sign.Md5Utils;
import org.apache.commons.codec.binary.Base64;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;

/**
 * 发短信工具类
 */
public class SmsUtils {
    public static String sendMsg(String param, String url) throws Exception {
        // 创建URL对象
        URL obj = new URL(url);
        // 创建HttpsURLConnection对象
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        // 忽略证书验证
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }};
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        con.setSSLSocketFactory(sslContext.getSocketFactory());
        con.setHostnameVerifier((hostname, session) -> true);
        // 设置请求方法为POST
        con.setRequestMethod("POST");
        // 设置请求头部信息
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        // 启用输出流
        con.setDoOutput(true);
        // 获取输出流
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        // 写入参数
        wr.writeBytes(param);
        wr.flush();
        wr.close();
        // 获取响应码
        int responseCode = con.getResponseCode();
        // 读取响应内容
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public static String getBase64(String content, String phone, String ecName, String apId, String secretKey, String sign) {
        // 拼接内容
        SmsSubmit smsSubmit = new SmsSubmit();
        smsSubmit.setEc_name(ecName);
        smsSubmit.setAp_id(apId);
        smsSubmit.setSecret_key(secretKey);
        smsSubmit.setMobiles(phone);
        smsSubmit.setContent(content);
        smsSubmit.setSign(sign);
        smsSubmit.setAdd_serial("");

        StringBuffer buffer = new StringBuffer();
        buffer.append(smsSubmit.getEc_name());
        buffer.append(smsSubmit.getAp_id());
        buffer.append(smsSubmit.getSecret_key());
        buffer.append(smsSubmit.getMobiles());
        buffer.append(smsSubmit.getContent());
        buffer.append(smsSubmit.getSign());
        buffer.append(smsSubmit.getAdd_serial());

        String mac = Md5Utils.hash(buffer.toString());
        smsSubmit.setMac(mac);
        String param = JSON.toJSONString(smsSubmit);
        // Base64加密
        return Base64.encodeBase64String(param.getBytes(StandardCharsets.UTF_8));
    }
}
