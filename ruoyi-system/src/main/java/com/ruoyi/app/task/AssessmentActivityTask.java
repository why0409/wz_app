package com.ruoyi.app.task;

import com.ruoyi.app.service.IAssessmentActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 测评活动定时任务 (Spring Scheduled)
 * * @author wanghongyu
 */
@Component
public class AssessmentActivityTask {
    private static final Logger log = LoggerFactory.getLogger(AssessmentActivityTask.class);

    @Autowired
    private IAssessmentActivityService activityService;

    /**
     * 每5分钟执行一次
     */
    @Scheduled(cron = "0 */5 * * * ?")
    public void handleCloseExpiredActivities() {
        log.info("Spring Scheduled：正在关闭已过期的测评活动...");
        try {
            int updatedRows = activityService.closeExpiredActivities();
            if (updatedRows > 0) {
                log.info("Spring Scheduled：成功关闭了 {} 个已过期的测评活动。", updatedRows);
            } else {
                log.info("Spring Scheduled：没有需要关闭的测评活动。");
            }
        } catch (Exception e) {
            log.error("Spring Scheduled[handleCloseExpiredActivities]执行异常: ", e);
        }
    }
}