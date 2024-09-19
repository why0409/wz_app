package com.ruoyi.web.controller.wx;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.iflytek.wst.gateway.sdk.constant.SdkConstant;
import com.iflytek.wst.gateway.sdk.model.ApiResponse;
import com.ruoyi.common.utils.RsaUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.web.controller.wx.common.WstRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author tonyJiang
 * @Date 2024 01 15 17 14
 **/
@RestController
@RequestMapping("/applet")
public class SinglePointController {
    private Logger logger = LoggerFactory.getLogger(UserInfoController.class);

//    @PostMapping(value = "getUserInfo")
//    public String getUserInfo(HttpServletRequest request) throws Exception{
//        System.out.println(userInfoUrl);
//        System.out.println(uccpService);
//        System.out.println(appSecret);
//        System.out.println(appCode);
//        HttpSession session = request.getSession();
//        UCService ucservice = UCCasServiceContext.getUcService();
//        String token = ucservice.getCurrentToken(session);
//
////        UCConstant ucConstant = UCCasServiceContext.getUcConstant();
////        String appSercert = ucConstant.getAppSecret();
////        String appCode = ucConstant.getAppCode();
////        String uccpService = ucConstant.getResturl();
//        // 根据用户令牌获取用户信息
//        String url = uccpService + userInfoUrl;
//        logger.info("请求地址：{}，用户令牌token={}", url, token);
//
//        TreeMap<String, String> signParams = new TreeMap<>();
//        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//        signParams.put("time", time);
//        signParams.put("appCode", appCode);
//        signParams.put("token", token);
//
//        // 生成待签名的参数
//        String signData = SignUtil.produceData(signParams);
//        String sign = SignUtil.md5Encode(signData + appSecret);
//        logger.debug("待签名的参数：{}，签名：{}", signData, sign);
//        // 请求体
//        Map<String, Object> paramData = new HashMap<>();
//        paramData.put("token", token);
//        // 请求头
//        Map<String, String> headers  = new HashMap<>();
//        headers.put("time", time);
//        headers.put("appCode", appCode);
//        headers.put("sign", sign);
//        // 请求返回列设置，不填默认为basic,legal
//        headers.put("cm","basic,credent,phone");
//        // doPost请求
//        String result = HttpUtils.doPost(url, paramData, headers);
//        // TODO 本地用户信息缓存
//        logger.info("请求成功，返回结果：{}", result);
//
//        // 加密的字段使用密钥appSercet解密，如：
//        logger.info("解密方法示例：{}", SignUtil.decryptAES("fc8vHKNu7Nt5iCsMO8Oz7g==", appSecret));
//        return result;
//    }
//
//    /**
//     * 获取登录用户名x
//     * @Description
//     * @Author jiemin2
//     * @Date 10:47 2021/4/16
//     * @Param [session]
//     * @return java.lang.String
//     **/
//    @RequestMapping(value = "transition", method = {RequestMethod.POST, RequestMethod.GET})
//    public String transition(HttpSession session) {
//        UCService ucservice = UCCasServiceContext.getUcService();
//        String currentLoginName = ucservice.getCurrentLoginName(session);
//        return currentLoginName;
//    }




    @RequestMapping(value = "/getUserInfo")
    public String getUserInfoByToken(String token) throws IOException {
        //RSA加密后的字符串
        String secretStr = "";
        if (StringUtils.isNotEmpty(token)){
             ApiResponse apiResponse = WstRestClient.getInstance().getUserInfoByToken(token);
             //RSA加密
            String resultStr = getResultString(apiResponse);
            Map<String,Object> resultMap = new HashMap<>();
            if (!resultStr.equals("null")){
                JSONObject object = JSONObject.parseObject(resultStr);
                JSONObject data = object.getJSONObject("data");
                resultMap.put("perUserVo.name",data.get("perUserVo.name"));
                resultMap.put("perUserVo.bindPhone",data.get("perUserVo.bindPhone"));

                try {
                    //公钥加密
                    secretStr = RsaUtils.encryptByPublicKey(JSON.toJSONString(resultMap));
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
            return secretStr;
        }else {
            return new ApiResponse(500,"token为空",null).toString();
        }

    }




    private static String getResultString(ApiResponse response) throws IOException {
//        StringBuilder result = new StringBuilder();
//        result.append("Response from backend server").append(SdkConstant.CLOUDAPI_LF).append(SdkConstant.CLOUDAPI_LF);
//        result.append("ResultCode:").append(SdkConstant.CLOUDAPI_LF).append(response.getCode()).append(SdkConstant.CLOUDAPI_LF).append(SdkConstant.CLOUDAPI_LF);
//        if (response.getCode() != 200) {
//            result.append("Error description:").append(response.getHeaders().get("X-Ca-Error-Message")).append(SdkConstant.CLOUDAPI_LF).append(SdkConstant.CLOUDAPI_LF);
//        }
//        result.append("ResultBody:").append(SdkConstant.CLOUDAPI_LF).append(response.getBody() == null ? "null" : new String(response.getBody(), SdkConstant.CLOUDAPI_ENCODING));
//        System.out.println(result.toString());
        return response.getBody() == null ? "null" : new String(response.getBody(), SdkConstant.CLOUDAPI_ENCODING);
    }
}
