package com.ruoyi.web.controller.wx;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.alibaba.fastjson2.JSONObject;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.ruoyi.activities.domain.ActivitiesInfo;
import com.ruoyi.activities.domain.HomestayInfo;
import com.ruoyi.activities.domain.HomestayOfflineRegister;
import com.ruoyi.activities.domain.HomestayRegisteredInfo;
import com.ruoyi.activities.domain.vo.HomestayOfflineRegisterVo;
import com.ruoyi.activities.domain.vo.HomestayRegisteredInfoVo;
import com.ruoyi.activities.domain.vo.RegisteredInfoVoByGovernment;
import com.ruoyi.activities.service.IActivitiesInfoService;
import com.ruoyi.activities.service.IHomestayInfoService;
import com.ruoyi.activities.service.IHomestayOfflineRegisterService;
import com.ruoyi.activities.service.IHomestayRegisteredInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.DesensitizedUtils;
import com.ruoyi.common.utils.SmsUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.ws.WebSocketServer;
import com.ruoyi.web.controller.wx.common.SmsMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


/**
 * 民宿体验季活动Controller
 *
 * @author ruoyi
 * @date 2024-06-19
 */
@Slf4j
@RestController
@RequestMapping("/applet/homestay")
public class HomestayActivitiesController extends BaseController
{
    @Autowired
    private IHomestayRegisteredInfoService homestayRegisteredInfoService;

    @Autowired
    private RedisCache redisCache;

    private static final int QRCODE_EXPIRY_TIME_MINUTES = 10;

    @Autowired
    private IHomestayInfoService homestayInfoService;

    @Autowired
    private IActivitiesInfoService activitiesInfoService;

    @Autowired
    private WebSocketServer webSocketServer;

    @Autowired
    private IHomestayOfflineRegisterService homestayOfflineRegisterService;

    @Autowired
    private SmsMsgService smsMsgService;

    /**
     * 查询登记人员列表
     */
    @GetMapping("/getRegisteredInfoList")
    public TableDataInfo getRegisteredInfoList(HomestayRegisteredInfo homestayRegisteredInfo)
    {
        startPage();
        List<HomestayRegisteredInfoVo> list = homestayRegisteredInfoService.selectHomestayRegisteredInfoVoList(homestayRegisteredInfo);
        return getDataTable(list);
    }

    /**
     * 查询登记人员列表-政府端
     */
    @GetMapping("/getRegisteredInfoListByGovernment")
    public TableDataInfo getRegisteredInfoListByGovernment(HomestayRegisteredInfo homestayRegisteredInfo)
    {
        startPage();
        List<RegisteredInfoVoByGovernment> list = homestayRegisteredInfoService.selectRegisteredInfoVoByGovernmentList(homestayRegisteredInfo);
        return getDataTable(list);
    }

    /**
     * 导出登记人员列表
     */
    @PostMapping("/registeredInfo/export")
    public void registeredInfoExport(HttpServletResponse response, HomestayRegisteredInfo homestayRegisteredInfo)
    {
        List<HomestayRegisteredInfo> list = homestayRegisteredInfoService.selectHomestayRegisteredInfoList(homestayRegisteredInfo);
        ExcelUtil<HomestayRegisteredInfo> util = new ExcelUtil<>(HomestayRegisteredInfo.class);
        util.exportExcel(response, list, "民宿体验季活动数据");
    }

    /**
     * 获取登记人员详细信息
     */
    @GetMapping("/getRegisteredInfo/{id}")
    public AjaxResult getRegisteredInfo(@PathVariable("id") Long id)
    {
        return success(homestayRegisteredInfoService.selectHomestayRegisteredInfoById(id));
    }

