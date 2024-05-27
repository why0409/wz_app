package com.ruoyi.xcx.mapper;

import java.util.List;
import com.ruoyi.xcx.domain.XcxCollection;

/**
 * 收藏列Mapper接口
 *
 * @author ruoyi
 * @date 2023-10-31
 */
public interface XcxCollectionMapper
{
    /**
     * 查询收藏列
     *
     * @param phone 收藏列主键
     * @return 收藏列
     */
    public XcxCollection selectXcxCollectionByPhone(String phone);

    /**
     * 查询收藏列列表
     *
     * @param xcxCollection 收藏列
     * @return 收藏列集合
     */
    public List<XcxCollection> selectXcxCollectionList(XcxCollection xcxCollection);

    /**
     * 新增收藏列
     *
     * @param xcxCollection 收藏列
     * @return 结果
     */
    public int insertXcxCollection(XcxCollection xcxCollection);

    /**
     * 修改收藏列
     *
     * @param xcxCollection 收藏列
     * @return 结果
     */
    public int updateXcxCollection(XcxCollection xcxCollection);

    /**
     * 删除收藏列
     *
     * @param phone 收藏列主键
     * @return 结果
     */
    public int deleteXcxCollectionByPhone(String phone);

    /**
     * 批量删除收藏列
     *
     * @param phones 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXcxCollectionByPhones(String[] phones);

    List<Integer> getContentListByPhone(String phone);

    int deleteCollection(XcxCollection xcxCollection);
}
