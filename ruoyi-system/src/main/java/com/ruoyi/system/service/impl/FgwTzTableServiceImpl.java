package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FgwTzTableMapper;
import com.ruoyi.system.domain.FgwTzTable;
import com.ruoyi.system.service.IFgwTzTableService;

/**
 * 数据维护Service业务层处理
 *
 * @author ruoyi
 * @date 2022-11-17
 */
@Service
public class FgwTzTableServiceImpl implements IFgwTzTableService
{
    @Autowired
    private FgwTzTableMapper fgwTzTableMapper;

    /**
     * 查询数据维护
     *
     * @param id 数据维护主键
     * @return 数据维护
     */
    @Override
    public FgwTzTable selectFgwTzTableById(Long id)
    {
        return fgwTzTableMapper.selectFgwTzTableById(id);
    }

    /**
     * 查询数据维护列表
     *
     * @param fgwTzTable 数据维护
     * @return 数据维护
     */
    @Override
    public List<FgwTzTable> selectFgwTzTableList(FgwTzTable fgwTzTable)
    {
        return fgwTzTableMapper.selectFgwTzTableList(fgwTzTable);
    }

    /**
     * 新增数据维护
     *
     * @param fgwTzTable 数据维护
     * @return 结果
     */
    @Override
    public int insertFgwTzTable(FgwTzTable fgwTzTable)
    {
        fgwTzTable.setCreateTime(DateUtils.getNowDate());
        return fgwTzTableMapper.insertFgwTzTable(fgwTzTable);
    }

    /**
     * 修改数据维护
     *
     * @param fgwTzTable 数据维护
     * @return 结果
     */
    @Override
    public int updateFgwTzTable(FgwTzTable fgwTzTable)
    {
        fgwTzTable.setUpdateTime(DateUtils.getNowDate());
        return fgwTzTableMapper.updateFgwTzTable(fgwTzTable);
    }

    /**
     * 批量删除数据维护
     *
     * @param ids 需要删除的数据维护主键
     * @return 结果
     */
    @Override
    public int deleteFgwTzTableByIds(Long[] ids)
    {
        return fgwTzTableMapper.deleteFgwTzTableByIds(ids);
    }

    /**
     * 删除数据维护信息
     *
     * @param id 数据维护主键
     * @return 结果
     */
    @Override
    public int deleteFgwTzTableById(Long id)
    {
        return fgwTzTableMapper.deleteFgwTzTableById(id);
    }
}