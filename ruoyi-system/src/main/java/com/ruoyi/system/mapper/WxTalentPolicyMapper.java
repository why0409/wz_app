package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.WxTalentPolicy;

/**
 * 人才政策Mapper接口
 * 
 * @author ruoyi
 * @date 2023-02-22
 */
public interface WxTalentPolicyMapper 
{
    /**
     * 查询人才政策
     * 
     * @param uuid 人才政策主键
     * @return 人才政策
     */
    public WxTalentPolicy selectWxTalentPolicyByUuid(String uuid);

    /**
     * 查询人才政策列表
     * 
     * @param wxTalentPolicy 人才政策
     * @return 人才政策集合
     */
    public List<WxTalentPolicy> selectWxTalentPolicyList(WxTalentPolicy wxTalentPolicy);

    /**
     * 新增人才政策
     * 
     * @param wxTalentPolicy 人才政策
     * @return 结果
     */
    public int insertWxTalentPolicy(WxTalentPolicy wxTalentPolicy);

    /**
     * 修改人才政策
     * 
     * @param wxTalentPolicy 人才政策
     * @return 结果
     */
    public int updateWxTalentPolicy(WxTalentPolicy wxTalentPolicy);

    /**
     * 删除人才政策
     * 
     * @param uuid 人才政策主键
     * @return 结果
     */
    public int deleteWxTalentPolicyByUuid(String uuid);

    /**
     * 批量删除人才政策
     * 
     * @param uuids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxTalentPolicyByUuids(String[] uuids);

    public int checkSortNum(int sortNum);
}
