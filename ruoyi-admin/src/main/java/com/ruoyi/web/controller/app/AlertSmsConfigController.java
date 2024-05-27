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
import com.ruoyi.app.domain.AlertSmsConfig;
import com.ruoyi.app.service.IAlertSmsConfigService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 预警短信配置Controller
 *
 * @author ruoyi
 * @date 2024-05-06
 */
@RestController
@RequestMapping("/alertSmsConfig/config")
public class AlertSmsConfigController extends BaseController
{
    @Autowired
    private IAlertSmsConfigService alertSmsConfigService;

    /**
     * 查询预警短信配置列表
     */
    //@PreAuthorize("@ss.hasPermi('alertSmsConfig:config:list')")
    @GetMapping("/list")
    public TableDataInfo list(AlertSmsConfig alertSmsConfig)
    {
        startPage();
        List<AlertSmsConfig> list = alertSmsConfigService.selectAlertSmsConfigList(alertSmsConfig);
        return getDataTable(list);
    }

    /**
     * 导出预警短信配置列表
     */
    //@PreAuthorize("@ss.hasPermi('alertSmsConfig:config:export')")
    @Log(title = "预警短信配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AlertSmsConfig alertSmsConfig)
    {
        List<AlertSmsConfig> list = alertSmsConfigService.selectAlertSmsConfigList(alertSmsConfig);
        ExcelUtil<AlertSmsConfig> util = new ExcelUtil<AlertSmsConfig>(AlertSmsConfig.class);
        util.exportExcel(response, list, "预警短信配置数据");
    }

    /**
     * 获取预警短信配置详细信息
     */
    //@PreAuthorize("@ss.hasPermi('alertSmsConfig:config:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(alertSmsConfigService.selectAlertSmsConfigById(id));
    }

    /**
     * 新增预警短信配置
     */
    //@PreAuthorize("@ss.hasPermi('alertSmsConfig:config:add')")
    @Log(title = "预警短信配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AlertSmsConfig alertSmsConfig)
    {
        return toAjax(alertSmsConfigService.insertAlertSmsConfig(alertSmsConfig));
    }

    /**
     * 修改预警短信配置
     */
    //@PreAuthorize("@ss.hasPermi('alertSmsConfig:config:edit')")
    @Log(title = "预警短信配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AlertSmsConfig alertSmsConfig)
    {
        return toAjax(alertSmsConfigService.updateAlertSmsConfig(alertSmsConfig));
    }

    /**
     * 删除预警短信配置
     */
    //@PreAuthorize("@ss.hasPermi('alertSmsConfig:config:remove')")
    @Log(title = "预警短信配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(alertSmsConfigService.deleteAlertSmsConfigByIds(ids));
    }
}
