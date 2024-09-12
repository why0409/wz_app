package com.ruoyi.gisDb.mapper;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.gisDb.domain.WzBenefitEnterprise;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 惠企政策Mapper接口
 *
 * @author ruoyi
 * @date 2023-04-03
 */
@DataSource(value = DataSourceType.GISDB)
public interface WzBenefitEnterpriseMapper
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
     * 删除惠企政策
     *
     * @param id 惠企政策主键
     * @return 结果
     */
    public int deleteWzBenefitEnterpriseById(Long id);

    /**
     * 批量删除惠企政策
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWzBenefitEnterpriseByIds(Long[] ids);

    public int updateSum(@Param("payFrequencyPerson") String payFrequencyPerson,
                         @Param("payFrequencyEnterprise") String payFrequencyEnterprise,
                         @Param("payAmountPerson") String payAmountPerson,
                         @Param("payAmountEnterprise") String payAmountEnterprise);

    public WzBenefitEnterprise selectOne();
}
