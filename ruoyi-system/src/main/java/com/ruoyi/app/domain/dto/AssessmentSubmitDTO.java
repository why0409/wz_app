package com.ruoyi.app.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 小程序提交 DTO
 */
@Data
public class AssessmentSubmitDTO {

    @NotNull(message = "Token不能为空")
    private String token;

    @NotNull(message = "微信Code不能为空")
    private String code;

    @NotEmpty(message = "评价内容不能为空")
    private List<EvaluationItem> evaluations;

    @Data
    public static class EvaluationItem {
        @NotNull(message = "干部ID不能为空")
        private Long cadreId;

        @NotEmpty(message = "正面选项不能为空")
        private List<Long> positiveOptions; // 1-3项

        @NotEmpty(message = "负面选项不能为空")
        private List<Long> negativeOptions; // 1-3项
    }
}