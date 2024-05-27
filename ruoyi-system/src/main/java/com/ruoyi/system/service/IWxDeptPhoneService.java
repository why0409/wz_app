package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.WxDeptPhone;

/**
 * 部门电话Service接口
 *
 * @author ruoyi
 * @date 2023-02-14
 */
public interface IWxDeptPhoneService
{
    /**
     * 查询部门电话
     *
     * @param uuid 部门电话主键
     * @return 部门电话
     */
    public WxDeptPhone selectWxDeptPhoneByUuid(String uuid);

    /**
     * 查询部门电话列表
     *
     * @param wxDeptPhone 部门电话
     * @return 部门电话集合
     */
    public List<WxDeptPhone> selectWxDeptPhoneList(WxDeptPhone wxDeptPhone);

    /**
     * 新增部门电话
     *
     * @param wxDeptPhone 部门电话
     * @return 结果
     */
    public int insertWxDeptPhone(WxDeptPhone wxDeptPhone);

    /**
     * 修改部门电话
     *
     * @param wxDeptPhone 部门电话
     * @return 结果
     */
    public int updateWxDeptPhone(WxDeptPhone wxDeptPhone);

    /**
     * 批量删除部门电话
     *
     * @param uuids 需要删除的部门电话主键集合
     * @return 结果
     */
    public int deleteWxDeptPhoneByUuids(String[] uuids);

    /**
     * 删除部门电话信息
     *
     * @param uuid 部门电话主键
     * @return 结果
     */
    public int deleteWxDeptPhoneByUuid(String uuid);
}

