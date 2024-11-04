package com.ruoyi.electricity.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanValidators;
import com.ruoyi.electricity.domain.YdEnterpriseData;
import com.ruoyi.electricity.mapper.YdEnterpriseDataMapper;
import com.ruoyi.electricity.service.IYdEnterpriseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用电企业数据Service业务层处理
 *
 * @author ruoyi
 * @date 2024-11-04
 */
@Service
public class YdEnterpriseDataServiceImpl implements IYdEnterpriseDataService
{
    @Autowired
    private YdEnterpriseDataMapper ydEnterpriseDataMapper;

    /**
     * 查询用电企业数据
     *
     * @param id 用电企业数据主键
     * @return 用电企业数据
     */
    @Override
    public YdEnterpriseData selectYdEnterpriseDataById(Long id)
    {
        return ydEnterpriseDataMapper.selectYdEnterpriseDataById(id);
    }

    /**
     * 查询用电企业数据列表
     *
     * @param ydEnterpriseData 用电企业数据
     * @return 用电企业数据
     */
    @Override
    public List<YdEnterpriseData> selectYdEnterpriseDataList(YdEnterpriseData ydEnterpriseData)
    {
        return ydEnterpriseDataMapper.selectYdEnterpriseDataList(ydEnterpriseData);
    }

    /**
     * 新增用电企业数据
     *
     * @param ydEnterpriseData 用电企业数据
     * @return 结果
     */
    @Override
    public int insertYdEnterpriseData(YdEnterpriseData ydEnterpriseData)
    {
        ydEnterpriseData.setCreateTime(DateUtils.getNowDate());
        return ydEnterpriseDataMapper.insertYdEnterpriseData(ydEnterpriseData);
    }

    /**
     * 修改用电企业数据
     *
     * @param ydEnterpriseData 用电企业数据
     * @return 结果
     */
    @Override
    public int updateYdEnterpriseData(YdEnterpriseData ydEnterpriseData)
    {
        ydEnterpriseData.setUpdateTime(DateUtils.getNowDate());
        return ydEnterpriseDataMapper.updateYdEnterpriseData(ydEnterpriseData);
    }

    /**
     * 批量删除用电企业数据
     *
     * @param ids 需要删除的用电企业数据主键
     * @return 结果
     */
    @Override
    public int deleteYdEnterpriseDataByIds(Long[] ids)
    {
        return ydEnterpriseDataMapper.deleteYdEnterpriseDataByIds(ids);
    }

    /**
     * 删除用电企业数据信息
     *
     * @param id 用电企业数据主键
     * @return 结果
     */
    @Override
    public int deleteYdEnterpriseDataById(Long id)
    {
        return ydEnterpriseDataMapper.deleteYdEnterpriseDataById(id);
    }
}
