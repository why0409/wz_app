package com.ruoyi.web.controller.applet;

import com.alibaba.druid.util.StringUtils;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.xcx.domain.*;
import com.ruoyi.xcx.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 小程序----一张图、城市之眼相关接口
 */
@RestController
@RequestMapping("/applet")
public class XcxController extends BaseController {

    @Autowired
    private IXcxTypeService typeService;

    @Autowired
    private IXcxContentService contentService;

    @Autowired
    private IXcxCollectionService xcxCollectionService;

    @Autowired
    private IXcxContentService xcxContentService;

    @Autowired
    private IXcxHistorySearchService xcxHistorySearchService;

    @Autowired
    private IXcxCameraFirstService xcxCameraFirstService;

    @Autowired
    private IXcxCameraThirdService xcxCameraThirdService;

    @Autowired
    private IXcxModuleCoverService xcxModuleCoverService;

    /**
     * 获取小程序类型树
     * @return
     */
    @GetMapping("/getXcxTypeList")
    public List<XcxType> getXcxTypeList(){
        List<XcxType> typeList = typeService.getAllTypeAndColumn();
        return typeList;
    }

    /**
     * 根据小程序栏目id获取poi
     * @param columnId
     * @return
     */
    @GetMapping("/getPoiByColumnId")
    public List<XcxContent> getPoiByColumnId(Integer columnId, String phone){
        List<XcxContent> contentList = contentService.selectByColumnId(columnId,null,phone);
        return contentList;
    }

    @ApiOperation("根据登录手机号查询收藏列表")
    @GetMapping("/collectionList")
    public AjaxResult collectionList(String phone)
    {
        return AjaxResult.success(xcxContentService.getListByCollectionPhone(phone));
    }

    @ApiOperation("新增收藏")
    @PostMapping("/addCollection")
    public AjaxResult addCollection(@RequestBody XcxCollection xcxCollection)
    {
        return toAjax(xcxCollectionService.insertXcxCollection(xcxCollection));
    }

    @ApiOperation("取消收藏")
    @PostMapping("/deleteCollection")
    public AjaxResult deleteCollection(@RequestBody XcxCollection xcxCollection)
    {
        return toAjax(xcxCollectionService.deleteCollection(xcxCollection));
    }

    @ApiOperation("模糊查询")
    @GetMapping("/searchContent")
    public TableDataInfo searchContent(String phone, String contentName)
    {
        //插入历史查询库
        XcxHistorySearch xcxHistorySearch = new XcxHistorySearch();
        xcxHistorySearch.setPhone(phone);
        xcxHistorySearch.setKeyword(contentName);
        if (! StringUtils.isEmpty(contentName)) {
            List<XcxHistorySearch> list = xcxHistorySearchService.selectXcxHistorySearchList(xcxHistorySearch);
            if (list.size() == 0) {
                xcxHistorySearchService.insertXcxHistorySearch(xcxHistorySearch);
            }else {
                XcxHistorySearch Xc = list.get(0);
                xcxHistorySearchService.updateXcxHistorySearch(Xc);
            }
        }

        //查询列表
        startPage();
        List<XcxContent> list = xcxContentService.searchContent(phone, contentName);
        return getDataTable(list);
    }

    @GetMapping("/poiHistorySearch")
    public AjaxResult list(XcxHistorySearch xcxHistorySearch)
    {
        List<XcxHistorySearch> list = xcxHistorySearchService.selectXcxHistorySearchListLimit(xcxHistorySearch);
        return AjaxResult.success(list);
    }

    @GetMapping("/getCameraTreeByPermissions")
    public AjaxResult getCameraTreeByPermissions(String phone){
        List<XcxCameraType> typeList = xcxCameraFirstService.getAllTypeAndColumnByPermissions(phone);
        return success(typeList);
    }

    @GetMapping("/getCameraListBySecondId")
    public List<XcxCameraContent> getPoiByColumnId(Integer columnId){
        List<XcxCameraContent> contentList = xcxCameraThirdService.selectByColumnId(columnId,null,null,null,null,1);
        return contentList;
    }

    @GetMapping("/getCoverByModule")
    public AjaxResult getCoverByModule(String module)
    {
        return AjaxResult.success(xcxModuleCoverService.selectXcxModuleCoverByModule(module));
    }

}
