package com.ruoyi.safetyHazard.service;

import com.ruoyi.safetyHazard.domain.SafetyHazardUserType;
import com.ruoyi.safetyHazard.domain.vo.SafetyHazardUserTypeVo;

import java.util.List;

/**
 * 安全隐患-角色类型管理Service接口
 *
 * @author ruoyi
 * @date 2024-08-08
 */
public interface ISafetyHazardUserTypeService
{
    /**
     * 查询安全隐患-角色类型管理
     *
     * @param id 安全隐患-角色类型管理主键
     * @return 安全隐患-角色类型管理
     */
    public SafetyHazardUserType selectSafetyHazardUserTypeById(Long id);

    /**
     * 查询安全隐患-角色类型管理列表
     *
     * @param safetyHazardUserType 安全隐患-角色类型管理
     * @return 安全隐患-角色类型管理集合
     */
    public List<SafetyHazardUserType> selectSafetyHazardUserTypeList(SafetyHazardUserType safetyHazardUserType);

    /**
     * 新增安全隐患-角色类型管理
     *
     * @param safetyHazardUserType 安全隐患-角色类型管理
     * @return 结果
     */
    public int insertSafetyHazardUserType(SafetyHazardUserType safetyHazardUserType);

    /**
     * 修改安全隐患-角色类型管理
     *
     * @param safetyHazardUserType 安全隐患-角色类型管理
     * @return 结果
     */
    public int updateSafetyHazardUserType(SafetyHazardUserType safetyHazardUserType);

    /**
     * 批量删除安全隐患-角色类型管理
     *
     * @param ids 需要删除的安全隐患-角色类型管理主键集合
     * @return 结果
     */
    public int deleteSafetyHazardUserTypeByIds(Long[] ids);

    /**
     * 删除安全隐患-角色类型管理信息
     *
     * @param id 安全隐患-角色类型管理主键
     * @return 结果
     */
    public int deleteSafetyHazardUserTypeById(Long id);

    public List<SafetyHazardUserTypeVo> selectSafetyHazardUserTypeVoList();
}
