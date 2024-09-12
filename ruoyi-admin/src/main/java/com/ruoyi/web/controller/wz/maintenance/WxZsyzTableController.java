package com.ruoyi.web.controller.wz.maintenance;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.WxZsyzTable;
import com.ruoyi.system.service.IWxZsyzTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 招资引商Controller
 *
 * @author lgh
 * @date 2022-11-22
 */
@RestController
@RequestMapping("/maintenance/wxZsyzTable")
public class WxZsyzTableController extends BaseController
{
    @Autowired
    private IWxZsyzTableService wxZsyzTableService;

    /**
     * 新增招资引商
     */
    //@PreAuthorize("@ss.hasPermi('system:wxZsyzTable:add')")
    @Log(title = "招资引商", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxZsyzTable wxZsyzTable)
    {
        return toAjax(wxZsyzTableService.insertWxZsyzTable(wxZsyzTable));
    }

    /**
     * 导出招资引商列表
     */
    //@PreAuthorize("@ss.hasPermi('system:wxZsyzTable:export')")
    @Log(title = "招资引商", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxZsyzTable wxZsyzTable)
    {
        List<WxZsyzTable> list = wxZsyzTableService.selectWxZsyzTableList(wxZsyzTable);
        ExcelUtil<WxZsyzTable> util = new ExcelUtil<WxZsyzTable>(WxZsyzTable.class);
        util.exportExcel(response, list, "招资引商数据");
    }

    /**
     * 查询招资引商列表
     */
    //@PreAuthorize("@ss.hasPermi('system:wxZsyzTable:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxZsyzTable wxZsyzTable)
    {
        startPage();
        List<WxZsyzTable> list = wxZsyzTableService.selectWxZsyzTableList(wxZsyzTable);
        return getDataTable(list);
    }

    /**
     * 获取招资引商详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:wxZsyzTable:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(wxZsyzTableService.selectWxZsyzTableById(id));
    }


    /**
     * 修改招资引商
     */
    //@PreAuthorize("@ss.hasPermi('system:wxZsyzTable:edit')")
    @Log(title = "招资引商", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxZsyzTable wxZsyzTable)
    {
        return toAjax(wxZsyzTableService.updateWxZsyzTable(wxZsyzTable));
    }

    /**
     * 删除招资引商
     */
    //@PreAuthorize("@ss.hasPermi('system:wxZsyzTable:remove')")
    @Log(title = "招资引商", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wxZsyzTableService.deleteWxZsyzTableByIds(ids));
    }
}
