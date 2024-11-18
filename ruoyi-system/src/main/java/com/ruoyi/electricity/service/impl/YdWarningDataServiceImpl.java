package com.ruoyi.electricity.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.electricity.domain.YdWarningData;
import com.ruoyi.electricity.mapper.YdWarningDataMapper;
import com.ruoyi.electricity.mapper.YdWarningThresholdMapper;
import com.ruoyi.electricity.service.IYdWarningDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
    public int analysisImport(List<Long> updateList, String maxYdUpdateTime){
        //需更新的用电预警数据
        if (updateList.size() > 0){
            for (Long id : updateList) {
                //与当前id相关联的id列表
                List<Long> correlationIds = ydWarningDataMapper.getCorrelationIds(id);

                //获取需要更新的预警数据
                List<YdWarningData> updateListByIds = ydWarningDataMapper.getWarningDataListByIds(correlationIds);
                List<YdWarningData> warningUpdatetList = warningCalculations(updateListByIds);
                for (YdWarningData wd : warningUpdatetList) {
                    wd.setUpdateTime(new Date());
                    ydWarningDataMapper.updateByDataId(wd);
                }
            }
        }

        //需插入的用电预警数据
        List<YdWarningData> insertList = ydWarningDataMapper.getLatestWarningDataList(updateList,maxYdUpdateTime);
        List<YdWarningData> warningInsertList = warningCalculations(insertList);
        for (YdWarningData wd : warningInsertList) {
            wd.setCreateTime(new Date());
            ydWarningDataMapper.insertYdWarningData(wd);
        }

        return 1;
    }


    public List<YdWarningData> warningCalculations(List<YdWarningData> list){
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
                    Double[] doubleArray = Arrays.stream(array)
                            .map(Double::parseDouble)
                            .toArray(Double[]::new);
                    //升序排序
                    Arrays.sort(doubleArray);

                    DecimalFormat df = new DecimalFormat("0.0000");
                    double avgValue = (doubleArray[2]+doubleArray[3]+doubleArray[4])/3;
                    miniActivePower = df.format(avgValue);
                }
            }

            //设置变化幅度
            String volatilityRange;
            if (miniActivePower == null){
                volatilityRange = null;
            }else {
                Double miniValue = Double.parseDouble(miniActivePower);
                Double totalValue = Double.parseDouble(wd.getTotalActivePower());

                if (miniValue == 0){
                    volatilityRange = red + 10 + "";
                }else {
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
        }

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
