package com.ruoyi.xcx.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.xcx.mapper.XcxMessageMapper;
import com.ruoyi.xcx.domain.XcxMessage;
import com.ruoyi.xcx.service.IXcxMessageService;

/**
 * 一张图留言Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-08
 */
@Service
public class XcxMessageServiceImpl implements IXcxMessageService
{
    @Autowired
    private XcxMessageMapper xcxMessageMapper;

    /**
     * 查询一张图留言
     *
     * @param id 一张图留言主键
     * @return 一张图留言
     */
    @Override
    public XcxMessage selectXcxMessageById(Long id)
    {
        return xcxMessageMapper.selectXcxMessageById(id);
    }

    /**
     * 查询一张图留言列表
     *
     * @param xcxMessage 一张图留言
     * @return 一张图留言
     */
    @Override
    public List<XcxMessage> selectXcxMessageList(XcxMessage xcxMessage)
    {
        return xcxMessageMapper.selectXcxMessageList(xcxMessage);
    }

    /**
     * 新增一张图留言
     *
     * @param xcxMessage 一张图留言
     * @return 结果
     */
    @Override
    public int insertXcxMessage(XcxMessage xcxMessage)
    {
        xcxMessage.setCreateTime(DateUtils.getNowDate());
        return xcxMessageMapper.insertXcxMessage(xcxMessage);
    }

    /**
     * 修改一张图留言
     *
     * @param xcxMessage 一张图留言
     * @return 结果
     */
    @Override
    public int updateXcxMessage(XcxMessage xcxMessage)
    {
        xcxMessage.setUpdateTime(DateUtils.getNowDate());
        return xcxMessageMapper.updateXcxMessage(xcxMessage);
    }

    /**
     * 批量删除一张图留言
     *
     * @param ids 需要删除的一张图留言主键
     * @return 结果
     */
    @Override
    public int deleteXcxMessageByIds(Long[] ids)
    {
        return xcxMessageMapper.deleteXcxMessageByIds(ids);
    }

    /**
     * 删除一张图留言信息
     *
     * @param id 一张图留言主键
     * @return 结果
     */
    @Override
    public int deleteXcxMessageById(Long id)
    {
        return xcxMessageMapper.deleteXcxMessageById(id);
    }
}
