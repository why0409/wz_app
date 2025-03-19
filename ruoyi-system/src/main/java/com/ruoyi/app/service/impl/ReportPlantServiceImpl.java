package com.ruoyi.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.app.domain.PlantPermissions;
import com.ruoyi.app.domain.ReportPlant;
import com.ruoyi.app.mapper.PlantPermissionsMapper;
import com.ruoyi.app.mapper.ReportPlantMapper;
import com.ruoyi.app.service.IReportPlantService;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ReportPlantServiceImpl extends ServiceImpl<ReportPlantMapper, ReportPlant> implements IReportPlantService {
  @Resource
  private PlantPermissionsMapper plantPermissionsMapper;

    @Override
    public boolean insertReportPlant(ReportPlant reportPlant) {
        reportPlant.setCreateTime(new Date());
        return this.save(reportPlant);

    }

    @Override
    public AjaxResult getReportPlantList(String plantId, String phone) {
        QueryWrapper<ReportPlant> queryWrapper = new QueryWrapper<>();
        if (null!=plantId){
            queryWrapper.eq("plant_id",plantId);
        }
        queryWrapper.orderByDesc("create_time");
        //判断当前用户有无权限
        if (null!=phone){
            LambdaQueryWrapper<PlantPermissions> lambdaqueryWrapper = new LambdaQueryWrapper<>();
            lambdaqueryWrapper.eq(PlantPermissions::getPhone,phone);
            PlantPermissions plantPermissions = plantPermissionsMapper.selectOne(lambdaqueryWrapper);
            if (null!=plantPermissions){
                if (plantPermissions.getIsPlant()==1){
                    return AjaxResult.success(this.list(queryWrapper));
                }else {
                    return AjaxResult.error("暂无权限!");
                }
            }else {
                return AjaxResult.error("当前用户暂未授权！");
            }
        }else {
            return AjaxResult.success(this.list(queryWrapper));
        }

    }
}
