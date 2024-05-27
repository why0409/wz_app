package com.ruoyi.web.controller.xiaochengxu;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.xcx.domain.XcxMessage;
import com.ruoyi.xcx.service.IXcxMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 一张图留言Controller
 *
 * @author ruoyi
 * @date 2023-11-08
 */
@RestController
@RequestMapping("/xcx/message")
public class XcxMessageController extends BaseController
{
    @Autowired
    private IXcxMessageService xcxMessageService;

    /**
     * 查询一张图留言列表
     */
    //@PreAuthorize("@ss.hasPermi('xcx:message:list')")
    @GetMapping("/list")
    public TableDataInfo list(XcxMessage xcxMessage)
    {
        startPage();
        List<XcxMessage> list = xcxMessageService.selectXcxMessageList(xcxMessage);
        return getDataTable(list);
    }

    /**
     * 导出一张图留言列表
     */
    //@PreAuthorize("@ss.hasPermi('xcx:message:export')")
    @Log(title = "一张图留言", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XcxMessage xcxMessage)
    {
        List<XcxMessage> list = xcxMessageService.selectXcxMessageList(xcxMessage);
        ExcelUtil<XcxMessage> util = new ExcelUtil<XcxMessage>(XcxMessage.class);
        util.exportExcel(response, list, "一张图留言数据");
    }

    /**
     * 获取一张图留言详细信息
     */
    //@PreAuthorize("@ss.hasPermi('xcx:message:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(xcxMessageService.selectXcxMessageById(id));
    }

    /**
     * 新增一张图留言
     */
    //@PreAuthorize("@ss.hasPermi('xcx:message:add')")
    @Log(title = "一张图留言", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XcxMessage xcxMessage)
    {
        return toAjax(xcxMessageService.insertXcxMessage(xcxMessage));
    }

    /**
     * 修改一张图留言
     */
    //@PreAuthorize("@ss.hasPermi('xcx:message:edit')")
    @Log(title = "一张图留言", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XcxMessage xcxMessage)
    {
        return toAjax(xcxMessageService.updateXcxMessage(xcxMessage));
    }

    /**
     * 删除一张图留言
     */
    //@PreAuthorize("@ss.hasPermi('xcx:message:remove')")
    @Log(title = "一张图留言", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xcxMessageService.deleteXcxMessageByIds(ids));
    }
}
