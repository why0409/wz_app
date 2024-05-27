package com.ruoyi.app.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.app.mapper.HkxcPromotionalVideoMapper;
import com.ruoyi.app.domain.HkxcPromotionalVideo;
import com.ruoyi.app.service.IHkxcPromotionalVideoService;

/**
 * 航空新城-宣传片Service业务层处理
 *
 * @author ruoyi
 * @date 2024-05-07
 */
@Service
public class HkxcPromotionalVideoServiceImpl implements IHkxcPromotionalVideoService
{
    @Autowired
    private HkxcPromotionalVideoMapper hkxcPromotionalVideoMapper;

    /**
     * 查询航空新城-宣传片
     *
     * @param id 航空新城-宣传片主键
     * @return 航空新城-宣传片
     */
    @Override
    public HkxcPromotionalVideo selectHkxcPromotionalVideoById(Long id)
    {
        return hkxcPromotionalVideoMapper.selectHkxcPromotionalVideoById(id);
    }

    /**
     * 查询航空新城-宣传片列表
     *
     * @param hkxcPromotionalVideo 航空新城-宣传片
     * @return 航空新城-宣传片
     */
    @Override
    public List<HkxcPromotionalVideo> selectHkxcPromotionalVideoList(HkxcPromotionalVideo hkxcPromotionalVideo)
    {
        return hkxcPromotionalVideoMapper.selectHkxcPromotionalVideoList(hkxcPromotionalVideo);
    }

    /**
     * 新增航空新城-宣传片
     *
     * @param hkxcPromotionalVideo 航空新城-宣传片
     * @return 结果
     */
    @Override
    public int insertHkxcPromotionalVideo(HkxcPromotionalVideo hkxcPromotionalVideo)
    {
        hkxcPromotionalVideo.setCreateTime(DateUtils.getNowDate());
        return hkxcPromotionalVideoMapper.insertHkxcPromotionalVideo(hkxcPromotionalVideo);
    }

    /**
     * 修改航空新城-宣传片
     *
     * @param hkxcPromotionalVideo 航空新城-宣传片
     * @return 结果
     */
    @Override
    public int updateHkxcPromotionalVideo(HkxcPromotionalVideo hkxcPromotionalVideo)
    {
        hkxcPromotionalVideo.setUpdateTime(DateUtils.getNowDate());
        return hkxcPromotionalVideoMapper.updateHkxcPromotionalVideo(hkxcPromotionalVideo);
    }

    /**
     * 批量删除航空新城-宣传片
     *
     * @param ids 需要删除的航空新城-宣传片主键
     * @return 结果
     */
    @Override
    public int deleteHkxcPromotionalVideoByIds(Long[] ids)
    {
        return hkxcPromotionalVideoMapper.deleteHkxcPromotionalVideoByIds(ids);
    }

    /**
     * 删除航空新城-宣传片信息
     *
     * @param id 航空新城-宣传片主键
     * @return 结果
     */
    @Override
    public int deleteHkxcPromotionalVideoById(Long id)
    {
        return hkxcPromotionalVideoMapper.deleteHkxcPromotionalVideoById(id);
    }
}
