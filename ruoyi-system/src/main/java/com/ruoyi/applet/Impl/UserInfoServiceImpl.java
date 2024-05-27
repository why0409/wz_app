package com.ruoyi.applet.Impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.applet.UserInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Value("${applet.APPID}")
    private String APPID;
    @Value("${applet.SECRET}")
    private String SECRET;

    /**
     * 获取微信小程序sessionID
     * @author:
     * @date: 2022/11/25 8:44
     * @param code
     * @return
     */
    @Override
    public JSONObject getOpenId(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + APPID
                + "&secret=" + SECRET + "&js_code=" + code + "&grant_type=authorization_code";
        //调用get方法发起get请求，并把返回值赋值给returnvalue
        String  returnvalue=GET(url);
        //定义一个json对象。
        JSONObject convertvalue = new JSONObject();

        //将得到的字符串转换为json
        convertvalue=(JSONObject) JSON.parse(returnvalue);
        return convertvalue;
    }

    /**
     *  获取iv和encryptedData并解密获取手机号
     * @author:
     * @date: 2022/11/25 11:59
     * @param encryptedData
     * @param sessionKe
     * @param ivParameter
     * @return
     */
    @Override
    public String decrypt(String encryptedData, String sessionKe, String ivParameter) {
        String res = null;
        String encodingFormat="utf-8";
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] raw = decoder.decodeBuffer(sessionKe);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(decoder.decodeBuffer(ivParameter));
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] myendicod = decoder.decodeBuffer(encryptedData);
            byte[] original = cipher.doFinal(myendicod);
            System.out.println(new String(original, encodingFormat));
            res = new String(original, encodingFormat);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    //发起get请求的方法。
    public String GET(String url) {
        System.out.println("start");//打印发起请求的url
        String result = "";
        BufferedReader in = null;
        InputStream is = null;
        InputStreamReader isr = null;
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.connect();
            Map<String, List<String>> map = conn.getHeaderFields();
            is = conn.getInputStream();
            isr = new InputStreamReader(is);
            in = new BufferedReader(isr);
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            // 异常记录
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (is != null) {
                    is.close();
                }
                if (isr != null) {
                    isr.close();
                }
            } catch (Exception e2) {
                // 异常记录
                e2.printStackTrace();
            }
        }
        return result;
    }
}
