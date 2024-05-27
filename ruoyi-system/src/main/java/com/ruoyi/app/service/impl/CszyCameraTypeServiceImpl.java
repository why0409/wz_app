package com.ruoyi.app.service.impl;

import java.util.List;

import com.ruoyi.app.domain.vo.CszyCameraTypeVo;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.app.mapper.CszyCameraTypeMapper;
import com.ruoyi.app.domain.CszyCameraType;
import com.ruoyi.app.service.ICszyCameraTypeService;

/**
 * 城市之眼-视频分类Service业务层处理
 *
 * @author ruoyi
 * @date 2024-05-24
 */
@Service
public class CszyCameraTypeServiceImpl implements ICszyCameraTypeService
{
    @Autowired
    private CszyCameraTypeMapper cszyCameraTypeMapper;

    /**
     * 查询城市之眼-视频分类
     *
     * @param id 城市之眼-视频分类主键
     * @return 城市之眼-视频分类
     */
    @Override
    public CszyCameraType selectCszyCameraTypeById(Long id)
    {
        return cszyCameraTypeMapper.selectCszyCameraTypeById(id);
    }

    /**
     * 查询城市之眼-视频分类列表
     *
     * @param cszyCameraType 城市之眼-视频分类
     * @return 城市之眼-视频分类
     */
    @Override
    public List<CszyCameraType> selectCszyCameraTypeList(CszyCameraType cszyCameraType)
    {
        return cszyCameraTypeMapper.selectCszyCameraTypeList(cszyCameraType);
    }

    /**
     * 新增城市之眼-视频分类
     *
     * @param cszyCameraType 城市之眼-视频分类
     * @return 结果
     */
    @Override
    public int insertCszyCameraType(CszyCameraType cszyCameraType)
    {
        cszyCameraType.setCreateTime(DateUtils.getNowDate());
        return cszyCameraTypeMapper.insertCszyCameraType(cszyCameraType);
    }

    /**
     * 修改城市之眼-视频分类
     *
     * @param cszyCameraType 城市之眼-视频分类
     * @return 结果
     */
    @Override
    public int updateCszyCameraType(CszyCameraType cszyCameraType)
    {
        cszyCameraType.setUpdateTime(DateUtils.getNowDate());
        return cszyCameraTypeMapper.updateCszyCameraType(cszyCameraType);
    }

    /**
     * 批量删除城市之眼-视频分类
     *
     * @param ids 需要删除的城市之眼-视频分类主键
     * @return 结果
     */
    @Override
    public int deleteCszyCameraTypeByIds(Long[] ids)
    {
        return cszyCameraTypeMapper.deleteCszyCameraTypeByIds(ids);
    }

    /**
     * 删除城市之眼-视频分类信息
     *
     * @param id 城市之眼-视频分类主键
     * @return 结果
     */
    @Override
    public int deleteCszyCameraTypeById(Long id)
    {
        return cszyCameraTypeMapper.deleteCszyCameraTypeById(id);
    }

    @Override
    public List<CszyCameraTypeVo> getPermissionsByPhone(String phone){
        return cszyCameraTypeMapper.getPermissionsByPhone(phone);
    }
}
