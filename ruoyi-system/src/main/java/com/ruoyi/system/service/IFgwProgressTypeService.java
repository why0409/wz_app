package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.FgwProgressType;

/**
 * 项目进度类型Service接口
 * 
 * @author ruoyi
 * @date 2022-11-24
 */
public interface IFgwProgressTypeService 
{
    /**
     * 查询项目进度类型
     * 
     * @param id 项目进度类型主键
     * @return 项目进度类型
     */
    public FgwProgressType selectFgwProgressTypeById(Long id);

    /**
     * 查询项目进度类型列表
     * 
     * @param fgwProgressType 项目进度类型
     * @return 项目进度类型集合
     */
    public List<FgwProgressType> selectFgwProgressTypeList(FgwProgressType fgwProgressType);

    /**
     * 新增项目进度类型
     * 
     * @param fgwProgressType 项目进度类型
     * @return 结果
     */
    public int insertFgwProgressType(FgwProgressType fgwProgressType);

    /**
     * 先删后插
     * @param fgwProgressTypeList
     * @return
     */
    int saveList(List<FgwProgressType> fgwProgressTypeList);
    /**
     * 修改项目进度类型
     * 
     * @param fgwProgressType 项目进度类型
     * @return 结果
     */
    public int updateFgwProgressType(FgwProgressType fgwProgressType);

    /**
     * 批量删除项目进度类型
     * 
     * @param ids 需要删除的项目进度类型主键集合
     * @return 结果
     */
    public int deleteFgwProgressTypeByIds(Long[] ids);

    /**
     * 删除项目进度类型信息
     * 
     * @param id 项目进度类型主键
     * @return 结果
     */
    public int deleteFgwProgressTypeById(Long id);
}
