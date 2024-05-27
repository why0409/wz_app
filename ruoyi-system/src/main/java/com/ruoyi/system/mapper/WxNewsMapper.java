package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.WxNews;
import org.apache.ibatis.annotations.Mapper;

/**
 * 【最近动态】Mapper接口
 *
 * @author ruoyi
 * @date 2022-12-01
 */
@Mapper
public interface WxNewsMapper
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
     * 删除【最近动态】
     *
     * @param uuid 【最近动态】主键
     * @return 结果
     */
    public int deleteWxNewsByUuid(String uuid);

    /**
     * 批量删除【最近动态】
     *
     * @param uuids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxNewsByUuids(String[] uuids);

    public int checkSortNum(int sortNum);

    List<WxNews> searchWxNewsList(String content);
}
