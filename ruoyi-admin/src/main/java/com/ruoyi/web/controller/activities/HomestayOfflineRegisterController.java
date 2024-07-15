package com.ruoyi.web.controller.activities;

import com.ruoyi.activities.domain.HomestayOfflineRegister;
import com.ruoyi.activities.service.IHomestayOfflineRegisterService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 民宿活动线下劵注册信息Controller
 *
 * @author ruoyi
 * @date 2024-07-10
 */
@RestController
@RequestMapping("/system/register")
public class HomestayOfflineRegisterController extends BaseController
{
    @Autowired
    private IHomestayOfflineRegisterService homestayOfflineRegisterService;

    /**
     * 查询民宿活动线下劵注册信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:register:list')")
    @GetMapping("/list")
    public TableDataInfo list(HomestayOfflineRegister homestayOfflineRegister)
    {
        startPage();
        List<HomestayOfflineRegister> list = homestayOfflineRegisterService.selectHomestayOfflineRegisterList(homestayOfflineRegister);
        return getDataTable(list);
    }

    /**
     * 导出民宿活动线下劵注册信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:register:export')")
    @Log(title = "民宿活动线下劵注册信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HomestayOfflineRegister homestayOfflineRegister)
    {
        List<HomestayOfflineRegister> list = homestayOfflineRegisterService.selectHomestayOfflineRegisterList(homestayOfflineRegister);
        ExcelUtil<HomestayOfflineRegister> util = new ExcelUtil<HomestayOfflineRegister>(HomestayOfflineRegister.class);
        util.exportExcel(response, list, "民宿活动线下劵注册信息数据");
    }

    /**
     * 获取民宿活动线下劵注册信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:register:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(homestayOfflineRegisterService.selectHomestayOfflineRegisterById(id));
    }

    /**
     * 新增民宿活动线下劵注册信息
     */
    @PreAuthorize("@ss.hasPermi('system:register:add')")
    @Log(title = "民宿活动线下劵注册信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HomestayOfflineRegister homestayOfflineRegister)
    {
        return toAjax(homestayOfflineRegisterService.insertHomestayOfflineRegister(homestayOfflineRegister));
    }

    /**
     * 修改民宿活动线下劵注册信息
     */
    @PreAuthorize("@ss.hasPermi('system:register:edit')")
    @Log(title = "民宿活动线下劵注册信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HomestayOfflineRegister homestayOfflineRegister)
    {
        return toAjax(homestayOfflineRegisterService.updateHomestayOfflineRegister(homestayOfflineRegister));
    }

    /**
     * 删除民宿活动线下劵注册信息
     */
    @PreAuthorize("@ss.hasPermi('system:register:remove')")
    @Log(title = "民宿活动线下劵注册信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(homestayOfflineRegisterService.deleteHomestayOfflineRegisterByIds(ids));
    }
}
