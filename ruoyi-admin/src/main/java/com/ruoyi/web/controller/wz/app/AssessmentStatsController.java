package com.ruoyi.web.controller.wz.app;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.ruoyi.app.domain.dto.AssessmentStatsDTO;
import com.ruoyi.app.service.IAssessmentStatsService;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Log;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

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
     * 获取统计结果 (单活动)
     */
    @ApiOperation("获取测评统计结果")
    @GetMapping("/{activityId:[0-9]+}")
    public AjaxResult getStats(@PathVariable("activityId") Long activityId) {
        AssessmentStatsDTO statsDTO = statsService.getStats(activityId);
        return success(statsDTO);
    }

    /**
     * 导入历史测评数据
     */
    @ApiOperation("导入历史测评数据")
    @Log(title = "测评统计", businessType = BusinessType.IMPORT)
    @PostMapping("/importHistory")
    public AjaxResult importHistory(MultipartFile file,
                                    @RequestParam("year") String year,
                                    @RequestParam("type") String type) throws Exception {
        String operName = null;
        try {
            operName = getUsername();
        } catch (Exception e) {
            operName = "admin";
        }
        String message = statsService.importHistoryData(file, year, type, operName);
        return success(message);
    }

    /**
     * 获取年份区间统计结果
     */
    @ApiOperation("获取年份区间测评统计结果")
    @GetMapping("/summary")
    public AjaxResult getStatsByYearRange(
            @RequestParam("startYear") String startYear,
            @RequestParam("endYear") String endYear,
            @RequestParam(value = "cadreName", required = false) String cadreName) {
        AssessmentStatsDTO statsDTO = statsService.getStatsByYearRange(startYear, endYear, cadreName);
        return success(statsDTO);
    }

    /**
     * 导出统计结果 (单活动) - 保持原有逻辑
     */
    @ApiOperation("导出测评统计结果")
    @Log(title = "统计结果", businessType = BusinessType.EXPORT)
    @PostMapping("/export/{activityId:[0-9]+}")
    public AjaxResult export(HttpServletResponse response, @PathVariable("activityId") Long activityId) throws IOException {
        AssessmentStatsDTO statsDTO = statsService.getStats(activityId);
        return exportExcel(statsDTO, "测评统计数据");
    }

    /**
     * 导出年份区间统计结果 (直接流式输出，去除时间戳前缀)
     */
    @ApiOperation("导出年份区间测评统计结果")
    @Log(title = "统计结果", businessType = BusinessType.EXPORT)
    @PostMapping("/export/summary")
    public void exportSummary(HttpServletResponse response,
                              @RequestParam("startYear") String startYear,
                              @RequestParam("endYear") String endYear,
                              @RequestParam(value = "cadreName", required = false) String cadreName) throws IOException {
        AssessmentStatsDTO statsDTO = statsService.getStatsByYearRange(startYear, endYear, cadreName);
        // 使用流式导出，直接响应给浏览器
        exportMatrixEasyExcelStream(response, statsDTO, startYear, endYear);
    }

    /**
     * 使用 EasyExcel 直接写入 Response 流
     */
    private void exportMatrixEasyExcelStream(HttpServletResponse response, AssessmentStatsDTO statsDTO, String startYear, String endYear) throws IOException {
        // 1. 构造文件名
        String yearText = startYear.equals(endYear) ? startYear : startYear + "-" + endYear;
        String filename = yearText + "年度综合考核领导干部政治素质评价汇总表.xlsx";

        // 2. 设置响应头
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String encodedFileName = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-Disposition", "attachment; filename=" + encodedFileName);

        // 3. 定义Sheet标题
        String positiveTitle = yearText + "年度综合考核领导干部政治素质正面评价汇总表";
        String negativeTitle = yearText + "年度综合考核领导干部政治素质负面评价汇总表";

        // 4. 配置样式策略
        HorizontalCellStyleStrategy styleStrategy = getStyleStrategy();

        // 5. 使用 EasyExcel 写入 Response 输出流
        try (ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).registerWriteHandler(styleStrategy).build()) {

            // --- Sheet 1: 正面评价 ---
            MatrixData positiveData = prepareMatrixData(statsDTO, true, positiveTitle);
            WriteSheet writeSheet1 = EasyExcel.writerSheet(0, "正面评价")
                    .head(positiveData.getHeads())
                    .build();
            excelWriter.write(positiveData.getRows(), writeSheet1);

            // --- Sheet 2: 负面评价 ---
            MatrixData negativeData = prepareMatrixData(statsDTO, false, negativeTitle);
            WriteSheet writeSheet2 = EasyExcel.writerSheet(1, "负面评价")
                    .head(negativeData.getHeads())
                    .build();
            excelWriter.write(negativeData.getRows(), writeSheet2);
        }
    }

    /**
     * 准备矩阵数据 (表头 + 数据行)
     * 修改说明：逻辑已更新，不再根据票数动态排序，而是直接使用 Service 层返回的有序、完整的选项列表
     */
    private MatrixData prepareMatrixData(AssessmentStatsDTO stats, boolean isPositive, String mainTitle) {
        // 1. 获取干部统计列表
        List<AssessmentStatsDTO.CadreStat> cadres = stats.getCadreStats();
        if (cadres == null) cadres = new ArrayList<>();

        // 2. 确定表头列（选项）
        // 由于 Service 层已经保证了：
        // 1. 每个干部的 options 列表都包含所有标准选项（包含0票的）
        // 2. options 列表顺序一致（按后台 order_num 排序）
        // 因此，我们只需直接取第一条数据的选项列表作为表头即可
        List<String> dynamicOptions = new ArrayList<>();
        if (!cadres.isEmpty()) {
            // 取第一个干部的选项列表来定义列结构
            List<AssessmentStatsDTO.OptionVote> sampleVotes = isPositive ?
                    cadres.get(0).getPositiveResults() :
                    cadres.get(0).getNegativeResults();

            if (sampleVotes != null) {
                for (AssessmentStatsDTO.OptionVote v : sampleVotes) {
                    dynamicOptions.add(v.getContent());
                }
            }
        }

        // 3. 构建 Excel 表头 List<List<String>>
        List<List<String>> heads = new ArrayList<>();

        // 固定列：大标题 -> 字段名
        heads.add(Arrays.asList(mainTitle, "姓名"));
        heads.add(Arrays.asList(mainTitle, "单位"));
        heads.add(Arrays.asList(mainTitle, "职务"));

        // 动态列：大标题 -> 选项名
        for (String option : dynamicOptions) {
            heads.add(Arrays.asList(mainTitle, option));
        }

        // 4. 构建数据行 List<List<Object>>
        List<List<Object>> rows = new ArrayList<>();
        for (AssessmentStatsDTO.CadreStat cadre : cadres) {
            List<Object> row = new ArrayList<>();
            // 固定列数据
            row.add(cadre.getCadreName());
            row.add(cadre.getUnitName());
            row.add(cadre.getPostTitle());

            // 动态列数据
            // 直接遍历该干部的结果列表，因为顺序已经由 Service 保证与 dynamicOptions 一致
            List<AssessmentStatsDTO.OptionVote> votes = isPositive ?
                    cadre.getPositiveResults() :
                    cadre.getNegativeResults();

            if (votes != null) {
                for (AssessmentStatsDTO.OptionVote v : votes) {
                    Long count = v.getVotes();
                    // 有票数显示数字，无票数显示空字符串 (视觉上更干净，如果需要显示0可去掉判断)
                    row.add(count != null && count > 0 ? count : "");
                }
            } else {
                // 异常防御：如果该行没有数据列表，补空格
                for (int i = 0; i < dynamicOptions.size(); i++) {
                    row.add("");
                }
            }
            rows.add(row);
        }

        return new MatrixData(heads, rows);
    }

    /**
     * 定义 EasyExcel 样式策略
     */
    private HorizontalCellStyleStrategy getStyleStrategy() {
        // 表头样式
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontName("宋体");
        headWriteFont.setFontHeightInPoints((short) 12);
        headWriteFont.setBold(true);
        headWriteCellStyle.setWriteFont(headWriteFont);
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        headWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // 内容样式
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        WriteFont contentWriteFont = new WriteFont();
        contentWriteFont.setFontName("宋体");
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        return new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
    }

    /**
     * 内部数据结构类
     */
    @Data
    private static class MatrixData {
        private List<List<String>> heads;
        private List<List<Object>> rows;

        public MatrixData(List<List<String>> heads, List<List<Object>> rows) {
            this.heads = heads;
            this.rows = rows;
        }
    }

    /**
     * 提取公共导出逻辑 (旧接口保持不变)
     */
    private AjaxResult exportExcel(AssessmentStatsDTO statsDTO, String sheetName) {
        List<StatsExportVO> exportData = convertToExportData(statsDTO);
        ExcelUtil<StatsExportVO> util = new ExcelUtil<>(StatsExportVO.class);
        util.init(exportData, sheetName, StringUtils.EMPTY, Excel.Type.EXPORT);
        AjaxResult ajaxResult = util.exportExcel();
        String filename = (String) ajaxResult.get("msg");
        return AjaxResult.success(filename);
    }

    private List<StatsExportVO> convertToExportData(AssessmentStatsDTO statsDTO) {
        List<StatsExportVO> result = new ArrayList<>();
        if (statsDTO.getCadreStats() != null) {
            for (AssessmentStatsDTO.CadreStat cadreStat : statsDTO.getCadreStats()) {
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