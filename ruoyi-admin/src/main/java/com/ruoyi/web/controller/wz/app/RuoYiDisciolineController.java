package com.ruoyi.web.controller.wz.app;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.app.domain.ServiceHome;
import com.ruoyi.app.domain.vo.ServiceHomeInfo;
import com.ruoyi.app.service.IServiceHomeService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.discioline.domain.DisciolineTemplate;
import com.ruoyi.discioline.domain.DisciplineSign;
import com.ruoyi.discioline.service.DisciolineTemplateService;
import com.ruoyi.discioline.service.DisciplineSignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/service/discioline")
@Api(tags = "ruoyi-承诺书模版相关接口")
public class RuoYiDisciolineController extends BaseController {
    @Autowired
    private DisciolineTemplateService disciolineTemplateService;

    @Autowired
    private DisciplineSignService disciplineSignService;
    /**
     * 查询承诺书列表
     */
    //@PreAuthorize("@ss.hasPermi('service:home:list')")
    @GetMapping("/list")
    @ApiOperation("获取模板列表")
    public TableDataInfo list()
    {
        List<JSONObject> res= new ArrayList<JSONObject>();
        startPage();
        List<DisciolineTemplate> list = disciolineTemplateService.list();

//        TableDataInfo resT=getDataTable(list);

        for (int i = 0; i < list.size(); i++) {
            DisciolineTemplate dis=list.get(i);

            DisciplineSign temp=new DisciplineSign();
            temp.setTemplateId(dis.getId());
            List<DisciplineSign> userlist =disciplineSignService.selectByEntity(temp);
            JSONObject obj=JSONObject.parseObject(JSON.toJSONString(dis));
            obj.put("count",userlist.size());
            res.add(obj);

        }

        return getDataTable(res);
    }

    /**
     * 获取首页服务详细信息
     */
    //@PreAuthorize("@ss.hasPermi('service:home:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation("根据模版id获取模板列表")
    public AjaxResult getDisciolineListById(@PathVariable("id") int id)
    {
        DisciolineTemplate disciolineTemplate=new DisciolineTemplate();
        disciolineTemplate.setId(id);
        List<DisciolineTemplate> list = disciolineTemplateService.selectByEntity(disciolineTemplate);
        if(list.size()>0){
            return success(list.get(0));
        }else {
            return success(new ArrayList<>());
        }
    }

    /**
     * 新增首页服务
     */
    //@PreAuthorize("@ss.hasPermi('service:home:add')")
    @Log(title = "首页服务", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("添加模板")
    public AjaxResult add(@RequestBody DisciolineTemplate disciolineTemplate)
    {
        disciolineTemplate.setCreateTime(new Date());

        return toAjax(disciolineTemplateService.save(disciolineTemplate));
    }

    /**
     * 修改首页服务
     */
    //@PreAuthorize("@ss.hasPermi('service:home:edit')")
    @Log(title = "首页服务", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("编辑模板")
    public AjaxResult edit(@RequestBody DisciolineTemplate disciolineTemplate)
    {
        disciolineTemplate.setUpdateTime(new Date());
        return toAjax(disciolineTemplateService.updateByPrimaryKeySelective(disciolineTemplate));

    }

    /**
     * 删除首页服务
     */
    //@PreAuthorize("@ss.hasPermi('service:home:remove')")
    @Log(title = "首页服务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    @ApiOperation("删除模板")
    public AjaxResult remove(@PathVariable int id)
    {
        return toAjax(disciolineTemplateService.removeById(id));
    }


}
