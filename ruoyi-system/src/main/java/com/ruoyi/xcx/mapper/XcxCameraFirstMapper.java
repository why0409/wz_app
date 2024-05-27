package com.ruoyi.xcx.mapper;

import com.ruoyi.xcx.domain.XcxCameraType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 小程序---分类Mapper接口
 *
 * @author ruoyi
 * @date 2023-10-16
 */
public interface XcxCameraFirstMapper
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
     * @param XcxCameraType 小程序---分类
     * @return 小程序---分类集合
     */
    public List<XcxCameraType> selectXcxCameraTypeList(XcxCameraType XcxCameraType);

    /**
     * 新增小程序---分类
     *
     * @param XcxCameraType 小程序---分类
     * @return 结果
     */
    public int insertXcxCameraType(XcxCameraType XcxCameraType);

    /**
     * 修改小程序---分类
     *
     * @param XcxCameraType 小程序---分类
     * @return 结果
     */
    public int updateXcxCameraType(XcxCameraType XcxCameraType);

    /**
     * 删除小程序---分类
     *
     * @param id 小程序---分类主键
     * @return 结果
     */
    public int deleteXcxCameraTypeById(Long id);

    /**
     * 批量删除小程序---分类
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXcxCameraTypeByIds(Long[] ids);

    List<XcxCameraType> selectTypeByIdList(@Param("idList") List<Integer> typeIdList);

    Integer selectSort(@Param("sort") Long sort,@Param("id")Integer id);
}
