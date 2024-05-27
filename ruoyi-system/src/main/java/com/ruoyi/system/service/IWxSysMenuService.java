package com.ruoyi.system.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.system.domain.WxSysMenu;
import com.ruoyi.system.domain.vo.WxPermDto;
import com.ruoyi.system.domain.vo.WxSysMenuDto;

/**
 * 微信菜单Service接口
 *
 * @author lgh
 * @date 2022-11-22
 */
public interface IWxSysMenuService
{
    /**
     * 查询微信菜单
     *
     * @param id 微信菜单主键
     * @return 微信菜单
     */
    public WxSysMenu selectWxSysMenuById(Long id);

    /**
     * 查询微信菜单列表
     *
     * @param wxSysMenu 微信菜单
     * @return 微信菜单集合
     */
    public List<WxSysMenu> selectWxSysMenuList(WxSysMenu wxSysMenu);

    /**
     * 菜单权限
     * @author:
     * @date: 2022/12/2 9:23
     * @param wxSysMenuDto
     * @return
     */
    WxPermDto selectInfo(WxSysMenuDto wxSysMenuDto);
    /**
     * 新增微信菜单
     *
     * @param wxSysMenu 微信菜单
     * @return 结果
     */
    public int insertWxSysMenu(WxSysMenu wxSysMenu);

    /**
     * 修改微信菜单
     *
     * @param wxSysMenu 微信菜单
     * @return 结果
     */
    public int updateWxSysMenu(WxSysMenu wxSysMenu);

    /**
     * 批量删除微信菜单
     *
     * @param ids 需要删除的微信菜单主键集合
     * @return 结果
     */
    public int deleteWxSysMenuByIds(Long[] ids);

    /**
     * 删除微信菜单信息
     *
     * @param id 微信菜单主键
     * @return 结果
     */
    public int deleteWxSysMenuById(Long id);


    List<Map<String, Object>> queryWxUserMenuByPhone(String phone);
}
