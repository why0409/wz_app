package com.ruoyi.safetyHazard.mapper;

import com.ruoyi.safetyHazard.domain.SafetyHazardUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 安全隐患-用户管理Mapper接口
 *
 * @author ruoyi
 * @date 2024-08-08
 */
public interface SafetyHazardUserMapper
{
    /**
     * 查询安全隐患-用户管理
     *
     * @param userId 安全隐患-用户管理主键
     * @return 安全隐患-用户管理
     */
    public SafetyHazardUser selectSafetyHazardUserByUserId(Long userId);

    /**
     * 查询安全隐患-用户管理列表
     *
     * @param safetyHazardUser 安全隐患-用户管理
     * @return 安全隐患-用户管理集合
     */
    public List<SafetyHazardUser> selectSafetyHazardUserList(SafetyHazardUser safetyHazardUser);

    /**
     * 新增安全隐患-用户管理
     *
     * @param safetyHazardUser 安全隐患-用户管理
     * @return 结果
     */
    public int insertSafetyHazardUser(SafetyHazardUser safetyHazardUser);

    /**
     * 修改安全隐患-用户管理
     *
     * @param safetyHazardUser 安全隐患-用户管理
     * @return 结果
     */
    public int updateSafetyHazardUser(SafetyHazardUser safetyHazardUser);

    /**
     * 删除安全隐患-用户管理
     *
     * @param userId 安全隐患-用户管理主键
     * @return 结果
     */
    public int deleteSafetyHazardUserByUserId(Long userId);

    /**
     * 批量删除安全隐患-用户管理
     *
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSafetyHazardUserByUserIds(Long[] userIds);

    public List<SafetyHazardUser> selectListByUserIds(@Param("list") List<Long> userIds,
                                                      @Param("userName") String userName,
                                                      @Param("typeId") Long typeId);
}
