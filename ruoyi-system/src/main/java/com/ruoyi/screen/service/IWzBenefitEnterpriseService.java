package com.ruoyi.screen.service;


import com.ruoyi.screen.domain.WzBenefitEnterprise;
import java.util.List;

/**
 * 惠企政策Service接口
 *
 * @author ruoyi
 * @date 2023-04-03
 */
public interface IWzBenefitEnterpriseService
{
    /**
     * 查询惠企政策
     *
     * @param id 惠企政策主键
     * @return 惠企政策
     */
    public WzBenefitEnterprise selectWzBenefitEnterpriseById(Long id);

    /**
     * 查询惠企政策列表
     *
     * @param wzBenefitEnterprise 惠企政策
     * @return 惠企政策集合
     */
    public List<WzBenefitEnterprise> selectWzBenefitEnterpriseList(WzBenefitEnterprise wzBenefitEnterprise);

    /**
     * 新增惠企政策
     *
     * @param wzBenefitEnterprise 惠企政策
     * @return 结果
     */
    public int insertWzBenefitEnterprise(WzBenefitEnterprise wzBenefitEnterprise);

    /**
     * 修改惠企政策
     *
     * @param wzBenefitEnterprise 惠企政策
     * @return 结果
     */
    public int updateWzBenefitEnterprise(WzBenefitEnterprise wzBenefitEnterprise);

    /**
     * 批量删除惠企政策
     *
     * @param ids 需要删除的惠企政策主键集合
     * @return 结果
     */
    public int deleteWzBenefitEnterpriseByIds(Long[] ids);

    /**
     * 删除惠企政策信息
     *
     * @param id 惠企政策主键
     * @return 结果
     */
    public int deleteWzBenefitEnterpriseById(Long id);
}
