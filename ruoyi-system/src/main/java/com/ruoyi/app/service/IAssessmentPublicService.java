package com.ruoyi.app.service;


import com.ruoyi.app.domain.dto.AssessmentLoadDTO;
import com.ruoyi.app.domain.dto.AssessmentSubmitDTO;

/**
 * 测评小程序公共Service接口
 * * @author wanghongyu
 *
 * @date 2025-11-04
 */
public interface IAssessmentPublicService {

    /**
     * 加载测评内容
     *
     * @param token 测评Token
     * @return 测评所需数据
     */
    public AssessmentLoadDTO loadAssessment(String token, String openId);

    /**
     * 提交测评
     *
     * @param submitDTO 提交的数据
     */
    public void submitAssessment(AssessmentSubmitDTO submitDTO);
}