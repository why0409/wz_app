package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.system.domain.vo.WxUserLogininfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WxUserLogininfoMapper;
import com.ruoyi.system.domain.WxUserLogininfo;
import com.ruoyi.system.service.IWxUserLogininfoService;

/**
 * 微信用户登录信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-05
 */
@Service
public class WxUserLogininfoServiceImpl implements IWxUserLogininfoService
{
    @Autowired
    private WxUserLogininfoMapper wxUserLogininfoMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 查询微信用户登录信息
     *
     * @param phone 微信用户登录信息主键
     * @return 微信用户登录信息
     */
    @Override
    public WxUserLogininfo selectWxUserLogininfoByPhone(String phone)
    {
        return wxUserLogininfoMapper.selectWxUserLogininfoByPhone(phone);
    }

    /**
     * 查询微信用户登录信息列表
     *
     * @param wxUserLogininfo 微信用户登录信息
     * @return 微信用户登录信息
     */
    @Override
    public List<WxUserLogininfo> selectWxUserLogininfoList(WxUserLogininfo wxUserLogininfo)
    {
        return wxUserLogininfoMapper.selectWxUserLogininfoList(wxUserLogininfo);

    }
    @Override
    public List<WxUserLogininfo> selectWxUserLogininfoList3(WxUserLogininfo wxUserLogininfo)
    {
        if (wxUserLogininfo.getOnline().equals("-1")) {
            wxUserLogininfo.setOnline(null);
        }
        return wxUserLogininfoMapper.selectWxUserLogininfoList3(wxUserLogininfo);

    }
    @Override
    public List<WxUserLogininfoDto> selectWxUserLogininfoList2(WxUserLogininfoDto wxUserLogininfoDto)
    {
        WxUserLogininfo wxUserLogininfo =wxUserLogininfoDto;

        List<WxUserLogininfo> list = wxUserLogininfoMapper.selectWxUserLogininfoList(wxUserLogininfo);

        List<WxUserLogininfoDto> wxUserLogininfoDtoList = BeanUtils.convertListList(list,WxUserLogininfoDto.class);
        Set<String> resultSet =redisTemplate.opsForSet().members("wxOnline");
        List<String> phones = resultSet.stream().collect(Collectors.toList());

        wxUserLogininfoDtoList.stream().forEach(p->{
            if(phones.contains(p.getPhone()))
            {
                p.setOnline("1");
            }
            else
            {
                p.setOnline("0");
            }
        });

        if(wxUserLogininfoDto.getOnline().equals("0"))
        {
            wxUserLogininfoDtoList = wxUserLogininfoDtoList.stream().filter(l->l.getOnline()=="0").collect(Collectors.toList());
        }
        else if(wxUserLogininfoDto.getOnline().equals("1"))
        {
            wxUserLogininfoDtoList = wxUserLogininfoDtoList.stream().filter(l->l.getOnline()=="1").collect(Collectors.toList());
        }
        return wxUserLogininfoDtoList;
    }

    /**
     * 新增微信用户登录信息
     *
     * @param wxUserLogininfo 微信用户登录信息
     * @return 结果
     */
    @Override
    public int insertWxUserLogininfo(WxUserLogininfo wxUserLogininfo)
    {
        return wxUserLogininfoMapper.insertWxUserLogininfo(wxUserLogininfo);
    }

    /**
     * 修改微信用户登录信息
     *
     * @param wxUserLogininfo 微信用户登录信息
     * @return 结果
     */
    @Override
    public int updateWxUserLogininfo(WxUserLogininfo wxUserLogininfo)
    {
        return wxUserLogininfoMapper.updateWxUserLogininfo(wxUserLogininfo);
    }

    /**
     * 批量删除微信用户登录信息
     *
     * @param phones 需要删除的微信用户登录信息主键
     * @return 结果
     */
    @Override
    public int deleteWxUserLogininfoByPhones(String[] phones)
    {
        return wxUserLogininfoMapper.deleteWxUserLogininfoByPhones(phones);
    }

    /**
     * 删除微信用户登录信息信息
     *
     * @param phone 微信用户登录信息主键
     * @return 结果
     */
    @Override
    public int deleteWxUserLogininfoByPhone(String phone)
    {
        return wxUserLogininfoMapper.deleteWxUserLogininfoByPhone(phone);
    }

    /**
     * 根据手机号查询微信用户登录信息
     * @param phone
     * @return
     */
    @Override
    public List<WxUserLogininfo> selectByPhone(String phone) {
        return wxUserLogininfoMapper.selectByPhone(phone);
    }

    @Override
    public int updateOfflineStatus(Set<String> set){
        return wxUserLogininfoMapper.updateOfflineStatus(set);
    }

    @Override
    public int getCountByPhone(String phone){
        return wxUserLogininfoMapper.getCountByPhone(phone);
    }
}
