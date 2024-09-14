package com.ruoyi.safetyHazard.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.safetyHazard.domain.SafetyHazardUserEvaluate;
import com.ruoyi.safetyHazard.mapper.SafetyHazardUserEvaluateMapper;
import com.ruoyi.safetyHazard.service.ISafetyHazardUserEvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 隐患排查-用户评价Service业务层处理
 *
 * @author ruoyi
 * @date 2024-09-12
 */
@Service
public class SafetyHazardUserEvaluateServiceImpl extends ServiceImpl<SafetyHazardUserEvaluateMapper, SafetyHazardUserEvaluate> implements ISafetyHazardUserEvaluateService
{
    @Autowired
    private SafetyHazardUserEvaluateMapper safetyHazardUserEvaluateMapper;

    /**
     * 查询隐患排查-用户评价列表
     *
     * @param safetyHazardUserEvaluate 隐患排查-用户评价
     * @return 隐患排查-用户评价
     */
    @Override
    public List<SafetyHazardUserEvaluate> selectSafetyHazardUserEvaluateList(SafetyHazardUserEvaluate safetyHazardUserEvaluate)
    {
        return safetyHazardUserEvaluateMapper.selectSafetyHazardUserEvaluateList(safetyHazardUserEvaluate);
    }

}
