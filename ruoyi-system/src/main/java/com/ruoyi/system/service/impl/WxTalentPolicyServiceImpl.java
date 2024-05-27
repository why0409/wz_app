package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WxTalentPolicyMapper;
import com.ruoyi.system.domain.WxTalentPolicy;
import com.ruoyi.system.service.IWxTalentPolicyService;

/**
 * 人才政策Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-02-22
 */
@Service
public class WxTalentPolicyServiceImpl implements IWxTalentPolicyService 
{
    @Autowired
    private WxTalentPolicyMapper wxTalentPolicyMapper;

    /**
     * 查询人才政策
     * 
     * @param uuid 人才政策主键
     * @return 人才政策
     */
    @Override
    public WxTalentPolicy selectWxTalentPolicyByUuid(String uuid)
    {
        return wxTalentPolicyMapper.selectWxTalentPolicyByUuid(uuid);
    }

    /**
     * 查询人才政策列表
     * 
     * @param wxTalentPolicy 人才政策
     * @return 人才政策
     */
    @Override
    public List<WxTalentPolicy> selectWxTalentPolicyList(WxTalentPolicy wxTalentPolicy)
    {
        return wxTalentPolicyMapper.selectWxTalentPolicyList(wxTalentPolicy);
    }

    /**
     * 新增人才政策
     * 
     * @param wxTalentPolicy 人才政策
     * @return 结果
     */
    @Override
    public int insertWxTalentPolicy(WxTalentPolicy wxTalentPolicy)
    {
        return wxTalentPolicyMapper.insertWxTalentPolicy(wxTalentPolicy);
    }

    /**
     * 修改人才政策
     * 
     * @param wxTalentPolicy 人才政策
     * @return 结果
     */
    @Override
    public int updateWxTalentPolicy(WxTalentPolicy wxTalentPolicy)
    {
        return wxTalentPolicyMapper.updateWxTalentPolicy(wxTalentPolicy);
    }

    /**
     * 批量删除人才政策
     * 
     * @param uuids 需要删除的人才政策主键
     * @return 结果
     */
    @Override
    public int deleteWxTalentPolicyByUuids(String[] uuids)
    {
        return wxTalentPolicyMapper.deleteWxTalentPolicyByUuids(uuids);
    }

    /**
     * 删除人才政策信息
     * 
     * @param uuid 人才政策主键
     * @return 结果
     */
    @Override
    public int deleteWxTalentPolicyByUuid(String uuid)
    {
        return wxTalentPolicyMapper.deleteWxTalentPolicyByUuid(uuid);
    }
}
