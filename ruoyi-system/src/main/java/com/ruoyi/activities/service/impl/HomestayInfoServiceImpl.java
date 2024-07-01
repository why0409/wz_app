package com.ruoyi.activities.service.impl;

import com.ruoyi.activities.domain.HomestayInfo;
import com.ruoyi.activities.domain.vo.HomestayInfoVo;
import com.ruoyi.activities.mapper.HomestayInfoMapper;
import com.ruoyi.activities.service.IHomestayInfoService;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 民宿信息Service业务层处理
 *
 * @author ruoyi
 * @date 2024-06-20
 */
@Service
public class HomestayInfoServiceImpl implements IHomestayInfoService
{
    @Autowired
    private HomestayInfoMapper homestayInfoMapper;

    /**
     * 查询民宿信息
     *
     * @param id 民宿信息主键
     * @return 民宿信息
     */
    @Override
    public HomestayInfo selectHomestayInfoById(Long id)
    {
        return homestayInfoMapper.selectHomestayInfoById(id);
    }

    /**
     * 查询民宿信息列表
     *
     * @param homestayInfo 民宿信息
     * @return 民宿信息
     */
    @Override
    public List<HomestayInfo> selectHomestayInfoList(HomestayInfo homestayInfo)
    {
        return homestayInfoMapper.selectHomestayInfoList(homestayInfo);
    }

    /**
     * 新增民宿信息
     *
     * @param homestayInfo 民宿信息
     * @return 结果
     */
    @Override
    public int insertHomestayInfo(HomestayInfo homestayInfo)
    {
        homestayInfo.setCreateTime(DateUtils.getNowDate());
        return homestayInfoMapper.insertHomestayInfo(homestayInfo);
    }

    /**
     * 修改民宿信息
     *
     * @param homestayInfo 民宿信息
     * @return 结果
     */
    @Override
    public int updateHomestayInfo(HomestayInfo homestayInfo)
    {
        homestayInfo.setUpdateTime(DateUtils.getNowDate());
        return homestayInfoMapper.updateHomestayInfo(homestayInfo);
    }

    /**
     * 批量删除民宿信息
     *
     * @param ids 需要删除的民宿信息主键
     * @return 结果
     */
    @Override
    public int deleteHomestayInfoByIds(Long[] ids)
    {
        return homestayInfoMapper.deleteHomestayInfoByIds(ids);
    }

    /**
     * 删除民宿信息信息
     *
     * @param id 民宿信息主键
     * @return 结果
     */
    @Override
    public int deleteHomestayInfoById(Long id)
    {
        return homestayInfoMapper.deleteHomestayInfoById(id);
    }

    @Override
    public int checkVerifyPermission(String phone, Long activitiesId){
        return homestayInfoMapper.checkVerifyPermission(phone, activitiesId);
    }

    @Override
    public List<HomestayInfo> getHomestayInfoListByVerifyPhone(String phone, Long activitiesId){
        return homestayInfoMapper.getHomestayInfoListByVerifyPhone(phone, activitiesId);
    }

    @Override
    public List<HomestayInfo> getHomestayInfoByIds(List<Long> ids){
        return homestayInfoMapper.getHomestayInfoByIds(ids);
    }

    @Override
    public List<HomestayInfoVo> getHomestayListOrderByWinstatus(Long activitiesId){
        return homestayInfoMapper.getHomestayListOrderByWinstatus(activitiesId);
    }
}
