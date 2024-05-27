package com.ruoyi.app.service;

import java.util.List;
import com.ruoyi.app.domain.HmwzCover;

/**
 * 和美湾沚-封面管理Service接口
 *
 * @author ruoyi
 * @date 2024-05-06
 */
public interface IHmwzCoverService
{
    /**
     * 查询和美湾沚-封面管理
     *
     * @param id 和美湾沚-封面管理主键
     * @return 和美湾沚-封面管理
     */
    public HmwzCover selectHmwzCoverById(Long id);

    /**
     * 查询和美湾沚-封面管理列表
     *
     * @param hmwzCover 和美湾沚-封面管理
     * @return 和美湾沚-封面管理集合
     */
    public List<HmwzCover> selectHmwzCoverList(HmwzCover hmwzCover);

    /**
     * 新增和美湾沚-封面管理
     *
     * @param hmwzCover 和美湾沚-封面管理
     * @return 结果
     */
    public int insertHmwzCover(HmwzCover hmwzCover);

    /**
     * 修改和美湾沚-封面管理
     *
     * @param hmwzCover 和美湾沚-封面管理
     * @return 结果
     */
    public int updateHmwzCover(HmwzCover hmwzCover);

    /**
     * 批量删除和美湾沚-封面管理
     *
     * @param ids 需要删除的和美湾沚-封面管理主键集合
     * @return 结果
     */
    public int deleteHmwzCoverByIds(Long[] ids);

    /**
     * 删除和美湾沚-封面管理信息
     *
     * @param id 和美湾沚-封面管理主键
     * @return 结果
     */
    public int deleteHmwzCoverById(Long id);
}
