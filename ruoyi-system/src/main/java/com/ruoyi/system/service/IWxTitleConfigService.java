package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.WxTitleConfig;

/**
 * 标题配置Service接口
 *
 * @author ruoyi
 * @date 2023-04-24
 */
public interface IWxTitleConfigService
{
    /**
     * 查询标题配置
     *
     * @param id 标题配置主键
     * @return 标题配置
     */
    public WxTitleConfig selectWxTitleConfigById(Long id);

    /**
     * 查询标题配置列表
     *
     * @param wxTitleConfig 标题配置
     * @return 标题配置集合
     */
    public List<WxTitleConfig> selectWxTitleConfigList(WxTitleConfig wxTitleConfig);

    /**
     * 新增标题配置
     *
     * @param wxTitleConfig 标题配置
     * @return 结果
     */
    public int insertWxTitleConfig(WxTitleConfig wxTitleConfig);

    /**
     * 修改标题配置
     *
     * @param wxTitleConfig 标题配置
     * @return 结果
     */
    public int updateWxTitleConfig(WxTitleConfig wxTitleConfig);

    /**
     * 批量删除标题配置
     *
     * @param ids 需要删除的标题配置主键集合
     * @return 结果
     */
    public int deleteWxTitleConfigByIds(Long[] ids);

    /**
     * 删除标题配置信息
     *
     * @param id 标题配置主键
     * @return 结果
     */
    public int deleteWxTitleConfigById(Long id);
}
