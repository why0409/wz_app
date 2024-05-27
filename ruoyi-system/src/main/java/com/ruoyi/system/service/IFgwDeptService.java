package com.ruoyi.system.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.system.domain.FgwDept;

/**
 * 发改委责任单位关联Service接口
 * 
 * @author ruoyi
 * @date 2022-11-24
 */
public interface IFgwDeptService 
{
    /**
     * 查询发改委责任单位关联
     * 
     * @param id 发改委责任单位关联主键
     * @return 发改委责任单位关联
     */
    public FgwDept selectFgwDeptById(Long id);

    /**
     * 查询发改委责任单位关联列表
     * 
     * @param fgwDept 发改委责任单位关联
     * @return 发改委责任单位关联集合
     */
    public List<FgwDept> selectFgwDeptList(FgwDept fgwDept);

    /**
     * 新增发改委责任单位关联
     * 
     * @param fgwDept 发改委责任单位关联
     * @return 结果
     */
    public int insertFgwDept(FgwDept fgwDept);

    /**
     * 修改发改委责任单位关联
     * 
     * @param fgwDept 发改委责任单位关联
     * @return 结果
     */
    public int updateFgwDept(FgwDept fgwDept);

    /**
     * 批量删除发改委责任单位关联
     * 
     * @param ids 需要删除的发改委责任单位关联主键集合
     * @return 结果
     */
    public int deleteFgwDeptByIds(Long[] ids);

    /**
     * 删除发改委责任单位关联信息
     * 
     * @param id 发改委责任单位关联主键
     * @return 结果
     */
    public int deleteFgwDeptById(Long id);
    /**
     * 删除发改委责任单位关联信息
     *
     * @param pId 父键
     * @return 结果
     */
    public int deleteFgwDeptByPId(Long pId);

    /**
     * 根据父键删除
     * @param pIds
     * @return
     */
    public int deleteFgwDeptByPIds(Long[] pIds);

    /**
     * 根据条件查询父键集合
     * @author:
     * @date: 2022/11/25 17:21
     * @param map
     * @return
     */
    List<Long> selectPIdByMap(Map<String,String> map);
}
