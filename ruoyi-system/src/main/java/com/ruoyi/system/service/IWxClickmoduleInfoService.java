package com.ruoyi.system.service;

import com.ruoyi.system.domain.WxClickmoduleInfo;
import com.ruoyi.system.domain.WxUserLogininfo;
import com.ruoyi.system.domain.vo.WxClickmoduleInfoDto;
import com.ruoyi.system.domain.vo.WxUserLogininfoDto;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * 微信用户登录信息Service接口
 *
 * @author ruoyi
 * @date 2022-12-05
 */
public interface IWxClickmoduleInfoService
{
    public int insertWxClickmoduleInfo(WxClickmoduleInfo wxClickmoduleInfo);

    /**
     * 查询个人的点击数据
     * @param wxClickmoduleInfo
     * @return
     */
    public List<WxClickmoduleInfo> selectWxClickmoduleInfoRecord(WxClickmoduleInfoDto wxClickmoduleInfo);

    public List<WxClickmoduleInfo> selectWxClickmoduleInfoCount(WxClickmoduleInfoDto wxClickmoduleInfo);

    /**
     * 删除过期小程序点击日志
     * @param expireDate
     */
    public void deletetWxClickmoduleInfoExpireDate(int expireDate);
}
