package com.ruoyi.web.controller.wz.maintenance;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.WxDeptPhone;
import com.ruoyi.system.mapper.WxDeptPhoneMapper;
import com.ruoyi.system.service.IWxDeptPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 部门电话Controller
 *
 * @author ruoyi
 * @date 2023-02-14
 */
@RestController
@RequestMapping("/system/phone")
public class WxDeptPhoneController extends BaseController
{
    @Autowired
    private IWxDeptPhoneService wxDeptPhoneService;

    @Resource
    private WxDeptPhoneMapper wxDeptPhoneMapper;

    /**
     * 查询部门电话列表
     */
    //@PreAuthorize("@ss.hasPermi('system:phone:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxDeptPhone wxDeptPhone)
    {
        startPage();
        List<WxDeptPhone> list = wxDeptPhoneService.selectWxDeptPhoneList(wxDeptPhone);
        return getDataTable(list);
    }

    /**
     * 导出部门电话列表
     */
    //@PreAuthorize("@ss.hasPermi('system:phone:export')")
    @Log(title = "部门电话", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxDeptPhone wxDeptPhone)
    {
        List<WxDeptPhone> list = wxDeptPhoneService.selectWxDeptPhoneList(wxDeptPhone);
        ExcelUtil<WxDeptPhone> util = new ExcelUtil<WxDeptPhone>(WxDeptPhone.class);
        util.exportExcel(response, list, "部门电话数据");
    }

    /**
     * 获取部门电话详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:phone:query')")
    @GetMapping(value = "/{uuid}")
    public AjaxResult getInfo(@PathVariable("uuid") String uuid)
    {
        return success(wxDeptPhoneService.selectWxDeptPhoneByUuid(uuid));
    }

    /**
     * 新增部门电话
     */
    //@PreAuthorize("@ss.hasPermi('system:phone:add')")
    @Log(title = "部门电话", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxDeptPhone wxDeptPhone)
    {
        UUID uuid = UUID.randomUUID();
        wxDeptPhone.setUuid(uuid.toString());
        wxDeptPhone.setCreateTime(new Date());
        //int checkSortNum = wxDeptPhoneMapper.checkSortNum(wxDeptPhone.getSortNum());
        //return checkSortNum == 0 ? toAjax(wxDeptPhoneService.insertWxDeptPhone(wxDeptPhone)) : AjaxResult.error("已存在重复的序号，请修改！");
        return toAjax(wxDeptPhoneService.insertWxDeptPhone(wxDeptPhone));
    }

    /**
     * 修改部门电话
     */
    //@PreAuthorize("@ss.hasPermi('system:phone:edit')")
    @Log(title = "部门电话", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxDeptPhone wxDeptPhone)
    {
        wxDeptPhone.setUpdateTime(new Date());
        //WxDeptPhone w = wxDeptPhoneMapper.selectWxDeptPhoneByUuid(wxDeptPhone.getUuid());
        //int checkSortNum = wxDeptPhoneMapper.checkSortNum(wxDeptPhone.getSortNum());
        //if (w.getSortNum().equals(wxDeptPhone.getSortNum())){
        //    return toAjax(wxDeptPhoneService.updateWxDeptPhone(wxDeptPhone));
        //}else {
        //    return (checkSortNum == 0) ? toAjax(wxDeptPhoneService.updateWxDeptPhone(wxDeptPhone)) : AjaxResult.error("已存在重复的序号，请修改！");
        //}

        return toAjax(wxDeptPhoneService.updateWxDeptPhone(wxDeptPhone));
    }

    /**
     * 删除部门电话
     */
    //@PreAuthorize("@ss.hasPermi('system:phone:remove')")
    @Log(title = "部门电话", businessType = BusinessType.DELETE)
    @DeleteMapping("/{uuids}")
    public AjaxResult remove(@PathVariable String[] uuids)
    {
        return toAjax(wxDeptPhoneService.deleteWxDeptPhoneByUuids(uuids));
    }
}

