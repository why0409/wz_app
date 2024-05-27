package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WxMiniProgramsMapper;
import com.ruoyi.system.domain.WxMiniPrograms;
import com.ruoyi.system.service.IWxMiniProgramsService;

/**
 * 小程序模块Service业务层处理
 *
 * @author ruoyi
 * @date 2023-07-17
 */
@Service
public class WxMiniProgramsServiceImpl implements IWxMiniProgramsService
{
    @Autowired
    private WxMiniProgramsMapper wxMiniProgramsMapper;

    /**
     * 查询小程序模块
     *
     * @param uuid 小程序模块主键
     * @return 小程序模块
     */
    @Override
    public WxMiniPrograms selectWxMiniProgramsByUuid(String uuid)
    {
        return wxMiniProgramsMapper.selectWxMiniProgramsByUuid(uuid);
    }

    /**
     * 查询小程序模块列表
     *
     * @param wxMiniPrograms 小程序模块
     * @return 小程序模块
     */
    @Override
    public List<WxMiniPrograms> selectWxMiniProgramsList(WxMiniPrograms wxMiniPrograms)
    {
        return wxMiniProgramsMapper.selectWxMiniProgramsList(wxMiniPrograms);
    }

    /**
     * 新增小程序模块
     *
     * @param wxMiniPrograms 小程序模块
     * @return 结果
     */
    @Override
    public int insertWxMiniPrograms(WxMiniPrograms wxMiniPrograms)
    {
        return wxMiniProgramsMapper.insertWxMiniPrograms(wxMiniPrograms);
    }

    /**
     * 修改小程序模块
     *
     * @param wxMiniPrograms 小程序模块
     * @return 结果
     */
    @Override
    public int updateWxMiniPrograms(WxMiniPrograms wxMiniPrograms)
    {
        wxMiniPrograms.setUpdateTime(DateUtils.getNowDate());
        return wxMiniProgramsMapper.updateWxMiniPrograms(wxMiniPrograms);
    }

    /**
     * 批量删除小程序模块
     *
     * @param uuids 需要删除的小程序模块主键
     * @return 结果
     */
    @Override
    public int deleteWxMiniProgramsByUuids(String[] uuids)
    {
        return wxMiniProgramsMapper.deleteWxMiniProgramsByUuids(uuids);
    }

    /**
     * 删除小程序模块信息
     *
     * @param uuid 小程序模块主键
     * @return 结果
     */
    @Override
    public int deleteWxMiniProgramsByUuid(String uuid)
    {
        return wxMiniProgramsMapper.deleteWxMiniProgramsByUuid(uuid);
    }
}
