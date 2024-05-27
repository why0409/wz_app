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
import com.ruoyi.app.domain.HmwzBanner;
import com.ruoyi.app.service.IHmwzBannerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 和美湾沚-banner管理Controller
 *
 * @author ruoyi
 * @date 2024-05-06
 */
@RestController
@RequestMapping("/hmwz/banner")
public class HmwzBannerController extends BaseController
{
    @Autowired
    private IHmwzBannerService hmwzBannerService;

    /**
     * 查询和美湾沚-banner管理列表
     */
    //@PreAuthorize("@ss.hasPermi('hmwz:banner:list')")
    @GetMapping("/list")
    public TableDataInfo list(HmwzBanner hmwzBanner)
    {
        startPage();
        List<HmwzBanner> list = hmwzBannerService.selectHmwzBannerList(hmwzBanner);
        return getDataTable(list);
    }

    /**
     * 导出和美湾沚-banner管理列表
     */
    //@PreAuthorize("@ss.hasPermi('hmwz:banner:export')")
    @Log(title = "和美湾沚-banner管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HmwzBanner hmwzBanner)
    {
        List<HmwzBanner> list = hmwzBannerService.selectHmwzBannerList(hmwzBanner);
        ExcelUtil<HmwzBanner> util = new ExcelUtil<HmwzBanner>(HmwzBanner.class);
        util.exportExcel(response, list, "和美湾沚-banner管理数据");
    }

    /**
     * 获取和美湾沚-banner管理详细信息
     */
    //@PreAuthorize("@ss.hasPermi('hmwz:banner:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(hmwzBannerService.selectHmwzBannerById(id));
    }

    /**
     * 新增和美湾沚-banner管理
     */
    //@PreAuthorize("@ss.hasPermi('hmwz:banner:add')")
    @Log(title = "和美湾沚-banner管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HmwzBanner hmwzBanner)
    {
        return toAjax(hmwzBannerService.insertHmwzBanner(hmwzBanner));
    }

    /**
     * 修改和美湾沚-banner管理
     */
    //@PreAuthorize("@ss.hasPermi('hmwz:banner:edit')")
    @Log(title = "和美湾沚-banner管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HmwzBanner hmwzBanner)
    {
        return toAjax(hmwzBannerService.updateHmwzBanner(hmwzBanner));
    }

    /**
     * 删除和美湾沚-banner管理
     */
    //@PreAuthorize("@ss.hasPermi('hmwz:banner:remove')")
    @Log(title = "和美湾沚-banner管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(hmwzBannerService.deleteHmwzBannerByIds(ids));
    }
}
