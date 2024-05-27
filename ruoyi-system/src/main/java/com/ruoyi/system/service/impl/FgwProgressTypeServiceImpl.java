package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FgwProgressTypeMapper;
import com.ruoyi.system.domain.FgwProgressType;
import com.ruoyi.system.service.IFgwProgressTypeService;

/**
 * 项目进度类型Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-24
 */
@Service
public class FgwProgressTypeServiceImpl implements IFgwProgressTypeService 
{
    @Autowired
    private FgwProgressTypeMapper fgwProgressTypeMapper;

    /**
     * 查询项目进度类型
     * 
     * @param id 项目进度类型主键
     * @return 项目进度类型
     */
    @Override
    public FgwProgressType selectFgwProgressTypeById(Long id)
    {
        return fgwProgressTypeMapper.selectFgwProgressTypeById(id);
    }

    /**
     * 查询项目进度类型列表
     * 
     * @param fgwProgressType 项目进度类型
     * @return 项目进度类型
     */
    @Override
    public List<FgwProgressType> selectFgwProgressTypeList(FgwProgressType fgwProgressType)
    {
        return fgwProgressTypeMapper.selectFgwProgressTypeList(fgwProgressType);
    }

    /**
     * 新增项目进度类型
     * 
     * @param fgwProgressType 项目进度类型
     * @return 结果
     */
    @Override
    public int insertFgwProgressType(FgwProgressType fgwProgressType)
    {
        fgwProgressType.setCreateTime(DateUtils.getNowDate());
        return fgwProgressTypeMapper.insertFgwProgressType(fgwProgressType);
    }

    /**
     * 批量保存，先删后插
     * @param fgwProgressTypeList
     * @return
     */
    @Override
    public int saveList(List<FgwProgressType> fgwProgressTypeList) {
        //根据父键删除
        Long parentId = fgwProgressTypeList.get(0).getpId();
        if(parentId!=null){
            fgwProgressTypeMapper.deleteInfoByPId(parentId);
        }
        int value = 0;
        for(FgwProgressType fgwProgressType:fgwProgressTypeList){
            value += this.insertFgwProgressType(fgwProgressType);
        }
        return value;
    }
    /**
     * 修改项目进度类型
     * 
     * @param fgwProgressType 项目进度类型
     * @return 结果
     */
    @Override
    public int updateFgwProgressType(FgwProgressType fgwProgressType)
    {
        fgwProgressType.setUpdateTime(DateUtils.getNowDate());
        return fgwProgressTypeMapper.updateFgwProgressType(fgwProgressType);
    }

    /**
     * 批量删除项目进度类型
     * 
     * @param ids 需要删除的项目进度类型主键
     * @return 结果
     */
    @Override
    public int deleteFgwProgressTypeByIds(Long[] ids)
    {
        return fgwProgressTypeMapper.deleteFgwProgressTypeByIds(ids);
    }

    /**
     * 删除项目进度类型信息
     * 
     * @param id 项目进度类型主键
     * @return 结果
     */
    @Override
    public int deleteFgwProgressTypeById(Long id)
    {
        return fgwProgressTypeMapper.deleteFgwProgressTypeById(id);
    }
}
