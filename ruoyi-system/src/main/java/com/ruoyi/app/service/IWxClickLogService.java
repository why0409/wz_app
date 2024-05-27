package com.ruoyi.app.service;

import java.util.List;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.app.domain.WxClickLog;

/**
 * 小程序使用日志Service接口
 *
 * @author ruoyi
 * @date 2024-04-28
 */
public interface IWxClickLogService
{
    /**
     * 查询小程序使用日志
     *
     * @param id 小程序使用日志主键
     * @return 小程序使用日志
     */
    public WxClickLog selectWxClickLogById(Long id);

    /**
     * 查询小程序使用日志列表
     *
     * @param wxClickLog 小程序使用日志
     * @return 小程序使用日志集合
     */
    public List<WxClickLog> selectWxClickLogList(WxClickLog wxClickLog);

    /**
     * 新增小程序使用日志
     *
     * @param wxClickLog 小程序使用日志
     * @return 结果
     */
    public int insertWxClickLog(WxClickLog wxClickLog);

    /**
     * 修改小程序使用日志
     *
     * @param wxClickLog 小程序使用日志
     * @return 结果
     */
    public int updateWxClickLog(WxClickLog wxClickLog);

    /**
     * 批量删除小程序使用日志
     *
     * @param ids 需要删除的小程序使用日志主键集合
     * @return 结果
     */
    public int deleteWxClickLogByIds(Long[] ids);

    /**
     * 删除小程序使用日志信息
     *
     * @param id 小程序使用日志主键
     * @return 结果
     */
    public int deleteWxClickLogById(Long id);

    public JSONObject trendCountByDay(String time,String serviceId);

    public JSONObject trendCountByMonth(String time,String serviceId);

    public JSONObject trendCountByYear(String time,String serviceId);

    public JSONObject staticsCount();

    public JSONObject staticsCountByServiceId(String serviceId);
}
