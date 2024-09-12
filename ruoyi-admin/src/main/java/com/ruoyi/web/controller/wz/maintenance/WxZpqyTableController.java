package com.ruoyi.web.controller.wz.maintenance;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.WxZpqyTable;
import com.ruoyi.system.service.IWxZpqyTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 招聘企业信息Controller
 *
 * @author ruoyi
 * @date 2022-11-23
 */
@RestController
@RequestMapping("/maintenance/wxZpqyTable")
public class WxZpqyTableController extends BaseController
{
    @Autowired
    private IWxZpqyTableService wxZpqyTableService;

    /**
     * 查询招聘企业信息列表
     */
    //@PreAuthorize("@ss.hasPermi('system:wxZpqyTable:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxZpqyTable wxZpqyTable)
    {
        startPage();
        List<WxZpqyTable> list = wxZpqyTableService.selectWxZpqyTableList(wxZpqyTable);
        return getDataTable(list);
    }

    /**
     * 导出招聘企业信息列表
     */
    //@PreAuthorize("@ss.hasPermi('system:wxZpqyTable:export')")
    @Log(title = "招聘企业信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxZpqyTable wxZpqyTable)
    {
        List<WxZpqyTable> list = wxZpqyTableService.selectWxZpqyTableList(wxZpqyTable);
        ExcelUtil<WxZpqyTable> util = new ExcelUtil<WxZpqyTable>(WxZpqyTable.class);
        util.exportExcel(response, list, "招聘企业信息数据");
    }

    /**
     * 获取招聘企业信息详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:wxZpqyTable:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(wxZpqyTableService.selectWxZpqyTableById(id));
    }

    /**
     * 新增招聘企业信息
     */
    //@PreAuthorize("@ss.hasPermi('system:wxZpqyTable:add')")
    @Log(title = "招聘企业信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxZpqyTable wxZpqyTable)
    {
        return toAjax(wxZpqyTableService.insertWxZpqyTable(wxZpqyTable));
    }

    /**
     * 修改招聘企业信息
     */
    //@PreAuthorize("@ss.hasPermi('system:wxZpqyTable:edit')")
    @Log(title = "招聘企业信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxZpqyTable wxZpqyTable)
    {
        return toAjax(wxZpqyTableService.updateWxZpqyTable(wxZpqyTable));
    }

    /**
     * 删除招聘企业信息
     */
    //@PreAuthorize("@ss.hasPermi('system:wxZpqyTable:remove')")
    @Log(title = "招聘企业信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wxZpqyTableService.deleteWxZpqyTableByIds(ids));
    }
}
