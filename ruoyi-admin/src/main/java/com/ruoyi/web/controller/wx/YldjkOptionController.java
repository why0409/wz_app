package com.ruoyi.web.controller.wx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.discioline.domain.YldjkOption;
import com.ruoyi.discioline.domain.DisciplineSign;
import com.ruoyi.discioline.service.YldjkOptionService;
import com.ruoyi.discioline.service.DisciplineSignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/applet")
@Api(tags = "小程序----易涝点监控配置相关接口")
public class YldjkOptionController extends BaseController {
    @Autowired
    private YldjkOptionService YldjkOptionService;


    @GetMapping("/getYldjkOptionList")
    @ApiOperation("获取配置列表")
    public AjaxResult getYldjkOptionList() {
        QueryWrapper<YldjkOption> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("sort");
        List<YldjkOption> list = YldjkOptionService.list(wrapper);

        return success(list);
    }
    @GetMapping("/addYldjkOption")
    @ApiOperation("添加配置")
    public AjaxResult addYldjkOption(YldjkOption YldjkOption) {

        return success(YldjkOptionService.save(YldjkOption));
    }
    @GetMapping("/editYldjkOption")
    @ApiOperation("编辑配置")
    public AjaxResult editYldjkOption(YldjkOption YldjkOption) {

        return success(YldjkOptionService.updateByPrimaryKeySelective(YldjkOption));
    }
    @GetMapping("/deleteYldjkOption")
    @ApiOperation("删除配置")
    public AjaxResult deleteYldjkOption(int id) {
        return success(YldjkOptionService.removeById(id));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("根据模版id获取模板列表")
    public AjaxResult getDisciolineListById(@PathVariable("id") int id)
    {
        YldjkOption disciolineTemplate=new YldjkOption();
        disciolineTemplate.setId(id);
        List<YldjkOption> list = YldjkOptionService.selectByEntity(disciolineTemplate);
        if(list.size()>0){
            return success(list.get(0));
        }else {
            return success(new ArrayList<>());
        }
    }





}
