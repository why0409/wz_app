package com.ruoyi.system.service;

import java.util.List;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.system.domain.TempleFairInfo;

/**
 * 庙会信息Service接口
 *
 * @author ruoyi
 * @date 2024-03-09
 */
public interface ITempleFairInfoService
{
    /**
     * 查询庙会信息
     *
     * @param id 庙会信息主键
     * @return 庙会信息
     */
    public TempleFairInfo selectTempleFairInfoById(Long id);

    /**
     * 查询庙会信息列表
     *
     * @param templeFairInfo 庙会信息
     * @return 庙会信息集合
     */
    public List<TempleFairInfo> selectTempleFairInfoList(TempleFairInfo templeFairInfo);

    /**
     * 新增庙会信息
     *
     * @param templeFairInfo 庙会信息
     * @return 结果
     */
    public int insertTempleFairInfo(TempleFairInfo templeFairInfo);

    /**
     * 修改庙会信息
     *
     * @param templeFairInfo 庙会信息
     * @return 结果
     */
    public int updateTempleFairInfo(TempleFairInfo templeFairInfo);

    /**
     * 批量删除庙会信息
     *
     * @param ids 需要删除的庙会信息主键集合
     * @return 结果
     */
    public int deleteTempleFairInfoByIds(Long[] ids);

    /**
     * 删除庙会信息信息
     *
     * @param id 庙会信息主键
     * @return 结果
     */
    public int deleteTempleFairInfoById(Long id);

    public List<JSONObject> getSaleTypeList();

    public List<JSONObject> staticsBySaleType();

    public JSONObject staticsByArea();
}
