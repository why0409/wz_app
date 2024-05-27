package com.ruoyi.xcx.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.xcx.domain.XcxColumn;

import java.util.List;

/**
 * 小程序--栏目Service接口
 *
 * @author ruoyi
 * @date 2023-10-16
 */
public interface IXcxColumnService
{
    /**
     * 查询小程序--栏目
     *
     * @param id 小程序--栏目主键
     * @return 小程序--栏目
     */
    public XcxColumn selectXcxColumnById(Long id);

    /**
     * 查询小程序--栏目列表
     *
     * @param xcxColumn 小程序--栏目
     * @return 小程序--栏目集合
     */
    public List<XcxColumn> selectXcxColumnList(XcxColumn xcxColumn);

    /**
     * 新增小程序--栏目
     *
     * @param xcxColumn 小程序--栏目
     * @return 结果
     */
    public AjaxResult insertXcxColumn(XcxColumn xcxColumn);

    /**
     * 修改小程序--栏目
     *
     * @param xcxColumn 小程序--栏目
     * @return 结果
     */
    public AjaxResult updateXcxColumn(XcxColumn xcxColumn);

    /**
     * 批量删除小程序--栏目
     *
     * @param ids 需要删除的小程序--栏目主键集合
     * @return 结果
     */
    public int deleteXcxColumnByIds(Long[] ids);

    /**
     * 删除小程序--栏目信息
     *
     * @param id 小程序--栏目主键
     * @return 结果
     */
    public int deleteXcxColumnById(Long id);

    List<XcxColumn> selectByTypeId(Integer typeId);
}
