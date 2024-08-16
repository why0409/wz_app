package com.ruoyi.safetyHazard.service;


import com.ruoyi.safetyHazard.domain.SafetyHazardManifestReply;

import java.util.List;

/**
 * 安全隐患-清单-回复Service接口
 *
 * @author ruoyi
 * @date 2024-08-12
 */
public interface ISafetyHazardManifestReplyService
{
    /**
     * 查询安全隐患-清单-回复
     *
     * @param id 安全隐患-清单-回复主键
     * @return 安全隐患-清单-回复
     */
    public SafetyHazardManifestReply selectSafetyHazardManifestReplyById(Long id);

    /**
     * 查询安全隐患-清单-回复列表
     *
     * @param safetyHazardManifestReply 安全隐患-清单-回复
     * @return 安全隐患-清单-回复集合
     */
    public List<SafetyHazardManifestReply> selectSafetyHazardManifestReplyList(SafetyHazardManifestReply safetyHazardManifestReply);

    /**
     * 新增安全隐患-清单-回复
     *
     * @param safetyHazardManifestReply 安全隐患-清单-回复
     * @return 结果
     */
    public int insertSafetyHazardManifestReply(SafetyHazardManifestReply safetyHazardManifestReply);

    /**
     * 修改安全隐患-清单-回复
     *
     * @param safetyHazardManifestReply 安全隐患-清单-回复
     * @return 结果
     */
    public int updateSafetyHazardManifestReply(SafetyHazardManifestReply safetyHazardManifestReply);

    /**
     * 批量删除安全隐患-清单-回复
     *
     * @param ids 需要删除的安全隐患-清单-回复主键集合
     * @return 结果
     */
    public int deleteSafetyHazardManifestReplyByIds(Long[] ids);

    /**
     * 删除安全隐患-清单-回复信息
     *
     * @param id 安全隐患-清单-回复主键
     * @return 结果
     */
    public int deleteSafetyHazardManifestReplyById(Long id);

    public SafetyHazardManifestReply selectByManifestId(String manifestId);

    public int updateByManifestId(SafetyHazardManifestReply safetyHazardManifestReply);
}
