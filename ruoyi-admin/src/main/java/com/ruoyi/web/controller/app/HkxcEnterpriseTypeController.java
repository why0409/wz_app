package com.ruoyi.web.controller.app;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.app.domain.HkxcEnterpriseType;
import com.ruoyi.app.service.IHkxcEnterpriseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 航空新城-企业分类Controller
 *
 * @author ruoyi
 * @date 2024-05-07
 */
@RestController
@RequestMapping("/hkxcEnterpriseType/type")
public class HkxcEnterpriseTypeController extends BaseController
{
    @Autowired
    private IHkxcEnterpriseTypeService hkxcEnterpriseTypeService;

    /**
     * 查询航空新城-企业分类列表
     */
    //@PreAuthorize("@ss.hasPermi('hkxcEnterpriseType:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(HkxcEnterpriseType hkxcEnterpriseType)
    {
        startPage();
        List<HkxcEnterpriseType> list = hkxcEnterpriseTypeService.selectHkxcEnterpriseTypeList(hkxcEnterpriseType);
        return getDataTable(list);
    }

    /**
     * 导出航空新城-企业分类列表
     */
    //@PreAuthorize("@ss.hasPermi('hkxcEnterpriseType:type:export')")
    @Log(title = "航空新城-企业分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HkxcEnterpriseType hkxcEnterpriseType)
    {
        List<HkxcEnterpriseType> list = hkxcEnterpriseTypeService.selectHkxcEnterpriseTypeList(hkxcEnterpriseType);
        ExcelUtil<HkxcEnterpriseType> util = new ExcelUtil<HkxcEnterpriseType>(HkxcEnterpriseType.class);
        util.exportExcel(response, list, "航空新城-企业分类数据");
    }

    /**
     * 获取航空新城-企业分类详细信息
     */
    //@PreAuthorize("@ss.hasPermi('hkxcEnterpriseType:type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(hkxcEnterpriseTypeService.selectHkxcEnterpriseTypeById(id));
    }

    /**
     * 新增航空新城-企业分类
     */
    //@PreAuthorize("@ss.hasPermi('hkxcEnterpriseType:type:add')")
    @Log(title = "航空新城-企业分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HkxcEnterpriseType hkxcEnterpriseType)
    {
        return toAjax(hkxcEnterpriseTypeService.insertHkxcEnterpriseType(hkxcEnterpriseType));
    }

    /**
     * 修改航空新城-企业分类
     */
    //@PreAuthorize("@ss.hasPermi('hkxcEnterpriseType:type:edit')")
    @Log(title = "航空新城-企业分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HkxcEnterpriseType hkxcEnterpriseType)
    {
        return toAjax(hkxcEnterpriseTypeService.updateHkxcEnterpriseType(hkxcEnterpriseType));
    }

    /**
     * 删除航空新城-企业分类
     */
    //@PreAuthorize("@ss.hasPermi('hkxcEnterpriseType:type:remove')")
    @Log(title = "航空新城-企业分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(hkxcEnterpriseTypeService.deleteHkxcEnterpriseTypeByIds(ids));
    }
}

