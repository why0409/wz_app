package com.ruoyi.xcx.mapper;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.xcx.domain.XcxCameraContent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 小程序_内容详情Mapper接口
 *
 * @author ruoyi
 * @date 2023-10-16
 */
public interface XcxCameraThirdMapper
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
     * @param XcxCameraContent 小程序_内容详情
     * @return 小程序_内容详情集合
     */
    public List<XcxCameraContent> selectXcxCameraContentList(XcxCameraContent XcxCameraContent);

    /**
     * 新增小程序_内容详情
     *
     * @param XcxCameraContent 小程序_内容详情
     * @return 结果
     */
    public int insertXcxCameraContent(XcxCameraContent XcxCameraContent);

    /**
     * 修改小程序_内容详情
     *
     * @param XcxCameraContent 小程序_内容详情
     * @return 结果
     */
    public int updateXcxCameraContent(XcxCameraContent XcxCameraContent);

    /**
     * 删除小程序_内容详情
     *
     * @param id 小程序_内容详情主键
     * @return 结果
     */
    public int deleteXcxCameraContentById(Long id);

    /**
     * 批量删除小程序_内容详情
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXcxCameraContentByIds(Long[] ids);

    List<XcxCameraContent> selectByColumnId(@Param("columnId") Integer columnId,
                                            @Param("contentName") String contentName,
                                            @Param("phone") String phone,
                                            @Param("dept") String dept,
                                            @Param("operators") String operators,
                                            @Param("searchType") Integer searchType);

    Integer selectSort(@Param("contentSort") Long contentSort,
                       @Param("id") Long id,
                       @Param("columnId") String columnId);

    void deleteByTypeIds(Long[] ids);

    void deleteByColumnIds(@Param("ids") Long[] ids);

    public List<XcxCameraContent> getListByCollectionPhone(String phone);

    public List<XcxCameraContent> searchContent(@Param("phone") String phone,
                                          @Param("contentName") String contentName);

    public List<JSONObject> selectDistinctDept();
}
