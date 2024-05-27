package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WxWzWeatherMapper;
import com.ruoyi.system.domain.WxWzWeather;
import com.ruoyi.system.service.IWxWzWeatherService;

/**
 * 湾沚天气Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-02-22
 */
@Service
public class WxWzWeatherServiceImpl implements IWxWzWeatherService 
{
    @Autowired
    private WxWzWeatherMapper wxWzWeatherMapper;

    /**
     * 查询湾沚天气
     * 
     * @param uuid 湾沚天气主键
     * @return 湾沚天气
     */
    @Override
    public WxWzWeather selectWxWzWeatherByUuid(String uuid)
    {
        return wxWzWeatherMapper.selectWxWzWeatherByUuid(uuid);
    }

    /**
     * 查询湾沚天气列表
     * 
     * @param wxWzWeather 湾沚天气
     * @return 湾沚天气
     */
    @Override
    public List<WxWzWeather> selectWxWzWeatherList(WxWzWeather wxWzWeather)
    {
        return wxWzWeatherMapper.selectWxWzWeatherList(wxWzWeather);
    }

    /**
     * 新增湾沚天气
     * 
     * @param wxWzWeather 湾沚天气
     * @return 结果
     */
    @Override
    public int insertWxWzWeather(WxWzWeather wxWzWeather)
    {
        return wxWzWeatherMapper.insertWxWzWeather(wxWzWeather);
    }

    /**
     * 修改湾沚天气
     * 
     * @param wxWzWeather 湾沚天气
     * @return 结果
     */
    @Override
    public int updateWxWzWeather(WxWzWeather wxWzWeather)
    {
        return wxWzWeatherMapper.updateWxWzWeather(wxWzWeather);
    }

    /**
     * 批量删除湾沚天气
     * 
     * @param uuids 需要删除的湾沚天气主键
     * @return 结果
     */
    @Override
    public int deleteWxWzWeatherByUuids(String[] uuids)
    {
        return wxWzWeatherMapper.deleteWxWzWeatherByUuids(uuids);
    }

    /**
     * 删除湾沚天气信息
     * 
     * @param uuid 湾沚天气主键
     * @return 结果
     */
    @Override
    public int deleteWxWzWeatherByUuid(String uuid)
    {
        return wxWzWeatherMapper.deleteWxWzWeatherByUuid(uuid);
    }
}
