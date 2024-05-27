package com.ruoyi.web.controller.maintenance;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.WxTitleConfig;
import com.ruoyi.system.mapper.WxTitleConfigMapper;
import com.ruoyi.system.service.IWxTitleConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 标题配置Controller
 *
 * @author ruoyi
 * @date 2023-04-24
 */
@RestController
@RequestMapping("/system/titleConfig")
public class WxTitleConfigController extends BaseController
{
    @Autowired
    private IWxTitleConfigService wxTitleConfigService;

    @Resource
    private WxTitleConfigMapper wxTitleConfigMapper;

    /**
     * 查询标题配置列表
     */
//    @PreAuthorize("@ss.hasPermi('system:config:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxTitleConfig wxTitleConfig)
    {
        startPage();
        List<WxTitleConfig> list = wxTitleConfigService.selectWxTitleConfigList(wxTitleConfig);
        return getDataTable(list);
    }

    /**
     * 导出标题配置列表
     */
    //@PreAuthorize("@ss.hasPermi('system:titleConfig:export')")
    @Log(title = "标题配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxTitleConfig wxTitleConfig)
    {
        List<WxTitleConfig> list = wxTitleConfigService.selectWxTitleConfigList(wxTitleConfig);
        ExcelUtil<WxTitleConfig> util = new ExcelUtil<WxTitleConfig>(WxTitleConfig.class);
        util.exportExcel(response, list, "标题配置数据");
    }

    /**
     * 获取标题配置详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:titleConfig:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(wxTitleConfigService.selectWxTitleConfigById(id));
    }

    /**
     * 新增标题配置
     */
    //@PreAuthorize("@ss.hasPermi('system:titleConfig:add')")
    @Log(title = "标题配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxTitleConfig wxTitleConfig)
    {
        int checkSortNum = wxTitleConfigMapper.checkSortNum(wxTitleConfig.getSortNum());
        return checkSortNum == 0 ? toAjax(wxTitleConfigService.insertWxTitleConfig(wxTitleConfig)) : AjaxResult.error("已存在重复的序号，请修改！");
    }

    /**
     * 修改标题配置
     */
    //@PreAuthorize("@ss.hasPermi('system:titleConfig:edit')")
    @Log(title = "标题配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxTitleConfig wxTitleConfig)
    {
        WxTitleConfig w = wxTitleConfigMapper.selectWxTitleConfigById(wxTitleConfig.getId());
        int checkSortNum = wxTitleConfigMapper.checkSortNum(wxTitleConfig.getSortNum());
        if (w.getSortNum().equals(wxTitleConfig.getSortNum())){
            return toAjax(wxTitleConfigService.updateWxTitleConfig(wxTitleConfig));
        }else {
            return (checkSortNum == 0) ? toAjax(wxTitleConfigService.updateWxTitleConfig(wxTitleConfig)) : AjaxResult.error("已存在重复的序号，请修改！");
        }
    }

    /**
     * 删除标题配置
     */
    //@PreAuthorize("@ss.hasPermi('system:titleConfig:remove')")
    @Log(title = "标题配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wxTitleConfigService.deleteWxTitleConfigByIds(ids));
    }
}

