package com.ruoyi.quartz.task;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.system.domain.WxUserLogininfo;
import com.ruoyi.system.mapper.WxUserLogininfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Adminstrators
 */
@Component("WxLoginInfoTask")
public class WxLoginInfoTask {

    private static Logger logger = LoggerFactory.getLogger(WxLoginInfoTask.class);

    @Value("${jsj.syn.url}")
    private String ApiUrl;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private WxUserLogininfoMapper wxUserLogininfoMapper;

    /**
     * 用户登录记录入库
     */
    public void saveLoginInfo() {
        logger.info("用户登录记录入库定时任务开始执行=================");
        try {
            Set loginInfoSet = redisTemplate.opsForSet().members("WX_LOGIN_INFO");
            Iterator iterator = loginInfoSet.iterator();
            while (iterator.hasNext()) {
                String info = (String) iterator.next();
                Map<String,Object> loginInfoMap = JSONObject.parseObject(info);
                logger.info("========定时任务获取用户信息："+info);
                WxUserLogininfo logininfo = new WxUserLogininfo();
                String phone = (String) loginInfoMap.get("phone");

                if (phone.length() > 11) {
                    phone = phone.substring(0, 10);
                }

                List<WxUserLogininfo> list = wxUserLogininfoMapper.selectByPhone(phone);

                logger.info("####################list.size():"+list.size());
                if (list.size() > 0) {
                    // 不是第一次登录
                    logininfo.setPhone(phone);
                    logininfo.setLoginTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(loginInfoMap.get("login_time").toString()));
                    wxUserLogininfoMapper.updateLoginTimeByPhone(logininfo);
                }else {
                    // 第一次登录 直接入库
                    logininfo.setPhone(phone);
                    logininfo.setRegisterTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(loginInfoMap.get("login_time").toString()));
                    logininfo.setLoginTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(loginInfoMap.get("login_time").toString()));
                    wxUserLogininfoMapper.insertWxUserLogininfo(logininfo);
//                    // 同步用户信息给极视角
//                    try {
//                        logger.info("======================  开始同步用户信息-极视角  ======================");
//                        String resp = HttpUtil.post(ApiUrl, JSONObject.toJSONString(logininfo));
//                        logger.info("======================"+resp+ "======================");
//                        logger.info("======================  用户信息同步成功-极视角  ======================");
//                    } catch (Exception e) {
//                        logger.error("======================  用户信息同步失败-极视角  ======================");
//                        e.printStackTrace();
//                    }

                }
                // set 集合逐个删除
                redisTemplate.opsForSet().remove("WX_LOGIN_INFO", JSONObject.toJSONString(loginInfoMap));
            }
            logger.info("用户登录记录入库定时任务结束执行=================");
        } catch (Exception e) {
            logger.error("Redis连接异常，请确认Redis是否正常连接！");
            e.printStackTrace();
        }
    }

}
