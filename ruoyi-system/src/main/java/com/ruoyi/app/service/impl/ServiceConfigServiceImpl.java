package com.ruoyi.app.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.app.domain.vo.ServiceConfigVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.app.mapper.ServiceConfigMapper;
import com.ruoyi.app.domain.ServiceConfig;
import com.ruoyi.app.service.IServiceConfigService;

/**
 * 服务配置Service业务层处理
 *
 * @author ruoyi
 * @date 2024-04-25
 */
@Service
public class ServiceConfigServiceImpl implements IServiceConfigService
{
    @Autowired
    private ServiceConfigMapper serviceConfigMapper;

    /**
     * 查询服务配置
     *
     * @param id 服务配置主键
     * @return 服务配置
     */
    @Override
    public ServiceConfig selectServiceConfigById(Long id)
    {
        return serviceConfigMapper.selectServiceConfigById(id);
    }

    /**
     * 查询服务配置列表
     *
     * @param serviceConfig 服务配置
     * @return 服务配置
     */
    @Override
    public List<ServiceConfig> selectServiceConfigList(ServiceConfig serviceConfig)
    {
        return serviceConfigMapper.selectServiceConfigList(serviceConfig);
    }

    /**
     * 新增服务配置
     *
     * @param serviceConfig 服务配置
     * @return 结果
     */
    @Override
    public int insertServiceConfig(ServiceConfig serviceConfig)
    {
        serviceConfig.setCreateTime(DateUtils.getNowDate());
        return serviceConfigMapper.insertServiceConfig(serviceConfig);
    }

    /**
     * 修改服务配置
     *
     * @param serviceConfig 服务配置
     * @return 结果
     */
    @Override
    public int updateServiceConfig(ServiceConfig serviceConfig)
    {
        serviceConfig.setUpdateTime(DateUtils.getNowDate());
        return serviceConfigMapper.updateServiceConfig(serviceConfig);
    }

    /**
     * 批量删除服务配置
     *
     * @param ids 需要删除的服务配置主键
     * @return 结果
     */
    @Override
    public int deleteServiceConfigByIds(Long[] ids)
    {
        return serviceConfigMapper.deleteServiceConfigByIds(ids);
    }

    /**
     * 删除服务配置信息
     *
     * @param id 服务配置主键
     * @return 结果
     */
    @Override
    public int deleteServiceConfigById(Long id)
    {
        return serviceConfigMapper.deleteServiceConfigById(id);
    }

    @Override
    public List<ServiceConfig> searchDescriptionList(String phone, String description) {
        return serviceConfigMapper.searchDescriptionList(phone, description);
    }

    @Override
    public List<ServiceConfigVo> getFrequenceListByOpenid(String openid) {
        return serviceConfigMapper.getFrequenceListByOpenid(openid);
    }

    @Override
    public List<ServiceConfigVo> selectServiceConfigVoList(ServiceConfig serviceConfig) {
        return serviceConfigMapper.selectServiceConfigVoList(serviceConfig);
    }

    @Override
    public List<ServiceConfig> getMenuByPhoneNew(String phone) {
        return serviceConfigMapper.getMenuByPhoneNew(phone);
    }

    @Override
    public List<ServiceConfigVo> queryWxUserMenuByPhoneNew(String phone) {
        return serviceConfigMapper.queryWxUserMenuByPhoneNew(phone);
    }

    @Override
    public List<ServiceConfigVo> getRecentlyUsedListByOpenid(String openid){
        return serviceConfigMapper.getRecentlyUsedListByOpenid(openid);
    }

    @Override
    public List<ServiceConfig> getSmAndTjList(){
        return serviceConfigMapper.getSmAndTjList();
    }

    @Override
    public int countByUuid(String uuid){
        return serviceConfigMapper.countByUuid(uuid);
    }
}
