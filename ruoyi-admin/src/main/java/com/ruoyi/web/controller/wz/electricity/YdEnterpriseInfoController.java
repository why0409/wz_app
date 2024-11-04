package com.ruoyi.web.controller.wz.electricity;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.electricity.domain.YdEnterpriseInfo;
import com.ruoyi.electricity.service.IYdEnterpriseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用电企业信息Controller
 *
 * @author ruoyi
 * @date 2024-11-04
 */
@RestController
@RequestMapping("/yd/enterprise/info")
public class YdEnterpriseInfoController extends BaseController
{
    @Autowired
    private IYdEnterpriseInfoService ydEnterpriseInfoService;

    /**
     * 查询用电企业信息列表
     */
    //@PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(YdEnterpriseInfo ydEnterpriseInfo)
    {
        startPage();
        List<YdEnterpriseInfo> list = ydEnterpriseInfoService.selectYdEnterpriseInfoList(ydEnterpriseInfo);
        return getDataTable(list);
    }

    /**
     * 导出用电企业信息列表
     */
    //@PreAuthorize("@ss.hasPermi('system:info:export')")
    //@Log(title = "用电企业信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YdEnterpriseInfo ydEnterpriseInfo)
    {
        List<YdEnterpriseInfo> list = ydEnterpriseInfoService.selectYdEnterpriseInfoList(ydEnterpriseInfo);
        ExcelUtil<YdEnterpriseInfo> util = new ExcelUtil<YdEnterpriseInfo>(YdEnterpriseInfo.class);
        util.exportExcel(response, list, "用电企业信息数据");
    }

    /**
     * 获取用电企业信息详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(ydEnterpriseInfoService.selectYdEnterpriseInfoById(id));
    }

    /**
     * 新增用电企业信息
     */
    //@PreAuthorize("@ss.hasPermi('system:info:add')")
    //@Log(title = "用电企业信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YdEnterpriseInfo ydEnterpriseInfo)
    {
        return toAjax(ydEnterpriseInfoService.insertYdEnterpriseInfo(ydEnterpriseInfo));
    }

    /**
     * 修改用电企业信息
     */
    //@PreAuthorize("@ss.hasPermi('system:info:edit')")
    //@Log(title = "用电企业信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YdEnterpriseInfo ydEnterpriseInfo)
    {
        return toAjax(ydEnterpriseInfoService.updateYdEnterpriseInfo(ydEnterpriseInfo));
    }

    /**
     * 删除用电企业信息
     */
    //@PreAuthorize("@ss.hasPermi('system:info:remove')")
    //@Log(title = "用电企业信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ydEnterpriseInfoService.deleteYdEnterpriseInfoByIds(ids));
    }
}
