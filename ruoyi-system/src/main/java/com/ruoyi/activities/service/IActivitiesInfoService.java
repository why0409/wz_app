package com.ruoyi.activities.service;

import com.ruoyi.activities.domain.ActivitiesInfo;
import com.ruoyi.activities.domain.vo.ActivitiesInfoHomestayVo;

import java.util.List;

/**
 * 活动信息Service接口
 *
 * @author ruoyi
 * @date 2024-06-20
 */
public interface IActivitiesInfoService
{
    /**
     * 查询活动信息
     *
     * @param id 活动信息主键
     * @return 活动信息
     */
    public ActivitiesInfo selectActivitiesInfoById(Long id);

    /**
     * 查询活动信息列表
     *
     * @param activitiesInfo 活动信息
     * @return 活动信息集合
     */
    public List<ActivitiesInfo> selectActivitiesInfoList(ActivitiesInfo activitiesInfo);

    /**
     * 新增活动信息
     *
     * @param activitiesInfo 活动信息
     * @return 结果
     */
    public int insertActivitiesInfo(ActivitiesInfo activitiesInfo);

    /**
     * 修改活动信息
     *
     * @param activitiesInfo 活动信息
     * @return 结果
     */
    public int updateActivitiesInfo(ActivitiesInfo activitiesInfo);

    /**
     * 批量删除活动信息
     *
     * @param ids 需要删除的活动信息主键集合
     * @return 结果
     */
    public int deleteActivitiesInfoByIds(Long[] ids);

    /**
     * 删除活动信息信息
     *
     * @param id 活动信息主键
     * @return 结果
     */
    public int deleteActivitiesInfoById(Long id);

    public ActivitiesInfo selectNormalActivitiesInfo(String status);

    public List<ActivitiesInfoHomestayVo> getActivitiesListByHomestayId(Long homestayId);
}
