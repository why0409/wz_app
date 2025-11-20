package com.ruoyi.web.controller.wz.app;

import java.util.Arrays;
import java.util.List;

import com.ruoyi.app.domain.WxAdmin;
import com.ruoyi.app.service.IWxAdminService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;

@RestController
@RequestMapping("/wx/admin")
public class WxAdminController extends BaseController {
    @Autowired
    private IWxAdminService wxAdminService;

    // 获取列表
//    @PreAuthorize("@ss.hasPermi('wx:admin:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxAdmin wxAdmin) {
        startPage();
        List<WxAdmin> list = wxAdminService.selectWxAdminList(wxAdmin);
        return getDataTable(list);
    }

    // 获取详细信息
//    @PreAuthorize("@ss.hasPermi('wx:admin:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(wxAdminService.getById(id));
    }

    // 新增
//    @PreAuthorize("@ss.hasPermi('wx:admin:add')")
    @Log(title = "微信管理员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxAdmin wxAdmin) {
        wxAdmin.setCreateBy(getUsername());
        wxAdmin.setCreateTime(DateUtils.getNowDate());
        return toAjax(wxAdminService.save(wxAdmin));
    }

    // 修改
//    @PreAuthorize("@ss.hasPermi('wx:admin:edit')")
    @Log(title = "微信管理员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxAdmin wxAdmin) {
        wxAdmin.setUpdateBy(getUsername());
        wxAdmin.setUpdateTime(DateUtils.getNowDate());
        return toAjax(wxAdminService.updateById(wxAdmin));
    }

    // 删除
//    @PreAuthorize("@ss.hasPermi('wx:admin:remove')")
    @Log(title = "微信管理员", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(wxAdminService.removeByIds(Arrays.asList(ids)));
    }
}