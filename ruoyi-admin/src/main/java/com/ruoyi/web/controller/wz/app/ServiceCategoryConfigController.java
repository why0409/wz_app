package com.ruoyi.web.controller.wz.app;

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
import com.ruoyi.app.domain.ServiceCategoryConfig;
import com.ruoyi.app.service.IServiceCategoryConfigService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 服务类别配置Controller
 *
 * @author ruoyi
 * @date 2024-04-25
 */
@RestController
@RequestMapping("/serviceCategory/config")
public class ServiceCategoryConfigController extends BaseController
{
    @Autowired
    private IServiceCategoryConfigService serviceCategoryConfigService;

    /**
     * 查询服务类别配置列表
     */
    //@PreAuthorize("@ss.hasPermi('serviceCategory:config:list')")
    @GetMapping("/list")
    public TableDataInfo list(ServiceCategoryConfig serviceCategoryConfig)
    {
        startPage();
        List<ServiceCategoryConfig> list = serviceCategoryConfigService.selectServiceCategoryConfigList(serviceCategoryConfig);
        return getDataTable(list);
    }

    /**
     * 导出服务类别配置列表
     */
    //@PreAuthorize("@ss.hasPermi('serviceCategory:config:export')")
    @Log(title = "服务类别配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ServiceCategoryConfig serviceCategoryConfig)
    {
        List<ServiceCategoryConfig> list = serviceCategoryConfigService.selectServiceCategoryConfigList(serviceCategoryConfig);
        ExcelUtil<ServiceCategoryConfig> util = new ExcelUtil<ServiceCategoryConfig>(ServiceCategoryConfig.class);
        util.exportExcel(response, list, "服务类别配置数据");
    }

    /**
     * 获取服务类别配置详细信息
     */
    //@PreAuthorize("@ss.hasPermi('serviceCategory:config:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(serviceCategoryConfigService.selectServiceCategoryConfigById(id));
    }

    /**
     * 新增服务类别配置
     */
    //@PreAuthorize("@ss.hasPermi('serviceCategory:config:add')")
    @Log(title = "服务类别配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ServiceCategoryConfig serviceCategoryConfig)
    {
        return toAjax(serviceCategoryConfigService.insertServiceCategoryConfig(serviceCategoryConfig));
    }

    /**
     * 修改服务类别配置
     */
    //@PreAuthorize("@ss.hasPermi('serviceCategory:config:edit')")
    @Log(title = "服务类别配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ServiceCategoryConfig serviceCategoryConfig)
    {
        return toAjax(serviceCategoryConfigService.updateServiceCategoryConfig(serviceCategoryConfig));
    }

    /**
     * 删除服务类别配置
     */
    //@PreAuthorize("@ss.hasPermi('serviceCategory:config:remove')")
    @Log(title = "服务类别配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(serviceCategoryConfigService.deleteServiceCategoryConfigByIds(ids));
    }
}

