package com.ruoyi.web.controller.wz.maintenance;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.WxConvenienceService;
import com.ruoyi.system.mapper.WxConvenienceServiceMapper;
import com.ruoyi.system.service.IWxConvenienceServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 便民服务Controller
 *
 * @author ruoyi
 * @date 2023-04-10
 */
@RestController
@RequestMapping("/system/service")
public class WxConvenienceServiceController extends BaseController
{
    @Autowired
    private IWxConvenienceServiceService wxConvenienceServiceService;

    @Resource
    private WxConvenienceServiceMapper wxConvenienceServiceMapper;

    /**
     * 查询便民服务列表
     */
    //@PreAuthorize("@ss.hasPermi('system:service:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxConvenienceService wxConvenienceService)
    {
        startPage();
        List<WxConvenienceService> list = wxConvenienceServiceService.selectWxConvenienceServiceList(wxConvenienceService);
        return getDataTable(list);
    }

    /**
     * 导出便民服务列表
     */
    //@PreAuthorize("@ss.hasPermi('system:service:export')")
    @Log(title = "便民服务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxConvenienceService wxConvenienceService)
    {
        List<WxConvenienceService> list = wxConvenienceServiceService.selectWxConvenienceServiceList(wxConvenienceService);
        ExcelUtil<WxConvenienceService> util = new ExcelUtil<WxConvenienceService>(WxConvenienceService.class);
        util.exportExcel(response, list, "便民服务数据");
    }

    /**
     * 获取便民服务详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:service:query')")
    @GetMapping(value = "/{uuid}")
    public AjaxResult getInfo(@PathVariable("uuid") String uuid)
    {
        return success(wxConvenienceServiceService.selectWxConvenienceServiceByUuid(uuid));
    }

    /**
     * 新增便民服务
     */
    //@PreAuthorize("@ss.hasPermi('system:service:add')")
    @Log(title = "便民服务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxConvenienceService wxConvenienceService)
    {
        UUID uuid = UUID.randomUUID();
        wxConvenienceService.setUuid(uuid.toString());
        wxConvenienceService.setPublishTime(new Date());
        //int checkSortNum = wxConvenienceServiceMapper.checkSortNum(wxConvenienceService.getSortNum());
        //return checkSortNum == 0 ? toAjax(wxConvenienceServiceService.insertWxConvenienceService(wxConvenienceService)) : AjaxResult.error("已存在重复的序号，请修改！");

        return toAjax(wxConvenienceServiceService.insertWxConvenienceService(wxConvenienceService));
    }

    /**
     * 修改便民服务
     */
    //@PreAuthorize("@ss.hasPermi('system:service:edit')")
    @Log(title = "便民服务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxConvenienceService wxConvenienceService)
    {
        wxConvenienceService.setUpdateTime(new Date());
        //WxConvenienceService w = wxConvenienceServiceMapper.selectWxConvenienceServiceByUuid(wxConvenienceService.getUuid());
        //int checkSortNum = wxConvenienceServiceMapper.checkSortNum(wxConvenienceService.getSortNum());
        //if (w.getSortNum().equals(wxConvenienceService.getSortNum())){
        //    return toAjax(wxConvenienceServiceService.updateWxConvenienceService(wxConvenienceService));
        //}else {
        //    return (checkSortNum == 0) ? toAjax(wxConvenienceServiceService.updateWxConvenienceService(wxConvenienceService)) : AjaxResult.error("已存在重复的序号，请修改！");
        //}

        return toAjax(wxConvenienceServiceService.updateWxConvenienceService(wxConvenienceService));
    }

    /**
     * 删除便民服务
     */
    //@PreAuthorize("@ss.hasPermi('system:service:remove')")
    @Log(title = "便民服务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{uuids}")
    public AjaxResult remove(@PathVariable String[] uuids)
    {
        return toAjax(wxConvenienceServiceService.deleteWxConvenienceServiceByUuids(uuids));
    }
}

