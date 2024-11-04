package com.ruoyi.quartz.task;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.activities.domain.HomestayRegisteredInfo;
import com.ruoyi.activities.service.IActivitiesInfoService;
import com.ruoyi.activities.service.IHomestayRegisteredInfoService;
import com.ruoyi.common.utils.SmsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LJW
 * @Date: 2024/6/21 0021 16:25
 */

@Slf4j
@Component("HomestayActivitiesTask")
public class HomestayActivitiesTask {

    @Autowired
    private IHomestayRegisteredInfoService homestayRegisteredInfoService;

    @Autowired
    private IActivitiesInfoService activitiesInfoService;

    @Value("${zhwz.url}")
    private String url;

    @Value("${zhwz.secretKey}")
    private String secretKey;

    @Value("${zhwz.apId}")
    private String apId;

    @Value("${zhwz.sign}")
    private String sign;

    @Value("${zhwz.ecName}")
    private String ecName;

    public void sendWinSmsMsg(Long activitiesId) throws Exception {
        log.info(DateUtil.now()+"----发送中奖短信----");

        //ActivitiesInfo activitiesInfo = activitiesInfoService.selectNormalActivitiesInfo("0");
        //Long activitiesId = activitiesInfo.getId();

        HomestayRegisteredInfo hri = new HomestayRegisteredInfo();
        hri.setIsWin("1");
        hri.setActivitiesId(activitiesId);
        List<HomestayRegisteredInfo> list = homestayRegisteredInfoService.selectHomestayRegisteredInfoList(hri);

        // 获取所有中奖用户的手机号
        List<String> phonesList = new ArrayList<>();
        for (HomestayRegisteredInfo homestayRegisteredInfo : list) {
            phonesList.add(homestayRegisteredInfo.getContactPhone());
        }

        ////调试
        //List<String> phonesList = Arrays.asList("15979096269","15656937512");

        String content = "\"湾沚区文旅惠民精品民宿体验季2024年10-12月\"摇号活动已结束，请您前往智慧湾沚小程序\"民宿体验劵\"查看中奖详情！";
        String base64 = SmsUtils.getBase64(content, String.join(",", phonesList), ecName, apId, secretKey, sign);
        String msg = SmsUtils.sendMsg(base64, url);

        JSONObject object = JSONObject.parse(msg);
        if ("success".equals(object.get("rspcod"))) {
            log.info("中奖短信发送成功");
        } else {
            log.info("中奖短信发送失败");
        }
    }

}
