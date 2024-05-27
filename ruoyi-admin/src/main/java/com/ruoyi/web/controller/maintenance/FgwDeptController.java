package com.ruoyi.web.controller.maintenance;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.FgwDept;
import com.ruoyi.system.service.IFgwDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 发改委责任单位关联Controller
 * 
 * @author ruoyi
 * @date 2022-11-24
 */
@RestController
@RequestMapping("/system/pro_dept")
public class FgwDeptController extends BaseController
{
    @Autowired
    private IFgwDeptService fgwDeptService;

    /**
     * 查询发改委责任单位关联列表
     */
    //@PreAuthorize("@ss.hasPermi('system:pro_dept:list')")
    @GetMapping("/list")
    public TableDataInfo list(FgwDept fgwDept)
    {
        startPage();
        List<FgwDept> list = fgwDeptService.selectFgwDeptList(fgwDept);
        return getDataTable(list);
    }

    /**
     * 导出发改委责任单位关联列表
     */
    //@PreAuthorize("@ss.hasPermi('system:pro_dept:export')")
    @Log(title = "发改委责任单位关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FgwDept fgwDept)
    {
        List<FgwDept> list = fgwDeptService.selectFgwDeptList(fgwDept);
        ExcelUtil<FgwDept> util = new ExcelUtil<FgwDept>(FgwDept.class);
        util.exportExcel(response, list, "发改委责任单位关联数据");
    }

    /**
     * 获取发改委责任单位关联详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:pro_dept:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(fgwDeptService.selectFgwDeptById(id));
    }

    /**
     * 新增发改委责任单位关联
     */
    //@PreAuthorize("@ss.hasPermi('system:pro_dept:add')")
    @Log(title = "发改委责任单位关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FgwDept fgwDept)
    {
        return toAjax(fgwDeptService.insertFgwDept(fgwDept));
    }

    /**
     * 修改发改委责任单位关联
     */
    //@PreAuthorize("@ss.hasPermi('system:pro_dept:edit')")
    @Log(title = "发改委责任单位关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FgwDept fgwDept)
    {
        return toAjax(fgwDeptService.updateFgwDept(fgwDept));
    }

    /**
     * 删除发改委责任单位关联
     */
    //@PreAuthorize("@ss.hasPermi('system:pro_dept:remove')")
    @Log(title = "发改委责任单位关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(fgwDeptService.deleteFgwDeptByIds(ids));
    }
}