    /**
     * 新增登记人员
     */
    @PostMapping("/addRegisteredInfo")
    public AjaxResult addRegisteredInfo(@RequestBody HomestayRegisteredInfo homestayRegisteredInfo)
    {
        String redisKey = "sms_captcha_" + homestayRegisteredInfo.getContactPhone();
        String correctCode = redisCache.getCacheObject(redisKey)+"";

        String wxPhone = homestayRegisteredInfo.getWxPhone();
        String idNumber = homestayRegisteredInfo.getIdNumber();
        Long activitiesId = homestayRegisteredInfo.getActivitiesId();
        if (StringUtils.isEmpty(wxPhone) || activitiesId == null) {
            return error("必要参数缺失，请重新登录小程序");
        }

        int countByWxPhone = homestayRegisteredInfoService.checkCountByWxPhone(wxPhone, activitiesId);
        if (countByWxPhone > 0) {
            return error("该用户已登记");
        }

        int countByIdNumber = homestayRegisteredInfoService.checkCountByIdNumber(idNumber, activitiesId);
        if (countByIdNumber > 0) {
            return error("该身份证号已被登记");
        }

        if (correctCode == null) {
            return error("验证码已过期");
        } else if (! correctCode.equals(homestayRegisteredInfo.getSmsCode())) {
            return error("验证码无效");
        } else {
            redisCache.deleteObject(redisKey);

            homestayRegisteredInfo.setIsWin("0");
            return toAjax(homestayRegisteredInfoService.insertHomestayRegisteredInfo(homestayRegisteredInfo));
        }

        //return error("登记结束");
    }

    /**
     * 根据微信手机号获取登记人员详细信息
     */
    @GetMapping("/getRegisteredInfoByWxPhone")
    public AjaxResult getRegisteredInfoByWxPhone(String phone, Long activitiesId)
    {
        //if (activitiesId == null) {
        //    ActivitiesInfo ac =  activitiesInfoService.selectNormalActivitiesInfo("0");
        //    activitiesId = ac.getId();
        //}
        return success(homestayRegisteredInfoService.selectHomestayRegisteredInfoByWxPhone(phone, activitiesId));
    }

    /**
     * 修改登记人员信息
     */
    @PutMapping("/editRegisteredInfo")
    public AjaxResult editRegisteredInfo(@RequestBody HomestayRegisteredInfo homestayRegisteredInfo)
    {
        String redisKey = "sms_captcha_" + homestayRegisteredInfo.getContactPhone();
        String correctCode = redisCache.getCacheObject(redisKey)+"";

        if (correctCode == null) {
            return error("验证码已过期");
        } else if (! correctCode.equals(homestayRegisteredInfo.getSmsCode())) {
            return error("验证码无效");
        } else {
            redisCache.deleteObject(redisKey);
            return toAjax(homestayRegisteredInfoService.updateHomestayRegisteredInfoByWxPhone(homestayRegisteredInfo));
        }

        //return error("登记结束");
    }

    /**
     * 删除登记人员信息
     */
    @DeleteMapping("/onlineRegisteredInfos/delete/{ids}")
    public AjaxResult removeOnlineRegisteredInfos(@PathVariable Long[] ids)
    {
        return toAjax(homestayRegisteredInfoService.deleteHomestayRegisteredInfoByIds(ids));
    }

    /**
     * 发送验证码短信
     */
    @GetMapping("/sendSmsCode")
    public AjaxResult sendSmsCode(String phone) throws Exception {
        JSONObject object = smsMsgService.sendNumberCode(phone);

        if ("success".equals(object.get("rspcod"))) {
            return AjaxResult.success("发送成功");
        } else {
            return AjaxResult.error("发送失败");
        }
    }

