package com.ruoyi.web.controller.wz.activities;

import com.ruoyi.activities.domain.HomestayInfo;
import com.ruoyi.activities.service.IHomestayInfoService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 民宿信息Controller
 *
 * @author ruoyi
 * @date 2024-06-20
 */
@RestController
@RequestMapping("/homestay/info")
public class HomestayInfoController extends BaseController
{
    @Autowired
    private IHomestayInfoService homestayInfoService;

    /**
     * 查询民宿信息列表
     */
    //@PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(HomestayInfo homestayInfo)
    {
        startPage();
        List<HomestayInfo> list = homestayInfoService.selectHomestayInfoList(homestayInfo);
        return getDataTable(list);
    }

    /**
     * 导出民宿信息列表
     */
    //@PreAuthorize("@ss.hasPermi('system:info:export')")
    @Log(title = "民宿信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HomestayInfo homestayInfo)
    {
        List<HomestayInfo> list = homestayInfoService.selectHomestayInfoList(homestayInfo);
        ExcelUtil<HomestayInfo> util = new ExcelUtil<HomestayInfo>(HomestayInfo.class);
        util.exportExcel(response, list, "民宿信息数据");
    }

    /**
     * 获取民宿信息详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(homestayInfoService.selectHomestayInfoById(id));
    }

    /**
     * 新增民宿信息
     */
    //@PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "民宿信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HomestayInfo homestayInfo)
    {
        return toAjax(homestayInfoService.insertHomestayInfo(homestayInfo));
    }

    /**
     * 修改民宿信息
     */
    //@PreAuthorize("@ss.hasPermi('system:info:edit')")
    @Log(title = "民宿信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HomestayInfo homestayInfo)
    {
        return toAjax(homestayInfoService.updateHomestayInfo(homestayInfo));
    }

    /**
     * 删除民宿信息
     */
    //@PreAuthorize("@ss.hasPermi('system:info:remove')")
    @Log(title = "民宿信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(homestayInfoService.deleteHomestayInfoByIds(ids));
    }


}
