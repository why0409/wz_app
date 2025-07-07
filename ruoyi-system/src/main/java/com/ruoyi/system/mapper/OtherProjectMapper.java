package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.OtherProject;

import java.util.List;

public interface OtherProjectMapper {
    /**
     * 查询湾沚房源政策简介
     *
     * @param uuid 湾沚房源政策简介主键
     * @return 湾沚房源政策简介
     */
    OtherProject selectOtherProjectByUuid(String uuid);

    /**
     * 查询湾沚房源政策简介列表
     *
     * @param otherProject 湾沚房源政策简介
     * @return 湾沚房源政策简介集合
     */
    List<OtherProject> selectOtherProjectList(OtherProject otherProject);

    /**
     * 新增湾沚房源政策简介
     *
     * @param otherProject 湾沚房源政策简介
     * @return 结果
     */
    int insertOtherProject(OtherProject otherProject);

    /**
     * 修改湾沚房源政策简介
     *
     * @param otherProject 湾沚房源政策简介
     * @return 结果
     */
    int updateOtherProject(OtherProject otherProject);

    /**
     * 删除湾沚房源政策简介
     *
     * @param uuid 湾沚房源政策简介主键
     * @return 结果
     */
    int deleteOtherProjectByUuid(String uuid);

    /**
     * 批量删除湾沚房源政策简介
     *
     * @param uuids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteOtherProjectByUuids(String[] uuids);

    int checkSortNum(int sortNum);
}
