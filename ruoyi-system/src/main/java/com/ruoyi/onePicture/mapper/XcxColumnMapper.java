package com.ruoyi.onePicture.mapper;

import com.ruoyi.onePicture.domain.XcxColumn;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 小程序--栏目Mapper接口
 *
 * @author ruoyi
 * @date 2023-10-16
 */
public interface XcxColumnMapper
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
    public int insertXcxColumn(XcxColumn xcxColumn);

    /**
     * 修改小程序--栏目
     *
     * @param xcxColumn 小程序--栏目
     * @return 结果
     */
    public int updateXcxColumn(XcxColumn xcxColumn);

    /**
     * 删除小程序--栏目
     *
     * @param id 小程序--栏目主键
     * @return 结果
     */
    public int deleteXcxColumnById(Long id);

    /**
     * 批量删除小程序--栏目
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXcxColumnByIds(Long[] ids);

    List<XcxColumn> selectByTypeId(@Param("typeId") Integer typeId);

    Integer selectSort(@Param("columnSort") Long columnSort,@Param("id") Long id,@Param("typeId") String typeId);

    void updateTypeId(Long id);
}
