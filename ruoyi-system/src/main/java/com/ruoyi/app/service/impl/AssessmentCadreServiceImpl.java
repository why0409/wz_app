package com.ruoyi.app.service.impl;

import java.util.List;

import com.ruoyi.app.domain.AssessmentCadre;
import com.ruoyi.app.mapper.AssessmentCadreMapper;
import com.ruoyi.app.service.IAssessmentCadreService;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.utils.StringUtils;

/**
 * 被测评干部Service业务层处理
 * * @author wanghongyu
 *
 * @date 2025-11-04
 */
@Service
public class AssessmentCadreServiceImpl extends ServiceImpl<AssessmentCadreMapper, AssessmentCadre> implements IAssessmentCadreService {
    /**
     * 查询被测评干部列表
     * * @param assessmentCadre 被测评干部
     *
     * @return 被测评干部
     */
    @Override
    public List<AssessmentCadre> selectAssessmentCadreList(AssessmentCadre assessmentCadre) {
        LambdaQueryWrapper<AssessmentCadre> lqw = new LambdaQueryWrapper<AssessmentCadre>();
        lqw.like(StringUtils.isNotEmpty(assessmentCadre.getCadreName()), AssessmentCadre::getCadreName, assessmentCadre.getCadreName());
        lqw.like(StringUtils.isNotEmpty(assessmentCadre.getUnitName()), AssessmentCadre::getUnitName, assessmentCadre.getUnitName());
        lqw.like(StringUtils.isNotEmpty(assessmentCadre.getPostTitle()), AssessmentCadre::getPostTitle, assessmentCadre.getPostTitle());
        lqw.eq(StringUtils.isNotEmpty(assessmentCadre.getStatus()), AssessmentCadre::getStatus, assessmentCadre.getStatus());
        return this.list(lqw);
    }

    /**
     * 导入干部数据
     * (简化版：仅支持新增，不支持更新)
     */
    @Override
    public String importCadres(List<AssessmentCadre> cadreList, Boolean isUpdateSupport, String operName) {
        if (StringUtils.isNull(cadreList) || cadreList.isEmpty()) {
            return "导入数据不能为空！";
        }

        // TODO: 在实际业务中，这里应该增加isUpdateSupport的逻辑
        // 即便不支持更新，也应该检查 "姓名+单位" 是否已存在，防止重复导入

        try {

            for (AssessmentCadre cadre : cadreList) {
                cadre.setCreateBy(operName);
                cadre.setStatus("0");
                cadre.setCreateTime(DateUtils.getNowDate());
            }
        } catch (Exception e) {
            return "数据导入失败，请检查Excel文件列名是否正确（不能包含空格）";
        }

        this.saveBatch(cadreList);
        return "导入成功";
    }
}