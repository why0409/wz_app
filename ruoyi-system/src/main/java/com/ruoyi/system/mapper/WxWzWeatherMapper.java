package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.WxWzWeather;

/**
 * 湾沚天气Mapper接口
 * 
 * @author ruoyi
 * @date 2023-02-22
 */
public interface WxWzWeatherMapper 
{
    /**
     * 查询湾沚天气
     * 
     * @param uuid 湾沚天气主键
     * @return 湾沚天气
     */
    public WxWzWeather selectWxWzWeatherByUuid(String uuid);

    /**
     * 查询湾沚天气列表
     * 
     * @param wxWzWeather 湾沚天气
     * @return 湾沚天气集合
     */
    public List<WxWzWeather> selectWxWzWeatherList(WxWzWeather wxWzWeather);

    /**
     * 新增湾沚天气
     * 
     * @param wxWzWeather 湾沚天气
     * @return 结果
     */
    public int insertWxWzWeather(WxWzWeather wxWzWeather);

    /**
     * 修改湾沚天气
     * 
     * @param wxWzWeather 湾沚天气
     * @return 结果
     */
    public int updateWxWzWeather(WxWzWeather wxWzWeather);

    /**
     * 删除湾沚天气
     * 
     * @param uuid 湾沚天气主键
     * @return 结果
     */
    public int deleteWxWzWeatherByUuid(String uuid);

    /**
     * 批量删除湾沚天气
     * 
     * @param uuids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxWzWeatherByUuids(String[] uuids);

    public int checkSortNum(int sortNum);
}
