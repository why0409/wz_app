package com.ruoyi.web.controller.wz.app;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.app.domain.HmwzVideo;
import com.ruoyi.app.service.IHmwzVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 和美湾沚-视频管理Controller
 *
 * @author ruoyi
 * @date 2024-05-06
 */
@RestController
@RequestMapping("/hmwz/video")
public class HmwzVideoController extends BaseController
{
    @Autowired
    private IHmwzVideoService hmwzVideoService;

    /**
     * 查询和美湾沚-视频管理列表
     */
    //@PreAuthorize("@ss.hasPermi('hmwz:video:list')")
    @GetMapping("/list")
    public TableDataInfo list(HmwzVideo hmwzVideo)
    {
        startPage();
        List<HmwzVideo> list = hmwzVideoService.selectHmwzVideoList(hmwzVideo);
        return getDataTable(list);
    }

    /**
     * 导出和美湾沚-视频管理列表
     */
    //@PreAuthorize("@ss.hasPermi('hmwz:video:export')")
    @Log(title = "和美湾沚-视频管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HmwzVideo hmwzVideo)
    {
        List<HmwzVideo> list = hmwzVideoService.selectHmwzVideoList(hmwzVideo);
        ExcelUtil<HmwzVideo> util = new ExcelUtil<HmwzVideo>(HmwzVideo.class);
        util.exportExcel(response, list, "和美湾沚-视频管理数据");
    }

    /**
     * 获取和美湾沚-视频管理详细信息
     */
    //@PreAuthorize("@ss.hasPermi('hmwz:video:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(hmwzVideoService.selectHmwzVideoById(id));
    }

    /**
     * 新增和美湾沚-视频管理
     */
    //@PreAuthorize("@ss.hasPermi('hmwz:video:add')")
    @Log(title = "和美湾沚-视频管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HmwzVideo hmwzVideo)
    {
        return toAjax(hmwzVideoService.insertHmwzVideo(hmwzVideo));
    }

    /**
     * 修改和美湾沚-视频管理
     */
    //@PreAuthorize("@ss.hasPermi('hmwz:video:edit')")
    @Log(title = "和美湾沚-视频管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HmwzVideo hmwzVideo)
    {
        return toAjax(hmwzVideoService.updateHmwzVideo(hmwzVideo));
    }

    /**
     * 删除和美湾沚-视频管理
     */
    //@PreAuthorize("@ss.hasPermi('hmwz:video:remove')")
    @Log(title = "和美湾沚-视频管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(hmwzVideoService.deleteHmwzVideoByIds(ids));
    }
}
