package com.ruoyi.app.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.app.mapper.CszyCameraListMapper;
import com.ruoyi.app.domain.CszyCameraList;
import com.ruoyi.app.service.ICszyCameraListService;

/**
 * 城市之眼-视频列Service业务层处理
 *
 * @author ruoyi
 * @date 2024-05-24
 */
@Service
public class CszyCameraListServiceImpl implements ICszyCameraListService
{
    @Autowired
    private CszyCameraListMapper cszyCameraListMapper;

    /**
     * 查询城市之眼-视频列
     *
     * @param id 城市之眼-视频列主键
     * @return 城市之眼-视频列
     */
    @Override
    public CszyCameraList selectCszyCameraListById(Long id)
    {
        return cszyCameraListMapper.selectCszyCameraListById(id);
    }

    /**
     * 查询城市之眼-视频列列表
     *
     * @param cszyCameraList 城市之眼-视频列
     * @return 城市之眼-视频列
     */
    @Override
    public List<CszyCameraList> selectCszyCameraListList(CszyCameraList cszyCameraList)
    {
        return cszyCameraListMapper.selectCszyCameraListList(cszyCameraList);
    }

    /**
     * 新增城市之眼-视频列
     *
     * @param cszyCameraList 城市之眼-视频列
     * @return 结果
     */
    @Override
    public int insertCszyCameraList(CszyCameraList cszyCameraList)
    {
        cszyCameraList.setCreateTime(DateUtils.getNowDate());
        return cszyCameraListMapper.insertCszyCameraList(cszyCameraList);
    }

    /**
     * 修改城市之眼-视频列
     *
     * @param cszyCameraList 城市之眼-视频列
     * @return 结果
     */
    @Override
    public int updateCszyCameraList(CszyCameraList cszyCameraList)
    {
        cszyCameraList.setUpdateTime(DateUtils.getNowDate());
        return cszyCameraListMapper.updateCszyCameraList(cszyCameraList);
    }

    /**
     * 批量删除城市之眼-视频列
     *
     * @param ids 需要删除的城市之眼-视频列主键
     * @return 结果
     */
    @Override
    public int deleteCszyCameraListByIds(Long[] ids)
    {
        return cszyCameraListMapper.deleteCszyCameraListByIds(ids);
    }

    /**
     * 删除城市之眼-视频列信息
     *
     * @param id 城市之眼-视频列主键
     * @return 结果
     */
    @Override
    public int deleteCszyCameraListById(Long id)
    {
        return cszyCameraListMapper.deleteCszyCameraListById(id);
    }
}
