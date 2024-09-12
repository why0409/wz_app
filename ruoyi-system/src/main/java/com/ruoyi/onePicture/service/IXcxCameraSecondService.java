package com.ruoyi.onePicture.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.onePicture.domain.XcxCameraColumn;

import java.util.List;

/**
 * 小程序--栏目Service接口
 *
 * @author ruoyi
 * @date 2023-10-16
 */
public interface IXcxCameraSecondService
{
    /**
     * 查询小程序--栏目
     *
     * @param id 小程序--栏目主键
     * @return 小程序--栏目
     */
    public XcxCameraColumn selectXcxCameraColumnById(Long id);

    /**
     * 查询小程序--栏目列表
     *
     * @param xcxCameraColumn 小程序--栏目
     * @return 小程序--栏目集合
     */
    public List<XcxCameraColumn> selectXcxCameraColumnList(XcxCameraColumn xcxCameraColumn);

    /**
     * 新增小程序--栏目
     *
     * @param XcxCameraColumn 小程序--栏目
     * @return 结果
     */
    public AjaxResult insertXcxCameraColumn(XcxCameraColumn XcxCameraColumn);

    /**
     * 修改小程序--栏目
     *
     * @param XcxCameraColumn 小程序--栏目
     * @return 结果
     */
    public AjaxResult updateXcxCameraColumn(XcxCameraColumn XcxCameraColumn);

    /**
     * 批量删除小程序--栏目
     *
     * @param ids 需要删除的小程序--栏目主键集合
     * @return 结果
     */
    public int deleteXcxCameraColumnByIds(Long[] ids);

    /**
     * 删除小程序--栏目信息
     *
     * @param id 小程序--栏目主键
     * @return 结果
     */
    public int deleteXcxCameraColumnById(Long id);

    List<XcxCameraColumn> selectByTypeId(Integer typeId);


}
