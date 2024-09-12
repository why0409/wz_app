package com.ruoyi.web.controller.wz.maintenance;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.WxPictureAppreciate;
import com.ruoyi.system.mapper.WxPictureAppreciateMapper;
import com.ruoyi.system.service.IWxPictureAppreciateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 图片欣赏Controller
 *
 * @author ruoyi
 * @date 2023-02-08
 */
@RestController
@RequestMapping("/system/pictureAppreciate")
public class WxPictureAppreciateController extends BaseController
{
    @Autowired
    private IWxPictureAppreciateService wxPictureAppreciateService;

    @Resource
    private WxPictureAppreciateMapper wxPictureAppreciateMapper;

    /**
     * 查询图片欣赏列表
     */
//    @PreAuthorize("@ss.hasPermi('system:appreciate:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxPictureAppreciate wxPictureAppreciate)
    {
        startPage();
        List<WxPictureAppreciate> list = wxPictureAppreciateService.selectWxPictureAppreciateList(wxPictureAppreciate);
        return getDataTable(list);
    }

    /**
     * 导出图片欣赏列表
     */
    //@PreAuthorize("@ss.hasPermi('system:appreciate:export')")
    @Log(title = "图片欣赏", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxPictureAppreciate wxPictureAppreciate)
    {
        List<WxPictureAppreciate> list = wxPictureAppreciateService.selectWxPictureAppreciateList(wxPictureAppreciate);
        ExcelUtil<WxPictureAppreciate> util = new ExcelUtil<WxPictureAppreciate>(WxPictureAppreciate.class);
        util.exportExcel(response, list, "图片欣赏数据");
    }

    /**
     * 获取图片欣赏详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:appreciate:query')")
    @GetMapping(value = "/{uuid}")
    public AjaxResult getInfo(@PathVariable("uuid") String uuid)
    {
        return AjaxResult.success(wxPictureAppreciateService.selectWxPictureAppreciateByUuid(uuid));
    }

    /**
     * 新增图片欣赏
     */
    //@PreAuthorize("@ss.hasPermi('system:appreciate:add')")
    @Log(title = "图片欣赏", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxPictureAppreciate wxPictureAppreciate)
    {
        UUID uuid = UUID.randomUUID();
        wxPictureAppreciate.setUuid(uuid.toString());
        wxPictureAppreciate.setPublishTime(new Date());
        //int checkSortNum = wxPictureAppreciateMapper.checkSortNum(wxPictureAppreciate.getSortNum());
        //return checkSortNum == 0 ? toAjax(wxPictureAppreciateService.insertWxPictureAppreciate(wxPictureAppreciate)) : AjaxResult.error("已存在重复的序号，请修改！");

        return toAjax(wxPictureAppreciateService.insertWxPictureAppreciate(wxPictureAppreciate));
    }

    /**
     * 修改图片欣赏
     */
    //@PreAuthorize("@ss.hasPermi('system:appreciate:edit')")
    @Log(title = "图片欣赏", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxPictureAppreciate wxPictureAppreciate)
    {
        wxPictureAppreciate.setUpdateTime(new Date());
        //WxPictureAppreciate w = wxPictureAppreciateMapper.selectWxPictureAppreciateByUuid(wxPictureAppreciate.getUuid());
        //int checkSortNum = wxPictureAppreciateMapper.checkSortNum(wxPictureAppreciate.getSortNum());
        //if (w.getSortNum().equals(wxPictureAppreciate.getSortNum())){
        //    return toAjax(wxPictureAppreciateService.updateWxPictureAppreciate(wxPictureAppreciate));
        //}else {
        //    return (checkSortNum == 0) ? toAjax(wxPictureAppreciateService.updateWxPictureAppreciate(wxPictureAppreciate)) : AjaxResult.error("已存在重复的序号，请修改！");
        //}
        return toAjax(wxPictureAppreciateService.updateWxPictureAppreciate(wxPictureAppreciate));
    }

    /**
     * 删除图片欣赏
     */
    //@PreAuthorize("@ss.hasPermi('system:appreciate:remove')")
    @Log(title = "图片欣赏", businessType = BusinessType.DELETE)
    @DeleteMapping("/{uuids}")
    public AjaxResult remove(@PathVariable String[] uuids)
    {
        return toAjax(wxPictureAppreciateService.deleteWxPictureAppreciateByUuids(uuids));
    }
}
