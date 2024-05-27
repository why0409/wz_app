package com.ruoyi.app.service;

import java.util.List;
import com.ruoyi.app.domain.HkxcPromotionalVideo;

/**
 * 航空新城-宣传片Service接口
 *
 * @author ruoyi
 * @date 2024-05-07
 */
public interface IHkxcPromotionalVideoService
{
    /**
     * 查询航空新城-宣传片
     *
     * @param id 航空新城-宣传片主键
     * @return 航空新城-宣传片
     */
    public HkxcPromotionalVideo selectHkxcPromotionalVideoById(Long id);

    /**
     * 查询航空新城-宣传片列表
     *
     * @param hkxcPromotionalVideo 航空新城-宣传片
     * @return 航空新城-宣传片集合
     */
    public List<HkxcPromotionalVideo> selectHkxcPromotionalVideoList(HkxcPromotionalVideo hkxcPromotionalVideo);

    /**
     * 新增航空新城-宣传片
     *
     * @param hkxcPromotionalVideo 航空新城-宣传片
     * @return 结果
     */
    public int insertHkxcPromotionalVideo(HkxcPromotionalVideo hkxcPromotionalVideo);

    /**
     * 修改航空新城-宣传片
     *
     * @param hkxcPromotionalVideo 航空新城-宣传片
     * @return 结果
     */
    public int updateHkxcPromotionalVideo(HkxcPromotionalVideo hkxcPromotionalVideo);

    /**
     * 批量删除航空新城-宣传片
     *
     * @param ids 需要删除的航空新城-宣传片主键集合
     * @return 结果
     */
    public int deleteHkxcPromotionalVideoByIds(Long[] ids);

    /**
     * 删除航空新城-宣传片信息
     *
     * @param id 航空新城-宣传片主键
     * @return 结果
     */
    public int deleteHkxcPromotionalVideoById(Long id);
}
