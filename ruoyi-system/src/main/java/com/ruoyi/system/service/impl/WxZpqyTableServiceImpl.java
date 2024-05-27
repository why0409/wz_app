package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WxZpqyTableMapper;
import com.ruoyi.system.domain.WxZpqyTable;
import com.ruoyi.system.service.IWxZpqyTableService;

/**
 * 招聘企业信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-23
 */
@Service
public class WxZpqyTableServiceImpl implements IWxZpqyTableService 
{
    @Autowired
    private WxZpqyTableMapper wxZpqyTableMapper;

    /**
     * 查询招聘企业信息
     * 
     * @param id 招聘企业信息主键
     * @return 招聘企业信息
     */
    @Override
    public WxZpqyTable selectWxZpqyTableById(Long id)
    {
        return wxZpqyTableMapper.selectWxZpqyTableById(id);
    }

    /**
     * 查询招聘企业信息列表
     * 
     * @param wxZpqyTable 招聘企业信息
     * @return 招聘企业信息
     */
    @Override
    public List<WxZpqyTable> selectWxZpqyTableList(WxZpqyTable wxZpqyTable)
    {
        return wxZpqyTableMapper.selectWxZpqyTableList(wxZpqyTable);
    }

    /**
     * 新增招聘企业信息
     * 
     * @param wxZpqyTable 招聘企业信息
     * @return 结果
     */
    @Override
    public int insertWxZpqyTable(WxZpqyTable wxZpqyTable)
    {
        return wxZpqyTableMapper.insertWxZpqyTable(wxZpqyTable);
    }

    /**
     * 修改招聘企业信息
     * 
     * @param wxZpqyTable 招聘企业信息
     * @return 结果
     */
    @Override
    public int updateWxZpqyTable(WxZpqyTable wxZpqyTable)
    {
        return wxZpqyTableMapper.updateWxZpqyTable(wxZpqyTable);
    }

    /**
     * 批量删除招聘企业信息
     * 
     * @param ids 需要删除的招聘企业信息主键
     * @return 结果
     */
    @Override
    public int deleteWxZpqyTableByIds(Long[] ids)
    {
        return wxZpqyTableMapper.deleteWxZpqyTableByIds(ids);
    }

    /**
     * 删除招聘企业信息信息
     * 
     * @param id 招聘企业信息主键
     * @return 结果
     */
    @Override
    public int deleteWxZpqyTableById(Long id)
    {
        return wxZpqyTableMapper.deleteWxZpqyTableById(id);
    }
}
