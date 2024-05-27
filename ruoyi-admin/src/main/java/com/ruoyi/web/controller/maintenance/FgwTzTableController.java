package com.ruoyi.web.controller.maintenance;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.FgwTzTable;
import com.ruoyi.system.service.IFgwTzTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 数据维护Controller
 *
 * @author ruoyi
 * @date 2022-11-17
 */
@RestController
@RequestMapping("/maintenance/dataMain")
public class FgwTzTableController extends BaseController
{
    @Autowired
    private IFgwTzTableService fgwTzTableService;

    /**
     * 查询数据维护列表
     */
    //@PreAuthorize("@ss.hasPermi('maintenance:dataMain:list')")
    @GetMapping("/list")
    public TableDataInfo list(FgwTzTable fgwTzTable)
    {
        startPage();
        List<FgwTzTable> list = fgwTzTableService.selectFgwTzTableList(fgwTzTable);
        return getDataTable(list);
    }

    /**
     * 导出数据维护列表
     */
    //@PreAuthorize("@ss.hasPermi('maintenance:dataMain:export')")
    @Log(title = "数据维护", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FgwTzTable fgwTzTable)
    {
        List<FgwTzTable> list = fgwTzTableService.selectFgwTzTableList(fgwTzTable);
        ExcelUtil<FgwTzTable> util = new ExcelUtil<FgwTzTable>(FgwTzTable.class);
        util.exportExcel(response, list, "数据维护数据");
    }

    /**
     * 获取数据维护详细信息
     */
    //@PreAuthorize("@ss.hasPermi('maintenance:dataMain:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(fgwTzTableService.selectFgwTzTableById(id));
    }

    /**
     * 新增数据维护
     */
    //@PreAuthorize("@ss.hasPermi('maintenance:dataMain:add')")
    @Log(title = "数据维护", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FgwTzTable fgwTzTable)
    {
        return toAjax(fgwTzTableService.insertFgwTzTable(fgwTzTable));
    }

    /**
     * 修改数据维护
     */
    //@PreAuthorize("@ss.hasPermi('maintenance:dataMain:edit')")
    @Log(title = "数据维护", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FgwTzTable fgwTzTable)
    {
        return toAjax(fgwTzTableService.updateFgwTzTable(fgwTzTable));
    }

    /**
     * 删除数据维护
     */
    //@PreAuthorize("@ss.hasPermi('maintenance:dataMain:remove')")
    @Log(title = "数据维护", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(fgwTzTableService.deleteFgwTzTableByIds(ids));
    }


    /**
     * 自定义表格下载
     */
    @RequestMapping("exportData")
    public void exportData(HttpServletResponse response) throws Exception {

    }
}
