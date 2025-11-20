package com.ruoyi.app.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.app.domain.*;
import com.ruoyi.app.domain.dto.AssessmentLoadDTO;
import com.ruoyi.app.domain.dto.AssessmentSubmitDTO;
import com.ruoyi.app.service.*;
import com.ruoyi.applet.UserInfoService;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 测评小程序公共Service业务层处理
 * * @author wanghongyu
 *
 * @date 2025-11-04
 */
@Service
public class AssessmentPublicServiceImpl implements IAssessmentPublicService {

    @Autowired
    private IAssessmentActivityService activityService;
    @Autowired
    private IAssessmentActivityCadreService activityCadreService;
    @Autowired
    private IAssessmentActivityOptionService activityOptionService;
    @Autowired
    private IAssessmentCadreService cadreService;
    @Autowired
    private IAssessmentOptionService optionService;
    @Autowired
    private IAssessmentLogService logService;
    @Autowired
    private IAssessmentResultService resultService;

    // 注入一个用于 code 换 openid 的服务 (这里用一个模拟的)
    // @Autowired
    // private IWxAuthService wxAuthService;
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 验证活动有效性
     */
    private AssessmentActivity validateActivity(String token, String openId) {
        AssessmentActivity activity = activityService.getOne(
                new LambdaQueryWrapper<AssessmentActivity>().eq(AssessmentActivity::getQrToken, token)
        );

        if (activity == null) {
            throw new ServiceException("测评活动不存在");
        }
        if (!"1".equals(activity.getStatus())) {
            throw new ServiceException("在线测评已结束");
        }

        if (activity.getQrExpireTime() == null || new Date().after(activity.getQrExpireTime())) {
            // 如果过期，顺便更新下状态
            activity.setStatus("2");
            activityService.updateById(activity);
            throw new ServiceException("在线测评已结束");
        }

        long count = logService.count(new LambdaQueryWrapper<AssessmentLog>()
                .eq(AssessmentLog::getActivityId, activity.getActivityId())
                .eq(AssessmentLog::getOpenId, openId));
        if (count > 0) {
            throw new ServiceException("您已提交，请勿重复扫码");
        }

        return activity;
    }

    private AssessmentActivity validateActivity(String token) {
        AssessmentActivity activity = activityService.getOne(
                new LambdaQueryWrapper<AssessmentActivity>().eq(AssessmentActivity::getQrToken, token)
        );

        if (activity == null) {
            throw new ServiceException("测评活动不存在");
        }
        if (!"1".equals(activity.getStatus())) {
            throw new ServiceException("在线测评已结束");
        }

        if (activity.getQrExpireTime() == null || new Date().after(activity.getQrExpireTime())) {
            // 如果过期，顺便更新下状态
            activity.setStatus("2");
            activityService.updateById(activity);
            throw new ServiceException("在线测评已结束");
        }

//        long count = logService.count(new LambdaQueryWrapper<AssessmentLog>()
//                .eq(AssessmentLog::getActivityId, activity.getActivityId())
//                .eq(AssessmentLog::getOpenId, openId));
//        if (count > 0) {
//            throw new ServiceException("您已提交，请勿重复提交");
//        }

        return activity;
    }

    /**
     * 模拟：根据code获取OpenID
     *
     * @param code 微信code
     * @return OpenID
     */
    private String getOpenIdFromCode(String code) {
        // 在真实项目中，这里会调用微信接口
        // return wxAuthService.getOpenId(code);

        // 为方便测试，这里返回一个基于code的模拟值
//        if ("WX_LOGIN_CODE".equals(code)) {
//            // 模拟一个随机用户
//            return "mock_openid_" + UUID.randomUUID().toString().substring(0, 8);
//        }
//        return "mock_openid_" + code;
        JSONObject jsonObject = userInfoService.getOpenId(code);
        return jsonObject.getString("session_key");
    }


