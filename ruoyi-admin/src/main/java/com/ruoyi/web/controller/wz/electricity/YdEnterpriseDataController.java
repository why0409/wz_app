package com.ruoyi.web.controller.wz.electricity;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.electricity.domain.YdEnterpriseData;
import com.ruoyi.electricity.service.IYdEnterpriseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用电企业数据Controller
 *
 * @author ruoyi
 * @date 2024-11-04
 */
@RestController
@RequestMapping("/yd/enterprise/data")
public class YdEnterpriseDataController extends BaseController
{
    @Autowired
    private IYdEnterpriseDataService ydEnterpriseDataService;

    /**
     * 查询用电企业数据列表
     */
    //@PreAuthorize("@ss.hasPermi('system:data:list')")
    @GetMapping("/list")
    public TableDataInfo list(YdEnterpriseData ydEnterpriseData)
    {
        startPage();
        List<YdEnterpriseData> list = ydEnterpriseDataService.selectYdEnterpriseDataList(ydEnterpriseData);
        return getDataTable(list);
    }

    /**
     * 导出用电企业数据列表
     */
    //@PreAuthorize("@ss.hasPermi('system:data:export')")
    //@Log(title = "用电企业数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YdEnterpriseData ydEnterpriseData)
    {
        List<YdEnterpriseData> list = ydEnterpriseDataService.selectYdEnterpriseDataList(ydEnterpriseData);
        ExcelUtil<YdEnterpriseData> util = new ExcelUtil<YdEnterpriseData>(YdEnterpriseData.class);
        util.exportExcel(response, list, "用电企业数据数据");
    }

    /**
     * 获取用电企业数据详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:data:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(ydEnterpriseDataService.selectYdEnterpriseDataById(id));
    }

    /**
     * 新增用电企业数据
     */
    //@PreAuthorize("@ss.hasPermi('system:data:add')")
    //@Log(title = "用电企业数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YdEnterpriseData ydEnterpriseData)
    {
        return toAjax(ydEnterpriseDataService.insertYdEnterpriseData(ydEnterpriseData));
    }

    /**
     * 修改用电企业数据
     */
    //@PreAuthorize("@ss.hasPermi('system:data:edit')")
    //@Log(title = "用电企业数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YdEnterpriseData ydEnterpriseData)
    {
        return toAjax(ydEnterpriseDataService.updateYdEnterpriseData(ydEnterpriseData));
    }

    /**
     * 删除用电企业数据
     */
    //@PreAuthorize("@ss.hasPermi('system:data:remove')")
    //@Log(title = "用电企业数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ydEnterpriseDataService.deleteYdEnterpriseDataByIds(ids));
    }

}
