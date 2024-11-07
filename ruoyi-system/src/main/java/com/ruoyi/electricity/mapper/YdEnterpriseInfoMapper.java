package com.ruoyi.electricity.mapper;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.electricity.domain.YdEnterpriseInfo;

import java.util.List;

/**
 * 用电企业信息Mapper接口
 *
 * @author ruoyi
 * @date 2024-11-04
 */
public interface YdEnterpriseInfoMapper
{
    /**
     * 查询用电企业信息
     *
     * @param id 用电企业信息主键
     * @return 用电企业信息
     */
    public YdEnterpriseInfo selectYdEnterpriseInfoById(Long id);

    /**
     * 查询用电企业信息列表
     *
     * @param ydEnterpriseInfo 用电企业信息
     * @return 用电企业信息集合
     */
    public List<YdEnterpriseInfo> selectYdEnterpriseInfoList(YdEnterpriseInfo ydEnterpriseInfo);

    /**
     * 新增用电企业信息
     *
     * @param ydEnterpriseInfo 用电企业信息
     * @return 结果
     */
    public int insertYdEnterpriseInfo(YdEnterpriseInfo ydEnterpriseInfo);

    /**
     * 修改用电企业信息
     *
     * @param ydEnterpriseInfo 用电企业信息
     * @return 结果
     */
    public int updateYdEnterpriseInfo(YdEnterpriseInfo ydEnterpriseInfo);

    /**
     * 删除用电企业信息
     *
     * @param id 用电企业信息主键
     * @return 结果
     */
    public int deleteYdEnterpriseInfoById(Long id);

    /**
     * 批量删除用电企业信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteYdEnterpriseInfoByIds(Long[] ids);
}
