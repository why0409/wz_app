package com.ruoyi.jsjDb.service;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.jsjDb.domain.WxUser;

import java.util.List;

/**
 * 用户Service接口
 *
 * @author lgh
 * @date 2022-11-22
 */
public interface IWxUserService
{
    /**
     * 查询用户
     *
     * @param id 用户主键
     * @return 用户
     */
    public WxUser selectWxUserById(String id);

    /**
     * 查询用户列表
     *
     * @param wxUser 用户
     * @return 用户集合
     */
    public List<WxUser> selectWxUserList(WxUser wxUser);

    /**
     * 新增用户
     *
     * @param wxUser 用户
     * @return 结果
     */
    public int insertWxUser(WxUser wxUser);

    /**
     * 修改用户
     *
     * @param wxUser 用户
     * @return 结果
     */
    public int updateWxUser(WxUser wxUser);

    /**
     * 批量删除用户
     *
     * @param ids 需要删除的用户主键集合
     * @return 结果
     */
    public int deleteWxUserByIds(String[] ids);

    /**
     * 删除用户信息
     *
     * @param id 用户主键
     * @return 结果
     */
    public int deleteWxUserById(String id);

    public JSONObject getListByMenuId(Long menuId, int pageNum, int pageSize);

    public int deleteUserByMenuId(Long menuId,String mobile);
}
