package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.WxClickmoduleInfo;
import com.ruoyi.system.domain.vo.WxClickmoduleInfoDto;
import com.ruoyi.system.mapper.WxClickmoduleInfoMapper;
import com.ruoyi.system.service.IWxClickmoduleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxClickmoduleInfoServiceImpl implements IWxClickmoduleInfoService {

    @Autowired
    WxClickmoduleInfoMapper wxClickmoduleInfoMapper;
    @Override
    public int insertWxClickmoduleInfo(WxClickmoduleInfo wxClickmoduleInfo) {
        return wxClickmoduleInfoMapper.insertWxClickmoduleInfo(wxClickmoduleInfo);
    }

    @Override
    public List<WxClickmoduleInfo> selectWxClickmoduleInfoRecord(WxClickmoduleInfoDto wxClickmoduleInfo) {
        return wxClickmoduleInfoMapper.selectWxClickmoduleInfoRecord(wxClickmoduleInfo);
    }

    @Override
    public List<WxClickmoduleInfo> selectWxClickmoduleInfoCount(WxClickmoduleInfoDto wxClickmoduleInfo) {
        return wxClickmoduleInfoMapper.selectWxClickmoduleInfoCount(wxClickmoduleInfo);
    }

    @Override
    public void deletetWxClickmoduleInfoExpireDate(int expireDate) {
        wxClickmoduleInfoMapper.deletetWxClickmoduleInfoExpireDate(expireDate);
    }

}
