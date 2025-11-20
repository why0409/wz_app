package com.ruoyi.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.app.domain.AssessmentCadre;

import java.util.List;

/**
 * 被测评干部Service接口
 * * @author wanghongyu
 *
 * @date 2025-11-04
 */
public interface IAssessmentCadreService extends IService<AssessmentCadre> {
    /**
     * 查询被测评干部列表
     * * @param assessmentCadre 被测评干部
     *
     * @return 被测评干部集合
     */
    public List<AssessmentCadre> selectAssessmentCadreList(AssessmentCadre assessmentCadre);

    /**
     * 导入干部数据
     *
     * @param cadreList       干部数据列表
     * @param isUpdateSupport 是否支持更新
     * @param operName        操作用户
     * @return 结果
     */
    public String importCadres(List<AssessmentCadre> cadreList, Boolean isUpdateSupport, String operName);
}