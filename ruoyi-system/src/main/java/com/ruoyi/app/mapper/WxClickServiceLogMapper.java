package com.ruoyi.app.mapper;

import java.util.List;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.app.domain.WxClickServiceLog;
import org.apache.ibatis.annotations.Param;

/**
 * 小程序各服务使用日志Mapper接口
 *
 * @author ruoyi
 * @date 2024-04-29
 */
public interface WxClickServiceLogMapper
{
    /**
     * 查询小程序各服务使用日志
     *
     * @param id 小程序各服务使用日志主键
     * @return 小程序各服务使用日志
     */
    public WxClickServiceLog selectWxClickServiceLogById(Long id);

    /**
     * 查询小程序各服务使用日志列表
     *
     * @param wxClickServiceLog 小程序各服务使用日志
     * @return 小程序各服务使用日志集合
     */
    public List<WxClickServiceLog> selectWxClickServiceLogList(WxClickServiceLog wxClickServiceLog);

    /**
     * 新增小程序各服务使用日志
     *
     * @param wxClickServiceLog 小程序各服务使用日志
     * @return 结果
     */
    public int insertWxClickServiceLog(WxClickServiceLog wxClickServiceLog);

    /**
     * 修改小程序各服务使用日志
     *
     * @param wxClickServiceLog 小程序各服务使用日志
     * @return 结果
     */
    public int updateWxClickServiceLog(WxClickServiceLog wxClickServiceLog);

    /**
     * 删除小程序各服务使用日志
     *
     * @param id 小程序各服务使用日志主键
     * @return 结果
     */
    public int deleteWxClickServiceLogById(Long id);

    /**
     * 批量删除小程序各服务使用日志
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxClickServiceLogByIds(Long[] ids);

    public JSONObject staticsCountByAllByServiceId(@Param("serviceId") String serviceId);

    public JSONObject staticsCountByDayByServiceId(@Param("serviceId") String serviceId,
                                                   @Param("time") String time);

    public JSONObject staticsCountByMonthByServiceId(@Param("serviceId") String serviceId,
                                                     @Param("time") String time);

    public JSONObject staticsCountByYearByServiceId(@Param("serviceId") String serviceId,
                                                    @Param("time") String time);

    public List<JSONObject> trendCountByDayByServiceId(@Param("type") String type,
                                                       @Param("serviceId") String serviceId,
                                                       @Param("time") String time);

    public List<JSONObject> trendCountByMonthByServiceId(@Param("type") String type,
                                                         @Param("serviceId") String serviceId,
                                                         @Param("time") String time);

    public List<JSONObject> trendCountByYearByServiceId(@Param("type") String type,
                                                        @Param("serviceId") String serviceId,
                                                        @Param("time") String time);

    public List<JSONObject> staticsCountByAll();

    public List<JSONObject> staticsCountByDay( @Param("time") String time);

    public List<JSONObject> staticsCountByMonth(@Param("time") String time);

    public List<JSONObject> staticsCountByYear(@Param("time") String time);
}
