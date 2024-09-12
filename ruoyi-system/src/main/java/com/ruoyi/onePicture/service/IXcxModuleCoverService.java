package com.ruoyi.onePicture.service;


import com.ruoyi.onePicture.domain.XcxModuleCover;

import java.util.List;

/**
 * 模块封面Service接口
 *
 * @author ruoyi
 * @date 2023-12-22
 */
public interface IXcxModuleCoverService
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
     * 批量删除模块封面
     *
     * @param ids 需要删除的模块封面主键集合
     * @return 结果
     */
    public int deleteXcxModuleCoverByIds(Long[] ids);

    /**
     * 删除模块封面信息
     *
     * @param id 模块封面主键
     * @return 结果
     */
    public int deleteXcxModuleCoverById(Long id);

    public XcxModuleCover selectXcxModuleCoverByModule(String module);

    public int updateXcxModuleCoverByModule(XcxModuleCover xcxModuleCover);
}
