package com.ruoyi.common.core.domain;

import lombok.Data;

@Data
public class SmsSubmit {
    // 企业名称
    private String ec_name;
    // 接口账号用户名
    private String ap_id;
    // 密钥
    private String secret_key;
    // 手机号，英文逗号分割
    private String mobiles;
    // 短信内容
    private String content;
    // 签名编码
    private String sign;
    // 拓展码
    private String add_serial;
    // 参数校验序列，生成方法：将ecName、apId、secretKey、mobiles、content、sign、addSerial按序拼接（无间隔符），通过MD5（32位小写）计算得出值。
    private String mac;
}
