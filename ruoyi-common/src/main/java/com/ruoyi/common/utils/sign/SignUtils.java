package com.ruoyi.common.utils.sign;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class SignUtils {

    /**
     * 将参数按照ASCII码排序，并用secret生成签名
     *
     * @param params    参数集合map,不含密钥secret
     * @param secret    分配的密钥secret
     * @return sign     签名
     */
    public static String getSign(Map<String, String> params, String secret) {
        // 先对请求参数去重并排序
        Set<String> keySet = params.keySet();
        TreeSet<String> sortSet = new TreeSet<>(keySet);
        StringBuilder sb = new StringBuilder();
        for (String key : sortSet) {
            String value = params.get(key);
            sb.append(key).append("=").append(value).append("&");
        }
        sb.append("secret=").append(secret);
        System.out.println("签名："+sb.toString());
        return DigestUtil.md5Hex(sb.toString());
    }

    public static String getSignObj(Map<String, Object> params, String secret) {
        // 先对请求参数去重并排序
        Set<String> keySet = params.keySet();
        TreeSet<String> sortSet = new TreeSet<>(keySet);
        StringBuilder sb = new StringBuilder();
        for (String key : sortSet) {
            Object value = params.get(key);
            sb.append(key).append("=").append(value).append("&");
        }
        sb.append("secret=").append(secret);
        System.out.println("签名："+sb.toString());
        return DigestUtil.md5Hex(sb.toString());
    }

    // 颁发给你的appId
    private static final String appId = "mini-program";

    // 对参数加密的秘钥
    private static final String secret = "wanzhi-mini";

    public static void main(String[] args) {
        long now = System.currentTimeMillis();
        Map<String, String> signMap = new HashMap<>();
        // 接口调用必传参数  ------ ↓↓↓↓↓↓↓  -----
        signMap.put("appId", appId);
        signMap.put("timestamp", now + "");
        // 接口调用必传参数  ------ ↑↑↑↑↑↑↑  -----

        // 接口调用接口定义的业务参数
        signMap.put("mobile", "15814725852");

        // 生成签名
        String sign = getSign(signMap, secret);
        System.out.println("得到签名sign: " + sign);
        String url = "http://localhost:8001/api/open/permit?appId=" + appId + "&timestamp=" + now +
                "&sign=" + sign + "&mobile=15814725852";
        System.out.println("生成的url: " + url);
        String resp = HttpRequest.get(url).execute().body();
        System.out.println(resp);
    }
}
