package com.ruoyi.web.controller.wz.app;

import com.ruoyi.app.domain.dto.AssessmentStatsDTO;
import com.ruoyi.app.service.IAssessmentStatsService;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 统计查看Controller
 * * @author wanghongyu
 *
 * @date 2025-11-04
 */
@Api(tags = "测评统计结果接口--小程序端")
@RestController
@RequestMapping("/applet/assessment/stats")
public class AssessmentStatsController extends BaseController {
    @Autowired
    private IAssessmentStatsService statsService;

    /**
     * 获取统计结果
     */
//    @PreAuthorize("@ss.hasPermi('assessment:stats:query')")
    @ApiOperation("获取测评统计结果")
    @GetMapping("/{activityId}")
    public AjaxResult getStats(@PathVariable("activityId") Long activityId) {
        AssessmentStatsDTO statsDTO = statsService.getStats(activityId);
        return success(statsDTO);
    }

    /**
     * 导出统计结果
     * 实现统计数据的Excel导出功能
     */
//    @PreAuthorize("@ss.hasPermi('assessment:stats:export')")
    @ApiOperation("导出测评统计结果")
    @Log(title = "统计结果", businessType = BusinessType.EXPORT)
    @PostMapping("/export/{activityId}")
    public AjaxResult export(HttpServletResponse response, @PathVariable("activityId") Long activityId) throws IOException {
        // 1. 获取统计数据
        AssessmentStatsDTO statsDTO = statsService.getStats(activityId);
        
        // 2. 将统计数据转换为Excel导出格式
        List<StatsExportVO> exportData = convertToExportData(statsDTO);
        
        // 3. 使用ExcelUtil导出数据
        ExcelUtil<StatsExportVO> util = new ExcelUtil<>(StatsExportVO.class);
//        util.exportExcel(response, exportData, "测评统计数据");
        util.init(exportData, "测评统计数据", StringUtils.EMPTY, Excel.Type.EXPORT);
        AjaxResult ajaxResult = util.exportExcel();
        String filename = (String) ajaxResult.get("msg");
        String fileUrl = "/profile/download/" + filename;
        return AjaxResult.success(fileUrl);
    }
    
    /**
     * 将统计数据转换为导出格式
     */
    private List<StatsExportVO> convertToExportData(AssessmentStatsDTO statsDTO) {
        List<StatsExportVO> result = new ArrayList<>();
        
        if (statsDTO.getCadreStats() != null) {
            for (AssessmentStatsDTO.CadreStat cadreStat : statsDTO.getCadreStats()) {
                // 处理正面评价
                if (cadreStat.getPositiveResults() != null) {
                    for (AssessmentStatsDTO.OptionVote vote : cadreStat.getPositiveResults()) {
                        StatsExportVO exportVO = new StatsExportVO();
                        exportVO.setCadreName(cadreStat.getCadreName());
                        exportVO.setUnitName(cadreStat.getUnitName());
                        exportVO.setPostTitle(cadreStat.getPostTitle());
                        exportVO.setOptionContent(vote.getContent());
                        exportVO.setOptionType("正面");
                        exportVO.setVotes(vote.getVotes());
                        exportVO.setTotalParticipants(statsDTO.getTotalParticipants());
                        result.add(exportVO);
                    }
                }
                
                // 处理负面评价
                if (cadreStat.getNegativeResults() != null) {
                    for (AssessmentStatsDTO.OptionVote vote : cadreStat.getNegativeResults()) {
                        StatsExportVO exportVO = new StatsExportVO();
                        exportVO.setCadreName(cadreStat.getCadreName());
                        exportVO.setUnitName(cadreStat.getUnitName());
                        exportVO.setPostTitle(cadreStat.getPostTitle());
                        exportVO.setOptionContent(vote.getContent());
                        exportVO.setOptionType("负面");
                        exportVO.setVotes(vote.getVotes());
                        exportVO.setTotalParticipants(statsDTO.getTotalParticipants());
                        result.add(exportVO);
                    }
                }
            }
        }
        
        return result;
    }
    
    /**
     * 统计导出VO类
     */
    @Data
    public static class StatsExportVO {
        @Excel(name = "干部姓名")
        private String cadreName;
        
        @Excel(name = "单位名称")
        private String unitName;
        
        @Excel(name = "考核职务")
        private String postTitle;
        
        @Excel(name = "选项内容")
        private String optionContent;
        
        @Excel(name = "选项类型")
        private String optionType;
        
        @Excel(name = "得票数")
        private Long votes;
        
        @Excel(name = "总参与人数")
        private Long totalParticipants;

    }
}