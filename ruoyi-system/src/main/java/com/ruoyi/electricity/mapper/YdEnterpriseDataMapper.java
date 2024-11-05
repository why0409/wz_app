package com.ruoyi.electricity.mapper;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.electricity.domain.YdEnterpriseData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用电企业数据Mapper接口
 *
 * @author ruoyi
 * @date 2024-11-04
 */
@DataSource(DataSourceType.GISDB)
public interface YdEnterpriseDataMapper {
    /**
     * 查询用电企业数据
     *
     * @param id 用电企业数据主键
     * @return 用电企业数据
     */
    public YdEnterpriseData selectYdEnterpriseDataById(Long id);

    /**
     * 查询用电企业数据列表
     *
     * @param ydEnterpriseData 用电企业数据
     * @return 用电企业数据集合
     */
    public List<YdEnterpriseData> selectYdEnterpriseDataList(YdEnterpriseData ydEnterpriseData);

    /**
     * 新增用电企业数据
     *
     * @param ydEnterpriseData 用电企业数据
     * @return 结果
     */
    public int insertYdEnterpriseData(YdEnterpriseData ydEnterpriseData);

    /**
     * 修改用电企业数据
     *
     * @param ydEnterpriseData 用电企业数据
     * @return 结果
     */
    public int updateYdEnterpriseData(YdEnterpriseData ydEnterpriseData);

    /**
     * 删除用电企业数据
     *
     * @param id 用电企业数据主键
     * @return 结果
     */
    public int deleteYdEnterpriseDataById(Long id);

    /**
     * 批量删除用电企业数据
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteYdEnterpriseDataByIds(Long[] ids);

    String getNewDay();

    List<JSONObject> selectData(@Param("time") String time, @Param("meterNumber") String meterNumber);

    String getNewMonth();

    List<JSONObject> selectMonthData(String meterNumber);

    int selectByParam(@Param("meterNumber") String meterNumber, @Param("dataDate") String dataDate, @Param("dataTime") String dataTime);

    int updateData(YdEnterpriseData ydEnterpriseData);

    Long getMaxId();
}
