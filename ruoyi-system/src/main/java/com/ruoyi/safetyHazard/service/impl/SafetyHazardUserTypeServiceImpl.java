package com.ruoyi.safetyHazard.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.safetyHazard.domain.SafetyHazardUserType;
import com.ruoyi.safetyHazard.domain.vo.SafetyHazardUserTypeVo;
import com.ruoyi.safetyHazard.mapper.SafetyHazardUserTypeMapper;
import com.ruoyi.safetyHazard.service.ISafetyHazardUserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 安全隐患-角色类型管理Service业务层处理
 *
 * @author ruoyi
 * @date 2024-08-08
 */
@Service
public class SafetyHazardUserTypeServiceImpl implements ISafetyHazardUserTypeService
{
    @Autowired
    private SafetyHazardUserTypeMapper safetyHazardUserTypeMapper;

    /**
     * 查询安全隐患-角色类型管理
     *
     * @param id 安全隐患-角色类型管理主键
     * @return 安全隐患-角色类型管理
     */
    @Override
    public SafetyHazardUserType selectSafetyHazardUserTypeById(Long id)
    {
        return safetyHazardUserTypeMapper.selectSafetyHazardUserTypeById(id);
    }

    /**
     * 查询安全隐患-角色类型管理列表
     *
     * @param safetyHazardUserType 安全隐患-角色类型管理
     * @return 安全隐患-角色类型管理
     */
    @Override
    public List<SafetyHazardUserType> selectSafetyHazardUserTypeList(SafetyHazardUserType safetyHazardUserType)
    {
        return safetyHazardUserTypeMapper.selectSafetyHazardUserTypeList(safetyHazardUserType);
    }

    /**
     * 新增安全隐患-角色类型管理
     *
     * @param safetyHazardUserType 安全隐患-角色类型管理
     * @return 结果
     */
    @Override
    public int insertSafetyHazardUserType(SafetyHazardUserType safetyHazardUserType)
    {
        safetyHazardUserType.setCreateTime(DateUtils.getNowDate());
        return safetyHazardUserTypeMapper.insertSafetyHazardUserType(safetyHazardUserType);
    }

    /**
     * 修改安全隐患-角色类型管理
     *
     * @param safetyHazardUserType 安全隐患-角色类型管理
     * @return 结果
     */
    @Override
    public int updateSafetyHazardUserType(SafetyHazardUserType safetyHazardUserType)
    {
        safetyHazardUserType.setUpdateTime(DateUtils.getNowDate());
        return safetyHazardUserTypeMapper.updateSafetyHazardUserType(safetyHazardUserType);
    }

    /**
     * 批量删除安全隐患-角色类型管理
     *
     * @param ids 需要删除的安全隐患-角色类型管理主键
     * @return 结果
     */
    @Override
    public int deleteSafetyHazardUserTypeByIds(Long[] ids)
    {
        return safetyHazardUserTypeMapper.deleteSafetyHazardUserTypeByIds(ids);
    }

    /**
     * 删除安全隐患-角色类型管理信息
     *
     * @param id 安全隐患-角色类型管理主键
     * @return 结果
     */
    @Override
    public int deleteSafetyHazardUserTypeById(Long id)
    {
        return safetyHazardUserTypeMapper.deleteSafetyHazardUserTypeById(id);
    }

    @Override
    public List<SafetyHazardUserTypeVo> selectSafetyHazardUserTypeVoList(){
        return safetyHazardUserTypeMapper.selectSafetyHazardUserTypeVoList();
    }
}
