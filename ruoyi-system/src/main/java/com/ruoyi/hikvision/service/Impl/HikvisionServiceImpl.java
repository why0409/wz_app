package com.ruoyi.hikvision.service.Impl;

import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.hikvision.Dto.HKVideoDto;
import com.ruoyi.hikvision.Dto.RegionsDto;
import com.ruoyi.hikvision.HikvisionUtil;
import com.ruoyi.hikvision.service.HikvisionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HikvisionServiceImpl implements HikvisionService {
    private static final Logger log = LoggerFactory.getLogger(HikvisionServiceImpl.class);
    @Autowired
    private HikvisionUtil hikvisionUtil;
    @Autowired
    private RedisCache redisCache;

    @Override
    public Map<String,Object> getAllShiPingInfo(Integer pageNo,Integer pageSize){
        Map<String,Object> map = hikvisionUtil.getAllShiPingInfo(pageNo,pageSize);
//        List<JSONObject> videolist= (List<JSONObject>) map.get("videolist");
//        List<JSONObject> regionslist= (List<JSONObject>) map.get("regionslist");
//        redisCache.setCacheObject(CacheConstants.HIKGROUP_KEY+"videolist",videolist);
//        redisCache.setCacheObject(CacheConstants.HIKGROUP_KEY+"regionslist",regionslist);
        return map;
    }

    /**
     * 定时任务获取海康列表信息放到redis中
     * @author:
     * @date: 2022/11/28 19:15
     */
    public void TimingGetHIKList(Integer pageNo,Integer pageSize){
        Map<String,Object> map = this.getAllShiPingInfo(pageNo,pageSize);
        List<JSONObject> videolist= (List<JSONObject>) map.get("videolist");
        List<JSONObject> regionslist= (List<JSONObject>) map.get("regionslist");
        this.generateCameraUrl(videolist);//获取视频url
        redisCache.setCacheObject(CacheConstants.HIKGROUP_KEY+"videolist",videolist);
        redisCache.setCacheObject(CacheConstants.HIKGROUP_KEY+"regionslist",regionslist);

    }

    /**
     * 从内网接收视频信息
     * @date: 2022/12/9 10:25
     * @param map
     */
    public void receiveFromZD(Map<String, Object> map){
        log.info("====从内网接收视频信息 开始====");
        log.info("====接收的信息为===="+map);
        List<JSONObject> videolist= (List<JSONObject>) map.get("videolist");
        List<JSONObject> regionslist= (List<JSONObject>) map.get("regionslist");

        //将 linkHashMap 转为json
        ObjectMapper mapper = new ObjectMapper();
        List<JSONObject> list = mapper.convertValue(videolist, new TypeReference<List<JSONObject>>() { });

        mapper = new ObjectMapper();
        List<JSONObject> list2 = mapper.convertValue(regionslist, new TypeReference<List<JSONObject>>() { });

        redisCache.setCacheObject(CacheConstants.HIKGROUP_KEY+"videolist",list);
        redisCache.setCacheObject(CacheConstants.HIKGROUP_KEY+"regionslist",list2);
        log.info("========从内网接收视频信息 结束======");
    }
    /**
     * 从前端获取海康列表树
     * @author:
     * @date: 2022/11/28 20:49
     * @return
     */
    public List<RegionsDto> getListFromFront(){
        List<HKVideoDto> hkVideoDtos = new ArrayList<>();
        List<JSONObject> videolist = redisCache.getCacheObject(CacheConstants.HIKGROUP_KEY+"videolist");
        List<JSONObject> regionslist= redisCache.getCacheObject(CacheConstants.HIKGROUP_KEY+"regionslist");
        HKVideoDto hkVideoDto = null;
        for (int i=0;i<videolist.size();i++){
            hkVideoDto = new HKVideoDto();
            hkVideoDto.setCameraIndexCode((String)videolist.get(i).get("cameraIndexCode"));
            hkVideoDto.setName((String)videolist.get(i).get("name"));
            hkVideoDto.setUnitIndexCode((String)videolist.get(i).get("unitIndexCode"));
            hkVideoDto.setUrl((String)videolist.get(i).get("url"));
            hkVideoDtos.add(hkVideoDto);
        }
        Map<String, List<HKVideoDto>> videoMap =
                hkVideoDtos.stream().collect(Collectors.groupingBy(HKVideoDto::getUnitIndexCode));

        List<RegionsDto> regionsDtos = new ArrayList<>();
        RegionsDto regionsDto = null;
        String indexCode = "";
        for (int j=0;j<regionslist.size();j++){
            regionsDto = new RegionsDto();
            indexCode = (String) regionslist.get(j).get("indexCode");
            regionsDto.setIndexCode(indexCode);
            regionsDto.setExternalIndexCode((String) regionslist.get(j).get("externalIndexCode"));
            regionsDto.setName((String) regionslist.get(j).get("name"));
            regionsDto.setRegionPathName((String) regionslist.get(j).get("regionPathName"));
            if(videoMap.containsKey(indexCode)){
                regionsDto.setHkVideoDtoList(videoMap.get(indexCode));
            }
            regionsDtos.add(regionsDto);
        }
        return regionsDtos;
    }
//    @Override
//    public Map<String,Object> camerasPreviewURLsFront(String indexCode){
//        Map<String,Object> map = hikvisionUtil.camerasPreviewURLsFront(indexCode,type);
//        return map;
//    }

    public void getCameraUrl(){
        List<JSONObject> videolist = redisCache.getCacheObject(CacheConstants.HIKGROUP_KEY+"videolist");
        this.generateCameraUrl(videolist);
        redisCache.setCacheObject(CacheConstants.HIKGROUP_KEY+"videolist",videolist);
    }

    /**
     * 公共方法
     * @author:
     * @date: 2022/11/30 10:57
     * @param videolist
     * @return
     */
    public List<JSONObject> generateCameraUrl(List<JSONObject> videolist){
        Map<String,Object> map = null;
        JSONObject obj = null;
        for (JSONObject jsonObject:videolist){
            map = new HashMap<>();
            if(StringUtils.isNotEmpty(jsonObject.getString("cameraIndexCode"))){
                try {
                    map = hikvisionUtil.camerasPreviewURLsFront(jsonObject.getString("cameraIndexCode"),"hls");
                    obj = (JSONObject)map.get("data");
                    jsonObject.put("url",(String)obj.get("url"));
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
        return videolist;
    }
}
