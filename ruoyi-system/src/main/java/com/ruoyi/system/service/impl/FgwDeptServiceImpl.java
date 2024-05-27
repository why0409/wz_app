package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FgwDeptMapper;
import com.ruoyi.system.domain.FgwDept;
import com.ruoyi.system.service.IFgwDeptService;

/**
 * 发改委责任单位关联Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-24
 */
@Service
public class FgwDeptServiceImpl implements IFgwDeptService 
{
    @Autowired
    private FgwDeptMapper fgwDeptMapper;

    /**
     * 查询发改委责任单位关联
     * 
     * @param id 发改委责任单位关联主键
     * @return 发改委责任单位关联
     */
    @Override
    public FgwDept selectFgwDeptById(Long id)
    {
        return fgwDeptMapper.selectFgwDeptById(id);
    }

    /**
     * 查询发改委责任单位关联列表
     * 
     * @param fgwDept 发改委责任单位关联
     * @return 发改委责任单位关联
     */
    @Override
    public List<FgwDept> selectFgwDeptList(FgwDept fgwDept)
    {
        return fgwDeptMapper.selectFgwDeptList(fgwDept);
    }

    /**
     * 新增发改委责任单位关联
     * 
     * @param fgwDept 发改委责任单位关联
     * @return 结果
     */
    @Override
    public int insertFgwDept(FgwDept fgwDept)
    {
        fgwDept.setCreateTime(DateUtils.getNowDate());
        return fgwDeptMapper.insertFgwDept(fgwDept);
    }

    /**
     * 修改发改委责任单位关联
     * 
     * @param fgwDept 发改委责任单位关联
     * @return 结果
     */
    @Override
    public int updateFgwDept(FgwDept fgwDept)
    {
        fgwDept.setUpdateTime(DateUtils.getNowDate());
        return fgwDeptMapper.updateFgwDept(fgwDept);
    }

    /**
     * 批量删除发改委责任单位关联
     * 
     * @param ids 需要删除的发改委责任单位关联主键
     * @return 结果
     */
    @Override
    public int deleteFgwDeptByIds(Long[] ids)
    {
        return fgwDeptMapper.deleteFgwDeptByIds(ids);
    }
    /**
     * 批量删除发改委责任单位关联
     *
     * @param pIds 父键集合
     * @return 结果
     */
    @Override
    public int deleteFgwDeptByPIds(Long[] pIds)
    {
        return fgwDeptMapper.deleteFgwDeptByPIds(pIds);
    }

    /**
     * 删除发改委责任单位关联信息
     * 
     * @param id 发改委责任单位关联主键
     * @return 结果
     */
    @Override
    public int deleteFgwDeptById(Long id)
    {
        return fgwDeptMapper.deleteFgwDeptById(id);
    }
    /**
     * 根据父键删除
     *
     * @param pId 父键
     * @return 结果
     */
    @Override
    public int deleteFgwDeptByPId(Long pId)
    {
        return fgwDeptMapper.deleteFgwDeptByPId(pId);
    }

    /**
     * 根据条件查询父键集合
     * @author:
     * @date: 2022/11/25 17:21
     * @param map
     * @return
     */
    public List<Long> selectPIdByMap(Map<String,String> map){
        return fgwDeptMapper.selectPIdByMap(map);
    }
}
