package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WxConvenienceServiceMapper;
import com.ruoyi.system.domain.WxConvenienceService;
import com.ruoyi.system.service.IWxConvenienceServiceService;

/**
 * 便民服务Service业务层处理
 *
 * @author ruoyi
 * @date 2023-04-10
 */
@Service
public class WxConvenienceServiceServiceImpl implements IWxConvenienceServiceService
{
    @Autowired
    private WxConvenienceServiceMapper wxConvenienceServiceMapper;

    /**
     * 查询便民服务
     *
     * @param uuid 便民服务主键
     * @return 便民服务
     */
    @Override
    public WxConvenienceService selectWxConvenienceServiceByUuid(String uuid)
    {
        return wxConvenienceServiceMapper.selectWxConvenienceServiceByUuid(uuid);
    }

    /**
     * 查询便民服务列表
     *
     * @param wxConvenienceService 便民服务
     * @return 便民服务
     */
    @Override
    public List<WxConvenienceService> selectWxConvenienceServiceList(WxConvenienceService wxConvenienceService)
    {
        return wxConvenienceServiceMapper.selectWxConvenienceServiceList(wxConvenienceService);
    }

    /**
     * 新增便民服务
     *
     * @param wxConvenienceService 便民服务
     * @return 结果
     */
    @Override
    public int insertWxConvenienceService(WxConvenienceService wxConvenienceService)
    {
        return wxConvenienceServiceMapper.insertWxConvenienceService(wxConvenienceService);
    }

    /**
     * 修改便民服务
     *
     * @param wxConvenienceService 便民服务
     * @return 结果
     */
    @Override
    public int updateWxConvenienceService(WxConvenienceService wxConvenienceService)
    {
        wxConvenienceService.setUpdateTime(DateUtils.getNowDate());
        return wxConvenienceServiceMapper.updateWxConvenienceService(wxConvenienceService);
    }

    /**
     * 批量删除便民服务
     *
     * @param uuids 需要删除的便民服务主键
     * @return 结果
     */
    @Override
    public int deleteWxConvenienceServiceByUuids(String[] uuids)
    {
        return wxConvenienceServiceMapper.deleteWxConvenienceServiceByUuids(uuids);
    }

    /**
     * 删除便民服务信息
     *
     * @param uuid 便民服务主键
     * @return 结果
     */
    @Override
    public int deleteWxConvenienceServiceByUuid(String uuid)
    {
        return wxConvenienceServiceMapper.deleteWxConvenienceServiceByUuid(uuid);
    }
}
