package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.WxPictureAppreciate;
import com.ruoyi.system.mapper.WxPictureAppreciateMapper;
import com.ruoyi.system.service.IWxPictureAppreciateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 图片欣赏Service业务层处理
 *
 * @author ruoyi
 * @date 2023-02-08
 */
@Service
public class WxPictureAppreciateServiceImpl implements IWxPictureAppreciateService
{
    @Resource
    private WxPictureAppreciateMapper wxPictureAppreciateMapper;

    /**
     * 查询图片欣赏
     *
     * @param uuid 图片欣赏主键
     * @return 图片欣赏
     */
    @Override
    public WxPictureAppreciate selectWxPictureAppreciateByUuid(String uuid)
    {
        return wxPictureAppreciateMapper.selectWxPictureAppreciateByUuid(uuid);
    }

    /**
     * 查询图片欣赏列表
     *
     * @param wxPictureAppreciate 图片欣赏
     * @return 图片欣赏
     */
    @Override
    public List<WxPictureAppreciate> selectWxPictureAppreciateList(WxPictureAppreciate wxPictureAppreciate)
    {
        return wxPictureAppreciateMapper.selectWxPictureAppreciateList(wxPictureAppreciate);
    }

    /**
     * 新增图片欣赏
     *
     * @param wxPictureAppreciate 图片欣赏
     * @return 结果
     */
    @Override
    public int insertWxPictureAppreciate(WxPictureAppreciate wxPictureAppreciate)
    {
        return wxPictureAppreciateMapper.insertWxPictureAppreciate(wxPictureAppreciate);
    }

    /**
     * 修改图片欣赏
     *
     * @param wxPictureAppreciate 图片欣赏
     * @return 结果
     */
    @Override
    public int updateWxPictureAppreciate(WxPictureAppreciate wxPictureAppreciate)
    {
        return wxPictureAppreciateMapper.updateWxPictureAppreciate(wxPictureAppreciate);
    }

    /**
     * 批量删除图片欣赏
     *
     * @param uuids 需要删除的图片欣赏主键
     * @return 结果
     */
    @Override
    public int deleteWxPictureAppreciateByUuids(String[] uuids)
    {
        return wxPictureAppreciateMapper.deleteWxPictureAppreciateByUuids(uuids);
    }

    /**
     * 删除图片欣赏信息
     *
     * @param uuid 图片欣赏主键
     * @return 结果
     */
    @Override
    public int deleteWxPictureAppreciateByUuid(String uuid)
    {
        return wxPictureAppreciateMapper.deleteWxPictureAppreciateByUuid(uuid);
    }
}
