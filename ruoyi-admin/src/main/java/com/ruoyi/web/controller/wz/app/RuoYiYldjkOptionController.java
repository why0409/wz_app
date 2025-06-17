package com.ruoyi.web.controller.wz.app;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.discioline.domain.DisciolineTemplate;
import com.ruoyi.discioline.domain.YldjkOption;
import com.ruoyi.discioline.service.YldjkOptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/service/YldjkOption")
@Api(tags = "易涝点监控配置相关接口")
public class RuoYiYldjkOptionController extends BaseController {
    @Autowired
    private YldjkOptionService YldjkOptionService;


    @GetMapping("/list")
    @ApiOperation("获取模板列表")
    public TableDataInfo getYldjkOptionList() {
        startPage();
        List<YldjkOption> list = YldjkOptionService.list();

        return getDataTable(list);
    }
    @PostMapping
    @ApiOperation("添加模板")
    public AjaxResult addYldjkOption(@RequestBody  YldjkOption YldjkOption) {

        return success(YldjkOptionService.save(YldjkOption));
    }
    @PutMapping
    @ApiOperation("编辑模板")
    public AjaxResult editYldjkOption(@RequestBody YldjkOption YldjkOption) {

        return success(YldjkOptionService.updateByPrimaryKeySelective(YldjkOption));
    }
    @DeleteMapping("/{id}")
    @ApiOperation("删除模板")
    public AjaxResult deleteYldjkOption(@PathVariable int id) {
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
