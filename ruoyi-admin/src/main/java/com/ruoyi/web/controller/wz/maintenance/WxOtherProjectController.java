package com.ruoyi.web.controller.wz.maintenance;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.OtherProject;
import com.ruoyi.system.service.IWxOtherProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/system/other/project")
@Api(tags = "ruoyi-其他项目相关接口")
public class WxOtherProjectController extends BaseController {
    @Autowired
    private IWxOtherProjectService otherProjectService;

    /**
     * 查询其他项目简介列表
     */
    //@PreAuthorize("@ss.hasPermi('system:weather:list')")
    @GetMapping("/list")
    @ApiOperation("查询其他项目列表")
    public TableDataInfo list(OtherProject otherProject) {
        startPage();
        List<OtherProject> list = otherProjectService.selectOtherProjectList(otherProject);
        return getDataTable(list);
    }

    /**
     * 导出其他项目简介列表
     */
    //@PreAuthorize("@ss.hasPermi('system:weather:export')")
    @Log(title = "其他项目简介", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("导出其他项目列表")
    public void export(HttpServletResponse response, OtherProject otherProject) {
        List<OtherProject> list = otherProjectService.selectOtherProjectList(otherProject);
        ExcelUtil<OtherProject> util = new ExcelUtil<OtherProject>(OtherProject.class);
        util.exportExcel(response, list, "其他项目数据");
    }

    /**
     * 获取其他项目简介详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:weather:query')")
    @GetMapping(value = "/{uuid}")
    @ApiOperation("获取其他项目详细信息")
    public AjaxResult getInfo(@PathVariable("uuid") String uuid) {
        return success(otherProjectService.selectOtherProjectByUuid(uuid));
    }

    /**
     * 新增其他项目简介
     */
    //@PreAuthorize("@ss.hasPermi('system:weather:add')")
    @Log(title = "其他项目简介", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增其他项目")
    public AjaxResult add(@RequestBody OtherProject otherProject) {
        UUID uuid = UUID.randomUUID();
        otherProject.setUuid(uuid.toString());
        otherProject.setPublishTime(new Date());
        //int checkSortNum = OtherProjectMapper.checkSortNum(OtherProject.getSortNum());
        //return checkSortNum == 0 ? toAjax(OtherProjectService.insertOtherProject(OtherProject)) : AjaxResult.error("已存在重复的序号，请修改！");

        return toAjax(otherProjectService.insertOtherProject(otherProject));
    }

    /**
     * 修改其他项目简介
     */
    //@PreAuthorize("@ss.hasPermi('system:weather:edit')")
    @Log(title = "其他项目简介", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改其他项目")
    public AjaxResult edit(@RequestBody OtherProject otherProject) {
        otherProject.setUpdateTime(new Date());
        //OtherProject w = OtherProjectMapper.selectOtherProjectByUuid(OtherProject.getUuid());
        //int checkSortNum = OtherProjectMapper.checkSortNum(OtherProject.getSortNum());
        //if (w.getSortNum().equals(OtherProject.getSortNum())){
        //    return toAjax(OtherProjectService.updateOtherProject(OtherProject));
        //}else {
        //    return (checkSortNum == 0) ? toAjax(OtherProjectService.updateOtherProject(OtherProject)) : AjaxResult.error("已存在重复的序号，请修改！");
        //}

        return toAjax(otherProjectService.updateOtherProject(otherProject));
    }

    /**
     * 删除其他项目简介
     */
    //@PreAuthorize("@ss.hasPermi('system:weather:remove')")
    @Log(title = "其他项目简介", businessType = BusinessType.DELETE)
    @DeleteMapping("/{uuids}")
    @ApiOperation("删除其他项目")
    public AjaxResult remove(@PathVariable String[] uuids) {
        return toAjax(otherProjectService.deleteOtherProjectByUuids(uuids));
    }


}
