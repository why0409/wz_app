package com.ruoyi.activities.service;


import com.ruoyi.activities.domain.HomestayOfflineRegister;
import com.ruoyi.activities.domain.vo.HomestayOfflineRegisterVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 民宿活动线下劵注册信息Service接口
 *
 * @author ruoyi
 * @date 2024-07-10
 */
public interface IHomestayOfflineRegisterService
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
     * 批量删除民宿活动线下劵注册信息
     *
     * @param ids 需要删除的民宿活动线下劵注册信息主键集合
     * @return 结果
     */
    public int deleteHomestayOfflineRegisterByIds(Long[] ids);

    /**
     * 删除民宿活动线下劵注册信息信息
     *
     * @param id 民宿活动线下劵注册信息主键
     * @return 结果
     */
    public int deleteHomestayOfflineRegisterById(Long id);

    public int getTodayCountByIdNumber(Long activitiesId, String idNumber);

    public List<HomestayOfflineRegisterVo> selectHomestayOfflineRegisterVoList(HomestayOfflineRegister homestayOfflineRegister);
}
