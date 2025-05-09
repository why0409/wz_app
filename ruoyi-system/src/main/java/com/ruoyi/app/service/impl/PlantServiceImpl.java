package com.ruoyi.app.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.app.domain.vo.QueryAssetPlantVo;
import com.ruoyi.app.domain.vo.QueryCommentVo;
import com.ruoyi.app.domain.vo.QueryPlantVo;
import com.ruoyi.app.mapper.ReportPlantMapper;
import com.ruoyi.app.service.IPlantService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlantServiceImpl implements IPlantService {
    @Value("${interface.url}")
    private String url;
    @Value("${interface.assetPlantUrl}")
    private String assetPlantUrl;
    @Value("${interface.createCommentUrl}")
    private String createCommentUrl;

    @Value("${App.appkey}")
    private String appKey;
    @Value("${App.appsecret}")
    private String appSecret;
    @Value("${App.Authorization}")
    private String authorization;

    @Resource
    private ReportPlantMapper plantMapper;

    @Override
    @Deprecated
    public JSONArray getPlantList(QueryPlantVo plant) {
        Map<String, Object> paramMap = new HashMap<>();
        if (null != plant.getMaxFloorHeight()) {
            paramMap.put("maxFloorHeight", plant.getMaxFloorHeight());
        }
        if (null != plant.getMinFloorHeight()) {
            paramMap.put("minFloorHeight", plant.getMinFloorHeight());
        }
        if (null != plant.getMinFloorArea()) {
            paramMap.put("maxFloorArea", plant.getMaxFloorArea());
        }
        if (null != plant.getMaxFloorArea()) {
            paramMap.put("minFloorArea", plant.getMinFloorArea());
        }
        if (null != plant.getAssetName()) {
            paramMap.put("assetName", plant.getAssetName());
        }


        //调用接口
        String body = HttpRequest.get(url)
                .form(paramMap)
//                .header("APP-KEY", "dHdAWwAk")
//                .header("APP-SECRET", "f916732c907090126b2302faf2ab3737")
                .header("APP-KEY", appKey)
                .header("APP-SECRET", appSecret)
                .execute().body();
        JSONObject jsonObject = JSON.parseObject(body);
        Integer code = jsonObject.getInteger("code");
        if (code == 0) {
            return JSON.parseArray(jsonObject.getString("data"));
//            return JSON.parseArray(jsonObject.getString("data"),Plant.class);
        } else {
            return null;
        }
    }

    /**
     * 厂房列表
     *
     * @param queryAssetPlantVo
     * @return
     */
    @Override
    public JSONArray getAssetPlant(QueryAssetPlantVo queryAssetPlantVo) {
        Map<String, Object> paramMap = new HashMap<>();
        if (null != queryAssetPlantVo.getKeyword()) {
            paramMap.put("keyword", queryAssetPlantVo.getKeyword());
        }
        if (null != queryAssetPlantVo.getAreaStart()) {
            paramMap.put("areaStart", queryAssetPlantVo.getAreaStart());
        }
        if (null != queryAssetPlantVo.getAreaEnd()) {
            paramMap.put("areaEnd", queryAssetPlantVo.getAreaEnd());
        }
        if (null != queryAssetPlantVo.getHeightStart()) {
            paramMap.put("heightStart", queryAssetPlantVo.getHeightStart());
        }
        if (null != queryAssetPlantVo.getHeightEnd()) {
            paramMap.put("heightEnd", queryAssetPlantVo.getHeightEnd());
        }
        if (null != queryAssetPlantVo.getFactoryType()) {
            paramMap.put("factoryType", queryAssetPlantVo.getFactoryType());
        }
        //调用接口
        String body = HttpRequest.get(assetPlantUrl)
                .form(paramMap)
                .header("APP-KEY", appKey)
                .header("APP-SECRET", appSecret)
//                .header("Authorization",authorization)
                .execute().body();
        JSONObject jsonObject = JSON.parseObject(body);
        Integer code = jsonObject.getInteger("code");
        if (code == 0) {
            return JSON.parseArray(jsonObject.getString("data"));
        } else {
            return null;
        }
    }

    /**
     * 新建留言
     *
     * @param queryCommentVo
     * @return
     */
    @Override
    public JSONObject createComment(QueryCommentVo queryCommentVo) {
        Map<String, Object> paramMap = new HashMap<>();
        if (null != queryCommentVo.getBusinessId()) {
            paramMap.put("businessId", queryCommentVo.getBusinessId());
        }
        if (null != queryCommentVo.getAssetType()) {
            paramMap.put("assetType", queryCommentVo.getAssetType());
        }
        if (null != queryCommentVo.getComment()) {
            paramMap.put("comment", queryCommentVo.getComment());
        }
        if (null != queryCommentVo.getMember()) {
            paramMap.put("member", queryCommentVo.getMember());
        }
        if (null != queryCommentVo.getPhone()) {
            paramMap.put("phone", queryCommentVo.getPhone());
        }
        if (null != queryCommentVo.getAddress()) {
            paramMap.put("address", queryCommentVo.getAddress());
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        paramMap.put("commentTime", simpleDateFormat.format(DateTime.now()));
        //调用接口
        String body = HttpRequest.post(createCommentUrl)
                .body(JSONObject.toJSONString( paramMap))
                .header("APP-KEY", appKey)
                .header("APP-SECRET", appSecret)
//                .header("Authorization",authorization)
                .execute().body();
        JSONObject jsonObject = JSON.parseObject(body);
        Integer code = jsonObject.getInteger("code");
        if (code == 0) {
            return jsonObject;
        } else {
            return null;
        }
    }

}
