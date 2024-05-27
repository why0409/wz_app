package com.ruoyi.app.service;


import com.ruoyi.app.domain.HkxcEnterpriseList;

import java.util.List;

/**
 * 航空新城-企业列表Service接口
 *
 * @author ruoyi
 * @date 2024-05-07
 */
public interface IHkxcEnterpriseListService
{
    /**
     * 查询航空新城-企业列表
     *
     * @param id 航空新城-企业列表主键
     * @return 航空新城-企业列表
     */
    public HkxcEnterpriseList selectHkxcEnterpriseListById(Long id);

    /**
     * 查询航空新城-企业列表列表
     *
     * @param hkxcEnterpriseList 航空新城-企业列表
     * @return 航空新城-企业列表集合
     */
    public List<HkxcEnterpriseList> selectHkxcEnterpriseListList(HkxcEnterpriseList hkxcEnterpriseList);

    /**
     * 新增航空新城-企业列表
     *
     * @param hkxcEnterpriseList 航空新城-企业列表
     * @return 结果
     */
    public int insertHkxcEnterpriseList(HkxcEnterpriseList hkxcEnterpriseList);

    /**
     * 修改航空新城-企业列表
     *
     * @param hkxcEnterpriseList 航空新城-企业列表
     * @return 结果
     */
    public int updateHkxcEnterpriseList(HkxcEnterpriseList hkxcEnterpriseList);

    /**
     * 批量删除航空新城-企业列表
     *
     * @param ids 需要删除的航空新城-企业列表主键集合
     * @return 结果
     */
    public int deleteHkxcEnterpriseListByIds(Long[] ids);

    /**
     * 删除航空新城-企业列表信息
     *
     * @param id 航空新城-企业列表主键
     * @return 结果
     */
    public int deleteHkxcEnterpriseListById(Long id);
}
