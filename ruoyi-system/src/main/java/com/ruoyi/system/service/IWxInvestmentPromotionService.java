package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.WxInvestmentPromotion;

/**
 * 招商宣传Service接口
 * 
 * @author ruoyi
 * @date 2023-02-22
 */
public interface IWxInvestmentPromotionService 
{
    /**
     * 查询招商宣传
     * 
     * @param uuid 招商宣传主键
     * @return 招商宣传
     */
    public WxInvestmentPromotion selectWxInvestmentPromotionByUuid(String uuid);

    /**
     * 查询招商宣传列表
     * 
     * @param wxInvestmentPromotion 招商宣传
     * @return 招商宣传集合
     */
    public List<WxInvestmentPromotion> selectWxInvestmentPromotionList(WxInvestmentPromotion wxInvestmentPromotion);

    /**
     * 新增招商宣传
     * 
     * @param wxInvestmentPromotion 招商宣传
     * @return 结果
     */
    public int insertWxInvestmentPromotion(WxInvestmentPromotion wxInvestmentPromotion);

    /**
     * 修改招商宣传
     * 
     * @param wxInvestmentPromotion 招商宣传
     * @return 结果
     */
    public int updateWxInvestmentPromotion(WxInvestmentPromotion wxInvestmentPromotion);

    /**
     * 批量删除招商宣传
     * 
     * @param uuids 需要删除的招商宣传主键集合
     * @return 结果
     */
    public int deleteWxInvestmentPromotionByUuids(String[] uuids);

    /**
     * 删除招商宣传信息
     * 
     * @param uuid 招商宣传主键
     * @return 结果
     */
    public int deleteWxInvestmentPromotionByUuid(String uuid);
}
