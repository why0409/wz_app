package com.ruoyi.xcx.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.xcx.domain.XcxCameraContent;
import com.ruoyi.xcx.mapper.XcxCameraThirdMapper;
import com.ruoyi.xcx.service.IXcxCameraThirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 小程序_内容详情Service业务层处理
 *
 * @author ruoyi
 * @date 2023-10-16
 */
@Service
public class XcxCameraThirdServiceImpl implements IXcxCameraThirdService
{

    @Autowired
    private XcxCameraThirdMapper xcxCameraThird;

    /**
     * 查询小程序_内容详情
     *
     * @param id 小程序_内容详情主键
     * @return 小程序_内容详情
     */
    @Override
    public XcxCameraContent selectXcxCameraContentById(Long id)
    {
        XcxCameraContent xcxCameraContent = xcxCameraThird.selectXcxCameraContentById(id);
         //查找文件
//         String fileIds = xcxContent.getFileIds();
//        if (StringUtils.isNotEmpty(fileIds)){
//            List<String> idList = Arrays.asList(fileIds.split(","));
//            List<Integer> fileIdList = idList.stream().map(Integer::parseInt).collect(Collectors.toList());
//            List<XcxFile> fileList =fileMapper.selectFileByIdList(fileIdList);
//            xcxContent.setFileList(fileList);
//        }
        return xcxCameraContent;
    }

    /**
     * 查询小程序_内容详情列表
     *
     * @param xcxCameraContent 小程序_内容详情
     * @return 小程序_内容详情
     */
    @Override
    public List<XcxCameraContent> selectXcxCameraContentList(XcxCameraContent xcxCameraContent)
    {
        return xcxCameraThird.selectXcxCameraContentList(xcxCameraContent);
    }

    /**
     * 新增小程序_内容详情
     *
     * @param xcxCameraContent 小程序_内容详情
     * @return 结果
     */
    @Override
    public AjaxResult insertXcxCameraContent(XcxCameraContent xcxCameraContent)
    {
        List<String> columnIdList = Arrays.stream(xcxCameraContent.getColumnId().split(","))
                .collect(Collectors.toList());

        for (String columnId : columnIdList) {
            //排序号判断
            Integer count = xcxCameraThird.selectSort(xcxCameraContent.getContentSort(),null,columnId);
            if (count>0){
                return AjaxResult.error("序号存在重复，请重新填写");
            }

            XcxCameraContent xc = xcxCameraContent;
            xc.setCreateTime(DateUtils.getNowDate());
            xc.setColumnId(columnId);
            xcxCameraThird.insertXcxCameraContent(xc);
        }

        return AjaxResult.success("操作成功");
    }

    /**
     * 修改小程序_内容详情
     *
     * @param xcxCameraContent 小程序_内容详情
     * @return 结果
     */
    @Override
    public AjaxResult updateXcxCameraContent(XcxCameraContent xcxCameraContent)
    {
        //排序号判断
        Integer count = xcxCameraThird.selectSort(xcxCameraContent.getContentSort(),xcxCameraContent.getId(),xcxCameraContent.getColumnId());
        if (count>0){
            return AjaxResult.error("序号存在重复，请重新填写");
        }
        xcxCameraThird.updateXcxCameraContent(xcxCameraContent);
        return AjaxResult.success("操作成功");
    }

    /**
     * 批量删除小程序_内容详情
     *
     * @param ids 需要删除的小程序_内容详情主键
     * @return 结果
     */
    @Override
    public int deleteXcxCameraContentByIds(Long[] ids)
    {
        return xcxCameraThird.deleteXcxCameraContentByIds(ids);
    }

    /**
     * 删除小程序_内容详情信息
     *
     * @param id 小程序_内容详情主键
     * @return 结果
     */
    @Override
    public int deleteXcxCameraContentById(Long id)
    {
        return xcxCameraThird.deleteXcxCameraContentById(id);
    }

    @Override
    public List<XcxCameraContent> selectByColumnId(Integer columnId, String contentName, String phone, String dept, String operators, Integer searchType) {
        return xcxCameraThird.selectByColumnId(columnId, contentName, phone, dept, operators, searchType);
    }

    @Override
    public List<XcxCameraContent> getListByCollectionPhone(String phone) {
        return xcxCameraThird.getListByCollectionPhone(phone);
    }

    @Override
    public List<XcxCameraContent> searchContent(String phone, String contentName) {
        return xcxCameraThird.searchContent(phone, contentName);
    }

    @Override
    public List<JSONObject> selectDistinctDept() {
        return xcxCameraThird.selectDistinctDept();
    }

}
