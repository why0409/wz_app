package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WxDeptPhoneMapper;
import com.ruoyi.system.domain.WxDeptPhone;
import com.ruoyi.system.service.IWxDeptPhoneService;

/**
 * 部门电话Service业务层处理
 *
 * @author ruoyi
 * @date 2023-02-14
 */
@Service
public class WxDeptPhoneServiceImpl implements IWxDeptPhoneService
{
    @Autowired
    private WxDeptPhoneMapper wxDeptPhoneMapper;

    /**
     * 查询部门电话
     *
     * @param uuid 部门电话主键
     * @return 部门电话
     */
    @Override
    public WxDeptPhone selectWxDeptPhoneByUuid(String uuid)
    {
        return wxDeptPhoneMapper.selectWxDeptPhoneByUuid(uuid);
    }

    /**
     * 查询部门电话列表
     *
     * @param wxDeptPhone 部门电话
     * @return 部门电话
     */
    @Override
    public List<WxDeptPhone> selectWxDeptPhoneList(WxDeptPhone wxDeptPhone)
    {
        return wxDeptPhoneMapper.selectWxDeptPhoneList(wxDeptPhone);
    }

    /**
     * 新增部门电话
     *
     * @param wxDeptPhone 部门电话
     * @return 结果
     */
    @Override
    public int insertWxDeptPhone(WxDeptPhone wxDeptPhone)
    {
        wxDeptPhone.setCreateTime(DateUtils.getNowDate());
        return wxDeptPhoneMapper.insertWxDeptPhone(wxDeptPhone);
    }

    /**
     * 修改部门电话
     *
     * @param wxDeptPhone 部门电话
     * @return 结果
     */
    @Override
    public int updateWxDeptPhone(WxDeptPhone wxDeptPhone)
    {
        return wxDeptPhoneMapper.updateWxDeptPhone(wxDeptPhone);
    }

    /**
     * 批量删除部门电话
     *
     * @param uuids 需要删除的部门电话主键
     * @return 结果
     */
    @Override
    public int deleteWxDeptPhoneByUuids(String[] uuids)
    {
        return wxDeptPhoneMapper.deleteWxDeptPhoneByUuids(uuids);
    }

    /**
     * 删除部门电话信息
     *
     * @param uuid 部门电话主键
     * @return 结果
     */
    @Override
    public int deleteWxDeptPhoneByUuid(String uuid)
    {
        return wxDeptPhoneMapper.deleteWxDeptPhoneByUuid(uuid);
    }
}

