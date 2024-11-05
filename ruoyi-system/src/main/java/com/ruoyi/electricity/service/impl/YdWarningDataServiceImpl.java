package com.ruoyi.electricity.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.electricity.domain.YdWarningData;
import com.ruoyi.electricity.mapper.YdEnterpriseDataMapper;
import com.ruoyi.electricity.mapper.YdWarningDataMapper;
import com.ruoyi.electricity.mapper.YdWarningThresholdMapper;
import com.ruoyi.electricity.service.IYdWarningDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 * 用电预警数据Service业务层处理
 *
 * @author ruoyi
 * @date 2024-11-04
 */
@Service
public class YdWarningDataServiceImpl extends ServiceImpl<YdWarningDataMapper, YdWarningData> implements IYdWarningDataService
{
    @Autowired
    private YdWarningDataMapper ydWarningDataMapper;

    @Autowired
    private YdEnterpriseDataMapper ydEnterpriseDataMapper;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private YdWarningThresholdMapper ydWarningThresholdMapper;

    /**
     * 查询用电预警数据
     *
     * @param id 用电预警数据主键
     * @return 用电预警数据
     */
    @Override
    public YdWarningData selectYdWarningDataById(Long id)
    {
        return ydWarningDataMapper.selectYdWarningDataById(id);
    }

    /**
     * 查询用电预警数据列表
     *
     * @param ydWarningData 用电预警数据
     * @return 用电预警数据
     */
    @Override
    public List<YdWarningData> selectYdWarningDataList(YdWarningData ydWarningData)
    {
        return ydWarningDataMapper.selectYdWarningDataList(ydWarningData);
    }

    /**
     * 新增用电预警数据
     *
     * @param ydWarningData 用电预警数据
     * @return 结果
     */
    @Override
    public int insertYdWarningData(YdWarningData ydWarningData)
    {
        ydWarningData.setCreateTime(DateUtils.getNowDate());
        return ydWarningDataMapper.insertYdWarningData(ydWarningData);
    }

    /**
     * 修改用电预警数据
     *
     * @param ydWarningData 用电预警数据
     * @return 结果
     */
    @Override
    public int updateYdWarningData(YdWarningData ydWarningData)
    {
        ydWarningData.setUpdateTime(DateUtils.getNowDate());
        return ydWarningDataMapper.updateYdWarningData(ydWarningData);
    }

    /**
     * 批量删除用电预警数据
     *
     * @param ids 需要删除的用电预警数据主键
     * @return 结果
     */
    @Override
    public int deleteYdWarningDataByIds(Long[] ids)
    {
        return ydWarningDataMapper.deleteYdWarningDataByIds(ids);
    }

    /**
     * 删除用电预警数据信息
     *
     * @param id 用电预警数据主键
     * @return 结果
     */
    @Override
    public int deleteYdWarningDataById(Long id)
    {
        return ydWarningDataMapper.deleteYdWarningDataById(id);
    }

    @Override
    public List<YdWarningData> analysisImport(){
        Long latestMaxId = redisCache.getCacheObject("maxYdDataId");
        List<YdWarningData> list = ydWarningDataMapper.getLatestWarningDataList(latestMaxId);

        Double yellow = ydWarningThresholdMapper.getThresholdByStatus("1");
        Double red = ydWarningThresholdMapper.getThresholdByStatus("2");

        for (YdWarningData wd : list) {
            //设置前7日平均功率值
            String miniActivePower;
            if (StringUtils.isEmpty(wd.getMiniActivePower())){
                miniActivePower = null;
            }else {
                String[] array = wd.getMiniActivePower().split(",");
                if (array.length != 7){
                    miniActivePower = null;
                }else {
                    DecimalFormat df = new DecimalFormat("0.0000");
                    double avgValue = (Double.parseDouble(array[2])+Double.parseDouble(array[3])+Double.parseDouble(array[4]))/3;
                    miniActivePower = df.format(avgValue);
                }
            }

            //设置变化幅度
            String volatilityRange;
            if (miniActivePower == null){
                volatilityRange = null;
            }else {
                Double totalValue = Double.parseDouble(wd.getTotalActivePower());
                if (totalValue == 0){
                    volatilityRange = null;
                }else {
                    Double miniValue = Double.parseDouble(miniActivePower);

                    DecimalFormat df = new DecimalFormat("0.00");
                    double value =  Math.abs((totalValue - miniValue) * 100 / miniValue);
                    volatilityRange = df.format(value);
                }
            }

            //设置预警状态
            String status;
            if (volatilityRange == null){
                status = null;
            }else {
                Double value = Double.parseDouble(volatilityRange);
                if (value >= yellow && value < red){
                    status = "1";
                }else if (value >= red){
                    status = "2";
                }else {
                    status = "0";
                }
            }

            wd.setMiniActivePower(miniActivePower);
            wd.setVolatilityRange(volatilityRange);
            wd.setStatus(status);
            wd.setCreateTime(new Date());

            ydWarningDataMapper.insertYdWarningData(wd);
        }

        //记录上次分析的最大Id
        Long maxYdDataId = ydEnterpriseDataMapper.getMaxId();
        redisCache.setCacheObject("maxYdDataId",maxYdDataId);

        return list;
    }

    @Override
    public YdWarningData getLatestWarningDataByMeter(String meterNumber){
        return ydWarningDataMapper.getLatestWarningDataByMeter(meterNumber);
    }

    @Override
    public List<JSONObject> statisticsByStatus(String meterNumber){
        return ydWarningDataMapper.statisticsByStatus(meterNumber);
    }

    @Override
    public List<YdWarningData> getWarningList(YdWarningData ydWarningData){
        return ydWarningDataMapper.getWarningList(ydWarningData);
    }
}
