package com.ruoyi.app.domain.dto;

import com.ruoyi.app.domain.AssessmentActivity;
import lombok.Data;

import java.util.List;

/**
 * 活动配置详情 DTO (用于回显)
 */
@Data
public class ActivityConfigDetailDTO {

    /**
     * 活动基本信息
     */
    private AssessmentActivity activityInfo;

    /**
     * 已选干部ID列表
     */
    private List<Long> selectedCadres;

    /**
     * 已选选项ID列表
     */
    private List<Long> selectedOptions;
}