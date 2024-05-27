package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.WxMarathon;

/**
 * 湾沚航空马拉松Service接口
 *
 * @author ruoyi
 * @date 2023-03-20
 */
public interface IWxMarathonService
{
    /**
     * 查询湾沚航空马拉松
     *
     * @param uuid 湾沚航空马拉松主键
     * @return 湾沚航空马拉松
     */
    public WxMarathon selectWxMarathonByUuid(String uuid);

    /**
     * 查询湾沚航空马拉松列表
     *
     * @param wxMarathon 湾沚航空马拉松
     * @return 湾沚航空马拉松集合
     */
    public List<WxMarathon> selectWxMarathonList(WxMarathon wxMarathon);

    /**
     * 新增湾沚航空马拉松
     *
     * @param wxMarathon 湾沚航空马拉松
     * @return 结果
     */
    public int insertWxMarathon(WxMarathon wxMarathon);

    /**
     * 修改湾沚航空马拉松
     *
     * @param wxMarathon 湾沚航空马拉松
     * @return 结果
     */
    public int updateWxMarathon(WxMarathon wxMarathon);

    /**
     * 批量删除湾沚航空马拉松
     *
     * @param uuids 需要删除的湾沚航空马拉松主键集合
     * @return 结果
     */
    public int deleteWxMarathonByUuids(String[] uuids);

    /**
     * 删除湾沚航空马拉松信息
     *
     * @param uuid 湾沚航空马拉松主键
     * @return 结果
     */
    public int deleteWxMarathonByUuid(String uuid);
}

