package com.ruoyi.app.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.app.domain.*;
import com.ruoyi.app.domain.dto.AssessmentStatsDTO;
import com.ruoyi.app.mapper.AssessmentActivityMapper;
import com.ruoyi.app.mapper.AssessmentCadreMapper;
import com.ruoyi.app.mapper.AssessmentOptionMapper;
import com.ruoyi.app.mapper.AssessmentResultMapper;
import com.ruoyi.app.service.IAssessmentActivityReportService;
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

    @Autowired
    private IAssessmentActivityReportService reportService;


    @Override
    public AssessmentStatsDTO getStats(Long activityId) {
        // 1. 检查活动状态
        AssessmentActivity activity = activityMapper.selectById(activityId);
        if (activity != null && "2".equals(activity.getStatus())) {
            // 已结束，尝试查询报告
            AssessmentActivityReport report = reportService.getOne(
                    new LambdaQueryWrapper<AssessmentActivityReport>()
                            .eq(AssessmentActivityReport::getActivityId, activityId)
            );

            if (report != null && StringUtils.isNotEmpty(report.getStatsJson())) {
                try {
                    return com.alibaba.fastjson2.JSON.parseObject(report.getStatsJson(), AssessmentStatsDTO.class);
                } catch (Exception e) {
                    // 解析失败，降级为实时计算
                }
            } else {
                // 如果没有报告（可能是旧数据），尝试生成并补录（懒加载策略）
                // 这里我们先实时计算，计算完后暂不强制保存，避免查询接口副作用太大
                // 或者可以异步保存。为简单起见，这里仅实时计算。
                // 如果需要补录，建议通过专门的脚本或管理功能触发。
            }
        }

        // 2. 实时计算逻辑
        // 2.1 统计参与人数
        long totalParticipants = logService.count(
                new LambdaQueryWrapper<AssessmentLog>().eq(AssessmentLog::getActivityId, activityId)
        );

        // 2.2 查询原始统计数据
        List<Map<String, Object>> rawStats = resultMapper.selectStatsByActivityId(activityId);

        // 2.3 调用统一处理逻辑（包含数据补全）
        return getStatsInternal(rawStats, totalParticipants);
    }

    /**
     * 实现跨年份统计
     */
    @Override
    public AssessmentStatsDTO getStatsByYearRange(String startYear, String endYear, String cadreName) {
        AssessmentStatsDTO finalStats = new AssessmentStatsDTO();
        finalStats.setTotalParticipants(0L);
        finalStats.setCadreStats(new ArrayList<>());

        // 1. 查询该年份范围内的所有活动
        List<AssessmentActivity> activities = activityMapper.selectList(
                new LambdaQueryWrapper<AssessmentActivity>()
                        .ge(AssessmentActivity::getActivityYear, startYear)
                        .le(AssessmentActivity::getActivityYear, endYear)
        );

        if (activities.isEmpty()) {
            return finalStats;
        }

        // 2. 分离已结束（需要查报告）和未结束（需要查实时）的活动
        List<Long> finishedIds = activities.stream()
                .filter(a -> "2".equals(a.getStatus()))
                .map(AssessmentActivity::getActivityId)
                .collect(Collectors.toList());

        // 3. 处理已结束的活动：从报告表中查询
        if (!finishedIds.isEmpty()) {
            List<AssessmentActivityReport> reports = reportService.list(
                    new LambdaQueryWrapper<AssessmentActivityReport>()
                            .in(AssessmentActivityReport::getActivityId, finishedIds)
            );

            for (AssessmentActivityReport report : reports) {
                if (StringUtils.isNotEmpty(report.getStatsJson())) {
                    try {
                        AssessmentStatsDTO reportStats = com.alibaba.fastjson2.JSON.parseObject(report.getStatsJson(), AssessmentStatsDTO.class);
                        mergeStats(finalStats, reportStats);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // 4. 处理未结束的活动：查实时数据库 (排除状态为2的)
        List<Map<String, Object>> liveRawStats = resultMapper.selectStatsByYearRange(startYear, endYear, cadreName, "2");
        AssessmentStatsDTO liveStats = getStatsInternal(liveRawStats, 0L);

        // 5. 合并结果
        mergeStats(finalStats, liveStats);
        if (StringUtils.isNotEmpty(cadreName) && finalStats.getCadreStats() != null) {
            List<AssessmentStatsDTO.CadreStat> filteredList = finalStats.getCadreStats().stream()
                    .filter(c -> c.getCadreName() != null && c.getCadreName().contains(cadreName)) // 使用 contains 实现模糊查询
                    .collect(Collectors.toList());
            finalStats.setCadreStats(filteredList);
        }
        return finalStats;
    }

    /**
     * 辅助方法：合并两个统计结果
     */
    private void mergeStats(AssessmentStatsDTO target, AssessmentStatsDTO source) {
        if (source == null) return;

        target.setTotalParticipants(target.getTotalParticipants() + source.getTotalParticipants());

        if (source.getCadreStats() == null || source.getCadreStats().isEmpty()) return;
        if (target.getCadreStats() == null) target.setCadreStats(new ArrayList<>());

        Map<Long, AssessmentStatsDTO.CadreStat> targetMap = target.getCadreStats().stream()
                .collect(Collectors.toMap(AssessmentStatsDTO.CadreStat::getCadreId, c -> c));

        for (AssessmentStatsDTO.CadreStat srcStat : source.getCadreStats()) {
            AssessmentStatsDTO.CadreStat targetStat = targetMap.get(srcStat.getCadreId());
            if (targetStat == null) {
                target.getCadreStats().add(srcStat);
            } else {
                // 合并选项票数
                targetStat.setPositiveResults(mergeOptionVotes(targetStat.getPositiveResults(), srcStat.getPositiveResults()));
                targetStat.setNegativeResults(mergeOptionVotes(targetStat.getNegativeResults(), srcStat.getNegativeResults()));
            }
        }

        // 可选：对最终列表重新排序?
    }

    private List<AssessmentStatsDTO.OptionVote> mergeOptionVotes(List<AssessmentStatsDTO.OptionVote> list1, List<AssessmentStatsDTO.OptionVote> list2) {
        if (list1 == null) list1 = new ArrayList<>();
        if (list2 == null) return list1;

        Map<String, AssessmentStatsDTO.OptionVote> map1 = list1.stream()
                .collect(Collectors.toMap(AssessmentStatsDTO.OptionVote::getContent, v -> v, (v1, v2) -> v1));

        for (AssessmentStatsDTO.OptionVote v2 : list2) {
            AssessmentStatsDTO.OptionVote v1 = map1.get(v2.getContent());
            if (v1 != null) {
                v1.setVotes(v1.getVotes() + v2.getVotes());
            } else {
                list1.add(v2);
            }
        }
        return list1;
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
            activity.setStatus("2"); // 状态：已结束
            activity.setCreateBy(operName);
            activity.setCreateTime(new Date());
            activityMapper.insert(activity);
        }
        Long activityId = activity.getActivityId();

        // 2. 获取现有的统计报告（如果已存在，则在此基础上合并；如果不存在，则新建）
        AssessmentActivityReport report = reportService.getOne(new LambdaQueryWrapper<AssessmentActivityReport>()
                .eq(AssessmentActivityReport::getActivityId, activityId));

        AssessmentStatsDTO currentStats;
        if (report != null && StringUtils.isNotEmpty(report.getStatsJson())) {
            // 解析已有数据以便合并（例如先导了正面，现在导负面）
            currentStats = JSON.parseObject(report.getStatsJson(), AssessmentStatsDTO.class);
        } else {
            currentStats = new AssessmentStatsDTO();
            currentStats.setTotalParticipants(0L); // 历史数据通常无法确切知道总参与人数，置0或预估
            currentStats.setCadreStats(new ArrayList<>());
        }

        // 3. 准备基础数据映射（用于匹配数据库中已存在的人员和选项 ID，匹配不到的生成虚拟ID）
        // 即使不插入新数据，尽量匹配已有数据是个好习惯
        List<AssessmentCadre> dbCadres = cadreMapper.selectList(null);
        Map<String, Long> cadreNameIdMap = dbCadres.stream()
                .collect(Collectors.toMap(AssessmentCadre::getCadreName, AssessmentCadre::getCadreId, (k1, k2) -> k1));

        List<AssessmentOption> dbOptions = optionMapper.selectList(new LambdaQueryWrapper<AssessmentOption>().eq(AssessmentOption::getOptionType, type));
        Map<String, Long> optionNameIdMap = dbOptions.stream()
                .collect(Collectors.toMap(AssessmentOption::getOptionContent, AssessmentOption::getOptionId, (k1, k2) -> k1));

        // 4. 读取 Excel 并更新 stats 对象
        HistoryImportListener listener = new HistoryImportListener(currentStats, type, cadreNameIdMap, optionNameIdMap);
        EasyExcel.read(file.getInputStream(), listener).sheet(0).headRowNumber(2).doRead(); // 假设表头在第2行

        // 5. 保存更新后的报告
        if (report == null) {
            report = new AssessmentActivityReport();
            report.setActivityId(activityId);
            report.setCreateTime(new Date());
        }
        // 更新 JSON
        report.setStatsJson(JSON.toJSONString(currentStats));
        reportService.saveOrUpdate(report, new LambdaQueryWrapper<AssessmentActivityReport>().eq(AssessmentActivityReport::getActivityId, activityId));

//        return "导入处理完成，当前活动包含 " + currentStats.getCadreStats().size() + " 名干部的考评数据。";
        return "导入处理完成。";
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
    public static class HistoryImportListener extends com.alibaba.excel.event.AnalysisEventListener<Map<Integer, String>> {
        private AssessmentStatsDTO statsDTO;
        private String type; // "1"=正面, "2"=负面
        private Map<String, Long> cadreMap;
        private Map<String, Long> optionMap;

        // 缓存表头：列索引 -> 选项名称
        private Map<Integer, String> headMap = new HashMap<>();

        public HistoryImportListener(AssessmentStatsDTO statsDTO, String type,
                                     Map<String, Long> cadreMap, Map<String, Long> optionMap) {
            this.statsDTO = statsDTO;
            this.type = type;
            this.cadreMap = cadreMap;
            this.optionMap = optionMap;
        }

        @Override
        public void invokeHeadMap(Map<Integer, String> headMap, com.alibaba.excel.context.AnalysisContext context) {
            this.headMap.putAll(headMap);
        }

        @Override
        public void invoke(Map<Integer, String> data, com.alibaba.excel.context.AnalysisContext context) {
            // 假设第0列是姓名
            String cadreName = data.get(0);
            if (StringUtils.isEmpty(cadreName) || "姓名".equals(cadreName)) {
                return;
            }

            // 1. 在 DTO 中查找该干部是否存在（实现跨类型导入时的合并）
            AssessmentStatsDTO.CadreStat targetStat = findOrCreateCadreStat(cadreName, data);

            // 2. 遍历列，处理选项票数
            List<AssessmentStatsDTO.OptionVote> targetVoteList = "1".equals(type) ?
                    targetStat.getPositiveResults() :
                    targetStat.getNegativeResults();
            if (targetVoteList == null) {
                targetVoteList = new ArrayList<>();
                if ("1".equals(type)) targetStat.setPositiveResults(targetVoteList);
                else targetStat.setNegativeResults(targetVoteList);
            }

            for (Map.Entry<Integer, String> cell : data.entrySet()) {
                Integer colIndex = cell.getKey();
                if (colIndex == 0) continue; // 跳过姓名列

                String optionName = headMap.get(colIndex);
                if (StringUtils.isEmpty(optionName)) continue;

                String voteStr = cell.getValue();
                long votes = 0;
                try {
                    if (StringUtils.isNotEmpty(voteStr)) {
                        votes = Double.valueOf(voteStr).longValue();
                    }
                } catch (NumberFormatException ignored) {
                }

                // 即使票数为0，如果为了保持列完整，也可以选择添加，这里仅添加有票数或存在的列
                // 查找或创建 OptionVote 对象
                addOrUpdateOptionVote(targetVoteList, optionName, votes);
            }
        }

        /**
         * 辅助方法：在统计列表中根据姓名查找对象，没有则创建
         */
        private AssessmentStatsDTO.CadreStat findOrCreateCadreStat(String name, Map<Integer, String> rowData) {
            // 尝试在现有列表中查找
            Optional<AssessmentStatsDTO.CadreStat> exist = statsDTO.getCadreStats().stream()
                    .filter(c -> name.equals(c.getCadreName()))
                    .findFirst();

            if (exist.isPresent()) {
                return exist.get();
            }

            // 创建新的干部统计对象
            AssessmentStatsDTO.CadreStat newStat = new AssessmentStatsDTO.CadreStat();

            // 处理 ID：如果数据库有，用数据库的；没有，生成基于 Name 的负数 HashCode 作为临时 ID
            // 这样能保证同一个名字每次导入生成的 ID 一致，前端 Key 不会乱
            Long realId = cadreMap.get(name);
            newStat.setCadreId(realId != null ? realId : Math.abs(name.hashCode()) * -1L);

            newStat.setCadreName(name);
            // 尝试从 Excel 读取其他信息（如果有对应列，假设1是单位，2是职务，根据实际 Excel 调整）
            // 这里简单处理，设为“历史导入”
//            newStat.setUnitName("历史导入");
//            newStat.setPostTitle("历史导入");
            newStat.setPositiveResults(new ArrayList<>());
            newStat.setNegativeResults(new ArrayList<>());

            statsDTO.getCadreStats().add(newStat);
            return newStat;
        }

        /**
         * 辅助方法：更新选项得票
         */
        private void addOrUpdateOptionVote(List<AssessmentStatsDTO.OptionVote> list, String optionName, long votes) {
            Optional<AssessmentStatsDTO.OptionVote> existOpt = list.stream()
                    .filter(o -> optionName.equals(o.getContent()))
                    .findFirst();

            if (existOpt.isPresent()) {
                // 如果已存在（可能是重复导入），选择覆盖或累加，这里采用覆盖最新值
                existOpt.get().setVotes(votes);
            } else {
                AssessmentStatsDTO.OptionVote newVote = new AssessmentStatsDTO.OptionVote();
                // 同理，选项 ID 处理
                Long realOptId = optionMap.get(optionName);
                newVote.setOptionId(realOptId != null ? realOptId : Math.abs(optionName.hashCode()) * -1L);
                newVote.setContent(optionName);
                newVote.setVotes(votes);
                list.add(newVote);
            }
        }

        @Override
        public void doAfterAllAnalysed(com.alibaba.excel.context.AnalysisContext context) {
            // 解析完成
        }
    }
}