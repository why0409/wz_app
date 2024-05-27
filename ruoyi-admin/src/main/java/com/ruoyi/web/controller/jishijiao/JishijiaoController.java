package com.ruoyi.web.controller.jishijiao;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.urllink.GenerateUrlLinkRequest;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.net.URLEncoder;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.ImageUtils;
import com.ruoyi.framework.config.ServerConfig;
import com.ruoyi.jishijiao.Dto.FileReturnDto;
import com.ruoyi.jishijiao.Dto.ReportDto;
import com.ruoyi.jishijiao.Dto.TranCCityDto;
import com.ruoyi.jishijiao.service.SspService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 数据维护Controller
 *
 * @author ruoyi
 * @date 2022-11-17
 */
@RestController
@Slf4j
@RequestMapping("/jsj")
public class JishijiaoController extends BaseController {

    @Value("${jsj.jsonPath}")
    private String jsonPath;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private SspService sspService;

    private final static int iCount = 3;

    private final static int vCount = 3;

    @Autowired
    private WxMaService wxMaService;

    /**
     * 随手拍上报接口
     *
     * @author:
     * @date: 2022/11/21 10:58
     */
    @RequestMapping("/report")
    public AjaxResult report(@RequestBody ReportDto reportDto) {
        String key = reportDto.getMobile() + reportDto.getTitle();
        if (redisCache.keyIsExists(CacheConstants.SSP_CHECK_KEY + key)) {
            return error("1分钟之内禁止提交重复信息");
        } else {
            //1分钟内禁止重复提交
            redisCache.setCacheObject(CacheConstants.SSP_CHECK_KEY + key, "1", 1, TimeUnit.MINUTES);
        }
        List<FileReturnDto> fileReturnDtoList = reportDto.getFileReturnDtoList();
        int imgCount = 0;
        int videoCount = 0;
        for (FileReturnDto fileReturnDto : fileReturnDtoList) {
            if (fileReturnDto.getFileType().equals("image")) {
                imgCount++;
            } else if (fileReturnDto.getFileType().equals("video")) {
                videoCount++;
            }
        }
        if (imgCount > iCount) {
            return error("上报图片的最大数量为" + iCount + "张");
        }
        if (videoCount > vCount) {
            return error("上报视频的最大数量为" + vCount + "个");
        }
        return success(sspService.report(reportDto));
    }

    /**
     * 用户-随手拍事件详情
     *
     * @param request
     * @return
     * @author:
     * @date: 2022/11/21 11:16
     */
    @RequestMapping("/eventDetail")
    public AjaxResult eventDetail(HttpServletRequest request) {
        String eventId = request.getParameter("eventId");
        String mobile = request.getParameter("mobile");
        JSONObject jsonObject = sspService.eventDetail(eventId, mobile);
        if (jsonObject.get("score") == null) {
            jsonObject.put("score", "null");
        }
        return success(jsonObject);
    }

    /**
     * 用户-用户评分
     *
     * @param jsonObject
     * @return
     * @author:
     * @date: 2022/11/21 11:16
     */
    @RequestMapping("/Evaluate")
    public AjaxResult Evaluate(@RequestBody JSONObject jsonObject) {
        String eventId = jsonObject.getString("eventId");
        String score = jsonObject.getString("score");
        String mobile = jsonObject.getString("mobile");
        return success(sspService.Evaluate(eventId,score,mobile));
    }

    /**
     * 用户-随手拍事件分页查询
     *
     * @param request
     * @return
     * @author:
     * @date: 2022/11/21 11:29
     */
    @RequestMapping("/eventList")
    public AjaxResult eventList(HttpServletRequest request) {
        String mobile = request.getParameter("mobile");
        String page = request.getParameter("page");
        String size = request.getParameter("size");
        return success(sspService.eventList(mobile, page, size));
    }

    @RequestMapping("/paiList")
    public AjaxResult paiList(HttpServletRequest request) {
        String mobile = request.getParameter("mobile");
        String page = request.getParameter("page");
        String size = request.getParameter("size");
        return success(sspService.paiList(mobile, page, size));
    }

