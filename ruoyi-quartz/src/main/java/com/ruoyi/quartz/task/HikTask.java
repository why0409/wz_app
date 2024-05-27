package com.ruoyi.quartz.task;

import com.ruoyi.hikvision.service.HikvisionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("HikTask")
public class HikTask {

    private static final Logger log = LoggerFactory.getLogger(HikTask.class);

    @Autowired
    private HikvisionService hikvisionService;


    public void getHIKList(Integer pageNo,Integer pageSize){
        log.info("定时获取海康列表任务开始");
        log.info("开始时间为："+System.currentTimeMillis());
        hikvisionService.TimingGetHIKList(pageNo,pageSize);
        log.info("定时获取海康列表任务结束");
        log.info("结束时间为："+System.currentTimeMillis());
    }

    public void getCameraUrl(){
        log.info("定时获取海康摄像头url任务开始");
        log.info("开始时间为："+System.currentTimeMillis());
        hikvisionService.getCameraUrl();
        log.info("定时获取海康摄像头url任务结束");
        log.info("结束时间为："+System.currentTimeMillis());
    }
}
