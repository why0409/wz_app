package com.ruoyi.safetyHazard.service;

import com.ruoyi.safetyHazard.domain.SafetyHazardUser;
import com.ruoyi.safetyHazard.domain.vo.ExportSafetyHazardUserVo;
import com.ruoyi.safetyHazard.domain.vo.SafetyHazardUserPermissionVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 安全隐患-用户管理Service接口
 *
 * @author ruoyi
 * @date 2024-08-08
 */
public interface ISafetyHazardUserService
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
     * 批量删除安全隐患-用户管理
     *
     * @param userIds 需要删除的安全隐患-用户管理主键集合
     * @return 结果
     */
    public int deleteSafetyHazardUserByUserIds(Long[] userIds);

    /**
     * 删除安全隐患-用户管理信息
     *
     * @param userId 安全隐患-用户管理主键
     * @return 结果
     */
    public int deleteSafetyHazardUserByUserId(Long userId);

    public List<SafetyHazardUser> selectListByUserIds(List<Long> userIds, String userName, Long typeId);

   public String getUsernameByWxphone(String phone);

    public int setPermission(SafetyHazardUserPermissionVo safetyHazardUserPermissionVo);

    public List<ExportSafetyHazardUserVo> exportSafetyHazardUserList(SafetyHazardUser safetyHazardUser);

    public List<Long> selectUserIdsByParentId(Long parentId);
}
