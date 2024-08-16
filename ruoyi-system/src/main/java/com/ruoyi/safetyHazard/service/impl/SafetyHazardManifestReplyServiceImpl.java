package com.ruoyi.safetyHazard.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.safetyHazard.domain.SafetyHazardManifestReply;
import com.ruoyi.safetyHazard.mapper.SafetyHazardManifestReplyMapper;
import com.ruoyi.safetyHazard.service.ISafetyHazardManifestReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 安全隐患-清单-回复Service业务层处理
 *
 * @author ruoyi
 * @date 2024-08-12
 */
@Service
public class SafetyHazardManifestReplyServiceImpl implements ISafetyHazardManifestReplyService
{
    @Autowired
    private SafetyHazardManifestReplyMapper safetyHazardManifestReplyMapper;

    /**
     * 查询安全隐患-清单-回复
     *
     * @param id 安全隐患-清单-回复主键
     * @return 安全隐患-清单-回复
     */
    @Override
    public SafetyHazardManifestReply selectSafetyHazardManifestReplyById(Long id)
    {
        return safetyHazardManifestReplyMapper.selectSafetyHazardManifestReplyById(id);
    }

    /**
     * 查询安全隐患-清单-回复列表
     *
     * @param safetyHazardManifestReply 安全隐患-清单-回复
     * @return 安全隐患-清单-回复
     */
    @Override
    public List<SafetyHazardManifestReply> selectSafetyHazardManifestReplyList(SafetyHazardManifestReply safetyHazardManifestReply)
    {
        return safetyHazardManifestReplyMapper.selectSafetyHazardManifestReplyList(safetyHazardManifestReply);
    }

    /**
     * 新增安全隐患-清单-回复
     *
     * @param safetyHazardManifestReply 安全隐患-清单-回复
     * @return 结果
     */
    @Override
    public int insertSafetyHazardManifestReply(SafetyHazardManifestReply safetyHazardManifestReply)
    {
        safetyHazardManifestReply.setCreateTime(DateUtils.getNowDate());
        return safetyHazardManifestReplyMapper.insertSafetyHazardManifestReply(safetyHazardManifestReply);
    }

    /**
     * 修改安全隐患-清单-回复
     *
     * @param safetyHazardManifestReply 安全隐患-清单-回复
     * @return 结果
     */
    @Override
    public int updateSafetyHazardManifestReply(SafetyHazardManifestReply safetyHazardManifestReply)
    {
        safetyHazardManifestReply.setUpdateTime(DateUtils.getNowDate());
        return safetyHazardManifestReplyMapper.updateSafetyHazardManifestReply(safetyHazardManifestReply);
    }

    /**
     * 批量删除安全隐患-清单-回复
     *
     * @param ids 需要删除的安全隐患-清单-回复主键
     * @return 结果
     */
    @Override
    public int deleteSafetyHazardManifestReplyByIds(Long[] ids)
    {
        return safetyHazardManifestReplyMapper.deleteSafetyHazardManifestReplyByIds(ids);
    }

    /**
     * 删除安全隐患-清单-回复信息
     *
     * @param id 安全隐患-清单-回复主键
     * @return 结果
     */
    @Override
    public int deleteSafetyHazardManifestReplyById(Long id)
    {
        return safetyHazardManifestReplyMapper.deleteSafetyHazardManifestReplyById(id);
    }

    @Override
    public SafetyHazardManifestReply selectByManifestId(String manifestId){
        return safetyHazardManifestReplyMapper.selectByManifestId(manifestId);
    }

    @Override
    public int updateByManifestId(SafetyHazardManifestReply safetyHazardManifestReply){
        safetyHazardManifestReply.setUpdateTime(DateUtils.getNowDate());
        return safetyHazardManifestReplyMapper.updateByManifestId(safetyHazardManifestReply);
    }
}
