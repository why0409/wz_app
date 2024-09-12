package com.ruoyi.onePicture.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.onePicture.domain.XcxContent;

import java.util.List;

/**
 * 小程序_内容详情Service接口
 *
 * @author ruoyi
 * @date 2023-10-16
 */
public interface IXcxContentService
{
    /**
     * 查询小程序_内容详情
     *
     * @param id 小程序_内容详情主键
     * @return 小程序_内容详情
     */
    public XcxContent selectXcxContentById(Long id);

    /**
     * 查询小程序_内容详情列表
     *
     * @param xcxContent 小程序_内容详情
     * @return 小程序_内容详情集合
     */
    public List<XcxContent> selectXcxContentList(XcxContent xcxContent);

    /**
     * 新增小程序_内容详情
     *
     * @param xcxContent 小程序_内容详情
     * @return 结果
     */
    public AjaxResult insertXcxContent(XcxContent xcxContent);

    /**
     * 修改小程序_内容详情
     *
     * @param xcxContent 小程序_内容详情
     * @return 结果
     */
    public AjaxResult updateXcxContent(XcxContent xcxContent);

    /**
     * 批量删除小程序_内容详情
     *
     * @param ids 需要删除的小程序_内容详情主键集合
     * @return 结果
     */
    public int deleteXcxContentByIds(Long[] ids);

    /**
     * 删除小程序_内容详情信息
     *
     * @param id 小程序_内容详情主键
     * @return 结果
     */
    public int deleteXcxContentById(Long id);

    List<XcxContent> selectByColumnId(Integer columnId, String contentName, String phone);

    List<XcxContent> getListByCollectionPhone(String phone);

    public List<XcxContent> searchContent(String phone, String contentName);
}
