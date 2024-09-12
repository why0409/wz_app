package com.ruoyi.onePicture.mapper;


import com.ruoyi.onePicture.domain.XcxHistorySearch;
import java.util.List;

/**
 * 历史搜索Mapper接口
 *
 * @author ruoyi
 * @date 2023-11-14
 */
public interface XcxHistorySearchMapper
{
    /**
     * 查询历史搜索
     *
     * @param id 历史搜索主键
     * @return 历史搜索
     */
    public XcxHistorySearch selectXcxHistorySearchById(Long id);

    /**
     * 查询历史搜索列表
     *
     * @param xcxHistorySearch 历史搜索
     * @return 历史搜索集合
     */
    public List<XcxHistorySearch> selectXcxHistorySearchList(XcxHistorySearch xcxHistorySearch);

    /**
     * 新增历史搜索
     *
     * @param xcxHistorySearch 历史搜索
     * @return 结果
     */
    public int insertXcxHistorySearch(XcxHistorySearch xcxHistorySearch);

    /**
     * 修改历史搜索
     *
     * @param xcxHistorySearch 历史搜索
     * @return 结果
     */
    public int updateXcxHistorySearch(XcxHistorySearch xcxHistorySearch);

    /**
     * 删除历史搜索
     *
     * @param id 历史搜索主键
     * @return 结果
     */
    public int deleteXcxHistorySearchById(Long id);

    /**
     * 批量删除历史搜索
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXcxHistorySearchByIds(Long[] ids);

    public List<XcxHistorySearch> selectXcxHistorySearchListLimit(XcxHistorySearch xcxHistorySearch);
}

