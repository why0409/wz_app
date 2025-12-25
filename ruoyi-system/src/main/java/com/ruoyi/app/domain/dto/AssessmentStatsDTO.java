package com.ruoyi.app.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * 统计结果 DTO
 */
@Data
public class AssessmentStatsDTO {

    /**
     * 参与人数
     */
    private long totalParticipants;

    /**
     * 按干部统计
     */
    private List<CadreStat> cadreStats;

    @Data
    public static class CadreStat {
        private Long cadreId;
        private String cadreName;
//        private String unitName;
//        private String postTitle;
        private List<OptionVote> positiveResults;
        private List<OptionVote> negativeResults;
    }

    @Data
    public static class OptionVote {
        private Long optionId;
        private String content;
        private Long votes;
    }
}