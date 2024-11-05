package com.ruoyi.electricity.service;

import com.ruoyi.electricity.domain.YdWarningThreshold;

import java.util.List;

/**
 * 用电预警阈值管理Service接口
 *
 * @author ruoyi
 * @date 2024-11-05
 */
public interface IYdWarningThresholdService
{
    /**
     * 查询用电预警阈值管理
     *
     * @param id 用电预警阈值管理主键
     * @return 用电预警阈值管理
     */
    public YdWarningThreshold selectYdWarningThresholdById(Long id);

    /**
     * 查询用电预警阈值管理列表
     *
     * @param ydWarningThreshold 用电预警阈值管理
     * @return 用电预警阈值管理集合
     */
    public List<YdWarningThreshold> selectYdWarningThresholdList(YdWarningThreshold ydWarningThreshold);

    /**
     * 新增用电预警阈值管理
     *
     * @param ydWarningThreshold 用电预警阈值管理
     * @return 结果
     */
    public int insertYdWarningThreshold(YdWarningThreshold ydWarningThreshold);

    /**
     * 修改用电预警阈值管理
     *
     * @param ydWarningThreshold 用电预警阈值管理
     * @return 结果
     */
    public int updateYdWarningThreshold(YdWarningThreshold ydWarningThreshold);

    /**
     * 批量删除用电预警阈值管理
     *
     * @param ids 需要删除的用电预警阈值管理主键集合
     * @return 结果
     */
    public int deleteYdWarningThresholdByIds(Long[] ids);

    /**
     * 删除用电预警阈值管理信息
     *
     * @param id 用电预警阈值管理主键
     * @return 结果
     */
    public int deleteYdWarningThresholdById(Long id);
}
