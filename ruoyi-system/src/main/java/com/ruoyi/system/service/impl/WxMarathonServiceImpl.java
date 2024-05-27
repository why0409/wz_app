package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WxMarathonMapper;
import com.ruoyi.system.domain.WxMarathon;
import com.ruoyi.system.service.IWxMarathonService;

/**
 * 湾沚航空马拉松Service业务层处理
 *
 * @author ruoyi
 * @date 2023-03-20
 */
@Service
public class WxMarathonServiceImpl implements IWxMarathonService
{
    @Autowired
    private WxMarathonMapper wxMarathonMapper;

    /**
     * 查询湾沚航空马拉松
     *
     * @param uuid 湾沚航空马拉松主键
     * @return 湾沚航空马拉松
     */
    @Override
    public WxMarathon selectWxMarathonByUuid(String uuid)
    {
        return wxMarathonMapper.selectWxMarathonByUuid(uuid);
    }

    /**
     * 查询湾沚航空马拉松列表
     *
     * @param wxMarathon 湾沚航空马拉松
     * @return 湾沚航空马拉松
     */
    @Override
    public List<WxMarathon> selectWxMarathonList(WxMarathon wxMarathon)
    {
        return wxMarathonMapper.selectWxMarathonList(wxMarathon);
    }

    /**
     * 新增湾沚航空马拉松
     *
     * @param wxMarathon 湾沚航空马拉松
     * @return 结果
     */
    @Override
    public int insertWxMarathon(WxMarathon wxMarathon)
    {
        return wxMarathonMapper.insertWxMarathon(wxMarathon);
    }

    /**
     * 修改湾沚航空马拉松
     *
     * @param wxMarathon 湾沚航空马拉松
     * @return 结果
     */
    @Override
    public int updateWxMarathon(WxMarathon wxMarathon)
    {
        wxMarathon.setUpdateTime(DateUtils.getNowDate());
        return wxMarathonMapper.updateWxMarathon(wxMarathon);
    }

    /**
     * 批量删除湾沚航空马拉松
     *
     * @param uuids 需要删除的湾沚航空马拉松主键
     * @return 结果
     */
    @Override
    public int deleteWxMarathonByUuids(String[] uuids)
    {
        return wxMarathonMapper.deleteWxMarathonByUuids(uuids);
    }

    /**
     * 删除湾沚航空马拉松信息
     *
     * @param uuid 湾沚航空马拉松主键
     * @return 结果
     */
    @Override
    public int deleteWxMarathonByUuid(String uuid)
    {
        return wxMarathonMapper.deleteWxMarathonByUuid(uuid);
    }
}

