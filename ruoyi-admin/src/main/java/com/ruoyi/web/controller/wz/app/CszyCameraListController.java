package com.ruoyi.web.controller.wz.app;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.app.domain.CszyCameraList;
import com.ruoyi.app.service.ICszyCameraListService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 城市之眼-视频列Controller
 *
 * @author ruoyi
 * @date 2024-05-24
 */
@RestController
@RequestMapping("/cszy/list")
public class CszyCameraListController extends BaseController
{
    @Autowired
    private ICszyCameraListService cszyCameraListService;

    /**
     * 查询城市之眼-视频列列表
     */
    //@PreAuthorize("@ss.hasPermi('cszy:list:list')")
    @GetMapping("/list")
    public TableDataInfo list(CszyCameraList cszyCameraList)
    {
        startPage();
        List<CszyCameraList> list = cszyCameraListService.selectCszyCameraListList(cszyCameraList);
        return getDataTable(list);
    }

    /**
     * 导出城市之眼-视频列列表
     */
    //@PreAuthorize("@ss.hasPermi('cszy:list:export')")
    @Log(title = "城市之眼-视频列", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CszyCameraList cszyCameraList)
    {
        List<CszyCameraList> list = cszyCameraListService.selectCszyCameraListList(cszyCameraList);
        ExcelUtil<CszyCameraList> util = new ExcelUtil<CszyCameraList>(CszyCameraList.class);
        util.exportExcel(response, list, "城市之眼-视频列数据");
    }

    /**
     * 获取城市之眼-视频列详细信息
     */
    //@PreAuthorize("@ss.hasPermi('cszy:list:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(cszyCameraListService.selectCszyCameraListById(id));
    }

    /**
     * 新增城市之眼-视频列
     */
    //@PreAuthorize("@ss.hasPermi('cszy:list:add')")
    @Log(title = "城市之眼-视频列", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CszyCameraList cszyCameraList)
    {
        return toAjax(cszyCameraListService.insertCszyCameraList(cszyCameraList));
    }

    /**
     * 修改城市之眼-视频列
     */
    //@PreAuthorize("@ss.hasPermi('cszy:list:edit')")
    @Log(title = "城市之眼-视频列", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CszyCameraList cszyCameraList)
    {
        return toAjax(cszyCameraListService.updateCszyCameraList(cszyCameraList));
    }

    /**
     * 删除城市之眼-视频列
     */
    //@PreAuthorize("@ss.hasPermi('cszy:list:remove')")
    @Log(title = "城市之眼-视频列", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(cszyCameraListService.deleteCszyCameraListByIds(ids));
    }
}
