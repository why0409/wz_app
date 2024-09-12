package com.ruoyi.web.controller.wz.maintenance;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.WxInvestmentPromotion;
import com.ruoyi.system.mapper.WxInvestmentPromotionMapper;
import com.ruoyi.system.service.IWxInvestmentPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 招商宣传Controller
 *
 * @author ruoyi
 * @date 2023-02-22
 */
@RestController
@RequestMapping("/system/promotion")
public class WxInvestmentPromotionController extends BaseController
{
    @Autowired
    private IWxInvestmentPromotionService wxInvestmentPromotionService;

    @Resource
    private WxInvestmentPromotionMapper wxInvestmentPromotionMapper;

    /**
     * 查询招商宣传列表
     */
    //@PreAuthorize("@ss.hasPermi('system:promotion:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxInvestmentPromotion wxInvestmentPromotion)
    {
        startPage();
        List<WxInvestmentPromotion> list = wxInvestmentPromotionService.selectWxInvestmentPromotionList(wxInvestmentPromotion);
        return getDataTable(list);
    }

    /**
     * 导出招商宣传列表
     */
    //@PreAuthorize("@ss.hasPermi('system:promotion:export')")
    @Log(title = "招商宣传", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxInvestmentPromotion wxInvestmentPromotion)
    {
        List<WxInvestmentPromotion> list = wxInvestmentPromotionService.selectWxInvestmentPromotionList(wxInvestmentPromotion);
        ExcelUtil<WxInvestmentPromotion> util = new ExcelUtil<WxInvestmentPromotion>(WxInvestmentPromotion.class);
        util.exportExcel(response, list, "招商宣传数据");
    }

    /**
     * 获取招商宣传详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:promotion:query')")
    @GetMapping(value = "/{uuid}")
    public AjaxResult getInfo(@PathVariable("uuid") String uuid)
    {
        return success(wxInvestmentPromotionService.selectWxInvestmentPromotionByUuid(uuid));
    }

    /**
     * 新增招商宣传
     */
    //@PreAuthorize("@ss.hasPermi('system:promotion:add')")
    @Log(title = "招商宣传", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxInvestmentPromotion wxInvestmentPromotion)
    {
        UUID uuid = UUID.randomUUID();
        wxInvestmentPromotion.setUuid(uuid.toString());
        wxInvestmentPromotion.setPublishTime(new Date());
        //int checkSortNum = wxInvestmentPromotionMapper.checkSortNum(wxInvestmentPromotion.getSortNum());
        //return checkSortNum == 0 ? toAjax(wxInvestmentPromotionService.insertWxInvestmentPromotion(wxInvestmentPromotion)) : AjaxResult.error("已存在重复的序号，请修改！");

        return toAjax(wxInvestmentPromotionService.insertWxInvestmentPromotion(wxInvestmentPromotion));
    }

    /**
     * 修改招商宣传
     */
    //@PreAuthorize("@ss.hasPermi('system:promotion:edit')")
    @Log(title = "招商宣传", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxInvestmentPromotion wxInvestmentPromotion)
    {
        wxInvestmentPromotion.setUpdateTime(new Date());
        //WxInvestmentPromotion w = wxInvestmentPromotionMapper.selectWxInvestmentPromotionByUuid(wxInvestmentPromotion.getUuid());
        //int checkSortNum = wxInvestmentPromotionMapper.checkSortNum(wxInvestmentPromotion.getSortNum());
        //if (w.getSortNum().equals(wxInvestmentPromotion.getSortNum())){
        //    return toAjax(wxInvestmentPromotionService.updateWxInvestmentPromotion(wxInvestmentPromotion));
        //}else {
        //    return (checkSortNum == 0) ? toAjax(wxInvestmentPromotionService.updateWxInvestmentPromotion(wxInvestmentPromotion)) : AjaxResult.error("已存在重复的序号，请修改！");
        //}

        return toAjax(wxInvestmentPromotionService.updateWxInvestmentPromotion(wxInvestmentPromotion));
    }

    /**
     * 删除招商宣传
     */
    //@PreAuthorize("@ss.hasPermi('system:promotion:remove')")
    @Log(title = "招商宣传", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uuids}")
    public AjaxResult remove(@PathVariable String[] uuids)
    {
        return toAjax(wxInvestmentPromotionService.deleteWxInvestmentPromotionByUuids(uuids));
    }
}
