package com.ruoyi.activities.mapper;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.activities.domain.HomestayRegisteredInfo;
import com.ruoyi.activities.domain.vo.HomestayRegisteredInfoUseVo;
import com.ruoyi.activities.domain.vo.HomestayRegisteredInfoVo;
import com.ruoyi.activities.domain.vo.RegisteredInfoVoByGovernment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 民宿体验季活动Mapper接口
 *
 * @author ruoyi
 * @date 2024-06-19
 */
public interface HomestayRegisteredInfoMapper
{
    /**
     * 查询民宿体验季活动
     *
     * @param id 民宿体验季活动主键
     * @return 民宿体验季活动
     */
    public HomestayRegisteredInfo selectHomestayRegisteredInfoById(Long id);

    public HomestayRegisteredInfoVo selectHomestayRegisteredInfoVoById(Long id);

    /**
     * 查询民宿体验季活动列表
     *
     * @param homestayRegisteredInfo 民宿体验季活动
     * @return 民宿体验季活动集合
     */
    public List<HomestayRegisteredInfo> selectHomestayRegisteredInfoList(HomestayRegisteredInfo homestayRegisteredInfo);

    public List<HomestayRegisteredInfoVo> selectHomestayRegisteredInfoVoList(HomestayRegisteredInfo homestayRegisteredInfo);

    public List<RegisteredInfoVoByGovernment> selectRegisteredInfoVoByGovernmentList(HomestayRegisteredInfo homestayRegisteredInfo);

    public List<HomestayRegisteredInfoUseVo> selectHomestayRegisteredInfoUseVoList(@Param("homestayId") Long homestayId,
                                                                                   @Param("activitiesId") Long activitiesId);

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

    public int updateHomestayRegisteredInfoByWxPhone(HomestayRegisteredInfo homestayRegisteredInfo);

    /**
     * 删除民宿体验季活动
     *
     * @param id 民宿体验季活动主键
     * @return 结果
     */
    public int deleteHomestayRegisteredInfoById(Long id);

    /**
     * 批量删除民宿体验季活动
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHomestayRegisteredInfoByIds(Long[] ids);

    public int checkCountByWxPhone(@Param("phone") String phone,
                          @Param("activitiesId") Long activitiesId);

    public int checkCountByIdNumber(@Param("idNumber") String idNumber,
                                   @Param("activitiesId") Long activitiesId);

    public HomestayRegisteredInfo selectHomestayRegisteredInfoByWxPhone(@Param("phone") String phone,
                                                                        @Param("activitiesId") Long activitiesId);

    public int updateIsWinByIds(@Param("isWin") String isWin,
                                @Param("winStatus") String winStatus,
                                @Param("ids") List<Long> ids);

    public List<Long> selectNotWinIds(Long activitiesId);

    public List<Long> selectNotWinIdsByWz(Long activitiesId);

    public List<Long> selectNotWinIdsByWhNotWz(Long activitiesId);

    public List<Long> selectNotWinIdsByOther(Long activitiesId);

    public List<Long> selectNotWinIdsByWxPhones(@Param("activitiesId") Long activitiesId,
                                                @Param("list") List<String> list);

    public List<HomestayRegisteredInfoVo> selectHomestayRegisteredInfoVoByIds(List<Long> ids);

    public List<HomestayRegisteredInfo> selectHomestayRegisteredInfoByIds(List<Long> ids);
    public JSONObject getStaticsCount(Long activitiesId);

    public int getStaticsCountByXzqh(@Param("activitiesId") Long activitiesId,
                                     @Param("list") List<String> list);

    public int getWinCount(Long activitiesId);

    public int updateIsLotteryingShowByIds(@Param("isLotteryingShow") String isLotteryingShow,
                                           @Param("ids") List<Long> ids);

    public List<JSONObject> getStaticsCountByProvince(Long activitiesId);
}
