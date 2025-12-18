package com.ruoyi.app.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.app.domain.*;
import com.ruoyi.app.domain.dto.AssessmentStatsDTO;
import com.ruoyi.app.mapper.AssessmentActivityMapper;
import com.ruoyi.app.mapper.AssessmentCadreMapper;
import com.ruoyi.app.mapper.AssessmentOptionMapper;
import com.ruoyi.app.mapper.AssessmentResultMapper;
import com.ruoyi.app.service.IAssessmentLogService;
import com.ruoyi.app.service.IAssessmentStatsService;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 统计Service业务层处理
 * * @author wanghongyu
 *
 * @date 2025-11-04
 */
@Service
public class AssessmentStatsServiceImpl implements IAssessmentStatsService {

    @Autowired
    private AssessmentResultMapper resultMapper;

    @Autowired
    private AssessmentCadreMapper cadreMapper;

    @Autowired
    private AssessmentActivityMapper activityMapper;

    @Autowired
    private AssessmentOptionMapper optionMapper;

    @Autowired
    private IAssessmentLogService logService;

    @Override
    public AssessmentStatsDTO getStats(Long activityId) {
        // 1. 统计参与人数
        long totalParticipants = logService.count(
                new LambdaQueryWrapper<AssessmentLog>().eq(AssessmentLog::getActivityId, activityId)
        );

        // 2. 查询原始统计数据
        List<Map<String, Object>> rawStats = resultMapper.selectStatsByActivityId(activityId);

        // 3. 调用统一处理逻辑（包含数据补全）
        return getStatsInternal(rawStats, totalParticipants);
    }

    /**
     * 实现跨年份统计
     */
    @Override
    public AssessmentStatsDTO getStatsByYearRange(String startYear, String endYear, String cadreName) {
        List<Map<String, Object>> rawStats = resultMapper.selectStatsByYearRange(startYear, endYear, cadreName);
        return getStatsInternal(rawStats, 0L);
    }

