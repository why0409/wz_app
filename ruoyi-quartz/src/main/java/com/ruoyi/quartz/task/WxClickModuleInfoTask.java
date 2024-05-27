package com.ruoyi.quartz.task;

import com.ruoyi.system.mapper.WxClickmoduleInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("WxClickModuleInfoTask")
public class WxClickModuleInfoTask {

    @Autowired
    WxClickmoduleInfoMapper wxClickmoduleInfoMapper;

    private static Logger logger = LoggerFactory.getLogger(WxLoginInfoTask.class);

    public void deletetWxClickmoduleInfoExpireDate(){
        logger.info("删除小程序过期点击模块日志=================");
        wxClickmoduleInfoMapper.deletetWxClickmoduleInfoExpireDate(30);
    }

}
