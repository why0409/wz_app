package com.ruoyi.quartz.task;

import cn.hutool.core.io.FileUtil;
import com.ruoyi.common.config.RuoYiConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: LJW
 * @Date: 2024/6/21 0021 16:25
 */

@Slf4j
@Component("DeleteDownLoadFilesTask")
public class DeleteDownLoadFilesTask {

    public static void delete() {
        String downloadPath = RuoYiConfig.getDownloadPath();

        if (FileUtil.exist(downloadPath)) {
            // 如果存在，清空文件夹
            FileUtil.clean(downloadPath);
            log.info("下载文件夹已清空");
        }
    }

}
