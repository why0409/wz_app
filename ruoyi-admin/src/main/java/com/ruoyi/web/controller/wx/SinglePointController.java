package com.ruoyi.web.controller.wx;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.utils.RsaUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.web.controller.wx.common.uccp.service.UccpService;
import com.ruoyi.web.controller.wx.common.uccp.vo.UserUnitDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 单点登录/统一认证控制器
 *
 * @Author tonyJiang
 * @Date 2024 01 15 17 14
 **/
@Slf4j
@RestController
@RequestMapping("/applet")
public class SinglePointController {

    @Autowired
    private UccpService uccpService;

    @RequestMapping(value = "/getUserInfo")
    public String getUserInfoByToken(String token) {
        // RSA加密后的字符串
        String secretStr = "";
        if (StringUtils.isNotEmpty(token)) {
            try {
                log.info("======根据token获取用户信息:token={} ========：", token);
                UserUnitDto userUnitDto = uccpService.getUserInfo(token);
                log.info("======成功获取用户信息{}========：", token);

                Map<String, Object> resultMap = new HashMap<>();
                if (userUnitDto != null) {
                    if (userUnitDto.getPerUserVo() != null) {
                        resultMap.put("perUserVo.name", userUnitDto.getPerUserVo().getName());
                        resultMap.put("perUserVo.bindPhone", userUnitDto.getPerUserVo().getBindPhone());
                    } else if (userUnitDto.getLegalUserVo() != null) {
                        resultMap.put("perUserVo.name", userUnitDto.getLegalUserVo().getName());
                        resultMap.put("perUserVo.bindPhone", userUnitDto.getLegalUserVo().getBindPhone());
                    }
                }

                if (!resultMap.isEmpty()) {
                    try {
                        // 公钥加密
                        secretStr = RsaUtils.encryptByPublicKey(JSON.toJSONString(resultMap));
                    } catch (Exception e) {
                        log.error("RSA加密用户信息失败: {}", e.getMessage(), e);
                    }
                }
            } catch (Exception e) {
                log.error("======失败获取用户信息{}========：", e.getMessage(), e);
            }
            return secretStr;
        } else {
            JSONObject error = new JSONObject();
            error.put("code", 500);
            error.put("msg", "token为空");
            error.put("data", null);
            return error.toJSONString();
        }
    }
}
