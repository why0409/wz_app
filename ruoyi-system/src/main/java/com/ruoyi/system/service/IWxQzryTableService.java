package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.WxQzryTable;

/**
 * 求职人员信息Service接口
 * 
 * @author lgh
 * @date 2022-11-23
 */
public interface IWxQzryTableService 
{
    /**
     * 查询求职人员信息
     * 
     * @param id 求职人员信息主键
     * @return 求职人员信息
     */
    public WxQzryTable selectWxQzryTableById(Long id);

    /**
     * 查询求职人员信息列表
     * 
     * @param wxQzryTable 求职人员信息
     * @return 求职人员信息集合
     */
    public List<WxQzryTable> selectWxQzryTableList(WxQzryTable wxQzryTable);

    /**
     * 新增求职人员信息
     * 
     * @param wxQzryTable 求职人员信息
     * @return 结果
     */
    public int insertWxQzryTable(WxQzryTable wxQzryTable);

    /**
     * 修改求职人员信息
     * 
     * @param wxQzryTable 求职人员信息
     * @return 结果
     */
    public int updateWxQzryTable(WxQzryTable wxQzryTable);

    /**
     * 批量删除求职人员信息
     * 
     * @param ids 需要删除的求职人员信息主键集合
     * @return 结果
     */
    public int deleteWxQzryTableByIds(Long[] ids);

    /**
     * 删除求职人员信息信息
     * 
     * @param id 求职人员信息主键
     * @return 结果
     */
    public int deleteWxQzryTableById(Long id);
}
