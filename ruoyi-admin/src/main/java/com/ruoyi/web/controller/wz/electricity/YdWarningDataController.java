package com.ruoyi.web.controller.wz.electricity;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.electricity.domain.YdWarningData;
import com.ruoyi.electricity.service.IYdEnterpriseDataService;
import com.ruoyi.electricity.service.IYdWarningDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用电预警数据Controller
 *
 * @author ruoyi
 * @date 2024-11-04
 */
@RestController
@RequestMapping("/yd/warning/data")
public class YdWarningDataController extends BaseController
{
    @Autowired
    private IYdWarningDataService ydWarningDataService;

    /**
     * 查询用电预警数据列表
     */
    //@PreAuthorize("@ss.hasPermi('system:data:list')")
    @GetMapping("/list")
    public TableDataInfo list(YdWarningData ydWarningData)
    {
        startPage();
        List<YdWarningData> list = ydWarningDataService.selectYdWarningDataList(ydWarningData);
        return getDataTable(list);
    }

    /**
     * 导出用电预警数据列表
     */
    //@PreAuthorize("@ss.hasPermi('system:data:export')")
    //@Log(title = "用电预警数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YdWarningData ydWarningData)
    {
        List<YdWarningData> list = ydWarningDataService.selectYdWarningDataList(ydWarningData);
        ExcelUtil<YdWarningData> util = new ExcelUtil<YdWarningData>(YdWarningData.class);
        util.exportExcel(response, list, "用电预警数据数据");
    }

    /**
     * 获取用电预警数据详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:data:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(ydWarningDataService.selectYdWarningDataById(id));
    }

    /**
     * 新增用电预警数据
     */
    //@PreAuthorize("@ss.hasPermi('system:data:add')")
    //@Log(title = "用电预警数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YdWarningData ydWarningData)
    {
        return toAjax(ydWarningDataService.insertYdWarningData(ydWarningData));
    }

    /**
     * 修改用电预警数据
     */
    //@PreAuthorize("@ss.hasPermi('system:data:edit')")
    //@Log(title = "用电预警数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YdWarningData ydWarningData)
    {
        return toAjax(ydWarningDataService.updateYdWarningData(ydWarningData));
    }

    /**
     * 删除用电预警数据
     */
    //@PreAuthorize("@ss.hasPermi('system:data:remove')")
    //@Log(title = "用电预警数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ydWarningDataService.deleteYdWarningDataByIds(ids));
    }

    //@GetMapping("/analysisImport")
    //public AjaxResult analysisImport()
    //{
    //    List<YdWarningData> list = ydWarningDataService.analysisImport();
    //    ydWarningDataService.saveBatch(list);
    //
    //    //记录上次分析的最大更新时间
    //    String maxUpdateTime = ydEnterpriseDataService.getMaxUpdateTime();
    //    redisCache.setCacheObject("maxUpdateTime",maxUpdateTime);
    //
    //    return success(list.size());
    //}

    @GetMapping("/getWarningList")
    public TableDataInfo getWarningList(YdWarningData ydWarningData)
    {
        startPage();
        List<YdWarningData> list = ydWarningDataService.getWarningList(ydWarningData);
        return getDataTable(list);
    }
}
