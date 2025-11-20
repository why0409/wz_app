package com.ruoyi.app.domain.dto;

import lombok.Data;

import java.util.Date;

/**
 * 开通测评响应 DTO
 */
@Data
public class AssessmentStartDTO {

    /**
     * 测评Token
     */
    private String token;

    /**
     * 过期时间
     */
    private Date expireTime;
}