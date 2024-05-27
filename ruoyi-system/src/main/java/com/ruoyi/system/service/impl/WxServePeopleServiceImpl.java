package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WxServePeopleMapper;
import com.ruoyi.system.domain.WxServePeople;
import com.ruoyi.system.service.IWxServePeopleService;

/**
 * 为民服务Service业务层处理
 *
 * @author ruoyi
 * @date 2023-04-10
 */
@Service
public class WxServePeopleServiceImpl implements IWxServePeopleService
{
    @Autowired
    private WxServePeopleMapper wxServePeopleMapper;

    /**
     * 查询为民服务
     *
     * @param uuid 为民服务主键
     * @return 为民服务
     */
    @Override
    public WxServePeople selectWxServePeopleByUuid(String uuid)
    {
        return wxServePeopleMapper.selectWxServePeopleByUuid(uuid);
    }

    /**
     * 查询为民服务列表
     *
     * @param wxServePeople 为民服务
     * @return 为民服务
     */
    @Override
    public List<WxServePeople> selectWxServePeopleList(WxServePeople wxServePeople)
    {
        return wxServePeopleMapper.selectWxServePeopleList(wxServePeople);
    }

    /**
     * 新增为民服务
     *
     * @param wxServePeople 为民服务
     * @return 结果
     */
    @Override
    public int insertWxServePeople(WxServePeople wxServePeople)
    {
        return wxServePeopleMapper.insertWxServePeople(wxServePeople);
    }

    /**
     * 修改为民服务
     *
     * @param wxServePeople 为民服务
     * @return 结果
     */
    @Override
    public int updateWxServePeople(WxServePeople wxServePeople)
    {
        wxServePeople.setUpdateTime(DateUtils.getNowDate());
        return wxServePeopleMapper.updateWxServePeople(wxServePeople);
    }

    /**
     * 批量删除为民服务
     *
     * @param uuids 需要删除的为民服务主键
     * @return 结果
     */
    @Override
    public int deleteWxServePeopleByUuids(String[] uuids)
    {
        return wxServePeopleMapper.deleteWxServePeopleByUuids(uuids);
    }

    /**
     * 删除为民服务信息
     *
     * @param uuid 为民服务主键
     * @return 结果
     */
    @Override
    public int deleteWxServePeopleByUuid(String uuid)
    {
        return wxServePeopleMapper.deleteWxServePeopleByUuid(uuid);
    }
}
