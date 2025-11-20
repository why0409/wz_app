package com.ruoyi.web.controller.wz.app;

import com.ruoyi.app.domain.dto.AssessmentLoadDTO;
import com.ruoyi.app.domain.dto.AssessmentSubmitDTO;
import com.ruoyi.app.service.IAssessmentPublicService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 测评小程序公共Controller
 * * @author wanghongyu
 *
 * @date 2025-11-04
 */
@Api(tags = "测评小程序用户接口")
@RestController
@RequestMapping("/applet/assessment")
public class AssessmentPublicController extends BaseController {
    @Autowired
    private IAssessmentPublicService publicService;

    /**
     * 加载测评内容
     *
     * @param token 测评Token (来自二维码)
     */
    @ApiOperation("加载测评内容")
    @GetMapping("/load")
    public AjaxResult loadAssessment(String token, String openId) {
        // Service层会处理Token校验和过期
        AssessmentLoadDTO loadDTO = publicService.loadAssessment(token, openId);
        return success(loadDTO);
    }

    /**
     * 提交测评结果
     */
    @ApiOperation("提交测评结果")
    @PostMapping("/submit")
    public AjaxResult submitAssessment(@Validated @RequestBody AssessmentSubmitDTO submitDTO) {
        // Service层会处理Token校验、防刷校验、规则校验
        publicService.submitAssessment(submitDTO);
        return success("匿名评价成功");
    }
}