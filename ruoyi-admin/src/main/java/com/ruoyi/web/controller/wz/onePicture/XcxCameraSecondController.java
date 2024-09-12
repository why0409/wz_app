package com.ruoyi.web.controller.wz.onePicture;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.onePicture.domain.XcxCameraColumn;
import com.ruoyi.onePicture.service.IXcxCameraSecondService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 小程序--栏目Controller
 *
 * @author ruoyi
 * @date 2023-10-16
 */
@RestController
@RequestMapping("/xcx/cameraSecond")
@Api(tags = "栏目业务管理")
public class XcxCameraSecondController extends BaseController
{

    @Autowired
    private IXcxCameraSecondService xcxCameraSecondService;

    /**
     * 查询小程序--栏目列表
     */
//    @PreAuthorize("@ss.hasPermi('system:column:list')")
    @ApiOperation("栏目列表")
    @GetMapping("/list")
    public TableDataInfo list(XcxCameraColumn xcxCameraColumn)
    {
        startPage();
        List<XcxCameraColumn> list = xcxCameraSecondService.selectXcxCameraColumnList(xcxCameraColumn);
        return getDataTable(list);
    }

    /**
     * 导出小程序--栏目列表
     */
//    @PreAuthorize("@ss.hasPermi('system:column:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, XcxCameraColumn xcxCameraColumn)
    {
        List<XcxCameraColumn> list = xcxCameraSecondService.selectXcxCameraColumnList(xcxCameraColumn);
        ExcelUtil<XcxCameraColumn> util = new ExcelUtil<>(XcxCameraColumn.class);
        util.exportExcel(response, list, "小程序--栏目数据");
    }

    /**
     * 获取小程序--栏目详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:column:query')")
    @ApiOperation("根据id查看栏目")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(xcxCameraSecondService.selectXcxCameraColumnById(id));
    }

    /**
     * 新增小程序--栏目
     */
//    @PreAuthorize("@ss.hasPermi('system:column:add')")
    @ApiOperation("新增栏目")
    @PostMapping
    public AjaxResult add(@RequestBody XcxCameraColumn xcxCameraColumn)
    {
        return xcxCameraSecondService.insertXcxCameraColumn(xcxCameraColumn);
    }

    /**
     * 修改小程序--栏目
     */
//    @PreAuthorize("@ss.hasPermi('system:column:edit')")
    @ApiOperation("修改栏目")
    @PutMapping
    public AjaxResult edit(@RequestBody XcxCameraColumn xcxCameraColumn)
    {
        return xcxCameraSecondService.updateXcxCameraColumn(xcxCameraColumn);
    }

    /**
     * 删除小程序--栏目
     */
//    @PreAuthorize("@ss.hasPermi('system:column:remove')")
    @ApiOperation("删除栏目")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xcxCameraSecondService.deleteXcxCameraColumnByIds(ids));
    }

    @ApiOperation("根据分类id查询栏目列表")
    @GetMapping("/selectByTypeId")
    public AjaxResult selectByTypeId(Integer typeId){
        return success(xcxCameraSecondService.selectByTypeId(typeId));
    }
}
