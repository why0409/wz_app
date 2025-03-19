package com.ruoyi.app.service.impl;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.app.domain.vo.QueryPlantVo;
import com.ruoyi.app.mapper.ReportPlantMapper;
import com.ruoyi.app.service.IPlantService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlantServiceImpl implements IPlantService {
    @Value("${interface.url}")
    private String url;
    @Resource
    private ReportPlantMapper plantMapper;
    @Override
    public JSONArray getPlantList(QueryPlantVo plant) {
        Map<String, Object> paramMap = new HashMap<>();
        if (null!=plant.getMaxFloorHeight()){
            paramMap.put("maxFloorHeight",plant.getMaxFloorHeight());
        }
        if (null!=plant.getMinFloorHeight()){
            paramMap.put("minFloorHeight",plant.getMinFloorHeight());
        }
        if (null!=plant.getMinFloorArea()){
            paramMap.put("maxFloorArea",plant.getMaxFloorArea());
        }
        if (null!=plant.getMaxFloorArea()) {
            paramMap.put("minFloorArea", plant.getMinFloorArea());
        }
        if (null!=plant.getAssetName()){
            paramMap.put("assetName", plant.getAssetName());
        }


        //调用接口
        String body = HttpRequest.get(url)
                .form(paramMap)
                .header("APP-KEY", "dHdAWwAk")
                .header("APP-SECRET", "f916732c907090126b2302faf2ab3737")
                .execute().body();
        JSONObject jsonObject = JSON.parseObject(body);
        Integer code = jsonObject.getInteger("code");
        if (code==0){
            return JSON.parseArray(jsonObject.getString("data"));
//            return JSON.parseArray(jsonObject.getString("data"),Plant.class);
        }else {
            return null;
        }
    }

}
