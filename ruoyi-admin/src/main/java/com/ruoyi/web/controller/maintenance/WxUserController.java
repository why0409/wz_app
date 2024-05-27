package com.ruoyi.web.controller.maintenance;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.jishijiao.service.SspService;
import com.ruoyi.wxuser.domain.WxUser;
import com.ruoyi.wxuser.service.IWxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户Controller
 * 
 * @author lgh
 * @date 2022-11-22
 */
@RestController
@RequestMapping("/maintenance/wxUser")
public class WxUserController extends BaseController
{
    @Autowired
    private IWxUserService wxUserService;

    @Autowired
    private SspService sspService;

    /**
     * 查询用户列表
     */
    //@PreAuthorize("@ss.hasPermi('system:wxUser:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxUser wxUser)
    {
        startPage();
        List<WxUser> list = wxUserService.selectWxUserList(wxUser);
        return getDataTable(list);
    }

    /**
     * 导出用户列表
     */
    //@PreAuthorize("@ss.hasPermi('system:wxUser:export')")
    @Log(title = "用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxUser wxUser)
    {
        List<WxUser> list = wxUserService.selectWxUserList(wxUser);
        ExcelUtil<WxUser> util = new ExcelUtil<WxUser>(WxUser.class);
        util.exportExcel(response, list, "用户数据");
    }

    /**
     * 获取用户详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:wxUser:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(wxUserService.selectWxUserById(id));
    }

    /**
     * 新增用户
     */
    //@PreAuthorize("@ss.hasPermi('system:wxUser:add')")
    @Log(title = "用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxUser wxUser)
    {
        return toAjax(wxUserService.insertWxUser(wxUser));
    }

    /**
     * 修改用户
     */
    //@PreAuthorize("@ss.hasPermi('system:wxUser:edit')")
    @Log(title = "用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxUser wxUser)
    {
        return toAjax(wxUserService.updateWxUser(wxUser));
    }

    /**
     * 删除用户
     */
    //@PreAuthorize("@ss.hasPermi('system:wxUser:remove')")
    @Log(title = "用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(wxUserService.deleteWxUserByIds(ids));
    }

    /**
     * 根据菜单查询用户
     */
    @GetMapping("/getListByMenuId")
    public AjaxResult getListByMenuId(Long menuId,int pageNum,int pageSize)
    {
        return success(wxUserService.getListByMenuId(menuId,pageNum,pageSize));
    }

    @GetMapping("/updateLabel")
    public AjaxResult updateLabel(String id, String label)
    {
        //WxUser wxUser = new WxUser();
        //wxUser.setId(id);
        //wxUser.setLabel(label);
        //return success(wxUserService.updateWxUser(wxUser));

        return success(sspService.updateLabel(id, label));
    }

    @GetMapping("/updateVideoPermissions")
    public AjaxResult updateVideoPermissions(String id, String videoPermissions)
    {
        //WxUser wxUser = new WxUser();
        //wxUser.setId(id);
        //wxUser.setVideoPermissions(videoPermissions);
        //return success(wxUserService.updateWxUser(wxUser));

        return success(sspService.updateVideoPermissions(id, videoPermissions));
    }

    /**
     * 根据手机号删除模块下用户
     */
    @GetMapping("/deleteUserByMenuId")
    public AjaxResult deleteUserByMenuId(Long menuId,String mobile) {
        return success(wxUserService.deleteUserByMenuId(menuId,mobile));
    }

}
