package com.ruoyi.wxuser.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.WxUserMenu;
import com.ruoyi.system.mapper.WxUserMenuMapper;
import com.ruoyi.wxuser.domain.WxUser;
import com.ruoyi.wxuser.mapper.WxUserMapper;
import com.ruoyi.wxuser.service.IWxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户Service业务层处理
 * 
 * @author lgh
 * @date 2022-11-22
 */
@Service
public class WxUserServiceImpl implements IWxUserService
{
    @Resource
    private WxUserMapper wxUserMapper;

    @Resource
    private WxUserMenuMapper wxUserMenuMapper;

    /**
     * 查询用户
     * 
     * @param id 用户主键
     * @return 用户
     */
    @Override
    public WxUser selectWxUserById(String id)
    {
        return wxUserMapper.selectWxUserById(id);
    }

    /**
     * 查询用户列表
     * 
     * @param wxUser 用户
     * @return 用户
     */
    @Override
    public List<WxUser> selectWxUserList(WxUser wxUser)
    {
        return wxUserMapper.selectWxUserList(wxUser);
    }

    /**
     * 新增用户
     * 
     * @param wxUser 用户
     * @return 结果
     */
    @Override
    public int insertWxUser(WxUser wxUser)
    {
        wxUser.setCreateTime(DateUtils.getNowDate());
        return wxUserMapper.insertWxUser(wxUser);
    }

    /**
     * 修改用户
     * 
     * @param wxUser 用户
     * @return 结果
     */
    @Override
    public int updateWxUser(WxUser wxUser)
    {
        wxUser.setUpdateTime(DateUtils.getNowDate());
        return wxUserMapper.updateWxUser(wxUser);
    }

    /**
     * 批量删除用户
     * 
     * @param ids 需要删除的用户主键
     * @return 结果
     */
    @Override
    public int deleteWxUserByIds(String[] ids)
    {
        return wxUserMapper.deleteWxUserByIds(ids);
    }

    /**
     * 删除用户信息
     * 
     * @param id 用户主键
     * @return 结果
     */
    @Override
    public int deleteWxUserById(String id)
    {
        return wxUserMapper.deleteWxUserById(id);
    }

    @Override
    public JSONObject getListByMenuId(Long menuId,int pageNum,int pageSize){
        WxUserMenu wxUserMenu = new WxUserMenu();
        wxUserMenu.setMenuId(menuId);
        List<WxUserMenu> wxUserMenuList = wxUserMenuMapper.selectWxUserMenuList(wxUserMenu);

        List<String> phoneList = new ArrayList<>();
        for (WxUserMenu wu : wxUserMenuList) {
            phoneList.add(wu.getPhone());
        }
        JSONObject result = new JSONObject();
        if (wxUserMenuList.size() == 0){
            result.put("total",0);
            result.put("list",new ArrayList<>());
        }else {
            List<WxUser> wxUserByPhoneList = wxUserMapper.selectWxUserByPhoneList(phoneList,(pageNum-1)*pageSize,pageSize);
            result.put("total",wxUserMapper.countWxUserByPhoneList(phoneList));
            result.put("list",wxUserByPhoneList);
        }

        return result;
    }

    @Override
    public int deleteUserByMenuId(Long menuId,String mobile){
        return wxUserMenuMapper.deleteWxUserMenu(menuId,mobile);
    }
}
