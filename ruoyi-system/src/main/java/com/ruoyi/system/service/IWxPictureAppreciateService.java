package com.ruoyi.system.service;

import com.ruoyi.system.domain.WxPictureAppreciate;

import java.util.List;


/**
 * 图片欣赏Service接口
 *
 * @author ruoyi
 * @date 2023-02-08
 */
public interface IWxPictureAppreciateService
{
    /**
     * 查询图片欣赏
     *
     * @param uuid 图片欣赏主键
     * @return 图片欣赏
     */
    public WxPictureAppreciate selectWxPictureAppreciateByUuid(String uuid);

    /**
     * 查询图片欣赏列表
     *
     * @param wxPictureAppreciate 图片欣赏
     * @return 图片欣赏集合
     */
    public List<WxPictureAppreciate> selectWxPictureAppreciateList(WxPictureAppreciate wxPictureAppreciate);

    /**
     * 新增图片欣赏
     *
     * @param wxPictureAppreciate 图片欣赏
     * @return 结果
     */
    public int insertWxPictureAppreciate(WxPictureAppreciate wxPictureAppreciate);

    /**
     * 修改图片欣赏
     *
     * @param wxPictureAppreciate 图片欣赏
     * @return 结果
     */
    public int updateWxPictureAppreciate(WxPictureAppreciate wxPictureAppreciate);

    /**
     * 批量删除图片欣赏
     *
     * @param uuids 需要删除的图片欣赏主键集合
     * @return 结果
     */
    public int deleteWxPictureAppreciateByUuids(String[] uuids);

    /**
     * 删除图片欣赏信息
     *
     * @param uuid 图片欣赏主键
     * @return 结果
     */
    public int deleteWxPictureAppreciateByUuid(String uuid);
}
