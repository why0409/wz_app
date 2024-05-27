package com.ruoyi.web.controller.maintenance;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.WxMiniPrograms;
import com.ruoyi.system.mapper.WxMiniProgramsMapper;
import com.ruoyi.system.service.IWxMiniProgramsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 小程序模块Controller
 *
 * @author ruoyi
 * @date 2023-07-17
 */
@RestController
@RequestMapping("/system/programs")
public class WxMiniProgramsController extends BaseController
{
    @Autowired
    private IWxMiniProgramsService wxMiniProgramsService;

    @Autowired
    private WxMiniProgramsMapper wxMiniProgramsMapper;

    /**
     * 查询小程序模块列表
     */
    //@PreAuthorize("@ss.hasPermi('system:programs:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxMiniPrograms wxMiniPrograms)
    {
        startPage();
        List<WxMiniPrograms> list = wxMiniProgramsService.selectWxMiniProgramsList(wxMiniPrograms);
        return getDataTable(list);
    }

    /**
     * 导出小程序模块列表
     */
    //@PreAuthorize("@ss.hasPermi('system:programs:export')")
    @Log(title = "小程序模块", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxMiniPrograms wxMiniPrograms)
    {
        List<WxMiniPrograms> list = wxMiniProgramsService.selectWxMiniProgramsList(wxMiniPrograms);
        ExcelUtil<WxMiniPrograms> util = new ExcelUtil<WxMiniPrograms>(WxMiniPrograms.class);
        util.exportExcel(response, list, "小程序模块数据");
    }

    /**
     * 获取小程序模块详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:programs:query')")
    @GetMapping(value = "/{uuid}")
    public AjaxResult getInfo(@PathVariable("uuid") String uuid)
    {
        return success(wxMiniProgramsService.selectWxMiniProgramsByUuid(uuid));
    }

    /**
     * 新增小程序模块
     */
    //@PreAuthorize("@ss.hasPermi('system:programs:add')")
    @Log(title = "小程序模块", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxMiniPrograms wxMiniPrograms)
    {

        UUID uuid = UUID.randomUUID();
        wxMiniPrograms.setUuid(uuid.toString());
        wxMiniPrograms.setPublishTime(new Date());
        //int checkSortNum = wxMiniProgramsMapper.checkSortNum(wxMiniPrograms.getSortNum(),wxMiniPrograms.getType());
        //return checkSortNum == 0 ? toAjax(wxMiniProgramsService.insertWxMiniPrograms(wxMiniPrograms)) : AjaxResult.error("已存在重复的序号，请修改！");

        return toAjax(wxMiniProgramsService.insertWxMiniPrograms(wxMiniPrograms));
    }

    /**
     * 修改小程序模块
     */
    //@PreAuthorize("@ss.hasPermi('system:programs:edit')")
    @Log(title = "小程序模块", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxMiniPrograms wxMiniPrograms)
    {
        //WxMiniPrograms w = wxMiniProgramsMapper.selectWxMiniProgramsByUuid(wxMiniPrograms.getUuid());
        //int checkSortNum = wxMiniProgramsMapper.checkSortNum(wxMiniPrograms.getSortNum(),wxMiniPrograms.getType());
        //if (w.getSortNum().equals(wxMiniPrograms.getSortNum())){
        //    return toAjax(wxMiniProgramsService.updateWxMiniPrograms(wxMiniPrograms));
        //}else {
        //    return (checkSortNum == 0) ? toAjax(wxMiniProgramsService.updateWxMiniPrograms(wxMiniPrograms)) : AjaxResult.error("已存在重复的序号，请修改！");
        //}

        return toAjax(wxMiniProgramsService.updateWxMiniPrograms(wxMiniPrograms));
    }

    /**
     * 删除小程序模块
     */
    //@PreAuthorize("@ss.hasPermi('system:programs:remove')")
    @Log(title = "小程序模块", businessType = BusinessType.DELETE)
    @DeleteMapping("/{uuids}")
    public AjaxResult remove(@PathVariable String[] uuids)
    {
        return toAjax(wxMiniProgramsService.deleteWxMiniProgramsByUuids(uuids));
    }


}
