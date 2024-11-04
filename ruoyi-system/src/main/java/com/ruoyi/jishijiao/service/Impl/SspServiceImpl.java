package com.ruoyi.jishijiao.service.Impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.Method;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.utils.RsaUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.sign.SignUtils;
import com.ruoyi.jishijiao.Dto.ReportDto;
import com.ruoyi.jishijiao.Dto.TranCCityDto;
import com.ruoyi.jishijiao.service.SspService;
import com.ruoyi.system.service.ISysUploadFileInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SspServiceImpl implements SspService {
    private static final Logger log = LoggerFactory.getLogger(SspServiceImpl.class);

    @Value("${jsj.host}")
    private String host;
    @Value("${jsj.port}")
    private String port;
    @Value("${jsj.appId}")
    private String appId;
    @Value("${jsj.secret}")
    private String secret;
    @Autowired
    private ISysUploadFileInfoService sysUploadFileInfoService;

    /**
     *  随手拍事件上报
     * @author:
     * @date: 2022/11/21 10:30
     */
    @Override
    public JSONObject report(ReportDto reportDto){
        log.info("开始执行随手拍事件上报："+new Date().toString());
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("mobile",reportDto.getMobile());
        paramMap.put("type",reportDto.getType());
        paramMap.put("description",reportDto.getDescription().replaceAll("\r|\t|\n",""));
        paramMap.put("title",reportDto.getTitle());
        paramMap.put("address",reportDto.getAddress());
        paramMap.put("realName",reportDto.getRealName());
        paramMap.put("longitude",reportDto.getLongitude());
        paramMap.put("latitude",reportDto.getLatitude());
        StringBuffer stringBuffer = new StringBuffer();

        String resp = "";
        try {


            paramMap.put("files", JSONUtil.toJsonStr(reportDto.getFileReturnDtoList()));
            String param = this.postParams(paramMap);

            long now = System.currentTimeMillis();
            paramMap.put("appId", appId);
            paramMap.put("timestamp", now + "");
            String sign = SignUtils.getSignObj(paramMap,secret);
            String requst_url = "http://"+host+":"+port+"/api/open/report?appId="+ appId+ "&timestamp="+now +"&sign=" + sign +"&"+param;
            log.info("事件上报请求参数:"+requst_url);
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("data",stringBuffer.toString());
//            resp = HttpRequest.post(requst_url).execute().body();

            resp = HttpRequest.post("http://"+host+":"+port+"/api/open/report")
                    .form("mobile",reportDto.getMobile())
                    .form("type",reportDto.getType())
                    .form("description",reportDto.getDescription().replaceAll("\r|\t|\n",""))
                    .form("title",reportDto.getTitle())
                    .form("address",reportDto.getAddress())
                    .form("realName",reportDto.getRealName())
                    .form("longitude",reportDto.getLongitude())
                    .form("latitude",reportDto.getLatitude())
                    .form("appId",appId)
                    .form("timestamp",now)
                    .form("files", JSONUtil.toJsonStr(reportDto.getFileReturnDtoList()))
                    .form("sign", sign)
                    .execute().body();
            log.info("事件上报返回数据"+JSON.parseObject(resp));

        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }

    /**
     * 事件详情查询
     * @author:
     * @date: 2022/11/22 10:58
     * @param eventId
     * @return
     */
    @Override
    public JSONObject eventDetail(String eventId, String mobile) {
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("eventId",eventId);
        paramMap.put("mobile",mobile);
        String resp = "";
        try {
            String param = this.dealPostParams(paramMap);
            long now = System.currentTimeMillis();
            paramMap.put("appId", appId);
            paramMap.put("timestamp", now + "");
            String sign = SignUtils.getSign(paramMap,secret);
            log.info("事件详情查询请求参数"+param);
            String requst_url = "http://"+host+":"+port+"/api/open/event-detail?appId="+appId+ "&timestamp="+now +"&sign=" + sign +"&"+param;
            resp = HttpRequest.get(requst_url).execute().body();
            log.info("事件详情查询返回数据"+JSON.parseObject(resp));
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }

    /**
     * 用户-判断是否有领导驾驶舱权限
     * @author:
     * @date: 2022/11/21 11:28
     * @param mobile 手机号
     * @return
     */
    @Override
    public JSONObject permit(String mobile) {

        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("mobile",mobile);
        String resp = "";
        try {
            String param = this.dealPostParams(paramMap);
            long now = System.currentTimeMillis();
            paramMap.put("appId", appId);
            paramMap.put("timestamp", now + "");
            String sign = SignUtils.getSign(paramMap,secret);

            String requst_url = "http://"+host+":"+port+"/api/open/permit?appId="+ appId+ "&timestamp="+now +"&sign=" + sign +"&"+param;
            resp = HttpRequest.get(requst_url).execute().body();

        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }

    /**
     * 用户-评价
     * @author:
     * @date: 2022/11/21 11:32
     * @param eventId 打分
     * @param score 评价
     * @return
     */
    @Override
    public JSONObject Evaluate(String eventId,String score,String mobile) {
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("eventId",eventId);
        paramMap.put("score",score);
        paramMap.put("mobile",mobile);
        String resp = "";
        try {
            String param = this.dealPostParams(paramMap);
            long now = System.currentTimeMillis();
            paramMap.put("appId", appId);
            paramMap.put("timestamp", now + "");
            String sign = SignUtils.getSign(paramMap,secret);
            log.info("用户-评价请求参数:"+param);
            String requst_url = "http://"+host+":"+port+"/api/open/score?appId="+ appId+ "&timestamp="+now +"&sign=" + sign +"&"+param;
            resp = HttpRequest.post(requst_url).execute().body();
            log.info("用户-评价返回数据:"+JSON.parseObject(resp));
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }

    /**
     * 随手拍-事件分页查询
     * @author:
     * @date: 2022/11/21 11:32
     * @param mobile 手机号
     * @return
     */
    @Override
    public JSONObject eventList(String mobile,String page,String size) {
        //解密-手机号码
        try {
            mobile = RsaUtils.decryptByPrivateKey(mobile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if(StringUtils.isEmpty(page)){
            page = "1";
        }
        if(StringUtils.isEmpty(size)){
            size = "10";
        }
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("mobile",mobile);
        paramMap.put("page",page);
        paramMap.put("size",size);
        String resp = "";
        try {
            String param = this.dealPostParams(paramMap);
            long now = System.currentTimeMillis();
            paramMap.put("appId", appId);
            paramMap.put("timestamp", now + "");
            String sign = SignUtils.getSign(paramMap,secret);
            log.info("随手拍-事件分页查询请求参数:"+param);
            String requst_url = "http://"+host+":"+port+"/api/open/event-list?appId="+ appId+ "&timestamp="+now +"&sign=" + sign +"&"+param;
            log.info("请求完整路径"+requst_url);
            resp = HttpRequest.get(requst_url).execute().body();
            log.info("随手拍-事件分页查询返回数据:"+JSON.parseObject(resp));
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }


    /**
     * 城市运行事件列表
     * @param mobile 手机号码
     * @param page
     * @param size
     * @param title 事件标题
     * @param sourceFrom 事件来源 1-AI中台，3-区长信箱，4-拍一拍，5-其他，6-12345热线
     * @param deptId 部门
     * @param eventStatus 事件状态  1-办理中,2-已办结，传空查全部
     * @return
     */
    @Override
    public JSONObject cityRunEventList(String mobile,String page,String size,String title,
                                       String sourceFrom,String deptId,String eventStatus,String deviceId) {
        if(StringUtils.isEmpty(page)){
            page = "1";
        }
        if(StringUtils.isEmpty(size)){
            size = "10";
        }
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("mobile",mobile);
        paramMap.put("page",page);
        paramMap.put("size",size);
        if(StringUtils.isNotEmpty(title)&&title!="")
        {
            paramMap.put("title",title);
        }
        if(StringUtils.isNotEmpty(sourceFrom)&&sourceFrom!="")
        {
            paramMap.put("sourceFrom",sourceFrom);
        }
        if(StringUtils.isNotEmpty(deptId)&&deptId!="")
        {
            paramMap.put("deptId",deptId);
        }
        if(StringUtils.isNotEmpty(eventStatus)&&eventStatus!="")
        {
            paramMap.put("eventStatus",eventStatus);
        }

        if(StringUtils.isNotEmpty(deviceId)&&deviceId!="") {
            paramMap.put("deviceId", deviceId);
        }

        String resp = "";
        try {
            String param = this.dealPostParams(paramMap);
            long now = System.currentTimeMillis();
            paramMap.put("appId", appId);
            paramMap.put("timestamp", now + "");
            String sign = SignUtils.getSign(paramMap,secret);
            log.info("城市运行事件预览列表:"+param);
            String requst_url = "http://"+host+":"+port+"/api/open/event/index/list?appId="+ appId+ "&timestamp="+now +"&sign=" + sign +"&"+param;
            log.info("请求完整路径"+requst_url);
            resp = HttpRequest.get(requst_url).execute().body();
            log.info("城市运行事件预览列表:"+JSON.parseObject(resp));
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }

    /**
     * 城市运行-地图-设备点位列表
     * @author:
     * @date: 2022/11/21 11:32
     * @param mobile 手机号
     * @return
     */
    @Override
    public JSONObject cityRunDeviceList(String mobile) {
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("mobile",mobile);
        String resp = "";
        try {
            String param = this.dealPostParams(paramMap);
            long now = System.currentTimeMillis();
            paramMap.put("appId", appId);
            paramMap.put("timestamp", now + "");
            String sign = SignUtils.getSign(paramMap,secret);
            log.info("城市运行-地图-设备点位列表,请求参数:"+param);
            String requst_url = "http://"+host+":"+port+"/api/open/event/map/deviceList?appId="+ appId+ "&timestamp="+now +"&sign=" + sign +"&"+param;
            log.info("请求完整路径"+requst_url);
            resp = HttpRequest.get(requst_url).execute().body();
            log.info("城市运行-地图-设备点位列表,返回结果:"+JSON.parseObject(resp));
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }
    /**
     * 城市运行-部门下拉列表
     * @author:
     * @date: 2022/11/21 11:32
     * @return
     */
    @Override
    public JSONObject cityRunDeptList() {
        Map<String,String> paramMap = new HashMap<>();
        String resp = "";
        try {
            String param = this.dealPostParams(paramMap);
            long now = System.currentTimeMillis();
            paramMap.put("appId", appId);
            paramMap.put("timestamp", now + "");
            String sign = SignUtils.getSign(paramMap,secret);
            log.info("城市运行-部门列表,请求参数:"+param);
            String requst_url = "http://"+host+":"+port+"/api/open/dept/root-list?appId="+ appId+ "&timestamp="+now +"&sign=" + sign +"&"+param;
            log.info("请求完整路径"+requst_url);
            resp = HttpRequest.get(requst_url).execute().body();
            log.info("城市运行-部门列表,返回结果:"+JSON.parseObject(resp));
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }
    /**
     * 市民表扬列表
     * @author:
     * @date: 2022/11/21 11:32
     * @return
     */
    @Override
    public JSONObject ShiMinBiaoyangList(String mobile,String page,String size) {
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("mobile",mobile);
        paramMap.put("page",page);
        paramMap.put("size",size);
        String resp = "";
        try {
            String param = this.dealPostParams(paramMap);
            long now = System.currentTimeMillis();
            paramMap.put("appId", appId);
            paramMap.put("timestamp", now + "");
            String sign = SignUtils.getSign(paramMap,secret);
            log.info("城市运行-市民表扬列表,请求参数:"+param);
            String requst_url = "http://"+host+":"+port+"/api/open/event/biaoyang/list/citizen?appId="+ appId+ "&timestamp="+now +"&sign=" + sign +"&"+param;
            log.info("请求完整路径"+requst_url);
            resp = HttpRequest.get(requst_url).execute().body();
            log.info("城市运行-市民表扬列表,返回结果:"+JSON.parseObject(resp));
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }

    /**
     * 系统表扬列表
     * @author:
     * @date: 2022/11/21 11:32
     * @return
     */
    @Override
    public JSONObject SystemBiaoyangList(String mobile,String page,String size) {
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("mobile",mobile);
        paramMap.put("page",page);
        paramMap.put("size",size);
        String resp = "";
        try {
            String param = this.dealPostParams(paramMap);
            long now = System.currentTimeMillis();
            paramMap.put("appId", appId);
            paramMap.put("timestamp", now + "");
            String sign = SignUtils.getSign(paramMap,secret);
            log.info("城市运行-市民表扬列表,请求参数:"+param);
            String requst_url = "http://"+host+":"+port+"/api/open/event/biaoyang/list/system?appId="+ appId+ "&timestamp="+now +"&sign=" + sign +"&"+param;
            log.info("请求完整路径"+requst_url);
            resp = HttpRequest.get(requst_url).execute().body();
            log.info("城市运行-市民表扬列表,返回结果:"+JSON.parseObject(resp));
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }
    /**
     * 系统表扬统计
     * @author:
     * @date: 2022/11/21 11:32
     * @return
     */
    @Override
    public JSONObject BiaoyangStatistic(String mobile) {
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("mobile",mobile);
        String resp = "";
        try {
            String param = this.dealPostParams(paramMap);
            long now = System.currentTimeMillis();
            paramMap.put("appId", appId);
            paramMap.put("timestamp", now + "");
            String sign = SignUtils.getSign(paramMap,secret);
            log.info("城市运行-市民表扬列表,请求参数:"+param);
            String requst_url = "http://"+host+":"+port+"/api/open/event/biaoyang/statistic?appId="+ appId+ "&timestamp="+now +"&sign=" + sign +"&"+param;
            log.info("请求完整路径"+requst_url);
            resp = HttpRequest.get(requst_url).execute().body();
            log.info("城市运行-市民表扬列表,返回结果:"+JSON.parseObject(resp));
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }
    /**
     * 表扬事件统计
     * @author:
     * @date: 2022/11/21 11:32
     * @return
     */
    @Override
    public JSONObject BiaoyangEventStatistic(String mobile) {
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("mobile",mobile);
        String resp = "";
        try {
            String param = this.dealPostParams(paramMap);
            long now = System.currentTimeMillis();
            paramMap.put("appId", appId);
            paramMap.put("timestamp", now + "");
            String sign = SignUtils.getSign(paramMap,secret);
            log.info("城市运行-表扬事件统计,请求参数:"+param);
            String requst_url = "http://"+host+":"+port+"/api/open/event/statistic/dept?appId="+ appId+ "&timestamp="+now +"&sign=" + sign +"&"+param;
            log.info("请求完整路径"+requst_url);
            resp = HttpRequest.get(requst_url).execute().body();
            log.info("城市运行-表扬事件统计,返回结果:"+JSON.parseObject(resp));
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }
    /**
     * 事件分析统计图表
     * @author:
     * @date: 2022/11/21 11:32
     * @return
     */
    @Override
    public JSONObject EventStatisticEchart(String mobile) {
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("mobile",mobile);
        String resp = "";
        try {
            String param = this.dealPostParams(paramMap);
            long now = System.currentTimeMillis();
            paramMap.put("appId", appId);
            paramMap.put("timestamp", now + "");
            String sign = SignUtils.getSign(paramMap,secret);
            log.info("城市运行-表扬事件统计,请求参数:"+param);
            String requst_url = "http://"+host+":"+port+"/api/open/event/analysis/list?appId="+ appId+ "&timestamp="+now +"&sign=" + sign +"&"+param;
            log.info("请求完整路径"+requst_url);
            resp = HttpRequest.get(requst_url).execute().body();
            log.info("城市运行-表扬事件统计,返回结果:"+JSON.parseObject(resp));
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }
    /**
     * 城市运行事件总览
     * @author:
     * @date: 2022/11/21 11:32
     * @param mobile 手机号
     * @return
     */
    @Override
    public JSONObject cityRunEventOverView(String mobile) {
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("mobile",mobile);
        String resp = "";
        try {
            String param = this.dealPostParams(paramMap);
            long now = System.currentTimeMillis();
            paramMap.put("appId", appId);
            paramMap.put("timestamp", now + "");
            String sign = SignUtils.getSign(paramMap,secret);
            log.info("城市运行事件总览，请求参数:"+param);
            String requst_url = "http://"+host+":"+port+"/api/open/event/index/overview?appId="+ appId+ "&timestamp="+now +"&sign=" + sign +"&"+param;
            log.info("请求完整路径"+requst_url);
            resp = HttpRequest.get(requst_url).execute().body();
            log.info("城市运行事件总览返回数据:"+JSON.parseObject(resp));
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }

    @Override
    public JSONObject paiList(String mobile,String page,String size){
        if(StringUtils.isEmpty(page)){
            page = "1";
        }
        if(StringUtils.isEmpty(size)){
            size = "10";
        }
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("mobile",mobile);
        paramMap.put("page",page);
        paramMap.put("size",size);
        String resp = "";
        try {
            String param = this.dealPostParams(paramMap);
            long now = System.currentTimeMillis();
            paramMap.put("appId", appId);
            paramMap.put("timestamp", now + "");
            String sign = SignUtils.getSign(paramMap,secret);
            log.info("随手拍-事件分页查询请求参数:"+param);
            String requst_url = "http://"+host+":"+port+"/api/open/event/paiList?appId="+ appId+ "&timestamp="+now +"&sign=" + sign +"&"+param;
            log.info("请求完整路径"+requst_url);
            resp = HttpRequest.get(requst_url).execute().body();
            log.info("随手拍-事件分页查询返回数据:"+JSON.parseObject(resp));
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }
    /**
     * 城运中心  地图中心-设备点位列表
     * @author:
     * @date: 2022/11/21 11:32
     * @return
     */
    @Override
    public JSONObject deviceList() {

        Map<String,String> paramMap = new HashMap<>();
        String resp = "";
        try {
            long now = System.currentTimeMillis();
            paramMap.put("appId", appId);
            paramMap.put("timestamp", now + "");
            String sign = SignUtils.getSign(paramMap,secret);
            String requst_url = "http://"+host+":"+port+"/api/open/event/deviceList?appId="+ appId+ "&timestamp="+now +"&sign=" + sign ;
            log.info("请求完整路径"+requst_url);
            resp = HttpRequest.get(requst_url).execute().body();
            log.info("城运中心  地图中心-设备点位列表返回数据"+JSON.parseObject(resp));
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }

    /**
     * 城运中心  地图中心-设备点位列表
     * @author:
     * @date: 2022/11/21 11:32
     * @return
     */
    @Override
    public JSONObject cyzxList(TranCCityDto tranCCityDto) {

        Map<String,String> paramMap = new HashMap<>();
        String resp = "";
        try {
            if(StringUtils.isNotEmpty(tranCCityDto.getMobile())){
                paramMap.put("mobile", tranCCityDto.getMobile());
            }
            if(tranCCityDto.getPage()!=null){
                paramMap.put("page", String.valueOf(tranCCityDto.getPage()));
            }
            if(tranCCityDto.getSize()!=null){
                paramMap.put("size", String.valueOf(tranCCityDto.getSize()));
            }
            if(tranCCityDto.getEventTypeId()!=null){
                paramMap.put("eventTypeId", String.valueOf(tranCCityDto.getEventTypeId()));
            }
            if(tranCCityDto.getSourceFrom()!=null){
                paramMap.put("sourceFrom", String.valueOf(tranCCityDto.getSourceFrom()));
            }
            if(StringUtils.isNotEmpty(tranCCityDto.getStartTime())){
                paramMap.put("startTime", tranCCityDto.getStartTime());
            }
            if(StringUtils.isNotEmpty(tranCCityDto.getEndTime())){
                paramMap.put("endTime", tranCCityDto.getEndTime());
            }
            if(tranCCityDto.getPriority()!=null){
                paramMap.put("priority", String.valueOf(tranCCityDto.getPriority()));
            }
            if(tranCCityDto.getEventStatus()!=null){
                paramMap.put("eventStatus", String.valueOf(tranCCityDto.getEventStatus()));
            }
            if(tranCCityDto.getUid()!=null){
                paramMap.put("uid", String.valueOf(tranCCityDto.getUid()));
            }
            if(tranCCityDto.getDeptId()!=null){
                paramMap.put("deptId", String.valueOf(tranCCityDto.getDeptId()));
            }
            if(tranCCityDto.getCheckedUid()!=null){
                paramMap.put("checkedUid", String.valueOf(tranCCityDto.getCheckedUid()));
            }

            long now = System.currentTimeMillis();
            paramMap.put("appId", appId);
            paramMap.put("timestamp", now + "");
            if(tranCCityDto.getDeviceId()!=null&&StringUtils.isNotEmpty(tranCCityDto.getDeviceId()))
            {
                paramMap.put("deviceId", tranCCityDto.getDeviceId() );
            }
            String param = this.dealPostParams(paramMap);

            String sign = SignUtils.getSign(paramMap,secret);
            log.info("城运中心  地图中心-设备点位列表接口请求参数:"+param+"===============");
            String requst_url = "http://"+host+":"+port+"/api/open/event/list?appId="+ appId+ "&timestamp="+now +"&sign=" + sign +"&"+param;
            log.info("请求完整路径"+requst_url);
            resp = HttpRequest.get(requst_url).execute().body();
            log.info("城运中心  地图中心-设备点位列表返回数据:"+JSON.parseObject(resp));
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }

    /**
     * 事件-累计事件top5
     * @author:
     * @date: 2022/11/21 11:28
     * @param mobile 手机号
     * @return
     */
    @Override
    public JSONObject topFive(String mobile) {

        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("mobile",mobile);
        String resp = "";
        try {
            String param = this.dealPostParams(paramMap);
            long now = System.currentTimeMillis();
            paramMap.put("appId", appId);
            paramMap.put("timestamp", now + "");
            String sign = SignUtils.getSign(paramMap,secret);
            log.info("事件-累计事件top5接口请求参数:"+param+"===============");
            String requst_url = "http://"+host+":"+port+"/api/open/event/topFive?appId="+ appId+ "&timestamp="+now +"&sign=" + sign +"&"+param;
            log.info("请求完整路径"+requst_url);
            resp = HttpRequest.get(requst_url).execute().body();
            log.info("事件-累计事件top5接口返回:"+JSON.parseObject(resp)+"===============");
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }

    /**
     * 领导驾驶舱-用户详情
     * @author:
     * @date: 2022/11/21 11:28
     * @param mobile 手机号
     * @return
     */
    @Override
    public JSONObject userDetail(String mobile) {

        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("mobile",mobile);
        String resp = "";
        try {
            String param = this.dealPostParams(paramMap);
            long now = System.currentTimeMillis();
            paramMap.put("appId", appId);
            paramMap.put("timestamp", now + "");
            String sign = SignUtils.getSign(paramMap,secret);
            log.info("领导驾驶舱-用户详情接口请求参数:"+param+"===============");
            String requst_url = "http://"+host+":"+port+"/api/open/user/detail?appId="+ appId+ "&timestamp="+now +"&sign=" + sign +"&"+param;
            log.info("请求完整路径"+requst_url);
            resp = HttpRequest.get(requst_url).execute().body();
            log.info("领导驾驶舱-用户详情接口返回:"+JSON.parseObject(resp)+"===============");
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }

    /**
     * 是否有地图中心权限
     * @author:
     * @date: 2022/11/21 11:28
     * @param mobile 手机号
     * @return
     */
    @Override
    public JSONObject mapAuth(String mobile) {

        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("mobile",mobile);
        String resp = "";
        try {
            String param = this.dealPostParams(paramMap);
            long now = System.currentTimeMillis();
            paramMap.put("appId", appId);
            paramMap.put("timestamp", now + "");
            String sign = SignUtils.getSign(paramMap,secret);
            log.info("是否有地图中心权限接口请求参数:"+param+"===============");
            String requst_url = "http://"+host+":"+port+"/api/open/event/map-auth?appId="+ appId+ "&timestamp="+now +"&sign=" + sign +"&"+param;
            resp = HttpRequest.get(requst_url).execute().body();
            log.info("是否有地图中心权限接口返回:"+JSON.parseObject(resp)+"===============");
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }


    /**
     * 消息中心  消息列表
     * @author:
     * @date: 2022/11/21 11:28
     * @param mobile 手机号
     * @return
     */
    @Override
    public JSONObject messageList(String mobile,String type,String page,String size) {

        Map<String,String> paramMap = new HashMap<>();
        if(StringUtils.isEmpty(page)){
            page = "1";
        }
        if(StringUtils.isEmpty(size)){
            size = "10";
        }
        paramMap.put("mobile",mobile);
        paramMap.put("type",type);
        paramMap.put("page",page);
        paramMap.put("size",size);
        String resp = "";
        try {
            String param = this.dealPostParams(paramMap);
            long now = System.currentTimeMillis();
            paramMap.put("appId", appId);
            paramMap.put("timestamp", now + "");
            String sign = SignUtils.getSign(paramMap,secret);

            log.info("城运中心 消息列表接口请求参数:"+param+"=========");
            String requst_url = "http://"+host+":"+port+"/api/open/message/list?appId="+ appId+ "&timestamp="+now +"&sign=" + sign +"&"+param;
            log.info("请求完整路径"+requst_url);
            resp = HttpRequest.get(requst_url).execute().body();
            log.info("城运中心 消息列表接口接口返回:"+JSON.parseObject(resp)+"===============");
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }

    /**
     * 消息中心  事件详情
     * @author:
     * @date: 2022/11/21 11:28
     * @param mobile 手机号
     * @return
     */
    @Override
    public JSONObject messageDetail(String mobile,String eventNumber,String fromPublic) {

        Map<String,String> paramMap = new HashMap<>();

        paramMap.put("mobile",mobile);
        paramMap.put("eventNumber",eventNumber);
        if (StringUtils.isNotEmpty(fromPublic)){
            paramMap.put("fromPublic",fromPublic);
        }

        String resp = "";
        try {
            String param = this.dealPostParams(paramMap);
            long now = System.currentTimeMillis();
            paramMap.put("appId", appId);
            paramMap.put("timestamp", now + "");
            String sign = SignUtils.getSign(paramMap,secret);

            log.info("消息中心  事件详情接口请求参数:"+param+"=========");
            String requst_url = "http://"+host+":"+port+"/api/open/event/detail?appId="+ appId+ "&timestamp="+now +"&sign=" + sign +"&"+param;
            log.info("请求完整路径"+requst_url);
            resp = HttpRequest.get(requst_url).execute().body();
            log.info("消息中心  事件详情接口接口返回:"+JSON.parseObject(resp)+"===============");
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }

    @Override
    public JSONObject updateLabel(String id, String label) {
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("id",id);
        paramMap.put("label",label);
        String resp = "";
        try {
            String param = this.dealPostParams(paramMap);
            long now = System.currentTimeMillis();
            paramMap.put("appId", appId);
            paramMap.put("timestamp", now + "");
            String sign = SignUtils.getSign(paramMap,secret);
            log.info("开始更新微信用户:"+id+"的标签内容"+"===============");
            String requst_url = "http://"+host+":"+port+"/api/open/user/label?appId="+ appId+ "&timestamp="+now +"&sign=" + sign +"&"+param;
            resp = HttpRequest.get(requst_url).execute().body();
            log.info("结束更新微信用户:"+id+"的标签内容"+"===============");
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }

    @Override
    public JSONObject updateVideoPermissions(String id, String videoPermissions) {
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("id",id);
        paramMap.put("videoPermissions",videoPermissions);
        String resp = "";
        try {
            String param = this.dealPostParams(paramMap);
            long now = System.currentTimeMillis();
            paramMap.put("appId", appId);
            paramMap.put("timestamp", now + "");
            String sign = SignUtils.getSign(paramMap,secret);
            log.info("开始更新微信用户:"+id+"的城市之眼权限"+"===============");
            String requst_url = "http://"+host+":"+port+"/api/open/user/modify?appId="+ appId+ "&timestamp="+now +"&sign=" + sign +"&"+param;
            resp = HttpRequest.post(requst_url).execute().body();
            log.info("结束更新微信用户:"+id+"的城市之眼权限"+"===============");
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }


    /**
     * 处理POST请求URL
     *
     * @param params
     * @return
     */
    private String dealPostParams(Map<String, String> params) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            try {
                sb.append(entry.getKey()).append("=")
                        .append(entry.getValue())
                        .append("&");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(sb.length()>0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    private String postParams(Map<String, Object> params) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            try {
                sb.append(entry.getKey()).append("=")
                        .append(entry.getValue().toString())
                        .append("&");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(sb.length()>0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }


    @Override
    public JSONObject envProtectList(){
        Map<String,String> paramMap = new HashMap<>();
        String resp = "";
        try {
            String param = this.dealPostParams(paramMap);
            long now = System.currentTimeMillis();
            paramMap.put("appId", appId);
            paramMap.put("timestamp", now + "");
            String sign = SignUtils.getSign(paramMap,secret);
            log.info("请求环保事件列表接口===============");
            String requst_url = "http://"+host+":"+port+"/api/open/bigScreen/statistic/list/envProtect?appId="+ appId+ "&timestamp="+now +"&sign=" + sign +"&"+param;
            resp = HttpRequest.get(requst_url).execute().body();
            log.info("是否环保事件列表接口返回:"+JSON.parseObject(resp)+"===============");
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }

    @Override
    public JSONObject envProtectDetail(String eventNumber){
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("eventNumber", eventNumber);
        String resp = "";
        try {
            String param = this.dealPostParams(paramMap);
            long now = System.currentTimeMillis();
            paramMap.put("appId", appId);
            paramMap.put("timestamp", now + "");
            String sign = SignUtils.getSign(paramMap,secret);
            log.info("请求环保事件列表接口===============");
            String requst_url = "http://"+host+":"+port+"/api/open/bigScreen/statistic/detail/envProtect?appId="+ appId+ "&timestamp="+now +"&sign=" + sign +"&"+param;
            resp = HttpRequest.get(requst_url).execute().body();
            log.info("是否环保事件详情接口返回:"+JSON.parseObject(resp)+"===============");
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }

    @Override
    public JSONObject publicEventList(String page,String size){
        Map<String,String> paramMap = new HashMap<>();
        if(StringUtils.isEmpty(page)){
            page = "1";
        }
        if(StringUtils.isEmpty(size)){
            size = "10";
        }
        paramMap.put("page",page);
        paramMap.put("size",size);
        String resp = "";
        try {
            String param = this.dealPostParams(paramMap);
            long now = System.currentTimeMillis();
            paramMap.put("appId", appId);
            paramMap.put("timestamp", now + "");
            String sign = SignUtils.getSign(paramMap,secret);

            log.info("回应列表接口请求参数:"+param+"=========");
            String requst_url = "http://"+host+":"+port+"/api/open/event/list/public?appId="+ appId+ "&timestamp="+now +"&sign=" + sign +"&"+param;
            log.info("请求完整路径"+requst_url);
            resp = HttpRequest.get(requst_url).execute().body();
            log.info("回应列表接口接口返回:"+JSON.parseObject(resp)+"===============");
        }catch (Exception e){
            e.printStackTrace();
        }

        return JSON.parseObject(resp);

    }

    @Override
    public JSONObject eventGetTree(String mobile,String deptIds){
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("mobile",mobile);
        paramMap.put("deptIds",deptIds);
        String resp = "";
        try {
            String param = this.dealPostParams(paramMap);
            long now = System.currentTimeMillis();
            paramMap.put("appId", appId);
            paramMap.put("timestamp", now + "");
            String sign = SignUtils.getSign(paramMap,secret);

            log.info("事件获取抄送树接口请求参数:"+param+"=========");
            String requst_url = "http://"+host+":"+port+"/api/open/event/getTree?appId="+ appId+ "&timestamp="+now +"&sign=" + sign +"&"+param;
            log.info("事件获取抄送树接口请求完整路径"+requst_url);
            resp = HttpRequest.get(requst_url).execute().body();
            log.info("事件获取抄送树接口返回:"+JSON.parseObject(resp)+"===============");
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }

    @Override
    public JSONObject eventChaoSong(String mobile,String eventId,String eventInstanceId,String chaoSongRen){
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("mobile",mobile);
        paramMap.put("eventId",eventId);
        paramMap.put("eventInstanceId",eventInstanceId);
        paramMap.put("chaoSongRen",chaoSongRen);
        String resp = "";
        try {
            String param = this.dealPostParams(paramMap);
            long now = System.currentTimeMillis();
            paramMap.put("appId", appId);
            paramMap.put("timestamp", now + "");
            String sign = SignUtils.getSign(paramMap,secret);

            log.info("事件-抄送接口请求参数:"+param+"=========");
            String requst_url = "http://"+host+":"+port+"/api/open/event/chaoSong?appId="+ appId+ "&timestamp="+now
                    +"&sign=" + sign +"&"+param;
            log.info("事件-抄送接口请求完整路径"+requst_url);
            resp = HttpRequest.post(requst_url).execute().body();
            log.info("事件-抄送接口返回:"+JSON.parseObject(resp)+"===============");
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }

    @Override
    public JSONObject keywordSettingList(String mobile){
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("mobile",mobile);
        String resp = "";
        try {
            String param = this.dealPostParams(paramMap);
            long now = System.currentTimeMillis();
            paramMap.put("appId", appId);
            paramMap.put("timestamp", now + "");
            String sign = SignUtils.getSign(paramMap,secret);

            log.info("关键词设置-列表查询接口请求参数:"+param+"=========");
            String requst_url = "http://"+host+":"+port+"/api/open/keyword/setting-list?appId="+ appId+ "&timestamp="+now +"&sign=" + sign +"&"+param;
            log.info("关键词设置-列表查询接口请求完整路径"+requst_url);
            resp = HttpRequest.get(requst_url).execute().body();
            log.info("关键词设置-列表查询接口返回:"+JSON.parseObject(resp)+"===============");
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseObject(resp);
    }

    @Override
    public JSONObject proxyWithoutAut(HttpServletRequest request, JSONObject jsonObject) throws URISyntaxException {
        Map<String,String> paramMap = jsonObject.to(Map.class);
        String methodName = request.getMethod();
        if (jsonObject.containsKey("targetMethod") && jsonObject.containsKey("targetParam") && jsonObject.size() == 2) {
            methodName = jsonObject.getString("targetMethod");
            paramMap = jsonObject.getObject("targetParam",Map.class);
        }

        String param = this.dealPostParams(paramMap);

        URI uri = new URI(request.getRequestURI());
        String path = uri.getPath();
        String target = "http://"+host+":"+port+path.replace("/jsj/proxyWithoutAut", "");

        if (param != null && !param.equals("") && !param.equals("null")) {
            target = target + "?" + param;
        }
        // 执行代理查询
        JSONObject result = new JSONObject();
        try {
            log.info("接口转发请求路径："+target);
            String resp = HttpRequest.patch(target).method(Method.valueOf(methodName)).execute().body();
            log.info("接口转发返回数据："+ JSON.parseObject(resp));
            result = JSON.parseObject(resp);
        }catch (Exception e){
            e.printStackTrace();
            result.put("msg","接口请求失败");
        }
        return result;
    }

    @Override
    public JSONObject proxyWithAut(HttpServletRequest request, Map<String,String> paramMap) throws URISyntaxException {
        //api鉴权
        String methodName = request.getMethod();

        String param = this.dealPostParams(paramMap);
        long now = System.currentTimeMillis();
        paramMap.put("appId", appId);
        paramMap.put("timestamp", now + "");
        String sign = SignUtils.getSign(paramMap,secret);

        URI uri = new URI(request.getRequestURI());
        String path = uri.getPath();
        String target = "http://"+host+":"+port+path.replace("/jsj/proxyWithAut", "")+"?appId="+appId+ "&timestamp="+now+"&sign="+sign;

        if (param != null && !param.equals("") && !param.equals("null")) {
            target = target +"&"+param;
        }
        // 执行代理查询
        JSONObject result = new JSONObject();
        try {
            log.info(methodName+"接口转发请求路径："+target);
            String resp = HttpRequest.patch(target).method(Method.valueOf(methodName)).execute().body();
            log.info("接口转发返回数据："+ JSON.parseObject(resp));
            result = JSON.parseObject(resp);
        }catch (Exception e){
            e.printStackTrace();
            result.put("msg","接口请求失败");
        }
        return result;
    }

    @Override
    public JSONObject proxyWithAutNew(HttpServletRequest request, JSONObject jsonObject) throws Exception  {
        //api鉴权
        Map<String,String> paramMap = jsonObject.to(Map.class);

        String methodName = request.getMethod();
        if (jsonObject.containsKey("targetMethod") && jsonObject.containsKey("targetParam") && jsonObject.size() == 2) {
            methodName = jsonObject.getString("targetMethod");
            paramMap = jsonObject.getObject("targetParam",Map.class);
        }

        String param = this.dealPostParams(paramMap);
        long now = System.currentTimeMillis();
        paramMap.put("appId", appId);
        paramMap.put("timestamp", now + "");
        String sign = SignUtils.getSign(paramMap,secret);

        URI uri = new URI(request.getRequestURI());
        String path = uri.getPath();
        String target = "http://"+host+":"+port+path.replace("/jsj/proxyWithAutNew", "")+"?appId="+appId+ "&timestamp="+now+"&sign="+sign;

        if (param != null && !param.equals("") && !param.equals("null")) {
            target = target +"&"+param;
        }

        // 执行代理查询
        JSONObject result = new JSONObject();
        try {
            log.info(methodName+"接口转发请求路径："+target);
            String resp = HttpRequest.patch(target).method(Method.valueOf(methodName)).execute().body();
            log.info("接口转发返回数据："+ JSON.parseObject(resp));
            result = JSON.parseObject(resp);
        }catch (Exception e){
            e.printStackTrace();
            result.put("msg","接口请求失败");
        }
        return result;
    }

}
