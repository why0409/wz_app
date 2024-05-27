package com.ruoyi.app.mapper;

import com.ruoyi.app.domain.CszyCameraType;
import com.ruoyi.app.domain.vo.CszyCameraTypeVo;

import java.util.List;

/**
 * 城市之眼-视频分类Mapper接口
 *
 * @author ruoyi
 * @date 2024-05-24
 */
public interface CszyCameraTypeMapper
{
    /**
     * 查询城市之眼-视频分类
     *
     * @param id 城市之眼-视频分类主键
     * @return 城市之眼-视频分类
     */
    public CszyCameraType selectCszyCameraTypeById(Long id);

    /**
     * 查询城市之眼-视频分类列表
     *
     * @param cszyCameraType 城市之眼-视频分类
     * @return 城市之眼-视频分类集合
     */
    public List<CszyCameraType> selectCszyCameraTypeList(CszyCameraType cszyCameraType);

    /**
     * 新增城市之眼-视频分类
     *
     * @param cszyCameraType 城市之眼-视频分类
     * @return 结果
     */
    public int insertCszyCameraType(CszyCameraType cszyCameraType);

    /**
     * 修改城市之眼-视频分类
     *
     * @param cszyCameraType 城市之眼-视频分类
     * @return 结果
     */
    public int updateCszyCameraType(CszyCameraType cszyCameraType);

    /**
     * 删除城市之眼-视频分类
     *
     * @param id 城市之眼-视频分类主键
     * @return 结果
     */
    public int deleteCszyCameraTypeById(Long id);

    /**
     * 批量删除城市之眼-视频分类
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCszyCameraTypeByIds(Long[] ids);

    public List<CszyCameraTypeVo> getPermissionsByPhone(String phone);
}

