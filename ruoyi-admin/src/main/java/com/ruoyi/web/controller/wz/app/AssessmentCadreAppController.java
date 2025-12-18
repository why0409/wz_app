package com.ruoyi.web.controller.wz.app;

import com.ruoyi.app.domain.AssessmentCadre;
import com.ruoyi.app.service.IAssessmentCadreService;
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
 * 被测评干部Controller
 * * @author wanghongyu
 *
 * @date 2025-11-04
 */
@Api(tags = "被测评干部接口-小程序端")
@RestController
@RequestMapping("/applet/assessment/cadreApp")
public class AssessmentCadreAppController extends BaseController {
    @Autowired
    private IAssessmentCadreService assessmentCadreService;

    /**
     * 查询被测评干部列表
     */
//    @PreAuthorize("@ss.hasPermi('assessment:cadre:list')")
    @ApiOperation("查询被测评干部列表")
    @GetMapping("/list")
    public TableDataInfo list(AssessmentCadre assessmentCadre) {
        startPage();
        List<AssessmentCadre> list = assessmentCadreService.selectAssessmentCadreList(assessmentCadre);
        return getDataTable(list);
    }

    /**
     * 导出被测评干部列表
     */
//    @PreAuthorize("@ss.hasPermi('assessment:cadre:export')")
//    @ApiOperation("导出被测评干部列表")
    @Log(title = "被测评干部", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AssessmentCadre assessmentCadre) {
        List<AssessmentCadre> list = assessmentCadreService.selectAssessmentCadreList(assessmentCadre);
        ExcelUtil<AssessmentCadre> util = new ExcelUtil<AssessmentCadre>(AssessmentCadre.class);
        util.exportExcel(response, list, "被测评干部数据");
    }

    /**
     * 获取被测评干部详细信息
     */
//    @PreAuthorize("@ss.hasPermi('assessment:cadre:query')")
//    @ApiOperation("获取被测评干部详细信息")
    @GetMapping(value = "/{cadreId}")
    public AjaxResult getInfo(@PathVariable("cadreId") Long cadreId) {
        return success(assessmentCadreService.getById(cadreId));
    }

    /**
     * 新增被测评干部
     */
//    @PreAuthorize("@ss.hasPermi('assessment:cadre:add')")
//    @ApiOperation("新增被测评干部")
    @Log(title = "被测评干部", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AssessmentCadre assessmentCadre) {
        assessmentCadre.setCreateBy(getUsername());
        return toAjax(assessmentCadreService.save(assessmentCadre));
    }

    /**
     * 修改被测评干部
     */
//    @PreAuthorize("@ss.hasPermi('assessment:cadre:edit')")
//    @ApiOperation("修改被测评干部")
    @Log(title = "被测评干部", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AssessmentCadre assessmentCadre) {
        assessmentCadre.setUpdateBy(getUsername());
        return toAjax(assessmentCadreService.updateById(assessmentCadre));
    }

    /**
     * 删除被测评干部
     */
//    @PreAuthorize("@ss.hasPermi('assessment:cadre:remove')")
//    @ApiOperation("删除被测评干部")
    @Log(title = "被测评干部", businessType = BusinessType.DELETE)
    @DeleteMapping("/{cadreIds}")
    public AjaxResult remove(@PathVariable Long[] cadreIds) {
        return toAjax(assessmentCadreService.removeByIds(Arrays.asList(cadreIds)));
    }

    /**
     * 导入干部数据
     */
//    @PreAuthorize("@ss.hasPermi('assessment:cadre:import')")
//    @ApiOperation("导入干部数据")
    @Log(title = "干部管理", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<AssessmentCadre> util = new ExcelUtil<AssessmentCadre>(AssessmentCadre.class);
        List<AssessmentCadre> cadreList = util.importExcel(file.getInputStream(), 1);
        String operName = getUsername();
        String message = assessmentCadreService.importCadres(cadreList, updateSupport, operName);
        return success(message);
    }
}