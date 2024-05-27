package com.ruoyi.xcx.mapper;

import java.util.List;
import com.ruoyi.xcx.domain.XcxMessage;

/**
 * 一张图留言Mapper接口
 *
 * @author ruoyi
 * @date 2023-11-08
 */
public interface XcxMessageMapper
{
    /**
     * 查询一张图留言
     *
     * @param id 一张图留言主键
     * @return 一张图留言
     */
    public XcxMessage selectXcxMessageById(Long id);

    /**
     * 查询一张图留言列表
     *
     * @param xcxMessage 一张图留言
     * @return 一张图留言集合
     */
    public List<XcxMessage> selectXcxMessageList(XcxMessage xcxMessage);

    /**
     * 新增一张图留言
     *
     * @param xcxMessage 一张图留言
     * @return 结果
     */
    public int insertXcxMessage(XcxMessage xcxMessage);

    /**
     * 修改一张图留言
     *
     * @param xcxMessage 一张图留言
     * @return 结果
     */
    public int updateXcxMessage(XcxMessage xcxMessage);

    /**
     * 删除一张图留言
     *
     * @param id 一张图留言主键
     * @return 结果
     */
    public int deleteXcxMessageById(Long id);

    /**
     * 批量删除一张图留言
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXcxMessageByIds(Long[] ids);
}
