package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.WxConvenienceService;

/**
 * 便民服务Service接口
 *
 * @author ruoyi
 * @date 2023-04-10
 */
public interface IWxConvenienceServiceService
{
    /**
     * 查询便民服务
     *
     * @param uuid 便民服务主键
     * @return 便民服务
     */
    public WxConvenienceService selectWxConvenienceServiceByUuid(String uuid);

    /**
     * 查询便民服务列表
     *
     * @param wxConvenienceService 便民服务
     * @return 便民服务集合
     */
    public List<WxConvenienceService> selectWxConvenienceServiceList(WxConvenienceService wxConvenienceService);

    /**
     * 新增便民服务
     *
     * @param wxConvenienceService 便民服务
     * @return 结果
     */
    public int insertWxConvenienceService(WxConvenienceService wxConvenienceService);

    /**
     * 修改便民服务
     *
     * @param wxConvenienceService 便民服务
     * @return 结果
     */
    public int updateWxConvenienceService(WxConvenienceService wxConvenienceService);

    /**
     * 批量删除便民服务
     *
     * @param uuids 需要删除的便民服务主键集合
     * @return 结果
     */
    public int deleteWxConvenienceServiceByUuids(String[] uuids);

    /**
     * 删除便民服务信息
     *
     * @param uuid 便民服务主键
     * @return 结果
     */
    public int deleteWxConvenienceServiceByUuid(String uuid);
}
