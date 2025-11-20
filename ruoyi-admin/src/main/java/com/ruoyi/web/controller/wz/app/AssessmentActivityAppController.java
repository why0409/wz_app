package com.ruoyi.web.controller.wz.app;

import com.ruoyi.app.domain.AssessmentActivity;
import com.ruoyi.app.domain.dto.ActivityConfigDetailDTO;
import com.ruoyi.app.domain.dto.AssessmentConfigDTO;
import com.ruoyi.app.domain.dto.AssessmentStartDTO;
import com.ruoyi.app.service.IAssessmentActivityService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 测评活动Controller-小程序端
 * * @author wanghongyu
 *
 * @date 2025-11-04
 */
@Api(tags = "测评活动接口-小程序端")
@RestController
@RequestMapping("/applet/assessment/activityApp")
public class AssessmentActivityAppController extends BaseController {
    @Autowired
    private IAssessmentActivityService assessmentActivityService;

    /**
     * 查询测评活动列表
     */
//    @PreAuthorize("@ss.hasPermi('assessment:activity:list')")
    @ApiOperation("查询测评活动列表")
    @GetMapping("/list")
    public TableDataInfo list(AssessmentActivity assessmentActivity) {
        startPage();
        List<AssessmentActivity> list = assessmentActivityService.selectAssessmentActivityList(assessmentActivity);
        return getDataTable(list);
    }

    /**
     * 获取测评活动详细信息
     */
//    @PreAuthorize("@ss.hasPermi('assessment:activity:query')")
    @ApiOperation("获取测评活动详细信息")
    @GetMapping(value = "/{activityId}")
    public AjaxResult getInfo(@PathVariable("activityId") Long activityId) {
        return success(assessmentActivityService.getById(activityId));
    }

    /**
     * 新增测评活动
     * (已修改)
     */
//    @PreAuthorize("@ss.hasPermi('assessment:activity:add')")
    @ApiOperation("新增测评活动")
    @Log(title = "测评活动", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AssessmentActivity assessmentActivity) {
//        assessmentActivity.setCreateBy(getUsername());

        // 1. 调用 save 方法
        boolean success = assessmentActivityService.save(assessmentActivity);

        if (success) {
            // 2. 保存成功后, MyBatis-Plus 会将自增ID回填到 assessmentActivity 对象中
            // 3. 将 activityId 放入 AjaxResult.success 的 data 字段中返回
            return AjaxResult.success("新增成功", assessmentActivity.getActivityId());
        } else {
            return AjaxResult.error("新增失败，请重试");
        }
    }

    /**
     * 修改测评活动
     */
//    @PreAuthorize("@ss.hasPermi('assessment:activity:edit')")
    @ApiOperation("修改测评活动")
    @Log(title = "测评活动", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AssessmentActivity assessmentActivity) {
//        assessmentActivity.setUpdateBy(getUsername());
        return toAjax(assessmentActivityService.updateById(assessmentActivity));
    }

    /**
     * 删除测评活动
     */
//    @PreAuthorize("@ss.hasPermi('assessment:activity:remove')")
    @ApiOperation("删除测评活动")
    @Log(title = "测评活动", businessType = BusinessType.DELETE)
    @DeleteMapping("/{activityIds}")
    public AjaxResult remove(@PathVariable Long[] activityIds) {
        return toAjax(assessmentActivityService.removeByIds(Arrays.asList(activityIds)));
    }

    // --- 以下是自定义业务接口 ---

    /**
     * 获取活动配置详情
     */
//    @PreAuthorize("@ss.hasPermi('assessment:activity:config')")
    @ApiOperation("获取活动配置详情")
    @GetMapping(value = "/config/{id}")
    public AjaxResult getConfigInfo(@PathVariable("id") Long activityId) {
        ActivityConfigDetailDTO configDetail = assessmentActivityService.selectActivityConfigById(activityId);
        return success(configDetail);
    }

    /**
     * 配置活动
     */
//    @PreAuthorize("@ss.hasPermi('assessment:activity:config')")
    @ApiOperation("配置测评活动")
    @Log(title = "活动配置", businessType = BusinessType.UPDATE)
    @PutMapping("/config")
    public AjaxResult configActivity(@RequestBody AssessmentConfigDTO configDTO) {
        assessmentActivityService.configActivity(configDTO);
        return success();
    }

    /**
     * 开通测评
     */
//    @PreAuthorize("@ss.hasPermi('assessment:activity:start')")
    @ApiOperation("开通测评")
    @Log(title = "开通测评", businessType = BusinessType.UPDATE)
    @PutMapping("/start/{id}")
    public AjaxResult startActivity(@PathVariable("id") Long activityId) {
        AssessmentStartDTO startDTO = assessmentActivityService.startAssessment(activityId);
        return success(startDTO);
    }
}