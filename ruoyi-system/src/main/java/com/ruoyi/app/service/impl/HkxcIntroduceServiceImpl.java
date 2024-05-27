package com.ruoyi.app.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.app.mapper.HkxcIntroduceMapper;
import com.ruoyi.app.domain.HkxcIntroduce;
import com.ruoyi.app.service.IHkxcIntroduceService;

/**
 * 航空新城-介绍管理Service业务层处理
 *
 * @author ruoyi
 * @date 2024-05-07
 */
@Service
public class HkxcIntroduceServiceImpl implements IHkxcIntroduceService
{
    @Autowired
    private HkxcIntroduceMapper hkxcIntroduceMapper;

    /**
     * 查询航空新城-介绍管理
     *
     * @param id 航空新城-介绍管理主键
     * @return 航空新城-介绍管理
     */
    @Override
    public HkxcIntroduce selectHkxcIntroduceById(Long id)
    {
        return hkxcIntroduceMapper.selectHkxcIntroduceById(id);
    }

    /**
     * 查询航空新城-介绍管理列表
     *
     * @param hkxcIntroduce 航空新城-介绍管理
     * @return 航空新城-介绍管理
     */
    @Override
    public List<HkxcIntroduce> selectHkxcIntroduceList(HkxcIntroduce hkxcIntroduce)
    {
        return hkxcIntroduceMapper.selectHkxcIntroduceList(hkxcIntroduce);
    }

    /**
     * 新增航空新城-介绍管理
     *
     * @param hkxcIntroduce 航空新城-介绍管理
     * @return 结果
     */
    @Override
    public int insertHkxcIntroduce(HkxcIntroduce hkxcIntroduce)
    {
        hkxcIntroduce.setCreateTime(DateUtils.getNowDate());
        return hkxcIntroduceMapper.insertHkxcIntroduce(hkxcIntroduce);
    }

    /**
     * 修改航空新城-介绍管理
     *
     * @param hkxcIntroduce 航空新城-介绍管理
     * @return 结果
     */
    @Override
    public int updateHkxcIntroduce(HkxcIntroduce hkxcIntroduce)
    {
        hkxcIntroduce.setUpdateTime(DateUtils.getNowDate());
        return hkxcIntroduceMapper.updateHkxcIntroduce(hkxcIntroduce);
    }

    /**
     * 批量删除航空新城-介绍管理
     *
     * @param ids 需要删除的航空新城-介绍管理主键
     * @return 结果
     */
    @Override
    public int deleteHkxcIntroduceByIds(Long[] ids)
    {
        return hkxcIntroduceMapper.deleteHkxcIntroduceByIds(ids);
    }

    /**
     * 删除航空新城-介绍管理信息
     *
     * @param id 航空新城-介绍管理主键
     * @return 结果
     */
    @Override
    public int deleteHkxcIntroduceById(Long id)
    {
        return hkxcIntroduceMapper.deleteHkxcIntroduceById(id);
    }
}
