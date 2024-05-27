package com.ruoyi.app.mapper;

import java.util.List;
import com.ruoyi.app.domain.ServiceHome;
import com.ruoyi.app.domain.vo.ServiceHomeInfo;

/**
 * 首页服务Mapper接口
 *
 * @author ruoyi
 * @date 2024-04-25
 */
public interface ServiceHomeMapper
{
    /**
     * 查询首页服务
     *
     * @param id 首页服务主键
     * @return 首页服务
     */
    public ServiceHome selectServiceHomeById(Long id);

    /**
     * 查询首页服务列表
     *
     * @param serviceHome 首页服务
     * @return 首页服务集合
     */
    public List<ServiceHome> selectServiceHomeList(ServiceHome serviceHome);

    /**
     * 新增首页服务
     *
     * @param serviceHome 首页服务
     * @return 结果
     */
    public int insertServiceHome(ServiceHome serviceHome);

    /**
     * 修改首页服务
     *
     * @param serviceHome 首页服务
     * @return 结果
     */
    public int updateServiceHome(ServiceHome serviceHome);

    /**
     * 删除首页服务
     *
     * @param id 首页服务主键
     * @return 结果
     */
    public int deleteServiceHomeById(Long id);

    /**
     * 批量删除首页服务
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteServiceHomeByIds(Long[] ids);

    public List<ServiceHomeInfo> getServiceHomeInfoList(ServiceHome serviceHome);
}
