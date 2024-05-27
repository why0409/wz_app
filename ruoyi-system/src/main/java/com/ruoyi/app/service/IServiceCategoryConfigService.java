package com.ruoyi.app.service;

import java.util.List;
import com.ruoyi.app.domain.ServiceCategoryConfig;
import com.ruoyi.app.domain.vo.ServiceCategoryConfigVo;

/**
 * 服务类别配置Service接口
 *
 * @author ruoyi
 * @date 2024-04-25
 */
public interface IServiceCategoryConfigService
{
    /**
     * 查询服务类别配置
     *
     * @param id 服务类别配置主键
     * @return 服务类别配置
     */
    public ServiceCategoryConfig selectServiceCategoryConfigById(Long id);

    /**
     * 查询服务类别配置列表
     *
     * @param serviceCategoryConfig 服务类别配置
     * @return 服务类别配置集合
     */
    public List<ServiceCategoryConfig> selectServiceCategoryConfigList(ServiceCategoryConfig serviceCategoryConfig);

    /**
     * 新增服务类别配置
     *
     * @param serviceCategoryConfig 服务类别配置
     * @return 结果
     */
    public int insertServiceCategoryConfig(ServiceCategoryConfig serviceCategoryConfig);

    /**
     * 修改服务类别配置
     *
     * @param serviceCategoryConfig 服务类别配置
     * @return 结果
     */
    public int updateServiceCategoryConfig(ServiceCategoryConfig serviceCategoryConfig);

    /**
     * 批量删除服务类别配置
     *
     * @param ids 需要删除的服务类别配置主键集合
     * @return 结果
     */
    public int deleteServiceCategoryConfigByIds(Long[] ids);

    /**
     * 删除服务类别配置信息
     *
     * @param id 服务类别配置主键
     * @return 结果
     */
    public int deleteServiceCategoryConfigById(Long id);


    public List<ServiceCategoryConfigVo> selectVoList(ServiceCategoryConfig serviceCategoryConfig);

}
