package com.ruoyi.applet;


import com.alibaba.fastjson2.JSONObject;

public interface UserInfoService {

    JSONObject getOpenId(String code);

    String decrypt(String encryptedData,String sessionKe,String ivParameter);
}
