package com.ruoyi.app.mapper;

import java.util.List;
import com.ruoyi.app.domain.AlertSmsConfig;

/**
 * 预警短信配置Mapper接口
 *
 * @author ruoyi
 * @date 2024-05-06
 */
public interface AlertSmsConfigMapper
{
    /**
     * 查询预警短信配置
     *
     * @param id 预警短信配置主键
     * @return 预警短信配置
     */
    public AlertSmsConfig selectAlertSmsConfigById(Long id);

    /**
     * 查询预警短信配置列表
     *
     * @param alertSmsConfig 预警短信配置
     * @return 预警短信配置集合
     */
    public List<AlertSmsConfig> selectAlertSmsConfigList(AlertSmsConfig alertSmsConfig);

    /**
     * 新增预警短信配置
     *
     * @param alertSmsConfig 预警短信配置
     * @return 结果
     */
    public int insertAlertSmsConfig(AlertSmsConfig alertSmsConfig);

    /**
     * 修改预警短信配置
     *
     * @param alertSmsConfig 预警短信配置
     * @return 结果
     */
    public int updateAlertSmsConfig(AlertSmsConfig alertSmsConfig);

    /**
     * 删除预警短信配置
     *
     * @param id 预警短信配置主键
     * @return 结果
     */
    public int deleteAlertSmsConfigById(Long id);

    /**
     * 批量删除预警短信配置
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAlertSmsConfigByIds(Long[] ids);
}
