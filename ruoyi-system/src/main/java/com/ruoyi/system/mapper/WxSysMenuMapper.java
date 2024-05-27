package com.ruoyi.system.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.system.domain.WxSysMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * 微信菜单Mapper接口
 *
 * @author lgh
 * @date 2022-11-22
 */
@Mapper
public interface WxSysMenuMapper
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
     * 删除微信菜单
     *
     * @param id 微信菜单主键
     * @return 结果
     */
    public int deleteWxSysMenuById(Long id);

    /**
     * 批量删除微信菜单
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxSysMenuByIds(Long[] ids);

    List<Map<String, Object>> queryWxUserMenuByPhone(String phone);
}
