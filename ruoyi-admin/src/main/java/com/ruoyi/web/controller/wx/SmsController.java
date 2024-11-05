package com.ruoyi.web.controller.wx;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.web.controller.wx.common.SmsMsgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/applet/sms")
@Api(tags = "短信")
public class SmsController {

    @Autowired
    private SmsMsgService smsMsgService;

    @ApiOperation("发送短信")
    @GetMapping("/sendSms")
    public AjaxResult sendSms(String[] phonesList, String content) throws Exception {
        JSONObject jsonObject = smsMsgService.sendMsgByGroup(Arrays.asList(phonesList), content);
        if ("success".equals(jsonObject.get("rspcod"))) {
            return AjaxResult.success(DateUtils.getTime() + "短信发送成功，发送用户：" + Arrays.toString(phonesList));
        } else {
            return AjaxResult.error("发送失败");
        }
    }
}
