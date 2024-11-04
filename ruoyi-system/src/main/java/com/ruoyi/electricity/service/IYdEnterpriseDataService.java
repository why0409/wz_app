package com.ruoyi.electricity.service;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.electricity.domain.YdEnterpriseData;

import java.text.ParseException;
import java.util.List;

/**
 * 用电企业数据Service接口
 *
 * @author ruoyi
 * @date 2024-11-04
 */
public interface IYdEnterpriseDataService
{
    /**
     * 查询用电企业数据
     *
     * @param id 用电企业数据主键
     * @return 用电企业数据
     */
    public YdEnterpriseData selectYdEnterpriseDataById(Long id);

    /**
     * 查询用电企业数据列表
     *
     * @param ydEnterpriseData 用电企业数据
     * @return 用电企业数据集合
     */
    public List<YdEnterpriseData> selectYdEnterpriseDataList(YdEnterpriseData ydEnterpriseData);

    /**
     * 新增用电企业数据
     *
     * @param ydEnterpriseData 用电企业数据
     * @return 结果
     */
    public int insertYdEnterpriseData(YdEnterpriseData ydEnterpriseData);

    /**
     * 修改用电企业数据
     *
     * @param ydEnterpriseData 用电企业数据
     * @return 结果
     */
    public int updateYdEnterpriseData(YdEnterpriseData ydEnterpriseData);

    /**
     * 批量删除用电企业数据
     *
     * @param ids 需要删除的用电企业数据主键集合
     * @return 结果
     */
    public int deleteYdEnterpriseDataByIds(Long[] ids);

    /**
     * 删除用电企业数据信息
     *
     * @param id 用电企业数据主键
     * @return 结果
     */
    public int deleteYdEnterpriseDataById(Long id);

    int importData(List<YdEnterpriseData> dataList) throws ParseException;

    List<JSONObject> getEleCount(String meterNumber, String flag);
}
