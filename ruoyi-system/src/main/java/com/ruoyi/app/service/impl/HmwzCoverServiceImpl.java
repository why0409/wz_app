package com.ruoyi.app.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.app.mapper.HmwzCoverMapper;
import com.ruoyi.app.domain.HmwzCover;
import com.ruoyi.app.service.IHmwzCoverService;

/**
 * 和美湾沚-封面管理Service业务层处理
 *
 * @author ruoyi
 * @date 2024-05-06
 */
@Service
public class HmwzCoverServiceImpl implements IHmwzCoverService
{
    @Autowired
    private HmwzCoverMapper hmwzCoverMapper;

    /**
     * 查询和美湾沚-封面管理
     *
     * @param id 和美湾沚-封面管理主键
     * @return 和美湾沚-封面管理
     */
    @Override
    public HmwzCover selectHmwzCoverById(Long id)
    {
        return hmwzCoverMapper.selectHmwzCoverById(id);
    }

    /**
     * 查询和美湾沚-封面管理列表
     *
     * @param hmwzCover 和美湾沚-封面管理
     * @return 和美湾沚-封面管理
     */
    @Override
    public List<HmwzCover> selectHmwzCoverList(HmwzCover hmwzCover)
    {
        return hmwzCoverMapper.selectHmwzCoverList(hmwzCover);
    }

    /**
     * 新增和美湾沚-封面管理
     *
     * @param hmwzCover 和美湾沚-封面管理
     * @return 结果
     */
    @Override
    public int insertHmwzCover(HmwzCover hmwzCover)
    {
        hmwzCover.setCreateTime(DateUtils.getNowDate());
        return hmwzCoverMapper.insertHmwzCover(hmwzCover);
    }

    /**
     * 修改和美湾沚-封面管理
     *
     * @param hmwzCover 和美湾沚-封面管理
     * @return 结果
     */
    @Override
    public int updateHmwzCover(HmwzCover hmwzCover)
    {
        hmwzCover.setUpdateTime(DateUtils.getNowDate());
        return hmwzCoverMapper.updateHmwzCover(hmwzCover);
    }

    /**
     * 批量删除和美湾沚-封面管理
     *
     * @param ids 需要删除的和美湾沚-封面管理主键
     * @return 结果
     */
    @Override
    public int deleteHmwzCoverByIds(Long[] ids)
    {
        return hmwzCoverMapper.deleteHmwzCoverByIds(ids);
    }

    /**
     * 删除和美湾沚-封面管理信息
     *
     * @param id 和美湾沚-封面管理主键
     * @return 结果
     */
    @Override
    public int deleteHmwzCoverById(Long id)
    {
        return hmwzCoverMapper.deleteHmwzCoverById(id);
    }
}
