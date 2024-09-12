package com.ruoyi.safetyHazard.service.impl;

import com.ruoyi.common.utils.DateUtils;
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
public class SafetyHazardUserEvaluateServiceImpl implements ISafetyHazardUserEvaluateService
{
    @Autowired
    private SafetyHazardUserEvaluateMapper safetyHazardUserEvaluateMapper;

    /**
     * 查询隐患排查-用户评价
     *
     * @param id 隐患排查-用户评价主键
     * @return 隐患排查-用户评价
     */
    @Override
    public SafetyHazardUserEvaluate selectSafetyHazardUserEvaluateById(Long id)
    {
        return safetyHazardUserEvaluateMapper.selectSafetyHazardUserEvaluateById(id);
    }

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

    /**
     * 新增隐患排查-用户评价
     *
     * @param safetyHazardUserEvaluate 隐患排查-用户评价
     * @return 结果
     */
    @Override
    public int insertSafetyHazardUserEvaluate(SafetyHazardUserEvaluate safetyHazardUserEvaluate)
    {
        safetyHazardUserEvaluate.setCreateTime(DateUtils.getNowDate());
        return safetyHazardUserEvaluateMapper.insertSafetyHazardUserEvaluate(safetyHazardUserEvaluate);
    }

    /**
     * 修改隐患排查-用户评价
     *
     * @param safetyHazardUserEvaluate 隐患排查-用户评价
     * @return 结果
     */
    @Override
    public int updateSafetyHazardUserEvaluate(SafetyHazardUserEvaluate safetyHazardUserEvaluate)
    {
        safetyHazardUserEvaluate.setUpdateTime(DateUtils.getNowDate());
        return safetyHazardUserEvaluateMapper.updateSafetyHazardUserEvaluate(safetyHazardUserEvaluate);
    }

    /**
     * 批量删除隐患排查-用户评价
     *
     * @param ids 需要删除的隐患排查-用户评价主键
     * @return 结果
     */
    @Override
    public int deleteSafetyHazardUserEvaluateByIds(Long[] ids)
    {
        return safetyHazardUserEvaluateMapper.deleteSafetyHazardUserEvaluateByIds(ids);
    }

    /**
     * 删除隐患排查-用户评价信息
     *
     * @param id 隐患排查-用户评价主键
     * @return 结果
     */
    @Override
    public int deleteSafetyHazardUserEvaluateById(Long id)
    {
        return safetyHazardUserEvaluateMapper.deleteSafetyHazardUserEvaluateById(id);
    }
}
