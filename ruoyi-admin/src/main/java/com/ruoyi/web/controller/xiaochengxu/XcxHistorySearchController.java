package com.ruoyi.web.controller.xiaochengxu;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.xcx.domain.XcxHistorySearch;
import com.ruoyi.xcx.service.IXcxHistorySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 历史搜索Controller
 *
 * @author ruoyi
 * @date 2023-11-14
 */
@RestController
@RequestMapping("/system/search")
public class XcxHistorySearchController extends BaseController
{
    @Autowired
    private IXcxHistorySearchService xcxHistorySearchService;

    /**
     * 查询历史搜索列表
     */
    //@PreAuthorize("@ss.hasPermi('system:search:list')")
    @GetMapping("/list")
    public TableDataInfo list(XcxHistorySearch xcxHistorySearch)
    {
        startPage();
        List<XcxHistorySearch> list = xcxHistorySearchService.selectXcxHistorySearchList(xcxHistorySearch);
        return getDataTable(list);
    }

    /**
     * 导出历史搜索列表
     */
    //@PreAuthorize("@ss.hasPermi('system:search:export')")
    @Log(title = "历史搜索", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XcxHistorySearch xcxHistorySearch)
    {
        List<XcxHistorySearch> list = xcxHistorySearchService.selectXcxHistorySearchList(xcxHistorySearch);
        ExcelUtil<XcxHistorySearch> util = new ExcelUtil<XcxHistorySearch>(XcxHistorySearch.class);
        util.exportExcel(response, list, "历史搜索数据");
    }

    /**
     * 获取历史搜索详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:search:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(xcxHistorySearchService.selectXcxHistorySearchById(id));
    }

    /**
     * 新增历史搜索
     */
    //@PreAuthorize("@ss.hasPermi('system:search:add')")
    @Log(title = "历史搜索", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XcxHistorySearch xcxHistorySearch)
    {
        return toAjax(xcxHistorySearchService.insertXcxHistorySearch(xcxHistorySearch));
    }

    /**
     * 修改历史搜索
     */
    //@PreAuthorize("@ss.hasPermi('system:search:edit')")
    @Log(title = "历史搜索", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XcxHistorySearch xcxHistorySearch)
    {
        return toAjax(xcxHistorySearchService.updateXcxHistorySearch(xcxHistorySearch));
    }

    /**
     * 删除历史搜索
     */
    //@PreAuthorize("@ss.hasPermi('system:search:remove')")
    @Log(title = "历史搜索", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xcxHistorySearchService.deleteXcxHistorySearchByIds(ids));
    }
}

