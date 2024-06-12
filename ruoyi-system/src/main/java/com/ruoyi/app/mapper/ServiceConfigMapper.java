package com.ruoyi.app.mapper;

import java.util.List;
import com.ruoyi.app.domain.ServiceConfig;
import com.ruoyi.app.domain.vo.ServiceConfigVo;
import org.apache.ibatis.annotations.Param;

/**
 * 服务配置Mapper接口
 *
 * @author ruoyi
 * @date 2024-04-25
 */
public interface ServiceConfigMapper
{
    /**
     * 查询服务配置
     *
     * @param id 服务配置主键
     * @return 服务配置
     */
    public ServiceConfig selectServiceConfigById(Long id);

    /**
     * 查询服务配置列表
     *
     * @param serviceConfig 服务配置
     * @return 服务配置集合
     */
    public List<ServiceConfig> selectServiceConfigList(ServiceConfig serviceConfig);

    /**
     * 新增服务配置
     *
     * @param serviceConfig 服务配置
     * @return 结果
     */
    public int insertServiceConfig(ServiceConfig serviceConfig);

    /**
     * 修改服务配置
     *
     * @param serviceConfig 服务配置
     * @return 结果
     */
    public int updateServiceConfig(ServiceConfig serviceConfig);

    /**
     * 删除服务配置
     *
     * @param id 服务配置主键
     * @return 结果
     */
    public int deleteServiceConfigById(Long id);

    /**
     * 批量删除服务配置
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteServiceConfigByIds(Long[] ids);

    List<ServiceConfig> searchDescriptionList(@Param("phone") String phone,
                                              @Param("description") String description);

    List<ServiceConfigVo> getFrequenceListByOpenid(String openid);

    public List<ServiceConfigVo> selectServiceConfigVoList(ServiceConfig serviceConfig);

    public List<ServiceConfig> getMenuByPhoneNew(String phone);

    public List<ServiceConfigVo> queryWxUserMenuByPhoneNew(String phone);

    public List<ServiceConfigVo> getRecentlyUsedListByOpenid(String openid);

    public List<ServiceConfig> getSmAndTjList();

    public int countByUuid(String uuid);
}
