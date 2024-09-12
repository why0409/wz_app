package com.ruoyi.web.controller.wx;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.app.domain.*;
import com.ruoyi.app.domain.vo.CszyCameraTypeVo;
import com.ruoyi.app.domain.vo.ServiceCategoryConfigVo;
import com.ruoyi.app.domain.vo.ServiceConfigVo;
import com.ruoyi.app.domain.vo.ServiceHomeInfo;
import com.ruoyi.app.service.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.WxNews;
import com.ruoyi.system.service.IWxNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: LJW
 * @Date: 2024/4/25 0025 17:54
 */
@RestController
@RequestMapping("/applet")
public class NewVersionController extends BaseController {

    @Autowired
    private IServiceCategoryConfigService serviceCategoryConfigService;

    @Autowired
    private IServiceConfigService serviceConfigService;

    @Autowired
    private IServiceHomeService serviceHomeService;

    @Autowired
    private IWxNewsService wxNewsService;

    @Autowired
    private IWxClickServiceLogService wxClickServiceLogService;

    @Autowired
    private IServiceBannerService serviceBannerService;

    @Autowired
    private IAlertSmsConfigService alertSmsConfigService;

    @Autowired
    private IHmwzBannerService hmwzBannerService;

    @Autowired
    private IHmwzCoverService hmwzCoverService;

    @Autowired
    private IHmwzVideoService hmwzVideoService;

    @Autowired
    private IHkxcEnterpriseListService hkxcEnterpriseListService;

    @Autowired
    private IHkxcEnterpriseTypeService hkxcEnterpriseTypeService;

    @Autowired
    private IHkxcIntroduceService hkxcIntroduceService;

    @Autowired
    private IHkxcPromotionalVideoService hkxcPromotionalVideoService;

    @Autowired
    private ICszyCameraTypeService cszyCameraTypeService;

    @Autowired
    private ICszyCameraListService cszyCameraListService;

    /**
     * 获取服务列表
     */
    @GetMapping("/getServiceConfigList")
    public AjaxResult getServiceConfigList(String type)
    {
        if ("市民服务".equals(type)) {
            ServiceCategoryConfig scc = new ServiceCategoryConfig();
            scc.setCategory(type);
            List<ServiceCategoryConfigVo> list = serviceCategoryConfigService.selectVoList(scc);
            for (ServiceCategoryConfigVo s : list) {
                ServiceConfig sc = new ServiceConfig();
                sc.setCategory(s.getCategory());
                sc.setSubCategory(s.getSubCategory());
                sc.setIsDeactivated(0L);

                s.setServiceList(serviceConfigService.selectServiceConfigList(sc));
            }

            return AjaxResult.success(list);
        }else {
            ServiceConfig sc = new ServiceConfig();
            sc.setCategory(type);
            sc.setIsDeactivated(0L);

            return AjaxResult.success(serviceConfigService.selectServiceConfigList(sc));
        }
    }

