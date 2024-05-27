package com.ruoyi.web.controller.xiaochengxu;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.xcx.domain.XcxCameraContent;
import com.ruoyi.xcx.service.IXcxCameraThirdService;
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
@RequestMapping("/xcx/cameraThird")
@Api(tags = "内容业务管理")
public class XcxCameraThirdController extends BaseController
{
    @Autowired
    private IXcxCameraThirdService xcxCameraThirdService;


    /**
     * 查询小程序_内容详情列表
     */
//    @PreAuthorize("@ss.hasPermi('system:content:list')")
    @ApiOperation("内容列表")
    @GetMapping("/list")
    public TableDataInfo list(XcxCameraContent xcxCameraContent)
    {
        startPage();
        List<XcxCameraContent> list = xcxCameraThirdService.selectXcxCameraContentList(xcxCameraContent);
        return getDataTable(list);
    }

    /**
     * 导出小程序_内容详情列表
     */
//    @PreAuthorize("@ss.hasPermi('system:content:export')")
    @Log(title = "小程序_内容详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XcxCameraContent xcxCameraContent)
    {
        List<XcxCameraContent> list = xcxCameraThirdService.selectXcxCameraContentList(xcxCameraContent);
        ExcelUtil<XcxCameraContent> util = new ExcelUtil<>(XcxCameraContent.class);
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
        return success(xcxCameraThirdService.selectXcxCameraContentById(id));
    }

    /**
     * 新增小程序_内容详情
     */
//    @PreAuthorize("@ss.hasPermi('system:content:add')")
    @ApiOperation("新增内容")
    @PostMapping
    public AjaxResult add(@RequestBody XcxCameraContent xcxCameraContent)
    {
        return xcxCameraThirdService.insertXcxCameraContent(xcxCameraContent);
    }

    /**
     * 修改小程序_内容详情
     */
//    @PreAuthorize("@ss.hasPermi('system:content:edit')")
    @ApiOperation("修改内容详情")
    @PutMapping
    public AjaxResult edit(@RequestBody XcxCameraContent xcxCameraContent)
    {
        return xcxCameraThirdService.updateXcxCameraContent(xcxCameraContent);
    }

    /**
     * 删除小程序_内容详情
     */
//    @PreAuthorize("@ss.hasPermi('system:content:remove')")
    @ApiOperation("删除内容详情")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xcxCameraThirdService.deleteXcxCameraContentByIds(ids));
    }


    @ApiOperation("根据栏目id获取poi")
    @GetMapping("/selectByColumnId")
    public TableDataInfo selectByColumnId(Integer columnId, String contentName, String dept, String operators,Integer searchType){
        startPage();
        List<XcxCameraContent> list = xcxCameraThirdService.selectByColumnId(columnId,contentName,null,dept,operators,searchType);
        return getDataTable(list);
    }

    @GetMapping("/selectDistinctDept")
    public AjaxResult selectDistinctDept()
    {
        return success(xcxCameraThirdService.selectDistinctDept());
    }

}
