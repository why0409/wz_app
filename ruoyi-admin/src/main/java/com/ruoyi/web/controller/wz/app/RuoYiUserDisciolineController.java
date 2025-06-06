package com.ruoyi.web.controller.wz.app;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.discioline.domain.DisciolineTemplate;
import com.ruoyi.discioline.domain.DisciplineSign;
import com.ruoyi.discioline.service.DisciolineTemplateService;
import com.ruoyi.discioline.service.DisciplineSignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/service/userDiscioline")
@Api(tags = "ruoyi-用户承诺书相关接口")
public class RuoYiUserDisciolineController extends BaseController {

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
        startPage();
        List<DisciplineSign> list = disciplineSignService.list();
        return getDataTable(list);
    }

    /**
     * 获取首页服务详细信息
     */
    //@PreAuthorize("@ss.hasPermi('service:home:query')")
    @GetMapping(value = "/getUserDisciolineListByPhone/{phone}")
    @ApiOperation("根据手机号获取用户承诺书列表")
    public TableDataInfo getUserDisciolineListByPhone(@PathVariable("phone") String phone)
    {
        DisciplineSign disciplineSign=new DisciplineSign();
        disciplineSign.setPhone(phone);
        List<DisciplineSign> list = disciplineSignService.selectByEntity(disciplineSign);
        return getDataTable(list);
    }
    /**
     * 获取首页服务详细信息
     */
    //@PreAuthorize("@ss.hasPermi('service:home:query')")
    @GetMapping(value = "/getUserDisciolineListById/{id}")
    @ApiOperation("根据模版id获取用户承诺书列表")
    public AjaxResult getUserDisciolineListById(@PathVariable("id") int id)
    {
        DisciplineSign disciplineSign=new DisciplineSign();
        disciplineSign.setTemplateId(id);
        List<DisciplineSign> list = disciplineSignService.selectByEntity(disciplineSign);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setSign(null);
        }
        return success(list);
    }
    /**
     * 新增首页服务
     */
    //@PreAuthorize("@ss.hasPermi('service:home:add')")
    @Log(title = "首页服务", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("添加用户承诺书")
    public AjaxResult add(@RequestBody DisciplineSign disciplineSign)
    {
        disciplineSign.setCreateTime(new Date());
        return success(disciplineSignService.save(disciplineSign));
    }

    /**
     * 修改首页服务
     */
    //@PreAuthorize("@ss.hasPermi('service:home:edit')")
    @Log(title = "首页服务", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("编辑用户承诺书")
    public AjaxResult edit(@RequestBody DisciplineSign disciplineSign)
    {
        disciplineSign.setUpdateTime(new Date());
        return success(disciplineSignService.updateByPrimaryKeySelective(disciplineSign));
    }

    /**
     * 删除首页服务
     */
    //@PreAuthorize("@ss.hasPermi('service:home:remove')")
    @Log(title = "首页服务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    @ApiOperation("删除用户承诺书")
    public AjaxResult remove(@PathVariable int id)
    {
        return success(disciplineSignService.removeById(id));
    }


}
