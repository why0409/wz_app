package com.ruoyi.xcx.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.xcx.domain.XcxColumn;
import com.ruoyi.xcx.domain.XcxType;
import com.ruoyi.xcx.mapper.XcxColumnMapper;
import com.ruoyi.xcx.mapper.XcxContentMapper;
import com.ruoyi.xcx.mapper.XcxTypeMapper;
import com.ruoyi.xcx.service.IXcxTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * 小程序---分类Service业务层处理
 *
 * @author ruoyi
 * @date 2023-10-16
 */
@Service
public class XcxTypeServiceImpl implements IXcxTypeService
{
    @Autowired
    private XcxTypeMapper xcxTypeMapper;
    @Autowired
    private XcxColumnMapper xcxColumnMapper;
    @Autowired
    private XcxContentMapper xcxContentMapper;

    /**
     * 查询小程序---分类
     *
     * @param id 小程序---分类主键
     * @return 小程序---分类
     */
    @Override
    public XcxType selectXcxTypeById(Long id)
    {
        return xcxTypeMapper.selectXcxTypeById(id);
    }

    /**
     * 查询小程序---分类列表
     *
     * @param xcxType 小程序---分类
     * @return 小程序---分类
     */
    @Override
    public List<XcxType> selectXcxTypeList(XcxType xcxType)
    {
        return xcxTypeMapper.selectXcxTypeList(xcxType);
    }

    /**
     * 新增小程序---分类
     *
     * @param xcxType 小程序---分类
     * @return 结果
     */
    @Override
    public AjaxResult insertXcxType(XcxType xcxType)
    {
        Integer count = xcxTypeMapper.selectSort(xcxType.getSort(),null);
        if (count>0){
            return AjaxResult.error("序号存在重复，请重新填写");
        }
        xcxType.setCreateTime(DateUtils.getNowDate());
        xcxTypeMapper.insertXcxType(xcxType);
        return AjaxResult.success("操作成功");
    }

    /**
     * 修改小程序---分类
     *
     * @param xcxType 小程序---分类
     * @return 结果
     */
    @Override
    public AjaxResult updateXcxType(XcxType xcxType)
    {
        Integer count = xcxTypeMapper.selectSort(xcxType.getSort(),xcxType.getId());
        if (count>0){
            return AjaxResult.error("序号存在重复，请重新填写");
        }
        xcxTypeMapper.updateXcxType(xcxType);
        return AjaxResult.success("操作成功");
    }

    /**
     * 批量删除小程序---分类
     *
     * @param ids 需要删除的小程序---分类主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteXcxTypeByIds(Long[] ids)
    {
        //删除分类
        xcxTypeMapper.deleteXcxTypeByIds(ids);
        List<Long> columnIdList = new ArrayList<>();
        for (int i = 0;i<ids.length;i++){
//            //删除分类对应栏目
//            String replaceTypeId = ids[i]+",";
            //查询分类下所有栏目
            List<XcxColumn> columnList = xcxColumnMapper.selectByTypeId(Integer.valueOf(String.valueOf(ids[i])));
            for (XcxColumn column:columnList){
                columnIdList.add(column.getId());
            }
            xcxColumnMapper.updateTypeId(ids[i]);
        }

        //删除分类下面的poi
        if (!CollectionUtils.isEmpty(columnIdList)){
            xcxContentMapper.deleteByColumnIds(columnIdList.toArray(new Long[columnIdList.size()]));
        }

        return 1;
    }

    /**
     * 删除小程序---分类信息
     *
     * @param id 小程序---分类主键
     * @return 结果
     */
    @Override
    public int deleteXcxTypeById(Long id)
    {
        return xcxTypeMapper.deleteXcxTypeById(id);
    }

    @Override
    public List<XcxType> getAllTypeAndColumn() {
        //查询所有分类
        List<XcxType> xcxTypeList = xcxTypeMapper.selectXcxTypeList(null);
        //根据分类id查询下属栏目
        if (!CollectionUtils.isEmpty(xcxTypeList)){
            for (XcxType type:xcxTypeList){
                 Integer typeId = type.getId();
                 List<XcxColumn> columnList = xcxColumnMapper.selectByTypeId(typeId);
                 if (!CollectionUtils.isEmpty(columnList)){
                     type.setColumnList(columnList);
                 }
            }
        }
        return xcxTypeList;
    }
}
