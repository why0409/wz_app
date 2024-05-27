package com.ruoyi.app.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.app.mapper.HmwzVideoMapper;
import com.ruoyi.app.domain.HmwzVideo;
import com.ruoyi.app.service.IHmwzVideoService;

/**
 * 和美湾沚-视频管理Service业务层处理
 *
 * @author ruoyi
 * @date 2024-05-06
 */
@Service
public class HmwzVideoServiceImpl implements IHmwzVideoService
{
    @Autowired
    private HmwzVideoMapper hmwzVideoMapper;

    /**
     * 查询和美湾沚-视频管理
     *
     * @param id 和美湾沚-视频管理主键
     * @return 和美湾沚-视频管理
     */
    @Override
    public HmwzVideo selectHmwzVideoById(Long id)
    {
        return hmwzVideoMapper.selectHmwzVideoById(id);
    }

    /**
     * 查询和美湾沚-视频管理列表
     *
     * @param hmwzVideo 和美湾沚-视频管理
     * @return 和美湾沚-视频管理
     */
    @Override
    public List<HmwzVideo> selectHmwzVideoList(HmwzVideo hmwzVideo)
    {
        return hmwzVideoMapper.selectHmwzVideoList(hmwzVideo);
    }

    /**
     * 新增和美湾沚-视频管理
     *
     * @param hmwzVideo 和美湾沚-视频管理
     * @return 结果
     */
    @Override
    public int insertHmwzVideo(HmwzVideo hmwzVideo)
    {
        hmwzVideo.setCreateTime(DateUtils.getNowDate());
        return hmwzVideoMapper.insertHmwzVideo(hmwzVideo);
    }

    /**
     * 修改和美湾沚-视频管理
     *
     * @param hmwzVideo 和美湾沚-视频管理
     * @return 结果
     */
    @Override
    public int updateHmwzVideo(HmwzVideo hmwzVideo)
    {
        hmwzVideo.setUpdateTime(DateUtils.getNowDate());
        return hmwzVideoMapper.updateHmwzVideo(hmwzVideo);
    }

    /**
     * 批量删除和美湾沚-视频管理
     *
     * @param ids 需要删除的和美湾沚-视频管理主键
     * @return 结果
     */
    @Override
    public int deleteHmwzVideoByIds(Long[] ids)
    {
        return hmwzVideoMapper.deleteHmwzVideoByIds(ids);
    }

    /**
     * 删除和美湾沚-视频管理信息
     *
     * @param id 和美湾沚-视频管理主键
     * @return 结果
     */
    @Override
    public int deleteHmwzVideoById(Long id)
    {
        return hmwzVideoMapper.deleteHmwzVideoById(id);
    }
}
