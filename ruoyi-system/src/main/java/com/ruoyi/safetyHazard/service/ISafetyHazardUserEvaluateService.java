package com.ruoyi.safetyHazard.service;

import com.ruoyi.safetyHazard.domain.SafetyHazardUserEvaluate;

import java.util.List;

/**
 * 隐患排查-用户评价Service接口
 *
 * @author ruoyi
 * @date 2024-09-12
 */
public interface ISafetyHazardUserEvaluateService
{
    /**
     * 查询隐患排查-用户评价
     *
     * @param id 隐患排查-用户评价主键
     * @return 隐患排查-用户评价
     */
    public SafetyHazardUserEvaluate selectSafetyHazardUserEvaluateById(Long id);

    /**
     * 查询隐患排查-用户评价列表
     *
     * @param safetyHazardUserEvaluate 隐患排查-用户评价
     * @return 隐患排查-用户评价集合
     */
    public List<SafetyHazardUserEvaluate> selectSafetyHazardUserEvaluateList(SafetyHazardUserEvaluate safetyHazardUserEvaluate);

    /**
     * 新增隐患排查-用户评价
     *
     * @param safetyHazardUserEvaluate 隐患排查-用户评价
     * @return 结果
     */
    public int insertSafetyHazardUserEvaluate(SafetyHazardUserEvaluate safetyHazardUserEvaluate);

    /**
     * 修改隐患排查-用户评价
     *
     * @param safetyHazardUserEvaluate 隐患排查-用户评价
     * @return 结果
     */
    public int updateSafetyHazardUserEvaluate(SafetyHazardUserEvaluate safetyHazardUserEvaluate);

    /**
     * 批量删除隐患排查-用户评价
     *
     * @param ids 需要删除的隐患排查-用户评价主键集合
     * @return 结果
     */
    public int deleteSafetyHazardUserEvaluateByIds(Long[] ids);

    /**
     * 删除隐患排查-用户评价信息
     *
     * @param id 隐患排查-用户评价主键
     * @return 结果
     */
    public int deleteSafetyHazardUserEvaluateById(Long id);
}
