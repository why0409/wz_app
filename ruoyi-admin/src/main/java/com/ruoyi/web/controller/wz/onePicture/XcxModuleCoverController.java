package com.ruoyi.web.controller.wz.onePicture;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.onePicture.domain.XcxModuleCover;
import com.ruoyi.onePicture.service.IXcxModuleCoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 模块封面Controller
 *
 * @author ruoyi
 * @date 2023-12-22
 */
@RestController
@RequestMapping("/xcx/cover")
public class XcxModuleCoverController extends BaseController
{
    @Autowired
    private IXcxModuleCoverService xcxModuleCoverService;

    /**
     * 查询模块封面列表
     */
    //@PreAuthorize("@ss.hasPermi('xcx:cover:list')")
    @GetMapping("/list")
    public TableDataInfo list(XcxModuleCover xcxModuleCover)
    {
        startPage();
        List<XcxModuleCover> list = xcxModuleCoverService.selectXcxModuleCoverList(xcxModuleCover);
        return getDataTable(list);
    }

    /**
     * 导出模块封面列表
     */
    //@PreAuthorize("@ss.hasPermi('xcx:cover:export')")
    @Log(title = "模块封面", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XcxModuleCover xcxModuleCover)
    {
        List<XcxModuleCover> list = xcxModuleCoverService.selectXcxModuleCoverList(xcxModuleCover);
        ExcelUtil<XcxModuleCover> util = new ExcelUtil<XcxModuleCover>(XcxModuleCover.class);
        util.exportExcel(response, list, "模块封面数据");
    }

    /**
     * 获取模块封面详细信息
     */
    //@PreAuthorize("@ss.hasPermi('xcx:cover:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(xcxModuleCoverService.selectXcxModuleCoverById(id));
    }

    /**
     * 新增模块封面
     */
    //@PreAuthorize("@ss.hasPermi('xcx:cover:add')")
    @Log(title = "模块封面", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XcxModuleCover xcxModuleCover)
    {
        return toAjax(xcxModuleCoverService.insertXcxModuleCover(xcxModuleCover));
    }


    @PostMapping("/setCover")
    public AjaxResult setCover(@RequestBody XcxModuleCover xcxModuleCover)
    {
        String module = xcxModuleCover.getModule();
        if (StringUtils.isEmpty(module)) {
            return error("模块名称为必传项！");
        }

        XcxModuleCover x = xcxModuleCoverService.selectXcxModuleCoverByModule(module);
        if (x == null) {
            return toAjax(xcxModuleCoverService.insertXcxModuleCover(xcxModuleCover));
        } else {
            return toAjax(xcxModuleCoverService.updateXcxModuleCoverByModule(xcxModuleCover));
        }
    }

    @GetMapping("/getCoverByModule")
    public AjaxResult getCoverByModule(String module)
    {
        return AjaxResult.success(xcxModuleCoverService.selectXcxModuleCoverByModule(module));
    }

    /**
     * 修改模块封面
     */
    //@PreAuthorize("@ss.hasPermi('xcx:cover:edit')")
    @Log(title = "模块封面", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XcxModuleCover xcxModuleCover)
    {
        return toAjax(xcxModuleCoverService.updateXcxModuleCover(xcxModuleCover));
    }

    /**
     * 删除模块封面
     */
    //@PreAuthorize("@ss.hasPermi('xcx:cover:remove')")
    @Log(title = "模块封面", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xcxModuleCoverService.deleteXcxModuleCoverByIds(ids));
    }
}

