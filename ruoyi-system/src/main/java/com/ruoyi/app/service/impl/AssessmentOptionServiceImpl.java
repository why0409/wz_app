package com.ruoyi.app.service.impl;

import java.util.List;

import com.ruoyi.app.domain.AssessmentCadre;
import com.ruoyi.app.domain.AssessmentOption;
import com.ruoyi.app.mapper.AssessmentOptionMapper;
import com.ruoyi.app.service.IAssessmentOptionService;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.utils.StringUtils;

/**
 * 测评选项Service业务层处理
 * * @author wanghongyu
 *
 * @date 2025-11-04
 */
@Service
public class AssessmentOptionServiceImpl extends ServiceImpl<AssessmentOptionMapper, AssessmentOption> implements IAssessmentOptionService {

    @Override
    public List<AssessmentOption> selectAssessmentOptionList(AssessmentOption assessmentOption) {
        LambdaQueryWrapper<AssessmentOption> lqw = new LambdaQueryWrapper<AssessmentOption>();
        lqw.like(StringUtils.isNotEmpty(assessmentOption.getOptionContent()), AssessmentOption::getOptionContent, assessmentOption.getOptionContent());
        lqw.eq(StringUtils.isNotEmpty(assessmentOption.getOptionType()), AssessmentOption::getOptionType, assessmentOption.getOptionType());
        lqw.eq(StringUtils.isNotEmpty(assessmentOption.getStatus()), AssessmentOption::getStatus, assessmentOption.getStatus());
        return this.list(lqw);
    }

    @Override
    public List<AssessmentOption> selectAllAvailableOptions() {
        LambdaQueryWrapper<AssessmentOption> lqw = new LambdaQueryWrapper<AssessmentOption>();
        // 只查询状态正常的
        lqw.eq(AssessmentOption::getStatus, "0");
        return this.list(lqw);
    }

    @Override
    public String importOptions(List<AssessmentOption> optionList, boolean isUpdateSupport, String operName) {
        if (StringUtils.isNull(optionList) || optionList.isEmpty()) {
            return "导入数据不能为空！";
        }

        // TODO: 在实际业务中，这里应该增加isUpdateSupport的逻辑

        try {

            for (AssessmentOption option : optionList) {
                option.setCreateBy(operName);
                option.setStatus("0");
                option.setCreateTime(DateUtils.getNowDate());
            }
        } catch (Exception e) {
            return "数据导入失败，请检查Excel文件列名是否正确（不能包含空格）";
        }

        this.saveBatch(optionList);
        return "导入成功";
    }
}