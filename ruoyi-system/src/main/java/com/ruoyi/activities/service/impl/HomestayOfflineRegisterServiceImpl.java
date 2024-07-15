package com.ruoyi.activities.service.impl;

import com.ruoyi.activities.domain.HomestayOfflineRegister;
import com.ruoyi.activities.domain.vo.HomestayOfflineRegisterVo;
import com.ruoyi.activities.mapper.HomestayOfflineRegisterMapper;
import com.ruoyi.activities.service.IHomestayOfflineRegisterService;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 民宿活动线下劵注册信息Service业务层处理
 *
 * @author ruoyi
 * @date 2024-07-10
 */
@Service
public class HomestayOfflineRegisterServiceImpl implements IHomestayOfflineRegisterService
{
    @Autowired
    private HomestayOfflineRegisterMapper homestayOfflineRegisterMapper;

    /**
     * 查询民宿活动线下劵注册信息
     *
     * @param id 民宿活动线下劵注册信息主键
     * @return 民宿活动线下劵注册信息
     */
    @Override
    public HomestayOfflineRegister selectHomestayOfflineRegisterById(Long id)
    {
        return homestayOfflineRegisterMapper.selectHomestayOfflineRegisterById(id);
    }

    /**
     * 查询民宿活动线下劵注册信息列表
     *
     * @param homestayOfflineRegister 民宿活动线下劵注册信息
     * @return 民宿活动线下劵注册信息
     */
    @Override
    public List<HomestayOfflineRegister> selectHomestayOfflineRegisterList(HomestayOfflineRegister homestayOfflineRegister)
    {
        return homestayOfflineRegisterMapper.selectHomestayOfflineRegisterList(homestayOfflineRegister);
    }

    /**
     * 新增民宿活动线下劵注册信息
     *
     * @param homestayOfflineRegister 民宿活动线下劵注册信息
     * @return 结果
     */
    @Override
    public int insertHomestayOfflineRegister(HomestayOfflineRegister homestayOfflineRegister)
    {
        homestayOfflineRegister.setCreateTime(DateUtils.getNowDate());
        return homestayOfflineRegisterMapper.insertHomestayOfflineRegister(homestayOfflineRegister);
    }

    /**
     * 修改民宿活动线下劵注册信息
     *
     * @param homestayOfflineRegister 民宿活动线下劵注册信息
     * @return 结果
     */
    @Override
    public int updateHomestayOfflineRegister(HomestayOfflineRegister homestayOfflineRegister)
    {
        homestayOfflineRegister.setUpdateTime(DateUtils.getNowDate());
        return homestayOfflineRegisterMapper.updateHomestayOfflineRegister(homestayOfflineRegister);
    }

    /**
     * 批量删除民宿活动线下劵注册信息
     *
     * @param ids 需要删除的民宿活动线下劵注册信息主键
     * @return 结果
     */
    @Override
    public int deleteHomestayOfflineRegisterByIds(Long[] ids)
    {
        return homestayOfflineRegisterMapper.deleteHomestayOfflineRegisterByIds(ids);
    }

    /**
     * 删除民宿活动线下劵注册信息信息
     *
     * @param id 民宿活动线下劵注册信息主键
     * @return 结果
     */
    @Override
    public int deleteHomestayOfflineRegisterById(Long id)
    {
        return homestayOfflineRegisterMapper.deleteHomestayOfflineRegisterById(id);
    }

    @Override
    public int getTodayCountByIdNumber(Long activitiesId, String idNumber){
        return homestayOfflineRegisterMapper.getTodayCountByIdNumber(activitiesId, idNumber);
    }

    @Override
    public List<HomestayOfflineRegisterVo> selectHomestayOfflineRegisterVoList(HomestayOfflineRegister homestayOfflineRegister){
        return homestayOfflineRegisterMapper.selectHomestayOfflineRegisterVoList(homestayOfflineRegister);
    }
}
