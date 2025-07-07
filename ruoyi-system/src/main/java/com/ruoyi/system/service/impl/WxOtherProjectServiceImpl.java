package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.OtherProject;
import com.ruoyi.system.mapper.OtherProjectMapper;
import com.ruoyi.system.service.IWxOtherProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WxOtherProjectServiceImpl implements IWxOtherProjectService {


    @Autowired
    private OtherProjectMapper otherProjectMapper;

    /**
     * 查询其他项目
     *
     * @param uuid 其他项目主键
     * @return 其他项目
     */
    @Override
    public OtherProject selectOtherProjectByUuid(String uuid) {
        return otherProjectMapper.selectOtherProjectByUuid(uuid);
    }

    /**
     * 查询其他项目列表
     *
     * @param otherProject 其他项目
     * @return 其他项目
     */
    @Override
    public List<OtherProject> selectOtherProjectList(OtherProject otherProject) {
        return otherProjectMapper.selectOtherProjectList(otherProject);
    }

    /**
     * 新增其他项目
     *
     * @param otherProject 其他项目
     * @return 结果
     */
    @Override
    public int insertOtherProject(OtherProject otherProject) {
        return otherProjectMapper.insertOtherProject(otherProject);
    }

    /**
     * 修改其他项目
     *
     * @param otherProject 其他项目
     * @return 结果
     */
    @Override
    public int updateOtherProject(OtherProject otherProject) {
        return otherProjectMapper.updateOtherProject(otherProject);
    }

    /**
     * 批量删除其他项目
     *
     * @param uuids 需要删除的其他项目主键
     * @return 结果
     */
    @Override
    public int deleteOtherProjectByUuids(String[] uuids) {
        return otherProjectMapper.deleteOtherProjectByUuids(uuids);
    }

    /**
     * 删除其他项目信息
     *
     * @param uuid 其他项目主键
     * @return 结果
     */
    @Override
    public int deleteOtherProjectByUuid(String uuid) {
        return otherProjectMapper.deleteOtherProjectByUuid(uuid);
    }
}
