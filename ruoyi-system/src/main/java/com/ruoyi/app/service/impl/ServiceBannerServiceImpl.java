package com.ruoyi.app.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.app.mapper.ServiceBannerMapper;
import com.ruoyi.app.domain.ServiceBanner;
import com.ruoyi.app.service.IServiceBannerService;

/**
 * 服务banner图轮播Service业务层处理
 *
 * @author ruoyi
 * @date 2024-04-30
 */
@Service
public class ServiceBannerServiceImpl implements IServiceBannerService
{
    @Autowired
    private ServiceBannerMapper serviceBannerMapper;

    /**
     * 查询服务banner图轮播
     *
     * @param id 服务banner图轮播主键
     * @return 服务banner图轮播
     */
    @Override
    public ServiceBanner selectServiceBannerById(Long id)
    {
        return serviceBannerMapper.selectServiceBannerById(id);
    }

    /**
     * 查询服务banner图轮播列表
     *
     * @param serviceBanner 服务banner图轮播
     * @return 服务banner图轮播
     */
    @Override
    public List<ServiceBanner> selectServiceBannerList(ServiceBanner serviceBanner)
    {
        return serviceBannerMapper.selectServiceBannerList(serviceBanner);
    }

    /**
     * 新增服务banner图轮播
     *
     * @param serviceBanner 服务banner图轮播
     * @return 结果
     */
    @Override
    public int insertServiceBanner(ServiceBanner serviceBanner)
    {
        serviceBanner.setCreateTime(DateUtils.getNowDate());
        return serviceBannerMapper.insertServiceBanner(serviceBanner);
    }

    /**
     * 修改服务banner图轮播
     *
     * @param serviceBanner 服务banner图轮播
     * @return 结果
     */
    @Override
    public int updateServiceBanner(ServiceBanner serviceBanner)
    {
        serviceBanner.setUpdateTime(DateUtils.getNowDate());
        return serviceBannerMapper.updateServiceBanner(serviceBanner);
    }

    /**
     * 批量删除服务banner图轮播
     *
     * @param ids 需要删除的服务banner图轮播主键
     * @return 结果
     */
    @Override
    public int deleteServiceBannerByIds(Long[] ids)
    {
        return serviceBannerMapper.deleteServiceBannerByIds(ids);
    }

    /**
     * 删除服务banner图轮播信息
     *
     * @param id 服务banner图轮播主键
     * @return 结果
     */
    @Override
    public int deleteServiceBannerById(Long id)
    {
        return serviceBannerMapper.deleteServiceBannerById(id);
    }
}
