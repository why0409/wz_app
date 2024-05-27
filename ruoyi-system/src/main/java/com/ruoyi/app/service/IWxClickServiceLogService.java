package com.ruoyi.app.service;

import java.util.List;
import com.ruoyi.app.domain.WxClickServiceLog;

/**
 * 小程序各服务使用日志Service接口
 *
 * @author ruoyi
 * @date 2024-04-29
 */
public interface IWxClickServiceLogService
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
     * 批量删除小程序各服务使用日志
     *
     * @param ids 需要删除的小程序各服务使用日志主键集合
     * @return 结果
     */
    public int deleteWxClickServiceLogByIds(Long[] ids);

    /**
     * 删除小程序各服务使用日志信息
     *
     * @param id 小程序各服务使用日志主键
     * @return 结果
     */
    public int deleteWxClickServiceLogById(Long id);
}
