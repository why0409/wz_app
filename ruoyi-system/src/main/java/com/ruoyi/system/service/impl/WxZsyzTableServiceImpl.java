package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WxZsyzTableMapper;
import com.ruoyi.system.domain.WxZsyzTable;
import com.ruoyi.system.service.IWxZsyzTableService;

/**
 * 招资引商Service业务层处理
 * 
 * @author lgh
 * @date 2022-11-22
 */
@Service
public class WxZsyzTableServiceImpl implements IWxZsyzTableService 
{
    @Autowired
    private WxZsyzTableMapper wxZsyzTableMapper;

    /**
     * 查询招资引商
     * 
     * @param id 招资引商主键
     * @return 招资引商
     */
    @Override
    public WxZsyzTable selectWxZsyzTableById(Long id)
    {
        return wxZsyzTableMapper.selectWxZsyzTableById(id);
    }

    /**
     * 查询招资引商列表
     * 
     * @param wxZsyzTable 招资引商
     * @return 招资引商
     */
    @Override
    public List<WxZsyzTable> selectWxZsyzTableList(WxZsyzTable wxZsyzTable)
    {
        return wxZsyzTableMapper.selectWxZsyzTableList(wxZsyzTable);
    }

    /**
     * 新增招资引商
     * 
     * @param wxZsyzTable 招资引商
     * @return 结果
     */
    @Override
    public int insertWxZsyzTable(WxZsyzTable wxZsyzTable)
    {
        return wxZsyzTableMapper.insertWxZsyzTable(wxZsyzTable);
    }

    /**
     * 修改招资引商
     * 
     * @param wxZsyzTable 招资引商
     * @return 结果
     */
    @Override
    public int updateWxZsyzTable(WxZsyzTable wxZsyzTable)
    {
        return wxZsyzTableMapper.updateWxZsyzTable(wxZsyzTable);
    }

    /**
     * 批量删除招资引商
     * 
     * @param ids 需要删除的招资引商主键
     * @return 结果
     */
    @Override
    public int deleteWxZsyzTableByIds(Long[] ids)
    {
        return wxZsyzTableMapper.deleteWxZsyzTableByIds(ids);
    }

    /**
     * 删除招资引商信息
     * 
     * @param id 招资引商主键
     * @return 结果
     */
    @Override
    public int deleteWxZsyzTableById(Long id)
    {
        return wxZsyzTableMapper.deleteWxZsyzTableById(id);
    }
}
