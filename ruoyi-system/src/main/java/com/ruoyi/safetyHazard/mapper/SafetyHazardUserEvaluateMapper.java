package com.ruoyi.safetyHazard.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.safetyHazard.domain.SafetyHazardUserEvaluate;

import java.util.List;

/**
 * 隐患排查-用户评价Mapper接口
 *
 * @author ruoyi
 * @date 2024-09-12
 */
public interface SafetyHazardUserEvaluateMapper extends BaseMapper<SafetyHazardUserEvaluate>
{


    /**
     * 查询隐患排查-用户评价列表
     *
     * @param safetyHazardUserEvaluate 隐患排查-用户评价
     * @return 隐患排查-用户评价集合
     */
    public List<SafetyHazardUserEvaluate> selectSafetyHazardUserEvaluateList(SafetyHazardUserEvaluate safetyHazardUserEvaluate);


}
