package com.ruoyi.app.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.app.mapper.WxClickServiceLogMapper;
import com.ruoyi.app.domain.WxClickServiceLog;
import com.ruoyi.app.service.IWxClickServiceLogService;

/**
 * 小程序各服务使用日志Service业务层处理
 *
 * @author ruoyi
 * @date 2024-04-29
 */
@Service
public class WxClickServiceLogServiceImpl implements IWxClickServiceLogService
{
    @Autowired
    private WxClickServiceLogMapper wxClickServiceLogMapper;

    /**
     * 查询小程序各服务使用日志
     *
     * @param id 小程序各服务使用日志主键
     * @return 小程序各服务使用日志
     */
    @Override
    public WxClickServiceLog selectWxClickServiceLogById(Long id)
    {
        return wxClickServiceLogMapper.selectWxClickServiceLogById(id);
    }

    /**
     * 查询小程序各服务使用日志列表
     *
     * @param wxClickServiceLog 小程序各服务使用日志
     * @return 小程序各服务使用日志
     */
    @Override
    public List<WxClickServiceLog> selectWxClickServiceLogList(WxClickServiceLog wxClickServiceLog)
    {
        return wxClickServiceLogMapper.selectWxClickServiceLogList(wxClickServiceLog);
    }

    /**
     * 新增小程序各服务使用日志
     *
     * @param wxClickServiceLog 小程序各服务使用日志
     * @return 结果
     */
    @Override
    public int insertWxClickServiceLog(WxClickServiceLog wxClickServiceLog)
    {
        wxClickServiceLog.setCreateTime(DateUtils.getNowDate());
        return wxClickServiceLogMapper.insertWxClickServiceLog(wxClickServiceLog);
    }

    /**
     * 修改小程序各服务使用日志
     *
     * @param wxClickServiceLog 小程序各服务使用日志
     * @return 结果
     */
    @Override
    public int updateWxClickServiceLog(WxClickServiceLog wxClickServiceLog)
    {
        wxClickServiceLog.setUpdateTime(DateUtils.getNowDate());
        return wxClickServiceLogMapper.updateWxClickServiceLog(wxClickServiceLog);
    }

    /**
     * 批量删除小程序各服务使用日志
     *
     * @param ids 需要删除的小程序各服务使用日志主键
     * @return 结果
     */
    @Override
    public int deleteWxClickServiceLogByIds(Long[] ids)
    {
        return wxClickServiceLogMapper.deleteWxClickServiceLogByIds(ids);
    }

    /**
     * 删除小程序各服务使用日志信息
     *
     * @param id 小程序各服务使用日志主键
     * @return 结果
     */
    @Override
    public int deleteWxClickServiceLogById(Long id)
    {
        return wxClickServiceLogMapper.deleteWxClickServiceLogById(id);
    }
}
