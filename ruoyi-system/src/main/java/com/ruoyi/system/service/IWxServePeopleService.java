package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.WxServePeople;

/**
 * 为民服务Service接口
 *
 * @author ruoyi
 * @date 2023-04-10
 */
public interface IWxServePeopleService
{
    /**
     * 查询为民服务
     *
     * @param uuid 为民服务主键
     * @return 为民服务
     */
    public WxServePeople selectWxServePeopleByUuid(String uuid);

    /**
     * 查询为民服务列表
     *
     * @param wxServePeople 为民服务
     * @return 为民服务集合
     */
    public List<WxServePeople> selectWxServePeopleList(WxServePeople wxServePeople);

    /**
     * 新增为民服务
     *
     * @param wxServePeople 为民服务
     * @return 结果
     */
    public int insertWxServePeople(WxServePeople wxServePeople);

    /**
     * 修改为民服务
     *
     * @param wxServePeople 为民服务
     * @return 结果
     */
    public int updateWxServePeople(WxServePeople wxServePeople);

    /**
     * 批量删除为民服务
     *
     * @param uuids 需要删除的为民服务主键集合
     * @return 结果
     */
    public int deleteWxServePeopleByUuids(String[] uuids);

    /**
     * 删除为民服务信息
     *
     * @param uuid 为民服务主键
     * @return 结果
     */
    public int deleteWxServePeopleByUuid(String uuid);
}
