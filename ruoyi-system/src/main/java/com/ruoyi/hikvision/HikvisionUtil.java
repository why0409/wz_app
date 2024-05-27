package com.ruoyi.hikvision;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.*;

/**
 * 海康视频接口
 *
 * @author zxr
 * @date 2021-05-15
 */
@Configuration
public class HikvisionUtil {
    private static final Logger log = LoggerFactory.getLogger(HikvisionUtil.class);
    private static String PROPERTY_NAME = "application-prod.yml";
    public static Object getCommonYml(Object key) {
        Resource resource = new ClassPathResource(PROPERTY_NAME);
        Properties properties = null;
        try {
            YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
            yamlFactory.setResources(resource);
            properties = yamlFactory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return properties.get(key);
    }

    static {
        ArtemisConfig.host = getCommonYml("hikvision.hikvisionHost").toString();
        ArtemisConfig.appKey = getCommonYml("hikvision.hikvisionAppKey").toString();
        ArtemisConfig.appSecret = getCommonYml("hikvision.hikvisionAppSecret").toString();
    }

    /**
     * 能力开放平台的网站路径
     * TODO 路径不用修改，就是/artemis
     */
    private static final String ARTEMIS_PATH = "/artemis";

    private static final Long expireTime = 2592000L;


    /**
     * 通用海康接口
     * 调用POST请求类型(application/json)接口*
     *
     * @return
     */
    public static String publicHkInterface(JSONObject jsonBody, String url) {
        final String getCamsApi = ARTEMIS_PATH + url;
        log.info("===请求路径==="+ArtemisConfig.host+getCamsApi);
        Map<String, String> path = new HashMap<String, String>(2);
        path.put("https://", getCamsApi);
        // post请求application/json类型参数
        String result = ArtemisHttpUtil.doPostStringArtemis(path, jsonBody.toJSONString(), null, null, "application/json", null);
          return result;
    }

    /**
     * 获取监控点预览取流URL
     *
     * @param id 设备编号
     * @return
     */
    public static Map<String, Object> camerasPreviewURLs(String id, String type) {

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("cameraIndexCode", id);
        jsonBody.put("protocol", type);
        if (type == "rtsp") {
            jsonBody.put("expand", "streamform=rtp");
        }
        String returnStr = publicHkInterface(jsonBody, "/api/video/v2/cameras/previewURLs");
        HashMap hashMap = JSON.parseObject(returnStr, HashMap.class);
//        HashMap hashMap = JSON.parseObject("{\"code\":\"0\",\"msg\":\"success\",\"data\":{\"url\":\"http://10.2.0.23:83/openUrl/ZgWw6LC/live.m3u8\"}}", HashMap.class);
        return hashMap;
    }

    public static Map<String, Object> getAllShiPingInfo(int pageNum, int pageSize) {
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("pageNo", pageNum);
        jsonBody.put("pageSize", pageSize);
        //目前不知道用哪一个
        // String returnStr=publicHkInterface(jsonBody,"/api/resource/v1/cameras"); 这个是获取监控列表
        jsonBody.put("resourceType", "camera");
        String returnStr1 = publicHkInterface(jsonBody, "/api/resource/v1/cameras"); //这个是资源列表根据设备类型来查询 这个返回的数据可以解析成树形
        log.info("海康接口返回区域列表====="+returnStr1);
        JSONObject jsonBody1 = new JSONObject();
        jsonBody1.put("pageNo", pageNum);
        jsonBody1.put("pageSize", pageSize);
        String returnStr2 = publicHkInterface(jsonBody1, "/api/resource/v1/regions");
        log.info("海康接口返回视频列表====="+returnStr2);
        Map<String, Object> map = new HashMap<>();
        JSONArray jsonObject1 = (JSONArray) JSONObject.parseObject(returnStr1).getJSONObject("data").getJSONArray("list");

        JSONArray jsonObject2 = (JSONArray) JSONObject.parseObject(returnStr2).getJSONObject("data").getJSONArray("list");

        map.put("videolist", jsonObject1);
        map.put("regionslist", jsonObject2);
        return map;
    }

    //获取视频列表包含状态
    @Deprecated
    public static Map<String,Object> getCameras(int pageNum, int pageSize){
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("pageNo", pageNum);
        jsonBody.put("pageSize", pageSize);
        String returnStr=publicHkInterface(jsonBody,"/api/resource/v1/cameras");
        HashMap hashMap = JSON.parseObject(returnStr, HashMap.class);
        return hashMap;
    }


    /**
     * 查询编码设备列表v2
     * @param pageNum
     * @param pageSize
     * @return
     */
    public static Map<String, Object> getVideoInfosearch(int pageNum, int pageSize) {
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("pageNo", pageNum);
        jsonBody.put("pageSize", pageSize);
        String returnStr = publicHkInterface(jsonBody, "/api/resource/v2/encodeDevice/search");
        HashMap hashMap = JSON.parseObject(returnStr, HashMap.class);
        return hashMap;
    }

    /**
     * 获取监控点预览取流URL
     *
     * @param indexCode 设备编号
     * @return
     */
    public static Map<String, Object> camerasPreviewURLsFront(String indexCode, String type) {
        String returnStr = "";
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("cameraIndexCode", indexCode);
            jsonBody.put("protocol", type);
            jsonBody.put("expireTime", expireTime);
//            jsonBody.put("expand", "transcode=1&streamform=rtp");
            jsonBody.put("expand", "transcode=1");
            returnStr = publicHkInterface(jsonBody, "/api/video/v1/cameras/previewURLs");

            log.info("获取视频流请求indexCode"+indexCode);
            log.info("获取视频流返回信息"+returnStr);
            if (returnStr == null) {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap hashMap = JSON.parseObject(returnStr, HashMap.class);
        return hashMap;
    }


}
