package com.ruoyi.electricity.service.impl;

import com.ruoyi.electricity.domain.YdEnterpriseInfo;
import com.ruoyi.electricity.mapper.YdEnterpriseInfoMapper;
import com.ruoyi.electricity.service.IYdEnterpriseInfoService;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用电企业信息Service业务层处理
 *
 * @author ruoyi
 * @date 2024-11-04
 */
@Service
public class YdEnterpriseInfoServiceImpl implements IYdEnterpriseInfoService
{
    @Autowired
    private YdEnterpriseInfoMapper ydEnterpriseInfoMapper;

    /**
     * 查询用电企业信息
     *
     * @param id 用电企业信息主键
     * @return 用电企业信息
     */
    @Override
    public YdEnterpriseInfo selectYdEnterpriseInfoById(Long id)
    {
        return ydEnterpriseInfoMapper.selectYdEnterpriseInfoById(id);
    }

    /**
     * 查询用电企业信息列表
     *
     * @param ydEnterpriseInfo 用电企业信息
     * @return 用电企业信息
     */
    @Override
    public List<YdEnterpriseInfo> selectYdEnterpriseInfoList(YdEnterpriseInfo ydEnterpriseInfo)
    {
        return ydEnterpriseInfoMapper.selectYdEnterpriseInfoList(ydEnterpriseInfo);
    }

    /**
     * 新增用电企业信息
     *
     * @param ydEnterpriseInfo 用电企业信息
     * @return 结果
     */
    @Override
    public int insertYdEnterpriseInfo(YdEnterpriseInfo ydEnterpriseInfo)
    {
        ydEnterpriseInfo.setCreateTime(DateUtils.getNowDate());
        return ydEnterpriseInfoMapper.insertYdEnterpriseInfo(ydEnterpriseInfo);
    }

    /**
     * 修改用电企业信息
     *
     * @param ydEnterpriseInfo 用电企业信息
     * @return 结果
     */
    @Override
    public int updateYdEnterpriseInfo(YdEnterpriseInfo ydEnterpriseInfo)
    {
        ydEnterpriseInfo.setUpdateTime(DateUtils.getNowDate());
        return ydEnterpriseInfoMapper.updateYdEnterpriseInfo(ydEnterpriseInfo);
    }

    /**
     * 批量删除用电企业信息
     *
     * @param ids 需要删除的用电企业信息主键
     * @return 结果
     */
    @Override
    public int deleteYdEnterpriseInfoByIds(Long[] ids)
    {
        return ydEnterpriseInfoMapper.deleteYdEnterpriseInfoByIds(ids);
    }

    /**
     * 删除用电企业信息信息
     *
     * @param id 用电企业信息主键
     * @return 结果
     */
    @Override
    public int deleteYdEnterpriseInfoById(Long id)
    {
        return ydEnterpriseInfoMapper.deleteYdEnterpriseInfoById(id);
    }
}
