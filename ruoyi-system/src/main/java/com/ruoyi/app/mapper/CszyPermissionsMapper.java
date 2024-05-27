package com.ruoyi.app.mapper;

import java.util.List;
import com.ruoyi.app.domain.CszyPermissions;
import com.ruoyi.system.domain.WxUserMenu;

/**
 * 城市之眼-权限Mapper接口
 *
 * @author ruoyi
 * @date 2024-05-24
 */
public interface CszyPermissionsMapper
{
    /**
     * 查询城市之眼-权限
     *
     * @param id 城市之眼-权限主键
     * @return 城市之眼-权限
     */
    public CszyPermissions selectCszyPermissionsById(Long id);

    /**
     * 查询城市之眼-权限列表
     *
     * @param cszyPermissions 城市之眼-权限
     * @return 城市之眼-权限集合
     */
    public List<CszyPermissions> selectCszyPermissionsList(CszyPermissions cszyPermissions);

    /**
     * 新增城市之眼-权限
     *
     * @param cszyPermissions 城市之眼-权限
     * @return 结果
     */
    public int insertCszyPermissions(CszyPermissions cszyPermissions);

    /**
     * 修改城市之眼-权限
     *
     * @param cszyPermissions 城市之眼-权限
     * @return 结果
     */
    public int updateCszyPermissions(CszyPermissions cszyPermissions);

    /**
     * 删除城市之眼-权限
     *
     * @param id 城市之眼-权限主键
     * @return 结果
     */
    public int deleteCszyPermissionsById(Long id);

    /**
     * 批量删除城市之眼-权限
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCszyPermissionsByIds(Long[] ids);

    public void deleteCszyPermissionsByPhone(String phone);

    public int addBatchPermissions(List<CszyPermissions> list);
}
