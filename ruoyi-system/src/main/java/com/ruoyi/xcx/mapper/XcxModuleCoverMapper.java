package com.ruoyi.xcx.mapper;

import com.ruoyi.xcx.domain.XcxModuleCover;

import java.util.List;

/**
 * 模块封面Mapper接口
 *
 * @author ruoyi
 * @date 2023-12-22
 */
public interface XcxModuleCoverMapper
{
    /**
     * 查询模块封面
     *
     * @param id 模块封面主键
     * @return 模块封面
     */
    public XcxModuleCover selectXcxModuleCoverById(Long id);

    /**
     * 查询模块封面列表
     *
     * @param xcxModuleCover 模块封面
     * @return 模块封面集合
     */
    public List<XcxModuleCover> selectXcxModuleCoverList(XcxModuleCover xcxModuleCover);

    /**
     * 新增模块封面
     *
     * @param xcxModuleCover 模块封面
     * @return 结果
     */
    public int insertXcxModuleCover(XcxModuleCover xcxModuleCover);

    /**
     * 修改模块封面
     *
     * @param xcxModuleCover 模块封面
     * @return 结果
     */
    public int updateXcxModuleCover(XcxModuleCover xcxModuleCover);

    /**
     * 删除模块封面
     *
     * @param id 模块封面主键
     * @return 结果
     */
    public int deleteXcxModuleCoverById(Long id);

    /**
     * 批量删除模块封面
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXcxModuleCoverByIds(Long[] ids);

    public XcxModuleCover selectXcxModuleCoverByModule(String module);

    public int updateXcxModuleCoverByModule(XcxModuleCover xcxModuleCover);
}