    /**
     * 根据id获取服务详情
     */
    @GetMapping("/getServiceConfigInfo/{id}")
    public AjaxResult getServiceConfigInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(serviceConfigService.selectServiceConfigById(id));
    }

    /**
     * 获取个人服务列表
     */
    @GetMapping("/getPersonalServiceList")
    public AjaxResult getPersonalServiceList()
    {
        ServiceConfig sc = new ServiceConfig();
        sc.setIsDeactivated(0L);
        sc.setCategory("市民服务");
        sc.setIsPersonal(1L);

        return AjaxResult.success(serviceConfigService.selectServiceConfigList(sc));
    }

    /**
     * 获取金刚区服务列表
     */
    @GetMapping("/getServiceHomeList")
    public AjaxResult getServiceHomeList(ServiceHome serviceHome)
    {
        List<ServiceHomeInfo> list = serviceHomeService.getServiceHomeInfoList(serviceHome);
        return AjaxResult.success(list);
    }

    /**
     * 获取轮播最新资讯、和美湾沚最新资讯
     */
    @GetMapping("/getCarouseNewsList")
    public AjaxResult getCarouseNewslList(WxNews wxNews)
    {
        List<WxNews> list = wxNewsService.selectWxNewsList(wxNews);
        return AjaxResult.success(list);
    }

    /**
     * 获取推荐服务列表
     */
    @GetMapping("/getServiceRecommendList")
    public AjaxResult getServiceRecommendList(ServiceConfig serviceConfig)
    {
        serviceConfig.setCategory("推荐关注");
        List<ServiceConfigVo> list = serviceConfigService.selectServiceConfigVoList(serviceConfig);
        return AjaxResult.success(list);
    }

    /**
     * 搜索服务、最新资讯
     */
    @GetMapping("/searchByContentAndPhone")
    public AjaxResult searchByContentAndPhone(String phone, String content)
    {
        JSONObject result = new JSONObject();
        // 查询服务
        result.put("serviceList", serviceConfigService.searchDescriptionList(phone, content));
        // 查询资讯
        result.put("wxNewsList", wxNewsService.searchWxNewsList(content));

        return AjaxResult.success(result);
    }

    /**
     * 根据openid获取最近使用小程序列表
     */
    @GetMapping("/getRecentlyUsedListByOpenid")
    public AjaxResult getRecentlyUsedListByOpenid(String openid)
    {
        return AjaxResult.success(serviceConfigService.getRecentlyUsedListByOpenid(openid));
    }

    /**
     * 插入小程序各服务使用记录
     */
    @PostMapping("/addWxClickServiceLog")
    public AjaxResult addWxClickServiceLog(@RequestBody WxClickServiceLog wxClickServiceLog)
    {
        if (StringUtils.isEmpty(wxClickServiceLog.getServiceId())) {
            return AjaxResult.error("服务uuid不能为空");
        } else {
            //查询服务uuid
            String serviceId = wxClickServiceLog.getServiceId();
            ServiceConfig sc = serviceConfigService.selectServiceConfigById(Long.valueOf(serviceId));
            String serviceUuid = sc.getUuid();

            //uuid记录服务日志
            wxClickServiceLog.setServiceId(serviceUuid);
            wxClickServiceLogService.insertWxClickServiceLog(wxClickServiceLog);
            return AjaxResult.success();
        }
    }

    /**
     * 根据手机号查询权限（新）
     */
    //@LoginInfo(description = "微信用户登录获取权限（新）")
    @GetMapping("getMenuByPhoneNew")
    public AjaxResult getMenuByPhoneNew(String phone) {
        ////权限白名单
        //List<String> permissionList = Arrays.asList("15979096269");
        //if (permissionList.contains(phone)) {
        //    ServiceConfig sc = new ServiceConfig();
        //    sc.setCategory("政府服务");
        //    sc.setIsDeactivated(0L);
        //
        //    return AjaxResult.success(serviceConfigService.selectServiceConfigList(sc));
        //}

        return AjaxResult.success(serviceConfigService.getMenuByPhoneNew(phone));
    }

    /**
     * 服务banner图轮播列表
     */
    @GetMapping("/getServiceBannerList")
    public AjaxResult getServiceBannerList(ServiceBanner serviceBanner)
    {
        List<ServiceBanner> list = serviceBannerService.selectServiceBannerList(serviceBanner);
        return AjaxResult.success(list);
    }

    /**
     * 预警短信配置列表
     */
    @GetMapping("/getAlertSmsConfigList")
    public AjaxResult getAlertSmsConfigList(AlertSmsConfig alertSmsConfig)
    {
        List<AlertSmsConfig> list = alertSmsConfigService.selectAlertSmsConfigList(alertSmsConfig);
        return AjaxResult.success(list);
    }

    /**
     * 和美湾沚-banner管理列表
     */
    @GetMapping("/getHmwzBannerList")
    public AjaxResult getHmwzBannerList(HmwzBanner hmwzBanner)
    {
        List<HmwzBanner> list = hmwzBannerService.selectHmwzBannerList(hmwzBanner);
        return AjaxResult.success(list);
    }

    @GetMapping(value = "/getHmwzBannerInfo/{id}")
    public AjaxResult getHmwzBannerInfo(@PathVariable("id") Long id)
    {
        return success(hmwzBannerService.selectHmwzBannerById(id));
    }

    /**
     * 和美湾沚-封面管理列表
     */
    @GetMapping("/getHmwzCoverList")
    public AjaxResult getHmwzCoverList(HmwzCover hmwzCover)
    {
        List<HmwzCover> list = hmwzCoverService.selectHmwzCoverList(hmwzCover);
        return AjaxResult.success(list);
    }

    /**
     * 和美湾沚-视频管理列表
     */
    @GetMapping("/getHmwzVideoList")
    public TableDataInfo getHmwzVideoList(HmwzVideo hmwzVideo)
    {
        startPage();
        List<HmwzVideo> list = hmwzVideoService.selectHmwzVideoList(hmwzVideo);
        return getDataTable(list);
    }

    /**
     * 航空新城-企业列表
     */
    @GetMapping("/getHkxcEnterpriseList")
    public AjaxResult getHkxcEnterpriseList(HkxcEnterpriseList hkxcEnterpriseList)
    {
        List<HkxcEnterpriseList> list = hkxcEnterpriseListService.selectHkxcEnterpriseListList(hkxcEnterpriseList);
        return AjaxResult.success(list);
    }

    /**
     * 航空新城-企业详情
     */
    @GetMapping(value = "/getHkxcEnterprise/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(hkxcEnterpriseListService.selectHkxcEnterpriseListById(id));
    }

    /**
     * 航空新城-企业分类列表
     */
    @GetMapping("/getHkxcEnterpriseTypeList")
    public AjaxResult getHkxcEnterpriseTypeList(HkxcEnterpriseType hkxcEnterpriseType)
    {
        List<HkxcEnterpriseType> list = hkxcEnterpriseTypeService.selectHkxcEnterpriseTypeList(hkxcEnterpriseType);
        return AjaxResult.success(list);
    }

    /**
     * 航空新城-介绍管理列表
     */
    @GetMapping("/getHkxcIntroduceList")
    public AjaxResult getHkxcIntroduceList(HkxcIntroduce hkxcIntroduce)
    {
        List<HkxcIntroduce> list = hkxcIntroduceService.selectHkxcIntroduceList(hkxcIntroduce);
        return AjaxResult.success(list);
    }

    /**
     * 航空新城-介绍管理根据id获取详情
     */
    @GetMapping(value = "/getHkxcIntroduce/{id}")
    public AjaxResult getHkxcIntroduce(@PathVariable("id") Long id)
    {
        return AjaxResult.success(hkxcIntroduceService.selectHkxcIntroduceById(id));
    }

    /**
     * 航空新城-宣传片列表
     */
    @GetMapping("/getHkxcPromotionalVideoList")
    public AjaxResult getHkxcPromotionalVideoList(HkxcPromotionalVideo hkxcPromotionalVideo)
    {
        List<HkxcPromotionalVideo> list = hkxcPromotionalVideoService.selectHkxcPromotionalVideoList(hkxcPromotionalVideo);
        return AjaxResult.success(list);
    }

    /**
     * 城市之眼-权限
     */
    @GetMapping("/getPermissionsByPhone")
    public TableDataInfo getPermissionsByPhone(String phone)
    {
        startPage();
        List<CszyCameraTypeVo> list = cszyCameraTypeService.getPermissionsByPhone(phone);
        return getDataTable(list);
    }

    /**
     * 城市之眼-视频列表
     */
    @GetMapping("/getCszyCameraList")
    public TableDataInfo getCszyCameraList(CszyCameraList cszyCameraList)
    {
        startPage();
        List<CszyCameraList> list = cszyCameraListService.selectCszyCameraListList(cszyCameraList);
        return getDataTable(list);
    }

}
