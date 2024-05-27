package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WxNewsMapper;
import com.ruoyi.system.domain.WxNews;
import com.ruoyi.system.service.IWxNewsService;

/**
 * 【最近动态】Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-01
 */
@Service
public class WxNewsServiceImpl implements IWxNewsService
{
    @Autowired
    private WxNewsMapper wxNewsMapper;

    /**
     * 查询【最近动态】
     *
     * @param uuid 【最近动态】主键
     * @return 【最近动态】
     */
    @Override
    public WxNews selectWxNewsByUuid(String uuid)
    {
        return wxNewsMapper.selectWxNewsByUuid(uuid);
    }

    /**
     * 查询【最近动态】列表
     *
     * @param wxNews 【最近动态】
     * @return 【最近动态】
     */
    @Override
    public List<WxNews> selectWxNewsList(WxNews wxNews)
    {
        return wxNewsMapper.selectWxNewsList(wxNews);
    }

    /**
     * 新增【最近动态】
     *
     * @param wxNews 【最近动态】
     * @return 结果
     */
    @Override
    public int insertWxNews(WxNews wxNews)
    {
        return wxNewsMapper.insertWxNews(wxNews);
    }

    /**
     * 修改【最近动态】
     *
     * @param wxNews 【最近动态】
     * @return 结果
     */
    @Override
    public int updateWxNews(WxNews wxNews)
    {
        return wxNewsMapper.updateWxNews(wxNews);
    }

    /**
     * 批量删除【最近动态】
     *
     * @param uuids 需要删除的【最近动态】主键
     * @return 结果
     */
    @Override
    public int deleteWxNewsByUuids(String[] uuids)
    {
        return wxNewsMapper.deleteWxNewsByUuids(uuids);
    }

    /**
     * 删除【最近动态】信息
     *
     * @param uuid 【最近动态】主键
     * @return 结果
     */
    @Override
    public int deleteWxNewsByUuid(String uuid)
    {
        return wxNewsMapper.deleteWxNewsByUuid(uuid);
    }

    @Override
    public List<WxNews> searchWxNewsList(String content){
        return wxNewsMapper.searchWxNewsList(content);
    }
}
