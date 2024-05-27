package com.ruoyi.app.mapper;

import java.util.List;
import com.ruoyi.app.domain.ServiceBanner;

/**
 * 服务banner图轮播Mapper接口
 *
 * @author ruoyi
 * @date 2024-04-30
 */
public interface ServiceBannerMapper
{
    /**
     * 查询服务banner图轮播
     *
     * @param id 服务banner图轮播主键
     * @return 服务banner图轮播
     */
    public ServiceBanner selectServiceBannerById(Long id);

    /**
     * 查询服务banner图轮播列表
     *
     * @param serviceBanner 服务banner图轮播
     * @return 服务banner图轮播集合
     */
    public List<ServiceBanner> selectServiceBannerList(ServiceBanner serviceBanner);

    /**
     * 新增服务banner图轮播
     *
     * @param serviceBanner 服务banner图轮播
     * @return 结果
     */
    public int insertServiceBanner(ServiceBanner serviceBanner);

    /**
     * 修改服务banner图轮播
     *
     * @param serviceBanner 服务banner图轮播
     * @return 结果
     */
    public int updateServiceBanner(ServiceBanner serviceBanner);

    /**
     * 删除服务banner图轮播
     *
     * @param id 服务banner图轮播主键
     * @return 结果
     */
    public int deleteServiceBannerById(Long id);

    /**
     * 批量删除服务banner图轮播
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteServiceBannerByIds(Long[] ids);
}
