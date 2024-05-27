package com.ruoyi.app.mapper;

import java.util.List;
import com.ruoyi.app.domain.CszyCameraList;

/**
 * 城市之眼-视频列Mapper接口
 *
 * @author ruoyi
 * @date 2024-05-24
 */
public interface CszyCameraListMapper
{
    /**
     * 查询城市之眼-视频列
     *
     * @param id 城市之眼-视频列主键
     * @return 城市之眼-视频列
     */
    public CszyCameraList selectCszyCameraListById(Long id);

    /**
     * 查询城市之眼-视频列列表
     *
     * @param cszyCameraList 城市之眼-视频列
     * @return 城市之眼-视频列集合
     */
    public List<CszyCameraList> selectCszyCameraListList(CszyCameraList cszyCameraList);

    /**
     * 新增城市之眼-视频列
     *
     * @param cszyCameraList 城市之眼-视频列
     * @return 结果
     */
    public int insertCszyCameraList(CszyCameraList cszyCameraList);

    /**
     * 修改城市之眼-视频列
     *
     * @param cszyCameraList 城市之眼-视频列
     * @return 结果
     */
    public int updateCszyCameraList(CszyCameraList cszyCameraList);

    /**
     * 删除城市之眼-视频列
     *
     * @param id 城市之眼-视频列主键
     * @return 结果
     */
    public int deleteCszyCameraListById(Long id);

    /**
     * 批量删除城市之眼-视频列
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCszyCameraListByIds(Long[] ids);
}
