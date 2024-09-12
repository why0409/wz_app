package com.ruoyi.web.controller.wz.safetyHazard;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.safetyHazard.domain.SafetyHazardUserType;
import com.ruoyi.safetyHazard.service.ISafetyHazardUserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 安全隐患-角色类型管理Controller
 *
 * @author ruoyi
 * @date 2024-08-08
 */
@RestController
@RequestMapping("/safetyHazard/userType")
public class SafetyHazardUserTypeController extends BaseController
{
    @Autowired
    private ISafetyHazardUserTypeService safetyHazardUserTypeService;

    /**
     * 查询安全隐患-角色类型管理列表
     */
    //@PreAuthorize("@ss.hasPermi('system:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(SafetyHazardUserType safetyHazardUserType)
    {
        startPage();
        List<SafetyHazardUserType> list = safetyHazardUserTypeService.selectSafetyHazardUserTypeList(safetyHazardUserType);
        return getDataTable(list);
    }

    /**
     * 导出安全隐患-角色类型管理列表
     */
    //@PreAuthorize("@ss.hasPermi('system:type:export')")
    //@Log(title = "安全隐患-角色类型管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SafetyHazardUserType safetyHazardUserType)
    {
        List<SafetyHazardUserType> list = safetyHazardUserTypeService.selectSafetyHazardUserTypeList(safetyHazardUserType);
        ExcelUtil<SafetyHazardUserType> util = new ExcelUtil<SafetyHazardUserType>(SafetyHazardUserType.class);
        util.exportExcel(response, list, "安全隐患-角色类型管理数据");
    }

    /**
     * 获取安全隐患-角色类型管理详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(safetyHazardUserTypeService.selectSafetyHazardUserTypeById(id));
    }

    /**
     * 新增安全隐患-角色类型管理
     */
    //@PreAuthorize("@ss.hasPermi('system:type:add')")
    //@Log(title = "安全隐患-角色类型管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SafetyHazardUserType safetyHazardUserType)
    {
        return toAjax(safetyHazardUserTypeService.insertSafetyHazardUserType(safetyHazardUserType));
    }

    /**
     * 修改安全隐患-角色类型管理
     */
    //@PreAuthorize("@ss.hasPermi('system:type:edit')")
    //@Log(title = "安全隐患-角色类型管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SafetyHazardUserType safetyHazardUserType)
    {
        return toAjax(safetyHazardUserTypeService.updateSafetyHazardUserType(safetyHazardUserType));
    }

    /**
     * 删除安全隐患-角色类型管理
     */
    //@PreAuthorize("@ss.hasPermi('system:type:remove')")
    //@Log(title = "安全隐患-角色类型管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(safetyHazardUserTypeService.deleteSafetyHazardUserTypeByIds(ids));
    }

    /**
     * 类型-树结构
     */
    @GetMapping("/getTypeVoList")
    public AjaxResult getTypeVoList()
    {
        return success(safetyHazardUserTypeService.selectSafetyHazardUserTypeVoList());
    }
}