    /**
     * 获取当前正常活动
     */
    @GetMapping("/getNormalActivities")
    public AjaxResult getNormalActivities(Long activitiesId)
    {
        //ActivitiesInfo ac =  activitiesInfoService.selectNormalActivitiesInfo("0");
        ActivitiesInfo ac =  activitiesInfoService.selectActivitiesInfoById(activitiesId);

        Long registerStartTime = DateUtil.parseDateTime(ac.getRegistrationTime().split(",")[0]).getTime();
        Long registerEndTime = DateUtil.parseDateTime(ac.getRegistrationTime().split(",")[1]).getTime();
        Long releaseStartTime = DateUtil.parseDateTime(ac.getReleaseTime().split(",")[0]).getTime();
        Long releaseEndTime = DateUtil.parseDateTime(ac.getReleaseTime().split(",")[1]).getTime();
        Long activitiesEndTime = DateUtil.parseDateTime(ac.getConsumptionTime().split(",")[1]).getTime();
        if (System.currentTimeMillis() < registerStartTime){
            //活动未开始
            ac.setCurrentStatus("0");
        }else if(registerStartTime  <= System.currentTimeMillis() && System.currentTimeMillis() <= registerEndTime){
            //登记进行中
            ac.setCurrentStatus("1");
        }else if (registerEndTime < System.currentTimeMillis() && System.currentTimeMillis() < releaseStartTime) {
            //登记结束摇号未开始
            ac.setCurrentStatus("2");
        } else if(releaseStartTime <= System.currentTimeMillis() && System.currentTimeMillis() <= releaseEndTime){
            //摇号进行中
            ac.setCurrentStatus("3");
        } else if (releaseEndTime < System.currentTimeMillis() && System.currentTimeMillis() <= activitiesEndTime) {
            //摇号结束
            ac.setCurrentStatus("4");
        }else {
            //活动结束
            ac.setCurrentStatus("5");
        }

        return success(ac);
    }

    /**
     * 民宿列表
     */
    @GetMapping("/getHomestaylist")
    public AjaxResult getHomestaylist(HomestayInfo homestayInfo)
    {
        List<HomestayInfo> list = homestayInfoService.selectHomestayInfoList(homestayInfo);
        return success(list);
    }

    /**
     * 获取民宿信息详情
     */
    @GetMapping("/getHomestayInfo/{id}")
    public AjaxResult getHomestayInfo(@PathVariable("id") Long id)
    {
        return success(homestayInfoService.selectHomestayInfoById(id));
    }


    /**
     * 根据手机号查询验劵权限
     */
    @GetMapping("/checkVerifyPermission")
    public AjaxResult checkVerifyPermission(String phone, Long activitiesId)
    {
        int count = homestayInfoService.checkVerifyPermission(phone, activitiesId);

        return count > 0 ? success(true) : success(false);
    }

