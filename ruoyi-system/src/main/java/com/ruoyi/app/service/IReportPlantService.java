package com.ruoyi.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.app.domain.ReportPlant;
import com.ruoyi.common.core.domain.AjaxResult;

import java.util.List;

public interface IReportPlantService extends IService<ReportPlant> {
    boolean insertReportPlant(ReportPlant reportPlant);

    AjaxResult getReportPlantList(String plantId, String phone);
}
