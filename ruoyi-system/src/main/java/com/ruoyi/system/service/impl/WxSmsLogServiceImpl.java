package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.system.domain.WxSmsLog;
import com.ruoyi.system.mapper.WxSmsLogMapper;
import com.ruoyi.system.service.IWxSmsLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxSmsLogServiceImpl extends ServiceImpl<WxSmsLogMapper, WxSmsLog> implements IWxSmsLogService {
    @Autowired
    private WxSmsLogMapper wxSmsLogMapper;

    @Override
    public int getNumTimes() {
        return wxSmsLogMapper.getNumTimes();
    }
}
