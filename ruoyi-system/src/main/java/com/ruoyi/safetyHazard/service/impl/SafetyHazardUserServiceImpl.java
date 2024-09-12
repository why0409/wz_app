package com.ruoyi.safetyHazard.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.safetyHazard.domain.SafetyHazardUser;
import com.ruoyi.safetyHazard.domain.vo.ExportSafetyHazardUserVo;
import com.ruoyi.safetyHazard.domain.vo.SafetyHazardUserPermissionVo;
import com.ruoyi.safetyHazard.mapper.SafetyHazardUserMapper;
import com.ruoyi.safetyHazard.service.ISafetyHazardUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 安全隐患-用户管理Service业务层处理
 *
 * @author ruoyi
 * @date 2024-08-08
 */
@Service
public class SafetyHazardUserServiceImpl implements ISafetyHazardUserService
{
    @Autowired
    private SafetyHazardUserMapper safetyHazardUserMapper;

    /**
     * 查询安全隐患-用户管理
     *
     * @param userId 安全隐患-用户管理主键
     * @return 安全隐患-用户管理
     */
    @Override
    public SafetyHazardUser selectSafetyHazardUserByUserId(Long userId)
    {
        return safetyHazardUserMapper.selectSafetyHazardUserByUserId(userId);
    }

    /**
     * 查询安全隐患-用户管理列表
     *
     * @param safetyHazardUser 安全隐患-用户管理
     * @return 安全隐患-用户管理
     */
    @Override
    public List<SafetyHazardUser> selectSafetyHazardUserList(SafetyHazardUser safetyHazardUser)
    {
        return safetyHazardUserMapper.selectSafetyHazardUserList(safetyHazardUser);
    }

    /**
     * 新增安全隐患-用户管理
     *
     * @param safetyHazardUser 安全隐患-用户管理
     * @return 结果
     */
    @Override
    public int insertSafetyHazardUser(SafetyHazardUser safetyHazardUser)
    {
        safetyHazardUser.setCreateTime(DateUtils.getNowDate());
        return safetyHazardUserMapper.insertSafetyHazardUser(safetyHazardUser);
    }

    /**
     * 修改安全隐患-用户管理
     *
     * @param safetyHazardUser 安全隐患-用户管理
     * @return 结果
     */
    @Override
    public int updateSafetyHazardUser(SafetyHazardUser safetyHazardUser)
    {
        safetyHazardUser.setUpdateTime(DateUtils.getNowDate());
        return safetyHazardUserMapper.updateSafetyHazardUser(safetyHazardUser);
    }

    /**
     * 批量删除安全隐患-用户管理
     *
     * @param userIds 需要删除的安全隐患-用户管理主键
     * @return 结果
     */
    @Override
    public int deleteSafetyHazardUserByUserIds(Long[] userIds)
    {
        return safetyHazardUserMapper.deleteSafetyHazardUserByUserIds(userIds);
    }

    /**
     * 删除安全隐患-用户管理信息
     *
     * @param userId 安全隐患-用户管理主键
     * @return 结果
     */
    @Override
    public int deleteSafetyHazardUserByUserId(Long userId)
    {
        return safetyHazardUserMapper.deleteSafetyHazardUserByUserId(userId);
    }

    @Override
    public List<SafetyHazardUser> selectListByUserIds(List<Long> userIds, String userName, Long typeId){
        return safetyHazardUserMapper.selectListByUserIds(userIds, userName, typeId);
    }

    @Override
    public String getUsernameByWxphone(String phone){
        JSONArray array = JSONArray.parseArray(safetyHazardUserMapper.getUsernameByWxphone(phone));

        String userName = "";
        for (int i = 0; i < array.size(); i++) {
            if (phone.equals(array.getJSONObject(i).getString("phone"))){
                userName = array.getJSONObject(i).getString("username");
                break;
            }
        }

        return userName;
    }

    @Override
    public int setPermission(SafetyHazardUserPermissionVo safetyHazardUserPermissionVo){
        Long parentId = safetyHazardUserPermissionVo.getParenId();
        List<Long> userIds = safetyHazardUserPermissionVo.getUserIds();

        //删除原有权限
        safetyHazardUserMapper.deletePermission(userIds);

        //更新权限
        safetyHazardUserMapper.setPermission(parentId, userIds);

        return 1;
    }

    @Override
    public List<ExportSafetyHazardUserVo> exportSafetyHazardUserList(SafetyHazardUser safetyHazardUser){
        return safetyHazardUserMapper.exportSafetyHazardUserList(safetyHazardUser);
    }

    @Override
    public List<Long> selectUserIdsByParentId(Long parentId){
        return safetyHazardUserMapper.selectUserIdsByParentId(parentId);
    }
}
