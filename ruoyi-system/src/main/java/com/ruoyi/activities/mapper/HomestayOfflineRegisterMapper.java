package com.ruoyi.activities.mapper;


import com.ruoyi.activities.domain.HomestayOfflineRegister;
import com.ruoyi.activities.domain.vo.HomestayOfflineRegisterVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 民宿活动线下劵注册信息Mapper接口
 *
 * @author ruoyi
 * @date 2024-07-10
 */
public interface HomestayOfflineRegisterMapper
{
    /**
     * 查询民宿活动线下劵注册信息
     *
     * @param id 民宿活动线下劵注册信息主键
     * @return 民宿活动线下劵注册信息
     */
    public HomestayOfflineRegister selectHomestayOfflineRegisterById(Long id);

    /**
     * 查询民宿活动线下劵注册信息列表
     *
     * @param homestayOfflineRegister 民宿活动线下劵注册信息
     * @return 民宿活动线下劵注册信息集合
     */
    public List<HomestayOfflineRegister> selectHomestayOfflineRegisterList(HomestayOfflineRegister homestayOfflineRegister);

    /**
     * 新增民宿活动线下劵注册信息
     *
     * @param homestayOfflineRegister 民宿活动线下劵注册信息
     * @return 结果
     */
    public int insertHomestayOfflineRegister(HomestayOfflineRegister homestayOfflineRegister);

    /**
     * 修改民宿活动线下劵注册信息
     *
     * @param homestayOfflineRegister 民宿活动线下劵注册信息
     * @return 结果
     */
    public int updateHomestayOfflineRegister(HomestayOfflineRegister homestayOfflineRegister);

    /**
     * 删除民宿活动线下劵注册信息
     *
     * @param id 民宿活动线下劵注册信息主键
     * @return 结果
     */
    public int deleteHomestayOfflineRegisterById(Long id);

    /**
     * 批量删除民宿活动线下劵注册信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHomestayOfflineRegisterByIds(Long[] ids);

    public int getTodayCountByIdNumber(@Param("activitiesId") Long activitiesId,
                                       @Param("idNumber") String idNumber);

    public List<HomestayOfflineRegisterVo> selectHomestayOfflineRegisterVoList(HomestayOfflineRegister homestayOfflineRegister);
}
