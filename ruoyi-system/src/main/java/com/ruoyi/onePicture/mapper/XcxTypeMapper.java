package com.ruoyi.onePicture.mapper;

import com.ruoyi.onePicture.domain.XcxType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 小程序---分类Mapper接口
 *
 * @author ruoyi
 * @date 2023-10-16
 */
public interface XcxTypeMapper
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
    public int insertXcxType(XcxType xcxType);

    /**
     * 修改小程序---分类
     *
     * @param xcxType 小程序---分类
     * @return 结果
     */
    public int updateXcxType(XcxType xcxType);

    /**
     * 删除小程序---分类
     *
     * @param id 小程序---分类主键
     * @return 结果
     */
    public int deleteXcxTypeById(Long id);

    /**
     * 批量删除小程序---分类
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXcxTypeByIds(Long[] ids);

    List<XcxType> selectTypeByIdList(@Param("idList") List<Integer> typeIdList);

    Integer selectSort(@Param("sort") Long sort,@Param("id")Integer id);
}
