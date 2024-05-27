package com.ruoyi.system.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.system.domain.FgwProProgress;
import com.ruoyi.system.domain.vo.FgwProProgressDto;

/**
 * 项目进度Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-24
 */
public interface FgwProProgressMapper 
{
    /**
     * 查询项目进度
     * 
     * @param id 项目进度主键
     * @return 项目进度
     */
    public FgwProProgressDto selectFgwProProgressById(Long id);

    /**
     * 查询项目进度列表
     * 
     * @param fgwProProgress 项目进度
     * @return 项目进度集合
     */
    public List<FgwProProgress> selectFgwProProgressList(FgwProProgress fgwProProgress);

    /**
     * 新增项目进度
     * 
     * @param fgwProProgress 项目进度
     * @return 结果
     */
    public int insertFgwProProgress(FgwProProgress fgwProProgress);

    /**
     * 修改项目进度
     * 
     * @param fgwProProgress 项目进度
     * @return 结果
     */
    public int updateFgwProProgress(FgwProProgress fgwProProgress);

    /**
     * 删除项目进度
     * 
     * @param id 项目进度主键
     * @return 结果
     */
    public int deleteFgwProProgressById(Long id);

    /**
     * 批量删除项目进度
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFgwProProgressByIds(Long[] ids);

    /**
     *  根据id更新红黄绿标识
     * @author:
     * @date: 2022/11/24 15:27
     * @return
     */
    int updateHhlTypeById(FgwProProgress fgwProProgress);

    List<FgwProProgressDto> selectFgwProProgressDtoList(FgwProProgress fgwProProgress);
    List<FgwProProgressDto> selectListByMap(Map<String,String> map);
}
