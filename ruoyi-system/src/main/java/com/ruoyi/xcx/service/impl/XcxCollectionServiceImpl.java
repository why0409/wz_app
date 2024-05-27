package com.ruoyi.xcx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.xcx.mapper.XcxCollectionMapper;
import com.ruoyi.xcx.domain.XcxCollection;
import com.ruoyi.xcx.service.IXcxCollectionService;

/**
 * 收藏列Service业务层处理
 *
 * @author ruoyi
 * @date 2023-10-31
 */
@Service
public class XcxCollectionServiceImpl implements IXcxCollectionService
{
    @Autowired
    private XcxCollectionMapper xcxCollectionMapper;

    /**
     * 查询收藏列
     *
     * @param phone 收藏列主键
     * @return 收藏列
     */
    @Override
    public XcxCollection selectXcxCollectionByPhone(String phone)
    {
        return xcxCollectionMapper.selectXcxCollectionByPhone(phone);
    }

    /**
     * 查询收藏列列表
     *
     * @param xcxCollection 收藏列
     * @return 收藏列
     */
    @Override
    public List<XcxCollection> selectXcxCollectionList(XcxCollection xcxCollection)
    {
        return xcxCollectionMapper.selectXcxCollectionList(xcxCollection);
    }

    /**
     * 新增收藏列
     *
     * @param xcxCollection 收藏列
     * @return 结果
     */
    @Override
    public int insertXcxCollection(XcxCollection xcxCollection)
    {
        return xcxCollectionMapper.insertXcxCollection(xcxCollection);
    }

    /**
     * 修改收藏列
     *
     * @param xcxCollection 收藏列
     * @return 结果
     */
    @Override
    public int updateXcxCollection(XcxCollection xcxCollection)
    {
        return xcxCollectionMapper.updateXcxCollection(xcxCollection);
    }

    /**
     * 批量删除收藏列
     *
     * @param phones 需要删除的收藏列主键
     * @return 结果
     */
    @Override
    public int deleteXcxCollectionByPhones(String[] phones)
    {
        return xcxCollectionMapper.deleteXcxCollectionByPhones(phones);
    }

    /**
     * 删除收藏列信息
     *
     * @param phone 收藏列主键
     * @return 结果
     */
    @Override
    public int deleteXcxCollectionByPhone(String phone)
    {
        return xcxCollectionMapper.deleteXcxCollectionByPhone(phone);
    }

    @Override
    public List<Integer> getContentListByPhone(String phone) {
        return xcxCollectionMapper.getContentListByPhone(phone);
    }

    @Override
    public int deleteCollection(XcxCollection xcxCollection) {
        return xcxCollectionMapper.deleteCollection(xcxCollection);
    }

}
