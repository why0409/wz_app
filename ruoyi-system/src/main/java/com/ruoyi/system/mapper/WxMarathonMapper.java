package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.WxMarathon;

/**
 * 湾沚航空马拉松Mapper接口
 *
 * @author ruoyi
 * @date 2023-03-20
 */
public interface WxMarathonMapper
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
     * 删除湾沚航空马拉松
     *
     * @param uuid 湾沚航空马拉松主键
     * @return 结果
     */
    public int deleteWxMarathonByUuid(String uuid);

    /**
     * 批量删除湾沚航空马拉松
     *
     * @param uuids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxMarathonByUuids(String[] uuids);

    public int checkSortNum(int sortNum);
}

