package com.ruoyi.xcx.mapper;

import com.ruoyi.xcx.domain.XcxContent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 小程序_内容详情Mapper接口
 *
 * @author ruoyi
 * @date 2023-10-16
 */
public interface XcxContentMapper
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
    public int insertXcxContent(XcxContent xcxContent);

    /**
     * 修改小程序_内容详情
     *
     * @param xcxContent 小程序_内容详情
     * @return 结果
     */
    public int updateXcxContent(XcxContent xcxContent);

    /**
     * 删除小程序_内容详情
     *
     * @param id 小程序_内容详情主键
     * @return 结果
     */
    public int deleteXcxContentById(Long id);

    /**
     * 批量删除小程序_内容详情
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXcxContentByIds(Long[] ids);

    List<XcxContent> selectByColumnId(@Param("columnId") Integer columnId,
                                      @Param("contentName") String contentName,
                                      @Param("phone") String phone);

    Integer selectSort(@Param("contentSort") Long contentSort,
                       @Param("id") Long id,
                       @Param("columnId") Long columnId);

    void deleteByTypeIds(Long[] ids);

    void deleteByColumnIds(@Param("ids") Long[] ids);

    public List<XcxContent> getListByCollectionPhone(String phone);

    public List<XcxContent> searchContent(@Param("phone") String phone,
                                          @Param("contentName") String contentName);
}
