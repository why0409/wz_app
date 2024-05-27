package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.WzMorning;

/**
 * 湾沚早报Service接口
 *
 * @author ruoyi
 * @date 2023-12-25
 */
public interface IWzMorningService
{
    /**
     * 查询湾沚早报
     *
     * @param id 湾沚早报主键
     * @return 湾沚早报
     */
    public WzMorning selectWzMorningById(Long id);

    /**
     * 查询湾沚早报列表
     *
     * @param wzMorning 湾沚早报
     * @return 湾沚早报集合
     */
    public List<WzMorning> selectWzMorningList(WzMorning wzMorning);

    /**
     * 新增湾沚早报
     *
     * @param wzMorning 湾沚早报
     * @return 结果
     */
    public int insertWzMorning(WzMorning wzMorning);

    /**
     * 修改湾沚早报
     *
     * @param wzMorning 湾沚早报
     * @return 结果
     */
    public int updateWzMorning(WzMorning wzMorning);

    /**
     * 批量删除湾沚早报
     *
     * @param ids 需要删除的湾沚早报主键集合
     * @return 结果
     */
    public int deleteWzMorningByIds(Long[] ids);

    /**
     * 删除湾沚早报信息
     *
     * @param id 湾沚早报主键
     * @return 结果
     */
    public int deleteWzMorningById(Long id);
}

