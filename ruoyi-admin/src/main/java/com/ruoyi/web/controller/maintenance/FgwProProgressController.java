package com.ruoyi.web.controller.maintenance;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.FgwProProgress;
import com.ruoyi.system.domain.vo.FgwProProgressDto;
import com.ruoyi.system.service.IFgwProProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 项目进度Controller
 * 
 * @author ruoyi
 * @date 2022-11-24
 */
@RestController
@RequestMapping("/system/pro_gress")
public class FgwProProgressController extends BaseController
{
    @Autowired
    private IFgwProProgressService fgwProProgressService;

    /**
     * 查询项目进度列表
     */
    //@PreAuthorize("@ss.hasPermi('system:pro_gress:list')")
    @GetMapping("/list")
    public TableDataInfo list(FgwProProgressDto fgwProProgressDto)
    {
        startPage();
        List<FgwProProgressDto> list = fgwProProgressService.selectFgwProProgressList(fgwProProgressDto);
        return getDataTable(list);
    }

    /**
     * 导出项目进度列表
     */
    //@PreAuthorize("@ss.hasPermi('system:pro_gress:export')")
    @Log(title = "项目进度", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FgwProProgressDto fgwProProgressDto)
    {
        List<FgwProProgressDto> list = fgwProProgressService.selectFgwProProgressList(fgwProProgressDto);
        ExcelUtil<FgwProProgressDto> util = new ExcelUtil<FgwProProgressDto>(FgwProProgressDto.class);
        util.exportExcel(response, list, "项目进度数据");
    }

    /**
     * 获取项目进度详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:pro_gress:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(fgwProProgressService.selectFgwProProgressById(id));
    }

    /**
     * 新增项目进度
     */
    //@PreAuthorize("@ss.hasPermi('system:pro_gress:add')")
    @Log(title = "项目进度", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FgwProProgressDto fgwProProgressDto)
    {
        return toAjax(fgwProProgressService.insertFgwProProgress(fgwProProgressDto));
    }

    /**
     * 修改项目进度
     */
    //@PreAuthorize("@ss.hasPermi('system:pro_gress:edit')")
    @Log(title = "项目进度", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FgwProProgressDto fgwProProgressDto)
    {
        return toAjax(fgwProProgressService.updateFgwProProgress(fgwProProgressDto));
    }

    /**
     * 删除项目进度
     */
    //@PreAuthorize("@ss.hasPermi('system:pro_gress:remove')")
    @Log(title = "项目进度", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(fgwProProgressService.deleteFgwProProgressByIds(ids));
    }

    /**
     * 根据id更新红黄绿标识
     * @author:
     * @date: 2022/11/24 15:29
     * @param fgwProProgress
     * @return
     */
    @RequestMapping("/updateHhlTypeById")
    public AjaxResult updateHhlTypeById(@RequestBody FgwProProgress fgwProProgress)
    {
        return toAjax(fgwProProgressService.updateHhlTypeById(fgwProProgress));
    }
}
