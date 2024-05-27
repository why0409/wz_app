package com.ruoyi.web.controller.maintenance;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.WxNews;
import com.ruoyi.system.mapper.WxNewsMapper;
import com.ruoyi.system.service.IWxNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 【最近动态】Controller
 *
 * @author ruoyi
 * @date 2022-12-01
 */
@RestController
@RequestMapping("/system/news")
public class WxNewsController extends BaseController
{
    @Autowired
    private IWxNewsService wxNewsService;

    @Resource
    private WxNewsMapper wxNewsMapper;

    /**
     * 查询【最近动态】列表
     */
    //@PreAuthorize("@ss.hasPermi('system:news:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxNews wxNews)
    {
        startPage();
        List<WxNews> list = wxNewsService.selectWxNewsList(wxNews);
        return getDataTable(list);
    }

    /**
     * 导出【最近动态】列表
     */
    //@PreAuthorize("@ss.hasPermi('system:news:export')")
    @Log(title = "最近动态导出", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxNews wxNews)
    {
        List<WxNews> list = wxNewsService.selectWxNewsList(wxNews);
        ExcelUtil<WxNews> util = new ExcelUtil<WxNews>(WxNews.class);
        util.exportExcel(response, list, "【最近动态】数据");
    }

    /**
     * 获取【最近动态】详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:news:query')")
    @GetMapping(value = "/{uuid}")
    public AjaxResult getInfo(@PathVariable("uuid") String uuid)
    {
        return success(wxNewsService.selectWxNewsByUuid(uuid));
    }

    /**
     * 新增【最近动态】
     */
    //@PreAuthorize("@ss.hasPermi('system:news:add')")
    @Log(title = "最近动态新增", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxNews wxNews)
    {
        UUID uuid = UUID.randomUUID();
        wxNews.setUuid(uuid.toString());
        wxNews.setPublishTime(new Date());
        //int checkSortNum = wxNewsMapper.checkSortNum(wxNews.getSortNum());
        //return checkSortNum == 0 ? toAjax(wxNewsService.insertWxNews(wxNews)) : AjaxResult.error("已存在重复的序号，请修改！");
        return toAjax(wxNewsService.insertWxNews(wxNews));
    }

    /**
     * 修改【最近动态】
     */
    //@PreAuthorize("@ss.hasPermi('system:news:edit')")
    @Log(title = "最近动态更新", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxNews wxNews)
    {
        wxNews.setUpdateTime(new Date());
        //WxNews w = wxNewsMapper.selectWxNewsByUuid(wxNews.getUuid());
        //int checkSortNum = wxNewsMapper.checkSortNum(wxNews.getSortNum());
        //if (w.getSortNum().equals(wxNews.getSortNum())){
        //    return toAjax(wxNewsService.updateWxNews(wxNews));
        //}else {
        //    return (checkSortNum == 0) ? toAjax(wxNewsService.updateWxNews(wxNews)) : AjaxResult.error("已存在重复的序号，请修改！");
        //}

        return toAjax(wxNewsService.updateWxNews(wxNews));
    }

    /**
     * 删除【最近动态】
     */
    //@PreAuthorize("@ss.hasPermi('system:news:remove')")
    @Log(title = "最近动态删除", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uuids}")
    public AjaxResult remove(@PathVariable String[] uuids)
    {
        return toAjax(wxNewsService.deleteWxNewsByUuids(uuids));
    }

}


