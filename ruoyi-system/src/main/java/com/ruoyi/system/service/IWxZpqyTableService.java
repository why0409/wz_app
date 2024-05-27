package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.WxZpqyTable;

/**
 * 招聘企业信息Service接口
 * 
 * @author ruoyi
 * @date 2022-11-23
 */
public interface IWxZpqyTableService 
{
    /**
     * 查询招聘企业信息
     * 
     * @param id 招聘企业信息主键
     * @return 招聘企业信息
     */
    public WxZpqyTable selectWxZpqyTableById(Long id);

    /**
     * 查询招聘企业信息列表
     * 
     * @param wxZpqyTable 招聘企业信息
     * @return 招聘企业信息集合
     */
    public List<WxZpqyTable> selectWxZpqyTableList(WxZpqyTable wxZpqyTable);

    /**
     * 新增招聘企业信息
     * 
     * @param wxZpqyTable 招聘企业信息
     * @return 结果
     */
    public int insertWxZpqyTable(WxZpqyTable wxZpqyTable);

    /**
     * 修改招聘企业信息
     * 
     * @param wxZpqyTable 招聘企业信息
     * @return 结果
     */
    public int updateWxZpqyTable(WxZpqyTable wxZpqyTable);

    /**
     * 批量删除招聘企业信息
     * 
     * @param ids 需要删除的招聘企业信息主键集合
     * @return 结果
     */
    public int deleteWxZpqyTableByIds(Long[] ids);

    /**
     * 删除招聘企业信息信息
     * 
     * @param id 招聘企业信息主键
     * @return 结果
     */
    public int deleteWxZpqyTableById(Long id);
}
