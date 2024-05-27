package com.ruoyi.web.controller.maintenance;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.WxWzWeather;
import com.ruoyi.system.mapper.WxWzWeatherMapper;
import com.ruoyi.system.service.IWxWzWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 湾沚天气Controller
 * 
 * @author ruoyi
 * @date 2023-02-22
 */
@RestController
@RequestMapping("/system/weather")
public class WxWzWeatherController extends BaseController
{
    @Autowired
    private IWxWzWeatherService wxWzWeatherService;

    @Resource
    private WxWzWeatherMapper wxWzWeatherMapper;

    /**
     * 查询湾沚天气列表
     */
    //@PreAuthorize("@ss.hasPermi('system:weather:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxWzWeather wxWzWeather)
    {
        startPage();
        List<WxWzWeather> list = wxWzWeatherService.selectWxWzWeatherList(wxWzWeather);
        return getDataTable(list);
    }

    /**
     * 导出湾沚天气列表
     */
    //@PreAuthorize("@ss.hasPermi('system:weather:export')")
    @Log(title = "湾沚天气", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxWzWeather wxWzWeather)
    {
        List<WxWzWeather> list = wxWzWeatherService.selectWxWzWeatherList(wxWzWeather);
        ExcelUtil<WxWzWeather> util = new ExcelUtil<WxWzWeather>(WxWzWeather.class);
        util.exportExcel(response, list, "湾沚天气数据");
    }

    /**
     * 获取湾沚天气详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:weather:query')")
    @GetMapping(value = "/{uuid}")
    public AjaxResult getInfo(@PathVariable("uuid") String uuid)
    {
        return success(wxWzWeatherService.selectWxWzWeatherByUuid(uuid));
    }

    /**
     * 新增湾沚天气
     */
    //@PreAuthorize("@ss.hasPermi('system:weather:add')")
    @Log(title = "湾沚天气", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxWzWeather wxWzWeather)
    {
        UUID uuid = UUID.randomUUID();
        wxWzWeather.setUuid(uuid.toString());
        wxWzWeather.setPublishTime(new Date());
        //int checkSortNum = wxWzWeatherMapper.checkSortNum(wxWzWeather.getSortNum());
        //return checkSortNum == 0 ? toAjax(wxWzWeatherService.insertWxWzWeather(wxWzWeather)) : AjaxResult.error("已存在重复的序号，请修改！");

        return toAjax(wxWzWeatherService.insertWxWzWeather(wxWzWeather));
    }

    /**
     * 修改湾沚天气
     */
    //@PreAuthorize("@ss.hasPermi('system:weather:edit')")
    @Log(title = "湾沚天气", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxWzWeather wxWzWeather)
    {
        wxWzWeather.setUpdateTime(new Date());
        //WxWzWeather w = wxWzWeatherMapper.selectWxWzWeatherByUuid(wxWzWeather.getUuid());
        //int checkSortNum = wxWzWeatherMapper.checkSortNum(wxWzWeather.getSortNum());
        //if (w.getSortNum().equals(wxWzWeather.getSortNum())){
        //    return toAjax(wxWzWeatherService.updateWxWzWeather(wxWzWeather));
        //}else {
        //    return (checkSortNum == 0) ? toAjax(wxWzWeatherService.updateWxWzWeather(wxWzWeather)) : AjaxResult.error("已存在重复的序号，请修改！");
        //}

        return toAjax(wxWzWeatherService.updateWxWzWeather(wxWzWeather));
    }

    /**
     * 删除湾沚天气
     */
    //@PreAuthorize("@ss.hasPermi('system:weather:remove')")
    @Log(title = "湾沚天气", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uuids}")
    public AjaxResult remove(@PathVariable String[] uuids)
    {
        return toAjax(wxWzWeatherService.deleteWxWzWeatherByUuids(uuids));
    }
}
