package com.ruoyi.web.controller.wz.maintenance;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.WxQzryTable;
import com.ruoyi.system.service.IWxQzryTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 求职人员信息Controller
 *
 * @author lgh
 * @date 2022-11-23
 */
@RestController
@RequestMapping("/maintenance/wxQzryTable")
public class WxQzryTableController extends BaseController
{
    @Autowired
    private IWxQzryTableService wxQzryTableService;

    /**
     * 查询求职人员信息列表
     */
    //@PreAuthorize("@ss.hasPermi('system:wxQzryTable:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxQzryTable wxQzryTable)
    {
        startPage();
        List<WxQzryTable> list = wxQzryTableService.selectWxQzryTableList(wxQzryTable);
        return getDataTable(list);
    }

    /**
     * 导出求职人员信息列表
     */
    //@PreAuthorize("@ss.hasPermi('system:wxQzryTable:export')")
    @Log(title = "求职人员信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxQzryTable wxQzryTable)
    {
        List<WxQzryTable> list = wxQzryTableService.selectWxQzryTableList(wxQzryTable);
        ExcelUtil<WxQzryTable> util = new ExcelUtil<WxQzryTable>(WxQzryTable.class);
        util.exportExcel(response, list, "求职人员信息数据");
    }

    /**
     * 获取求职人员信息详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:wxQzryTable:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(wxQzryTableService.selectWxQzryTableById(id));
    }

    /**
     * 新增求职人员信息
     */
    //@PreAuthorize("@ss.hasPermi('system:wxQzryTable:add')")
    @Log(title = "求职人员信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxQzryTable wxQzryTable)
    {
        return toAjax(wxQzryTableService.insertWxQzryTable(wxQzryTable));
    }

    /**
     * 修改求职人员信息
     */
    //@PreAuthorize("@ss.hasPermi('system:wxQzryTable:edit')")
    @Log(title = "求职人员信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxQzryTable wxQzryTable)
    {
        return toAjax(wxQzryTableService.updateWxQzryTable(wxQzryTable));
    }

    /**
     * 删除求职人员信息
     */
    //@PreAuthorize("@ss.hasPermi('system:wxQzryTable:remove')")
    @Log(title = "求职人员信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wxQzryTableService.deleteWxQzryTableByIds(ids));
    }
}
