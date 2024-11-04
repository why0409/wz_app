package com.ruoyi.jishijiao.service;


import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.jishijiao.Dto.ReportDto;
import com.ruoyi.jishijiao.Dto.TranCCityDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public interface SspService {
     JSONObject report(ReportDto reportDto);

     JSONObject eventDetail(String eventId, String mobile);

     JSONObject permit(String mobile);

     JSONObject topFive(String mobile);

     JSONObject eventList(String mobile,String page,String size);

     /**
      *
      * @param mobile 手机号码
      * @param page
      * @param size
      * @param title 事件标题
      * @param sourceFrom 事件来源 1-AI中台，3-区长信箱，4-拍一拍，5-其他，6-12345热线
      * @param deptId 部门
      * @param eventStatus 事件状态  1-办理中,2-已办结，传空查全部
      * @return
      */
     JSONObject cityRunEventList(String mobile,String page,String size,String title,String sourceFrom,
                                 String deptId,String eventStatus,String deviceId);

     JSONObject cityRunEventOverView(String mobile);

     JSONObject cityRunDeviceList(String mobile);

     JSONObject cityRunDeptList();

     JSONObject ShiMinBiaoyangList(String mobile,String page,String size);

     JSONObject SystemBiaoyangList(String mobile,String page,String size);

     JSONObject BiaoyangStatistic(String mobile);

     JSONObject BiaoyangEventStatistic(String mobile);

     JSONObject EventStatisticEchart(String mobile);

     JSONObject paiList(String mobile,String page,String size);

     JSONObject Evaluate(String eventId,String score,String mobile);

     JSONObject deviceList();

     JSONObject cyzxList(TranCCityDto tranCCityDto);

     JSONObject messageList(String mobile,String type,String page,String size);

     JSONObject messageDetail(String mobile,String eventNumber,String fromPublic);

     JSONObject userDetail(String mobile);

     JSONObject mapAuth(String mobile);

     JSONObject updateLabel(String id, String label);

     JSONObject updateVideoPermissions(String id, String videoPermissions);

     JSONObject envProtectList();

     JSONObject envProtectDetail(String eventNumber);

     JSONObject publicEventList(String page,String size);

     JSONObject eventGetTree(String mobile,String deptIds);

     JSONObject eventChaoSong(String mobile,String eventId,String eventInstanceId,String chaoSongRen);

     JSONObject keywordSettingList(String mobile);

     JSONObject proxyWithoutAut(HttpServletRequest request, JSONObject jsonObject) throws URISyntaxException;

     JSONObject proxyWithAut(HttpServletRequest request, Map<String,String> paramMap) throws URISyntaxException;

     JSONObject proxyWithAutNew(HttpServletRequest request, JSONObject jsonObject) throws Exception ;
}
