package com.ruoyi.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ruoyi.system.domain.WxUserLogininfo;
import com.ruoyi.system.domain.vo.WxUserLogininfoDto;

/**
 * 微信用户登录信息Service接口
 *
 * @author ruoyi
 * @date 2022-12-05
 */
public interface IWxUserLogininfoService
{
    /**
     * 查询微信用户登录信息
     *
     * @param phone 微信用户登录信息主键
     * @return 微信用户登录信息
     */
    public WxUserLogininfo selectWxUserLogininfoByPhone(String phone);

    /**
     * 查询微信用户登录信息列表
     *
     * @param wxUserLogininfo 微信用户登录信息
     * @return 微信用户登录信息集合
     */
    public List<WxUserLogininfoDto> selectWxUserLogininfoList2(WxUserLogininfoDto wxUserLogininfoDto);

    public List<WxUserLogininfo> selectWxUserLogininfoList(WxUserLogininfo wxUserLogininfo);

    public List<WxUserLogininfo> selectWxUserLogininfoList3(WxUserLogininfo wxUserLogininfo);
    /**
     * 新增微信用户登录信息
     *
     * @param wxUserLogininfo 微信用户登录信息
     * @return 结果
     */
    public int insertWxUserLogininfo(WxUserLogininfo wxUserLogininfo);

    /**
     * 修改微信用户登录信息
     *
     * @param wxUserLogininfo 微信用户登录信息
     * @return 结果
     */
    public int updateWxUserLogininfo(WxUserLogininfo wxUserLogininfo);

    /**
     * 批量删除微信用户登录信息
     *
     * @param phones 需要删除的微信用户登录信息主键集合
     * @return 结果
     */
    public int deleteWxUserLogininfoByPhones(String[] phones);

    /**
     * 删除微信用户登录信息信息
     *
     * @param phone 微信用户登录信息主键
     * @return 结果
     */
    public int deleteWxUserLogininfoByPhone(String phone);

    /**
     * 根据手机号查询微信用户登录信息
     * @param phone
     * @return
     */
    List<WxUserLogininfo> selectByPhone(String phone);

    public int updateOfflineStatus(Set<String> set);

    public int getCountByPhone(String phone);
}
