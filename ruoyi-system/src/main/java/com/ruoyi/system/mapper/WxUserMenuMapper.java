package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.WxUserMenu;
import com.ruoyi.system.domain.vo.WxUserMenuVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户菜单关系Mapper接口
 *
 * @author ruoyi
 * @date 2022-11-22
 */
@Mapper
public interface WxUserMenuMapper
{
    /**
     * 查询用户菜单关系
     *
     * @param id 用户菜单关系主键
     * @return 用户菜单关系
     */
    public WxUserMenu selectWxUserMenuById(Long id);

    /**
     * 根据手机号查询用户菜单关系
     *
     * @param phone 手机号
     * @return 用户菜单关系
     */
    public List<WxUserMenuVo> getMenuByPhone(String phone);

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
     * 批量新增角色菜单信息
     *
     * @param wxUserMenuList 角色菜单列表
     * @return 结果
     */
    public int addWxUserMenu(List<WxUserMenu> wxUserMenuList);

    /**
     * 修改用户菜单关系
     *
     * @param wxUserMenu 用户菜单关系
     * @return 结果
     */
    public int updateWxUserMenu(WxUserMenu wxUserMenu);

    /**
     * 删除用户菜单关系
     *
     * @param id 用户菜单关系主键
     * @return 结果
     */
    public int deleteWxUserMenuById(Long id);

    /**
     * 批量删除用户菜单关系
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxUserMenuByIds(Long[] ids);

    void deleteWxUserMenuByPhone(String phone);

    int deleteWxUserMenu(@Param("menuId") Long menuId, @Param("phone")String phone);

    void deleteWxUserMenuByPhoneNew(String phone);

    public int addWxUserMenuNew(List<WxUserMenu> wxUserMenuList);

}
