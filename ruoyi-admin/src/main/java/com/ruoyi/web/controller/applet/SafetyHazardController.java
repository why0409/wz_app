package com.ruoyi.web.controller.applet;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.safetyHazard.domain.SafetyHazardManifestSchool;
import com.ruoyi.safetyHazard.domain.SafetyHazardManifestReply;
import com.ruoyi.safetyHazard.domain.SafetyHazardUser;
import com.ruoyi.safetyHazard.domain.SafetyHazardUserType;
import com.ruoyi.safetyHazard.domain.dto.SafetyHazardManifestSchoolDto;
import com.ruoyi.safetyHazard.service.ISafetyHazardManifestReplyService;
import com.ruoyi.safetyHazard.service.ISafetyHazardManifestSchoolService;
import com.ruoyi.safetyHazard.service.ISafetyHazardUserService;
import com.ruoyi.safetyHazard.service.ISafetyHazardUserTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LJW
 * @Date: 2024/8/12 0012 13:39
 */
@Api(tags = "安全隐患接口")
@RestController
@RequestMapping("/applet/safetyHazard")
public class SafetyHazardController extends BaseController {

    @Autowired
    private ISafetyHazardUserService safetyHazardUserService;

    @Autowired
    private ISafetyHazardManifestSchoolService safetyHazardManifestSchoolService;

    @Autowired
    private ISafetyHazardManifestReplyService safetyHazardManifestReplyService;

    @Autowired
    private ISafetyHazardUserTypeService safetyHazardUserTypeService;

    /**
     * 根据手机号查询信息
     */
    @ApiOperation("根据手机号查询信息")
    @GetMapping("/getUserByPhone")
    public AjaxResult getUserByPhone(String phone)
    {
        SafetyHazardUser shu = new SafetyHazardUser();
        shu.setContactGroup(phone);

        List<SafetyHazardUser> list = safetyHazardUserService.selectSafetyHazardUserList(shu);
        return AjaxResult.success(list);
    }

    /**
     * 查询安全隐患-清单-学校列表
     */
    @GetMapping("/manifest/school/list")
    public TableDataInfo list(SafetyHazardManifestSchool safetyHazardManifestSchool)
    {
        startPage();
        List<SafetyHazardManifestSchool> list = safetyHazardManifestSchoolService.selectSafetyHazardManifestSchoolList(safetyHazardManifestSchool);
        return getDataTable(list);
    }

    /**
     * 获取安全隐患-清单-学校详细信息
     */
    @GetMapping("/manifest/school/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(safetyHazardManifestSchoolService.selectSafetyHazardManifestSchoolById(id));
    }

    /**
     * 新增安全隐患-清单-学校
     */
    @PostMapping("/manifest/school")
    public AjaxResult add(@RequestBody SafetyHazardManifestSchoolDto safetyHazardManifestSchoolDto)
    {
        SafetyHazardManifestSchool safetyHazardManifestSchool = safetyHazardManifestSchoolDto.convertSafetyHazardManifestSchool();

        String uuid = UUID.fastUUID().toString();
        safetyHazardManifestSchool.setId("school-"+uuid);

        return toAjax(safetyHazardManifestSchoolService.insertSafetyHazardManifestSchool(safetyHazardManifestSchool));
    }

    /**
     * 修改安全隐患-清单-学校
     */
    @PutMapping("/manifest/school")
    public AjaxResult edit(@RequestBody SafetyHazardManifestSchoolDto safetyHazardManifestSchoolDto)
    {
        SafetyHazardManifestSchool safetyHazardManifestSchool = safetyHazardManifestSchoolDto.convertSafetyHazardManifestSchool();

        return toAjax(safetyHazardManifestSchoolService.updateSafetyHazardManifestSchool(safetyHazardManifestSchool));
    }

    /**
     * 删除安全隐患-清单-学校
     */
    @DeleteMapping("/manifest/school/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(safetyHazardManifestSchoolService.deleteSafetyHazardManifestSchoolByIds(ids));
    }

