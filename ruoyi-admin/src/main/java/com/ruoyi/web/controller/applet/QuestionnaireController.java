package com.ruoyi.web.controller.applet;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.alibaba.fastjson2.JSONObject;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.ruoyi.activities.domain.HomestayRegisteredInfo;
import com.ruoyi.app.domain.QuestionnaireGiftInfo;
import com.ruoyi.app.domain.QuestionnaireInfo;
import com.ruoyi.app.domain.vo.QuestionnaireInfoVo;
import com.ruoyi.app.service.IQuestionnaireGiftInfoService;
import com.ruoyi.app.service.IQuestionnaireInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.UUID;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author: LJW
 * @Date: 2024/7/31 0031 15:29
 */
@Api(tags = "问卷调查接口")
@RestController
@RequestMapping("/applet/questionnaire")
    public class QuestionnaireController extends BaseController {

    @Autowired
    private IQuestionnaireInfoService questionnaireInfoService;

    @Autowired
    private IQuestionnaireGiftInfoService questionnaireGiftInfoService;

    private final int MAX_GIFT_COUNT = 150;

    /**
     * 查询问卷调查信息列表
     */
    @ApiOperation("查询问卷调查信息列表")
    @GetMapping("/list")
    public TableDataInfo questionnaireList(QuestionnaireInfo questionnaireInfo)
    {
        startPage();
        List<QuestionnaireInfoVo> list = questionnaireInfoService.selectQuestionnaireInfoVoList(questionnaireInfo);
        return getDataTable(list);
    }

    /**
     * 获取问卷调查信息详细信息
     */
    @ApiOperation("获取问卷调查信息详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getQuestionnaireInfo(@PathVariable("id") String id)
    {
        return success(questionnaireInfoService.selectQuestionnaireInfoVoById(id));
    }

    /**
     * 新增问卷调查信息
     */
    @ApiOperation("新增问卷调查信息（自动领取礼物）")
    @PostMapping
    public AjaxResult addQuestionnaire(@RequestBody QuestionnaireInfo questionnaireInfo)
    {
        // 插入问卷记录
        String uuid = UUID.fastUUID().toString();

        String isGift = "";
        String phone = questionnaireInfo.getPhone();

        // 检查是否已经领取过
        QuestionnaireGiftInfo isExistQg = questionnaireGiftInfoService.selectTodayQuestionnaireInfoByPhone(phone);
        if (isExistQg != null) {
            // 插入申领记录（已领取）
            isExistQg.setQuestionnaireId(uuid);
            questionnaireGiftInfoService.insertQuestionnaireGiftInfo(isExistQg);

            // 已领取状态
            isGift = "2";
        } else {
            // 未领取，检查礼物发放状态
            QuestionnaireGiftInfo lastQg = questionnaireGiftInfoService.selectTodayQuestionnaireInfoByPhone(null);
            String giftNumber = "";
            if (lastQg == null) {
                giftNumber = DateUtil.format(new Date(), "yyyyMMdd") + "001";
            } else {
                int lastGiftCount = Integer.parseInt(lastQg.getGiftNumber().substring(8));
                if (lastGiftCount >= MAX_GIFT_COUNT) {
                    // 发放额度已满状态
                    isGift = "3";
                } else {
                    giftNumber = String.valueOf(Long.parseLong(lastQg.getGiftNumber()) + 1);
                }
            }

            if (isGift.equals("")) {
                // 插入申领记录（未领取）
                QuestionnaireGiftInfo qgf = new QuestionnaireGiftInfo();
                qgf.setGiftNumber(giftNumber);
                qgf.setPhone(phone);
                qgf.setQuestionnaireId(uuid);
                qgf.setGiftAddress("芜湖市湾沚区市民服务中心");
                qgf.setGiftTime(new Date());
                questionnaireGiftInfoService.insertQuestionnaireGiftInfo(qgf);

                isGift = "1";
            }
        }

        // 插入问卷记录
        questionnaireInfo.setId(uuid);
        questionnaireInfo.setIsGift(StringUtils.isEmpty(isGift) ? "0" : isGift);
        questionnaireInfoService.insertQuestionnaireInfo(questionnaireInfo);

        return success(questionnaireInfoService.selectQuestionnaireInfoVoById(uuid));
    }

    @ApiOperation("新增问卷调查信息（原）")
    @PostMapping("/addQuestionnaireNoAuto")
    public AjaxResult addQuestionnaireNoAuto(@RequestBody QuestionnaireInfo questionnaireInfo)
    {
        String uuid = UUID.fastUUID().toString();

        questionnaireInfo.setId(uuid);
        questionnaireInfo.setIsGift("0");
        questionnaireInfoService.insertQuestionnaireInfo(questionnaireInfo);

        return success(questionnaireInfo);
    }

    /**
     * 修改问卷调查信息
     */
    @ApiOperation("修改问卷调查信息")
    @PutMapping
    public AjaxResult editQuestionnaire(@RequestBody QuestionnaireInfo questionnaireInfo)
    {
        return toAjax(questionnaireInfoService.updateQuestionnaireInfo(questionnaireInfo));
    }

    /**
     * 删除问卷调查信息
     */
    @ApiOperation("删除问卷调查信息")
    @DeleteMapping("/{ids}")
    public AjaxResult removeQuestionnaire(@PathVariable Long[] ids)
    {
        return toAjax(questionnaireInfoService.deleteQuestionnaireInfoByIds(ids));
    }

    /**
     * 查询问卷调查-申领礼品信息列表
     */
    @ApiOperation("查询问卷调查-申领礼品信息列表")
    @GetMapping("/gift/list")
    public TableDataInfo giftList(QuestionnaireGiftInfo questionnaireGiftInfo)
    {
        startPage();
        List<QuestionnaireGiftInfo> list = questionnaireGiftInfoService.selectQuestionnaireGiftInfoList(questionnaireGiftInfo);
        return getDataTable(list);
    }

    /**
     * 获取问卷调查-申领礼品信息详细信息
     */
    @GetMapping(value = "/gift/{id}")
    public AjaxResult getGiftInfo(@PathVariable("id") Long id)
    {
        return success(questionnaireGiftInfoService.selectQuestionnaireGiftInfoById(id));
    }

    /**
     * 新增问卷调查-申领礼品信息
     */
    @Transactional
    @PostMapping("/gift")
    public AjaxResult addGift(@RequestBody QuestionnaireGiftInfo questionnaireGiftInfo)
    {
        String isGift = "";
        String phone = questionnaireGiftInfo.getPhone();

        // 检查是否已经领取过
        QuestionnaireGiftInfo isExistQg = questionnaireGiftInfoService.selectTodayQuestionnaireInfoByPhone(phone);
        if (isExistQg != null) {
            // 插入申领记录（已领取）
            isExistQg.setQuestionnaireId(questionnaireGiftInfo.getQuestionnaireId());
            questionnaireGiftInfoService.insertQuestionnaireGiftInfo(isExistQg);

            // 已领取状态
            isGift = "2";
        } else {
            // 未领取，检查礼物发放状态
            QuestionnaireGiftInfo lastQg = questionnaireGiftInfoService.selectTodayQuestionnaireInfoByPhone(null);
            String giftNumber = "";
            if (lastQg == null) {
                giftNumber = DateUtil.format(new Date(), "yyyyMMdd") + "001";
            } else {
                int lastGiftCount = Integer.parseInt(lastQg.getGiftNumber().substring(8));
                if (lastGiftCount >= MAX_GIFT_COUNT) {
                    // 发放额度已满状态
                    isGift = "3";
                } else {
                    giftNumber = String.valueOf(Long.parseLong(lastQg.getGiftNumber()) + 1);
                }
            }

            if (isGift.equals("")) {
                // 插入申领记录（未领取）
                questionnaireGiftInfo.setGiftNumber(giftNumber);
                questionnaireGiftInfo.setGiftAddress("芜湖市湾沚区市民服务中心");
                questionnaireGiftInfo.setGiftTime(new Date());
                questionnaireGiftInfoService.insertQuestionnaireGiftInfo(questionnaireGiftInfo);

                isGift = "1";
            }
        }

        //更新问卷记录
        QuestionnaireInfo qi = new QuestionnaireInfo();
        qi.setId(questionnaireGiftInfo.getQuestionnaireId());
        qi.setIsGift(isGift);
        questionnaireInfoService.updateQuestionnaireInfo(qi);

        return success(questionnaireInfoService.selectQuestionnaireInfoVoById(questionnaireGiftInfo.getQuestionnaireId()));
    }

    /**
     * 修改问卷调查-申领礼品信息
     */
    @PutMapping("/gift")
    public AjaxResult editGift(@RequestBody QuestionnaireGiftInfo questionnaireGiftInfo)
    {
        return toAjax(questionnaireGiftInfoService.updateQuestionnaireGiftInfo(questionnaireGiftInfo));
    }

    /**
     * 删除问卷调查-申领礼品信息
     */
    @DeleteMapping("/gift/{ids}")
    public AjaxResult removeGift(@PathVariable Long[] ids)
    {
        return toAjax(questionnaireGiftInfoService.deleteQuestionnaireGiftInfoByIds(ids));
    }

    /**
     * 根据窗口统计问卷数
     */
    @ApiOperation("根据窗口统计问卷数")
    @GetMapping("/staticsCountByWindows")
    public AjaxResult staticsCountByWindows(String windowNumber)
    {
        return success(questionnaireInfoService.staticsCountByWindows(windowNumber));
    }

    /**
     * 生成扫码二维码
     */
    @ApiOperation("生成扫码二维码")
    @GetMapping("/getQRCode")
    public AjaxResult getQRCode() {
        //二维码配置
        QrConfig config = new QrConfig();
        config.setMargin(0);
        config.setErrorCorrection(ErrorCorrectionLevel.H);

        JSONObject jb = new JSONObject();
        jb.put("longitude", "118.575388");
        jb.put("latitude", "31.12959");
        jb.put("mark", "questionnaire");

        String base64Code = QrCodeUtil.generateAsBase64(jb.toJSONString(), config, ImgUtil.IMAGE_TYPE_PNG);

        return AjaxResult.success(base64Code);
    }

    /**
     * 统计问满意度
     */
    @ApiOperation("统计问满意度")
    @GetMapping("/staticsSatisfaction")
    public AjaxResult staticsSatisfaction()
    {
        return success(questionnaireInfoService.staticsSatisfaction());
    }

}
