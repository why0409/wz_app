package com.ruoyi.app.service.impl;

import com.ruoyi.app.domain.HkxcEnterpriseList;
import com.ruoyi.app.mapper.HkxcEnterpriseListMapper;
import com.ruoyi.app.service.IHkxcEnterpriseListService;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 航空新城-企业列表Service业务层处理
 *
 * @author ruoyi
 * @date 2024-05-07
 */
@Service
public class HkxcEnterpriseListServiceImpl implements IHkxcEnterpriseListService
{
    @Autowired
    private HkxcEnterpriseListMapper hkxcEnterpriseListMapper;

    /**
     * 查询航空新城-企业列表
     *
     * @param id 航空新城-企业列表主键
     * @return 航空新城-企业列表
     */
    @Override
    public HkxcEnterpriseList selectHkxcEnterpriseListById(Long id)
    {
        return hkxcEnterpriseListMapper.selectHkxcEnterpriseListById(id);
    }

    /**
     * 查询航空新城-企业列表列表
     *
     * @param hkxcEnterpriseList 航空新城-企业列表
     * @return 航空新城-企业列表
     */
    @Override
    public List<HkxcEnterpriseList> selectHkxcEnterpriseListList(HkxcEnterpriseList hkxcEnterpriseList)
    {
        return hkxcEnterpriseListMapper.selectHkxcEnterpriseListList(hkxcEnterpriseList);
    }

    /**
     * 新增航空新城-企业列表
     *
     * @param hkxcEnterpriseList 航空新城-企业列表
     * @return 结果
     */
    @Override
    public int insertHkxcEnterpriseList(HkxcEnterpriseList hkxcEnterpriseList)
    {
        hkxcEnterpriseList.setCreateTime(DateUtils.getNowDate());
        return hkxcEnterpriseListMapper.insertHkxcEnterpriseList(hkxcEnterpriseList);
    }

    /**
     * 修改航空新城-企业列表
     *
     * @param hkxcEnterpriseList 航空新城-企业列表
     * @return 结果
     */
    @Override
    public int updateHkxcEnterpriseList(HkxcEnterpriseList hkxcEnterpriseList)
    {
        hkxcEnterpriseList.setUpdateTime(DateUtils.getNowDate());
        return hkxcEnterpriseListMapper.updateHkxcEnterpriseList(hkxcEnterpriseList);
    }

    /**
     * 批量删除航空新城-企业列表
     *
     * @param ids 需要删除的航空新城-企业列表主键
     * @return 结果
     */
    @Override
    public int deleteHkxcEnterpriseListByIds(Long[] ids)
    {
        return hkxcEnterpriseListMapper.deleteHkxcEnterpriseListByIds(ids);
    }

    /**
     * 删除航空新城-企业列表信息
     *
     * @param id 航空新城-企业列表主键
     * @return 结果
     */
    @Override
    public int deleteHkxcEnterpriseListById(Long id)
    {
        return hkxcEnterpriseListMapper.deleteHkxcEnterpriseListById(id);
    }
}
