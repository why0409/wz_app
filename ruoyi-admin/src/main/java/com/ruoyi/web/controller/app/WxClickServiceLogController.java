package com.ruoyi.web.controller.app;


import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.app.domain.WxClickServiceLog;
import com.ruoyi.app.service.IWxClickServiceLogService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 小程序各服务使用日志Controller
 *
 * @author ruoyi
 * @date 2024-04-29
 */
@RestController
@RequestMapping("/clickService/log")
public class WxClickServiceLogController extends BaseController
{
    @Autowired
    private IWxClickServiceLogService wxClickServiceLogService;

    /**
     * 查询小程序各服务使用日志列表
     */
    //@PreAuthorize("@ss.hasPermi('clickService:log:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxClickServiceLog wxClickServiceLog)
    {
        startPage();
        List<WxClickServiceLog> list = wxClickServiceLogService.selectWxClickServiceLogList(wxClickServiceLog);
        return getDataTable(list);
    }

    /**
     * 导出小程序各服务使用日志列表
     */
    //@PreAuthorize("@ss.hasPermi('clickService:log:export')")
    @Log(title = "小程序各服务使用日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxClickServiceLog wxClickServiceLog)
    {
        List<WxClickServiceLog> list = wxClickServiceLogService.selectWxClickServiceLogList(wxClickServiceLog);
        ExcelUtil<WxClickServiceLog> util = new ExcelUtil<WxClickServiceLog>(WxClickServiceLog.class);
        util.exportExcel(response, list, "小程序各服务使用日志数据");
    }

    /**
     * 获取小程序各服务使用日志详细信息
     */
    //@PreAuthorize("@ss.hasPermi('clickService:log:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(wxClickServiceLogService.selectWxClickServiceLogById(id));
    }

    /**
     * 新增小程序各服务使用日志
     */
    //@PreAuthorize("@ss.hasPermi('clickService:log:add')")
    @Log(title = "小程序各服务使用日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxClickServiceLog wxClickServiceLog)
    {
        return toAjax(wxClickServiceLogService.insertWxClickServiceLog(wxClickServiceLog));
    }

    /**
     * 修改小程序各服务使用日志
     */
    //@PreAuthorize("@ss.hasPermi('clickService:log:edit')")
    @Log(title = "小程序各服务使用日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxClickServiceLog wxClickServiceLog)
    {
        return toAjax(wxClickServiceLogService.updateWxClickServiceLog(wxClickServiceLog));
    }

    /**
     * 删除小程序各服务使用日志
     */
    //@PreAuthorize("@ss.hasPermi('clickService:log:remove')")
    @Log(title = "小程序各服务使用日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wxClickServiceLogService.deleteWxClickServiceLogByIds(ids));
    }
}
