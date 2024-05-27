package com.ruoyi.app.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.app.mapper.HmwzBannerMapper;
import com.ruoyi.app.domain.HmwzBanner;
import com.ruoyi.app.service.IHmwzBannerService;

/**
 * 和美湾沚-banner管理Service业务层处理
 *
 * @author ruoyi
 * @date 2024-05-06
 */
@Service
public class HmwzBannerServiceImpl implements IHmwzBannerService
{
    @Autowired
    private HmwzBannerMapper hmwzBannerMapper;

    /**
     * 查询和美湾沚-banner管理
     *
     * @param id 和美湾沚-banner管理主键
     * @return 和美湾沚-banner管理
     */
    @Override
    public HmwzBanner selectHmwzBannerById(Long id)
    {
        return hmwzBannerMapper.selectHmwzBannerById(id);
    }

    /**
     * 查询和美湾沚-banner管理列表
     *
     * @param hmwzBanner 和美湾沚-banner管理
     * @return 和美湾沚-banner管理
     */
    @Override
    public List<HmwzBanner> selectHmwzBannerList(HmwzBanner hmwzBanner)
    {
        return hmwzBannerMapper.selectHmwzBannerList(hmwzBanner);
    }

    /**
     * 新增和美湾沚-banner管理
     *
     * @param hmwzBanner 和美湾沚-banner管理
     * @return 结果
     */
    @Override
    public int insertHmwzBanner(HmwzBanner hmwzBanner)
    {
        hmwzBanner.setCreateTime(DateUtils.getNowDate());
        return hmwzBannerMapper.insertHmwzBanner(hmwzBanner);
    }

    /**
     * 修改和美湾沚-banner管理
     *
     * @param hmwzBanner 和美湾沚-banner管理
     * @return 结果
     */
    @Override
    public int updateHmwzBanner(HmwzBanner hmwzBanner)
    {
        hmwzBanner.setUpdateTime(DateUtils.getNowDate());
        return hmwzBannerMapper.updateHmwzBanner(hmwzBanner);
    }

    /**
     * 批量删除和美湾沚-banner管理
     *
     * @param ids 需要删除的和美湾沚-banner管理主键
     * @return 结果
     */
    @Override
    public int deleteHmwzBannerByIds(Long[] ids)
    {
        return hmwzBannerMapper.deleteHmwzBannerByIds(ids);
    }

    /**
     * 删除和美湾沚-banner管理信息
     *
     * @param id 和美湾沚-banner管理主键
     * @return 结果
     */
    @Override
    public int deleteHmwzBannerById(Long id)
    {
        return hmwzBannerMapper.deleteHmwzBannerById(id);
    }
}
