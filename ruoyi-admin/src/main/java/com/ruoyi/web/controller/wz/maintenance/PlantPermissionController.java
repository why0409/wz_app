package com.ruoyi.web.controller.wz.maintenance;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.app.domain.PlantPermissions;
import com.ruoyi.app.service.IPlantPermissionsService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.jsjDb.domain.WxUser;
import com.ruoyi.jsjDb.service.IWxUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plant")
public class PlantPermissionController {
    @Autowired
    private IPlantPermissionsService plantPermissionsService;
    @Autowired
    private IWxUserService wxUserService;
    @ApiOperation("新增留言板查看权限")
    @PostMapping("/addPlantPermissions")
    public AjaxResult addPlantPermissions(@RequestBody PlantPermissions plantPermissions){
        //删除相同手机号数据
        LambdaQueryWrapper<PlantPermissions> lambdaqueryWrapper = new LambdaQueryWrapper<>();
        lambdaqueryWrapper.eq(PlantPermissions::getPhone,plantPermissions.getPhone());
        plantPermissionsService.remove(lambdaqueryWrapper);
//        }

        //修改用户表
        WxUser wxUser = wxUserService.selectWxUserById(plantPermissions.getUserId());
        Integer isPlant = plantPermissions.getIsPlant();
        wxUser.setIsPlant(isPlant);
        wxUserService.updateWxUser(wxUser);
        return AjaxResult.success(plantPermissionsService.save(plantPermissions));
    }
}
