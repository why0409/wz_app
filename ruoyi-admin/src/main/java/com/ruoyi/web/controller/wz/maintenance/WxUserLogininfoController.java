package com.ruoyi.web.controller.wz.maintenance;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.WxClickmoduleInfo;
import com.ruoyi.system.domain.WxSysMenu;
import com.ruoyi.system.domain.WxUserLogininfo;
import com.ruoyi.system.domain.vo.WxClickmoduleInfoDto;
import com.ruoyi.system.service.IWxClickmoduleInfoService;
import com.ruoyi.system.service.IWxSysMenuService;
import com.ruoyi.system.service.IWxUserLogininfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 微信用户登录信息Controller
 *
 * @author ruoyi
 * @date 2022-12-05
 */
@RestController
@RequestMapping("/system/logininfo")
public class WxUserLogininfoController extends BaseController {
    @Autowired
    private IWxUserLogininfoService wxUserLogininfoService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IWxClickmoduleInfoService wxClickmoduleInfoService;
    @Autowired
    private IWxSysMenuService wxSysMenuService;

    /**
     * 查询微信用户登录信息列表
     */
    //@PreAuthorize("@ss.hasPermi('system:logininfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxUserLogininfo wxUserLogininfo) {
        startPage();
        List<WxUserLogininfo> list = wxUserLogininfoService.selectWxUserLogininfoList(wxUserLogininfo);
        return getDataTable(list);
    }

    /**
     * 查询微信用在线户登录信息列表
     */
    //@PreAuthorize("@ss.hasPermi('system:logininfo:list')")
    @GetMapping("/onList")
    public TableDataInfo onList(WxUserLogininfo wxUserLogininfo) {
        startPage();
        List<WxUserLogininfo> list = wxUserLogininfoService.selectWxUserLogininfoList3(wxUserLogininfo);
        return getDataTable(list);
    }

    /**
     * 查询微信用在线户登录信息列表
     */
    //@PreAuthorize("@ss.hasPermi('system:logininfo:list')")
    @GetMapping("/clickModuleInfoCount")
    public TableDataInfo selectWxClickmoduleInfoCount(WxClickmoduleInfoDto wxClickmoduleInfo) {
        startPage();

        List<WxClickmoduleInfo> list = wxClickmoduleInfoService.selectWxClickmoduleInfoCount(wxClickmoduleInfo);

        List<HashMap> resultList = new ArrayList<>();
        List<WxSysMenu> wxSysMenuList = wxSysMenuService.selectWxSysMenuList(null);
//        wxSysMenuList.forEach(item->{
//            if(item.getMenuName().equals("疫情防控"))
//            {
//                item.setMenuName("疫情防控(管理版)");
//            }
//        });
        List<String> wxmenuList = new ArrayList<>();
        wxmenuList.add("政务服务");
        wxmenuList.add("暖民心");
        wxmenuList.add("12345");
//        wxmenuList.add("疫情防控(市民版)");
        wxmenuList.add("拍一拍");
        wxmenuList.add("智慧旅游");
        wxmenuList.add("智慧医疗");
        wxmenuList.add("最新动态");
        wxSysMenuList.forEach(item -> {
            wxmenuList.add(item.getMenuName());
        });

        /**
         * 先移除一部分暂无功能的模块
         */
        wxmenuList.remove("党的建设");
        wxmenuList.remove("应急指挥");
        wxmenuList.remove("招商引资");
        wxmenuList.remove("招才引智");
        wxmenuList.remove("生态环境");
        wxmenuList.remove("经济运行");
        wxmenuList.remove("重点项目");

        list.forEach((item -> {
            List<String> tempList = new ArrayList<>();
            HashMap<String, Object> map = new HashMap<String, Object>();
            List<HashMap<String, String>> modules = new ArrayList<>();
            HashMap<String, String> moduleMap = new HashMap<>();
            String[] moduleItems = item.getClickCount().split(",");
            for (int i = 0; i < moduleItems.length; i++) {
                HashMap<String, String> moduleMap1 = new HashMap<>();
                String key = moduleItems[i].split(":")[0];
                String value = moduleItems[i].split(":")[1];
                moduleMap1.put("name", key);
                moduleMap1.put("value", value);
                modules.add(moduleMap1);
                tempList.add(key);
            }
            wxmenuList.forEach(s -> {
                if (!tempList.contains(s)) {
                    HashMap<String, String> moduleMap1 = new HashMap<>();
                    moduleMap1.put("name", s);
                    moduleMap1.put("value", "");
                    modules.add(moduleMap1);
                }
            });
            List<HashMap<String, String>> modulesTemp=modules.stream().sorted(Comparator.comparing(m->m.get("name"))).collect(Collectors.toList());
            map.put("phone", item.getPhone());
            map.put("modules",modulesTemp);
            if (StringUtils.isNotEmpty(item.getWxName()) && (map.get("wxName") == null|| map.get("wxName") == "")) {
                map.put("wxName", item.getWxName());
            } else if (map.get("wxName") == null||map.get("wxName") == "") {
                map.put("wxName", "");
            }
            resultList.add(map);
        }));
        TableDataInfo tableDataInfo= getDataTable(resultList);
        tableDataInfo.setTotal(new PageInfo(list).getTotal());
        return tableDataInfo;
    }

    /**
     * 查询点击数据
     * @param wxClickmoduleInfo
     * @return
     */
    //@PreAuthorize("@ss.hasPermi('system:logininfo:list')")
    @GetMapping("/selectWxClickmoduleInfoRecord")
    public TableDataInfo  selectWxClickmoduleInfoRecord(WxClickmoduleInfoDto wxClickmoduleInfo) {
        startPage();
        List<WxClickmoduleInfo> list = wxClickmoduleInfoService.selectWxClickmoduleInfoRecord(wxClickmoduleInfo);
        return  getDataTable(list);
    }
    /**
     * 导出微信用户登录信息列表
     */
    //@PreAuthorize("@ss.hasPermi('system:logininfo:export')")
    @Log(title = "微信用户登录信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxUserLogininfo wxUserLogininfo) {
        List<WxUserLogininfo> list = wxUserLogininfoService.selectWxUserLogininfoList(wxUserLogininfo);
        ExcelUtil<WxUserLogininfo> util = new ExcelUtil<WxUserLogininfo>(WxUserLogininfo.class);
        util.exportExcel(response, list, "微信用户登录信息数据");
    }

    /**
     * 获取微信用户登录信息详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:logininfo:query')")
    @GetMapping(value = "/{phone}")
    public AjaxResult getInfo(@PathVariable("phone") String phone) {
        return success(wxUserLogininfoService.selectWxUserLogininfoByPhone(phone));
    }

    /**
     * 新增微信用户登录信息
     */
    //@PreAuthorize("@ss.hasPermi('system:logininfo:add')")
    @Log(title = "微信用户登录信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxUserLogininfo wxUserLogininfo) {
        return toAjax(wxUserLogininfoService.insertWxUserLogininfo(wxUserLogininfo));
    }

    /**
     * 修改微信用户登录信息
     */
    //@PreAuthorize("@ss.hasPermi('system:logininfo:edit')")
    @Log(title = "微信用户登录信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxUserLogininfo wxUserLogininfo) {
        return toAjax(wxUserLogininfoService.updateWxUserLogininfo(wxUserLogininfo));
    }

    /**
     * 删除微信用户登录信息
     */
    //@PreAuthorize("@ss.hasPermi('system:logininfo:remove')")
    @Log(title = "微信用户登录信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{phones}")
    public AjaxResult remove(@PathVariable String[] phones) {
        return toAjax(wxUserLogininfoService.deleteWxUserLogininfoByPhones(phones));
    }

    public boolean isFirstLogin(String phone) {
        List<WxUserLogininfo> list = wxUserLogininfoService.selectByPhone(phone);
        if (list.size() > 0) {
            return false;
        } else {
            return true;
        }
    }


}
