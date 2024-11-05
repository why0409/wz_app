package com.ruoyi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.system.domain.WxSmsLog;

public interface IWxSmsLogService extends IService<WxSmsLog> {
    int getNumTimes();
}
