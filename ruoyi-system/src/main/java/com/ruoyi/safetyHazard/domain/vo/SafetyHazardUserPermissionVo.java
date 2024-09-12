package com.ruoyi.safetyHazard.domain.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 安全隐患-用户管理对象 safety_hazard_user
 *
 * @author ruoyi
 * @date 2024-08-08
 */
@Data
public class SafetyHazardUserPermissionVo {

    private Long parenId;

    private List<Long> userIds;
}
