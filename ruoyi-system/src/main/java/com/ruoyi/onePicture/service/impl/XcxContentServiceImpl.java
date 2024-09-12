package com.ruoyi.onePicture.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.onePicture.domain.XcxContent;
import com.ruoyi.onePicture.mapper.FileMapper;
import com.ruoyi.onePicture.mapper.XcxContentMapper;
import com.ruoyi.onePicture.service.IXcxContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 小程序_内容详情Service业务层处理
 *
 * @author ruoyi
 * @date 2023-10-16
 */
@Service
public class XcxContentServiceImpl implements IXcxContentService
{
    @Autowired
    private XcxContentMapper xcxContentMapper;
    @Autowired
    private FileMapper fileMapper;

    /**
     * 查询小程序_内容详情
     *
     * @param id 小程序_内容详情主键
     * @return 小程序_内容详情
     */
    @Override
    public XcxContent selectXcxContentById(Long id)
    {
         XcxContent xcxContent = xcxContentMapper.selectXcxContentById(id);
         //查找文件
//         String fileIds = xcxContent.getFileIds();
//        if (StringUtils.isNotEmpty(fileIds)){
//            List<String> idList = Arrays.asList(fileIds.split(","));
//            List<Integer> fileIdList = idList.stream().map(Integer::parseInt).collect(Collectors.toList());
//            List<XcxFile> fileList =fileMapper.selectFileByIdList(fileIdList);
//            xcxContent.setFileList(fileList);
//        }
        return xcxContent;
    }

    /**
     * 查询小程序_内容详情列表
     *
     * @param xcxContent 小程序_内容详情
     * @return 小程序_内容详情
     */
    @Override
    public List<XcxContent> selectXcxContentList(XcxContent xcxContent)
    {
        return xcxContentMapper.selectXcxContentList(xcxContent);
    }

    /**
     * 新增小程序_内容详情
     *
     * @param xcxContent 小程序_内容详情
     * @return 结果
     */
    @Override
    public AjaxResult insertXcxContent(XcxContent xcxContent)
    {
        //排序号判断
        Integer count = xcxContentMapper.selectSort(xcxContent.getContentSort(),null,xcxContent.getColumnId());
        if (count>0){
            return AjaxResult.error("序号存在重复，请重新填写");
        }
        xcxContent.setCreateTime(DateUtils.getNowDate());
        xcxContentMapper.insertXcxContent(xcxContent);
        return AjaxResult.success("操作成功");
    }

    /**
     * 修改小程序_内容详情
     *
     * @param xcxContent 小程序_内容详情
     * @return 结果
     */
    @Override
    public AjaxResult updateXcxContent(XcxContent xcxContent)
    {
        //排序号判断
        Integer count = xcxContentMapper.selectSort(xcxContent.getContentSort(),xcxContent.getId(),xcxContent.getColumnId());
        if (count>0){
            return AjaxResult.error("序号存在重复，请重新填写");
        }
        xcxContentMapper.updateXcxContent(xcxContent);
        return AjaxResult.success("操作成功");
    }

    /**
     * 批量删除小程序_内容详情
     *
     * @param ids 需要删除的小程序_内容详情主键
     * @return 结果
     */
    @Override
    public int deleteXcxContentByIds(Long[] ids)
    {
        return xcxContentMapper.deleteXcxContentByIds(ids);
    }

    /**
     * 删除小程序_内容详情信息
     *
     * @param id 小程序_内容详情主键
     * @return 结果
     */
    @Override
    public int deleteXcxContentById(Long id)
    {
        return xcxContentMapper.deleteXcxContentById(id);
    }

    @Override
    public List<XcxContent> selectByColumnId(Integer columnId, String contentName, String phone) {
        return xcxContentMapper.selectByColumnId(columnId, contentName, phone);
    }

    @Override
    public List<XcxContent> getListByCollectionPhone(String phone) {
        return xcxContentMapper.getListByCollectionPhone(phone);
    }

    @Override
    public List<XcxContent> searchContent(String phone, String contentName) {
        return xcxContentMapper.searchContent(phone, contentName);
    }

}
