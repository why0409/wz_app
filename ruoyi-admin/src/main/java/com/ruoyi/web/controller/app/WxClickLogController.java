package com.ruoyi.web.controller.app;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.app.domain.WxClickLog;
import com.ruoyi.app.service.IServiceConfigService;
import com.ruoyi.app.service.IWxClickLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 小程序使用日志Controller
 *
 * @author ruoyi
 * @date 2024-04-28
 */
@RestController
@RequestMapping("/wxClick/log")
public class WxClickLogController extends BaseController
{
    @Autowired
    private IWxClickLogService wxClickLogService;

    @Autowired
    private IServiceConfigService serviceConfigService;

    /**
     * 查询小程序使用日志列表
     */
    //@PreAuthorize("@ss.hasPermi('wxClick:log:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxClickLog wxClickLog)
    {
        startPage();
        List<WxClickLog> list = wxClickLogService.selectWxClickLogList(wxClickLog);
        return getDataTable(list);
    }

    /**
     * 导出小程序使用日志列表
     */
    //@PreAuthorize("@ss.hasPermi('wxClick:log:export')")
    @Log(title = "小程序使用日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxClickLog wxClickLog)
    {
        List<WxClickLog> list = wxClickLogService.selectWxClickLogList(wxClickLog);
        ExcelUtil<WxClickLog> util = new ExcelUtil<WxClickLog>(WxClickLog.class);
        util.exportExcel(response, list, "小程序使用日志数据");
    }

    /**
     * 获取小程序使用日志详细信息
     */
    //@PreAuthorize("@ss.hasPermi('wxClick:log:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(wxClickLogService.selectWxClickLogById(id));
    }

    /**
     * 新增小程序使用日志
     */
    //@PreAuthorize("@ss.hasPermi('wxClick:log:add')")
    @Log(title = "小程序使用日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxClickLog wxClickLog)
    {
        return toAjax(wxClickLogService.insertWxClickLog(wxClickLog));
    }

    /**
     * 修改小程序使用日志
     */
    //@PreAuthorize("@ss.hasPermi('wxClick:log:edit')")
    @Log(title = "小程序使用日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxClickLog wxClickLog)
    {
        return toAjax(wxClickLogService.updateWxClickLog(wxClickLog));
    }

    /**
     * 删除小程序使用日志
     */
    //@PreAuthorize("@ss.hasPermi('wxClick:log:remove')")
    @Log(title = "小程序使用日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wxClickLogService.deleteWxClickLogByIds(ids));
    }

    @GetMapping("/staticsCount")
    public AjaxResult staticsCount(){
        return success(wxClickLogService.staticsCount());
    }

    @GetMapping("/trendCountByDay")
    public AjaxResult trendCountByDay(String time,String serviceId){
        return success(wxClickLogService.trendCountByDay(time, serviceId));
    }

    @GetMapping("/trendCountByMonth")
    public AjaxResult trendCountByMonth(String time,String serviceId){
        return success(wxClickLogService.trendCountByMonth(time, serviceId));
    }

    @GetMapping("/trendCountByYear")
    public AjaxResult trendCountByYear(String time,String serviceId){
        return success(wxClickLogService.trendCountByYear(time, serviceId));
    }

    @GetMapping("/getFrequenceListByOpenid")
    public AjaxResult getFrequenceListByOpenid(String openid){
        return success(serviceConfigService.getFrequenceListByOpenid(openid));
    }
}

