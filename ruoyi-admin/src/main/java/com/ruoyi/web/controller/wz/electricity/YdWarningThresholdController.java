package com.ruoyi.web.controller.wz.electricity;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.electricity.domain.YdWarningThreshold;
import com.ruoyi.electricity.service.IYdWarningThresholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用电预警阈值管理Controller
 *
 * @author ruoyi
 * @date 2024-11-05
 */
@RestController
@RequestMapping("/yd/warning/threshold")
public class YdWarningThresholdController extends BaseController
{
    @Autowired
    private IYdWarningThresholdService ydWarningThresholdService;

    /**
     * 查询用电预警阈值管理列表
     */
    //@PreAuthorize("@ss.hasPermi('system:threshold:list')")
    @GetMapping("/list")
    public TableDataInfo list(YdWarningThreshold ydWarningThreshold)
    {
        startPage();
        List<YdWarningThreshold> list = ydWarningThresholdService.selectYdWarningThresholdList(ydWarningThreshold);
        return getDataTable(list);
    }

    /**
     * 导出用电预警阈值管理列表
     */
    //@PreAuthorize("@ss.hasPermi('system:threshold:export')")
    //@Log(title = "用电预警阈值管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YdWarningThreshold ydWarningThreshold)
    {
        List<YdWarningThreshold> list = ydWarningThresholdService.selectYdWarningThresholdList(ydWarningThreshold);
        ExcelUtil<YdWarningThreshold> util = new ExcelUtil<YdWarningThreshold>(YdWarningThreshold.class);
        util.exportExcel(response, list, "用电预警阈值管理数据");
    }

    /**
     * 获取用电预警阈值管理详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:threshold:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(ydWarningThresholdService.selectYdWarningThresholdById(id));
    }

    /**
     * 新增用电预警阈值管理
     */
    //@PreAuthorize("@ss.hasPermi('system:threshold:add')")
    //@Log(title = "用电预警阈值管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YdWarningThreshold ydWarningThreshold)
    {
        return toAjax(ydWarningThresholdService.insertYdWarningThreshold(ydWarningThreshold));
    }

    /**
     * 修改用电预警阈值管理
     */
    //@PreAuthorize("@ss.hasPermi('system:threshold:edit')")
    //@Log(title = "用电预警阈值管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YdWarningThreshold ydWarningThreshold)
    {
        return toAjax(ydWarningThresholdService.updateYdWarningThreshold(ydWarningThreshold));
    }

    /**
     * 删除用电预警阈值管理
     */
    //@PreAuthorize("@ss.hasPermi('system:threshold:remove')")
    //@Log(title = "用电预警阈值管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ydWarningThresholdService.deleteYdWarningThresholdByIds(ids));
    }
}
