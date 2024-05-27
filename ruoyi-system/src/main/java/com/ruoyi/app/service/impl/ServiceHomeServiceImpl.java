package com.ruoyi.app.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.app.domain.vo.ServiceHomeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.app.mapper.ServiceHomeMapper;
import com.ruoyi.app.domain.ServiceHome;
import com.ruoyi.app.service.IServiceHomeService;

/**
 * 首页服务Service业务层处理
 *
 * @author ruoyi
 * @date 2024-04-25
 */
@Service
public class ServiceHomeServiceImpl implements IServiceHomeService
{
    @Autowired
    private ServiceHomeMapper serviceHomeMapper;

    /**
     * 查询首页服务
     *
     * @param id 首页服务主键
     * @return 首页服务
     */
    @Override
    public ServiceHome selectServiceHomeById(Long id)
    {
        return serviceHomeMapper.selectServiceHomeById(id);
    }

    /**
     * 查询首页服务列表
     *
     * @param serviceHome 首页服务
     * @return 首页服务
     */
    @Override
    public List<ServiceHome> selectServiceHomeList(ServiceHome serviceHome)
    {
        return serviceHomeMapper.selectServiceHomeList(serviceHome);
    }

    /**
     * 新增首页服务
     *
     * @param serviceHome 首页服务
     * @return 结果
     */
    @Override
    public int insertServiceHome(ServiceHome serviceHome)
    {
        serviceHome.setCreateTime(DateUtils.getNowDate());
        return serviceHomeMapper.insertServiceHome(serviceHome);
    }

    /**
     * 修改首页服务
     *
     * @param serviceHome 首页服务
     * @return 结果
     */
    @Override
    public int updateServiceHome(ServiceHome serviceHome)
    {
        serviceHome.setUpdateTime(DateUtils.getNowDate());
        return serviceHomeMapper.updateServiceHome(serviceHome);
    }

    /**
     * 批量删除首页服务
     *
     * @param ids 需要删除的首页服务主键
     * @return 结果
     */
    @Override
    public int deleteServiceHomeByIds(Long[] ids)
    {
        return serviceHomeMapper.deleteServiceHomeByIds(ids);
    }

    /**
     * 删除首页服务信息
     *
     * @param id 首页服务主键
     * @return 结果
     */
    @Override
    public int deleteServiceHomeById(Long id)
    {
        return serviceHomeMapper.deleteServiceHomeById(id);
    }

    @Override
    public List<ServiceHomeInfo> getServiceHomeInfoList(ServiceHome serviceHome){
        return serviceHomeMapper.getServiceHomeInfoList(serviceHome);
    }
}
