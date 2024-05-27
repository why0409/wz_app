package com.ruoyi.web.controller.maintenance;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.FgwProgressType;
import com.ruoyi.system.service.IFgwProgressTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 项目进度类型Controller
 * 
 * @author ruoyi
 * @date 2022-11-24
 */
@RestController
@RequestMapping("/system/pro_type")
public class FgwProgressTypeController extends BaseController
{
    @Autowired
    private IFgwProgressTypeService fgwProgressTypeService;

    /**
     * 查询项目进度类型列表
     */
    //@PreAuthorize("@ss.hasPermi('system:pro_type:list')")
    @GetMapping("/list")
    public TableDataInfo list(FgwProgressType fgwProgressType)
    {
        startPage();
        List<FgwProgressType> list = fgwProgressTypeService.selectFgwProgressTypeList(fgwProgressType);
        return getDataTable(list);
    }

    /**
     * 导出项目进度类型列表
     */
    //@PreAuthorize("@ss.hasPermi('system:pro_type:export')")
    @Log(title = "项目进度类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FgwProgressType fgwProgressType)
    {
        List<FgwProgressType> list = fgwProgressTypeService.selectFgwProgressTypeList(fgwProgressType);
        ExcelUtil<FgwProgressType> util = new ExcelUtil<FgwProgressType>(FgwProgressType.class);
        util.exportExcel(response, list, "项目进度类型数据");
    }

    /**
     * 获取项目进度类型详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:pro_type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(fgwProgressTypeService.selectFgwProgressTypeById(id));
    }

    /**
     * 新增项目进度类型
     */
    //@PreAuthorize("@ss.hasPermi('system:pro_type:add')")
    @Log(title = "项目进度类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FgwProgressType fgwProgressType)
    {
        return toAjax(fgwProgressTypeService.insertFgwProgressType(fgwProgressType));
    }

    /**
     * 先删后插
     */
    @RequestMapping("/saveList")
    public AjaxResult saveList(@RequestBody List<FgwProgressType> fgwProgressTypeList)
    {
        return toAjax(fgwProgressTypeService.saveList(fgwProgressTypeList));
    }
    /**
     * 修改项目进度类型
     */
    //@PreAuthorize("@ss.hasPermi('system:pro_type:edit')")
    @Log(title = "项目进度类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FgwProgressType fgwProgressType)
    {
        return toAjax(fgwProgressTypeService.updateFgwProgressType(fgwProgressType));
    }

    /**
     * 删除项目进度类型
     */
    //@PreAuthorize("@ss.hasPermi('system:pro_type:remove')")
    @Log(title = "项目进度类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(fgwProgressTypeService.deleteFgwProgressTypeByIds(ids));
    }
}
