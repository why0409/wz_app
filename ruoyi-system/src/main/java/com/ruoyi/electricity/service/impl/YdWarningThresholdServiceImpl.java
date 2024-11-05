package com.ruoyi.electricity.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.electricity.domain.YdWarningThreshold;
import com.ruoyi.electricity.mapper.YdWarningThresholdMapper;
import com.ruoyi.electricity.service.IYdWarningThresholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用电预警阈值管理Service业务层处理
 *
 * @author ruoyi
 * @date 2024-11-05
 */
@Service
public class YdWarningThresholdServiceImpl implements IYdWarningThresholdService
{
    @Autowired
    private YdWarningThresholdMapper ydWarningThresholdMapper;

    /**
     * 查询用电预警阈值管理
     *
     * @param id 用电预警阈值管理主键
     * @return 用电预警阈值管理
     */
    @Override
    public YdWarningThreshold selectYdWarningThresholdById(Long id)
    {
        return ydWarningThresholdMapper.selectYdWarningThresholdById(id);
    }

    /**
     * 查询用电预警阈值管理列表
     *
     * @param ydWarningThreshold 用电预警阈值管理
     * @return 用电预警阈值管理
     */
    @Override
    public List<YdWarningThreshold> selectYdWarningThresholdList(YdWarningThreshold ydWarningThreshold)
    {
        return ydWarningThresholdMapper.selectYdWarningThresholdList(ydWarningThreshold);
    }

    /**
     * 新增用电预警阈值管理
     *
     * @param ydWarningThreshold 用电预警阈值管理
     * @return 结果
     */
    @Override
    public int insertYdWarningThreshold(YdWarningThreshold ydWarningThreshold)
    {
        ydWarningThreshold.setCreateTime(DateUtils.getNowDate());
        return ydWarningThresholdMapper.insertYdWarningThreshold(ydWarningThreshold);
    }

    /**
     * 修改用电预警阈值管理
     *
     * @param ydWarningThreshold 用电预警阈值管理
     * @return 结果
     */
    @Override
    public int updateYdWarningThreshold(YdWarningThreshold ydWarningThreshold)
    {
        ydWarningThreshold.setUpdateTime(DateUtils.getNowDate());
        return ydWarningThresholdMapper.updateYdWarningThreshold(ydWarningThreshold);
    }

    /**
     * 批量删除用电预警阈值管理
     *
     * @param ids 需要删除的用电预警阈值管理主键
     * @return 结果
     */
    @Override
    public int deleteYdWarningThresholdByIds(Long[] ids)
    {
        return ydWarningThresholdMapper.deleteYdWarningThresholdByIds(ids);
    }

    /**
     * 删除用电预警阈值管理信息
     *
     * @param id 用电预警阈值管理主键
     * @return 结果
     */
    @Override
    public int deleteYdWarningThresholdById(Long id)
    {
        return ydWarningThresholdMapper.deleteYdWarningThresholdById(id);
    }
}
