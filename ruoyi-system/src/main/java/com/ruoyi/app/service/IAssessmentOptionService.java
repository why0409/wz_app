package com.ruoyi.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.app.domain.AssessmentCadre;
import com.ruoyi.app.domain.AssessmentOption;

import java.util.List;

/**
 * 测评选项Service接口
 * * @author wanghongyu
 *
 * @date 2025-11-04
 */
public interface IAssessmentOptionService extends IService<AssessmentOption> {
    /**
     * 查询测评选项列表
     * * @param assessmentOption 测评选项
     *
     * @return 测评选项集合
     */
    public List<AssessmentOption> selectAssessmentOptionList(AssessmentOption assessmentOption);

    /**
     * 查询所有可用的选项 (用于配置)
     *
     * @return 列表
     */
    public List<AssessmentOption> selectAllAvailableOptions();

    /**
     * 导入测评选项数据
     *
     * @param optionList       测评选项数据列表
     * @param isUpdateSupport 是否支持更新
     * @param operName        操作用户
     * @return 结果
     */
    String importOptions(List<AssessmentOption> optionList, boolean isUpdateSupport, String operName);
}