package com.ruoyi.app.domain.dto;

import lombok.Data;
import java.util.List;

/**
 * 活动配置 DTO
 */
@Data
public class AssessmentConfigDTO {
    
    /** 活动ID */
    private Long activityId;

    /** 干部ID列表 */
    private List<Long> cadreIds;

    /** 选项ID列表 */
    private List<Long> optionIds;
}