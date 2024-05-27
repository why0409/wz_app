package com.ruoyi.web.controller.app;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.app.domain.HkxcPromotionalVideo;
import com.ruoyi.app.service.IHkxcPromotionalVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 航空新城-宣传片Controller
 *
 * @author ruoyi
 * @date 2024-05-07
 */
@RestController
@RequestMapping("/hkxcPromotionalVideo/video")
public class HkxcPromotionalVideoController extends BaseController
{
    @Autowired
    private IHkxcPromotionalVideoService hkxcPromotionalVideoService;

    /**
     * 查询航空新城-宣传片列表
     */
    //@PreAuthorize("@ss.hasPermi('hkxcPromotionalVideo:video:list')")
    @GetMapping("/list")
    public TableDataInfo list(HkxcPromotionalVideo hkxcPromotionalVideo)
    {
        startPage();
        List<HkxcPromotionalVideo> list = hkxcPromotionalVideoService.selectHkxcPromotionalVideoList(hkxcPromotionalVideo);
        return getDataTable(list);
    }

    /**
     * 导出航空新城-宣传片列表
     */
    //@PreAuthorize("@ss.hasPermi('hkxcPromotionalVideo:video:export')")
    @Log(title = "航空新城-宣传片", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HkxcPromotionalVideo hkxcPromotionalVideo)
    {
        List<HkxcPromotionalVideo> list = hkxcPromotionalVideoService.selectHkxcPromotionalVideoList(hkxcPromotionalVideo);
        ExcelUtil<HkxcPromotionalVideo> util = new ExcelUtil<HkxcPromotionalVideo>(HkxcPromotionalVideo.class);
        util.exportExcel(response, list, "航空新城-宣传片数据");
    }

    /**
     * 获取航空新城-宣传片详细信息
     */
    //@PreAuthorize("@ss.hasPermi('hkxcPromotionalVideo:video:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(hkxcPromotionalVideoService.selectHkxcPromotionalVideoById(id));
    }

    /**
     * 新增航空新城-宣传片
     */
    //@PreAuthorize("@ss.hasPermi('hkxcPromotionalVideo:video:add')")
    @Log(title = "航空新城-宣传片", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HkxcPromotionalVideo hkxcPromotionalVideo)
    {
        return toAjax(hkxcPromotionalVideoService.insertHkxcPromotionalVideo(hkxcPromotionalVideo));
    }

    /**
     * 修改航空新城-宣传片
     */
    //@PreAuthorize("@ss.hasPermi('hkxcPromotionalVideo:video:edit')")
    @Log(title = "航空新城-宣传片", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HkxcPromotionalVideo hkxcPromotionalVideo)
    {
        return toAjax(hkxcPromotionalVideoService.updateHkxcPromotionalVideo(hkxcPromotionalVideo));
    }

    /**
     * 删除航空新城-宣传片
     */
    //@PreAuthorize("@ss.hasPermi('hkxcPromotionalVideo:video:remove')")
    @Log(title = "航空新城-宣传片", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(hkxcPromotionalVideoService.deleteHkxcPromotionalVideoByIds(ids));
    }
}

