package com.ruoyi.web.controller.maintenance;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.WzMorning;
import com.ruoyi.system.service.IWzMorningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 湾沚早报Controller
 *
 * @author ruoyi
 * @date 2023-12-25
 */
@RestController
@RequestMapping("/system/morning")
public class WzMorningController extends BaseController
{
    @Autowired
    private IWzMorningService wzMorningService;

    /**
     * 查询湾沚早报列表
     */
//    @PreAuthorize("@ss.hasPermi('system:morning:list')")
    @GetMapping("/list")
    public TableDataInfo list(WzMorning wzMorning)
    {
        startPage();
        List<WzMorning> list = wzMorningService.selectWzMorningList(wzMorning);
        return getDataTable(list);
    }

    /**
     * 导出湾沚早报列表
     */
//    @PreAuthorize("@ss.hasPermi('system:morning:export')")
    @Log(title = "湾沚早报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WzMorning wzMorning)
    {
        List<WzMorning> list = wzMorningService.selectWzMorningList(wzMorning);
        ExcelUtil<WzMorning> util = new ExcelUtil<WzMorning>(WzMorning.class);
        util.exportExcel(response, list, "湾沚早报数据");
    }

    /**
     * 获取湾沚早报详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:morning:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(wzMorningService.selectWzMorningById(id));
    }

    /**
     * 新增湾沚早报
     */
//    @PreAuthorize("@ss.hasPermi('system:morning:add')")
    @Log(title = "湾沚早报", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WzMorning wzMorning)
    {
        return toAjax(wzMorningService.insertWzMorning(wzMorning));
    }

    /**
     * 修改湾沚早报
     */
//    @PreAuthorize("@ss.hasPermi('system:morning:edit')")
    @Log(title = "湾沚早报", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WzMorning wzMorning)
    {
        return toAjax(wzMorningService.updateWzMorning(wzMorning));
    }

    /**
     * 删除湾沚早报
     */
//    @PreAuthorize("@ss.hasPermi('system:morning:remove')")
    @Log(title = "湾沚早报", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wzMorningService.deleteWzMorningByIds(ids));
    }
}

