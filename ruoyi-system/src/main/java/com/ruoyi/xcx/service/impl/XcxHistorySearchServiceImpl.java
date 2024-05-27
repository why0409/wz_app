package com.ruoyi.xcx.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.xcx.domain.XcxHistorySearch;
import com.ruoyi.xcx.mapper.XcxHistorySearchMapper;
import com.ruoyi.xcx.service.IXcxHistorySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 历史搜索Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-14
 */
@Service
public class XcxHistorySearchServiceImpl implements IXcxHistorySearchService
{
    @Autowired
    private XcxHistorySearchMapper xcxHistorySearchMapper;

    /**
     * 查询历史搜索
     *
     * @param id 历史搜索主键
     * @return 历史搜索
     */
    @Override
    public XcxHistorySearch selectXcxHistorySearchById(Long id)
    {
        return xcxHistorySearchMapper.selectXcxHistorySearchById(id);
    }

    /**
     * 查询历史搜索列表
     *
     * @param xcxHistorySearch 历史搜索
     * @return 历史搜索
     */
    @Override
    public List<XcxHistorySearch> selectXcxHistorySearchList(XcxHistorySearch xcxHistorySearch)
    {
        return xcxHistorySearchMapper.selectXcxHistorySearchList(xcxHistorySearch);
    }

    /**
     * 新增历史搜索
     *
     * @param xcxHistorySearch 历史搜索
     * @return 结果
     */
    @Override
    public int insertXcxHistorySearch(XcxHistorySearch xcxHistorySearch)
    {
        xcxHistorySearch.setCreateTime(DateUtils.getNowDate());
        xcxHistorySearch.setUpdateTime(DateUtils.getNowDate());
        return xcxHistorySearchMapper.insertXcxHistorySearch(xcxHistorySearch);
    }

    /**
     * 修改历史搜索
     *
     * @param xcxHistorySearch 历史搜索
     * @return 结果
     */
    @Override
    public int updateXcxHistorySearch(XcxHistorySearch xcxHistorySearch)
    {
        xcxHistorySearch.setUpdateTime(DateUtils.getNowDate());
        return xcxHistorySearchMapper.updateXcxHistorySearch(xcxHistorySearch);
    }

    /**
     * 批量删除历史搜索
     *
     * @param ids 需要删除的历史搜索主键
     * @return 结果
     */
    @Override
    public int deleteXcxHistorySearchByIds(Long[] ids)
    {
        return xcxHistorySearchMapper.deleteXcxHistorySearchByIds(ids);
    }

    /**
     * 删除历史搜索信息
     *
     * @param id 历史搜索主键
     * @return 结果
     */
    @Override
    public int deleteXcxHistorySearchById(Long id)
    {
        return xcxHistorySearchMapper.deleteXcxHistorySearchById(id);
    }

    @Override
    public List<XcxHistorySearch> selectXcxHistorySearchListLimit(XcxHistorySearch xcxHistorySearch) {
        return xcxHistorySearchMapper.selectXcxHistorySearchListLimit(xcxHistorySearch);
    }

}

