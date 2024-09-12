package com.ruoyi.web.controller.wz.safetyHazard;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.safetyHazard.domain.SafetyHazardUser;
import com.ruoyi.safetyHazard.service.ISafetyHazardUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 安全隐患-用户管理Controller
 *
 * @author ruoyi
 * @date 2024-08-08
 */
@RestController
@RequestMapping("/safetyHazard/user")
public class SafetyHazardUserController extends BaseController
{
    @Autowired
    private ISafetyHazardUserService safetyHazardUserService;

    /**
     * 查询安全隐患-用户管理列表
     */
    //@PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(SafetyHazardUser safetyHazardUser)
    {
        startPage();
        List<SafetyHazardUser> list = safetyHazardUserService.selectSafetyHazardUserList(safetyHazardUser);
        return getDataTable(list);
    }

    /**
     * 导出安全隐患-用户管理列表
     */
    //@PreAuthorize("@ss.hasPermi('system:user:export')")
    //@Log(title = "安全隐患-用户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SafetyHazardUser safetyHazardUser)
    {
        List<SafetyHazardUser> list = safetyHazardUserService.selectSafetyHazardUserList(safetyHazardUser);
        ExcelUtil<SafetyHazardUser> util = new ExcelUtil<SafetyHazardUser>(SafetyHazardUser.class);
        util.exportExcel(response, list, "安全隐患-用户管理数据");
    }

    /**
     * 获取安全隐患-用户管理详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:user:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return success(safetyHazardUserService.selectSafetyHazardUserByUserId(userId));
    }

    /**
     * 新增安全隐患-用户管理
     */
    //@PreAuthorize("@ss.hasPermi('system:user:add')")
    //@Log(title = "安全隐患-用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SafetyHazardUser safetyHazardUser)
    {
        return toAjax(safetyHazardUserService.insertSafetyHazardUser(safetyHazardUser));
    }

    /**
     * 修改安全隐患-用户管理
     */
    //@PreAuthorize("@ss.hasPermi('system:user:edit')")
    //@Log(title = "安全隐患-用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SafetyHazardUser safetyHazardUser)
    {
        return toAjax(safetyHazardUserService.updateSafetyHazardUser(safetyHazardUser));
    }

    /**
     * 删除安全隐患-用户管理
     */
    //@PreAuthorize("@ss.hasPermi('system:user:remove')")
    //@Log(title = "安全隐患-用户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(safetyHazardUserService.deleteSafetyHazardUserByUserIds(userIds));
    }
}
