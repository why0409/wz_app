package com.ruoyi.activities.service.impl;


import com.ruoyi.activities.domain.ActivitiesInfo;
import com.ruoyi.activities.domain.vo.ActivitiesInfoHomestayVo;
import com.ruoyi.activities.mapper.ActivitiesInfoMapper;
import com.ruoyi.activities.service.IActivitiesInfoService;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 活动信息Service业务层处理
 *
 * @author ruoyi
 * @date 2024-06-20
 */
@Service
public class ActivitiesInfoServiceImpl implements IActivitiesInfoService
{
    @Autowired
    private ActivitiesInfoMapper activitiesInfoMapper;

    /**
     * 查询活动信息
     *
     * @param id 活动信息主键
     * @return 活动信息
     */
    @Override
    public ActivitiesInfo selectActivitiesInfoById(Long id)
    {
        return activitiesInfoMapper.selectActivitiesInfoById(id);
    }

    /**
     * 查询活动信息列表
     *
     * @param activitiesInfo 活动信息
     * @return 活动信息
     */
    @Override
    public List<ActivitiesInfo> selectActivitiesInfoList(ActivitiesInfo activitiesInfo)
    {
        return activitiesInfoMapper.selectActivitiesInfoList(activitiesInfo);
    }

    /**
     * 新增活动信息
     *
     * @param activitiesInfo 活动信息
     * @return 结果
     */
    @Override
    public int insertActivitiesInfo(ActivitiesInfo activitiesInfo)
    {
        activitiesInfo.setCreateTime(DateUtils.getNowDate());
        return activitiesInfoMapper.insertActivitiesInfo(activitiesInfo);
    }

    /**
     * 修改活动信息
     *
     * @param activitiesInfo 活动信息
     * @return 结果
     */
    @Override
    public int updateActivitiesInfo(ActivitiesInfo activitiesInfo)
    {
        activitiesInfo.setUpdateTime(DateUtils.getNowDate());
        return activitiesInfoMapper.updateActivitiesInfo(activitiesInfo);
    }

    /**
     * 批量删除活动信息
     *
     * @param ids 需要删除的活动信息主键
     * @return 结果
     */
    @Override
    public int deleteActivitiesInfoByIds(Long[] ids)
    {
        return activitiesInfoMapper.deleteActivitiesInfoByIds(ids);
    }

    /**
     * 删除活动信息信息
     *
     * @param id 活动信息主键
     * @return 结果
     */
    @Override
    public int deleteActivitiesInfoById(Long id)
    {
        return activitiesInfoMapper.deleteActivitiesInfoById(id);
    }

    @Override
    public ActivitiesInfo selectNormalActivitiesInfo(String status){
        return activitiesInfoMapper.selectNormalActivitiesInfo(status);
    }

    @Override
    public List<ActivitiesInfoHomestayVo> getActivitiesListByHomestayId(Long homestayId){
        return activitiesInfoMapper.getActivitiesListByHomestayId(homestayId);
    }
}
