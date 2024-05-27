package com.ruoyi.system.service.impl;

import java.util.List;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TempleFairInfoMapper;
import com.ruoyi.system.domain.TempleFairInfo;
import com.ruoyi.system.service.ITempleFairInfoService;

/**
 * 庙会信息Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-09
 */
@Service
public class TempleFairInfoServiceImpl implements ITempleFairInfoService
{
    @Autowired
    private TempleFairInfoMapper templeFairInfoMapper;

    /**
     * 查询庙会信息
     *
     * @param id 庙会信息主键
     * @return 庙会信息
     */
    @Override
    public TempleFairInfo selectTempleFairInfoById(Long id)
    {
        return templeFairInfoMapper.selectTempleFairInfoById(id);
    }

    /**
     * 查询庙会信息列表
     *
     * @param templeFairInfo 庙会信息
     * @return 庙会信息
     */
    @Override
    public List<TempleFairInfo> selectTempleFairInfoList(TempleFairInfo templeFairInfo)
    {
        return templeFairInfoMapper.selectTempleFairInfoList(templeFairInfo);
    }

    /**
     * 新增庙会信息
     *
     * @param templeFairInfo 庙会信息
     * @return 结果
     */
    @Override
    public int insertTempleFairInfo(TempleFairInfo templeFairInfo)
    {
        templeFairInfo.setCreateTime(DateUtils.getNowDate());
        return templeFairInfoMapper.insertTempleFairInfo(templeFairInfo);
    }

    /**
     * 修改庙会信息
     *
     * @param templeFairInfo 庙会信息
     * @return 结果
     */
    @Override
    public int updateTempleFairInfo(TempleFairInfo templeFairInfo)
    {
        templeFairInfo.setUpdateTime(DateUtils.getNowDate());
        return templeFairInfoMapper.updateTempleFairInfo(templeFairInfo);
    }

    /**
     * 批量删除庙会信息
     *
     * @param ids 需要删除的庙会信息主键
     * @return 结果
     */
    @Override
    public int deleteTempleFairInfoByIds(Long[] ids)
    {
        return templeFairInfoMapper.deleteTempleFairInfoByIds(ids);
    }

    /**
     * 删除庙会信息信息
     *
     * @param id 庙会信息主键
     * @return 结果
     */
    @Override
    public int deleteTempleFairInfoById(Long id)
    {
        return templeFairInfoMapper.deleteTempleFairInfoById(id);
    }

    @Override
    public List<JSONObject> getSaleTypeList(){
        return templeFairInfoMapper.getSaleTypeList();
    }

    @Override
    public List<JSONObject> staticsBySaleType(){
        return templeFairInfoMapper.staticsBySaleType();
    }

    @Override
    public JSONObject staticsByArea() {
        return templeFairInfoMapper.staticsByArea();
    }

}
