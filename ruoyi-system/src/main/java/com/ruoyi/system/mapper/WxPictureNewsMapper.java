package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.WxPictureNews;

import java.util.List;

/**
 * 图片新闻Mapper接口
 *
 * @author ruoyi
 * @date 2023-02-08
 */
public interface WxPictureNewsMapper
{
    /**
     * 查询图片新闻
     *
     * @param uuid 图片新闻主键
     * @return 图片新闻
     */
    public WxPictureNews selectWxPictureNewsByUuid(String uuid);

    /**
     * 查询图片新闻列表
     *
     * @param wxPictureNews 图片新闻
     * @return 图片新闻集合
     */
    public List<WxPictureNews> selectWxPictureNewsList(WxPictureNews wxPictureNews);

    /**
     * 新增图片新闻
     *
     * @param wxPictureNews 图片新闻
     * @return 结果
     */
    public int insertWxPictureNews(WxPictureNews wxPictureNews);

    /**
     * 修改图片新闻
     *
     * @param wxPictureNews 图片新闻
     * @return 结果
     */
    public int updateWxPictureNews(WxPictureNews wxPictureNews);

    /**
     * 删除图片新闻
     *
     * @param uuid 图片新闻主键
     * @return 结果
     */
    public int deleteWxPictureNewsByUuid(String uuid);

    /**
     * 批量删除图片新闻
     *
     * @param uuids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxPictureNewsByUuids(String[] uuids);

    public int checkSortNum(int sortNum);
}
