package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.FgwProProgress;
import com.ruoyi.system.domain.vo.FgwProProgressDto;

/**
 * 项目进度Service接口
 * 
 * @author ruoyi
 * @date 2022-11-24
 */
public interface IFgwProProgressService 
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
     * @param fgwProProgressDto 项目进度
     * @return 项目进度集合
     */
    public List<FgwProProgressDto> selectFgwProProgressList(FgwProProgressDto fgwProProgressDto);

    /**
     * 新增项目进度
     * 
     * @param fgwProProgressDto 项目进度
     * @return 结果
     */
    public int insertFgwProProgress(FgwProProgressDto fgwProProgressDto);

    /**
     * 修改项目进度
     * 
     * @param fgwProProgressDto 项目进度
     * @return 结果
     */
    public int updateFgwProProgress(FgwProProgressDto fgwProProgressDto);

    /**
     * 批量删除项目进度
     * 
     * @param ids 需要删除的项目进度主键集合
     * @return 结果
     */
    public int deleteFgwProProgressByIds(Long[] ids);

    /**
     * 删除项目进度信息
     * 
     * @param id 项目进度主键
     * @return 结果
     */
    public int deleteFgwProProgressById(Long id);

    /**
     * 根据id更新红黄绿标识
     * @author:
     * @date: 2022/11/24 15:29
     * @param fgwProProgress
     * @return
     */
    int updateHhlTypeById(FgwProProgress fgwProProgress);
}