    /**
     * 城运中心  地图中心-设备点位列表
     *
     * @return
     * @author:
     * @date: 2022/11/21 11:29
     */
    @RequestMapping("/deviceList")
    public AjaxResult deviceList() {
        return success(sspService.deviceList());
    }

    /**
     * 城运中心  事件-事件列表
     *
     * @return
     * @author:
     * @date: 2022/11/21 11:29
     */
    @RequestMapping("/cyzxList")
    public AjaxResult cyzxList(@RequestBody TranCCityDto tranCCityDto) {
        return success(sspService.cyzxList(tranCCityDto));
    }

    /**
     * 用户-判断是否有领导驾驶舱权限
     *
     * @param request
     * @return
     * @author:
     * @date: 2022/11/21 11:26
     */
    @RequestMapping("/permit")
    @Deprecated
    public AjaxResult permit(HttpServletRequest request) {
        String mobile = request.getParameter("mobile");
        return success(sspService.permit(mobile));
    }

    /**
     * 城运中心 事件-累计事件top5
     *
     * @param request
     * @return
     * @author:
     * @date: 2022/11/21 11:26
     */
    @RequestMapping("/topFive")
    @Deprecated
    public AjaxResult topFive(HttpServletRequest request) {
        String mobile = request.getParameter("mobile");
        return success(sspService.topFive(mobile));
    }

    /**
     * 消息中心  消息列表
     *
     * @param request
     * @return
     * @author:
     * @date: 2022/11/21 11:29
     */
    @RequestMapping("/messageList")
    public AjaxResult messageList(HttpServletRequest request) {
        String mobile = request.getParameter("mobile");
        String type = request.getParameter("type");
        String page = request.getParameter("page");
        String size = request.getParameter("size");
        return success(sspService.messageList(mobile, type, page, size));
    }

    /**
     * 消息中心  事件详情
     *
     * @param request
     * @return
     * @author:
     * @date: 2022/11/21 11:29
     */
    @RequestMapping("/messageDetail")
    public AjaxResult messageDetail(HttpServletRequest request) {
        String mobile = request.getParameter("mobile");
        String eventNumber = request.getParameter("eventNumber");
        String fromPublic = request.getParameter("fromPublic");
        return success(sspService.messageDetail(mobile, eventNumber, fromPublic));
    }

    /**
     * 消息中心  事件详情
     *
     * @param request
     * @return
     * @author:
     * @date: 2022/11/21 11:29
     */
    @RequestMapping("/userDetail")
    public AjaxResult userDetail(HttpServletRequest request) {
        String mobile = request.getParameter("mobile");
        return success(sspService.userDetail(mobile));
    }

    /**
     * 消息中心  事件详情
     *
     * @param request
     * @return
     * @author:
     * @date: 2022/11/21 11:29
     */
    @RequestMapping("/mapAuth")
    public AjaxResult mapAuth(HttpServletRequest request) {
        String mobile = request.getParameter("mobile");
        return success(sspService.mapAuth(mobile));
    }

    /**
     * 从极视角接收健康小屋信息
     *
     * @param jsonObject
     * @author:
     * @date: 2022/11/26 17:25
     */
    @RequestMapping("/receiveInfoFromJSJ")
    public AjaxResult receiveInfoFromJSJ(@RequestBody JSONObject jsonObject) {
        String id = jsonObject.getString("cid");
        String base64 = jsonObject.getString("base64");
        String filePath = RuoYiConfig.getUploadPath() + "/jsj";
        String fileName = id + ".jpg";
        ImageUtils.convertBase64ToImage(base64, filePath, fileName);
        int dirLastIndex = RuoYiConfig.getProfile().length() + 1;
        String currentDir = StringUtils.substring(filePath, dirLastIndex);
        jsonObject.remove("base64");
        String img_url = serverConfig.getUrl() + Constants.RESOURCE_PREFIX + "/" + currentDir + "/" + fileName;
        jsonObject.put("pic", img_url);
        redisCache.setCacheObject(CacheConstants.JSJ_KEY + id, jsonObject);
        return success();
    }

