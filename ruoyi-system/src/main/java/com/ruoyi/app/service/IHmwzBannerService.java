package com.ruoyi.app.service;

import java.util.List;
import com.ruoyi.app.domain.HmwzBanner;

/**
 * 和美湾沚-banner管理Service接口
 *
 * @author ruoyi
 * @date 2024-05-06
 */
public interface IHmwzBannerService
{
    /**
     * 查询和美湾沚-banner管理
     *
     * @param id 和美湾沚-banner管理主键
     * @return 和美湾沚-banner管理
     */
    public HmwzBanner selectHmwzBannerById(Long id);

    /**
     * 查询和美湾沚-banner管理列表
     *
     * @param hmwzBanner 和美湾沚-banner管理
     * @return 和美湾沚-banner管理集合
     */
    public List<HmwzBanner> selectHmwzBannerList(HmwzBanner hmwzBanner);

    /**
     * 新增和美湾沚-banner管理
     *
     * @param hmwzBanner 和美湾沚-banner管理
     * @return 结果
     */
    public int insertHmwzBanner(HmwzBanner hmwzBanner);

    /**
     * 修改和美湾沚-banner管理
     *
     * @param hmwzBanner 和美湾沚-banner管理
     * @return 结果
     */
    public int updateHmwzBanner(HmwzBanner hmwzBanner);

    /**
     * 批量删除和美湾沚-banner管理
     *
     * @param ids 需要删除的和美湾沚-banner管理主键集合
     * @return 结果
     */
    public int deleteHmwzBannerByIds(Long[] ids);

    /**
     * 删除和美湾沚-banner管理信息
     *
     * @param id 和美湾沚-banner管理主键
     * @return 结果
     */
    public int deleteHmwzBannerById(Long id);
}
