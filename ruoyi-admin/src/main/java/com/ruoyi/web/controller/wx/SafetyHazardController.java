package com.ruoyi.web.controller.wx;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.safetyHazard.domain.*;
import com.ruoyi.safetyHazard.domain.dto.SafetyHazardManifestSchoolDto;
import com.ruoyi.safetyHazard.domain.vo.ExportSafetyHazardUserVo;
import com.ruoyi.safetyHazard.service.*;
import com.ruoyi.web.controller.wx.common.SmsMsgService;
import com.ruoyi.web.controller.wx.common.WxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @Autowired
    private ISafetyHazardUserEvaluateService safetyHazardUserEvaluateService;

    @Autowired
    private SmsMsgService smsMsgService;

    @Autowired
    private WxService wxService;

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
     * 获取安全隐患-清单-学校详细信息
     */
    @GetMapping("/manifest/isAddInTimes")
    public AjaxResult isAddInTimes(Long userId)
    {
        return success(safetyHazardManifestSchoolService.isAddInTimes(userId));
    }

    /**
     * 新增安全隐患-清单-学校
     */
    @PostMapping("/manifest/school")
    public AjaxResult add(@RequestBody SafetyHazardManifestSchoolDto safetyHazardManifestSchoolDto)
    {
        SafetyHazardManifestSchool safetyHazardManifestSchool = safetyHazardManifestSchoolDto.convertSafetyHazardManifestSchool();
        Long userId = safetyHazardManifestSchool.getUserId();
        String wxPhone = safetyHazardManifestSchool.getWxPhone();
        String isPart = safetyHazardManifestSchool.getIspart();

        //检查必要参数
        if (userId == null || StringUtils.isEmpty(wxPhone) || StringUtils.isEmpty(isPart)){
            return error("必要参数为空");
        }

        //检查30天内是否有填报记录
        if (! safetyHazardManifestSchoolService.isAddInTimes(userId)){
            return error("自查单位30天内已有填报记录");
        }

        //检查上次清单是否处理完成
        SafetyHazardManifestSchool lastSmc =  safetyHazardManifestSchoolService.getLastManifestByUserId(userId);
        if (lastSmc != null && !lastSmc.getStatus().equals("4")){
            return error("有清单尚未结束流程，请及时处理");
        }

        String uuid = UUID.fastUUID().toString();
        safetyHazardManifestSchool.setId("school-"+uuid);

        //清单状态 1-部分填报 2-填报完成
        if (isPart.equals("0")){
            safetyHazardManifestSchool.setStatus("1");
        }else if(isPart.equals("1")){
            safetyHazardManifestSchool.setStatus("2");
        }

        //获取填报人姓名
        String userName = safetyHazardUserService.getUsernameByWxphone(safetyHazardManifestSchool.getWxPhone());
        safetyHazardManifestSchool.setContact(userName);

        return toAjax(safetyHazardManifestSchoolService.insertSafetyHazardManifestSchool(safetyHazardManifestSchool));
    }

    /**
     * 修改安全隐患-清单-学校
     */
    @PutMapping("/manifest/school")
    public AjaxResult edit(@RequestBody SafetyHazardManifestSchoolDto safetyHazardManifestSchoolDto)
    {
        SafetyHazardManifestSchool safetyHazardManifestSchool = safetyHazardManifestSchoolDto.convertSafetyHazardManifestSchool();

        //清单状态 1-部分填报 2-填报完成
        if (safetyHazardManifestSchool.getIspart().equals("0")){
            safetyHazardManifestSchool.setStatus("1");
        }else if(safetyHazardManifestSchool.getIspart().equals("1")){
            safetyHazardManifestSchool.setStatus("2");
        }

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
        //JSONObject property = JSON.parseObject(s.getProperty());
        ////检查关联信息是否录入权限字段
        //if (! property.containsKey("sxdw")){
        //    return AjaxResult.success(new ArrayList<>());
        //}
        //
        //JSONArray sxdwArray = property.getJSONArray("sxdw");
        //
        //List<Long> userIds = new ArrayList<>();
        //for (int i = 0; i < sxdwArray.size(); i++) {
        //    JSONArray innerArray = sxdwArray.getJSONArray(i);
        //    Long userId = innerArray.getLongValue(1);
        //    userIds.add(userId);
        //}

        Long parentId = s.getUserId();
        List<Long> userIds = safetyHazardUserService.selectUserIdsByParentId(parentId);

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

        //新增回复
        safetyHazardManifestReplyService.insertSafetyHazardManifestReply(safetyHazardManifestReply);

        //更新清单状态 3-督查已回复
        SafetyHazardManifestSchool smc = new SafetyHazardManifestSchool();
        smc.setId(manifestId);
        if (! StringUtils.isEmpty(safetyHazardManifestReply.getDcReply())) {
            smc.setStatus("3");
        }
        safetyHazardManifestSchoolService.updateSafetyHazardManifestSchool(smc);

        return success();
    }

    /**
     * 根据id修改安全隐患-清单-回复
     */
    //@Transactional
    @PutMapping("/reply")
    public AjaxResult edit(@RequestBody SafetyHazardManifestReply safetyHazardManifestReply)
    {
        String manifestId =  safetyHazardManifestReply.getManifestId();
        //SafetyHazardManifestReply sr = safetyHazardManifestReplyService.selectByManifestId(manifestId);
        //if (sr != null && !StringUtils.isEmpty(sr.getZcReply())) {
        //    return AjaxResult.error("该清单已有回复");
        //}

        //更新回复
        safetyHazardManifestReplyService.updateSafetyHazardManifestReply(safetyHazardManifestReply);

        //更新清单状态 4-流程结束
        SafetyHazardManifestSchool smc = new SafetyHazardManifestSchool();
        smc.setId(manifestId);
        if (! StringUtils.isEmpty(safetyHazardManifestReply.getZcReply())) {
            smc.setStatus("4");
        }
        safetyHazardManifestSchoolService.updateSafetyHazardManifestSchool(smc);

        return success();
    }

    /**
     * 根据清单id修改安全隐患-清单-回复
     */
    @PutMapping("/reply/editByManifestId")
    public AjaxResult editByManifestId(@RequestBody SafetyHazardManifestReply safetyHazardManifestReply)
    {
        String manifestId =  safetyHazardManifestReply.getManifestId();
        //SafetyHazardManifestReply sr = safetyHazardManifestReplyService.selectByManifestId(manifestId);
        //if (sr != null && !StringUtils.isEmpty(sr.getZcReply())) {
        //    return AjaxResult.error("该清单已有回复");
        //}

        //更新回复
        safetyHazardManifestReplyService.updateByManifestId(safetyHazardManifestReply);

        //更新清单状态 4-流程结束
        SafetyHazardManifestSchool smc = new SafetyHazardManifestSchool();
        smc.setId(manifestId);
        if (! StringUtils.isEmpty(safetyHazardManifestReply.getZcReply())) {
            smc.setStatus("4");
        }
        safetyHazardManifestSchoolService.updateSafetyHazardManifestSchool(smc);

        return success();
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

    /**
     * 督察单位发送通知短信
     */
    @GetMapping("/sendSmsMsgToZc")
    public AjaxResult sendSmsMsgToZc(String manifestId) throws Exception {
        SafetyHazardManifestSchool smc = safetyHazardManifestSchoolService.selectSafetyHazardManifestSchoolById(manifestId);
        String phone = smc.getWxPhone();

        SafetyHazardManifestReply sr = safetyHazardManifestReplyService.selectByManifestId(manifestId);
        String term = JSONObject.parseObject(sr.getDcReply()).getString("term");

        //获取小程序链接
        String wxUrl = wxService.generateLinkByPath("packageE/pages/hazard/index");

        //发送通知短信
        String content = "您在单位隐患自查应用上提交的自查清单，辖区派出所已给出整改意见，整改期限为"+term+"天，请及时反馈整改情况。点击链接进入："+wxUrl;
        JSONObject object = smsMsgService.sendMsgByPerson(phone,content);
        if ("success".equals(object.get("rspcod"))) {
            return success("发送成功");
        } else {
            return error("发送失败");
        }
    }


    /**
     * 导出自查数据
     */
    @ApiOperation("导出数据")
    @PostMapping("/exportSafetyHazardData")
    public AjaxResult export(SafetyHazardUser safetyHazardUser)
    {
        List<ExportSafetyHazardUserVo> list = safetyHazardUserService.exportSafetyHazardUserList(safetyHazardUser);
        ExcelUtil<ExportSafetyHazardUserVo> util = new ExcelUtil<>(ExportSafetyHazardUserVo.class);
        util.init(list, "隐患排查填报数据", StringUtils.EMPTY, Excel.Type.EXPORT);

        AjaxResult ajaxResult = util.exportExcel();
        String fileName = (String) ajaxResult.get("msg");

        String fileUrl = "/profile/download/"+fileName;

        return AjaxResult.success(fileUrl);
    }

    /**
     * 用户列表
     */
    @GetMapping("/user/list")
    public AjaxResult list(SafetyHazardUser safetyHazardUser)
    {
        List<SafetyHazardUser> list = safetyHazardUserService.selectSafetyHazardUserList(safetyHazardUser);
        return AjaxResult.success(list);
    }

    /**
     * 查询隐患排查-用户评价列表
     */
    @GetMapping("/evaluate/list")
    public TableDataInfo list(SafetyHazardUserEvaluate safetyHazardUserEvaluate)
    {
        startPage();
        List<SafetyHazardUserEvaluate> list = safetyHazardUserEvaluateService.selectSafetyHazardUserEvaluateList(safetyHazardUserEvaluate);
        return getDataTable(list);
    }

    /**
     * 新增隐患排查-用户评价
     */
    @PostMapping("/evaluate")
    public AjaxResult add(@RequestBody SafetyHazardUserEvaluate safetyHazardUserEvaluate)
    {
        safetyHazardUserEvaluate.setCreateTime(new Date());
        return toAjax(safetyHazardUserEvaluateService.save(safetyHazardUserEvaluate));
    }

}
