package com.ruoyi.web.controller.wx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.discioline.domain.DisciolineTemplate;
import com.ruoyi.discioline.domain.DisciplineSign;
import com.ruoyi.discioline.service.DisciolineTemplateService;
import com.ruoyi.discioline.service.DisciplineSignService;
import com.ruoyi.electricity.domain.YdEnterpriseInfo;
import com.ruoyi.electricity.service.IYdEnterpriseInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@RestController
@RequestMapping("/applet")
@Api(tags = "小程序----承诺书相关接口")
public class DisciolineController extends BaseController {
    @Autowired
    private DisciolineTemplateService disciolineTemplateService;
    @Autowired
    private DisciplineSignService disciplineSignService;

    @GetMapping("/getDisciolineList")
    @ApiOperation("获取模板列表")
    public AjaxResult getDisciolineList() {
        List<DisciolineTemplate> list = disciolineTemplateService.list();

        return success(list);
    }
    @GetMapping("/getDisciolineListByPhone")
    @ApiOperation("根据管理员获取模板列表")
    public AjaxResult getDisciolineListByPhone(String phone) {
        List<JSONObject> res= new ArrayList<JSONObject>();
        DisciolineTemplate disciolineTemplate=new DisciolineTemplate();
        disciolineTemplate.setOperater(phone);
        List<DisciolineTemplate> list = disciolineTemplateService.list();

        for (int i = 0; i < list.size(); i++) {
            DisciolineTemplate dis=list.get(i);
            if(dis.getOperater().indexOf(phone)>-1){
                DisciplineSign temp=new DisciplineSign();
                temp.setTemplateId(dis.getId());
                List<DisciplineSign> userlist =disciplineSignService.selectByEntity(temp);
                JSONObject obj=JSONObject.parseObject(JSON.toJSONString(dis));
                obj.put("count",userlist.size());
                res.add(obj);
            }else {
                JSONObject obj=JSONObject.parseObject(JSON.toJSONString(dis));
                obj.put("count",null);
                res.add(obj);
            }
        }




//        for (int i = 0; i < list.size(); i++) {
//            DisciolineTemplate dis=list.get(i);
//            DisciplineSign temp=new DisciplineSign();
//            temp.setTemplateId(dis.getId());
//            List<DisciplineSign> userlist =disciplineSignService.selectByEntity(temp);
//            JSONObject obj=JSONObject.parseObject(JSON.toJSONString(dis));
//            obj.put("count",userlist.size());
//            res.add(obj);
//
//        }
        return success(res);
    }
    @GetMapping("/getDisciolineListById")
    @ApiOperation("根据模版id获取模板列表")
    public AjaxResult getDisciolineListById(int id) {
        DisciolineTemplate disciolineTemplate=new DisciolineTemplate();
        disciolineTemplate.setId(id);
        List<DisciolineTemplate> list = disciolineTemplateService.selectByEntity(disciolineTemplate);
        if(list.size()>0){
            return success(list.get(0));
        }else {
            return success(new ArrayList<>());
        }

    }
    @GetMapping("/addDisciolineTemplate")
    @ApiOperation("添加模板")
    public AjaxResult addDisciolineTemplate(DisciolineTemplate disciolineTemplate) {
        disciolineTemplate.setCreateTime(new Date());
        return success(disciolineTemplateService.save(disciolineTemplate));
    }
    @GetMapping("/editDisciolineTemplate")
    @ApiOperation("编辑模板")
    public AjaxResult editDisciolineTemplate(DisciolineTemplate disciolineTemplate) {
        disciolineTemplate.setUpdateTime(new Date());
        return success(disciolineTemplateService.updateByPrimaryKeySelective(disciolineTemplate));
    }
    @GetMapping("/deleteDisciolineTemplate")
    @ApiOperation("删除模板")
    public AjaxResult deleteDisciolineTemplate(int id) {
        return success(disciolineTemplateService.removeById(id));
    }



