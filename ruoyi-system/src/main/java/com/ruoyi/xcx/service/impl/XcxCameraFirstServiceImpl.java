package com.ruoyi.xcx.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.wxuser.domain.WxUser;
import com.ruoyi.wxuser.mapper.WxUserMapper;
import com.ruoyi.xcx.domain.XcxCameraColumn;
import com.ruoyi.xcx.domain.XcxCameraType;
import com.ruoyi.xcx.mapper.*;
import com.ruoyi.xcx.service.IXcxCameraFirstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 小程序---分类Service业务层处理
 *
 * @author ruoyi
 * @date 2023-10-16
 */
@Service
public class XcxCameraFirstServiceImpl implements IXcxCameraFirstService
{
    @Autowired
    private XcxCameraFirstMapper xcxCameraFirst;
    @Autowired
    private XcxCameraSecondMapper xcxCameraSecond;
    @Autowired
    private XcxCameraThirdMapper xcxCameraThird;
    @Autowired
    private WxUserMapper wxUserMapper;

    /**
     * 查询小程序---分类
     *
     * @param id 小程序---分类主键
     * @return 小程序---分类
     */
    @Override
    public XcxCameraType selectXcxCameraTypeById(Long id)
    {
        return xcxCameraFirst.selectXcxCameraTypeById(id);
    }

    /**
     * 查询小程序---分类列表
     *
     * @param xcxCameraType 小程序---分类
     * @return 小程序---分类
     */
    @Override
    public List<XcxCameraType> selectXcxCameraTypeList(XcxCameraType xcxCameraType)
    {
        return xcxCameraFirst.selectXcxCameraTypeList(xcxCameraType);
    }

    /**
     * 新增小程序---分类
     *
     * @param xcxCameraType 小程序---分类
     * @return 结果
     */
    @Override
    public AjaxResult insertXcxCameraType(XcxCameraType xcxCameraType)
    {
        Integer count = xcxCameraFirst.selectSort(xcxCameraType.getSort(),null);
        if (count>0){
            return AjaxResult.error("序号存在重复，请重新填写");
        }
        xcxCameraType.setCreateTime(DateUtils.getNowDate());
        xcxCameraFirst.insertXcxCameraType(xcxCameraType);
        return AjaxResult.success("操作成功");
    }

    /**
     * 修改小程序---分类
     *
     * @param xcxCameraType 小程序---分类
     * @return 结果
     */
    @Override
    public AjaxResult updateXcxCameraType(XcxCameraType xcxCameraType)
    {
        Integer count = xcxCameraFirst.selectSort(xcxCameraType.getSort(),xcxCameraType.getId());
        if (count>0){
            return AjaxResult.error("序号存在重复，请重新填写");
        }
        xcxCameraFirst.updateXcxCameraType(xcxCameraType);
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
    public int deleteXcxCameraTypeByIds(Long[] ids)
    {
        //删除分类
        xcxCameraFirst.deleteXcxCameraTypeByIds(ids);
        List<Long> columnIdList = new ArrayList<>();
        for (int i = 0;i<ids.length;i++){
//            //删除分类对应栏目
//            String replaceTypeId = ids[i]+",";
            //查询分类下所有栏目
            List<XcxCameraColumn> columnList = xcxCameraSecond.selectByTypeId(Integer.valueOf(String.valueOf(ids[i])));
            for (XcxCameraColumn column:columnList){
                columnIdList.add(column.getId());
            }
            xcxCameraSecond.updateTypeId(ids[i]);
        }

        //删除分类下面的poi
        if (!CollectionUtils.isEmpty(columnIdList)){
            xcxCameraThird.deleteByColumnIds(columnIdList.toArray(new Long[columnIdList.size()]));
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
    public int deleteXcxCameraTypeById(Long id)
    {
        return xcxCameraFirst.deleteXcxCameraTypeById(id);
    }

    @Override
    public List<XcxCameraType> getAllTypeAndColumn() {
        //查询所有分类
        List<XcxCameraType> XcxCameraTypeList = xcxCameraFirst.selectXcxCameraTypeList(null);
        //根据分类id查询下属栏目
        if (!CollectionUtils.isEmpty(XcxCameraTypeList)){
            for (XcxCameraType type:XcxCameraTypeList){
                 Integer typeId = type.getId();
                 List<XcxCameraColumn> columnList = xcxCameraSecond.selectByTypeId(typeId);
                 if (!CollectionUtils.isEmpty(columnList)){
                     type.setColumnList(columnList);
                 }
            }
        }
        return XcxCameraTypeList;
    }

    @Override
    public List<XcxCameraType> getAllTypeAndColumnByPermissions(String phone) {
        List<XcxCameraType> xcxCameraTypeList = new ArrayList<>();
        if(StringUtils.isEmpty(phone)) {
            xcxCameraTypeList = xcxCameraFirst.selectXcxCameraTypeList(null);
        } else {
            //权限判断
            WxUser wxUser = wxUserMapper.selectWxUserByPhone(phone);
            String permissions = wxUser.getVideoPermissions();
            if (StringUtils.isEmpty(permissions)) {
                return xcxCameraTypeList ;
            } else {
                List<Integer> permissionsList = Arrays.stream(permissions.split(","))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                xcxCameraTypeList = xcxCameraFirst.selectTypeByIdList(permissionsList);
            }
        }

        //根据分类id查询下属栏目
        if (!CollectionUtils.isEmpty(xcxCameraTypeList)){
            for (XcxCameraType type : xcxCameraTypeList){
                Integer typeId = type.getId();
                List<XcxCameraColumn> columnList = xcxCameraSecond.selectByTypeId(typeId);
                if (!CollectionUtils.isEmpty(columnList)){
                    type.setColumnList(columnList);
                }
            }
        }

        return xcxCameraTypeList;
    }
}
