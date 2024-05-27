package com.ruoyi.web.controller.maintenance;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.WxHealthy;
import com.ruoyi.system.mapper.WxHealthyMapper;
import com.ruoyi.system.service.IWxHealthyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 健康板块Controller
 *
 * @author ruoyi
 * @date 2023-05-19
 */
@RestController
@RequestMapping("/system/healthy")
public class WxHealthyController extends BaseController
{
    @Autowired
    private IWxHealthyService wxHealthyService;

    @Resource
    private WxHealthyMapper wxHealthyMapper;

    /**
     * 查询健康板块列表
     */
    //@PreAuthorize("@ss.hasPermi('system:healthy:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxHealthy wxHealthy)
    {
        startPage();
        List<WxHealthy> list = wxHealthyService.selectWxHealthyList(wxHealthy);
        return getDataTable(list);
    }

    /**
     * 导出健康板块列表
     */
    //@PreAuthorize("@ss.hasPermi('system:healthy:export')")
    @Log(title = "健康板块", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxHealthy wxHealthy)
    {
        List<WxHealthy> list = wxHealthyService.selectWxHealthyList(wxHealthy);
        ExcelUtil<WxHealthy> util = new ExcelUtil<WxHealthy>(WxHealthy.class);
        util.exportExcel(response, list, "健康板块数据");
    }

    /**
     * 获取健康板块详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:healthy:query')")
    @GetMapping(value = "/{uuid}")
    public AjaxResult getInfo(@PathVariable("uuid") String uuid)
    {
        return success(wxHealthyService.selectWxHealthyByUuid(uuid));
    }

    /**
     * 新增健康板块
     */
    //@PreAuthorize("@ss.hasPermi('system:healthy:add')")
    @Log(title = "健康板块", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxHealthy wxHealthy)
    {
        UUID uuid = UUID.randomUUID();
        wxHealthy.setUuid(uuid.toString());
        wxHealthy.setPublishTime(new Date());
        //int checkSortNum = wxHealthyMapper.checkSortNum(wxHealthy.getSortNum());
        //return checkSortNum == 0 ? toAjax(wxHealthyService.insertWxHealthy(wxHealthy)) : AjaxResult.error("已存在重复的序号，请修改！");
        return toAjax(wxHealthyService.insertWxHealthy(wxHealthy));
    }

    /**
     * 修改健康板块
     */
    //@PreAuthorize("@ss.hasPermi('system:healthy:edit')")
    @Log(title = "健康板块", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxHealthy wxHealthy)
    {
        wxHealthy.setUpdateTime(new Date());
        //WxHealthy w = wxHealthyMapper.selectWxHealthyByUuid(wxHealthy.getUuid());
        //int checkSortNum = wxHealthyMapper.checkSortNum(wxHealthy.getSortNum());
        //if (w.getSortNum().equals(wxHealthy.getSortNum())){
        //    return toAjax(wxHealthyService.updateWxHealthy(wxHealthy));
        //}else {
        //    return (checkSortNum == 0) ? toAjax(wxHealthyService.updateWxHealthy(wxHealthy)) : AjaxResult.error("已存在重复的序号，请修改！");
        //}
        return toAjax(wxHealthyService.updateWxHealthy(wxHealthy));
    }

    /**
     * 删除健康板块
     */
    //@PreAuthorize("@ss.hasPermi('system:healthy:remove')")
    @Log(title = "健康板块", businessType = BusinessType.DELETE)
    @DeleteMapping("/{uuids}")
    public AjaxResult remove(@PathVariable String[] uuids)
    {
        return toAjax(wxHealthyService.deleteWxHealthyByUuids(uuids));
    }
}

