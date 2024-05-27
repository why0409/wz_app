package com.ruoyi.xcx.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.xcx.domain.XcxCameraType;

import java.util.List;

/**
 * 小程序---分类Service接口
 *
 * @author ruoyi
 * @date 2023-10-16
 */
public interface IXcxCameraFirstService
{
    /**
     * 查询小程序---分类
     *
     * @param id 小程序---分类主键
     * @return 小程序---分类
     */
    public XcxCameraType selectXcxCameraTypeById(Long id);

    /**
     * 查询小程序---分类列表
     *
     * @param xcxCameraType 小程序---分类
     * @return 小程序---分类集合
     */
    public List<XcxCameraType> selectXcxCameraTypeList(XcxCameraType xcxCameraType);

    /**
     * 新增小程序---分类
     *
     * @param xcxCameraType 小程序---分类
     * @return 结果
     */
    public AjaxResult insertXcxCameraType(XcxCameraType xcxCameraType);

    /**
     * 修改小程序---分类
     *
     * @param xcxCameraType 小程序---分类
     * @return 结果
     */
    public AjaxResult updateXcxCameraType(XcxCameraType xcxCameraType);

    /**
     * 批量删除小程序---分类
     *
     * @param ids 需要删除的小程序---分类主键集合
     * @return 结果
     */
    public int deleteXcxCameraTypeByIds(Long[] ids);

    /**
     * 删除小程序---分类信息
     *
     * @param id 小程序---分类主键
     * @return 结果
     */
    public int deleteXcxCameraTypeById(Long id);

    List<XcxCameraType> getAllTypeAndColumn();

    List<XcxCameraType> getAllTypeAndColumnByPermissions(String phone);
}
