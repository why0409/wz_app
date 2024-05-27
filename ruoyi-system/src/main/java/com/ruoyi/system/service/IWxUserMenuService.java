package com.ruoyi.system.service;

import com.ruoyi.system.domain.WxUserMenu;
import com.ruoyi.system.domain.vo.WxUserMenuReqVo;
import com.ruoyi.system.domain.vo.WxUserMenuVo;

import java.util.List;

/**
 * 用户菜单关系Service接口
 *
 * @author ruoyi
 * @date 2022-11-22
 */
public interface IWxUserMenuService
{
    /**
     * 查询用户菜单关系
     *
     * @param id 用户菜单关系主键
     * @return 用户菜单关系
     */
    public WxUserMenu selectWxUserMenuById(Long id);


    /**
     * 查询用户菜单关系
     *
     * @param phone
     * @return 用户菜单关系
     */
    public List<WxUserMenuVo> getMenuByPhone(String phone);

    public List<Object> getMenuByPhoneOld(String phone);

    /**
     * 查询用户菜单关系列表
     *
     * @param wxUserMenu 用户菜单关系
     * @return 用户菜单关系集合
     */
    public List<WxUserMenu> selectWxUserMenuList(WxUserMenu wxUserMenu);

    /**
     * 新增用户菜单关系
     *
     * @param wxUserMenu 用户菜单关系
     * @return 结果
     */
    public int insertWxUserMenu(WxUserMenu wxUserMenu);

    /**
     * 批量新增用户菜单关系
     *
     * @param wxUserMenuReqVo 用户菜单关系
     * @return 结果
     */
    public int addWxUserMenu(WxUserMenuReqVo wxUserMenuReqVo);

    /**
     * 修改用户菜单关系
     *
     * @param wxUserMenu 用户菜单关系
     * @return 结果
     */
    public int updateWxUserMenu(WxUserMenu wxUserMenu);

    /**
     * 批量删除用户菜单关系
     *
     * @param ids 需要删除的用户菜单关系主键集合
     * @return 结果
     */
    public int deleteWxUserMenuByIds(Long[] ids);

    /**
     * 删除用户菜单关系信息
     *
     * @param id 用户菜单关系主键
     * @return 结果
     */
    public int deleteWxUserMenuById(Long id);

    public int addWxUserMenuNew(WxUserMenuReqVo wxUserMenuReqVo);
}
