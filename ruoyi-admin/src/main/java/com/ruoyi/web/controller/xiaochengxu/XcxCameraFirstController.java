package com.ruoyi.web.controller.xiaochengxu;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.xcx.domain.XcxCameraType;
import com.ruoyi.xcx.service.IXcxCameraFirstService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 小程序---分类Controller
 *
 * @author ruoyi
 * @date 2023-10-16
 */
@RestController
@RequestMapping("/xcx/cameraFirst")
@Api(tags = "小程序分类管理")
public class XcxCameraFirstController extends BaseController
{


    @Autowired
    private IXcxCameraFirstService xcxCameraFirstService;

    /**
     * 查询小程序---分类列表
     */
//    @PreAuthorize("@ss.hasPermi('system:type:list')")
    @ApiOperation("分类列表")
    @GetMapping("/list")
    public TableDataInfo list(XcxCameraType xcxCameraType)
    {
        startPage();
        List<XcxCameraType> list = xcxCameraFirstService.selectXcxCameraTypeList(xcxCameraType);
        return getDataTable(list);
    }

    /**
     * 导出小程序---分类列表
     */
//    @PreAuthorize("@ss.hasPermi('system:type:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, XcxCameraType xcxCameraType)
    {
        List<XcxCameraType> list = xcxCameraFirstService.selectXcxCameraTypeList(xcxCameraType);
        ExcelUtil<XcxCameraType> util = new ExcelUtil<>(XcxCameraType.class);
        util.exportExcel(response, list, "小程序---分类数据");
    }

    /**
     * 获取小程序---分类详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:type:query')")
    @ApiOperation("根据id查看分类详情")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(xcxCameraFirstService.selectXcxCameraTypeById(id));
    }

    /**
     * 新增小程序---分类
     */
//    @PreAuthorize("@ss.hasPermi('system:type:add')")
    @ApiOperation("新增分类")
    @PostMapping
    public AjaxResult add(@RequestBody XcxCameraType xcxCameraType)
    {
        return xcxCameraFirstService.insertXcxCameraType(xcxCameraType);
    }

    /**
     * 修改小程序---分类
     */
//    @PreAuthorize("@ss.hasPermi('system:type:edit')")
    @ApiOperation("修改分类")
    @PutMapping
    public AjaxResult edit(@RequestBody XcxCameraType xcxCameraType)
    {
        return xcxCameraFirstService.updateXcxCameraType(xcxCameraType);
    }

    /**
     * 删除小程序---分类
     */
//    @PreAuthorize("@ss.hasPermi('system:type:remove')")
    @ApiOperation("根据id集合删除分类")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xcxCameraFirstService.deleteXcxCameraTypeByIds(ids));
    }

    @ApiOperation("获取所有类型--栏目")
    @GetMapping("/getAllTypeAndColumn")
    public AjaxResult getAllTypeAndColumn(){
        return success(xcxCameraFirstService.getAllTypeAndColumn());
    }
}
