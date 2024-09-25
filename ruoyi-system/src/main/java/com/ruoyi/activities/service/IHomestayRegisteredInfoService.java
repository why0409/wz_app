package com.ruoyi.activities.service;


import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.activities.domain.ActivitiesInfo;
import com.ruoyi.activities.domain.HomestayRegisteredInfo;
import com.ruoyi.activities.domain.vo.HomestayRegisteredInfoUseVo;
import com.ruoyi.activities.domain.vo.HomestayRegisteredInfoVo;
import com.ruoyi.activities.domain.vo.RegisteredInfoVoByGovernment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 民宿体验季活动Service接口
 *
 * @author ruoyi
 * @date 2024-06-19
 */
public interface IHomestayRegisteredInfoService
{
    /**
     * 查询民宿体验季活动
     *
     * @param id 民宿体验季活动主键
     * @return 民宿体验季活动
     */
    public HomestayRegisteredInfo selectHomestayRegisteredInfoById(Long id);

    /**
     * 查询民宿体验季活动列表
     *
     * @param homestayRegisteredInfo 民宿体验季活动
     * @return 民宿体验季活动集合
     */
    public List<HomestayRegisteredInfo> selectHomestayRegisteredInfoList(HomestayRegisteredInfo homestayRegisteredInfo);

    /**
     * 新增民宿体验季活动
     *
     * @param homestayRegisteredInfo 民宿体验季活动
     * @return 结果
     */
    public int insertHomestayRegisteredInfo(HomestayRegisteredInfo homestayRegisteredInfo);

    /**
     * 修改民宿体验季活动
     *
     * @param homestayRegisteredInfo 民宿体验季活动
     * @return 结果
     */
    public int updateHomestayRegisteredInfo(HomestayRegisteredInfo homestayRegisteredInfo);

    /**
     * 批量删除民宿体验季活动
     *
     * @param ids 需要删除的民宿体验季活动主键集合
     * @return 结果
     */
    public int deleteHomestayRegisteredInfoByIds(Long[] ids);

    /**
     * 删除民宿体验季活动信息
     *
     * @param id 民宿体验季活动主键
     * @return 结果
     */
    public int deleteHomestayRegisteredInfoById(Long id);

    public int checkCountByWxPhone(String phone, Long activitiesId);

    public int checkCountByIdNumber(String idNumber, Long activitiesId);

    public HomestayRegisteredInfo selectHomestayRegisteredInfoByWxPhone(String phone, Long activitiesId);

    public HomestayRegisteredInfoVo selectHomestayRegisteredInfoVoById(Long id);

    public List<HomestayRegisteredInfoVo> selectHomestayRegisteredInfoVoList(HomestayRegisteredInfo homestayRegisteredInfo);

    public int updateIsWinByIds(String isWin, String winStatus, List<Long> ids);

    public List<Long> selectNotWinIdsByWxPhones(Long activitiesId, List<String> list);

    public List<HomestayRegisteredInfoVo> selectHomestayRegisteredInfoVoByIds(List<Long> ids);

    public int updateHomestayRegisteredInfoByWxPhone(HomestayRegisteredInfo homestayRegisteredInfo);

    public List<ActivitiesInfo> getStaticsCount();

    public List<HomestayRegisteredInfoUseVo> selectHomestayRegisteredInfoUseVoList(Long homestayId, Long activitiesId);

    public List<RegisteredInfoVoByGovernment> selectRegisteredInfoVoByGovernmentList(HomestayRegisteredInfo homestayRegisteredInfo);

    public int getWinCount(Long activitiesId);

    public List<Long> selectNotWinIds(Long activitiesId);

    public List<Long> selectNotWinIdsByWz(Long activitiesId);

    public List<Long> selectNotWinIdsByWhNotWz(Long activitiesId);

    public List<Long> selectNotWinIdsByOther(Long activitiesId);

    public List<HomestayRegisteredInfo> selectHomestayRegisteredInfoByIds(List<Long> ids);

    public int updateIsLotteryingShowByIds(String isLotteryingShow, List<Long> ids);
}