    @GetMapping("/getUserDisciolineList")
    @ApiOperation("获取用户承诺书列表")
    public AjaxResult getUserDisciolineList() {
        List<DisciplineSign> list = disciplineSignService.list();
        return success(list);
    }
    @GetMapping("/getUserDisciolineListByPhone")
    @ApiOperation("根据手机号获取用户承诺书列表")
    public AjaxResult getUserDisciolineListByPhone(String phone) {
        DisciplineSign disciplineSign=new DisciplineSign();
        disciplineSign.setPhone(phone);
        List<DisciplineSign> list = disciplineSignService.selectByEntity(disciplineSign);
        return success(list);
    }
    @GetMapping("/getUserDisciolineListByPhoneAndTempId")
    @ApiOperation("根据手机号和TempId获取用户承诺书列表")
    public AjaxResult getUserDisciolineListByPhone(@RequestParam(value = "phone",defaultValue = "") String phone,
                                                   @RequestParam(value = "id",required = false) String id ) {
        DisciplineSign disciplineSign=new DisciplineSign();
        disciplineSign.setPhone(phone);
        if(id!=null){
            disciplineSign.setTemplateId(Integer.parseInt(id));
        }

        List<DisciplineSign> list = disciplineSignService.selectByEntity(disciplineSign);
        return success(list);
    }
    @GetMapping("/getUserDisciolineListByTemplateId")
    @ApiOperation("根据模版id获取用户承诺书列表")
    public AjaxResult getUserDisciolineListByTemplateId(int id) {
        DisciplineSign disciplineSign=new DisciplineSign();
        disciplineSign.setTemplateId(id);
        List<DisciplineSign> list = disciplineSignService.selectByEntity(disciplineSign);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setSign(null);
        }
        return success(list);

    }
    @GetMapping("/addUserDiscioline")
    @ApiOperation("添加用户承诺书")
    public AjaxResult addUserDiscioline(DisciplineSign disciplineSign) {
        disciplineSign.setCreateTime(new Date());
        return success(disciplineSignService.save(disciplineSign));
    }
    @PostMapping("/sign")
    @ApiOperation("签名")
    public AjaxResult sign(@RequestBody DisciplineSign disciplineSign) {

        DisciplineSign disciplineSignNew=new DisciplineSign();
        disciplineSignNew.setPhone(disciplineSign.getPhone());
        disciplineSignNew.setTemplateId(disciplineSign.getTemplateId());
        List<DisciplineSign> list =disciplineSignService.selectByEntity(disciplineSignNew);
        if(list.size()>0){
            DisciplineSign data=list.get(0);
            data.setUpdateTime(new Date());
            data.setSign(disciplineSign.getSign());
            return success(disciplineSignService.updateByPrimaryKeySelective(data));
        }else {
            disciplineSignNew.setTemplateId(null);
            List<DisciplineSign> listAdd =disciplineSignService.selectByEntity(disciplineSignNew);
            if(listAdd.size()==0){
                return error("未找到用户信息");
            }else{

                for (int i = 0; i < listAdd.size(); i++) {
                    if(listAdd.get(i).getTemplateId()==null){
                        DisciplineSign data=listAdd.get(i);
                        data.setUpdateTime(new Date());
                        data.setSign(disciplineSign.getSign());
                        data.setTemplateId(disciplineSign.getTemplateId());
                        return success(disciplineSignService.updateByPrimaryKeySelective(data));
                    }
                }
                DisciplineSign dataAdd=listAdd.get(0);
                dataAdd.setCreateTime(new Date());
                dataAdd.setSign(disciplineSign.getSign());
                dataAdd.setTemplateId(disciplineSign.getTemplateId());
                dataAdd.setId(null);
                return success(disciplineSignService.save(dataAdd));
            }
        }
    }
    @GetMapping("/editUserDiscioline")
    @ApiOperation("编辑用户承诺书")
    public AjaxResult editUserDiscioline(DisciplineSign disciplineSign) {
        disciplineSign.setUpdateTime(new Date());
        return success(disciplineSignService.updateByPrimaryKeySelective(disciplineSign));
    }
    @GetMapping("/deleteUserDiscioline")
    @ApiOperation("删除用户承诺书")
    public AjaxResult deleteUserDiscioline(int id) {
        return success(disciplineSignService.removeById(id));
    }




}
