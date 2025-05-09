package com.ruoyi.web.controller.wx;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.applet.UserInfoService;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.AESEncryptorUtils;
import com.ruoyi.common.utils.RsaUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.sign.Md5Utils;
import com.ruoyi.framework.config.ServerConfig;
import com.ruoyi.hikvision.service.HikvisionService;
import com.ruoyi.system.domain.WxClickmoduleInfo;
import com.ruoyi.system.domain.WxUserLogininfo;
import com.ruoyi.system.service.ISysMenuService;
import com.ruoyi.system.service.IWxClickmoduleInfoService;
import com.ruoyi.system.service.IWxUserLogininfoService;
import com.ruoyi.system.service.IWxUserMenuService;
import com.ruoyi.web.annotation.ClickLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 小程序----用户相关接口
 */
@RestController
@RequestMapping("/applet")
@Api(tags = "小程序----用户相关接口")
public class UserInfoController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private IWxUserMenuService wxUserMenuService;

    @Autowired
    private HikvisionService hikvisionService;

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private IWxUserLogininfoService wxUserLogininfoService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IWxClickmoduleInfoService wxClickmoduleInfoService;

    @Autowired
    private ISysMenuService menuService;

    @Value("${jsj.syn.url}")
    private String ApiUrl;

    @Value("${jsj.host}")
    private String host;

    @Value("${jsj.port}")
    private String port;

    public static final long IMG_MAX_SIZE = 5 * 1024 * 1024;

    public static final long VIDEO_MAX_SIZE = 1024 * 1024 * 1024;

    /**
     * 获取sessionID
     *
     * @param
     */
    @ClickLog(description = "用户进入小程序")
    @RequestMapping("/getOpenId")
    public AjaxResult getOpenId(HttpServletRequest request) {
        String code = request.getParameter("code");

        if (StringUtils.isEmpty(code)) {
            log.error("code参数为空");
            return error("code参数为空");
        }

        // 获取openId
        JSONObject jsonObject;
        try {
            jsonObject = userInfoService.getOpenId(code);
        } catch (Exception e) {
            e.printStackTrace();
            return error("获取微信小程序OpenId失败");
        }

        // 存入redis
        try {
            redisTemplate.opsForSet().add("VALIDATE_KEYS", Md5Utils.encryptDES(code, Constants.DEFAULT_DES_KEY));
        } catch (Exception e) {
            log.error("Redis连接异常，请确认Redis是否正常连接！\n" + e.getMessage());
            e.printStackTrace();
        }

        return success(jsonObject);
    }

    @RequestMapping("/decrypt")
    public AjaxResult decrypt(HttpServletRequest request) throws Exception {
        // 获得encryptedData
        String encryptedData = request.getParameter("encryptedData");
        if (StringUtils.isEmpty(encryptedData)) {
            log.error("encryptedData参数为空");
            return error("encryptedData参数为空");
        }

        // 获得ivParameter
        String ivParameter = request.getParameter("iv");
        if (StringUtils.isEmpty(ivParameter)) {
            log.error("ivParameter参数为空");
            return error("ivParameter参数为空");
        }

        // 获得加密后的sessionKey
        String sessionKey = request.getParameter("sessionKey");
        if (StringUtils.isEmpty(sessionKey)) {
            log.error("sessionKey参数为空");
            return error("sessionKey参数为空");
        }

        // 解密sessionKey
        sessionKey = RsaUtils.decryptByPublicKey(sessionKey);

        // 解密用户信息
        String result = userInfoService.decrypt(encryptedData, sessionKey, ivParameter);
        log.info("解密用户信息：" + result);

//        return success(AESEncryptorUtils.encrypt(result));
        return success(Md5Utils.encryptDES(result, Constants.DEFAULT_DES_KEY));
    }

    /**
     * 根据手机号查询权限
     */
    @RequestMapping("getMenuByPhone")
    public TableDataInfo getMenuByPhone(@Param("phone") String phone) {
        List<Object> list = wxUserMenuService.getMenuByPhoneOld(phone);
        return getDataTable(list);
    }

    /**
     * 海康视频获取播放的url
     *
     * @param
     * @return
     * @author:
     * @date: 2022/11/22 15:59
     */
    @RequestMapping("/getListFromFront")
    public AjaxResult getListFromFront() {
        return AjaxResult.success(hikvisionService.getListFromFront());
    }

    /**
     * 从内网推海康视频信息至前置机
     *
     * @param map
     * @date: 2022/12/9 10:31
     */
    @RequestMapping("/SendHikInfo")
    public void receiveFromZD(@RequestBody Map<String, Object> map) {
        hikvisionService.receiveFromZD(map);
    }

    /**
     * 文件删除
     *
     * @param jsonObject
     */
    @RequestMapping("/deleteFile")
    public void deleteFile(@RequestBody JSONObject jsonObject) {
        try {
            String fileName = jsonObject.getString("fileName");
            fileName = Md5Utils.encryptDES(fileName, Constants.DEFAULT_DES_KEY);
            fileName = fileName.replace(Constants.RESOURCE_PREFIX + "/upload", "");
            String filePath = RuoYiConfig.getUploadPath() + fileName;
            FileUtils.deleteFile(filePath);
        } catch (Exception e) {
            log.error("文件删除失败", e);
        }
    }

    /**
     * 通用上传请求（单个）
     */
    @PostMapping("/upload")
    public AjaxResult uploadFile(MultipartFile file) {
        try {
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            String[] strs = file.getOriginalFilename().split("\\.");

            if (!Arrays.asList("jpg", "png", "jpeg", "mp4").contains(strs[1])) {
                return error("不支持的文件类型");
            }

            if (strs[1].equals("mp4")) {
                if (file.getSize() > VIDEO_MAX_SIZE) {
                    return error("视频大小不能超过1GB");
                }
            } else {
                if (file.getSize() > IMG_MAX_SIZE) {
                    return error("图片大小不能超过5M");
                }
            }
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("url", url);
            ajax.put("fileName", fileName);
            ajax.put("newFileName", FileUtils.getName(fileName));
            ajax.put("originalFilename", file.getOriginalFilename());
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 修改个人中心信息
     */
    @RequestMapping("/updatePerCen")
    public AjaxResult updatePerCen(@RequestBody WxUserLogininfo wxUserLogininfo) {
        try {
            logger.info("更新用户信息：" + JSONObject.toJSONString(wxUserLogininfo));
            // 同步用户信息给极视角
            String resp = HttpUtil.post(ApiUrl, JSONObject.toJSONString(wxUserLogininfo));

            int count = wxUserLogininfoService.getCountByPhone(wxUserLogininfo.getPhone());
            if (count > 0) {
                // 更新本地库
                wxUserLogininfoService.updateWxUserLogininfo(wxUserLogininfo);
            } else {
                // 插入本地库
                wxUserLogininfo.setRegisterTime(new Date());
                wxUserLogininfo.setLoginTime(new Date());
                wxUserLogininfoService.insertWxUserLogininfo(wxUserLogininfo);
            }

            logger.info("============= 更新用户信息成功 =============");
            return AjaxResult.success();
        } catch (Exception e) {
            logger.error("============= 更新用户信息失败 =============\n" + e.getMessage());
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 根据手机号查询个人中心信息
     */
    @RequestMapping("/getPerCen")
    public AjaxResult getPerCen(@Param("phone") String phone) {
        String resp = "";
        try {
            String requst_url = "http://" + host + ":" + 8200 + "/api/open/wxUser/detail?phone=" + phone;
            resp = HttpRequest.get(requst_url).execute().body();
        } catch (Exception e) {
            log.error("=============== 查询微信用户失败 ===============\n" + e.getMessage());
            e.printStackTrace();
        }
        return AjaxResult.success(JSON.parseObject(resp));
//        return AjaxResult.success(wxUserLogininfoService.selectWxUserLogininfoByPhone(phone));
    }

    @RequestMapping("/setWxOnLine")
    public AjaxResult setWxOnLine(@RequestParam("phone") String phone) {
        if (StringUtils.isEmpty(phone)) {
            return error("phone参数为空");
        }

        WxUserLogininfo wxUserLogininfo = new WxUserLogininfo();
        wxUserLogininfo.setPhone(phone);
        wxUserLogininfo.setOnline("1");
        wxUserLogininfo.setLoginTime(new Date());

        int count = wxUserLogininfoService.getCountByPhone(phone);

        // 上线用户存入redis
        // SetOperations setOperations = redisTemplate.opsForSet();
        // setOperations.add("wxOnline", phone);

        // 不是第一次登录
        if (count > 0) {
            wxUserLogininfoService.updateWxUserLogininfo(wxUserLogininfo);
            logger.info(phone + "微信用户已上线");

            return AjaxResult.success();
        }
        // 第一次登录
        else {
            wxUserLogininfo.setRegisterTime(new Date());

            try {
                logger.info("新用户信息：" + JSONObject.toJSONString(wxUserLogininfo));
                // 同步用户信息给极视角
                String resp = HttpUtil.post(ApiUrl, JSONObject.toJSONString(wxUserLogininfo));

                // 插入本地库
                wxUserLogininfoService.insertWxUserLogininfo(wxUserLogininfo);

                logger.info("============= 用户信息新增成功 =============");
                logger.info(phone + "微信用户已上线");
                return AjaxResult.success();
            } catch (Exception e) {
                logger.error("============= 用户信息新增失败 =============\n" + e.getMessage());
                e.printStackTrace();
                return AjaxResult.error(e.getMessage());
            }
        }
    }

    @PostMapping("/isOnline")
    @ApiOperation("isOnline")
    public AjaxResult isOnline(@RequestBody Map<String, String> map) {
        // 解析签名
        String string;
        try {
            String sign = map.get("sign");
            string = RsaUtils.decryptByPublicKey(sign);
        } catch (Exception e) {
            return AjaxResult.error("解析参数失败");
        }
        JSONObject jsonObject = JSONObject.parseObject(string);
        String appId = jsonObject.getString("appId");
        if (StringUtils.isEmpty(appId)) {
            return AjaxResult.error("appId不能为空");
        }
        if (!"wz_app".equals(appId)) {
            return AjaxResult.error("appId不正确");
        }
        String secret = jsonObject.getString("secret");
        if (StringUtils.isEmpty(secret)) {
            return AjaxResult.error("secret不能为空");
        }
        if (!"QpeHjk6HJA7ZVKpyN".equals(secret)) {
            return AjaxResult.error("secret不正确");
        }
        String phone = jsonObject.getString("phone");
        if (StringUtils.isEmpty(phone)) {
            return AjaxResult.error("手机号不能为空");
        }
        WxUserLogininfo wxUserLogininfo = wxUserLogininfoService.selectWxUserLogininfoByPhone(phone);
        if (wxUserLogininfo != null && "1".equals(wxUserLogininfo.getOnline())) {
            return AjaxResult.success("true");
        }
        return AjaxResult.success("false");
    }

    public static void main(String[] args) throws Exception {
//         OcKINC80NZ49mgyZUWjWmoJBrumZ2LM3fGapSygikT7dC08fGlCbUr80r70AXV3mnfZI0jJJ+09tarSgwlVT41k+Qmht2pAtoIpF/YLor2mNLJXf6kYjdfJPFTfMKhEB9IqPtEstfliT27U7FN6922eNwxZR262JXjL/OcqByCE=
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appId", "wz_app");
        jsonObject.put("secret", "QpeHjk6HJA7ZVKpyN");
        jsonObject.put("phone", "19156379157");
        String sign = RsaUtils.encryptByPrivateKey(jsonObject.toJSONString());
        System.out.println(sign);

        String string = RsaUtils.decryptByPublicKey(sign);
        System.out.println(string);
    }

    @RequestMapping("/removeWxOnLine")
    public AjaxResult removeWxOnLine(@RequestParam("phone") String phone) {
        try {
            // redisTemplate.opsForSet().remove("wxOnline", phone);

            WxUserLogininfo wxUserLogininfo = new WxUserLogininfo();
            wxUserLogininfo.setPhone(phone);
            wxUserLogininfo.setOnline("0");
            wxUserLogininfoService.updateWxUserLogininfo(wxUserLogininfo);
            logger.info(phone + "微信用户已下线");

            // Set<String> wxOnlineSet = redisTemplate.opsForSet().members("wxOnline");
            // wxUserLogininfoService.updateOfflineStatus(wxOnlineSet);

            return AjaxResult.success();
        } catch (Exception exception) {
            exception.printStackTrace();
            return AjaxResult.error(exception.getMessage());
        }
    }

    @RequestMapping(value = "/clickmoduleInfo", method = RequestMethod.POST)
    public AjaxResult clickmoduleInfo(WxClickmoduleInfo wxClickmoduleInfo) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            wxClickmoduleInfo.setClickTime(new Date());
            int i = wxClickmoduleInfoService.insertWxClickmoduleInfo(wxClickmoduleInfo);
            return AjaxResult.success();
        } catch (Exception ex) {
            ex.printStackTrace();
            return AjaxResult.error();
        }
    }

    @GetMapping("/menu/treeselectByAdmin")
    public AjaxResult treeselectByAdmin(SysMenu menu) {
        List<SysMenu> menus = menuService.selectMenuList(menu, 1L);
        return success(menuService.buildMenuTreeSelect(menus));
    }

}
