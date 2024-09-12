package com.ruoyi.web.controller.wz.maintenance;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.WxMarathon;
import com.ruoyi.system.mapper.WxMarathonMapper;
import com.ruoyi.system.service.IWxMarathonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 湾沚航空马拉松Controller
 *
 * @author ruoyi
 * @date 2023-03-20
 */
@RestController
@RequestMapping("/system/marathon")
public class WxMarathonController extends BaseController
{
    @Autowired
    private IWxMarathonService wxMarathonService;

    @Resource
    private WxMarathonMapper wxMarathonMapper;

    /**
     * 查询湾沚航空马拉松列表
     */
    //@PreAuthorize("@ss.hasPermi('system:marathon:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxMarathon wxMarathon)
    {
        startPage();
        List<WxMarathon> list = wxMarathonService.selectWxMarathonList(wxMarathon);
        return getDataTable(list);
    }

    /**
     * 导出湾沚航空马拉松列表
     */
    //@PreAuthorize("@ss.hasPermi('system:marathon:export')")
    @Log(title = "湾沚航空马拉松", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxMarathon wxMarathon)
    {
        List<WxMarathon> list = wxMarathonService.selectWxMarathonList(wxMarathon);
        ExcelUtil<WxMarathon> util = new ExcelUtil<WxMarathon>(WxMarathon.class);
        util.exportExcel(response, list, "湾沚航空马拉松数据");
    }

    /**
     * 获取湾沚航空马拉松详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:marathon:query')")
    @GetMapping(value = "/{uuid}")
    public AjaxResult getInfo(@PathVariable("uuid") String uuid)
    {
        return success(wxMarathonService.selectWxMarathonByUuid(uuid));
    }

    /**
     * 新增湾沚航空马拉松
     */
    //@PreAuthorize("@ss.hasPermi('system:marathon:add')")
    @Log(title = "湾沚航空马拉松", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxMarathon wxMarathon)
    {
        UUID uuid = UUID.randomUUID();
        wxMarathon.setUuid(uuid.toString());
        wxMarathon.setPublishTime(new Date());
        //int checkSortNum = wxMarathonMapper.checkSortNum(wxMarathon.getSortNum());
        //return checkSortNum == 0 ? toAjax(wxMarathonService.insertWxMarathon(wxMarathon)) : AjaxResult.error("已存在重复的序号，请修改！");

        return toAjax(wxMarathonService.insertWxMarathon(wxMarathon));
    }

    /**
     * 修改湾沚航空马拉松
     */
    //@PreAuthorize("@ss.hasPermi('system:marathon:edit')")
    @Log(title = "湾沚航空马拉松", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxMarathon wxMarathon)
    {
        wxMarathon.setUpdateTime(new Date());
        //WxMarathon w = wxMarathonMapper.selectWxMarathonByUuid(wxMarathon.getUuid());
        //int checkSortNum = wxMarathonMapper.checkSortNum(wxMarathon.getSortNum());
        //if (w.getSortNum().equals(wxMarathon.getSortNum())){
        //    return toAjax(wxMarathonService.updateWxMarathon(wxMarathon));
        //}else {
        //    return (checkSortNum == 0) ? toAjax(wxMarathonService.updateWxMarathon(wxMarathon)) : AjaxResult.error("已存在重复的序号，请修改！");
        //}

        return toAjax(wxMarathonService.updateWxMarathon(wxMarathon));
    }

    /**
     * 删除湾沚航空马拉松
     */
    //@PreAuthorize("@ss.hasPermi('system:marathon:remove')")
    @Log(title = "湾沚航空马拉松", businessType = BusinessType.DELETE)
    @DeleteMapping("/{uuids}")
    public AjaxResult remove(@PathVariable String[] uuids)
    {
        return toAjax(wxMarathonService.deleteWxMarathonByUuids(uuids));
    }
}

