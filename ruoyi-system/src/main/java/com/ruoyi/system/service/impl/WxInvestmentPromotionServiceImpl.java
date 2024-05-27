package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WxInvestmentPromotionMapper;
import com.ruoyi.system.domain.WxInvestmentPromotion;
import com.ruoyi.system.service.IWxInvestmentPromotionService;

/**
 * 招商宣传Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-02-22
 */
@Service
public class WxInvestmentPromotionServiceImpl implements IWxInvestmentPromotionService 
{
    @Autowired
    private WxInvestmentPromotionMapper wxInvestmentPromotionMapper;

    /**
     * 查询招商宣传
     * 
     * @param uuid 招商宣传主键
     * @return 招商宣传
     */
    @Override
    public WxInvestmentPromotion selectWxInvestmentPromotionByUuid(String uuid)
    {
        return wxInvestmentPromotionMapper.selectWxInvestmentPromotionByUuid(uuid);
    }

    /**
     * 查询招商宣传列表
     * 
     * @param wxInvestmentPromotion 招商宣传
     * @return 招商宣传
     */
    @Override
    public List<WxInvestmentPromotion> selectWxInvestmentPromotionList(WxInvestmentPromotion wxInvestmentPromotion)
    {
        return wxInvestmentPromotionMapper.selectWxInvestmentPromotionList(wxInvestmentPromotion);
    }

    /**
     * 新增招商宣传
     * 
     * @param wxInvestmentPromotion 招商宣传
     * @return 结果
     */
    @Override
    public int insertWxInvestmentPromotion(WxInvestmentPromotion wxInvestmentPromotion)
    {
        return wxInvestmentPromotionMapper.insertWxInvestmentPromotion(wxInvestmentPromotion);
    }

    /**
     * 修改招商宣传
     * 
     * @param wxInvestmentPromotion 招商宣传
     * @return 结果
     */
    @Override
    public int updateWxInvestmentPromotion(WxInvestmentPromotion wxInvestmentPromotion)
    {
        return wxInvestmentPromotionMapper.updateWxInvestmentPromotion(wxInvestmentPromotion);
    }

    /**
     * 批量删除招商宣传
     * 
     * @param uuids 需要删除的招商宣传主键
     * @return 结果
     */
    @Override
    public int deleteWxInvestmentPromotionByUuids(String[] uuids)
    {
        return wxInvestmentPromotionMapper.deleteWxInvestmentPromotionByUuids(uuids);
    }

    /**
     * 删除招商宣传信息
     * 
     * @param uuid 招商宣传主键
     * @return 结果
     */
    @Override
    public int deleteWxInvestmentPromotionByUuid(String uuid)
    {
        return wxInvestmentPromotionMapper.deleteWxInvestmentPromotionByUuid(uuid);
    }
}
