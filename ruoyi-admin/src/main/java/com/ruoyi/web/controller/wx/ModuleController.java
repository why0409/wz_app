package com.ruoyi.web.controller.wx;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.gisDb.domain.WzBenefitEnterprise;
import com.ruoyi.gisDb.mapper.WzBenefitEnterpriseMapper;
import com.ruoyi.gisDb.service.IWzBenefitEnterpriseService;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.*;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 小程序----各模块接口
 */
@RestController
@RequestMapping("/applet")
public class ModuleController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(ModuleController.class);

    @Autowired
    private IWxZsyzTableService wxZsyzTableService;

    @Autowired
    private IWxNewsService wxNewsService;

    @Autowired
    private IWxQzryTableService wxQzryTableService;

    @Autowired
    private IWxZpqyTableService wxZpqyTableService;

    @Autowired
    private IWxPictureAppreciateService wxPictureAppreciateService;

    @Autowired
    private IWxPictureNewsService wxPictureNewsService;

    @Autowired
    private IWxDeptPhoneService wxDeptPhoneService;

    @Autowired
    private IWxInvestmentPromotionService wxInvestmentPromotionService;

    @Autowired
    private IWxPositiveEnergyService wxPositiveEnergyService;

    @Autowired
    private IWxTalentPolicyService wxTalentPolicyService;

    @Autowired
    private IWxWzWeatherService wxWzWeatherService;

    @Autowired
    private IWzBenefitEnterpriseService wzBenefitEnterpriseService;

    @Autowired
    private WzBenefitEnterpriseMapper wzBenefitEnterpriseMapper;

    @Autowired
    private IWxConvenienceServiceService wxConvenienceServiceService;

    @Autowired
    private IWxServePeopleService wxServePeopleService;

    @Autowired
    private IWxHealthyService wxHealthyService;

    @Autowired
    private IWxMarathonService wxMarathonService;

    @Autowired
    private IWxTitleConfigService wxTitleConfigService;

    @Autowired
    private IWxCulturalTourismService wxCulturalTourismService;

    @Autowired
    private IWxMiniProgramsService wxMiniProgramsService;

    @Autowired
    private IWzMorningService wzMorningService;

    @Autowired
    private ITempleFairInfoService templeFairInfoService;

    /**
     * 最新动态列表
     */
    @GetMapping("/getLatestFive")
    public TableDataInfo list(WxNews wxNews)
    {
        startPage();
        List<WxNews> list = wxNewsService.selectWxNewsList(wxNews);
        return getDataTable(list);
    }


    /**
     * 最新动态详情
     */
    @GetMapping(value = "/news/{uuid}")
    public AjaxResult getLatestFiveInfo(@PathVariable("uuid") String uuid)
    {
        return success(wxNewsService.selectWxNewsByUuid(uuid));
    }

    /**
     * 新增招商引资
     */
    @RequestMapping("/addWxZsyz")
    public AjaxResult addWxZsyz(@RequestBody WxZsyzTable wxZsyzTable) {
        return toAjax(wxZsyzTableService.insertWxZsyzTable(wxZsyzTable));
    }

    /**
     * 新增求职人员信息
     */
    @RequestMapping("/addQZInfo")
    public AjaxResult addQZInfo(@RequestBody WxQzryTable wxQzryTable) {
        return toAjax(wxQzryTableService.insertWxQzryTable(wxQzryTable));
    }

    /**
     * 新增招聘企业信息
     */
    @RequestMapping("/addQYInfo")
    public AjaxResult addQYInfo(@RequestBody WxZpqyTable wxZpqyTable) {
        return toAjax(wxZpqyTableService.insertWxZpqyTable(wxZpqyTable));
    }

    /**
     * 图片欣赏列表
     */
    @GetMapping("/pictureAppreciate/list")
    public TableDataInfo list(WxPictureAppreciate wxPictureAppreciate)
    {
        startPage();
        List<WxPictureAppreciate> list = wxPictureAppreciateService.selectWxPictureAppreciateList(wxPictureAppreciate);
        return getDataTable(list);
    }

    /**
     * 图片欣赏详情
     */
    @GetMapping(value = "/pictureAppreciate/{uuid}")
    public AjaxResult getPictureAppreciateInfo(@PathVariable("uuid") String uuid)
    {
        return AjaxResult.success(wxPictureAppreciateService.selectWxPictureAppreciateByUuid(uuid));
    }

    /**
     * 图片新闻列表
     */
    @GetMapping("/pictureNews/list")
    public TableDataInfo list(WxPictureNews wxPictureNews)
    {
        startPage();
        List<WxPictureNews> list = wxPictureNewsService.selectWxPictureNewsList(wxPictureNews);
        return getDataTable(list);
    }

    /**
     * 图片新闻详情
     */
    @GetMapping("/pictureNews/{uuid}")
    public AjaxResult getPictureNewsInfo(@PathVariable("uuid") String uuid)
    {
        return AjaxResult.success(wxPictureNewsService.selectWxPictureNewsByUuid(uuid));
    }


    /**
     * 部门电话列表
     */
    @GetMapping("/phone/list")
    public TableDataInfo list(WxDeptPhone wxDeptPhone)
    {
        startPage();
        List<WxDeptPhone> list = wxDeptPhoneService.selectWxDeptPhoneList(wxDeptPhone);
        return getDataTable(list);
    }

    /**
     * 查询招商宣传列表
     */
    @GetMapping("/investmentPromotion/list")
    public TableDataInfo list(WxInvestmentPromotion wxInvestmentPromotion)
    {
        startPage();
        List<WxInvestmentPromotion> list = wxInvestmentPromotionService.selectWxInvestmentPromotionList(wxInvestmentPromotion);
        return getDataTable(list);
    }

    /**
     * 获取招商宣传详细信息
     */
    @GetMapping(value = "/investmentPromotion/{uuid}")
    public AjaxResult getInvestmentPromotionInfo(@PathVariable("uuid") String uuid)
    {
        return success(wxInvestmentPromotionService.selectWxInvestmentPromotionByUuid(uuid));
    }

    /**
     * 查询湾沚正能量列表
     */
    @GetMapping("/positiveEnergy/list")
    public TableDataInfo list(WxPositiveEnergy wxPositiveEnergy)
    {
        startPage();
        List<WxPositiveEnergy> list = wxPositiveEnergyService.selectWxPositiveEnergyList(wxPositiveEnergy);
        return getDataTable(list);
    }

    /**
     * 获取湾沚正能量详细信息
     */
    @GetMapping(value = "/positiveEnergy/{uuid}")
    public AjaxResult getPositiveEnergyInfo(@PathVariable("uuid") String uuid)
    {
        return success(wxPositiveEnergyService.selectWxPositiveEnergyByUuid(uuid));
    }

    /**
     * 查询人才政策列表
     */
    @GetMapping("/talentPolicy/list")
    public TableDataInfo list(WxTalentPolicy wxTalentPolicy)
    {
        startPage();
        List<WxTalentPolicy> list = wxTalentPolicyService.selectWxTalentPolicyList(wxTalentPolicy);
        return getDataTable(list);
    }

    /**
     * 获取人才政策详细信息
     */
    @GetMapping(value = "/talentPolicy/{uuid}")
    public AjaxResult getWxTalentPolicyInfo(@PathVariable("uuid") String uuid)
    {
        return success(wxTalentPolicyService.selectWxTalentPolicyByUuid(uuid));
    }

    /**
     * 查询湾沚天气列表
     */
    @GetMapping("/wzWeather/list")
    public TableDataInfo list(WxWzWeather wxWzWeather)
    {
        startPage();
        List<WxWzWeather> list = wxWzWeatherService.selectWxWzWeatherList(wxWzWeather);
        return getDataTable(list);
    }

    /**
     * 获取湾沚天气详细信息
     */
    @GetMapping(value = "/wzWeather/{uuid}")
    public AjaxResult getWxWzWeatherInfo(@PathVariable("uuid") String uuid)
    {
        return success(wxWzWeatherService.selectWxWzWeatherByUuid(uuid));
    }

    /**
     * 查询湾沚航空马拉松列表
     */
    @GetMapping("/marathon/list")
    public TableDataInfo list(WxMarathon wxMarathon)
    {
        startPage();
        List<WxMarathon> list = wxMarathonService.selectWxMarathonList(wxMarathon);
        return getDataTable(list);
    }

    @GetMapping("benefitPolicyStatics")
    @ApiOperation("惠企政策统计")
    public AjaxResult statics() {
        List<Map<String, Object>> list = new ArrayList<>();
        WzBenefitEnterprise benefitEnterprise = wzBenefitEnterpriseMapper.selectOne();

        for (int i = 0; i < 2; i++) {
            Map<String, Object> map = new HashMap<>();
            if (i == 0){
                map.put("type","企业");
                map.put("amount",benefitEnterprise.getPayAmountEnterprise());
                map.put("frequency",benefitEnterprise.getPayFrequencyEnterprise());
            }else if (i == 1){
                map.put("type","个人");
                map.put("amount",benefitEnterprise.getPayAmountPerson());
                map.put("frequency",benefitEnterprise.getPayFrequencyPerson());
            }
            list.add(map);
        }
        return success(list);
    }

    @GetMapping("/benefitPolicy/list")
    public TableDataInfo list(WzBenefitEnterprise wzBenefitEnterprise)
    {
        startPage();
        List<WzBenefitEnterprise> list = wzBenefitEnterpriseService.selectWzBenefitEnterpriseList(wzBenefitEnterprise);
        return getDataTable(list);
    }

    /**
     * 查询为民服务列表
     */
    @GetMapping("/serve/list")
    public TableDataInfo list(WxServePeople wxServePeople)
    {
        startPage();
        List<WxServePeople> list = wxServePeopleService.selectWxServePeopleList(wxServePeople);
        return getDataTable(list);
    }

    /**
     * 获取为民服务详细信息
     */
    @GetMapping(value = "/serve/{uuid}")
    public AjaxResult getServeInfo(@PathVariable("uuid") String uuid)
    {
        return success(wxServePeopleService.selectWxServePeopleByUuid(uuid));
    }

    /**
     * 查询便民服务列表
     */
    @GetMapping("/service/list")
    public TableDataInfo list(WxConvenienceService wxConvenienceService)
    {
        startPage();
        List<WxConvenienceService> list = wxConvenienceServiceService.selectWxConvenienceServiceList(wxConvenienceService);
        return getDataTable(list);
    }

    /**
     * 获取便民服务详细信息
     */
    @GetMapping(value = "/service/{uuid}")
    public AjaxResult getInfo(@PathVariable("uuid") String uuid)
    {
        return success(wxConvenienceServiceService.selectWxConvenienceServiceByUuid(uuid));
    }

    @GetMapping("/titleConfig/list")
    public TableDataInfo list(WxTitleConfig wxTitleConfig)
    {
        startPage();
        List<WxTitleConfig> list = wxTitleConfigService.selectWxTitleConfigList(wxTitleConfig);
        return getDataTable(list);
    }


    /**
     * 查询健康板块列表
     */
    @GetMapping("/wxHealthy/list")
    public TableDataInfo wxHealthylist(WxHealthy wxHealthy)
    {
        startPage();
        List<WxHealthy> list = wxHealthyService.selectWxHealthyList(wxHealthy);
        return getDataTable(list);
    }

    /**
     * 获取健康板块详细信息
     */
    @GetMapping(value = "/wxHealthy/{uuid}")
    public AjaxResult getWxHealthyInfo(@PathVariable("uuid") String uuid)
    {
        return success(wxHealthyService.selectWxHealthyByUuid(uuid));
    }

    @GetMapping("/wxCulturalTourism/list")
    public TableDataInfo wxCulturalTourismList(WxCulturalTourism wxCulturalTourism)
    {
        startPage();
        List<WxCulturalTourism> list = wxCulturalTourismService.selectWxCulturalTourismList(wxCulturalTourism);
        return getDataTable(list);
    }

    @GetMapping(value = "/wxCulturalTourism/{uuid}")
    public AjaxResult getWxCulturalTourismInfo(@PathVariable("uuid") String uuid)
    {
        return success(wxCulturalTourismService.selectWxCulturalTourismByUuid(uuid));
    }

    /**
     * 查询小程序模块列表
     */
    @GetMapping("/wxMiniPrograms/list")
    public TableDataInfo proGramslist(WxMiniPrograms wxMiniPrograms)
    {
        startPage();
        List<WxMiniPrograms> list = wxMiniProgramsService.selectWxMiniProgramsList(wxMiniPrograms);
        return getDataTable(list);
    }

    /**
     * 获取小程序模块详细信息
     */
    @GetMapping(value = "/wxMiniPrograms/{uuid}")
    public AjaxResult getProgramsInfo(@PathVariable("uuid") String uuid)
    {
        return success(wxMiniProgramsService.selectWxMiniProgramsByUuid(uuid));
    }

    /**
     * 湾沚早报列表
     */
    @GetMapping("/wzMorning/list")
    public TableDataInfo wzMorningList(WzMorning wzMorning)
    {
        startPage();
        List<WzMorning> list = wzMorningService.selectWzMorningList(wzMorning);
        return getDataTable(list);
    }


    /**
     * 庙会信息列表
     */
    @GetMapping("/temple/list")
    public TableDataInfo templeList(TempleFairInfo templeFairInfo)
    {
        startPage();
        List<TempleFairInfo> list = templeFairInfoService.selectTempleFairInfoList(templeFairInfo);
        return getDataTable(list);
    }


    /**
     * 获取庙会信息详细信息
     */
    @GetMapping(value = "/temple/{id}")
    public AjaxResult getTempleFairInfoById(@PathVariable("id") Long id)
    {
        return success(templeFairInfoService.selectTempleFairInfoById(id));
    }

    /**
     * 新增庙会信息
     */
    @PostMapping("/temple/add")
    public AjaxResult addTempleFairInfo(@RequestBody TempleFairInfo templeFairInfo)
    {
        return toAjax(templeFairInfoService.insertTempleFairInfo(templeFairInfo));
    }

    /**
     * 修改庙会信息
     */
    @PutMapping("/temple/update")
    public AjaxResult edit(@RequestBody TempleFairInfo templeFairInfo)
    {
        return toAjax(templeFairInfoService.updateTempleFairInfo(templeFairInfo));
    }

    /**
     * 删除庙会信息
     */
    @DeleteMapping("/temple/delete/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(templeFairInfoService.deleteTempleFairInfoByIds(ids));
    }

    @GetMapping("/temple/getSaleTypeList")
    public AjaxResult getSaleTypeList()
    {
        return AjaxResult.success(templeFairInfoService.getSaleTypeList());
    }

    @GetMapping("/temple/staticsBySaleType")
    public AjaxResult staticsBySaleType()
    {
        return AjaxResult.success(templeFairInfoService.staticsBySaleType());
    }

    @GetMapping("/temple/staticsByArea")
    public AjaxResult staticsByArea()
    {
        return AjaxResult.success(templeFairInfoService.staticsByArea());
    }

}
