package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.WxZsyzTable;

/**
 * 招资引商Service接口
 * 
 * @author lgh
 * @date 2022-11-22
 */
public interface IWxZsyzTableService 
{
    /**
     * 查询招资引商
     * 
     * @param id 招资引商主键
     * @return 招资引商
     */
    public WxZsyzTable selectWxZsyzTableById(Long id);

    /**
     * 查询招资引商列表
     * 
     * @param wxZsyzTable 招资引商
     * @return 招资引商集合
     */
    public List<WxZsyzTable> selectWxZsyzTableList(WxZsyzTable wxZsyzTable);

    /**
     * 新增招资引商
     * 
     * @param wxZsyzTable 招资引商
     * @return 结果
     */
    public int insertWxZsyzTable(WxZsyzTable wxZsyzTable);

    /**
     * 修改招资引商
     * 
     * @param wxZsyzTable 招资引商
     * @return 结果
     */
    public int updateWxZsyzTable(WxZsyzTable wxZsyzTable);

    /**
     * 批量删除招资引商
     * 
     * @param ids 需要删除的招资引商主键集合
     * @return 结果
     */
    public int deleteWxZsyzTableByIds(Long[] ids);

    /**
     * 删除招资引商信息
     * 
     * @param id 招资引商主键
     * @return 结果
     */
    public int deleteWxZsyzTableById(Long id);
}
