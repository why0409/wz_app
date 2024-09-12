package com.ruoyi.web.controller.wz.onePicture;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.onePicture.domain.XcxContent;
import com.ruoyi.onePicture.service.IXcxContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 小程序_内容详情Controller
 *
 * @author ruoyi
 * @date 2023-10-16
 */
@RestController
@RequestMapping("/xcx/content")
@Api(tags = "内容业务管理")
public class XcxContentController extends BaseController
{
    @Autowired
    private IXcxContentService xcxContentService;


    /**
     * 查询小程序_内容详情列表
     */
//    @PreAuthorize("@ss.hasPermi('system:content:list')")
    @ApiOperation("内容列表")
    @GetMapping("/list")
    public TableDataInfo list(XcxContent xcxContent)
    {
        startPage();
        List<XcxContent> list = xcxContentService.selectXcxContentList(xcxContent);
        return getDataTable(list);
    }

    /**
     * 导出小程序_内容详情列表
     */
//    @PreAuthorize("@ss.hasPermi('system:content:export')")
    @Log(title = "小程序_内容详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XcxContent xcxContent)
    {
        List<XcxContent> list = xcxContentService.selectXcxContentList(xcxContent);
        ExcelUtil<XcxContent> util = new ExcelUtil<XcxContent>(XcxContent.class);
        util.exportExcel(response, list, "小程序_内容详情数据");
    }

    /**
     * 获取小程序_内容详情详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:content:query')")
    @ApiOperation("根据内容id查看内容详情")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(xcxContentService.selectXcxContentById(id));
    }

    /**
     * 新增小程序_内容详情
     */
//    @PreAuthorize("@ss.hasPermi('system:content:add')")
    @ApiOperation("新增内容")
    @PostMapping
    public AjaxResult add(@RequestBody XcxContent xcxContent)
    {
        return xcxContentService.insertXcxContent(xcxContent);
    }

    /**
     * 修改小程序_内容详情
     */
//    @PreAuthorize("@ss.hasPermi('system:content:edit')")
    @ApiOperation("修改内容详情")
    @PutMapping
    public AjaxResult edit(@RequestBody XcxContent xcxContent)
    {
        return xcxContentService.updateXcxContent(xcxContent);
    }

    /**
     * 删除小程序_内容详情
     */
//    @PreAuthorize("@ss.hasPermi('system:content:remove')")
    @ApiOperation("删除内容详情")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xcxContentService.deleteXcxContentByIds(ids));
    }


    @ApiOperation("根据栏目id获取poi")
    @GetMapping("/selectByColumnId")
    public TableDataInfo selectByColumnId(Integer columnId, String contentName){
        startPage();
        List<XcxContent> list = xcxContentService.selectByColumnId(columnId,contentName,null);
        return getDataTable(list);
    }

}
