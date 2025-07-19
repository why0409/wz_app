package com.ruoyi.web.controller.wx;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iflytek.fsp.shield.java.sdk.model.ApiResponse;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.utils.AESUtils;
import com.ruoyi.web.controller.wx.common.RequestVo;
import com.ruoyi.web.controller.wx.common.ShieldSyncApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 小程序----第三方厂家接口
 */
@RestController
@RequestMapping("/applet")
public class ThirdPartyController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(ThirdPartyController.class);

    @Value("${smartPark.key}")
    private String key;

    @Value("${smartPark.iv}")
    private String iv;

    @Value("${smartPark.url}")
    private String url;


//    @Value("${uccp.userInfo.url:}")
//    private String userInfoUrl ;
//
//    @Value("${uccp.servic.url:}")
//    private String uccpService;
//    @Value("${uccp.client.appSecret:}")
//    private String appSecret;
//    @Value("${uccp.client.appCode:}")
//    private String appCode;

//    @RequestMapping(value = "/getUserInfo")
//    public JSONObject getUserInfo(String token) throws Exception{
//        // 根据用户令牌获取用户信息
//        //测试环境
//        String url = uccpService + userInfoUrl;
//        //正式
////        String url = "https://sso.ahzwfw.gov.cn/uccp-user/resources/appSystem/login/login-select.html";
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
//        logger.info("result: {}" , result);
//        JSONObject user = new JSONObject();
//        if (null!=result){
//            JSONObject object = JSONObject.parseObject(result);
//            String code = (String) object.get("code");
//            if (code.equals("200")){
//                    JSONObject data = (JSONObject) object.get("data");
//                        user =(JSONObject) data.get("perUserVo");
//                        //手机号
//                        String phone = (String) user.get("bindPhone");
//                        if (!StringUtils.isEmpty(phone)){
//                            user.put("bindPhone", SignUtil.decryptAES(phone, appSecret));
//                        }
//                        //credentNo
//                        String credentNo = (String) user.get("credentNo");
//                        if (!StringUtils.isEmpty(phone)){
//                            user.put("credentNo", SignUtil.decryptAES(credentNo, appSecret));
//                        }
//            }
//        }
//
////        // TODO 本地用户信息缓存
////        logger.info("请求成功，返回结果：{}", result);
////        System.out.println(appSecret);
////        // 加密的字段使用密钥appSercet解密，如：
////        logger.info("解密方法示例：{}", SignUtil.decryptAES(result, appSecret));
//        logger.info("请求完成后的用户名:{}",user.get("name"));
//        return user;
//    }


    /**
     * 城运环保接口-鉴权
     */
    @RequestMapping("/airData")
    public JSONObject airData() {
        String resp = "";
        logger.info("======================  请求接口：城运环保接口-鉴权  ======================");
        try {
            resp = HttpRequest.post("https://ahwzaqi.gbqyun.com/api/open/common/getUserToken/airData")
                    .header("Authentication-RequestKey", "PbN9HuMPVzlLKTysMkQezdImeEkWWjzM")
                    .execute().body();
            logger.info("======================  请求成功："+resp+"  ======================");
        } catch (Exception e) {
            logger.error("======================  请求失败：城运环保接口-鉴权  ======================");
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }

    /**
     * 城运环保接口-获取城市实时数据
     */
    @RequestMapping("/airData/getCityHourDataLives")
    public JSONObject getCityHourDataLives(String token,String areaCode) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("token",token);
        paramMap.put("areaCode",areaCode);
        String resp = "";
        logger.info("======================  请求接口：城运环保接口-获取城市实时数据  ======================");
        try {
            resp = HttpUtil.get("https://ahwzaqi.gbqyun.com/api/open/airData/getCityHourDataLives", paramMap);
            logger.info("======================  请求成功："+resp+"  ======================");
        } catch (Exception e) {
            logger.error("======================  请求失败：城运环保接口-获取城市实时数据  ======================");
            e.printStackTrace();
        }

        return JSON.parseObject(resp);
    }

    /**
     * 城运环保接口-获取城市历史数据
     */
    @RequestMapping("/airData/getCityHourDataHistory")
    public JSONObject getCityHourDataHistory(String token,String areaCode,String startTime,String endTime) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("token",token);
        paramMap.put("areaCode",areaCode);
        paramMap.put("startTime",startTime);
        paramMap.put("endTime",endTime);
        String resp = "";
        logger.info("======================  请求接口：城运环保接口-获取城市历史数据  ======================");
        try {
            resp = HttpUtil.get("https://ahwzaqi.gbqyun.com/api/open/airData/getCityHourDataHistory", paramMap);
            logger.info("======================  请求成功："+resp+"  ======================");
        } catch (Exception e) {
            logger.error("======================  请求失败：城运环保接口-获取城市历史数据  ======================");
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }

    /**
     * 城运环保接口-获取微站实时数据
     */
    @RequestMapping("/airData/getSiteHourDataLives")
    public JSONObject getSiteHourDataLives(String token,String areaCode,String stationType,String dateType) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("token",token);
        paramMap.put("areaCode",areaCode);
        paramMap.put("stationType",stationType);
        paramMap.put("dateType",dateType);
        String resp = "";
        logger.info("======================  请求接口：城运环保接口-获取微站实时数据  ======================");
        try {
            resp = HttpUtil.get("https://ahwzaqi.gbqyun.com/api/open/airData/getSiteHourDataLives", paramMap);
            logger.info("======================  请求成功："+resp+"  ======================");
        } catch (Exception e) {
            logger.error("======================  请求失败：城运环保接口-获取微站实时数据  ======================");
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }

    @GetMapping("/atmosphericSite")
    public JSONObject dataResponse(String time) {
        String body = "{\"params\":[{\"key\":\"hours\",\"value\":\"" + time + "\",\"isRequire\":1}]}";

        ShieldSyncApp syncApp = new ShieldSyncApp();
        ApiResponse apiResponse = syncApp.atmosphericSite(body.getBytes(StandardCharsets.UTF_8),"643f3dfce4b0a1411b5aadc1");
        JSONObject result = new JSONObject();
        try {
            result = JSON.parseObject(new String(apiResponse.getBody(), "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/smartPark/list")
    public JSONObject smartParkList(String gpslng, String gpslat, String lng, String lat){
        return smartParkInfo(gpslng,gpslat,lng,lat);
        //return smartParkInfoNew(gpslng,gpslat,lng,lat);
    }


    private JSONObject smartParkInfoNew(String gpslng, String gpslat, String lng, String lat) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("gpslon", gpslng);
        paramMap.put("gpslat", gpslat);
        paramMap.put("lon", lng);
        paramMap.put("lat", lat);

        String data = HttpRequest.get(url)
                .form(paramMap)
                .timeout(10000)
                .execute().body();

        JSONObject json = JSON.parseObject(data);
        json.remove("message");
        String resultStr = (String) json.get("result");
        String resultDecrypt = AESUtils.decrypt(resultStr, key, iv);
        String replace = resultDecrypt.replace("name","parkName")
                        .replace("lon","lng");
        try {
            ObjectMapper mapper = new ObjectMapper();
            json.put("result", mapper.readTree(replace));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }



    private JSONObject smartParkInfo(String gpslng, String gpslat, String lng, String lat){
        Map<String, Object>  paramMap = new HashMap<>();
        paramMap.put("gpslng", gpslng);
        paramMap.put("gpslat", gpslat);
        paramMap.put("lng", lng);
        paramMap.put("lat", lat);

        String result = HttpRequest.get("https://whwz.yichuangzhihui.com/parkmsapi/outside/nearby/list")
                .form(paramMap)
                .timeout(10000)
                .execute().body();

        if (StringUtils.isEmpty(gpslng) && StringUtils.isEmpty(gpslat)
                && StringUtils.isEmpty(lng) && StringUtils.isEmpty(lng)){
            result = HttpRequest.get("https://whwz.yichuangzhihui.com/parkmsapi/outside/nearby/all")
                    .timeout(10000)
                    .execute().body();
        }

        return JSON.parseObject(result);
    }

    @GetMapping("/stationsList")
    public JSONObject stationsList(){
        String secret = "2kB0QtBCZy0ZSkAW3APv";
        String random = new Random().nextInt(90000000) + 10000000 + "";
        String timestamp = System.currentTimeMillis() + "";

        Map<String, String> headMap = new HashMap<>();
        headMap.put("timestamp", timestamp);
        headMap.put("random", random);
        headMap.put("encryptStr", DigestUtil.md5Hex(random + timestamp + secret));

        String result = HttpRequest.get("http://223.240.82.123:9015/wzq_java/abutment/st-stbprp-b/getStInfo")
                .timeout(10000)
                .addHeaders(headMap)
                .execute().body();

        return JSON.parseObject(result);
    }

    @GetMapping("/getStationInfo")
    public JSONObject getStationInfo(String stcd){
        String secret = "2kB0QtBCZy0ZSkAW3APv";
        String random = new Random().nextInt(90000000) + 10000000 + "";
        String timestamp = System.currentTimeMillis() + "";

        Map<String, String> headMap = new HashMap<>();
        headMap.put("timestamp", timestamp);
        headMap.put("random", random);
        headMap.put("encryptStr", DigestUtil.md5Hex(random + timestamp + secret));

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("stcd", stcd);

        String waterResult = HttpRequest.get("http://223.240.82.123:9015/wzq_java/abutment/st-river-r/getRiverInfo")
                .timeout(10000)
                .addHeaders(headMap)
                .form(paramMap)
                .execute().body();

        String rainResult = HttpRequest.get("http://223.240.82.123:9015/wzq_java/abutment/st-pptn-r/getPptnInfo")
                .timeout(10000)
                .addHeaders(headMap)
                .form(paramMap)
                .execute().body();

        JSONObject result = new JSONObject();
        result.put("water", JSONObject.parseObject(waterResult));
        result.put("rain", JSONObject.parseObject(rainResult));

        return result;
    }

    @GetMapping("/getStYqWaterInfo")
    public JSONObject getStYqWaterInfo(){
        String secret = "2kB0QtBCZy0ZSkAW3APv";

        String random = new Random().nextInt(90000000) + 10000000 + "";
        String timestamp = System.currentTimeMillis() + "";

        Map<String, String> headMap = new HashMap<>();
        headMap.put("timestamp", timestamp);
        headMap.put("random", random);
        headMap.put("encryptStr", DigestUtil.md5Hex(random + timestamp + secret));

        String result = HttpRequest.get("http://223.240.82.123:9015/wzq_java/abutment/st-stbprp-b/getStYqWaterInfo")
                .timeout(10000)
                .addHeaders(headMap)
                .execute().body();

        return JSON.parseObject(result);
    }

    @RequestMapping(value = "/sellingHouse/**", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONObject sellingHouse(HttpServletRequest request, @RequestBody RequestVo requestVo) throws URISyntaxException {

        requestVo.setTargetAddr("http://59.203.159.50:8003");

        URI uri = new URI(request.getRequestURI());
        String path = uri.getPath();
        String target = requestVo.getTargetAddr() + path.replace("/applet/sellingHouse", "");

        // 执行代理查询
        String methodName = request.getMethod();
        JSONObject result = new JSONObject();
        try {
            log.info("（body参数）接口转发请求路径：" + target);
            String resp = HttpRequest.patch(target)
                    .method(Method.valueOf(methodName))
                    .body(new JSONObject(requestVo.getParamMap()).toJSONString())
                    .execute().body();
            log.info("（body参数）接口转发返回数据：" + JSON.parseObject(resp));
            result = JSON.parseObject(resp);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "接口请求失败");
        }
        return result;
    }


    /**
     * 接口转发（表单传参）
     */
    @RequestMapping(value = "/proxy/**", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONObject proxy(HttpServletRequest request, @RequestBody RequestVo requestVo) throws URISyntaxException {

        URI uri = new URI(request.getRequestURI());
        String path = uri.getPath();
        String target = requestVo.getTargetAddr() + path.replace("/applet/proxy", "");

        // 执行代理查询
        String methodName = request.getMethod();
        JSONObject result = new JSONObject();
        try {
            log.info("（form参数）接口转发请求路径：" + target);
            String resp = HttpRequest.patch(target)
                    .method(Method.valueOf(methodName))
                    .form(requestVo.getParamMap())
                    .execute().body();
            log.info("（form参数）接口转发返回数据：" + JSON.parseObject(resp));
            result = JSON.parseObject(resp);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "接口请求失败");
        }
        return result;
    }

    /**
     * 接口转发（body传参）
     */
    @RequestMapping(value = "/proxyBody/**", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONObject proxyBody(HttpServletRequest request, @RequestBody RequestVo requestVo) throws URISyntaxException {

        URI uri = new URI(request.getRequestURI());
        String path = uri.getPath();
        String target = requestVo.getTargetAddr() + path.replace("/applet/proxyBody", "");

        // 执行代理查询
        String methodName = request.getMethod();
        JSONObject result = new JSONObject();
        try {
            log.info("（body参数）接口转发请求路径：" + target);
            String resp = HttpRequest.patch(target)
                    .method(Method.valueOf(methodName))
                    .body(new JSONObject(requestVo.getParamMap()).toJSONString())
                    .execute().body();
            log.info("（body参数）接口转发返回数据：" + JSON.parseObject(resp));
            result = JSON.parseObject(resp);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "接口请求失败");
        }
        return result;
    }

    /**
     * 易涝点
     */
    @RequestMapping(value = "/proxy/waterloggingPoint/**", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONObject waterloggingPoint(HttpServletRequest request, @RequestBody RequestVo requestVo) throws URISyntaxException {

        requestVo.setTargetAddr("https://wz.xqxyd.com");

        URI uri = new URI(request.getRequestURI());
        String path = uri.getPath();
        String target = requestVo.getTargetAddr() + path.replace("/applet/proxy/waterloggingPoint", "");

        // 执行代理查询
        String methodName = request.getMethod();
        JSONObject result = new JSONObject();
        try {
            log.info("（form参数）接口转发请求路径：" + target);
            String resp = HttpRequest.patch(target)
                    .method(Method.valueOf(methodName))
                    .form(requestVo.getParamMap())
                    .execute().body();
            log.info("（form参数）接口转发返回数据：" + JSON.parseObject(resp));
            result = JSON.parseObject(resp);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "接口请求失败");
        }
        return result;
    }

    @PostMapping("/proxyApp/**")
    public JSONObject proxyApp(HttpServletRequest request, @RequestBody RequestVo requestVo) throws URISyntaxException {
        URI uri = new URI(request.getRequestURI());
        String path = uri.getPath();
        String target = requestVo.getTargetAddr() + path.replace("/applet/proxyApp", "");

        // 执行代理查询
        String methodName = requestVo.getMethod();
        JSONObject result = new JSONObject();
        try {
            log.info("接口转发请求路径：" + target);
            String resp = "";
            if(methodName.equalsIgnoreCase("GET")) {
                resp = HttpRequest.patch(target)
                        .method(Method.valueOf(methodName))
                        .form(requestVo.getParamMap())
                        .execute().body();
            }else {
                resp = HttpRequest.patch(target)
                        .method(Method.valueOf(methodName))
                        .body(new JSONObject(requestVo.getParamMap()).toJSONString())
                        .execute().body();
            }
            log.info("接口转发返回数据：" + JSON.parseObject(resp));
            result = JSON.parseObject(resp);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "接口请求失败");
        }
        return result;
    }

    @RequestMapping(value = "/airData/**", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONObject airData(HttpServletRequest request, @RequestBody RequestVo requestVo) throws URISyntaxException {
        //获取token
        String token = airData().getString("token");
        Map<String, Object> paramMap = requestVo.getParamMap();
        if (paramMap == null) {
            paramMap = new HashMap<>();
        }
        paramMap.put("token",token);

        requestVo.setTargetAddr("https://ahwzaqi.gbqyun.com");

        URI uri = new URI(request.getRequestURI());
        String path = uri.getPath();
        String target = requestVo.getTargetAddr() + path.replace("/applet/airData", "");

        // 执行代理查询
        String methodName = requestVo.getMethod();
        JSONObject result = new JSONObject();
        try {
            log.info("接口转发请求路径：" + target);
            String resp = "";
            if(methodName.equalsIgnoreCase("GET")) {
                resp = HttpRequest.patch(target)
                        .method(Method.valueOf(methodName))
                        .form(paramMap)
                        .execute().body();
            }else {
                resp = HttpRequest.patch(target)
                        .method(Method.valueOf(methodName))
                        .body(new JSONObject(paramMap).toJSONString())
                        .execute().body();
            }
            log.info("接口转发返回数据：" + JSON.parseObject(resp));
            result = JSON.parseObject(resp);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "接口请求失败");
        }
        return result;

    }

}
