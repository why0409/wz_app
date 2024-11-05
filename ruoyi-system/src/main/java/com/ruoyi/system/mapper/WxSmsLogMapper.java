package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.WxSmsLog;

public interface WxSmsLogMapper extends BaseMapper<WxSmsLog> {
    int getNumTimes();
}