    /**
     * 导入历史测评数据
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String importHistoryData(MultipartFile file, String year, String type, String operName) throws Exception {
        // 1. 获取或创建测评活动
        AssessmentActivity activity = activityMapper.selectOne(new LambdaQueryWrapper<AssessmentActivity>()
                .eq(AssessmentActivity::getActivityYear, year)
                .like(AssessmentActivity::getActivityName, "历史数据")
                .last("LIMIT 1"));

        if (activity == null) {
            activity = new AssessmentActivity();
            activity.setActivityName(year + "年度历史数据补录");
            activity.setActivityYear(year);
            activity.setStatus("2"); // 已结束
            activity.setCreateBy(operName);
            activity.setCreateTime(new java.util.Date());
            activityMapper.insert(activity);
        }
        Long activityId = activity.getActivityId();

        // 2. 预加载基础数据缓存
        List<AssessmentCadre> cadreList = cadreMapper.selectList(null);
        Map<String, Long> cadreMap = cadreList.stream()
                .collect(Collectors.toMap(AssessmentCadre::getCadreName, AssessmentCadre::getCadreId, (k1, k2) -> k1));

        List<AssessmentOption> optionList = optionMapper.selectList(new LambdaQueryWrapper<AssessmentOption>()
                .eq(AssessmentOption::getOptionType, type));
        Map<String, Long> optionMap = optionList.stream()
                .collect(Collectors.toMap(AssessmentOption::getOptionContent, AssessmentOption::getOptionId, (k1, k2) -> k1));

        // 3. 读取 Excel
        HistoryImportListener listener = new HistoryImportListener(activityId, type, operName, cadreMap, optionMap, resultMapper, optionMapper, cadreMapper);
        EasyExcel.read(file.getInputStream(), listener).sheet(0).headRowNumber(2).doRead();

        return "导入成功，共处理 " + listener.getCount() + " 条记录";
    }

    // 内部私有方法：转换DTO + 补全缺失选项列
    private AssessmentStatsDTO getStatsInternal(List<Map<String, Object>> rawStats, long totalParticipants) {
        AssessmentStatsDTO statsDTO = new AssessmentStatsDTO();
        statsDTO.setTotalParticipants(totalParticipants);

        // 1. 获取所有配置的选项（作为固定列的基础）
        // 假设 AssessmentOption 有 orderNum 字段，按排序号获取，保证前端列顺序一致
        List<AssessmentOption> allOptions = optionMapper.selectList(
                new LambdaQueryWrapper<AssessmentOption>()
                        .eq(AssessmentOption::getStatus, "0")
                        .orderByAsc(AssessmentOption::getOptionId) // 确保有序
        );

        // 按类型分组并保留完整对象，以便后续获取ID和Content
        List<AssessmentOption> allPositiveOptions = allOptions.stream()
                .filter(o -> "1".equals(o.getOptionType())).collect(Collectors.toList());
        List<AssessmentOption> allNegativeOptions = allOptions.stream()
                .filter(o -> "2".equals(o.getOptionType())).collect(Collectors.toList());

        // 如果没有原始数据，也要返回空列表，但通常最好返回空行结构？这里暂保持返回空
        if (rawStats == null || rawStats.isEmpty()) {
            statsDTO.setCadreStats(new ArrayList<>());
            return statsDTO;
        }

        // 2. 按干部ID分组
        Map<Long, List<Map<String, Object>>> groupedByCadre = rawStats.stream()
                .filter(row -> row.get("cadre_id") != null)
                .collect(Collectors.groupingBy(row -> ((Number) row.get("cadre_id")).longValue()));

        List<AssessmentStatsDTO.CadreStat> cadreStatsList = new ArrayList<>();

        for (Map.Entry<Long, List<Map<String, Object>>> entry : groupedByCadre.entrySet()) {
            AssessmentStatsDTO.CadreStat cadreStat = new AssessmentStatsDTO.CadreStat();
            List<Map<String, Object>> rows = entry.getValue();
            Map<String, Object> firstRow = rows.get(0);

            cadreStat.setCadreId(((Number) firstRow.get("cadre_id")).longValue());
            cadreStat.setCadreName((String) firstRow.get("cadre_name"));
            cadreStat.setUnitName((String) firstRow.get("unit_name"));
            cadreStat.setPostTitle((String) firstRow.get("post_title"));

            // 将当前干部的实际得票转为 Map<OptionContent, OptionVote>，方便查找
            // 使用 Content 作为 Key 是因为跨年份统计时 ID 可能不一致，但名称通常一致
            Map<String, AssessmentStatsDTO.OptionVote> positiveVoteMap = new HashMap<>();
            Map<String, AssessmentStatsDTO.OptionVote> negativeVoteMap = new HashMap<>();

            for (Map<String, Object> row : rows) {
                AssessmentStatsDTO.OptionVote vote = new AssessmentStatsDTO.OptionVote();
                // 处理 ID
                if (row.get("option_id") != null) {
                    vote.setOptionId(((Number) row.get("option_id")).longValue());
                } else {
                    String content = (String) row.get("option_content");
                    vote.setOptionId(content != null ? (long) content.hashCode() : 0L);
                }
                String content = (String) row.get("option_content");
                vote.setContent(content);
                Object votesObj = row.get("votes");
                vote.setVotes(votesObj != null ? ((Number) votesObj).longValue() : 0L);

                String optionType = (String) row.get("option_type");
                if ("1".equals(optionType)) {
                    positiveVoteMap.put(content, vote);
                } else if ("2".equals(optionType)) {
                    negativeVoteMap.put(content, vote);
                }
            }

            // 3. 构建最终列表：遍历【所有标准选项】，存在则用实际数据，不存在则补0
            // 这样可以保证：所有干部的 options 列表长度一致、顺序一致、包含所有列
            cadreStat.setPositiveResults(buildFullOptionList(allPositiveOptions, positiveVoteMap));
            cadreStat.setNegativeResults(buildFullOptionList(allNegativeOptions, negativeVoteMap));

            cadreStatsList.add(cadreStat);
        }

        statsDTO.setCadreStats(cadreStatsList);
        return statsDTO;
    }

    /**
     * 辅助方法：构建完整的选项列表
     */
    private List<AssessmentStatsDTO.OptionVote> buildFullOptionList(
            List<AssessmentOption> standardOptions,
            Map<String, AssessmentStatsDTO.OptionVote> actualVotesMap) {

        List<AssessmentStatsDTO.OptionVote> result = new ArrayList<>();

        // 1. 先添加所有标准选项
        for (AssessmentOption stdOpt : standardOptions) {
            String content = stdOpt.getOptionContent();
            if (actualVotesMap.containsKey(content)) {
                // 如果有实际投票，使用实际数据
                result.add(actualVotesMap.get(content));
                // 从Map中移除，标记为已处理
                actualVotesMap.remove(content);
            } else {
                // 如果没有投票，创建0票记录
                AssessmentStatsDTO.OptionVote zeroVote = new AssessmentStatsDTO.OptionVote();
                zeroVote.setOptionId(stdOpt.getOptionId());
                zeroVote.setContent(content);
                zeroVote.setVotes(0L);
                result.add(zeroVote);
            }
        }

        // 2. 处理“额外”选项（标准库里没有，但历史数据里有的选项，防止数据丢失）
        if (!actualVotesMap.isEmpty()) {
            result.addAll(actualVotesMap.values());
        }

        return result;
    }

