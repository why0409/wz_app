package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.WxNews;

/**
 * 【最近动态】Service接口
 *
 * @author ruoyi
 * @date 2022-12-01
 */
public interface IWxNewsService
{
    /**
     * 查询【最近动态】
     *
     * @param uuid 【最近动态】主键
     * @return 【最近动态】
     */
    public WxNews selectWxNewsByUuid(String uuid);

    /**
     * 查询【最近动态】列表
     *
     * @param wxNews 【最近动态】
     * @return 【最近动态】集合
     */
    public List<WxNews> selectWxNewsList(WxNews wxNews);

    /**
     * 新增【最近动态】
     *
     * @param wxNews 【最近动态】
     * @return 结果
     */
    public int insertWxNews(WxNews wxNews);

    /**
     * 修改【最近动态】
     *
     * @param wxNews 【最近动态】
     * @return 结果
     */
    public int updateWxNews(WxNews wxNews);

    /**
     * 批量删除【最近动态】
     *
     * @param uuids 需要删除的【最近动态】主键集合
     * @return 结果
     */
    public int deleteWxNewsByUuids(String[] uuids);

    /**
     * 删除【最近动态】信息
     *
     * @param uuid 【最近动态】主键
     * @return 结果
     */
    public int deleteWxNewsByUuid(String uuid);

    public List<WxNews> searchWxNewsList(String content);
}
