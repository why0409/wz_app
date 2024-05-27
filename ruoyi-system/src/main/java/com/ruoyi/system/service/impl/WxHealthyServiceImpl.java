package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WxHealthyMapper;
import com.ruoyi.system.domain.WxHealthy;
import com.ruoyi.system.service.IWxHealthyService;

/**
 * 健康板块Service业务层处理
 *
 * @author ruoyi
 * @date 2023-05-19
 */
@Service
public class WxHealthyServiceImpl implements IWxHealthyService
{
    @Autowired
    private WxHealthyMapper wxHealthyMapper;

    /**
     * 查询健康板块
     *
     * @param uuid 健康板块主键
     * @return 健康板块
     */
    @Override
    public WxHealthy selectWxHealthyByUuid(String uuid)
    {
        return wxHealthyMapper.selectWxHealthyByUuid(uuid);
    }

    /**
     * 查询健康板块列表
     *
     * @param wxHealthy 健康板块
     * @return 健康板块
     */
    @Override
    public List<WxHealthy> selectWxHealthyList(WxHealthy wxHealthy)
    {
        return wxHealthyMapper.selectWxHealthyList(wxHealthy);
    }

    /**
     * 新增健康板块
     *
     * @param wxHealthy 健康板块
     * @return 结果
     */
    @Override
    public int insertWxHealthy(WxHealthy wxHealthy)
    {
        return wxHealthyMapper.insertWxHealthy(wxHealthy);
    }

    /**
     * 修改健康板块
     *
     * @param wxHealthy 健康板块
     * @return 结果
     */
    @Override
    public int updateWxHealthy(WxHealthy wxHealthy)
    {
        wxHealthy.setUpdateTime(DateUtils.getNowDate());
        return wxHealthyMapper.updateWxHealthy(wxHealthy);
    }

    /**
     * 批量删除健康板块
     *
     * @param uuids 需要删除的健康板块主键
     * @return 结果
     */
    @Override
    public int deleteWxHealthyByUuids(String[] uuids)
    {
        return wxHealthyMapper.deleteWxHealthyByUuids(uuids);
    }

    /**
     * 删除健康板块信息
     *
     * @param uuid 健康板块主键
     * @return 结果
     */
    @Override
    public int deleteWxHealthyByUuid(String uuid)
    {
        return wxHealthyMapper.deleteWxHealthyByUuid(uuid);
    }
}
