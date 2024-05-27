package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.WxMiniPrograms;
import org.apache.ibatis.annotations.Param;

/**
 * 小程序模块Mapper接口
 *
 * @author ruoyi
 * @date 2023-07-17
 */
public interface WxMiniProgramsMapper
{
    /**
     * 查询小程序模块
     *
     * @param uuid 小程序模块主键
     * @return 小程序模块
     */
    public WxMiniPrograms selectWxMiniProgramsByUuid(String uuid);

    /**
     * 查询小程序模块列表
     *
     * @param wxMiniPrograms 小程序模块
     * @return 小程序模块集合
     */
    public List<WxMiniPrograms> selectWxMiniProgramsList(WxMiniPrograms wxMiniPrograms);

    /**
     * 新增小程序模块
     *
     * @param wxMiniPrograms 小程序模块
     * @return 结果
     */
    public int insertWxMiniPrograms(WxMiniPrograms wxMiniPrograms);

    /**
     * 修改小程序模块
     *
     * @param wxMiniPrograms 小程序模块
     * @return 结果
     */
    public int updateWxMiniPrograms(WxMiniPrograms wxMiniPrograms);

    /**
     * 删除小程序模块
     *
     * @param uuid 小程序模块主键
     * @return 结果
     */
    public int deleteWxMiniProgramsByUuid(String uuid);

    /**
     * 批量删除小程序模块
     *
     * @param uuids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxMiniProgramsByUuids(String[] uuids);

    public int checkSortNum(@Param("sortNum") int sortNum,
                            @Param("type") String type);
}
