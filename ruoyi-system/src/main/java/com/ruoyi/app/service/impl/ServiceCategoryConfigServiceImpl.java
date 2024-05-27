package com.ruoyi.app.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.app.domain.vo.ServiceCategoryConfigVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.app.mapper.ServiceCategoryConfigMapper;
import com.ruoyi.app.domain.ServiceCategoryConfig;
import com.ruoyi.app.service.IServiceCategoryConfigService;

/**
 * 服务类别配置Service业务层处理
 *
 * @author ruoyi
 * @date 2024-04-25
 */
@Service
public class ServiceCategoryConfigServiceImpl implements IServiceCategoryConfigService
{
    @Autowired
    private ServiceCategoryConfigMapper serviceCategoryConfigMapper;

    /**
     * 查询服务类别配置
     *
     * @param id 服务类别配置主键
     * @return 服务类别配置
     */
    @Override
    public ServiceCategoryConfig selectServiceCategoryConfigById(Long id)
    {
        return serviceCategoryConfigMapper.selectServiceCategoryConfigById(id);
    }

    /**
     * 查询服务类别配置列表
     *
     * @param serviceCategoryConfig 服务类别配置
     * @return 服务类别配置
     */
    @Override
    public List<ServiceCategoryConfig> selectServiceCategoryConfigList(ServiceCategoryConfig serviceCategoryConfig)
    {
        return serviceCategoryConfigMapper.selectServiceCategoryConfigList(serviceCategoryConfig);
    }

    /**
     * 新增服务类别配置
     *
     * @param serviceCategoryConfig 服务类别配置
     * @return 结果
     */
    @Override
    public int insertServiceCategoryConfig(ServiceCategoryConfig serviceCategoryConfig)
    {
        serviceCategoryConfig.setCreateTime(DateUtils.getNowDate());
        return serviceCategoryConfigMapper.insertServiceCategoryConfig(serviceCategoryConfig);
    }

    /**
     * 修改服务类别配置
     *
     * @param serviceCategoryConfig 服务类别配置
     * @return 结果
     */
    @Override
    public int updateServiceCategoryConfig(ServiceCategoryConfig serviceCategoryConfig)
    {
        serviceCategoryConfig.setUpdateTime(DateUtils.getNowDate());
        return serviceCategoryConfigMapper.updateServiceCategoryConfig(serviceCategoryConfig);
    }

    /**
     * 批量删除服务类别配置
     *
     * @param ids 需要删除的服务类别配置主键
     * @return 结果
     */
    @Override
    public int deleteServiceCategoryConfigByIds(Long[] ids)
    {
        return serviceCategoryConfigMapper.deleteServiceCategoryConfigByIds(ids);
    }

    /**
     * 删除服务类别配置信息
     *
     * @param id 服务类别配置主键
     * @return 结果
     */
    @Override
    public int deleteServiceCategoryConfigById(Long id)
    {
        return serviceCategoryConfigMapper.deleteServiceCategoryConfigById(id);
    }

    @Override
    public List<ServiceCategoryConfigVo> selectVoList(ServiceCategoryConfig serviceCategoryConfig){
        return serviceCategoryConfigMapper.selectVoList(serviceCategoryConfig);
    }

}