    /**
     * 摇号抽奖
     */
    @GetMapping("/lottery")
    public void lottery(int round, Long activitiesId) throws Exception {
        log.info(DateUtil.now()+"---开始第"+round+"轮抽奖---");

        //ActivitiesInfo activitiesInfo = activitiesInfoService.selectNormalActivitiesInfo("0");
        //Long activitiesId = activitiesInfo.getId();

        ActivitiesInfo activitiesInfo = activitiesInfoService.selectActivitiesInfoById(activitiesId);
        int prizesCount = activitiesInfo.getSecuritiesRestNumber();

        List<Long> allIds = new ArrayList<>();
        List<Long> whiteListIds = new ArrayList<>();
        int winCount = 0;
        int prizesCountByRound = 0;
        if (round == 1) {
            prizesCountByRound = 200;
            //湾沚区白名单
            List<String> whiteListByWz = Arrays.asList("18905530025");
            whiteListIds = homestayRegisteredInfoService.selectNotWinIdsByWxPhones(activitiesId,whiteListByWz);
            //第一轮抽取湾沚区名单
            allIds = homestayRegisteredInfoService.selectNotWinIdsByWz(activitiesId);
        } else if (round == 2){
            prizesCountByRound = 300;
            //本市外区白名单
            List<String> whiteListByWh = Arrays.asList("");
            whiteListIds = homestayRegisteredInfoService.selectNotWinIdsByWxPhones(activitiesId,whiteListByWh);
            //第二轮抽取本市外区名单
            allIds = homestayRegisteredInfoService.selectNotWinIdsByWhNotWz(activitiesId);
        } else if (round == 3){
            prizesCountByRound = 500;
            //其他地区白名单
            List<String> whiteListByOther = Arrays.asList("18105539005","15609690111","18118846365");
            whiteListIds = homestayRegisteredInfoService.selectNotWinIdsByWxPhones(activitiesId,whiteListByOther);
            //第二轮抽取其他地区名单
            allIds = homestayRegisteredInfoService.selectNotWinIdsByOther(activitiesId);
        }
        //待抽奖名单中移除白名单人员
        allIds.removeAll(whiteListIds);
        winCount = prizesCountByRound - whiteListIds.size();

        //随机抽奖
        List<Long> winIds = getRandomlyDraw(allIds,winCount);
        winIds.addAll(whiteListIds);

       //更新中奖用户中奖状态和未兑奖状态
        homestayRegisteredInfoService.updateIsWinByIds("1","0", winIds);

        //更新本次抽奖后剩余奖品数量
        ActivitiesInfo ac = new ActivitiesInfo();
        ac.setId(activitiesInfo.getId());
        ac.setSecuritiesRestNumber(prizesCount - winIds.size());
        activitiesInfoService.updateActivitiesInfo(ac);

        //模拟逐条抽奖并向前端发送信息
        List<HomestayRegisteredInfo> list = homestayRegisteredInfoService.selectHomestayRegisteredInfoByIds(winIds);
        for (HomestayRegisteredInfo h : list) {
            JSONObject msg = JSONObject.parse(h.toString());
            msg.put("winTime",new Date());
            msg.put("name", DesensitizedUtils.desensitizeName(msg.getString("name")));
            msg.put("wxPhone", DesensitizedUtil.mobilePhone(msg.getString("wxPhone")));
            msg.put("contactPhone", DesensitizedUtil.mobilePhone(msg.getString("contactPhone")));
            msg.put("idNumber", DesensitizedUtil.idCardNum(msg.getString("idNumber"), 3, 4));

            webSocketServer.sendInfo(msg.toJSONString(),"wzLottery");

            Thread.sleep(150);
        }

        //抽奖结束
        webSocketServer.sendInfo("over","wzLottery");

        //更新摇号中可查看
        homestayRegisteredInfoService.updateIsLotteryingShowByIds("1",winIds);
    }

    public List<Long> getRandomlyDraw(List<Long> allIds, int winCount) {
        List<Long> winIds;
        if (winCount >= allIds.size()) {
            // 如果中奖数量大于或等于登记人员的总数，则所有人都中奖
            winIds =new ArrayList<>(allIds);
        } else {
            // 创建一个随机数生成器
            Random random = new Random();
            // 创建一个临时列表用于打乱顺序
            List<Long> tempIds = new ArrayList<>(allIds);
            // 打乱顺序
            Collections.shuffle(tempIds, random);
            winIds = new ArrayList<>(tempIds.subList(0, winCount));
        }
        return winIds;
    }

    /**
     * 根据获奖手机号生成体验劵二维码
     */
    @GetMapping("/getQRCode")
    public AjaxResult getQRCode(String phone, Long activitiesId) {
        HomestayRegisteredInfo hi = homestayRegisteredInfoService.selectHomestayRegisteredInfoByWxPhone(phone,activitiesId);
        if (! "1".equals(hi.getIsWin())) {
            return error("当前用户未中奖！");
        }

        if ("1".equals(hi.getWinStatus())) {
            return error("该用户已兑奖！");
        }

        //二维码配置
        QrConfig config = new QrConfig();
        config.setMargin(0);
        config.setErrorCorrection(ErrorCorrectionLevel.H);

        //为二维码加上过期时间
        JSONObject jb = new JSONObject();
        Long expiryTime = System.currentTimeMillis() + QRCODE_EXPIRY_TIME_MINUTES * 60 * 1000;
        jb.put("id",hi.getId());
        jb.put("expiryTime", expiryTime);
        jb.put("label", "wzmsty");

        String base64Code = QrCodeUtil.generateAsBase64(jb.toJSONString(), config, ImgUtil.IMAGE_TYPE_PNG);

        return AjaxResult.success(base64Code);
    }

