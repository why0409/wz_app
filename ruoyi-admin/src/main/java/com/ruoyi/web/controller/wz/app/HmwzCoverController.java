package com.ruoyi.web.controller.wz.app;


import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.app.domain.HmwzCover;
import com.ruoyi.app.service.IHmwzCoverService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 和美湾沚-封面管理Controller
 *
 * @author ruoyi
 * @date 2024-05-06
 */
@RestController
@RequestMapping("/hmwz/cover")
public class HmwzCoverController extends BaseController
{
    @Autowired
    private IHmwzCoverService hmwzCoverService;

    /**
     * 查询和美湾沚-封面管理列表
     */
    //@PreAuthorize("@ss.hasPermi('hmwz:cover:list')")
    @GetMapping("/list")
    public TableDataInfo list(HmwzCover hmwzCover)
    {
        startPage();
        List<HmwzCover> list = hmwzCoverService.selectHmwzCoverList(hmwzCover);
        return getDataTable(list);
    }

    /**
     * 导出和美湾沚-封面管理列表
     */
    //@PreAuthorize("@ss.hasPermi('hmwz:cover:export')")
    @Log(title = "和美湾沚-封面管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HmwzCover hmwzCover)
    {
        List<HmwzCover> list = hmwzCoverService.selectHmwzCoverList(hmwzCover);
        ExcelUtil<HmwzCover> util = new ExcelUtil<HmwzCover>(HmwzCover.class);
        util.exportExcel(response, list, "和美湾沚-封面管理数据");
    }

    /**
     * 获取和美湾沚-封面管理详细信息
     */
    //@PreAuthorize("@ss.hasPermi('hmwz:cover:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(hmwzCoverService.selectHmwzCoverById(id));
    }

    /**
     * 新增和美湾沚-封面管理
     */
    //@PreAuthorize("@ss.hasPermi('hmwz:cover:add')")
    @Log(title = "和美湾沚-封面管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HmwzCover hmwzCover)
    {
        return toAjax(hmwzCoverService.insertHmwzCover(hmwzCover));
    }

    /**
     * 修改和美湾沚-封面管理
     */
    //@PreAuthorize("@ss.hasPermi('hmwz:cover:edit')")
    @Log(title = "和美湾沚-封面管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HmwzCover hmwzCover)
    {
        return toAjax(hmwzCoverService.updateHmwzCover(hmwzCover));
    }

    /**
     * 删除和美湾沚-封面管理
     */
    //@PreAuthorize("@ss.hasPermi('hmwz:cover:remove')")
    @Log(title = "和美湾沚-封面管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(hmwzCoverService.deleteHmwzCoverByIds(ids));
    }
}

