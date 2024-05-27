package com.ruoyi.app.service;

import java.util.List;
import com.ruoyi.app.domain.HmwzVideo;

/**
 * 和美湾沚-视频管理Service接口
 *
 * @author ruoyi
 * @date 2024-05-06
 */
public interface IHmwzVideoService
{
    /**
     * 查询和美湾沚-视频管理
     *
     * @param id 和美湾沚-视频管理主键
     * @return 和美湾沚-视频管理
     */
    public HmwzVideo selectHmwzVideoById(Long id);

    /**
     * 查询和美湾沚-视频管理列表
     *
     * @param hmwzVideo 和美湾沚-视频管理
     * @return 和美湾沚-视频管理集合
     */
    public List<HmwzVideo> selectHmwzVideoList(HmwzVideo hmwzVideo);

    /**
     * 新增和美湾沚-视频管理
     *
     * @param hmwzVideo 和美湾沚-视频管理
     * @return 结果
     */
    public int insertHmwzVideo(HmwzVideo hmwzVideo);

    /**
     * 修改和美湾沚-视频管理
     *
     * @param hmwzVideo 和美湾沚-视频管理
     * @return 结果
     */
    public int updateHmwzVideo(HmwzVideo hmwzVideo);

    /**
     * 批量删除和美湾沚-视频管理
     *
     * @param ids 需要删除的和美湾沚-视频管理主键集合
     * @return 结果
     */
    public int deleteHmwzVideoByIds(Long[] ids);

    /**
     * 删除和美湾沚-视频管理信息
     *
     * @param id 和美湾沚-视频管理主键
     * @return 结果
     */
    public int deleteHmwzVideoById(Long id);
}