    /**
     * Excel 读取监听器
     */
    public static class HistoryImportListener extends AnalysisEventListener<Map<Integer, String>> {
        private Long activityId;
        private String type; // 1正面 2负面
        private String operName;
        private Map<String, Long> cadreMap;
        private Map<String, Long> optionMap;
        private AssessmentResultMapper resultMapper;
        private AssessmentOptionMapper optionMapper;
        private AssessmentCadreMapper cadreMapper;

        private Map<Integer, String> headMap = new HashMap<>();
        private int count = 0;

        public HistoryImportListener(Long activityId, String type, String operName,
                                     Map<String, Long> cadreMap, Map<String, Long> optionMap,
                                     AssessmentResultMapper resultMapper, AssessmentOptionMapper optionMapper,
                                     AssessmentCadreMapper cadreMapper) {
            this.activityId = activityId;
            this.type = type;
            this.operName = operName;
            this.cadreMap = cadreMap;
            this.optionMap = optionMap;
            this.resultMapper = resultMapper;
            this.optionMapper = optionMapper;
            this.cadreMapper = cadreMapper;
        }

        @Override
        public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
            this.headMap.putAll(headMap);
        }

        @Override
        public void invoke(Map<Integer, String> data, AnalysisContext context) {
            String cadreName = data.get(0);
            if (StringUtils.isEmpty(cadreName) || "姓名".equals(cadreName) || (cadreName != null && cadreName.contains("评价"))) {
                return;
            }

            Long cadreId = cadreMap.get(cadreName);
            if (cadreId == null) {
                AssessmentCadre newCadre = new AssessmentCadre();
                newCadre.setCadreName(cadreName);
                newCadre.setUnitName("未设置");
                newCadre.setPostTitle("未设置");
                newCadre.setStatus("0");
                newCadre.setCreateBy(operName);
                newCadre.setCreateTime(new java.util.Date());
                cadreMapper.insert(newCadre);

                cadreId = newCadre.getCadreId();
                cadreMap.put(cadreName, cadreId);
            }

            for (Map.Entry<Integer, String> cell : data.entrySet()) {
                Integer colIndex = cell.getKey();
                if (colIndex == 0) continue;

                String voteStr = cell.getValue();
                if (StringUtils.isEmpty(voteStr)) continue;

                try {
                    long votes = Double.valueOf(voteStr).longValue();
                    if (votes <= 0) continue;

                    String optionName = headMap.get(colIndex);
                    if (StringUtils.isEmpty(optionName)) continue;

                    Long optionId = getOrCreateOptionId(optionName);

                    for (int i = 0; i < votes; i++) {
                        AssessmentResult record = new AssessmentResult();
                        record.setActivityId(activityId);
                        record.setCadreId(cadreId);
                        record.setOptionId(optionId);
                        record.setOptionType(type);
                        record.setCreateTime(new java.util.Date());
                        resultMapper.insert(record);
                    }
                    count++;
                } catch (NumberFormatException e) {
                }
            }
        }

        private Long getOrCreateOptionId(String optionName) {
            Long id = optionMap.get(optionName);
            if (id == null) {
                AssessmentOption newOption = new AssessmentOption();
                newOption.setOptionContent(optionName);
                newOption.setOptionType(type);
                // newOption.setOrderNum(99);
                newOption.setStatus("0");
                newOption.setCreateBy(operName);
                newOption.setCreateTime(new java.util.Date());
                optionMapper.insert(newOption);

                id = newOption.getOptionId();
                optionMap.put(optionName, id);
            }
            return id;
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
        }

        public int getCount() {
            return count;
        }
    }
}