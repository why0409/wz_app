package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WzMorningMapper;
import com.ruoyi.system.domain.WzMorning;
import com.ruoyi.system.service.IWzMorningService;

/**
 * 湾沚早报Service业务层处理
 *
 * @author ruoyi
 * @date 2023-12-25
 */
@Service
public class WzMorningServiceImpl implements IWzMorningService
{
    @Autowired
    private WzMorningMapper wzMorningMapper;

    /**
     * 查询湾沚早报
     *
     * @param id 湾沚早报主键
     * @return 湾沚早报
     */
    @Override
    public WzMorning selectWzMorningById(Long id)
    {
        return wzMorningMapper.selectWzMorningById(id);
    }

    /**
     * 查询湾沚早报列表
     *
     * @param wzMorning 湾沚早报
     * @return 湾沚早报
     */
    @Override
    public List<WzMorning> selectWzMorningList(WzMorning wzMorning)
    {
        return wzMorningMapper.selectWzMorningList(wzMorning);
    }

    /**
     * 新增湾沚早报
     *
     * @param wzMorning 湾沚早报
     * @return 结果
     */
    @Override
    public int insertWzMorning(WzMorning wzMorning)
    {
        wzMorning.setCreateTime(DateUtils.getNowDate());
        return wzMorningMapper.insertWzMorning(wzMorning);
    }

    /**
     * 修改湾沚早报
     *
     * @param wzMorning 湾沚早报
     * @return 结果
     */
    @Override
    public int updateWzMorning(WzMorning wzMorning)
    {
        wzMorning.setUpdateTime(DateUtils.getNowDate());
        return wzMorningMapper.updateWzMorning(wzMorning);
    }

    /**
     * 批量删除湾沚早报
     *
     * @param ids 需要删除的湾沚早报主键
     * @return 结果
     */
    @Override
    public int deleteWzMorningByIds(Long[] ids)
    {
        return wzMorningMapper.deleteWzMorningByIds(ids);
    }

    /**
     * 删除湾沚早报信息
     *
     * @param id 湾沚早报主键
     * @return 结果
     */
    @Override
    public int deleteWzMorningById(Long id)
    {
        return wzMorningMapper.deleteWzMorningById(id);
    }
}
