package com.ruoyi.web.controller.wx.common;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.urllink.GenerateUrlLinkRequest;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: LJW
 * @Date: 2024/9/4 0004 14:25
 */
@Slf4j
@Component
public class WxService {

    @Autowired
    private WxMaService wxMaService;

    /**
     * 生成小程序链接
     */
    public String generateLinkByPath(String path) {
        GenerateUrlLinkRequest request = GenerateUrlLinkRequest.builder()
                .isExpire(true)
                .expireTime(Convert.toInt(DateUtil.offset(new Date(), DateField.DAY_OF_YEAR, 180).getTime() / 1000))
                .path(path)
                .build();
        String url = "";
        try {
            url = wxMaService.getLinkService().generateUrlLink(request);
        } catch (WxErrorException e) {
            log.warn("生成小程序链接失败：{}", e);
            url = "";
        }
        return url;
    }
}
