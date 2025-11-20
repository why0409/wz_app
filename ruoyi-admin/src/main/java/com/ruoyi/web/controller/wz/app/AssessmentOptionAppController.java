package com.ruoyi.web.controller.wz.app;

import com.ruoyi.app.domain.AssessmentOption;
import com.ruoyi.app.service.IAssessmentOptionService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 测评选项Controller
 * * @author wanghongyu
 *
 * @date 2025-11-04
 */
@Api(tags = "测评选项接口-小程序端")
@RestController
@RequestMapping("/applet/assessment/optionApp")
public class AssessmentOptionAppController extends BaseController {
    @Autowired
    private IAssessmentOptionService assessmentOptionService;

    /**
     * 查询测评选项列表
     */
//    @PreAuthorize("@ss.hasPermi('assessment:option:list')")
//    @ApiOperation("查询测评选项列表")
    @GetMapping("/list")
    public TableDataInfo list(AssessmentOption assessmentOption) {
        startPage();
        List<AssessmentOption> list = assessmentOptionService.selectAssessmentOptionList(assessmentOption);
        return getDataTable(list);
    }

    /**
     * 查询所有可用的选项 (用于配置)
     */
//    @PreAuthorize("@ss.hasPermi('assessment:activity:config')")
    @ApiOperation("查询所有可用的测评选项(用于配置)")
    @GetMapping("/listAll")
    public AjaxResult listAll() {
        List<AssessmentOption> list = assessmentOptionService.selectAllAvailableOptions();
        return success(list);
    }

    /**
     * 导出测评选项列表
     */
//    @PreAuthorize("@ss.hasPermi('assessment:option:export')")
//    @ApiOperation("导出测评选项列表")
    @Log(title = "测评选项", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AssessmentOption assessmentOption) {
        List<AssessmentOption> list = assessmentOptionService.selectAssessmentOptionList(assessmentOption);
        ExcelUtil<AssessmentOption> util = new ExcelUtil<AssessmentOption>(AssessmentOption.class);
        util.exportExcel(response, list, "测评选项数据");
    }

    /**
     * 获取测评选项详细信息
     */
//    @PreAuthorize("@ss.hasPermi('assessment:option:query')")
    @ApiOperation("获取测评选项详细信息")
    @GetMapping(value = "/{optionId}")
    public AjaxResult getInfo(@PathVariable("optionId") Long optionId) {
        return success(assessmentOptionService.getById(optionId));
    }

    /**
     * 新增测评选项
     */
//    @PreAuthorize("@ss.hasPermi('assessment:option:add')")
//    @ApiOperation("新增测评选项")
    @Log(title = "测评选项", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AssessmentOption assessmentOption) {
        assessmentOption.setCreateBy(getUsername());
        return toAjax(assessmentOptionService.save(assessmentOption));
    }

    /**
     * 修改测评选项
     */
//    @PreAuthorize("@ss.hasPermi('assessment:option:edit')")
//    @ApiOperation("修改测评选项")
    @Log(title = "测评选项", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AssessmentOption assessmentOption) {
        assessmentOption.setUpdateBy(getUsername());
        return toAjax(assessmentOptionService.updateById(assessmentOption));
    }

    /**
     * 删除测评选项
     */
//    @PreAuthorize("@ss.hasPermi('assessment:option:remove')")
//    @ApiOperation("删除测评选项")
    @Log(title = "测评选项", businessType = BusinessType.DELETE)
    @DeleteMapping("/{optionIds}")
    public AjaxResult remove(@PathVariable Long[] optionIds) {
        return toAjax(assessmentOptionService.removeByIds(Arrays.asList(optionIds)));
    }

    /**
     * 导入干部数据
     */
//    @PreAuthorize("@ss.hasPermi('assessment:option:import')")
//    @ApiOperation("导入测评选项数据")
    @Log(title = "测评选项", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<AssessmentOption> util = new ExcelUtil<>(AssessmentOption.class);
        List<AssessmentOption> optionList = util.importExcel(file.getInputStream());
        String operName = getUsername();
//        String operName = "wzqadmin";
        String message = assessmentOptionService.importOptions(optionList, updateSupport, operName);
        return success(message);
    }
}