package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.WxCulturalTourism;

/**
 * 本地文旅Service接口
 *
 * @author ruoyi
 * @date 2023-06-19
 */
public interface IWxCulturalTourismService
{
    /**
     * 查询本地文旅
     *
     * @param uuid 本地文旅主键
     * @return 本地文旅
     */
    public WxCulturalTourism selectWxCulturalTourismByUuid(String uuid);

    /**
     * 查询本地文旅列表
     *
     * @param wxCulturalTourism 本地文旅
     * @return 本地文旅集合
     */
    public List<WxCulturalTourism> selectWxCulturalTourismList(WxCulturalTourism wxCulturalTourism);

    /**
     * 新增本地文旅
     *
     * @param wxCulturalTourism 本地文旅
     * @return 结果
     */
    public int insertWxCulturalTourism(WxCulturalTourism wxCulturalTourism);

    /**
     * 修改本地文旅
     *
     * @param wxCulturalTourism 本地文旅
     * @return 结果
     */
    public int updateWxCulturalTourism(WxCulturalTourism wxCulturalTourism);

    /**
     * 批量删除本地文旅
     *
     * @param uuids 需要删除的本地文旅主键集合
     * @return 结果
     */
    public int deleteWxCulturalTourismByUuids(String[] uuids);

    /**
     * 删除本地文旅信息
     *
     * @param uuid 本地文旅主键
     * @return 结果
     */
    public int deleteWxCulturalTourismByUuid(String uuid);
}