    @ApiOperation("根据督查Id查询关联自查单位")
    @GetMapping("/getZcListByDcId")
    public AjaxResult getZcListByDcId(Long id, String name, Long typeId)
    {
        //查询用户信息
        SafetyHazardUser s = safetyHazardUserService.selectSafetyHazardUserByUserId(id);

        //检查角色类型
        if ("1".equals(s.getRole())){
            return error("自查单位没有权限");
        }

        //区公安局治安大队拥有所有权限
        if ("psbsb".equals(s.getTypeUuid())){
            SafetyHazardUser safetyHazardUser = new SafetyHazardUser();
            safetyHazardUser.setRole("1");
            safetyHazardUser.setUserName(name);
            safetyHazardUser.setTypeId(typeId);

            return success(safetyHazardUserService.selectSafetyHazardUserList(safetyHazardUser));
        }

        //关联信息
        JSONObject property = JSON.parseObject(s.getProperty());
        //检查关联信息是否录入权限字段
        if (! property.containsKey("sxdw")){
            return AjaxResult.success(new ArrayList<>());
        }

        JSONArray sxdwArray = property.getJSONArray("sxdw");

        List<Long> userIds = new ArrayList<>();
        for (int i = 0; i < sxdwArray.size(); i++) {
            JSONArray innerArray = sxdwArray.getJSONArray(i);
            Long userId = innerArray.getLongValue(1);
            userIds.add(userId);
        }

        return AjaxResult.success(safetyHazardUserService.selectListByUserIds(userIds, name, typeId));
    }

    /**
     * 查询安全隐患-清单-回复列表
     */
    @GetMapping("/reply/list")
    public TableDataInfo list(SafetyHazardManifestReply safetyHazardManifestReply)
    {
        startPage();
        List<SafetyHazardManifestReply> list = safetyHazardManifestReplyService.selectSafetyHazardManifestReplyList(safetyHazardManifestReply);
        return getDataTable(list);
    }

    /**
     * 获取安全隐患-清单-回复详细信息
     */
    @GetMapping("/reply/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(safetyHazardManifestReplyService.selectSafetyHazardManifestReplyById(id));
    }

    /**
     * 新增安全隐患-清单-回复
     */
    @Transactional
    @PostMapping("/reply")
    public AjaxResult add(@RequestBody SafetyHazardManifestReply safetyHazardManifestReply)
    {
        String manifestId =  safetyHazardManifestReply.getManifestId();
        SafetyHazardManifestReply sr = safetyHazardManifestReplyService.selectByManifestId(manifestId);
        if (sr != null && !StringUtils.isEmpty(sr.getZcReply())) {
            return AjaxResult.error("该清单已有回复");
        }

        return toAjax(safetyHazardManifestReplyService.insertSafetyHazardManifestReply(safetyHazardManifestReply));
    }

    /**
     * 修改安全隐患-清单-回复
     */
    @PutMapping("/reply")
    public AjaxResult edit(@RequestBody SafetyHazardManifestReply safetyHazardManifestReply)
    {
        //String manifestId =  safetyHazardManifestReply.getManifestId();
        //SafetyHazardManifestReply sr = safetyHazardManifestReplyService.selectByManifestId(manifestId);
        //if (sr != null && !StringUtils.isEmpty(sr.getZcReply())) {
        //    return AjaxResult.error("该清单已有回复");
        //}

        return toAjax(safetyHazardManifestReplyService.updateSafetyHazardManifestReply(safetyHazardManifestReply));
    }

    /**
     * 修改安全隐患-清单-回复
     */
    @PutMapping("/reply/editByManifestId")
    public AjaxResult editByManifestId(@RequestBody SafetyHazardManifestReply safetyHazardManifestReply)
    {
        //String manifestId =  safetyHazardManifestReply.getManifestId();
        //SafetyHazardManifestReply sr = safetyHazardManifestReplyService.selectByManifestId(manifestId);
        //if (sr != null && !StringUtils.isEmpty(sr.getZcReply())) {
        //    return AjaxResult.error("该清单已有回复");
        //}

        return toAjax(safetyHazardManifestReplyService.updateByManifestId(safetyHazardManifestReply));
    }

    /**
     * 删除安全隐患-清单-回复
     */
    @DeleteMapping("/reply/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(safetyHazardManifestReplyService.deleteSafetyHazardManifestReplyByIds(ids));
    }

    @GetMapping("/userType/list")
    public AjaxResult list(SafetyHazardUserType safetyHazardUserType)
    {
        List<SafetyHazardUserType> list = safetyHazardUserTypeService.selectSafetyHazardUserTypeList(safetyHazardUserType);
        return success(list);
    }

}
