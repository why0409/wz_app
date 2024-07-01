package com.ruoyi.web.controller.activities;

import com.ruoyi.activities.domain.ActivitiesInfo;
import com.ruoyi.activities.service.IActivitiesInfoService;
import com.ruoyi.activities.service.IHomestayRegisteredInfoService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 活动信息Controller
 *
 * @author ruoyi
 * @date 2024-06-20
 */
@RestController
@RequestMapping("/activities/info")
public class ActivitiesInfoController extends BaseController
{
    @Autowired
    private IActivitiesInfoService activitiesInfoService;

    @Autowired
    private IHomestayRegisteredInfoService homestayRegisteredInfoService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 查询活动信息列表
     */
    //@PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(ActivitiesInfo activitiesInfo)
    {
        startPage();
        List<ActivitiesInfo> list = activitiesInfoService.selectActivitiesInfoList(activitiesInfo);
        return getDataTable(list);
    }

    /**
     * 导出活动信息列表
     */
    //@PreAuthorize("@ss.hasPermi('system:info:export')")
    @Log(title = "活动信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ActivitiesInfo activitiesInfo)
    {
        List<ActivitiesInfo> list = activitiesInfoService.selectActivitiesInfoList(activitiesInfo);
        ExcelUtil<ActivitiesInfo> util = new ExcelUtil<ActivitiesInfo>(ActivitiesInfo.class);
        util.exportExcel(response, list, "活动信息数据");
    }

    /**
     * 获取活动信息详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(activitiesInfoService.selectActivitiesInfoById(id));
    }

    /**
     * 新增活动信息
     */
    //@PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "活动信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ActivitiesInfo activitiesInfo)
    {
        activitiesInfo.setSecuritiesRestNumber(activitiesInfo.getSecuritiesTotalNumber());
        return toAjax(activitiesInfoService.insertActivitiesInfo(activitiesInfo));
    }

    /**
     * 修改活动信息
     */
    //@PreAuthorize("@ss.hasPermi('system:info:edit')")
    @Log(title = "活动信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ActivitiesInfo activitiesInfo)
    {
        if (activitiesInfo.getSecuritiesTotalNumber() != null) {
            int totalNum = activitiesInfo.getSecuritiesTotalNumber();
            //当前中奖人数
            int winCount = homestayRegisteredInfoService.getWinCount(activitiesInfo.getId());

            activitiesInfo.setSecuritiesRestNumber(totalNum - winCount);
        }

        return toAjax(activitiesInfoService.updateActivitiesInfo(activitiesInfo));
    }

    /**
     * 删除活动信息
     */
    //@PreAuthorize("@ss.hasPermi('system:info:remove')")
    @Log(title = "活动信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(activitiesInfoService.deleteActivitiesInfoByIds(ids));
    }

    @GetMapping("/verifySmsCode")
    public AjaxResult verifySmsCode (String phone, String code)
    {
        String redisKey = "sms_captcha_" + phone;
        String correctCode = redisCache.getCacheObject(redisKey)+"";

        if (correctCode == null) {
            return error("验证码已过期");
        } else if (! correctCode.equals(code)) {
            return error("验证码无效");
        }else {
            return success("验证成功");
        }
    }

}
