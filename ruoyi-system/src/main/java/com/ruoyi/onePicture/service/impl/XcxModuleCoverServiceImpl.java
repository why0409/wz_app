package com.ruoyi.onePicture.service.impl;


import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.onePicture.domain.XcxModuleCover;
import com.ruoyi.onePicture.mapper.XcxModuleCoverMapper;
import com.ruoyi.onePicture.service.IXcxModuleCoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 模块封面Service业务层处理
 *
 * @author ruoyi
 * @date 2023-12-22
 */
@Service
public class XcxModuleCoverServiceImpl implements IXcxModuleCoverService
{
    @Autowired
    private XcxModuleCoverMapper xcxModuleCoverMapper;

    /**
     * 查询模块封面
     *
     * @param id 模块封面主键
     * @return 模块封面
     */
    @Override
    public XcxModuleCover selectXcxModuleCoverById(Long id)
    {
        return xcxModuleCoverMapper.selectXcxModuleCoverById(id);
    }

    /**
     * 查询模块封面列表
     *
     * @param xcxModuleCover 模块封面
     * @return 模块封面
     */
    @Override
    public List<XcxModuleCover> selectXcxModuleCoverList(XcxModuleCover xcxModuleCover)
    {
        return xcxModuleCoverMapper.selectXcxModuleCoverList(xcxModuleCover);
    }

    /**
     * 新增模块封面
     *
     * @param xcxModuleCover 模块封面
     * @return 结果
     */
    @Override
    public int insertXcxModuleCover(XcxModuleCover xcxModuleCover)
    {
        xcxModuleCover.setCreateTime(DateUtils.getNowDate());
        return xcxModuleCoverMapper.insertXcxModuleCover(xcxModuleCover);
    }

    /**
     * 修改模块封面
     *
     * @param xcxModuleCover 模块封面
     * @return 结果
     */
    @Override
    public int updateXcxModuleCover(XcxModuleCover xcxModuleCover)
    {
        xcxModuleCover.setUpdateTime(DateUtils.getNowDate());
        return xcxModuleCoverMapper.updateXcxModuleCover(xcxModuleCover);
    }

    /**
     * 批量删除模块封面
     *
     * @param ids 需要删除的模块封面主键
     * @return 结果
     */
    @Override
    public int deleteXcxModuleCoverByIds(Long[] ids)
    {
        return xcxModuleCoverMapper.deleteXcxModuleCoverByIds(ids);
    }

    /**
     * 删除模块封面信息
     *
     * @param id 模块封面主键
     * @return 结果
     */
    @Override
    public int deleteXcxModuleCoverById(Long id)
    {
        return xcxModuleCoverMapper.deleteXcxModuleCoverById(id);
    }

    @Override
    public XcxModuleCover selectXcxModuleCoverByModule(String module) {
        return xcxModuleCoverMapper.selectXcxModuleCoverByModule(module);
    }


    @Override
    public int updateXcxModuleCoverByModule(XcxModuleCover xcxModuleCover)
    {
        xcxModuleCover.setUpdateTime(DateUtils.getNowDate());
        return xcxModuleCoverMapper.updateXcxModuleCoverByModule(xcxModuleCover);
    }
}
