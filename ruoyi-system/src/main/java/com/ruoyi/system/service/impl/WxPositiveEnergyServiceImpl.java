package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WxPositiveEnergyMapper;
import com.ruoyi.system.domain.WxPositiveEnergy;
import com.ruoyi.system.service.IWxPositiveEnergyService;

/**
 * 湾沚正能量Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-02-22
 */
@Service
public class WxPositiveEnergyServiceImpl implements IWxPositiveEnergyService 
{
    @Autowired
    private WxPositiveEnergyMapper wxPositiveEnergyMapper;

    /**
     * 查询湾沚正能量
     * 
     * @param uuid 湾沚正能量主键
     * @return 湾沚正能量
     */
    @Override
    public WxPositiveEnergy selectWxPositiveEnergyByUuid(String uuid)
    {
        return wxPositiveEnergyMapper.selectWxPositiveEnergyByUuid(uuid);
    }

    /**
     * 查询湾沚正能量列表
     * 
     * @param wxPositiveEnergy 湾沚正能量
     * @return 湾沚正能量
     */
    @Override
    public List<WxPositiveEnergy> selectWxPositiveEnergyList(WxPositiveEnergy wxPositiveEnergy)
    {
        return wxPositiveEnergyMapper.selectWxPositiveEnergyList(wxPositiveEnergy);
    }

    /**
     * 新增湾沚正能量
     * 
     * @param wxPositiveEnergy 湾沚正能量
     * @return 结果
     */
    @Override
    public int insertWxPositiveEnergy(WxPositiveEnergy wxPositiveEnergy)
    {
        return wxPositiveEnergyMapper.insertWxPositiveEnergy(wxPositiveEnergy);
    }

    /**
     * 修改湾沚正能量
     * 
     * @param wxPositiveEnergy 湾沚正能量
     * @return 结果
     */
    @Override
    public int updateWxPositiveEnergy(WxPositiveEnergy wxPositiveEnergy)
    {
        return wxPositiveEnergyMapper.updateWxPositiveEnergy(wxPositiveEnergy);
    }

    /**
     * 批量删除湾沚正能量
     * 
     * @param uuids 需要删除的湾沚正能量主键
     * @return 结果
     */
    @Override
    public int deleteWxPositiveEnergyByUuids(String[] uuids)
    {
        return wxPositiveEnergyMapper.deleteWxPositiveEnergyByUuids(uuids);
    }

    /**
     * 删除湾沚正能量信息
     * 
     * @param uuid 湾沚正能量主键
     * @return 结果
     */
    @Override
    public int deleteWxPositiveEnergyByUuid(String uuid)
    {
        return wxPositiveEnergyMapper.deleteWxPositiveEnergyByUuid(uuid);
    }
}
