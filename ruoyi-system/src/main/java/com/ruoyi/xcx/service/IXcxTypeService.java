package com.ruoyi.xcx.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.xcx.domain.XcxType;

import java.util.List;

/**
 * 小程序---分类Service接口
 *
 * @author ruoyi
 * @date 2023-10-16
 */
public interface IXcxTypeService
{
    /**
     * 查询小程序---分类
     *
     * @param id 小程序---分类主键
     * @return 小程序---分类
     */
    public XcxType selectXcxTypeById(Long id);

    /**
     * 查询小程序---分类列表
     *
     * @param xcxType 小程序---分类
     * @return 小程序---分类集合
     */
    public List<XcxType> selectXcxTypeList(XcxType xcxType);

    /**
     * 新增小程序---分类
     *
     * @param xcxType 小程序---分类
     * @return 结果
     */
    public AjaxResult insertXcxType(XcxType xcxType);

    /**
     * 修改小程序---分类
     *
     * @param xcxType 小程序---分类
     * @return 结果
     */
    public AjaxResult updateXcxType(XcxType xcxType);

    /**
     * 批量删除小程序---分类
     *
     * @param ids 需要删除的小程序---分类主键集合
     * @return 结果
     */
    public int deleteXcxTypeByIds(Long[] ids);

    /**
     * 删除小程序---分类信息
     *
     * @param id 小程序---分类主键
     * @return 结果
     */
    public int deleteXcxTypeById(Long id);

    List<XcxType> getAllTypeAndColumn();
}
