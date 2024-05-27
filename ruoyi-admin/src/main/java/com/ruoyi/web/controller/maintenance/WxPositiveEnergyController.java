package com.ruoyi.web.controller.maintenance;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.WxPositiveEnergy;
import com.ruoyi.system.mapper.WxPositiveEnergyMapper;
import com.ruoyi.system.service.IWxPositiveEnergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 湾沚正能量Controller
 * 
 * @author ruoyi
 * @date 2023-02-22
 */
@RestController
@RequestMapping("/system/energy")
public class WxPositiveEnergyController extends BaseController
{
    @Autowired 
    private IWxPositiveEnergyService wxPositiveEnergyService;

    @Resource
    private WxPositiveEnergyMapper wxPositiveEnergyMapper;

    /**
     * 查询湾沚正能量列表
     */
    //@PreAuthorize("@ss.hasPermi('system:energy:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxPositiveEnergy wxPositiveEnergy)
    {
        startPage();
        List<WxPositiveEnergy> list = wxPositiveEnergyService.selectWxPositiveEnergyList(wxPositiveEnergy);
        return getDataTable(list);
    }

    /**
     * 导出湾沚正能量列表
     */
    //@PreAuthorize("@ss.hasPermi('system:energy:export')")
    @Log(title = "湾沚正能量", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxPositiveEnergy wxPositiveEnergy)
    {
        List<WxPositiveEnergy> list = wxPositiveEnergyService.selectWxPositiveEnergyList(wxPositiveEnergy);
        ExcelUtil<WxPositiveEnergy> util = new ExcelUtil<WxPositiveEnergy>(WxPositiveEnergy.class);
        util.exportExcel(response, list, "湾沚正能量数据");
    }

    /**
     * 获取湾沚正能量详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:energy:query')")
    @GetMapping(value = "/{uuid}")
    public AjaxResult getInfo(@PathVariable("uuid") String uuid)
    {
        return success(wxPositiveEnergyService.selectWxPositiveEnergyByUuid(uuid));
    }

    /**
     * 新增湾沚正能量
     */
    //@PreAuthorize("@ss.hasPermi('system:energy:add')")
    @Log(title = "湾沚正能量", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxPositiveEnergy wxPositiveEnergy)
    {
        UUID uuid = UUID.randomUUID();
        wxPositiveEnergy.setUuid(uuid.toString());
        wxPositiveEnergy.setPublishTime(new Date());
        //int checkSortNum = wxPositiveEnergyMapper.checkSortNum(wxPositiveEnergy.getSortNum());
        //return checkSortNum == 0 ? toAjax(wxPositiveEnergyService.insertWxPositiveEnergy(wxPositiveEnergy)) : AjaxResult.error("已存在重复的序号，请修改！");

        return toAjax(wxPositiveEnergyService.insertWxPositiveEnergy(wxPositiveEnergy));
    }

    /**
     * 修改湾沚正能量
     */
    //@PreAuthorize("@ss.hasPermi('system:energy:edit')")
    @Log(title = "湾沚正能量", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxPositiveEnergy wxPositiveEnergy)
    {
        wxPositiveEnergy.setUpdateTime(new Date());
        //WxPositiveEnergy w = wxPositiveEnergyMapper.selectWxPositiveEnergyByUuid(wxPositiveEnergy.getUuid());
        //int checkSortNum = wxPositiveEnergyMapper.checkSortNum(wxPositiveEnergy.getSortNum());
        //if (w.getSortNum().equals(wxPositiveEnergy.getSortNum())){
        //    return toAjax(wxPositiveEnergyService.updateWxPositiveEnergy(wxPositiveEnergy));
        //}else {
        //    return (checkSortNum == 0) ? toAjax(wxPositiveEnergyService.updateWxPositiveEnergy(wxPositiveEnergy)) : AjaxResult.error("已存在重复的序号，请修改！");
        //}
        return toAjax(wxPositiveEnergyService.updateWxPositiveEnergy(wxPositiveEnergy));
    }

    /**
     * 删除湾沚正能量
     */
    //@PreAuthorize("@ss.hasPermi('system:energy:remove')")
    @Log(title = "湾沚正能量", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uuids}")
    public AjaxResult remove(@PathVariable String[] uuids)
    {
        return toAjax(wxPositiveEnergyService.deleteWxPositiveEnergyByUuids(uuids));
    }
}
