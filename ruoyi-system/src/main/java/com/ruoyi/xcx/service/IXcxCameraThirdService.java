package com.ruoyi.xcx.service;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.xcx.domain.XcxCameraContent;

import java.util.List;

/**
 * 小程序_内容详情Service接口
 *
 * @author ruoyi
 * @date 2023-10-16
 */
public interface IXcxCameraThirdService
{
    /**
     * 查询小程序_内容详情
     *
     * @param id 小程序_内容详情主键
     * @return 小程序_内容详情
     */
    public XcxCameraContent selectXcxCameraContentById(Long id);

    /**
     * 查询小程序_内容详情列表
     *
     * @param xcxCameraContent 小程序_内容详情
     * @return 小程序_内容详情集合
     */
    public List<XcxCameraContent> selectXcxCameraContentList(XcxCameraContent xcxCameraContent);

    /**
     * 新增小程序_内容详情
     *
     * @param xcxCameraContent 小程序_内容详情
     * @return 结果
     */
    public AjaxResult insertXcxCameraContent(XcxCameraContent xcxCameraContent);

    /**
     * 修改小程序_内容详情
     *
     * @param xcxCameraContent 小程序_内容详情
     * @return 结果
     */
    public AjaxResult updateXcxCameraContent(XcxCameraContent xcxCameraContent);

    /**
     * 批量删除小程序_内容详情
     *
     * @param ids 需要删除的小程序_内容详情主键集合
     * @return 结果
     */
    public int deleteXcxCameraContentByIds(Long[] ids);

    /**
     * 删除小程序_内容详情信息
     *
     * @param id 小程序_内容详情主键
     * @return 结果
     */
    public int deleteXcxCameraContentById(Long id);

    List<XcxCameraContent> selectByColumnId(Integer columnId, String contentName, String phone, String dept, String operators, Integer searchType);

    List<XcxCameraContent> getListByCollectionPhone(String phone);

    public List<XcxCameraContent> searchContent(String phone, String contentName);

    public List<JSONObject> selectDistinctDept();
}
