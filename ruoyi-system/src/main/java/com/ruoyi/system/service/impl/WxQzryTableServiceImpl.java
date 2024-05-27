package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WxQzryTableMapper;
import com.ruoyi.system.domain.WxQzryTable;
import com.ruoyi.system.service.IWxQzryTableService;

/**
 * 求职人员信息Service业务层处理
 * 
 * @author lgh
 * @date 2022-11-23
 */
@Service
public class WxQzryTableServiceImpl implements IWxQzryTableService 
{
    @Autowired
    private WxQzryTableMapper wxQzryTableMapper;

    /**
     * 查询求职人员信息
     * 
     * @param id 求职人员信息主键
     * @return 求职人员信息
     */
    @Override
    public WxQzryTable selectWxQzryTableById(Long id)
    {
        return wxQzryTableMapper.selectWxQzryTableById(id);
    }

    /**
     * 查询求职人员信息列表
     * 
     * @param wxQzryTable 求职人员信息
     * @return 求职人员信息
     */
    @Override
    public List<WxQzryTable> selectWxQzryTableList(WxQzryTable wxQzryTable)
    {
        return wxQzryTableMapper.selectWxQzryTableList(wxQzryTable);
    }

    /**
     * 新增求职人员信息
     * 
     * @param wxQzryTable 求职人员信息
     * @return 结果
     */
    @Override
    public int insertWxQzryTable(WxQzryTable wxQzryTable)
    {
        wxQzryTable.setPublishTime(DateUtils.getNowDate());
        return wxQzryTableMapper.insertWxQzryTable(wxQzryTable);
    }

    /**
     * 修改求职人员信息
     * 
     * @param wxQzryTable 求职人员信息
     * @return 结果
     */
    @Override
    public int updateWxQzryTable(WxQzryTable wxQzryTable)
    {
        return wxQzryTableMapper.updateWxQzryTable(wxQzryTable);
    }

    /**
     * 批量删除求职人员信息
     * 
     * @param ids 需要删除的求职人员信息主键
     * @return 结果
     */
    @Override
    public int deleteWxQzryTableByIds(Long[] ids)
    {
        return wxQzryTableMapper.deleteWxQzryTableByIds(ids);
    }

    /**
     * 删除求职人员信息信息
     * 
     * @param id 求职人员信息主键
     * @return 结果
     */
    @Override
    public int deleteWxQzryTableById(Long id)
    {
        return wxQzryTableMapper.deleteWxQzryTableById(id);
    }
}
