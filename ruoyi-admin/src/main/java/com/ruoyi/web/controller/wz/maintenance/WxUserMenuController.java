package com.ruoyi.web.controller.wz.maintenance;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.WxUserMenu;
import com.ruoyi.system.domain.vo.WxUserMenuReqVo;
import com.ruoyi.system.service.IWxUserMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户菜单关系Controller
 *
 * @author ruoyi
 * @date 2022-11-22
 */
@RestController
@RequestMapping("/maintenance/WxUserMenu")
public class WxUserMenuController extends BaseController
{
    @Autowired
    private IWxUserMenuService wxUserMenuService;

    /**
     * 批量新增用户菜单关系
     */
    //@PreAuthorize("@ss.hasPermi('system:WxUserMenu:add')")
    @Log(title = "批量添加用户菜单关系", businessType = BusinessType.INSERT)
    @PostMapping("/addWxUserMenu")
    public AjaxResult addWxUserMenu(@RequestBody WxUserMenuReqVo wxUserMenuReqVo)
    {
        return toAjax(wxUserMenuService.addWxUserMenu(wxUserMenuReqVo));
    }

    /**
     * 查询用户菜单关系列表
     */
    //@PreAuthorize("@ss.hasPermi('system:WxUserMenu:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxUserMenu wxUserMenu)
    {
        startPage();
        List<WxUserMenu> list = wxUserMenuService.selectWxUserMenuList(wxUserMenu);
        return getDataTable(list);
    }

    /**
     * 导出用户菜单关系列表
     */
    //@PreAuthorize("@ss.hasPermi('system:WxUserMenu:export')")
    @Log(title = "用户菜单关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxUserMenu wxUserMenu)
    {
        List<WxUserMenu> list = wxUserMenuService.selectWxUserMenuList(wxUserMenu);
        ExcelUtil<WxUserMenu> util = new ExcelUtil<WxUserMenu>(WxUserMenu.class);
        util.exportExcel(response, list, "用户菜单关系数据");
    }

//    /**
//     * 获取用户菜单关系详细信息
//     */
//    @PreAuthorize("@ss.hasPermi('system:WxUserMenu:query')")
//    @GetMapping(value = "/{id}")
//    public AjaxResult getInfo(@PathVariable("id") Long id)
//    {
//        return success(wxUserMenuService.selectWxUserMenuById(id));
//    }



    /**
     * 新增用户菜单关系
     */
//    @PreAuthorize("@ss.hasPermi('system:WxUserMenu:add')")
//    @Log(title = "用户菜单关系", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody WxUserMenu wxUserMenu)
//    {
//        return toAjax(wxUserMenuService.insertWxUserMenu(wxUserMenu));
//    }



    /**
     * 修改用户菜单关系
     */
    //@PreAuthorize("@ss.hasPermi('system:WxUserMenu:edit')")
    @Log(title = "用户菜单关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxUserMenu wxUserMenu)
    {
        return toAjax(wxUserMenuService.updateWxUserMenu(wxUserMenu));
    }

    /**
     * 删除用户菜单关系
     */
    //@PreAuthorize("@ss.hasPermi('system:WxUserMenu:remove')")
    @Log(title = "用户菜单关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wxUserMenuService.deleteWxUserMenuByIds(ids));
    }

    /**
     * 批量新增用户菜单关系（新）
     */
    //@PreAuthorize("@ss.hasPermi('system:WxUserMenu:add')")
    @Log(title = "批量添加用户菜单关系（新）", businessType = BusinessType.INSERT)
    @PostMapping("/addWxUserMenuNew")
    public AjaxResult addWxUserMenuNew(@RequestBody WxUserMenuReqVo wxUserMenuReqVo)
    {
        return toAjax(wxUserMenuService.addWxUserMenuNew(wxUserMenuReqVo));
    }

}
