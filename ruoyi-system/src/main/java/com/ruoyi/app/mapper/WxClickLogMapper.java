package com.ruoyi.app.mapper;

import java.util.List;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.app.domain.WxClickLog;
import org.apache.ibatis.annotations.Param;

/**
 * 小程序使用日志Mapper接口
 *
 * @author ruoyi
 * @date 2024-04-28
 */
public interface WxClickLogMapper
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
     * 删除小程序使用日志
     *
     * @param id 小程序使用日志主键
     * @return 结果
     */
    public int deleteWxClickLogById(Long id);

    /**
     * 批量删除小程序使用日志
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxClickLogByIds(Long[] ids);

    public JSONObject staticsCountByAll();

    public JSONObject staticsCountByDay(@Param("time") String time);

    public JSONObject staticsCountByMonth(@Param("time") String time);

    public JSONObject staticsCountByYear(@Param("time") String time);

    public List<JSONObject> trendCountByDay(@Param("type") String type,
                                            @Param("time") String time);

    public List<JSONObject> trendCountByMonth(@Param("type") String type,
                                            @Param("time") String time);

    public List<JSONObject> trendCountByYear(@Param("type") String type,
                                              @Param("time") String time);
}
