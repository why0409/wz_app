package com.ruoyi.web.controller.wz.maintenance;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.WxTalentPolicy;
import com.ruoyi.system.mapper.WxTalentPolicyMapper;
import com.ruoyi.system.service.IWxTalentPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 人才政策Controller
 *
 * @author ruoyi
 * @date 2023-02-22
 */
@RestController
@RequestMapping("/system/policy")
public class WxTalentPolicyController extends BaseController
{
    @Autowired
    private IWxTalentPolicyService wxTalentPolicyService;

    @Resource
    private WxTalentPolicyMapper wxTalentPolicyMapper;

    /**
     * 查询人才政策列表
     */
    //@PreAuthorize("@ss.hasPermi('system:policy:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxTalentPolicy wxTalentPolicy)
    {
        startPage();
        List<WxTalentPolicy> list = wxTalentPolicyService.selectWxTalentPolicyList(wxTalentPolicy);
        return getDataTable(list);
    }

    /**
     * 导出人才政策列表
     */
    //@PreAuthorize("@ss.hasPermi('system:policy:export')")
    @Log(title = "人才政策", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxTalentPolicy wxTalentPolicy)
    {
        List<WxTalentPolicy> list = wxTalentPolicyService.selectWxTalentPolicyList(wxTalentPolicy);
        ExcelUtil<WxTalentPolicy> util = new ExcelUtil<WxTalentPolicy>(WxTalentPolicy.class);
        util.exportExcel(response, list, "人才政策数据");
    }

    /**
     * 获取人才政策详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:policy:query')")
    @GetMapping(value = "/{uuid}")
    public AjaxResult getInfo(@PathVariable("uuid") String uuid)
    {
        return success(wxTalentPolicyService.selectWxTalentPolicyByUuid(uuid));
    }

    /**
     * 新增人才政策
     */
    //@PreAuthorize("@ss.hasPermi('system:policy:add')")
    @Log(title = "人才政策", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxTalentPolicy wxTalentPolicy)
    {
        UUID uuid = UUID.randomUUID();
        wxTalentPolicy.setUuid(uuid.toString());
        wxTalentPolicy.setPublishTime(new Date());
        //int checkSortNum = wxTalentPolicyMapper.checkSortNum(wxTalentPolicy.getSortNum());
        //return checkSortNum == 0 ? toAjax(wxTalentPolicyService.insertWxTalentPolicy(wxTalentPolicy)) : AjaxResult.error("已存在重复的序号，请修改！");

        return toAjax(wxTalentPolicyService.insertWxTalentPolicy(wxTalentPolicy));
    }

    /**
     * 修改人才政策
     */
    //@PreAuthorize("@ss.hasPermi('system:policy:edit')")
    @Log(title = "人才政策", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxTalentPolicy wxTalentPolicy)
    {
        wxTalentPolicy.setUpdateTime(new Date());
        //WxTalentPolicy w = wxTalentPolicyMapper.selectWxTalentPolicyByUuid(wxTalentPolicy.getUuid());
        //int checkSortNum = wxTalentPolicyMapper.checkSortNum(wxTalentPolicy.getSortNum());
        //if (w.getSortNum().equals(wxTalentPolicy.getSortNum())){
        //    return toAjax(wxTalentPolicyService.updateWxTalentPolicy(wxTalentPolicy));
        //}else {
        //    return (checkSortNum == 0) ? toAjax(wxTalentPolicyService.updateWxTalentPolicy(wxTalentPolicy)) : AjaxResult.error("已存在重复的序号，请修改！");
        //}
        return toAjax(wxTalentPolicyService.updateWxTalentPolicy(wxTalentPolicy));
    }

    /**
     * 删除人才政策
     */
    //@PreAuthorize("@ss.hasPermi('system:policy:remove')")
    @Log(title = "人才政策", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uuids}")
    public AjaxResult remove(@PathVariable String[] uuids)
    {
        return toAjax(wxTalentPolicyService.deleteWxTalentPolicyByUuids(uuids));
    }
}
