package com.ruoyi.app.mapper;

import java.util.List;
import com.ruoyi.app.domain.HkxcEnterpriseType;

/**
 * 航空新城-企业分类Mapper接口
 *
 * @author ruoyi
 * @date 2024-05-07
 */
public interface HkxcEnterpriseTypeMapper
{
    /**
     * 查询航空新城-企业分类
     *
     * @param id 航空新城-企业分类主键
     * @return 航空新城-企业分类
     */
    public HkxcEnterpriseType selectHkxcEnterpriseTypeById(Long id);

    /**
     * 查询航空新城-企业分类列表
     *
     * @param hkxcEnterpriseType 航空新城-企业分类
     * @return 航空新城-企业分类集合
     */
    public List<HkxcEnterpriseType> selectHkxcEnterpriseTypeList(HkxcEnterpriseType hkxcEnterpriseType);

    /**
     * 新增航空新城-企业分类
     *
     * @param hkxcEnterpriseType 航空新城-企业分类
     * @return 结果
     */
    public int insertHkxcEnterpriseType(HkxcEnterpriseType hkxcEnterpriseType);

    /**
     * 修改航空新城-企业分类
     *
     * @param hkxcEnterpriseType 航空新城-企业分类
     * @return 结果
     */
    public int updateHkxcEnterpriseType(HkxcEnterpriseType hkxcEnterpriseType);

    /**
     * 删除航空新城-企业分类
     *
     * @param id 航空新城-企业分类主键
     * @return 结果
     */
    public int deleteHkxcEnterpriseTypeById(Long id);

    /**
     * 批量删除航空新城-企业分类
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHkxcEnterpriseTypeByIds(Long[] ids);
}
