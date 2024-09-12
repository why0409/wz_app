package com.ruoyi.web.controller.wz.maintenance;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.WxSysMenu;
import com.ruoyi.system.domain.vo.WxPermDto;
import com.ruoyi.system.domain.vo.WxSysMenuDto;
import com.ruoyi.app.service.IServiceConfigService;
import com.ruoyi.system.service.IWxSysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 微信菜单Controller
 *
 * @author lgh
 * @date 2022-11-22
 */
@RestController
@RequestMapping("/maintenance/WxSysMenu")
public class WxSysMenuController extends BaseController
{
    @Autowired
    private IWxSysMenuService wxSysMenuService;

    @Autowired
    private IServiceConfigService serviceConfigService;

    /**
     * 查询微信菜单列表
     */
    //@PreAuthorize("@ss.hasPermi('system:WxSysMenu:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxSysMenu wxSysMenu)
    {
        startPage();
        List<WxSysMenu> list = wxSysMenuService.selectWxSysMenuList(wxSysMenu);
        return getDataTable(list);
    }

    /**
     * 菜单权限
     * @author:
     * @date: 2022/12/2 9:23
     * @param wxSysMenuDto
     * @return
     */
    @GetMapping("/selectInfo")
    public AjaxResult selectInfo(WxSysMenuDto wxSysMenuDto)
    {
        WxPermDto wxPermDto = wxSysMenuService.selectInfo(wxSysMenuDto);
        return AjaxResult.success(wxPermDto);
    }

    @GetMapping(value = "/queryWxUserMenuByPhone")
    public TableDataInfo queryWxUserMenuByPhone(String phone) {
        startPage();
        List<Map<String,Object>> list = wxSysMenuService.queryWxUserMenuByPhone(phone);
        return getDataTable(list);
    }

    /**
     * 导出微信菜单列表
     */
    //@PreAuthorize("@ss.hasPermi('system:WxSysMenu:export')")
    @Log(title = "微信菜单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxSysMenu wxSysMenu)
    {
        List<WxSysMenu> list = wxSysMenuService.selectWxSysMenuList(wxSysMenu);
        ExcelUtil<WxSysMenu> util = new ExcelUtil<WxSysMenu>(WxSysMenu.class);
        util.exportExcel(response, list, "微信菜单数据");
    }

    /**
     * 获取微信菜单详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:WxSysMenu:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(wxSysMenuService.selectWxSysMenuById(id));
    }

    /**
     * 新增微信菜单
     */
    //@PreAuthorize("@ss.hasPermi('system:WxSysMenu:add')")
    @Log(title = "微信菜单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxSysMenu wxSysMenu)
    {
        return toAjax(wxSysMenuService.insertWxSysMenu(wxSysMenu));
    }

    /**
     * 修改微信菜单
     */
    //@PreAuthorize("@ss.hasPermi('system:WxSysMenu:edit')")
    @Log(title = "微信菜单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxSysMenu wxSysMenu)
    {
        return toAjax(wxSysMenuService.updateWxSysMenu(wxSysMenu));
    }

    /**
     * 删除微信菜单
     */
    //@PreAuthorize("@ss.hasPermi('system:WxSysMenu:remove')")
    @Log(title = "微信菜单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wxSysMenuService.deleteWxSysMenuByIds(ids));
    }
}
