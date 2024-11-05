package com.ruoyi.electricity.mapper;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.electricity.domain.YdWarningData;

import java.util.List;

/**
 * 用电预警数据Mapper接口
 *
 * @author ruoyi
 * @date 2024-11-04
 */
public interface YdWarningDataMapper
{
    /**
     * 查询用电预警数据
     *
     * @param id 用电预警数据主键
     * @return 用电预警数据
     */
    public YdWarningData selectYdWarningDataById(Long id);

    /**
     * 查询用电预警数据列表
     *
     * @param ydWarningData 用电预警数据
     * @return 用电预警数据集合
     */
    public List<YdWarningData> selectYdWarningDataList(YdWarningData ydWarningData);

    /**
     * 新增用电预警数据
     *
     * @param ydWarningData 用电预警数据
     * @return 结果
     */
    public int insertYdWarningData(YdWarningData ydWarningData);

    /**
     * 修改用电预警数据
     *
     * @param ydWarningData 用电预警数据
     * @return 结果
     */
    public int updateYdWarningData(YdWarningData ydWarningData);

    /**
     * 删除用电预警数据
     *
     * @param id 用电预警数据主键
     * @return 结果
     */
    public int deleteYdWarningDataById(Long id);

    /**
     * 批量删除用电预警数据
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteYdWarningDataByIds(Long[] ids);

    public String getLatestDataTime();

    public List<YdWarningData> getLatestWarningDataList(Long id);

    public YdWarningData getLatestWarningDataByMeter(String meterNumber);

    public List<JSONObject> statisticsByStatus(String meterNumber);
}
