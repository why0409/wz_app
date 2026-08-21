package com.ruoyi.web.controller.wx.common.uccp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * UCCP HTTP请求工具类
 */
public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private static final int TIMEOUT_IN_MILLIONS_TEN = 10000;

    public static String doPost(String url, Map<String, Object> parameterData, Map<String, String> headers) throws Exception {
        // 组装参数
        StringBuilder sb = new StringBuilder();
        if (parameterData != null) {
            for (Map.Entry<String, Object> entry : parameterData.entrySet()) {
                sb.append("&").append(entry.getKey()).append("=").append(entry.getValue() != null ? entry.getValue().toString() : "");
            }
        }
        String param = "";
        if (sb.length() != 0) {
            param = sb.substring(1);
        }

        // 请求开始
        URL localURL = new URL(url);
        URLConnection connection = localURL.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(param.getBytes(StandardCharsets.UTF_8).length));
        httpURLConnection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        httpURLConnection.setConnectTimeout(TIMEOUT_IN_MILLIONS_TEN);
        httpURLConnection.setReadTimeout(TIMEOUT_IN_MILLIONS_TEN);
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }

        try (OutputStream outputStream = httpURLConnection.getOutputStream();
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {

            outputStreamWriter.write(param);
            outputStreamWriter.flush();

            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode >= 300) {
                logger.error("访问 {} 失败，http返回码 {} 参数 {}", url, responseCode, parameterData);
                throw new Exception("HTTP Request is not success, Response code is " + responseCode);
            }

            try (InputStream inputStream = httpURLConnection.getInputStream();
                 InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                 BufferedReader reader = new BufferedReader(inputStreamReader)) {

                StringBuilder resultBuffer = new StringBuilder();
                String tempLine;
                while ((tempLine = reader.readLine()) != null) {
                    resultBuffer.append(tempLine);
                }
                return resultBuffer.toString();
            }
        }
    }

    /**
     * 向指定URL发送GET方式的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数
     * @return URL 代表远程资源的响应
     */
    public static String sendGet(String url, String param) {
        StringBuilder result = new StringBuilder();
        String urlName = url;
        if (param != null && param.length() != 0) {
            urlName = url + "?" + param;
        }
        try {
            URL realUrl = new URL(urlName);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setConnectTimeout(TIMEOUT_IN_MILLIONS_TEN);
            conn.setReadTimeout(TIMEOUT_IN_MILLIONS_TEN);
            conn.connect();

            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = in.readLine()) != null) {
                    result.append(line);
                }
            }
        } catch (Exception e) {
            logger.error("发送GET请求出现异常", e);
        }
        return result.toString();
    }
}
