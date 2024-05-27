package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WxCulturalTourismMapper;
import com.ruoyi.system.domain.WxCulturalTourism;
import com.ruoyi.system.service.IWxCulturalTourismService;

/**
 * 本地文旅Service业务层处理
 *
 * @author ruoyi
 * @date 2023-06-19
 */
@Service
public class WxCulturalTourismServiceImpl implements IWxCulturalTourismService
{
    @Autowired
    private WxCulturalTourismMapper wxCulturalTourismMapper;

    /**
     * 查询本地文旅
     *
     * @param uuid 本地文旅主键
     * @return 本地文旅
     */
    @Override
    public WxCulturalTourism selectWxCulturalTourismByUuid(String uuid)
    {
        return wxCulturalTourismMapper.selectWxCulturalTourismByUuid(uuid);
    }

    /**
     * 查询本地文旅列表
     *
     * @param wxCulturalTourism 本地文旅
     * @return 本地文旅
     */
    @Override
    public List<WxCulturalTourism> selectWxCulturalTourismList(WxCulturalTourism wxCulturalTourism)
    {
        return wxCulturalTourismMapper.selectWxCulturalTourismList(wxCulturalTourism);
    }

    /**
     * 新增本地文旅
     *
     * @param wxCulturalTourism 本地文旅
     * @return 结果
     */
    @Override
    public int insertWxCulturalTourism(WxCulturalTourism wxCulturalTourism)
    {
        return wxCulturalTourismMapper.insertWxCulturalTourism(wxCulturalTourism);
    }

    /**
     * 修改本地文旅
     *
     * @param wxCulturalTourism 本地文旅
     * @return 结果
     */
    @Override
    public int updateWxCulturalTourism(WxCulturalTourism wxCulturalTourism)
    {
        wxCulturalTourism.setUpdateTime(DateUtils.getNowDate());
        return wxCulturalTourismMapper.updateWxCulturalTourism(wxCulturalTourism);
    }

    /**
     * 批量删除本地文旅
     *
     * @param uuids 需要删除的本地文旅主键
     * @return 结果
     */
    @Override
    public int deleteWxCulturalTourismByUuids(String[] uuids)
    {
        return wxCulturalTourismMapper.deleteWxCulturalTourismByUuids(uuids);
    }

    /**
     * 删除本地文旅信息
     *
     * @param uuid 本地文旅主键
     * @return 结果
     */
    @Override
    public int deleteWxCulturalTourismByUuid(String uuid)
    {
        return wxCulturalTourismMapper.deleteWxCulturalTourismByUuid(uuid);
    }
}
