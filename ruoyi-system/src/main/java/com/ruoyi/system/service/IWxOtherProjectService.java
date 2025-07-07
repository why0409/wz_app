package com.ruoyi.system.service;

import com.ruoyi.system.domain.OtherProject;

import java.util.List;

public interface IWxOtherProjectService {
    /**
     * 查询其他项目
     *
     * @param uuid 其他项目主键
     * @return 其他项目
     */
    public OtherProject selectOtherProjectByUuid(String uuid);

    /**
     * 查询其他项目列表
     *
     * @param OtherProject 其他项目
     * @return 其他项目集合
     */
    public List<OtherProject> selectOtherProjectList(OtherProject OtherProject);

    /**
     * 新增其他项目
     *
     * @param OtherProject 其他项目
     * @return 结果
     */
    public int insertOtherProject(OtherProject OtherProject);

    /**
     * 修改其他项目
     *
     * @param OtherProject 其他项目
     * @return 结果
     */
    public int updateOtherProject(OtherProject OtherProject);

    /**
     * 批量删除其他项目
     *
     * @param uuids 需要删除的其他项目主键集合
     * @return 结果
     */
    public int deleteOtherProjectByUuids(String[] uuids);

    /**
     * 删除其他项目信息
     *
     * @param uuid 其他项目主键
     * @return 结果
     */
    public int deleteOtherProjectByUuid(String uuid);
}
