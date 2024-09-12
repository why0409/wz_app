package com.ruoyi.web.controller.wz.app;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.app.domain.ServiceBanner;
import com.ruoyi.app.service.IServiceBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 服务banner图轮播Controller
 *
 * @author ruoyi
 * @date 2024-04-30
 */
@RestController
@RequestMapping("/service/banner")
public class ServiceBannerController extends BaseController
{
    @Autowired
    private IServiceBannerService serviceBannerService;

    /**
     * 查询服务banner图轮播列表
     */
    //@PreAuthorize("@ss.hasPermi('service:banner:list')")
    @GetMapping("/list")
    public TableDataInfo list(ServiceBanner serviceBanner)
    {
        startPage();
        List<ServiceBanner> list = serviceBannerService.selectServiceBannerList(serviceBanner);
        return getDataTable(list);
    }

    /**
     * 导出服务banner图轮播列表
     */
    //@PreAuthorize("@ss.hasPermi('service:banner:export')")
    @Log(title = "服务banner图轮播", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ServiceBanner serviceBanner)
    {
        List<ServiceBanner> list = serviceBannerService.selectServiceBannerList(serviceBanner);
        ExcelUtil<ServiceBanner> util = new ExcelUtil<ServiceBanner>(ServiceBanner.class);
        util.exportExcel(response, list, "服务banner图轮播数据");
    }

    /**
     * 获取服务banner图轮播详细信息
     */
    //@PreAuthorize("@ss.hasPermi('service:banner:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(serviceBannerService.selectServiceBannerById(id));
    }

    /**
     * 新增服务banner图轮播
     */
    //@PreAuthorize("@ss.hasPermi('service:banner:add')")
    @Log(title = "服务banner图轮播", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ServiceBanner serviceBanner)
    {
        return toAjax(serviceBannerService.insertServiceBanner(serviceBanner));
    }

    /**
     * 修改服务banner图轮播
     */
    //@PreAuthorize("@ss.hasPermi('service:banner:edit')")
    @Log(title = "服务banner图轮播", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ServiceBanner serviceBanner)
    {
        return toAjax(serviceBannerService.updateServiceBanner(serviceBanner));
    }

    /**
     * 删除服务banner图轮播
     */
    //@PreAuthorize("@ss.hasPermi('service:banner:remove')")
    @Log(title = "服务banner图轮播", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(serviceBannerService.deleteServiceBannerByIds(ids));
    }
}
