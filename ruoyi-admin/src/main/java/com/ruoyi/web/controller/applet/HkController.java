package com.ruoyi.web.controller.applet;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.system.domain.vo.HkCameraVo;
import com.ruoyi.system.mapper.HkCameraMapper;
import com.ruoyi.web.controller.applet.utils.HkHttpUtils;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * 小程序----海康相关接口
 */
@RestController
@RequestMapping("/applet")
public class HkController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(HkController.class);

    public static final Integer MAX_PAGE_NUM = 100;

    @Autowired
    private HkHttpUtils hkHttpUtils;

    @Resource
    private HkCameraMapper hkCameraMapper;


    @ApiOperation("根据区域编号获取下一级区域列表")
    @PostMapping("/subRegions")
    public JSONObject subRegionsList(String parentIndexCode,String treeCode) {
        String servletPath = "/api/resource/v1/regions/subRegions";
        Map<String, Object> hkParamVo = new HashMap<>();
        hkParamVo.put("parentIndexCode", parentIndexCode);
        if (!"".equals(treeCode)) {
            hkParamVo.put("treeCode", treeCode);
        }

        String result = hkHttpUtils.getHkUrlResult(servletPath, JSONObject.toJSONString(hkParamVo));
        return JSON.parseObject(result);
    }


    @ApiOperation("根据区域编号获取下级监控点列表")
    @PostMapping("/camerasListByRegion")
    public JSONObject camerasListByRegion(int pageNo, int pageSize, String regionIndexCode, String treeCode) {
        String servletPath = "/api/resource/v1/regions/regionIndexCode/cameras";
        Map<String, Object> hkParamVo = new HashMap<>();
        hkParamVo.put("pageNo", pageNo);
        hkParamVo.put("pageSize", pageSize);
        hkParamVo.put("regionIndexCode", regionIndexCode);
        if (!"".equals(treeCode)) {
            hkParamVo.put("treeCode", treeCode);
        }

        String result = hkHttpUtils.getHkUrlResult(servletPath, JSONObject.toJSONString(hkParamVo));
        return JSON.parseObject(result);
    }

    @ApiOperation("获取监控点预览取流URL")
    @PostMapping("/previewURLsByCode")
    public JSONObject previewURLsByCode(String cameraIndexCode, String streamType,
                                        String protocol, String transmode, String expand) {
        String servletPath = "/api/video/v1/cameras/previewURLs";
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("cameraIndexCode", cameraIndexCode);
        if (!"".equals(streamType)) {
            jsonBody.put("streamType", Integer.valueOf(streamType));
        }
        if (!"".equals(protocol)) {
            jsonBody.put("protocol", protocol);
        }
        if (!"".equals(transmode)) {
            jsonBody.put("transmode", Integer.valueOf(transmode));
        }
        if (!"".equals(expand)) {
            jsonBody.put("expand", expand);
        }

        String result = hkHttpUtils.getHkUrlResult(servletPath,JSONObject.toJSONString(jsonBody));
        return JSON.parseObject(result);
    }

    @ApiOperation("获取监控点预览取流URL（v2版本）")
    @PostMapping("/previewURLsByCodeByV2")
    public JSONObject previewURLsByCodeByV2(String cameraIndexCode, String streamType,
                                        String protocol, String transmode, String expand) {
        String servletPath = "/api/video/v1/cameras/previewURLs";
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("cameraIndexCode", cameraIndexCode);
        if (!"".equals(streamType)) {
            jsonBody.put("streamType", Integer.valueOf(streamType));
        }
        if (!"".equals(protocol)) {
            jsonBody.put("protocol", protocol);
        }
        if (!"".equals(transmode)) {
            jsonBody.put("transmode", Integer.valueOf(transmode));
        }
        if (!"".equals(expand)) {
            jsonBody.put("expand", expand);
        }

        String result = hkHttpUtils.getHkUrlResultByV2(servletPath,JSONObject.toJSONString(jsonBody));
        return JSON.parseObject(result);
    }


    /**
     * 获取 所有监控点数、在线视频监控数、在线视频百分比
     * @return
     */
    @GetMapping(value = "/queryAll")
    @ApiOperation(value = "获取监控总数、在线监控数、在线百分比")
    public Map<String,Object> queryAll() {
        return hkCameraMapper.staticsOnline("");
    }

    @GetMapping(value = "/saveAll")
    @ApiOperation(value = "保存所有视频信息")
    @Scheduled(cron = "0 0 0 * * ?")
    public void saveAll() {
        //清空表数据
        hkCameraMapper.deleteAll();

        for (int i = 1; i < MAX_PAGE_NUM; i ++) {
            Map<String,Object> paraMap = new HashMap<>();
            paraMap.put("pageNo",i);
            paraMap.put("pageSize",1000);
            paraMap.put("treeCode","0");
            String tmpResult = hkHttpUtils.getHkUrlResult("/api/resource/v1/cameras",JSONObject.toJSONString(paraMap));
            JSONArray tmpArray = JSON.parseObject(tmpResult).getJSONObject("data").getJSONArray("list");
            if (tmpArray.size() > 0) {
                for (int k = 0; k < tmpArray.size(); k ++) {
                    HkCameraVo hkCameraVo = new HkCameraVo();
                    hkCameraVo.setCameraIndexCode(tmpArray.getJSONObject(k).getString("cameraIndexCode"));
                    hkCameraVo.setName(tmpArray.getJSONObject(k).getString("name"));
                    hkCameraVo.setUnitIndexCode(tmpArray.getJSONObject(k).getString("unitIndexCode"));
                    hkCameraVo.setAltitude(tmpArray.getJSONObject(k).getString("alitude"));
                    hkCameraVo.setLatitude(tmpArray.getJSONObject(k).getString("latitude"));
                    hkCameraVo.setLongitude(tmpArray.getJSONObject(k).getString("longitude"));
                    hkCameraVo.setStatus(tmpArray.getJSONObject(k).getString("status"));

                    hkCameraMapper.insert(hkCameraVo);
//                    //判断更新还是插入
//                    if (hkCameraMapper.staticNumByCode(hkCameraVo.getCameraIndexCode()) == 0){
//                        hkCameraMapper.insert(hkCameraVo);
//                    }else {
//                        hkCameraMapper.updateHkCamera(hkCameraVo);
//                    }
                }
            }else {
                // 退出循环
                break;
            }
            log.info("更新海康监控列表:第"+i+"页");
        }
    }

}