    /**
     * 验证体验劵二维码是否过期及有效
     */
    @PostMapping("/verifyQRCode")
    public AjaxResult verifyQRCode(@RequestBody JSONObject info) {
        Long id = info.getLong("id");
        Long expiryTime = info.getLong("expiryTime");

        //验证二维码是否过期
        if (expiryTime < System.currentTimeMillis()) {
            return error("二维码已过期，请刷新");
        }

        //验证用户核验状态
        HomestayRegisteredInfo hri = homestayRegisteredInfoService.selectHomestayRegisteredInfoById(id);
        String winStatus = hri.getWinStatus();
        if ("1".equals(winStatus)) {
            return error("该用户已核验");
        }

        return AjaxResult.success("验证成功");
    }

    /**
     * 根据验证手机号获取民宿列表
     */
    @GetMapping("/getHomestayInfoListByVerifyPhone")
    public AjaxResult getHomestayInfoListByVerifyPhone(String phone, Long activitiesId) {
        return AjaxResult.success(homestayInfoService.getHomestayInfoListByVerifyPhone(phone, activitiesId));
    }

    /**
     * 根据用户id设置体验劵状态为已验证并且关联民宿id
     */
    @GetMapping("/updateWinStatus")
    public AjaxResult updateWinStatus(Long id, String phone, Long homestayId) {
        HomestayRegisteredInfo h = new HomestayRegisteredInfo();
        h.setId(id);
        h.setWinStatus("1");
        h.setHomestayId(homestayId);
        h.setVerifyPhone(phone);
        h.setVerifyTime(new Date());

        return AjaxResult.success(homestayRegisteredInfoService.updateHomestayRegisteredInfo(h));
    }

    /**
     * 根据活动id获取关联民宿信息列表
     */
    @GetMapping("/getHomestayInfoListById")
    public AjaxResult getHomestayInfoListById (Long id)
    {
        ActivitiesInfo ac = activitiesInfoService.selectActivitiesInfoById(id);

        List<Long> homestayIds = Arrays.stream(ac.getHomestayIds().split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());

        List<HomestayInfo> list = homestayInfoService.getHomestayInfoByIds(homestayIds);

        return AjaxResult.success(list);
    }

    /**
     * 活动总体统计情况
     */
    @GetMapping("/getStaticsCount")
    public AjaxResult getStaticsCount(){
        return success(homestayRegisteredInfoService.getStaticsCount());
    }

    /**
     * 根据消费数量排序民宿列表
     */
    @GetMapping("/getHomestayListOrderByWinstatus")
    public AjaxResult getHomestayListOrderByWinstatus(Long activitiesId){
        return success(homestayInfoService.getHomestayListOrderByWinstatus(activitiesId));
    }

    /**
     * 根据民宿id和活动id获取已验劵人员列表
     */
    @GetMapping("/getWinnerListByHomestayId")
    public AjaxResult getWinnerListByHomestayId(Long homestayId, Long activitiesId)
    {
        return success(homestayRegisteredInfoService.selectHomestayRegisteredInfoUseVoList(homestayId,activitiesId));
    }

    /**
     * 根据民宿id获取参与的活动列表
     */
    @GetMapping("/getActivitiesListByHomestayId")
    public AjaxResult getActivitiesListByHomestayId(Long homestayId)
    {
        return success(activitiesInfoService.getActivitiesListByHomestayId(homestayId));
    }

    /**
     * 查询民宿活动线下劵注册信息列表
     */
    @GetMapping("/offlineRegister/list")
    public TableDataInfo getOfflineRegisterList(HomestayOfflineRegister homestayOfflineRegister)
    {
        startPage();
        List<HomestayOfflineRegisterVo> list = homestayOfflineRegisterService.selectHomestayOfflineRegisterVoList(homestayOfflineRegister);
        return getDataTable(list);
    }

    /**
     * 导出民宿活动线下劵注册信息列表
     */
    @PostMapping("/offlineRegister/export")
    public void exportOfflineRegisters(HttpServletResponse response, HomestayOfflineRegister homestayOfflineRegister)
    {
        List<HomestayOfflineRegister> list = homestayOfflineRegisterService.selectHomestayOfflineRegisterList(homestayOfflineRegister);
        ExcelUtil<HomestayOfflineRegister> util = new ExcelUtil<>(HomestayOfflineRegister.class);
        util.exportExcel(response, list, "民宿活动线下劵注册信息数据");
    }

