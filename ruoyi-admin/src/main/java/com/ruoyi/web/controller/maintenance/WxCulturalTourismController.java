package com.ruoyi.web.controller.maintenance;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.WxCulturalTourism;
import com.ruoyi.system.mapper.WxCulturalTourismMapper;
import com.ruoyi.system.service.IWxCulturalTourismService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 本地文旅Controller
 *
 * @author ruoyi
 * @date 2023-06-19
 */
@RestController
@RequestMapping("/system/tourism")
public class WxCulturalTourismController extends BaseController
{
    @Autowired
    private IWxCulturalTourismService wxCulturalTourismService;

    @Autowired
    private WxCulturalTourismMapper wxCulturalTourismMapper;

    /**
     * 查询本地文旅列表
     */
    //@PreAuthorize("@ss.hasPermi('system:tourism:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxCulturalTourism wxCulturalTourism)
    {
        startPage();
        List<WxCulturalTourism> list = wxCulturalTourismService.selectWxCulturalTourismList(wxCulturalTourism);
        return getDataTable(list);
    }

    /**
     * 导出本地文旅列表
     */
    //@PreAuthorize("@ss.hasPermi('system:tourism:export')")
    @Log(title = "本地文旅", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxCulturalTourism wxCulturalTourism)
    {
        List<WxCulturalTourism> list = wxCulturalTourismService.selectWxCulturalTourismList(wxCulturalTourism);
        ExcelUtil<WxCulturalTourism> util = new ExcelUtil<WxCulturalTourism>(WxCulturalTourism.class);
        util.exportExcel(response, list, "本地文旅数据");
    }

    /**
     * 获取本地文旅详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:tourism:query')")
    @GetMapping(value = "/{uuid}")
    public AjaxResult getInfo(@PathVariable("uuid") String uuid)
    {
        return success(wxCulturalTourismService.selectWxCulturalTourismByUuid(uuid));
    }

    /**
     * 新增本地文旅
     */
    //@PreAuthorize("@ss.hasPermi('system:tourism:add')")
    @Log(title = "本地文旅", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxCulturalTourism wxCulturalTourism)
    {
        UUID uuid = UUID.randomUUID();
        wxCulturalTourism.setUuid(uuid.toString());
        wxCulturalTourism.setPublishTime(new Date());
        //int checkSortNum = wxCulturalTourismMapper.checkSortNum(wxCulturalTourism.getSortNum());
        //return checkSortNum == 0 ? toAjax(wxCulturalTourismService.insertWxCulturalTourism(wxCulturalTourism)) : AjaxResult.error("已存在重复的序号，请修改！");

        return toAjax(wxCulturalTourismService.insertWxCulturalTourism(wxCulturalTourism));
    }

    /**
     * 修改本地文旅
     */
    //@PreAuthorize("@ss.hasPermi('system:tourism:edit')")
    @Log(title = "本地文旅", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxCulturalTourism wxCulturalTourism)
    {
        //WxCulturalTourism w = wxCulturalTourismMapper.selectWxCulturalTourismByUuid(wxCulturalTourism.getUuid());
        //int checkSortNum = wxCulturalTourismMapper.checkSortNum(wxCulturalTourism.getSortNum());
        //if (w.getSortNum().equals(wxCulturalTourism.getSortNum())){
        //    return toAjax(wxCulturalTourismService.updateWxCulturalTourism(wxCulturalTourism));
        //}else {
        //    return (checkSortNum == 0) ? toAjax(wxCulturalTourismService.updateWxCulturalTourism(wxCulturalTourism)) : AjaxResult.error("已存在重复的序号，请修改！");
        //}
        return toAjax(wxCulturalTourismService.updateWxCulturalTourism(wxCulturalTourism));
    }

    /**
     * 删除本地文旅
     */
    //@PreAuthorize("@ss.hasPermi('system:tourism:remove')")
    @Log(title = "本地文旅", businessType = BusinessType.DELETE)
    @DeleteMapping("/{uuids}")
    public AjaxResult remove(@PathVariable String[] uuids)
    {
        return toAjax(wxCulturalTourismService.deleteWxCulturalTourismByUuids(uuids));
    }
}

