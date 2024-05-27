package com.ruoyi.web.controller.app;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.app.domain.vo.ServiceConfigVo;
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
import com.ruoyi.app.domain.ServiceConfig;
import com.ruoyi.app.service.IServiceConfigService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 服务配置Controller
 *
 * @author ruoyi
 * @date 2024-04-25
 */
@RestController
@RequestMapping("/service/config")
public class ServiceConfigController extends BaseController
{
    @Autowired
    private IServiceConfigService serviceConfigService;

    /**
     * 查询服务配置列表
     */
    //@PreAuthorize("@ss.hasPermi('service:config:list')")
    @GetMapping("/list")
    public TableDataInfo list(ServiceConfig serviceConfig)
    {
        startPage();
        //List<ServiceConfig> list = serviceConfigService.selectServiceConfigList(serviceConfig);
        List<ServiceConfigVo> list = serviceConfigService.selectServiceConfigVoList(serviceConfig);
        return getDataTable(list);
    }

    /**
     * 导出服务配置列表
     */
    //@PreAuthorize("@ss.hasPermi('service:config:export')")
    @Log(title = "服务配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ServiceConfig serviceConfig)
    {
        List<ServiceConfig> list = serviceConfigService.selectServiceConfigList(serviceConfig);
        ExcelUtil<ServiceConfig> util = new ExcelUtil<ServiceConfig>(ServiceConfig.class);
        util.exportExcel(response, list, "服务配置数据");
    }

    /**
     * 获取服务配置详细信息
     */
    //@PreAuthorize("@ss.hasPermi('service:config:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(serviceConfigService.selectServiceConfigById(id));
    }

    /**
     * 新增服务配置
     */
    //@PreAuthorize("@ss.hasPermi('service:config:add')")
    @Log(title = "服务配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ServiceConfig serviceConfig)
    {
        return toAjax(serviceConfigService.insertServiceConfig(serviceConfig));
    }

    /**
     * 修改服务配置
     */
    //@PreAuthorize("@ss.hasPermi('service:config:edit')")
    @Log(title = "服务配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ServiceConfig serviceConfig)
    {
        return toAjax(serviceConfigService.updateServiceConfig(serviceConfig));
    }

    /**
     * 删除服务配置
     */
    //@PreAuthorize("@ss.hasPermi('service:config:remove')")
    @Log(title = "服务配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(serviceConfigService.deleteServiceConfigByIds(ids));
    }

    /**
     * 政府服务权限列表（新）
     */
    @GetMapping("/queryWxUserMenuByPhoneNew")
    public TableDataInfo queryWxUserMenuByPhoneNew(String phone) {
        startPage();
        List<ServiceConfigVo> list = serviceConfigService.queryWxUserMenuByPhoneNew(phone);
        return getDataTable(list);
    }

    @GetMapping("/getSmAndTjList")
    public AjaxResult getSmAndTjList()
    {
        List<ServiceConfig> list = serviceConfigService.getSmAndTjList();
        return success(list);
    }
}

