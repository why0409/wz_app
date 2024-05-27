package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.WxClickmoduleInfo;

/**
 * @author Administrator
 */
public class WxClickmoduleInfoDto extends WxClickmoduleInfo {
    public String startTime;
    public String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
