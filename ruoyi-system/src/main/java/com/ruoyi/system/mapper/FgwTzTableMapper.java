package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.FgwTzTable;

/**
 * 数据维护Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-17
 */
public interface FgwTzTableMapper 
{
    /**
     * 查询数据维护
     * 
     * @param id 数据维护主键
     * @return 数据维护
     */
    public FgwTzTable selectFgwTzTableById(Long id);

    /**
     * 查询数据维护列表
     * 
     * @param fgwTzTable 数据维护
     * @return 数据维护集合
     */
    public List<FgwTzTable> selectFgwTzTableList(FgwTzTable fgwTzTable);

    /**
     * 新增数据维护
     * 
     * @param fgwTzTable 数据维护
     * @return 结果
     */
    public int insertFgwTzTable(FgwTzTable fgwTzTable);

    /**
     * 修改数据维护
     * 
     * @param fgwTzTable 数据维护
     * @return 结果
     */
    public int updateFgwTzTable(FgwTzTable fgwTzTable);

    /**
     * 删除数据维护
     * 
     * @param id 数据维护主键
     * @return 结果
     */
    public int deleteFgwTzTableById(Long id);

    /**
     * 批量删除数据维护
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFgwTzTableByIds(Long[] ids);
}
