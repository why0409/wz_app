package com.ruoyi.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.app.domain.AssessmentLog;
import com.ruoyi.app.domain.dto.AssessmentStatsDTO;
import com.ruoyi.app.mapper.AssessmentResultMapper;
import com.ruoyi.app.service.IAssessmentLogService;
import com.ruoyi.app.service.IAssessmentStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    private IAssessmentLogService logService;

    @Override
    public AssessmentStatsDTO getStats(Long activityId) {
        AssessmentStatsDTO statsDTO = new AssessmentStatsDTO();

        // 1. 统计参与人数
        long totalParticipants = logService.count(
                new LambdaQueryWrapper<AssessmentLog>().eq(AssessmentLog::getActivityId, activityId)
        );
        statsDTO.setTotalParticipants(totalParticipants);

        // 2. 查询原始统计数据
        List<Map<String, Object>> rawStats = resultMapper.selectStatsByActivityId(activityId);

        // 3. 按干部ID分组
        Map<Long, List<Map<String, Object>>> groupedByCadre = rawStats.stream()
                .collect(Collectors.groupingBy(row -> ((Number) row.get("cadre_id")).longValue()));

        List<AssessmentStatsDTO.CadreStat> cadreStatsList = new ArrayList<>();

        // 4. 遍历每个干部，组装数据
        for (Map.Entry<Long, List<Map<String, Object>>> entry : groupedByCadre.entrySet()) {
            AssessmentStatsDTO.CadreStat cadreStat = new AssessmentStatsDTO.CadreStat();
            List<Map<String, Object>> rows = entry.getValue();

            // 设置干部信息 (取第一条记录即可)
            Map<String, Object> firstRow = rows.get(0);
            cadreStat.setCadreId(((Number) firstRow.get("cadre_id")).longValue());
            cadreStat.setCadreName((String) firstRow.get("cadre_name"));
            cadreStat.setUnitName((String) firstRow.get("unit_name"));
            cadreStat.setPostTitle((String) firstRow.get("post_title"));

            // 5. 分组正面和负面评价
            List<AssessmentStatsDTO.OptionVote> positiveResults = new ArrayList<>();
            List<AssessmentStatsDTO.OptionVote> negativeResults = new ArrayList<>();

            for (Map<String, Object> row : rows) {
                AssessmentStatsDTO.OptionVote vote = new AssessmentStatsDTO.OptionVote();
                vote.setOptionId(((Number) row.get("option_id")).longValue());
                vote.setContent((String) row.get("option_content"));
                // votes 可能是 Long 或 BigDecimal，取决于数据库
                vote.setVotes(((Number) row.get("votes")).longValue());

                String optionType = (String) row.get("option_type");
                if ("1".equals(optionType)) {
                    positiveResults.add(vote);
                } else if ("2".equals(optionType)) {
                    negativeResults.add(vote);
                }
            }

            cadreStat.setPositiveResults(positiveResults);
            cadreStat.setNegativeResults(negativeResults);
            cadreStatsList.add(cadreStat);
            System.out.println("cadreStat:" + cadreStat);
        }

        // --- 新增排序逻辑：按干部总正面票数排序 ---
//        cadreStatsList.sort((stat1, stat2) -> {
//            // 计算 stat1 的总正面票数
//            long totalPositiveVotes1 = stat1.getPositiveResults().stream()
//                    .mapToLong(AssessmentStatsDTO.OptionVote::getVotes)
//                    .sum();
//
//            // 计算 stat2 的总正面票数
//            long totalPositiveVotes2 = stat2.getPositiveResults().stream()
//                    .mapToLong(AssessmentStatsDTO.OptionVote::getVotes)
//                    .sum();
//
//            // 倒序排列 (从高到低)
//            return Long.compare(totalPositiveVotes2, totalPositiveVotes1);
//        });
        // --- 排序结束 ---

        statsDTO.setCadreStats(cadreStatsList);
        return statsDTO;
    }
}