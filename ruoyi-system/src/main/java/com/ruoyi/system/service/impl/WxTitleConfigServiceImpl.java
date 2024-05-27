package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WxTitleConfigMapper;
import com.ruoyi.system.domain.WxTitleConfig;
import com.ruoyi.system.service.IWxTitleConfigService;

/**
 * 标题配置Service业务层处理
 *
 * @author ruoyi
 * @date 2023-04-24
 */
@Service
public class WxTitleConfigServiceImpl implements IWxTitleConfigService
{
    @Autowired
    private WxTitleConfigMapper wxTitleConfigMapper;

    /**
     * 查询标题配置
     *
     * @param id 标题配置主键
     * @return 标题配置
     */
    @Override
    public WxTitleConfig selectWxTitleConfigById(Long id)
    {
        return wxTitleConfigMapper.selectWxTitleConfigById(id);
    }

    /**
     * 查询标题配置列表
     *
     * @param wxTitleConfig 标题配置
     * @return 标题配置
     */
    @Override
    public List<WxTitleConfig> selectWxTitleConfigList(WxTitleConfig wxTitleConfig)
    {
        return wxTitleConfigMapper.selectWxTitleConfigList(wxTitleConfig);
    }

    /**
     * 新增标题配置
     *
     * @param wxTitleConfig 标题配置
     * @return 结果
     */
    @Override
    public int insertWxTitleConfig(WxTitleConfig wxTitleConfig)
    {
        wxTitleConfig.setCreateTime(DateUtils.getNowDate());
        return wxTitleConfigMapper.insertWxTitleConfig(wxTitleConfig);
    }

    /**
     * 修改标题配置
     *
     * @param wxTitleConfig 标题配置
     * @return 结果
     */
    @Override
    public int updateWxTitleConfig(WxTitleConfig wxTitleConfig)
    {
        wxTitleConfig.setUpdateTime(DateUtils.getNowDate());
        return wxTitleConfigMapper.updateWxTitleConfig(wxTitleConfig);
    }

    /**
     * 批量删除标题配置
     *
     * @param ids 需要删除的标题配置主键
     * @return 结果
     */
    @Override
    public int deleteWxTitleConfigByIds(Long[] ids)
    {
        return wxTitleConfigMapper.deleteWxTitleConfigByIds(ids);
    }

    /**
     * 删除标题配置信息
     *
     * @param id 标题配置主键
     * @return 结果
     */
    @Override
    public int deleteWxTitleConfigById(Long id)
    {
        return wxTitleConfigMapper.deleteWxTitleConfigById(id);
    }
}
