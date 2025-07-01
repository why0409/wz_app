package com.ruoyi.web.controller.wz.maintenance;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.HousePolicies;
import com.ruoyi.system.service.IWxHousePoliciesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/system/house/policies")
@Api(tags = "ruoyi-房源政策简介相关接口")
public class WxHousePoliciesController extends BaseController {
    @Autowired
    private IWxHousePoliciesService housePoliciesService;

    /**
     * 查询湾沚房源政策简介列表
     */
    //@PreAuthorize("@ss.hasPermi('system:weather:list')")
    @GetMapping("/list")
    @ApiOperation("查询湾沚房源政策简介列表")
    public TableDataInfo list(HousePolicies housePolicies) {
        startPage();
        List<HousePolicies> list = housePoliciesService.selectHousePoliciesList(housePolicies);
        return getDataTable(list);
    }

    /**
     * 导出湾沚房源政策简介列表
     */
    //@PreAuthorize("@ss.hasPermi('system:weather:export')")
    @Log(title = "湾沚房源政策简介", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("导出湾沚房源政策简介列表")
    public void export(HttpServletResponse response, HousePolicies housePolicies) {
        List<HousePolicies> list = housePoliciesService.selectHousePoliciesList(housePolicies);
        ExcelUtil<HousePolicies> util = new ExcelUtil<HousePolicies>(HousePolicies.class);
        util.exportExcel(response, list, "湾沚房源政策简介数据");
    }

    /**
     * 获取湾沚房源政策简介详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:weather:query')")
    @GetMapping(value = "/{uuid}")
    @ApiOperation("获取湾沚房源政策简介详细信息")
    public AjaxResult getInfo(@PathVariable("uuid") String uuid) {
        return success(housePoliciesService.selectHousePoliciesByUuid(uuid));
    }

    /**
     * 新增湾沚房源政策简介
     */
    //@PreAuthorize("@ss.hasPermi('system:weather:add')")
    @Log(title = "湾沚房源政策简介", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增湾沚房源政策简介")
    public AjaxResult add(@RequestBody HousePolicies housePolicies) {
        UUID uuid = UUID.randomUUID();
        housePolicies.setUuid(uuid.toString());
        housePolicies.setPublishTime(new Date());
        //int checkSortNum = HousePoliciesMapper.checkSortNum(HousePolicies.getSortNum());
        //return checkSortNum == 0 ? toAjax(HousePoliciesService.insertHousePolicies(HousePolicies)) : AjaxResult.error("已存在重复的序号，请修改！");

        return toAjax(housePoliciesService.insertHousePolicies(housePolicies));
    }

    /**
     * 修改湾沚房源政策简介
     */
    //@PreAuthorize("@ss.hasPermi('system:weather:edit')")
    @Log(title = "湾沚房源政策简介", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改湾沚房源政策简介")
    public AjaxResult edit(@RequestBody HousePolicies housePolicies) {
        housePolicies.setUpdateTime(new Date());
        //HousePolicies w = HousePoliciesMapper.selectHousePoliciesByUuid(HousePolicies.getUuid());
        //int checkSortNum = HousePoliciesMapper.checkSortNum(HousePolicies.getSortNum());
        //if (w.getSortNum().equals(HousePolicies.getSortNum())){
        //    return toAjax(HousePoliciesService.updateHousePolicies(HousePolicies));
        //}else {
        //    return (checkSortNum == 0) ? toAjax(HousePoliciesService.updateHousePolicies(HousePolicies)) : AjaxResult.error("已存在重复的序号，请修改！");
        //}

        return toAjax(housePoliciesService.updateHousePolicies(housePolicies));
    }

    /**
     * 删除湾沚房源政策简介
     */
    //@PreAuthorize("@ss.hasPermi('system:weather:remove')")
    @Log(title = "湾沚房源政策简介", businessType = BusinessType.DELETE)
    @DeleteMapping("/{uuids}")
    @ApiOperation("删除湾沚房源政策简介")
    public AjaxResult remove(@PathVariable String[] uuids) {
        return toAjax(housePoliciesService.deleteHousePoliciesByUuids(uuids));
    }


}