    /**
     * 前端查询核酸小屋情况
     *
     * @author:
     * @date: 2022/11/26 17:25
     */
    @RequestMapping("/queryHSXWInfoFromH5")
    public AjaxResult queryHSXWInfoFromH5(HttpServletRequest request) {
        String cid = request.getParameter("cid");
        return success((JSONObject) redisCache.getCacheObject(CacheConstants.JSJ_KEY + cid));
    }

    /**
     * 加载核算小屋json
     *
     * @return
     * @author:
     * @date: 2022/11/28 15:26
     */
    @RequestMapping("/loadPositionJson")
    public AjaxResult loadPositionJson() {
        String line = "";
        StringBuffer sb = new StringBuffer();
        try {
            File jsonFile = new File(jsonPath);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;

            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            line = sb.toString().replaceAll("\n", "").replaceAll(" ", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success(line);
    }

    @RequestMapping("/getBase64ByUrl")
    public AjaxResult getBase64ByUrl(HttpServletRequest request) {
        String urls = request.getParameter("urls");
        JSONArray jsonArray = JSONArray.parseArray(urls);
        List<String> base64List = new ArrayList<>();
        String base64 = "";
        for (int i = 0; i < jsonArray.size(); i++) {
            String url = (String) jsonArray.get(i);
            if(StringUtils.isNotEmpty(url)&&url!=""&&url!="null") {
                base64 = ImageUtils.getBase64(url);
                base64List.add(base64);
            }
        }
        log.info(base64.toString());
        return success(base64List);
    }

    @RequestMapping("/getPngByUrl")
    public AjaxResult getPngByUrl(HttpServletRequest request) {
        String filePath = RuoYiConfig.getUploadPath();
        String urls = request.getParameter("urls");
        JSONArray jsonArray = JSONArray.parseArray(urls);
        List<String> imageUrlList = new ArrayList<>();
        String base64 = "";
        for (int i = 0; i < jsonArray.size(); i++) {
            String url = (String) jsonArray.get(i);

            if (StringUtils.isNotEmpty(url) && url != "") {
                if (redisCache.keyIsExists(url)) {
                    String imageUrl = redisCache.getCacheObject(url);
                    imageUrlList.add(imageUrl);
                    logger.info(imageUrl);
                } else {
                    logger.info(url);
                    String fileName = DateUtils.datePath() + url.substring(url.lastIndexOf("/"), url.lastIndexOf(".")) + ".png";
                    String targetPath = filePath + "/" + fileName;
                    try {
                        HttpUtil.downloadFile(url, targetPath);
                        String imageUrl = serverConfig.getUrl() + Constants.RESOURCE_PREFIX + "/upload/" + fileName;
                        redisCache.setCacheObject(url, imageUrl);
                        imageUrlList.add(imageUrl);
                        logger.info(imageUrl);
                    } catch (Exception ex) {
                        imageUrlList.add("");
                        ex.printStackTrace();
                    }
                }
            }
        }

        return success(imageUrlList);
    }

    /**
     * 生成小程序链接
     *
     * @return
     * @author:
     * @date: 2022/12/13 18:00
     */
    @RequestMapping("/getMiniUrl")
    public AjaxResult generateLink() {
        GenerateUrlLinkRequest request = GenerateUrlLinkRequest.builder()
                .isExpire(true)
                .expireTime(Convert.toInt(DateUtil.offset(new Date(), DateField.DAY_OF_YEAR, 180).getTime() / 1000))
                .build();
        String url = "";
        WxMaConfig wxMaConfig = wxMaService.getWxMaConfig();
        try {
            url = wxMaService.getLinkService().generateUrlLink(request);
        } catch (WxErrorException e) {
            logger.warn("生成小程序链接失败：{}", e);
            url = "";
        }
        return success(url);
    }

    @RequestMapping("/getMiniUrlByPath")
    public AjaxResult generateLinkByPath(String path) {
        GenerateUrlLinkRequest request = GenerateUrlLinkRequest.builder()
                .isExpire(true)
                .expireTime(Convert.toInt(DateUtil.offset(new Date(), DateField.DAY_OF_YEAR, 180).getTime() / 1000))
                .path(path)
                .build();
        String url = "";
        try {
            url = wxMaService.getLinkService().generateUrlLink(request);
        } catch (WxErrorException e) {
            logger.warn("生成小程序链接失败：{}", e);
            url = "";
        }
        return success(url);
    }

    /**
     * 城市运行事件列表接口
     *
     * @param request
     * @return
     * @author:
     * @date: 2022/11/21 11:29
     */
    @RequestMapping("/cityRunEventList")
    public AjaxResult cityRunEventList(HttpServletRequest request) {
        String mobile = request.getParameter("mobile");
        String page = request.getParameter("page");
        String size = request.getParameter("size");
        String title = request.getParameter("title");
        String sourceFrom = request.getParameter("sourceFrom");
        String deptId = request.getParameter("deptId");
        String eventStatus = request.getParameter("eventStatus");
        String deviceId = request.getParameter("deviceId");
        return success(sspService.cityRunEventList(mobile, page, size,title,sourceFrom,deptId,eventStatus,deviceId));
    }
    /**
     * 城市运行事件总览
     *
     * @param request
     * @return
     * @author:
     * @date: 2022/11/21 11:29
     */
    @RequestMapping("/cityRunEventOverView")
    public AjaxResult cityRunEventOverView(HttpServletRequest request) {
        String mobile = request.getParameter("mobile");
        return success(sspService.cityRunEventOverView(mobile));
    }

    /**
     * 城市运行-设备点位列表
     *
     * @param request
     * @return
     * @author:
     * @date: 2022/11/21 11:29
     */
    @RequestMapping("/cityRunDeviceList")
    public AjaxResult cityRunDeviceList(HttpServletRequest request) {
        String mobile = request.getParameter("mobile");
        return success(sspService.cityRunDeviceList(mobile));
    }
    /**
     * 城市运行-设备点位列表
     * @return
     * @author:
     * @date: 2022/11/21 11:29
     */
    @RequestMapping("/cityRunDeptList")
    public AjaxResult cityRunDeptList() {
        return success(sspService.cityRunDeptList());
    }
    /**
     * 城市运行-市民表扬
     *
     * @param request
     * @return
     * @author:
     * @date: 2022/11/21 11:29
     */
    @RequestMapping("/shiMinBiaoyangList")
    public AjaxResult ShiMinBiaoyangList(HttpServletRequest request) {
        String mobile = request.getParameter("mobile");
        String page = request.getParameter("page");
        String size = request.getParameter("size");
        return success(sspService.ShiMinBiaoyangList(mobile,page,size));
    }
    /**
     * 城市运行-系统表扬列表
     *
     * @param request
     * @return
     * @author:
     * @date: 2022/11/21 11:29
     */
    @RequestMapping("/systemBiaoyangList")
    public AjaxResult SystemBiaoyangList(HttpServletRequest request) {
        String mobile = request.getParameter("mobile");
        String page = request.getParameter("page");
        String size = request.getParameter("size");
        return success(sspService.SystemBiaoyangList(mobile,page,size));
    }
    /**
     * 城市运行-系统表扬列表
     *
     * @param request
     * @return
     * @author:
     * @date: 2022/11/21 11:29
     */
    @RequestMapping("/biaoyangStatistic")
    public AjaxResult BiaoyangStatistic(HttpServletRequest request) {
        String mobile = request.getParameter("mobile");
        return success(sspService.BiaoyangStatistic(mobile));
    }
    /**
     * 城市运行-系统表扬列表
     *
     * @param request
     * @return
     * @author:
     * @date: 2022/11/21 11:29
     */
    @RequestMapping("/biaoyangEventStatistic")
    public AjaxResult BiaoyangEventStatistic(HttpServletRequest request) {
        String mobile = request.getParameter("mobile");
        return success(sspService.BiaoyangEventStatistic(mobile));
    }
    /**
     * 事件分析-统计图表
     *
     * @param request
     * @return
     * @author:
     * @date: 2022/11/21 11:29
     */
    @RequestMapping("/EventStatisticEchart")
    public AjaxResult EventStatisticEchart(HttpServletRequest request) {
        String mobile = request.getParameter("mobile");
        return success(sspService.EventStatisticEchart(mobile));
    }

    /**
     * 环保事件列表
     */
    @GetMapping("/envProtectList")
    public AjaxResult envProtectList() {
        return success(sspService.envProtectList());
    }
    /**
     * 环保事件详情
     */
    @GetMapping("/envProtectDetail")
    public AjaxResult envProtectDetail(String eventNumber) {
        return success(sspService.envProtectDetail(eventNumber));
    }

    /**
     * 回应列表
     */
    @GetMapping("/publicEventList")
    public AjaxResult publicEventList(HttpServletRequest request) {
        String page = request.getParameter("page");
        String size = request.getParameter("size");
        return success(sspService.publicEventList(page,size));
    }

    /**
     * 事件获取抄送树
     */
    @GetMapping("/eventGetTree")
    public AjaxResult eventGetTree(HttpServletRequest request) {
        String mobile = request.getParameter("mobile");
        String deptIds = request.getParameter("deptIds");
        return success(sspService.eventGetTree(mobile,deptIds));
    }

    @PostMapping("/eventChaoSong")
    public AjaxResult eventChaoSong(HttpServletRequest request) {
        String mobile = request.getParameter("mobile");
        String eventId = request.getParameter("eventId");
        String eventInstanceId = request.getParameter("eventInstanceId");
        String chaoSongRen = request.getParameter("chaoSongRen");
        return success(sspService.eventChaoSong(mobile,eventId,eventInstanceId,chaoSongRen));
    }

    @GetMapping("/keywordSettingList")
    public AjaxResult keywordSettingList(HttpServletRequest request) {
        String mobile = request.getParameter("mobile");
        return success(sspService.keywordSettingList(mobile));
    }

    @RequestMapping(value = "/proxyWithoutAut/**",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONObject proxyWithoutAut(HttpServletRequest request,@RequestBody JSONObject jsonObject) throws URISyntaxException {
        return sspService.proxyWithoutAut(request,jsonObject);
    }

    @RequestMapping(value = "/proxyWithAut/**",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONObject proxyWithAut(HttpServletRequest request, @RequestBody Map<String,String> paramMap) throws URISyntaxException {
        return sspService.proxyWithAut(request,paramMap);
    }

    @RequestMapping(value = "/proxyWithAutNew/**",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONObject proxyWithAutNew(HttpServletRequest request, @RequestBody JSONObject jsonObject) throws URISyntaxException {
        return sspService.proxyWithAutNew(request,jsonObject);
    }

    @RequestMapping(value = "/proxy")
    public void proxy(String target, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //对包含中文等特殊字符的URL部分进行重新编码
        String encodedTarget = URLEncoder.DEFAULT.encode(target, StandardCharsets.UTF_8);
        log.info("encodedTarget:"+encodedTarget);

        URL targetUrl = new URL(encodedTarget);

        String methodName = request.getMethod();
        HttpMethod httpMethod = HttpMethod.resolve(methodName);
        if (httpMethod == null) {
            return;
        }

        HttpURLConnection conn = (HttpURLConnection) targetUrl.openConnection();
        conn.setRequestMethod(httpMethod.name());

        //设置请求头
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            Enumeration<String> headerValues = request.getHeaders(headerName);
            while (headerValues.hasMoreElements()) {
                conn.addRequestProperty(headerName, headerValues.nextElement());
            }
        }

        //发送请求体数据
        if (httpMethod != HttpMethod.GET) {
            conn.setDoOutput(true);
            try (OutputStream os = conn.getOutputStream()) {
                StreamUtils.copy(request.getInputStream(), os);
            }
        }

        //处理响应
        response.setStatus(conn.getResponseCode());
        for (Map.Entry<String, List<String>> entry : conn.getHeaderFields().entrySet()) {
            String headerName = entry.getKey();
            if (headerName != null) {
                for (String headerValue : entry.getValue()) {
                    response.addHeader(headerName, headerValue);
                }
            }
        }

        try (InputStream is = conn.getInputStream();
             OutputStream os = response.getOutputStream()) {
            StreamUtils.copy(is, os);
        }
    }

}
