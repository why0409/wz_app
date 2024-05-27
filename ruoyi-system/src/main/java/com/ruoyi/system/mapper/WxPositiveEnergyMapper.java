package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.WxPositiveEnergy;

/**
 * 湾沚正能量Mapper接口
 * 
 * @author ruoyi
 * @date 2023-02-22
 */
public interface WxPositiveEnergyMapper 
{
    /**
     * 查询湾沚正能量
     * 
     * @param uuid 湾沚正能量主键
     * @return 湾沚正能量
     */
    public WxPositiveEnergy selectWxPositiveEnergyByUuid(String uuid);

    /**
     * 查询湾沚正能量列表
     * 
     * @param wxPositiveEnergy 湾沚正能量
     * @return 湾沚正能量集合
     */
    public List<WxPositiveEnergy> selectWxPositiveEnergyList(WxPositiveEnergy wxPositiveEnergy);

    /**
     * 新增湾沚正能量
     * 
     * @param wxPositiveEnergy 湾沚正能量
     * @return 结果
     */
    public int insertWxPositiveEnergy(WxPositiveEnergy wxPositiveEnergy);

    /**
     * 修改湾沚正能量
     * 
     * @param wxPositiveEnergy 湾沚正能量
     * @return 结果
     */
    public int updateWxPositiveEnergy(WxPositiveEnergy wxPositiveEnergy);

    /**
     * 删除湾沚正能量
     * 
     * @param uuid 湾沚正能量主键
     * @return 结果
     */
    public int deleteWxPositiveEnergyByUuid(String uuid);

    /**
     * 批量删除湾沚正能量
     * 
     * @param uuids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxPositiveEnergyByUuids(String[] uuids);

    public int checkSortNum(int sortNum);
}
