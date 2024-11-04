package com.ruoyi.web.controller.wx;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.electricity.domain.YdEnterpriseData;
import com.ruoyi.electricity.domain.YdEnterpriseInfo;
import com.ruoyi.electricity.service.IYdEnterpriseDataService;
import com.ruoyi.electricity.service.IYdEnterpriseInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/applet/electricity")
@Api(tags = "小程序/用电企业数据")
public class ElectricityController extends BaseController {

    @Autowired
    private IYdEnterpriseInfoService ydEnterpriseInfoService;

    @Autowired
    private IYdEnterpriseDataService ydEnterpriseDataService;

    @GetMapping("/getYdEnterpriseInfo")
    @ApiOperation("获取列表")
    public AjaxResult getYdEnterpriseInfo() {
        List<YdEnterpriseInfo> list = ydEnterpriseInfoService.selectYdEnterpriseInfoList(null);
        return success(list);
    }

    @GetMapping("/getYdDetail/{id}")
    @ApiOperation("根据id获取详情")
    public AjaxResult getYdDetail(@PathVariable(value = "id") Long id) {
        YdEnterpriseInfo ydEnterpriseInfo = ydEnterpriseInfoService.selectYdEnterpriseInfoById(id);
        return success(ydEnterpriseInfo);
    }

    @GetMapping("/list")
    @ApiOperation("分页查询用电企业数据列表")
    public TableDataInfo list(YdEnterpriseData ydEnterpriseData) {
        startPage();
        List<YdEnterpriseData> list = ydEnterpriseDataService.selectYdEnterpriseDataList(ydEnterpriseData);
        return getDataTable(list);
    }

    @GetMapping("/getEleCount")
    @ApiOperation("获取电表趋势图")
    public AjaxResult getEleCount(String meterNumber, String flag) {
        List<JSONObject> list = ydEnterpriseDataService.getEleCount(meterNumber, flag);
        return success(list);
    }
}
