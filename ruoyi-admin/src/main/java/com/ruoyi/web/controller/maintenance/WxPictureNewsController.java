package com.ruoyi.web.controller.maintenance;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.WxPictureNews;
import com.ruoyi.system.mapper.WxPictureNewsMapper;
import com.ruoyi.system.service.IWxPictureNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 图片新闻Controller
 *
 * @author ruoyi
 * @date 2023-02-08
 */
@RestController
@RequestMapping("/system/pictureNews")
public class WxPictureNewsController extends BaseController
{
    @Autowired
    private IWxPictureNewsService wxPictureNewsService;

    @Resource
    private WxPictureNewsMapper wxPictureNewsMapper;

    /**
     * 查询图片新闻列表
     */
//    @PreAuthorize("@ss.hasPermi('system:pictureNews:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxPictureNews wxPictureNews)
    {
        startPage();
        List<WxPictureNews> list = wxPictureNewsService.selectWxPictureNewsList(wxPictureNews);
        return getDataTable(list);
    }

    /**
     * 导出图片新闻列表
     */
    //@PreAuthorize("@ss.hasPermi('system:pictureNews:export')")
    @Log(title = "图片新闻", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxPictureNews wxPictureNews)
    {
        List<WxPictureNews> list = wxPictureNewsService.selectWxPictureNewsList(wxPictureNews);
        ExcelUtil<WxPictureNews> util = new ExcelUtil<WxPictureNews>(WxPictureNews.class);
        util.exportExcel(response, list, "图片新闻数据");
    }

    /**
     * 获取图片新闻详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:pictureNews:query')")
    @GetMapping(value = "/{uuid}")
    public AjaxResult getInfo(@PathVariable("uuid") String uuid)
    {
        return AjaxResult.success(wxPictureNewsService.selectWxPictureNewsByUuid(uuid));
    }

    /**
     * 新增图片新闻
     */
    //@PreAuthorize("@ss.hasPermi('system:pictureNews:add')")
    @Log(title = "图片新闻", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxPictureNews wxPictureNews)
    {
        UUID uuid = UUID.randomUUID();
        wxPictureNews.setUuid(uuid.toString());
        wxPictureNews.setPublishTime(new Date());
        //int checkSortNum = wxPictureNewsMapper.checkSortNum(wxPictureNews.getSortNum());
        //return checkSortNum == 0 ? toAjax(wxPictureNewsService.insertWxPictureNews(wxPictureNews)) : AjaxResult.error("已存在重复的序号，请修改！");
        return toAjax(wxPictureNewsService.insertWxPictureNews(wxPictureNews));
    }

    /**
     * 修改图片新闻
     */
    //@PreAuthorize("@ss.hasPermi('system:pictureNews:edit')")
    @Log(title = "图片新闻", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxPictureNews wxPictureNews)
    {
        wxPictureNews.setUpdateTime(new Date());
        //WxPictureNews w = wxPictureNewsMapper.selectWxPictureNewsByUuid(wxPictureNews.getUuid());
        //int checkSortNum = wxPictureNewsMapper.checkSortNum(wxPictureNews.getSortNum());
        //if (w.getSortNum().equals(wxPictureNews.getSortNum())){
        //    return toAjax(wxPictureNewsService.updateWxPictureNews(wxPictureNews));
        //}else {
        //    return (checkSortNum == 0) ? toAjax(wxPictureNewsService.updateWxPictureNews(wxPictureNews)) : AjaxResult.error("已存在重复的序号，请修改！");
        //}
        return toAjax(wxPictureNewsService.updateWxPictureNews(wxPictureNews));
    }

    /**
     * 删除图片新闻
     */
    //@PreAuthorize("@ss.hasPermi('system:pictureNews:remove')")
    @Log(title = "图片新闻", businessType = BusinessType.DELETE)
    @DeleteMapping("/{uuids}")
    public AjaxResult remove(@PathVariable String[] uuids)
    {
        return toAjax(wxPictureNewsService.deleteWxPictureNewsByUuids(uuids));
    }
}

