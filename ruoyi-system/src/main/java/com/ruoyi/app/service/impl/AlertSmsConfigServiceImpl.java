package com.ruoyi.app.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.app.mapper.AlertSmsConfigMapper;
import com.ruoyi.app.domain.AlertSmsConfig;
import com.ruoyi.app.service.IAlertSmsConfigService;

/**
 * 预警短信配置Service业务层处理
 *
 * @author ruoyi
 * @date 2024-05-06
 */
@Service
public class AlertSmsConfigServiceImpl implements IAlertSmsConfigService
{
    @Autowired
    private AlertSmsConfigMapper alertSmsConfigMapper;

    /**
     * 查询预警短信配置
     *
     * @param id 预警短信配置主键
     * @return 预警短信配置
     */
    @Override
    public AlertSmsConfig selectAlertSmsConfigById(Long id)
    {
        return alertSmsConfigMapper.selectAlertSmsConfigById(id);
    }

    /**
     * 查询预警短信配置列表
     *
     * @param alertSmsConfig 预警短信配置
     * @return 预警短信配置
     */
    @Override
    public List<AlertSmsConfig> selectAlertSmsConfigList(AlertSmsConfig alertSmsConfig)
    {
        return alertSmsConfigMapper.selectAlertSmsConfigList(alertSmsConfig);
    }

    /**
     * 新增预警短信配置
     *
     * @param alertSmsConfig 预警短信配置
     * @return 结果
     */
    @Override
    public int insertAlertSmsConfig(AlertSmsConfig alertSmsConfig)
    {
        alertSmsConfig.setCreateTime(DateUtils.getNowDate());
        return alertSmsConfigMapper.insertAlertSmsConfig(alertSmsConfig);
    }

    /**
     * 修改预警短信配置
     *
     * @param alertSmsConfig 预警短信配置
     * @return 结果
     */
    @Override
    public int updateAlertSmsConfig(AlertSmsConfig alertSmsConfig)
    {
        alertSmsConfig.setUpdateTime(DateUtils.getNowDate());
        return alertSmsConfigMapper.updateAlertSmsConfig(alertSmsConfig);
    }

    /**
     * 批量删除预警短信配置
     *
     * @param ids 需要删除的预警短信配置主键
     * @return 结果
     */
    @Override
    public int deleteAlertSmsConfigByIds(Long[] ids)
    {
        return alertSmsConfigMapper.deleteAlertSmsConfigByIds(ids);
    }

    /**
     * 删除预警短信配置信息
     *
     * @param id 预警短信配置主键
     * @return 结果
     */
    @Override
    public int deleteAlertSmsConfigById(Long id)
    {
        return alertSmsConfigMapper.deleteAlertSmsConfigById(id);
    }
}
