package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.system.domain.vo.WxPermDto;
import com.ruoyi.system.domain.vo.WxSysMenuDto;
import com.ruoyi.system.domain.vo.WxUserMenuVo;
import com.ruoyi.system.service.IWxUserMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WxSysMenuMapper;
import com.ruoyi.system.domain.WxSysMenu;
import com.ruoyi.system.service.IWxSysMenuService;

/**
 * 微信菜单Service业务层处理
 *
 * @author lgh
 * @date 2022-11-22
 */
@Service
public class WxSysMenuServiceImpl implements IWxSysMenuService
{
    @Autowired
    private WxSysMenuMapper wxSysMenuMapper;
    @Autowired
    private IWxUserMenuService wxUserMenuService;

    /**
     * 查询微信菜单
     *
     * @param id 微信菜单主键
     * @return 微信菜单
     */
    @Override
    public WxSysMenu selectWxSysMenuById(Long id)
    {
        return wxSysMenuMapper.selectWxSysMenuById(id);
    }

    /**
     * 查询微信菜单列表
     *
     * @param wxSysMenu 微信菜单
     * @return 微信菜单
     */
    @Override
    public List<WxSysMenu> selectWxSysMenuList(WxSysMenu wxSysMenu)
    {
        return wxSysMenuMapper.selectWxSysMenuList(wxSysMenu);
    }

    /**
     * 菜单权限
     * @param wxSysMenuDto 微信菜单
     * @return 微信菜单
     */
    @Override
    public WxPermDto selectInfo(WxSysMenuDto wxSysMenuDto)
    {
        WxPermDto wxPermDto = new WxPermDto();
        WxSysMenu wxSysMenu = new WxSysMenu();
        BeanUtils.copyBeanProp(wxSysMenu,wxSysMenuDto);
        List<WxSysMenu> wxSysMenuList = wxSysMenuMapper.selectWxSysMenuList(wxSysMenu);
        List<WxUserMenuVo> menuByPhone = wxUserMenuService.getMenuByPhone(wxSysMenuDto.getPhone());
        List<Long> ids = new ArrayList<>();
        for (WxUserMenuVo wxUserMenuVo:menuByPhone){
            ids.add(wxUserMenuVo.getMenuId());
        }
        wxPermDto.setWxSysMenuList(wxSysMenuList);
        wxPermDto.setIds(ids);
        return wxPermDto;
    }

    /**
     * 新增微信菜单
     *
     * @param wxSysMenu 微信菜单
     * @return 结果
     */
    @Override
    public int insertWxSysMenu(WxSysMenu wxSysMenu)
    {
        return wxSysMenuMapper.insertWxSysMenu(wxSysMenu);
    }

    /**
     * 修改微信菜单
     *
     * @param wxSysMenu 微信菜单
     * @return 结果
     */
    @Override
    public int updateWxSysMenu(WxSysMenu wxSysMenu)
    {
        return wxSysMenuMapper.updateWxSysMenu(wxSysMenu);
    }

    /**
     * 批量删除微信菜单
     *
     * @param ids 需要删除的微信菜单主键
     * @return 结果
     */
    @Override
    public int deleteWxSysMenuByIds(Long[] ids)
    {
        return wxSysMenuMapper.deleteWxSysMenuByIds(ids);
    }

    /**
     * 删除微信菜单信息
     *
     * @param id 微信菜单主键
     * @return 结果
     */
    @Override
    public int deleteWxSysMenuById(Long id)
    {
        return wxSysMenuMapper.deleteWxSysMenuById(id);
    }

    @Override
    public List<Map<String, Object>> queryWxUserMenuByPhone(String phone) {
        return wxSysMenuMapper.queryWxUserMenuByPhone(phone);
    }
}