    /**
     * 获取民宿活动线下劵注册信息详细信息
     */
    @GetMapping(value = "/offlineRegister/{id}")
    public AjaxResult getOfflineRegisterInfo(@PathVariable("id") Long id)
    {
        return success(homestayOfflineRegisterService.selectHomestayOfflineRegisterById(id));
    }

    /**
     * 新增民宿活动线下劵注册信息
     */
    @PostMapping("/offlineRegister/save")
    public AjaxResult addOfflineRegister(@RequestBody HomestayOfflineRegister homestayOfflineRegister)
    {
        //ActivitiesInfo ac =  activitiesInfoService.selectNormalActivitiesInfo("0");
        //Long activitiesId = ac.getId();
        //homestayOfflineRegister.setActivitiesId(activitiesId);

        Long activitiesId = homestayOfflineRegister.getActivitiesId();

        String idNumber = homestayOfflineRegister.getIdNumber();
        int count = homestayOfflineRegisterService.getTodayCountByIdNumber(activitiesId, idNumber);
        if (count > 0) {
            return error("当前身份证号码今日已注册！");
        }else {
            return toAjax(homestayOfflineRegisterService.insertHomestayOfflineRegister(homestayOfflineRegister));
        }
    }

    /**
     * 修改民宿活动线下劵注册信息
     */
    @PutMapping("/offlineRegister/edit")
    public AjaxResult editOfflineRegister(@RequestBody HomestayOfflineRegister homestayOfflineRegister)
    {
        return toAjax(homestayOfflineRegisterService.updateHomestayOfflineRegister(homestayOfflineRegister));
    }

    /**
     * 删除民宿活动线下劵注册信息
     */
    @DeleteMapping("/offlineRegister/delete/{ids}")
    public AjaxResult deleteOfflineRegisters(@PathVariable Long[] ids)
    {
        return toAjax(homestayOfflineRegisterService.deleteHomestayOfflineRegisterByIds(ids));
    }

    //@GetMapping("/sendNoUseMsg")
    //public AjaxResult sendNoUseMsg() throws Exception {
    //    ActivitiesInfo activitiesInfo = activitiesInfoService.selectNormalActivitiesInfo("0");
    //    Long activitiesId = activitiesInfo.getId();
    //
    //    HomestayRegisteredInfo hri = new HomestayRegisteredInfo();
    //    hri.setIsWin("1");
    //    hri.setWinStatus("0");
    //    hri.setActivitiesId(activitiesId);
    //    List<HomestayRegisteredInfo> list = homestayRegisteredInfoService.selectHomestayRegisteredInfoList(hri);
    //
    //    // 获取所有中奖用户的手机号
    //    List<String> phonesList = new ArrayList<>();
    //    for (HomestayRegisteredInfo homestayRegisteredInfo : list) {
    //        phonesList.add(homestayRegisteredInfo.getContactPhone());
    //    }
    //
    //    String content = "【芜湖市湾沚文化旅游体育局】温馨提示：您抽中的湾沚区民宿体验券还未使用，体验券将于9月30日到期，凭券可在湾沚区20家民宿中任选一家免费入住，详情请在“智慧湾沚”微信小程序中“民宿体验券”板块查询，详询0553-8912280。浪漫湾沚，沚等您来！";
    //    //发送通知短信
    //    JSONObject object = smsMsgService.sendMsgByGroup(phonesList,content);
    //    if ("success".equals(object.get("rspcod"))) {
    //        return success("发送成功");
    //    } else {
    //        return error("发送失败");
    //    }
    //}

    @GetMapping("/activities/list")
    public AjaxResult list(ActivitiesInfo activitiesInfo)
    {
        List<ActivitiesInfo> list = activitiesInfoService.selectActivitiesInfoList(activitiesInfo);
        return success(list);
    }

}
