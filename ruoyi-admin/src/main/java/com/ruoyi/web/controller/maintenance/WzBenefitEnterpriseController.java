package com.ruoyi.web.controller.maintenance;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.screen.domain.WzBenefitEnterprise;
import com.ruoyi.screen.mapper.WzBenefitEnterpriseMapper;
import com.ruoyi.screen.service.IWzBenefitEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 惠企政策Controller
 *
 * @author ruoyi
 * @date 2023-04-03
 */
@RestController
@RequestMapping("/wanzhi/enterprise")
public class WzBenefitEnterpriseController extends BaseController
{
    @Autowired
    private IWzBenefitEnterpriseService wzBenefitEnterpriseService;

    @Resource
    private WzBenefitEnterpriseMapper wzBenefitEnterpriseMapper;

    /**
     * 查询惠企政策列表
     */
//    @PreAuthorize("@ss.hasPermi('wanzhi:enterprise:list')")
    @GetMapping("/list")
    public TableDataInfo list(WzBenefitEnterprise wzBenefitEnterprise)
    {
        startPage();
        List<WzBenefitEnterprise> list = wzBenefitEnterpriseService.selectWzBenefitEnterpriseList(wzBenefitEnterprise);
        return getDataTable(list);
    }

    @GetMapping("/updateSum")
    public AjaxResult updateSum(String payFrequencyPerson, String payFrequencyEnterprise,
                                String payAmountPerson, String payAmountEnterprise)
    {
        if(payFrequencyPerson.equals("null")){
            payFrequencyPerson = "0";
        }
        if(payFrequencyEnterprise.equals("null")){
            payFrequencyEnterprise = "0";
        }
        if(payAmountPerson.equals("null")){
            payAmountPerson = "0";
        }
        if(payAmountEnterprise.equals("null")){
            payAmountEnterprise = "0";
        }

        return success(wzBenefitEnterpriseMapper.updateSum(payFrequencyPerson,payFrequencyEnterprise,
                                                           payAmountPerson,payAmountEnterprise));
    }

    /**
     * 导出惠企政策列表
     */
    //@PreAuthorize("@ss.hasPermi('wanzhi:enterprise:export')")
    @Log(title = "惠企政策", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WzBenefitEnterprise wzBenefitEnterprise)
    {
        List<WzBenefitEnterprise> list = wzBenefitEnterpriseService.selectWzBenefitEnterpriseList(wzBenefitEnterprise);
        ExcelUtil<WzBenefitEnterprise> util = new ExcelUtil<WzBenefitEnterprise>(WzBenefitEnterprise.class);
        util.exportExcel(response, list, "惠企政策数据");
    }

    /**
     * 获取惠企政策详细信息
     */
    //@PreAuthorize("@ss.hasPermi('wanzhi:enterprise:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(wzBenefitEnterpriseService.selectWzBenefitEnterpriseById(id));
    }

    /**
     * 新增惠企政策
     */
    //@PreAuthorize("@ss.hasPermi('wanzhi:enterprise:add')")
    @Log(title = "惠企政策", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WzBenefitEnterprise wzBenefitEnterprise)
    {
        return toAjax(wzBenefitEnterpriseService.insertWzBenefitEnterprise(wzBenefitEnterprise));
    }

    /**
     * 修改惠企政策
     */
    //@PreAuthorize("@ss.hasPermi('wanzhi:enterprise:edit')")
    @Log(title = "惠企政策", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WzBenefitEnterprise wzBenefitEnterprise)
    {
        return toAjax(wzBenefitEnterpriseService.updateWzBenefitEnterprise(wzBenefitEnterprise));
    }

    /**
     * 删除惠企政策
     */
    //@PreAuthorize("@ss.hasPermi('wanzhi:enterprise:remove')")
    @Log(title = "惠企政策", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wzBenefitEnterpriseService.deleteWzBenefitEnterpriseByIds(ids));
    }
}
