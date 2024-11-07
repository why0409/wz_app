package com.ruoyi.electricity.mapper;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.electricity.domain.YdWarningData;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 用电预警数据Mapper接口
 *
 * @author ruoyi
 * @date 2024-11-04
 */
public interface YdWarningDataMapper extends BaseMapper<YdWarningData>
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

    public List<YdWarningData> getLatestWarningDataList(@Param("list") List<Long> list,
                                                        @Param("updateTime") String updateTime);

    public YdWarningData getLatestWarningDataByMeter(String meterNumber);

    public List<JSONObject> statisticsByStatus(String meterNumber);

    public List<YdWarningData> getWarningList(YdWarningData ydWarningData);

    public int insertBatchYdWarningData(List<YdWarningData> list);

    public int updateNormalStatus(Double yellow);

    public int updateYellowStatus(@Param("yellow") Double yellow,
                                  @Param("red") Double red);

    public int updateRedStatus(Double red);

    public int getRepeatCount(@Param("meterNumber") String meterNumber,
                              @Param("dataTime") Date dataTime);

    public int updateRepeatData(YdWarningData ydWarningData);

    public List<YdWarningData> getWarningDataListByIds(@Param("list") List<Long> list);

    public int updateByDataId(YdWarningData ydWarningData);

    public List<Long> getCorrelationIds(Long id);
}
