package com.ruoyi.web.controller.wz.app;


import com.ruoyi.app.domain.HkxcEnterpriseList;
import com.ruoyi.app.service.IHkxcEnterpriseListService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 航空新城-企业列表Controller
 *
 * @author ruoyi
 * @date 2024-05-07
 */
@RestController
@RequestMapping("/hkxcEnterpriseList/list")
public class HkxcEnterpriseListController extends BaseController
{
    @Autowired
    private IHkxcEnterpriseListService hkxcEnterpriseListService;

    /**
     * 查询航空新城-企业列表列表
     */
    //@PreAuthorize("@ss.hasPermi('hkxcEnterpriseList:list:list')")
    @GetMapping("/list")
    public TableDataInfo list(HkxcEnterpriseList hkxcEnterpriseList)
    {
        startPage();
        List<HkxcEnterpriseList> list = hkxcEnterpriseListService.selectHkxcEnterpriseListList(hkxcEnterpriseList);
        return getDataTable(list);
    }

    /**
     * 导出航空新城-企业列表列表
     */
    //@PreAuthorize("@ss.hasPermi('hkxcEnterpriseList:list:export')")
    @Log(title = "航空新城-企业列", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HkxcEnterpriseList hkxcEnterpriseList)
    {
        List<HkxcEnterpriseList> list = hkxcEnterpriseListService.selectHkxcEnterpriseListList(hkxcEnterpriseList);
        ExcelUtil<HkxcEnterpriseList> util = new ExcelUtil<HkxcEnterpriseList>(HkxcEnterpriseList.class);
        util.exportExcel(response, list, "航空新城-企业列数据");
    }

    /**
     * 获取航空新城-企业列表详细信息
     */
    //@PreAuthorize("@ss.hasPermi('hkxcEnterpriseList:list:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(hkxcEnterpriseListService.selectHkxcEnterpriseListById(id));
    }

    /**
     * 新增航空新城-企业列表
     */
    //@PreAuthorize("@ss.hasPermi('hkxcEnterpriseList:list:add')")
    @Log(title = "航空新城-企业列表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HkxcEnterpriseList hkxcEnterpriseList)
    {
        return toAjax(hkxcEnterpriseListService.insertHkxcEnterpriseList(hkxcEnterpriseList));
    }

    /**
     * 修改航空新城-企业列表
     */
    //@PreAuthorize("@ss.hasPermi('hkxcEnterpriseList:list:edit')")
    @Log(title = "航空新城-企业列表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HkxcEnterpriseList hkxcEnterpriseList)
    {
        return toAjax(hkxcEnterpriseListService.updateHkxcEnterpriseList(hkxcEnterpriseList));
    }

    /**
     * 删除航空新城-企业列表
     */
    //@PreAuthorize("@ss.hasPermi('hkxcEnterpriseList:list:remove')")
    @Log(title = "航空新城-企业列表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(hkxcEnterpriseListService.deleteHkxcEnterpriseListByIds(ids));
    }
}
