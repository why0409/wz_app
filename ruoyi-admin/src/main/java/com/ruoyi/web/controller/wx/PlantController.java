package com.ruoyi.web.controller.wx;

import com.alibaba.fastjson2.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.app.domain.PlantPermissions;
import com.ruoyi.app.domain.ReportPlant;
import com.ruoyi.app.domain.vo.QueryPlantVo;
import com.ruoyi.app.service.IPlantPermissionsService;
import com.ruoyi.app.service.IPlantService;
import com.ruoyi.app.service.IReportPlantService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.jsjDb.domain.WxUser;
import com.ruoyi.jsjDb.service.IWxUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/applet/plant")
@RestController
@Api(tags = "厂房")
public class PlantController {

    @Autowired
    private IPlantService plantService;
    @Autowired
    private IReportPlantService reportPlantService;


    @ApiOperation("获取厂房列表")
    @GetMapping("/getPlantList")
    public JSONArray getPlantList(QueryPlantVo plant){
        return plantService.getPlantList(plant);
    }

    /**
     * 上报
     * @param reportPlant
     * @return
     */
    @ApiOperation("上报")
    @PostMapping("/reportPlant")
    public AjaxResult reportPlant(@RequestBody ReportPlant reportPlant){
        return AjaxResult.success(reportPlantService.insertReportPlant(reportPlant));
    }

    @ApiOperation("获取上报列表")
    @GetMapping("/getReportPlantList")
    public AjaxResult getReportPlantList(String plantId,String phone){
        return reportPlantService.getReportPlantList(plantId,phone);
    }



}
