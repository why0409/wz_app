package com.ruoyi.xcx.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.xcx.domain.XcxColumn;
import com.ruoyi.xcx.domain.XcxType;
import com.ruoyi.xcx.mapper.XcxColumnMapper;
import com.ruoyi.xcx.mapper.XcxContentMapper;
import com.ruoyi.xcx.mapper.XcxTypeMapper;
import com.ruoyi.xcx.service.IXcxColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * 小程序--栏目Service业务层处理
 *
 * @author ruoyi
 * @date 2023-10-16
 */
@Service
public class XcxColumnServiceImpl implements IXcxColumnService
{
    @Autowired
    private XcxColumnMapper xcxColumnMapper;
    @Autowired
    private XcxTypeMapper typeMapper;
    @Autowired
    private XcxContentMapper contentMapper;

    /**
     * 查询小程序--栏目
     *
     * @param id 小程序--栏目主键
     * @return 小程序--栏目
     */
    @Override
    public XcxColumn selectXcxColumnById(Long id)
    {
        return xcxColumnMapper.selectXcxColumnById(id);
    }

    /**
     * 查询小程序--栏目列表
     *
     * @param xcxColumn 小程序--栏目
     * @return 小程序--栏目
     */
    @Override
    public List<XcxColumn> selectXcxColumnList(XcxColumn xcxColumn)
    {
        List<XcxColumn> xcxColumns = xcxColumnMapper.selectXcxColumnList(xcxColumn);
        if (!CollectionUtils.isEmpty(xcxColumns)){
            for (XcxColumn column:xcxColumns){
                //查询栏目分类名称
                 String typeId = column.getTypeId();
                if (StringUtils.isNotEmpty(typeId)){
                    List<String> idList = Arrays.asList(typeId.split(","));
                    List<Integer> typeIdList = idList.stream().map(Integer::parseInt).collect(Collectors.toList());
                    List<XcxType> typeList = typeMapper.selectTypeByIdList(typeIdList);
                    String typeName = "";
                    if (!CollectionUtils.isEmpty(typeList)){
                        for (XcxType type:typeList){
                            typeName += type.getTypeName()+" ";
                        }
                    }
                    column.setTypeName(typeName);
                }
            }
        }
        return xcxColumns;
    }

    /**
     * 新增小程序--栏目
     *
     * @param xcxColumn 小程序--栏目
     * @return 结果
     */
    @Override
    public AjaxResult insertXcxColumn(XcxColumn xcxColumn)
    {
        Integer count = xcxColumnMapper.selectSort(xcxColumn.getColumnSort(),null,xcxColumn.getTypeId());
        if (count>0){
            return AjaxResult.error("序号存在重复，请重新填写");
        }
        //展示形式默认为0
        xcxColumn.setShowType(0);
        xcxColumn.setCreateTime(DateUtils.getNowDate());
        xcxColumnMapper.insertXcxColumn(xcxColumn);
        return AjaxResult.success("操作成功");
    }

    /**
     * 修改小程序--栏目
     *
     * @param xcxColumn 小程序--栏目
     * @return 结果
     */
    @Override
    public AjaxResult updateXcxColumn(XcxColumn xcxColumn)
    {
        Integer count = xcxColumnMapper.selectSort(xcxColumn.getColumnSort(),xcxColumn.getId(),xcxColumn.getTypeId());
        if (count>0){
            return AjaxResult.error("序号存在重复，请重新填写");
        }
        xcxColumnMapper.updateXcxColumn(xcxColumn);
        return AjaxResult.success("操作成功");
    }

    /**
     * 批量删除小程序--栏目
     *
     * @param ids 需要删除的小程序--栏目主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteXcxColumnByIds(Long[] ids)
    {
        //删除栏目下内容
        contentMapper.deleteByColumnIds(ids);
        //删除栏目
        xcxColumnMapper.deleteXcxColumnByIds(ids);
        return 1;
    }

    /**
     * 删除小程序--栏目信息
     *
     * @param id 小程序--栏目主键
     * @return 结果
     */
    @Override
    public int deleteXcxColumnById(Long id)
    {
        return xcxColumnMapper.deleteXcxColumnById(id);
    }


    /**
     * 根据类型id查询
     * @param typeId
     * @return
     */
    @Override
    public List<XcxColumn> selectByTypeId(Integer typeId) {

        return xcxColumnMapper.selectByTypeId(typeId);
    }
}
