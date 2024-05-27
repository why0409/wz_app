package com.ruoyi.web.controller.app;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.app.domain.vo.ServiceHomeInfo;
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
import com.ruoyi.app.domain.ServiceHome;
import com.ruoyi.app.service.IServiceHomeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 首页服务Controller
 *
 * @author ruoyi
 * @date 2024-04-25
 */
@RestController
@RequestMapping("/service/home")
public class ServiceHomeController extends BaseController
{
    @Autowired
    private IServiceHomeService serviceHomeService;

    /**
     * 查询首页服务列表
     */
    //@PreAuthorize("@ss.hasPermi('service:home:list')")
    @GetMapping("/list")
    public TableDataInfo list(ServiceHome serviceHome)
    {
        startPage();
        //List<ServiceHome> list = serviceHomeService.selectServiceHomeList(serviceHome);
        List<ServiceHomeInfo> list = serviceHomeService.getServiceHomeInfoList(serviceHome);
        return getDataTable(list);
    }

    /**
     * 导出首页服务列表
     */
    //@PreAuthorize("@ss.hasPermi('service:home:export')")
    @Log(title = "首页服务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ServiceHome serviceHome)
    {
        List<ServiceHome> list = serviceHomeService.selectServiceHomeList(serviceHome);
        ExcelUtil<ServiceHome> util = new ExcelUtil<ServiceHome>(ServiceHome.class);
        util.exportExcel(response, list, "首页服务数据");
    }

    /**
     * 获取首页服务详细信息
     */
    //@PreAuthorize("@ss.hasPermi('service:home:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(serviceHomeService.selectServiceHomeById(id));
    }

    /**
     * 新增首页服务
     */
    //@PreAuthorize("@ss.hasPermi('service:home:add')")
    @Log(title = "首页服务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ServiceHome serviceHome)
    {
        return toAjax(serviceHomeService.insertServiceHome(serviceHome));
    }

    /**
     * 修改首页服务
     */
    //@PreAuthorize("@ss.hasPermi('service:home:edit')")
    @Log(title = "首页服务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ServiceHome serviceHome)
    {
        return toAjax(serviceHomeService.updateServiceHome(serviceHome));
    }

    /**
     * 删除首页服务
     */
    //@PreAuthorize("@ss.hasPermi('service:home:remove')")
    @Log(title = "首页服务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(serviceHomeService.deleteServiceHomeByIds(ids));
    }
}
