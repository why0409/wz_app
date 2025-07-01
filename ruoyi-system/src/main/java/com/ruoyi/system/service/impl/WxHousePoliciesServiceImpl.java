package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.HousePolicies;
import com.ruoyi.system.mapper.HousePoliciesMapper;
import com.ruoyi.system.service.IWxHousePoliciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WxHousePoliciesServiceImpl implements IWxHousePoliciesService {


    @Autowired
    private HousePoliciesMapper housePoliciesMapper;

    /**
     * 查询湾沚房源政策简介
     *
     * @param uuid 湾沚房源政策简介主键
     * @return 湾沚房源政策简介
     */
    @Override
    public HousePolicies selectHousePoliciesByUuid(String uuid) {
        return housePoliciesMapper.selectHousePoliciesByUuid(uuid);
    }

    /**
     * 查询湾沚房源政策简介列表
     *
     * @param HousePolicies 湾沚房源政策简介
     * @return 湾沚房源政策简介
     */
    @Override
    public List<HousePolicies> selectHousePoliciesList(HousePolicies HousePolicies) {
        return housePoliciesMapper.selectHousePoliciesList(HousePolicies);
    }

    /**
     * 新增湾沚房源政策简介
     *
     * @param HousePolicies 湾沚房源政策简介
     * @return 结果
     */
    @Override
    public int insertHousePolicies(HousePolicies HousePolicies) {
        return housePoliciesMapper.insertHousePolicies(HousePolicies);
    }

    /**
     * 修改湾沚房源政策简介
     *
     * @param HousePolicies 湾沚房源政策简介
     * @return 结果
     */
    @Override
    public int updateHousePolicies(HousePolicies HousePolicies) {
        return housePoliciesMapper.updateHousePolicies(HousePolicies);
    }

    /**
     * 批量删除湾沚房源政策简介
     *
     * @param uuids 需要删除的湾沚房源政策简介主键
     * @return 结果
     */
    @Override
    public int deleteHousePoliciesByUuids(String[] uuids) {
        return housePoliciesMapper.deleteHousePoliciesByUuids(uuids);
    }

    /**
     * 删除湾沚房源政策简介信息
     *
     * @param uuid 湾沚房源政策简介主键
     * @return 结果
     */
    @Override
    public int deleteHousePoliciesByUuid(String uuid) {
        return housePoliciesMapper.deleteHousePoliciesByUuid(uuid);
    }
}
