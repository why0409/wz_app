package com.ruoyi.web.controller.wz;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.system.domain.WxSmsLog;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.system.service.IWxSmsLogService;
import com.ruoyi.web.controller.wx.common.SmsMsgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;

@RestController
@RequestMapping("/sms")
@Api(tags = "短信")
public class SmsController {

    @Autowired
    private SmsMsgService smsMsgService;

    @Autowired
    private IWxSmsLogService iWxSmsLogService;

    @ApiOperation("发送短信")
    @GetMapping("/sendSms")
    public AjaxResult sendSms(String[] phonesList, String content) throws Exception {
        JSONObject jsonObject = smsMsgService.sendMsgByGroup(Arrays.asList(phonesList), content);
        if ("success".equals(jsonObject.get("rspcod"))) {
            // 日志记录
            WxSmsLog wxSmsLog = new WxSmsLog();
            wxSmsLog.setContents(content);
            wxSmsLog.setSendTime(new Date());
            int numTimes = iWxSmsLogService.getNumTimes();
            wxSmsLog.setFlag(numTimes + 1);
            for (String phone : phonesList) {
                wxSmsLog.setPhone(phone);
                wxSmsLog.setId(UUID.fastUUID().toString());
                iWxSmsLogService.save(wxSmsLog);
            }
            return AjaxResult.success(DateUtils.getTime() + "短信发送成功，发送用户：" + Arrays.toString(phonesList));
        } else {
            return AjaxResult.error("发送失败");
        }
    }

    @ApiOperation("获取发送次数")
    @GetMapping("/getNumTimes")
    public AjaxResult getNumTimes() {
        return AjaxResult.success(iWxSmsLogService.getNumTimes());
    }

}
