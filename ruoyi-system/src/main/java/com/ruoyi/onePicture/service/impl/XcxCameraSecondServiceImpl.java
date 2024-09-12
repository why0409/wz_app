package com.ruoyi.onePicture.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.onePicture.domain.XcxCameraColumn;
import com.ruoyi.onePicture.domain.XcxCameraType;
import com.ruoyi.onePicture.mapper.*;
import com.ruoyi.onePicture.service.IXcxCameraSecondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 小程序--栏目Service业务层处理
 *
 * @author ruoyi
 * @date 2023-10-16
 */
@Service
public class XcxCameraSecondServiceImpl implements IXcxCameraSecondService
{


    @Autowired
    private XcxCameraFirstMapper xcxCameraFirst;
    @Autowired
    private XcxCameraSecondMapper xcxCameraSecond;
    @Autowired
    private XcxCameraThirdMapper xcxCameraThird;

    /**
     * 查询小程序--栏目
     *
     * @param id 小程序--栏目主键
     * @return 小程序--栏目
     */
    @Override
    public XcxCameraColumn selectXcxCameraColumnById(Long id)
    {
        return xcxCameraSecond.selectXcxCameraColumnById(id);
    }

    /**
     * 查询小程序--栏目列表
     *
     * @param xcxCameraColumn 小程序--栏目
     * @return 小程序--栏目
     */
    @Override
    public List<XcxCameraColumn> selectXcxCameraColumnList(XcxCameraColumn xcxCameraColumn)
    {
        List<XcxCameraColumn> xcxColumns = xcxCameraSecond.selectXcxCameraColumnList(xcxCameraColumn);
        if (!CollectionUtils.isEmpty(xcxColumns)){
            for (XcxCameraColumn column : xcxColumns){
                //查询栏目分类名称
                 String typeId = column.getTypeId();
                if (StringUtils.isNotEmpty(typeId)){
                    List<String> idList = Arrays.asList(typeId.split(","));
                    List<Integer> typeIdList = idList.stream().map(Integer::parseInt).collect(Collectors.toList());
                    List<XcxCameraType> typeList = xcxCameraFirst.selectTypeByIdList(typeIdList);
                    String typeName = "";
                    if (!CollectionUtils.isEmpty(typeList)){
                        for (XcxCameraType type:typeList){
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
     * @param xcxCameraColumn 小程序--栏目
     * @return 结果
     */
    @Override
    public AjaxResult insertXcxCameraColumn(XcxCameraColumn xcxCameraColumn)
    {
        Integer count = xcxCameraSecond.selectSort(xcxCameraColumn.getColumnSort(),null,xcxCameraColumn.getTypeId());
        if (count>0){
            return AjaxResult.error("序号存在重复，请重新填写");
        }
        //展示形式默认为0
        xcxCameraColumn.setShowType(0);
        xcxCameraColumn.setCreateTime(DateUtils.getNowDate());
        xcxCameraSecond.insertXcxCameraColumn(xcxCameraColumn);
        return AjaxResult.success("操作成功");
    }

    /**
     * 修改小程序--栏目
     *
     * @param xcxCameraColumn 小程序--栏目
     * @return 结果
     */
    @Override
    public AjaxResult updateXcxCameraColumn(XcxCameraColumn xcxCameraColumn)
    {
        Integer count = xcxCameraSecond.selectSort(xcxCameraColumn.getColumnSort(),xcxCameraColumn.getId(),xcxCameraColumn.getTypeId());
        if (count>0){
            return AjaxResult.error("序号存在重复，请重新填写");
        }
        xcxCameraSecond.updateXcxCameraColumn(xcxCameraColumn);
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
    public int deleteXcxCameraColumnByIds(Long[] ids)
    {
        //删除栏目下内容
        xcxCameraThird.deleteByColumnIds(ids);
        //删除栏目
        xcxCameraSecond.deleteXcxCameraColumnByIds(ids);
        return 1;
    }

    /**
     * 删除小程序--栏目信息
     *
     * @param id 小程序--栏目主键
     * @return 结果
     */
    @Override
    public int deleteXcxCameraColumnById(Long id)
    {
        return xcxCameraSecond.deleteXcxCameraColumnById(id);
    }


    /**
     * 根据类型id查询
     * @param typeId
     * @return
     */
    @Override
    public List<XcxCameraColumn> selectByTypeId(Integer typeId) {
        return xcxCameraSecond.selectByTypeId(typeId);
    }


}
