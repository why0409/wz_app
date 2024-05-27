package com.ruoyi.app.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.app.mapper.HkxcEnterpriseTypeMapper;
import com.ruoyi.app.domain.HkxcEnterpriseType;
import com.ruoyi.app.service.IHkxcEnterpriseTypeService;

/**
 * 航空新城-企业分类Service业务层处理
 *
 * @author ruoyi
 * @date 2024-05-07
 */
@Service
public class HkxcEnterpriseTypeServiceImpl implements IHkxcEnterpriseTypeService
{
    @Autowired
    private HkxcEnterpriseTypeMapper hkxcEnterpriseTypeMapper;

    /**
     * 查询航空新城-企业分类
     *
     * @param id 航空新城-企业分类主键
     * @return 航空新城-企业分类
     */
    @Override
    public HkxcEnterpriseType selectHkxcEnterpriseTypeById(Long id)
    {
        return hkxcEnterpriseTypeMapper.selectHkxcEnterpriseTypeById(id);
    }

    /**
     * 查询航空新城-企业分类列表
     *
     * @param hkxcEnterpriseType 航空新城-企业分类
     * @return 航空新城-企业分类
     */
    @Override
    public List<HkxcEnterpriseType> selectHkxcEnterpriseTypeList(HkxcEnterpriseType hkxcEnterpriseType)
    {
        return hkxcEnterpriseTypeMapper.selectHkxcEnterpriseTypeList(hkxcEnterpriseType);
    }

    /**
     * 新增航空新城-企业分类
     *
     * @param hkxcEnterpriseType 航空新城-企业分类
     * @return 结果
     */
    @Override
    public int insertHkxcEnterpriseType(HkxcEnterpriseType hkxcEnterpriseType)
    {
        hkxcEnterpriseType.setCreateTime(DateUtils.getNowDate());
        return hkxcEnterpriseTypeMapper.insertHkxcEnterpriseType(hkxcEnterpriseType);
    }

    /**
     * 修改航空新城-企业分类
     *
     * @param hkxcEnterpriseType 航空新城-企业分类
     * @return 结果
     */
    @Override
    public int updateHkxcEnterpriseType(HkxcEnterpriseType hkxcEnterpriseType)
    {
        hkxcEnterpriseType.setUpdateTime(DateUtils.getNowDate());
        return hkxcEnterpriseTypeMapper.updateHkxcEnterpriseType(hkxcEnterpriseType);
    }

    /**
     * 批量删除航空新城-企业分类
     *
     * @param ids 需要删除的航空新城-企业分类主键
     * @return 结果
     */
    @Override
    public int deleteHkxcEnterpriseTypeByIds(Long[] ids)
    {
        return hkxcEnterpriseTypeMapper.deleteHkxcEnterpriseTypeByIds(ids);
    }

    /**
     * 删除航空新城-企业分类信息
     *
     * @param id 航空新城-企业分类主键
     * @return 结果
     */
    @Override
    public int deleteHkxcEnterpriseTypeById(Long id)
    {
        return hkxcEnterpriseTypeMapper.deleteHkxcEnterpriseTypeById(id);
    }
}
