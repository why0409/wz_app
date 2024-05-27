package com.ruoyi.web.controller.hikvision;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.hikvision.service.HikvisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

@RestController
@RequestMapping("/hikvision")
public class HikvisionController {
    @Autowired
    private HikvisionService hikvisionService;


    /**
     * 获取海康摄像头列表
     * @author:
     * @date: 2022/11/22 15:58
     * @param request
     * @return
     */
    @RequestMapping("/getAllShiPingInfo")
    public AjaxResult getAllShiPingInfo(HttpServletRequest request){
       Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
       Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
       Map<String,Object> map = hikvisionService.getAllShiPingInfo(pageNo,pageSize);
       return AjaxResult.success(map);
    }
}
