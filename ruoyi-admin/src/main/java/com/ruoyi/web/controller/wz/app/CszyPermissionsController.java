package com.ruoyi.web.controller.wz.app;


import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.app.domain.vo.CszyCameraTypeVo;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.ruoyi.app.domain.CszyPermissions;
import com.ruoyi.app.service.ICszyPermissionsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 城市之眼-权限Controller
 *
 * @author ruoyi
 * @date 2024-05-24
 */
@RestController
@RequestMapping("/cszy/permissions")
public class CszyPermissionsController extends BaseController
{
    @Autowired
    private ICszyPermissionsService cszyPermissionsService;

    /**
     * 查询城市之眼-权限列表
     */
    //@PreAuthorize("@ss.hasPermi('cszy:permissions:list')")
    @GetMapping("/list")
    public TableDataInfo list(CszyPermissions cszyPermissions)
    {
        startPage();
        List<CszyPermissions> list = cszyPermissionsService.selectCszyPermissionsList(cszyPermissions);
        return getDataTable(list);
    }

    /**
     * 导出城市之眼-权限列表
     */
    //@PreAuthorize("@ss.hasPermi('cszy:permissions:export')")
    @Log(title = "城市之眼-权限", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CszyPermissions cszyPermissions)
    {
        List<CszyPermissions> list = cszyPermissionsService.selectCszyPermissionsList(cszyPermissions);
        ExcelUtil<CszyPermissions> util = new ExcelUtil<CszyPermissions>(CszyPermissions.class);
        util.exportExcel(response, list, "城市之眼-权限数据");
    }

    /**
     * 获取城市之眼-权限详细信息
     */
    @PreAuthorize("@ss.hasPermi('cszy:permissions:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(cszyPermissionsService.selectCszyPermissionsById(id));
    }

    /**
     * 新增城市之眼-权限
     */
    //@PreAuthorize("@ss.hasPermi('cszy:permissions:add')")
    @Log(title = "城市之眼-权限", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CszyPermissions cszyPermissions)
    {
        return toAjax(cszyPermissionsService.insertCszyPermissions(cszyPermissions));
    }

    /**
     * 修改城市之眼-权限
     */
    //@PreAuthorize("@ss.hasPermi('cszy:permissions:edit')")
    @Log(title = "城市之眼-权限", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CszyPermissions cszyPermissions)
    {
        return toAjax(cszyPermissionsService.updateCszyPermissions(cszyPermissions));
    }

    /**
     * 删除城市之眼-权限
     */
    //@PreAuthorize("@ss.hasPermi('cszy:permissions:remove')")
    @Log(title = "城市之眼-权限", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(cszyPermissionsService.deleteCszyPermissionsByIds(ids));
    }
}
