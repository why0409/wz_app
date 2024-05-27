package com.ruoyi.web.controller.app;

import com.ruoyi.app.domain.CszyCameraType;
import com.ruoyi.app.domain.vo.CszyCameraTypeVo;
import com.ruoyi.app.domain.vo.CszyPermissionsVo;
import com.ruoyi.app.service.ICszyCameraTypeService;
import com.ruoyi.app.service.ICszyPermissionsService;
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
 * 城市之眼-视频分类Controller
 *
 * @author ruoyi
 * @date 2024-05-24
 */
@RestController
@RequestMapping("/cszy/type")
public class CszyCameraTypeController extends BaseController
{
    @Autowired
    private ICszyCameraTypeService cszyCameraTypeService;

    @Autowired
    private ICszyPermissionsService cszyPermissionsService;

    /**
     * 查询城市之眼-视频分类列表
     */
    //@PreAuthorize("@ss.hasPermi('cszy:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(CszyCameraType cszyCameraType)
    {
        startPage();
        List<CszyCameraType> list = cszyCameraTypeService.selectCszyCameraTypeList(cszyCameraType);
        return getDataTable(list);
    }

    /**
     * 导出城市之眼-视频分类列表
     */
    //@PreAuthorize("@ss.hasPermi('cszy:type:export')")
    @Log(title = "城市之眼-视频分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CszyCameraType cszyCameraType)
    {
        List<CszyCameraType> list = cszyCameraTypeService.selectCszyCameraTypeList(cszyCameraType);
        ExcelUtil<CszyCameraType> util = new ExcelUtil<CszyCameraType>(CszyCameraType.class);
        util.exportExcel(response, list, "城市之眼-视频分类数据");
    }

    /**
     * 获取城市之眼-视频分类详细信息
     */
    //@PreAuthorize("@ss.hasPermi('cszy:type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(cszyCameraTypeService.selectCszyCameraTypeById(id));
    }

    /**
     * 新增城市之眼-视频分类
     */
    //@PreAuthorize("@ss.hasPermi('cszy:type:add')")
    @Log(title = "城市之眼-视频分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CszyCameraType cszyCameraType)
    {
        return toAjax(cszyCameraTypeService.insertCszyCameraType(cszyCameraType));
    }

    /**
     * 修改城市之眼-视频分类
     */
    //@PreAuthorize("@ss.hasPermi('cszy:type:edit')")
    @Log(title = "城市之眼-视频分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CszyCameraType cszyCameraType)
    {
        return toAjax(cszyCameraTypeService.updateCszyCameraType(cszyCameraType));
    }

    /**
     * 删除城市之眼-视频分类
     */
    //@PreAuthorize("@ss.hasPermi('cszy:type:remove')")
    @Log(title = "城市之眼-视频分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(cszyCameraTypeService.deleteCszyCameraTypeByIds(ids));
    }

    @Log(title = "批量添加城市之眼权限", businessType = BusinessType.INSERT)
    @PostMapping("/addBatchPermissions")
    public AjaxResult addBatchPermissions(@RequestBody CszyPermissionsVo cszyPermissionsVo)
    {
        return toAjax(cszyPermissionsService.addBatchPermissions(cszyPermissionsVo));
    }

    @GetMapping("/getPermissionsByPhone")
    public TableDataInfo getPermissionsByPhone(String phone)
    {
        startPage();
        List<CszyCameraTypeVo> list = cszyCameraTypeService.getPermissionsByPhone(phone);
        return getDataTable(list);
    }

}



