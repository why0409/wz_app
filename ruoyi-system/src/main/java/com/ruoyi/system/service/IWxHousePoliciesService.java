package com.ruoyi.system.service;

import com.ruoyi.system.domain.HousePolicies;

import java.util.List;

public interface IWxHousePoliciesService {
    /**
     * 查询湾沚房源政策简介
     *
     * @param uuid 湾沚房源政策简介主键
     * @return 湾沚房源政策简介
     */
    public HousePolicies selectHousePoliciesByUuid(String uuid);

    /**
     * 查询湾沚房源政策简介列表
     *
     * @param HousePolicies 湾沚房源政策简介
     * @return 湾沚房源政策简介集合
     */
    public List<HousePolicies> selectHousePoliciesList(HousePolicies HousePolicies);

    /**
     * 新增湾沚房源政策简介
     *
     * @param HousePolicies 湾沚房源政策简介
     * @return 结果
     */
    public int insertHousePolicies(HousePolicies HousePolicies);

    /**
     * 修改湾沚房源政策简介
     *
     * @param HousePolicies 湾沚房源政策简介
     * @return 结果
     */
    public int updateHousePolicies(HousePolicies HousePolicies);

    /**
     * 批量删除湾沚房源政策简介
     *
     * @param uuids 需要删除的湾沚房源政策简介主键集合
     * @return 结果
     */
    public int deleteHousePoliciesByUuids(String[] uuids);

    /**
     * 删除湾沚房源政策简介信息
     *
     * @param uuid 湾沚房源政策简介主键
     * @return 结果
     */
    public int deleteHousePoliciesByUuid(String uuid);
}
