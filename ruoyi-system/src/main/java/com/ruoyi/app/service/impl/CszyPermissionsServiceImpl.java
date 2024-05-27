package com.ruoyi.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.app.domain.vo.CszyPermissionsVo;
import com.ruoyi.system.domain.WxUserMenu;
import com.ruoyi.system.domain.vo.WxUserMenuReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.app.mapper.CszyPermissionsMapper;
import com.ruoyi.app.domain.CszyPermissions;
import com.ruoyi.app.service.ICszyPermissionsService;

/**
 * 城市之眼-权限Service业务层处理
 *
 * @author ruoyi
 * @date 2024-05-24
 */
@Service
public class CszyPermissionsServiceImpl implements ICszyPermissionsService
{
    @Autowired
    private CszyPermissionsMapper cszyPermissionsMapper;

    /**
     * 查询城市之眼-权限
     *
     * @param id 城市之眼-权限主键
     * @return 城市之眼-权限
     */
    @Override
    public CszyPermissions selectCszyPermissionsById(Long id)
    {
        return cszyPermissionsMapper.selectCszyPermissionsById(id);
    }

    /**
     * 查询城市之眼-权限列表
     *
     * @param cszyPermissions 城市之眼-权限
     * @return 城市之眼-权限
     */
    @Override
    public List<CszyPermissions> selectCszyPermissionsList(CszyPermissions cszyPermissions)
    {
        return cszyPermissionsMapper.selectCszyPermissionsList(cszyPermissions);
    }

    /**
     * 新增城市之眼-权限
     *
     * @param cszyPermissions 城市之眼-权限
     * @return 结果
     */
    @Override
    public int insertCszyPermissions(CszyPermissions cszyPermissions)
    {
        return cszyPermissionsMapper.insertCszyPermissions(cszyPermissions);
    }

    /**
     * 修改城市之眼-权限
     *
     * @param cszyPermissions 城市之眼-权限
     * @return 结果
     */
    @Override
    public int updateCszyPermissions(CszyPermissions cszyPermissions)
    {
        return cszyPermissionsMapper.updateCszyPermissions(cszyPermissions);
    }

    /**
     * 批量删除城市之眼-权限
     *
     * @param ids 需要删除的城市之眼-权限主键
     * @return 结果
     */
    @Override
    public int deleteCszyPermissionsByIds(Long[] ids)
    {
        return cszyPermissionsMapper.deleteCszyPermissionsByIds(ids);
    }

    /**
     * 删除城市之眼-权限信息
     *
     * @param id 城市之眼-权限主键
     * @return 结果
     */
    @Override
    public int deleteCszyPermissionsById(Long id)
    {
        return cszyPermissionsMapper.deleteCszyPermissionsById(id);
    }

    @Override
    public int addBatchPermissions(CszyPermissionsVo cszyPermissionsVo)
    {
        // 删除原权限
        cszyPermissionsMapper.deleteCszyPermissionsByPhone(cszyPermissionsVo.getPhone());
        // 新增用户与菜单管理
        List<CszyPermissions> list = new ArrayList<>();
        for (Long typeId : cszyPermissionsVo.getTypeIds()) {
            CszyPermissions cp = new CszyPermissions();
            cp.setPhone(cszyPermissionsVo.getPhone());
            cp.setTypeId(typeId);
            list.add(cp);
        }

        int rows = 0;
        if (list.size() > 0)
        {
            rows = cszyPermissionsMapper.addBatchPermissions(list);
        }
        return rows;
    }
}
