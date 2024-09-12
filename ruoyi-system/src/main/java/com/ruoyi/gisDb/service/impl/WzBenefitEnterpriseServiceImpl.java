package com.ruoyi.gisDb.service.impl;

import com.ruoyi.common.utils.DateUtils;

import com.ruoyi.gisDb.domain.WzBenefitEnterprise;
import com.ruoyi.gisDb.mapper.WzBenefitEnterpriseMapper;
import com.ruoyi.gisDb.service.IWzBenefitEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 惠企政策Service业务层处理
 *
 * @author ruoyi
 * @date 2023-04-03
 */
@Service
public class WzBenefitEnterpriseServiceImpl implements IWzBenefitEnterpriseService
{
    @Autowired
    private WzBenefitEnterpriseMapper wzBenefitEnterpriseMapper;

    /**
     * 查询惠企政策
     *
     * @param id 惠企政策主键
     * @return 惠企政策
     */
    @Override
    public WzBenefitEnterprise selectWzBenefitEnterpriseById(Long id)
    {
        return wzBenefitEnterpriseMapper.selectWzBenefitEnterpriseById(id);
    }

    /**
     * 查询惠企政策列表
     *
     * @param wzBenefitEnterprise 惠企政策
     * @return 惠企政策
     */
    @Override
    public List<WzBenefitEnterprise> selectWzBenefitEnterpriseList(WzBenefitEnterprise wzBenefitEnterprise)
    {
        return wzBenefitEnterpriseMapper.selectWzBenefitEnterpriseList(wzBenefitEnterprise);
    }

    /**
     * 新增惠企政策
     *
     * @param wzBenefitEnterprise 惠企政策
     * @return 结果
     */
    @Override
    public int insertWzBenefitEnterprise(WzBenefitEnterprise wzBenefitEnterprise)
    {
        wzBenefitEnterprise.setCreateTime(DateUtils.getNowDate());
        return wzBenefitEnterpriseMapper.insertWzBenefitEnterprise(wzBenefitEnterprise);
    }

    /**
     * 修改惠企政策
     *
     * @param wzBenefitEnterprise 惠企政策
     * @return 结果
     */
    @Override
    public int updateWzBenefitEnterprise(WzBenefitEnterprise wzBenefitEnterprise)
    {
        wzBenefitEnterprise.setUpdateTime(DateUtils.getNowDate());
        return wzBenefitEnterpriseMapper.updateWzBenefitEnterprise(wzBenefitEnterprise);
    }

    /**
     * 批量删除惠企政策
     *
     * @param ids 需要删除的惠企政策主键
     * @return 结果
     */
    @Override
    public int deleteWzBenefitEnterpriseByIds(Long[] ids)
    {
        return wzBenefitEnterpriseMapper.deleteWzBenefitEnterpriseByIds(ids);
    }

    /**
     * 删除惠企政策信息
     *
     * @param id 惠企政策主键
     * @return 结果
     */
    @Override
    public int deleteWzBenefitEnterpriseById(Long id)
    {
        return wzBenefitEnterpriseMapper.deleteWzBenefitEnterpriseById(id);
    }
}
