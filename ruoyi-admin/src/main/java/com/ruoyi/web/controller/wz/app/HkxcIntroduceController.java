package com.ruoyi.web.controller.wz.app;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.app.domain.HkxcIntroduce;
import com.ruoyi.app.service.IHkxcIntroduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 航空新城-介绍管理Controller
 *
 * @author ruoyi
 * @date 2024-05-07
 */
@RestController
@RequestMapping("/hkxcIntroduce/introduce")
public class HkxcIntroduceController extends BaseController
{
    @Autowired
    private IHkxcIntroduceService hkxcIntroduceService;

    /**
     * 查询航空新城-介绍管理列表
     */
    //@PreAuthorize("@ss.hasPermi('hkxcIntroduce:introduce:list')")
    @GetMapping("/list")
    public TableDataInfo list(HkxcIntroduce hkxcIntroduce)
    {
        startPage();
        List<HkxcIntroduce> list = hkxcIntroduceService.selectHkxcIntroduceList(hkxcIntroduce);
        return getDataTable(list);
    }

    /**
     * 导出航空新城-介绍管理列表
     */
    //@PreAuthorize("@ss.hasPermi('hkxcIntroduce:introduce:export')")
    @Log(title = "航空新城-介绍管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HkxcIntroduce hkxcIntroduce)
    {
        List<HkxcIntroduce> list = hkxcIntroduceService.selectHkxcIntroduceList(hkxcIntroduce);
        ExcelUtil<HkxcIntroduce> util = new ExcelUtil<HkxcIntroduce>(HkxcIntroduce.class);
        util.exportExcel(response, list, "航空新城-介绍管理数据");
    }

    /**
     * 获取航空新城-介绍管理详细信息
     */
    //@PreAuthorize("@ss.hasPermi('hkxcIntroduce:introduce:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(hkxcIntroduceService.selectHkxcIntroduceById(id));
    }

    /**
     * 新增航空新城-介绍管理
     */
    //@PreAuthorize("@ss.hasPermi('hkxcIntroduce:introduce:add')")
    @Log(title = "航空新城-介绍管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HkxcIntroduce hkxcIntroduce)
    {
        return toAjax(hkxcIntroduceService.insertHkxcIntroduce(hkxcIntroduce));
    }

    /**
     * 修改航空新城-介绍管理
     */
    //@PreAuthorize("@ss.hasPermi('hkxcIntroduce:introduce:edit')")
    @Log(title = "航空新城-介绍管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HkxcIntroduce hkxcIntroduce)
    {
        return toAjax(hkxcIntroduceService.updateHkxcIntroduce(hkxcIntroduce));
    }

    /**
     * 删除航空新城-介绍管理
     */
    //@PreAuthorize("@ss.hasPermi('hkxcIntroduce:introduce:remove')")
    @Log(title = "航空新城-介绍管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(hkxcIntroduceService.deleteHkxcIntroduceByIds(ids));
    }
}

