package com.ruoyi.system.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.ruoyi.system.domain.WxUserLogininfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 微信用户登录信息Mapper接口
 *
 * @author ruoyi
 * @date 2022-12-05
 */
@Mapper
public interface WxUserLogininfoMapper
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
    public List<WxUserLogininfo> selectWxUserLogininfoList(WxUserLogininfo wxUserLogininfo);

    /**
     * 查询微信用户登录信息列表
     *
     * @param wxUserLogininfo 微信用户登录信息
     * @return 微信用户登录信息集合
     */
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
     * 删除微信用户登录信息
     *
     * @param phone 微信用户登录信息主键
     * @return 结果
     */
    public int deleteWxUserLogininfoByPhone(String phone);

    /**
     * 批量删除微信用户登录信息
     *
     * @param phones 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxUserLogininfoByPhones(String[] phones);

    /**
     * 根据手机号查询微信用户登录信息
     * @param phone
     * @return
     */
    List<WxUserLogininfo> selectByPhone(String phone);

    /**
     * 根据手机号更新用户登录时间
     * @param logininfo
     */
    void updateLoginTimeByPhone(WxUserLogininfo logininfo);

    public int updateOfflineStatus(@Param("set") Set<String> set);

}
