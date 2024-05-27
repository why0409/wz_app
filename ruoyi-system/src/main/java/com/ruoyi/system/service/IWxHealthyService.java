package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.WxHealthy;

/**
 * 健康板块Service接口
 *
 * @author ruoyi
 * @date 2023-05-19
 */
public interface IWxHealthyService
{
    /**
     * 查询健康板块
     *
     * @param uuid 健康板块主键
     * @return 健康板块
     */
    public WxHealthy selectWxHealthyByUuid(String uuid);

    /**
     * 查询健康板块列表
     *
     * @param wxHealthy 健康板块
     * @return 健康板块集合
     */
    public List<WxHealthy> selectWxHealthyList(WxHealthy wxHealthy);

    /**
     * 新增健康板块
     *
     * @param wxHealthy 健康板块
     * @return 结果
     */
    public int insertWxHealthy(WxHealthy wxHealthy);

    /**
     * 修改健康板块
     *
     * @param wxHealthy 健康板块
     * @return 结果
     */
    public int updateWxHealthy(WxHealthy wxHealthy);

    /**
     * 批量删除健康板块
     *
     * @param uuids 需要删除的健康板块主键集合
     * @return 结果
     */
    public int deleteWxHealthyByUuids(String[] uuids);

    /**
     * 删除健康板块信息
     *
     * @param uuid 健康板块主键
     * @return 结果
     */
    public int deleteWxHealthyByUuid(String uuid);
}
