package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.FgwProgressType;

/**
 * 项目进度类型Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-24
 */
public interface FgwProgressTypeMapper 
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
     * 修改项目进度类型
     * 
     * @param fgwProgressType 项目进度类型
     * @return 结果
     */
    public int updateFgwProgressType(FgwProgressType fgwProgressType);

    /**
     * 删除项目进度类型
     * 
     * @param id 项目进度类型主键
     * @return 结果
     */
    public int deleteFgwProgressTypeById(Long id);

    /**
     * 批量删除项目进度类型
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFgwProgressTypeByIds(Long[] ids);

    /**
     * 根据父键删除信息
     * @param pId
     * @return
     */
    int deleteInfoByPId(Long pId);
}
