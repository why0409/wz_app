package com.ruoyi.safetyHazard.mapper;


import com.ruoyi.safetyHazard.domain.SafetyHazardManifestSchool;

import java.util.List;

/**
 * 安全隐患-清单-学校Mapper接口
 *
 * @author ruoyi
 * @date 2024-08-12
 */
public interface SafetyHazardManifestSchoolMapper
{
    /**
     * 查询安全隐患-清单-学校
     *
     * @param id 安全隐患-清单-学校主键
     * @return 安全隐患-清单-学校
     */
    public SafetyHazardManifestSchool selectSafetyHazardManifestSchoolById(String id);

    /**
     * 查询安全隐患-清单-学校列表
     *
     * @param safetyHazardManifestSchool 安全隐患-清单-学校
     * @return 安全隐患-清单-学校集合
     */
    public List<SafetyHazardManifestSchool> selectSafetyHazardManifestSchoolList(SafetyHazardManifestSchool safetyHazardManifestSchool);

    /**
     * 新增安全隐患-清单-学校
     *
     * @param safetyHazardManifestSchool 安全隐患-清单-学校
     * @return 结果
     */
    public int insertSafetyHazardManifestSchool(SafetyHazardManifestSchool safetyHazardManifestSchool);

    /**
     * 修改安全隐患-清单-学校
     *
     * @param safetyHazardManifestSchool 安全隐患-清单-学校
     * @return 结果
     */
    public int updateSafetyHazardManifestSchool(SafetyHazardManifestSchool safetyHazardManifestSchool);

    /**
     * 删除安全隐患-清单-学校
     *
     * @param id 安全隐患-清单-学校主键
     * @return 结果
     */
    public int deleteSafetyHazardManifestSchoolById(String id);

    /**
     * 批量删除安全隐患-清单-学校
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSafetyHazardManifestSchoolByIds(String[] ids);
}
