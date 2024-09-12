package com.ruoyi.web.controller.wz.maintenance;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.WxServePeople;
import com.ruoyi.system.mapper.WxServePeopleMapper;
import com.ruoyi.system.service.IWxServePeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 为民服务Controller
 *
 * @author ruoyi
 * @date 2023-04-10
 */
@RestController
@RequestMapping("/system/serve")
public class WxServePeopleController extends BaseController
{
    @Autowired
    private IWxServePeopleService wxServePeopleService;

    @Autowired
    private WxServePeopleMapper wxServePeopleMapper;

    /**
     * 查询为民服务列表
     */
    //@PreAuthorize("@ss.hasPermi('system:serve:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxServePeople wxServePeople)
    {
        startPage();
        List<WxServePeople> list = wxServePeopleService.selectWxServePeopleList(wxServePeople);
        return getDataTable(list);
    }

    /**
     * 导出为民服务列表
     */
    //@PreAuthorize("@ss.hasPermi('system:serve:export')")
    @Log(title = "为民服务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxServePeople wxServePeople)
    {
        List<WxServePeople> list = wxServePeopleService.selectWxServePeopleList(wxServePeople);
        ExcelUtil<WxServePeople> util = new ExcelUtil<WxServePeople>(WxServePeople.class);
        util.exportExcel(response, list, "为民服务数据");
    }

    /**
     * 获取为民服务详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:serve:query')")
    @GetMapping(value = "/{uuid}")
    public AjaxResult getInfo(@PathVariable("uuid") String uuid)
    {
        return success(wxServePeopleService.selectWxServePeopleByUuid(uuid));
    }

    /**
     * 新增为民服务
     */
    //@PreAuthorize("@ss.hasPermi('system:serve:add')")
    @Log(title = "为民服务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxServePeople wxServePeople)
    {
        UUID uuid = UUID.randomUUID();
        wxServePeople.setUuid(uuid.toString());
        wxServePeople.setPublishTime(new Date());
        //int checkSortNum = wxServePeopleMapper.checkSortNum(wxServePeople.getSortNum());
        //return checkSortNum == 0 ? toAjax(wxServePeopleService.insertWxServePeople(wxServePeople)) : AjaxResult.error("已存在重复的序号，请修改！");

        return toAjax(wxServePeopleService.insertWxServePeople(wxServePeople));
    }

    /**
     * 修改为民服务
     */
    //@PreAuthorize("@ss.hasPermi('system:serve:edit')")
    @Log(title = "为民服务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxServePeople wxServePeople)
    {
        wxServePeople.setUpdateTime(new Date());
        //WxServePeople w = wxServePeopleMapper.selectWxServePeopleByUuid(wxServePeople.getUuid());
        //int checkSortNum = wxServePeopleMapper.checkSortNum(wxServePeople.getSortNum());
        //if (w.getSortNum().equals(wxServePeople.getSortNum())){
        //    return toAjax(wxServePeopleService.updateWxServePeople(wxServePeople));
        //}else {
        //    return (checkSortNum == 0) ? toAjax(wxServePeopleService.updateWxServePeople(wxServePeople)) : AjaxResult.error("已存在重复的序号，请修改！");
        //}
        return toAjax(wxServePeopleService.updateWxServePeople(wxServePeople));
    }

    /**
     * 删除为民服务
     */
    //@PreAuthorize("@ss.hasPermi('system:serve:remove')")
    @Log(title = "为民服务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{uuids}")
    public AjaxResult remove(@PathVariable String[] uuids)
    {
        return toAjax(wxServePeopleService.deleteWxServePeopleByUuids(uuids));
    }
}
