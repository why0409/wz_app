package com.ruoyi.safetyHazard.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.safetyHazard.domain.SafetyHazardManifestSchool;
import com.ruoyi.safetyHazard.mapper.SafetyHazardManifestSchoolMapper;
import com.ruoyi.safetyHazard.service.ISafetyHazardManifestSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 安全隐患-清单-学校Service业务层处理
 *
 * @author ruoyi
 * @date 2024-08-12
 */
@Service
public class SafetyHazardManifestSchoolServiceImpl implements ISafetyHazardManifestSchoolService
{
    @Autowired
    private SafetyHazardManifestSchoolMapper safetyHazardManifestSchoolMapper;

    /**
     * 查询安全隐患-清单-学校
     *
     * @param id 安全隐患-清单-学校主键
     * @return 安全隐患-清单-学校
     */
    @Override
    public SafetyHazardManifestSchool selectSafetyHazardManifestSchoolById(String id)
    {
        return safetyHazardManifestSchoolMapper.selectSafetyHazardManifestSchoolById(id);
    }

    /**
     * 查询安全隐患-清单-学校列表
     *
     * @param safetyHazardManifestSchool 安全隐患-清单-学校
     * @return 安全隐患-清单-学校
     */
    @Override
    public List<SafetyHazardManifestSchool> selectSafetyHazardManifestSchoolList(SafetyHazardManifestSchool safetyHazardManifestSchool)
    {
        return safetyHazardManifestSchoolMapper.selectSafetyHazardManifestSchoolList(safetyHazardManifestSchool);
    }

    /**
     * 新增安全隐患-清单-学校
     *
     * @param safetyHazardManifestSchool 安全隐患-清单-学校
     * @return 结果
     */
    @Override
    public int insertSafetyHazardManifestSchool(SafetyHazardManifestSchool safetyHazardManifestSchool)
    {
        safetyHazardManifestSchool.setCreateTime(DateUtils.getNowDate());
        return safetyHazardManifestSchoolMapper.insertSafetyHazardManifestSchool(safetyHazardManifestSchool);
    }

    /**
     * 修改安全隐患-清单-学校
     *
     * @param safetyHazardManifestSchool 安全隐患-清单-学校
     * @return 结果
     */
    @Override
    public int updateSafetyHazardManifestSchool(SafetyHazardManifestSchool safetyHazardManifestSchool)
    {
        safetyHazardManifestSchool.setUpdateTime(DateUtils.getNowDate());
        return safetyHazardManifestSchoolMapper.updateSafetyHazardManifestSchool(safetyHazardManifestSchool);
    }

    /**
     * 批量删除安全隐患-清单-学校
     *
     * @param ids 需要删除的安全隐患-清单-学校主键
     * @return 结果
     */
    @Override
    public int deleteSafetyHazardManifestSchoolByIds(String[] ids)
    {
        return safetyHazardManifestSchoolMapper.deleteSafetyHazardManifestSchoolByIds(ids);
    }

    /**
     * 删除安全隐患-清单-学校信息
     *
     * @param id 安全隐患-清单-学校主键
     * @return 结果
     */
    @Override
    public int deleteSafetyHazardManifestSchoolById(String id)
    {
        return safetyHazardManifestSchoolMapper.deleteSafetyHazardManifestSchoolById(id);
    }
}
