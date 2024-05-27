package com.ruoyi.system.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.WxUserMenu;
import com.ruoyi.system.domain.vo.WxUserMenuReqVo;
import com.ruoyi.system.domain.vo.WxUserMenuVo;
import com.ruoyi.system.mapper.WxUserMenuMapper;
import com.ruoyi.system.service.IWxUserMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 用户菜单关系Service业务层处理
 *
 * @author ruoyi
 * @date 2022-11-22
 */
@Service
public class WxUserMenuServiceImpl implements IWxUserMenuService
{
    @Autowired
    private WxUserMenuMapper wxUserMenuMapper;

    /**
     * 查询用户菜单关系
     *
     * @param id 用户菜单关系主键
     * @return 用户菜单关系
     */
    @Override
    public WxUserMenu selectWxUserMenuById(Long id)
    {
        return wxUserMenuMapper.selectWxUserMenuById(id);
    }

    /**
     * 根据手机号查询用户菜单关系
     *
     * @param phone 手机号
     * @return 用户菜单关系
     */
    @Override
    public List<WxUserMenuVo> getMenuByPhone(String phone)
    {
        return wxUserMenuMapper.getMenuByPhone(phone);
    }

    @Override
    public List<Object> getMenuByPhoneOld(String phone) {
        List<Object> result = new ArrayList<>();
        List<WxUserMenuVo> list = wxUserMenuMapper.getMenuByPhone(phone);

        ////权限白名单
        //List<String> permissionList = Arrays.asList("15979096269");
        //if (permissionList.contains(phone)) {
        //    for (WxUserMenuVo w : list) {
        //        w.setPhone(phone);
        //        result.add(w);
        //    }
        //    return result;
        //}

        for (WxUserMenuVo w : list) {
            if (StringUtils.isEmpty(w.getPhone())) {
                JSONObject j = new JSONObject();
                j.put("id",w.getId());
                j.put("menuId",w.getMenuId());
                j.put("menuName",w.getMenuName());
                j.put("menuMark",w.getMenuMark());
                j.put("menuOrder",w.getMenuOrder());
                j.put("menuStatus",w.getMenuStatus());

                result.add(j);
            } else {
                result.add(w);
            }
        }

        return result;
    }

    /**
     * 查询用户菜单关系列表
     *
     * @param wxUserMenu 用户菜单关系
     * @return 用户菜单关系
     */
    @Override
    public List<WxUserMenu> selectWxUserMenuList(WxUserMenu wxUserMenu)
    {
        return wxUserMenuMapper.selectWxUserMenuList(wxUserMenu);
    }

    /**
     * 新增用户菜单关系
     *
     * @param wxUserMenu 用户菜单关系
     * @return 结果
     */
    @Override
    public int insertWxUserMenu(WxUserMenu wxUserMenu)
    {
        return wxUserMenuMapper.insertWxUserMenu(wxUserMenu);
    }

    /**
     * 批量新增用户菜单关系
     *
     * @param wxUserMenuReqVo 用户菜单关系
     * @return 结果
     */
    @Override
    public int addWxUserMenu(WxUserMenuReqVo wxUserMenuReqVo)
    {
        // 删除原权限
        wxUserMenuMapper.deleteWxUserMenuByPhone(wxUserMenuReqVo.getPhone());
        //
        int rows = 1;
        // 新增用户与菜单管理
        List<WxUserMenu> list = new ArrayList<WxUserMenu>();
        for (Long menuId : wxUserMenuReqVo.getMenuIds()) {
            WxUserMenu rm = new WxUserMenu();
            rm.setPhone(wxUserMenuReqVo.getPhone());
            rm.setMenuId(menuId);
            list.add(rm);
        }
        if (list.size() > 0)
        {
            rows = wxUserMenuMapper.addWxUserMenu(list);
        }
        return rows;
    }

    /**
     * 修改用户菜单关系
     *
     * @param wxUserMenu 用户菜单关系
     * @return 结果
     */
    @Override
    public int updateWxUserMenu(WxUserMenu wxUserMenu)
    {
        return wxUserMenuMapper.updateWxUserMenu(wxUserMenu);
    }

    /**
     * 批量删除用户菜单关系
     *
     * @param ids 需要删除的用户菜单关系主键
     * @return 结果
     */
    @Override
    public int deleteWxUserMenuByIds(Long[] ids)
    {
        return wxUserMenuMapper.deleteWxUserMenuByIds(ids);
    }

    /**
     * 删除用户菜单关系信息
     *
     * @param id 用户菜单关系主键
     * @return 结果
     */
    @Override
    public int deleteWxUserMenuById(Long id)
    {
        return wxUserMenuMapper.deleteWxUserMenuById(id);
    }

    /**
     * 批量新增用户菜单关系（新）
     *
     * @param wxUserMenuReqVo 用户菜单关系
     * @return 结果
     */
    @Override
    public int addWxUserMenuNew(WxUserMenuReqVo wxUserMenuReqVo)
    {
        // 删除原权限
        wxUserMenuMapper.deleteWxUserMenuByPhoneNew(wxUserMenuReqVo.getPhone());
        //
        int rows = 1;
        // 新增用户与菜单管理
        List<WxUserMenu> list = new ArrayList<WxUserMenu>();
        for (Long menuId : wxUserMenuReqVo.getMenuIds()) {
            WxUserMenu rm = new WxUserMenu();
            rm.setPhone(wxUserMenuReqVo.getPhone());
            rm.setMenuId(menuId);
            list.add(rm);
        }
        if (list.size() > 0)
        {
            rows = wxUserMenuMapper.addWxUserMenuNew(list);
        }
        return rows;
    }

}