    @Override
    public AssessmentLoadDTO loadAssessment(String token, String openId) {
        // 1. 校验活动有效性
        AssessmentActivity activity = validateActivity(token, openId);

        AssessmentLoadDTO loadDTO = new AssessmentLoadDTO();
        loadDTO.setActivity(activity);

        // 2. 获取关联的干部ID
        List<Long> cadreIds = activityCadreService.list(
                        new LambdaQueryWrapper<AssessmentActivityCadre>()
                                .eq(AssessmentActivityCadre::getActivityId, activity.getActivityId()))
                .stream()
                .map(AssessmentActivityCadre::getCadreId)
                .collect(Collectors.toList());

        if (!cadreIds.isEmpty()) {
            loadDTO.setCadres(cadreService.listByIds(cadreIds));
        }

        // 3. 获取关联的选项ID
        List<Long> optionIds = activityOptionService.list(
                        new LambdaQueryWrapper<AssessmentActivityOption>()
                                .eq(AssessmentActivityOption::getActivityId, activity.getActivityId()))
                .stream()
                .map(AssessmentActivityOption::getOptionId)
                .collect(Collectors.toList());

        if (!optionIds.isEmpty()) {
            List<AssessmentOption> options = optionService.listByIds(optionIds);
            // 按类型分组
            Map<String, List<AssessmentOption>> optionMap = options.stream()
                    .collect(Collectors.groupingBy(
                            opt -> "1".equals(opt.getOptionType()) ? "positive" : "negative"
                    ));
            loadDTO.setOptions(optionMap);
        }

        return loadDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submitAssessment(AssessmentSubmitDTO submitDTO) {

        // 1. 再次校验活动有效性
        AssessmentActivity activity = validateActivity(submitDTO.getToken());

        // 2. 获取OpenID
//        String openId = getOpenIdFromCode(submitDTO.getCode());
        String openId = submitDTO.getCode();
        System.out.println(submitDTO);
        if (openId == null) {
            throw new ServiceException("获取用户信息失败");
        }

        // 3. 防重提交校验
        long count = logService.count(new LambdaQueryWrapper<AssessmentLog>()
                .eq(AssessmentLog::getActivityId, activity.getActivityId())
                .eq(AssessmentLog::getOpenId, openId));
        if (count > 0) {
            throw new ServiceException("您已提交，请勿重复提交");
        }

        // 4. 规则校验 (1-3项)
        for (AssessmentSubmitDTO.EvaluationItem item : submitDTO.getEvaluations()) {
            int posCount = item.getPositiveOptions().size();
            int negCount = item.getNegativeOptions().size();
            if (posCount < 1 || posCount > 3 || negCount < 1 || negCount > 3) {
                // TODO: 应该把干部姓名也返回
                throw new ServiceException("选项数不符合规定(1-3项)，请重新选择");
            }
        }

        // 5. 准备批量插入数据
        List<AssessmentResult> resultList = new ArrayList<>();
        Date now = DateUtils.getNowDate();

        for (AssessmentSubmitDTO.EvaluationItem item : submitDTO.getEvaluations()) {
            Long cadreId = item.getCadreId();

            // 插入正面选项
            for (Long optionId : item.getPositiveOptions()) {
                AssessmentResult result = new AssessmentResult();
                result.setActivityId(activity.getActivityId());
                result.setCadreId(cadreId);
                result.setOptionId(optionId);
                result.setOptionType("1");
                result.setCreateTime(now);
                resultList.add(result);
            }

            // 插入负面选项
            for (Long optionId : item.getNegativeOptions()) {
                AssessmentResult result = new AssessmentResult();
                result.setActivityId(activity.getActivityId());
                result.setCadreId(cadreId);
                result.setOptionId(optionId);
                result.setOptionType("2");
                result.setCreateTime(now);
                resultList.add(result);
            }
        }

        // 6. 批量保存结果
        if (!resultList.isEmpty()) {
            resultService.saveBatch(resultList);
        }

        // 7. 写入日志
        AssessmentLog log = new AssessmentLog();
        log.setActivityId(activity.getActivityId());
        log.setOpenId(openId);
        log.setCreateTime(now);
        logService.save(log);
    }
}