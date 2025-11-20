package com.ruoyi.app.domain.dto;

import com.ruoyi.app.domain.AssessmentActivity;
import com.ruoyi.app.domain.AssessmentCadre;
import com.ruoyi.app.domain.AssessmentOption;
import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * 小程序加载数据 DTO
 */
@Data
public class AssessmentLoadDTO {

    /** 活动信息 */
    private AssessmentActivity activity;

    /** 干部列表 */
    private List<AssessmentCadre> cadres;

    /** 选项Map (positive: [], negative: []) */
    private Map<String, List<AssessmentOption>> options;
}