package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.WxPictureNews;
import com.ruoyi.system.mapper.WxPictureNewsMapper;
import com.ruoyi.system.service.IWxPictureNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 图片新闻Service业务层处理
 *
 * @author ruoyi
 * @date 2023-02-08
 */
@Service
public class WxPictureNewsServiceImpl implements IWxPictureNewsService
{
    @Resource
    private WxPictureNewsMapper wxPictureNewsMapper;

    /**
     * 查询图片新闻
     *
     * @param uuid 图片新闻主键
     * @return 图片新闻
     */
    @Override
    public WxPictureNews selectWxPictureNewsByUuid(String uuid)
    {
        return wxPictureNewsMapper.selectWxPictureNewsByUuid(uuid);
    }

    /**
     * 查询图片新闻列表
     *
     * @param wxPictureNews 图片新闻
     * @return 图片新闻
     */
    @Override
    public List<WxPictureNews> selectWxPictureNewsList(WxPictureNews wxPictureNews)
    {
        return wxPictureNewsMapper.selectWxPictureNewsList(wxPictureNews);
    }

    /**
     * 新增图片新闻
     *
     * @param wxPictureNews 图片新闻
     * @return 结果
     */
    @Override
    public int insertWxPictureNews(WxPictureNews wxPictureNews)
    {
        return wxPictureNewsMapper.insertWxPictureNews(wxPictureNews);
    }

    /**
     * 修改图片新闻
     *
     * @param wxPictureNews 图片新闻
     * @return 结果
     */
    @Override
    public int updateWxPictureNews(WxPictureNews wxPictureNews)
    {
        return wxPictureNewsMapper.updateWxPictureNews(wxPictureNews);
    }

    /**
     * 批量删除图片新闻
     *
     * @param uuids 需要删除的图片新闻主键
     * @return 结果
     */
    @Override
    public int deleteWxPictureNewsByUuids(String[] uuids)
    {
        return wxPictureNewsMapper.deleteWxPictureNewsByUuids(uuids);
    }

    /**
     * 删除图片新闻信息
     *
     * @param uuid 图片新闻主键
     * @return 结果
     */
    @Override
    public int deleteWxPictureNewsByUuid(String uuid)
    {
        return wxPictureNewsMapper.deleteWxPictureNewsByUuid(uuid);
    }
}
