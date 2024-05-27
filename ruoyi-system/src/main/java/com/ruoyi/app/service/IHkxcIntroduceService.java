package com.ruoyi.app.service;

import java.util.List;
import com.ruoyi.app.domain.HkxcIntroduce;

/**
 * 航空新城-介绍管理Service接口
 *
 * @author ruoyi
 * @date 2024-05-07
 */
public interface IHkxcIntroduceService
{
    /**
     * 查询航空新城-介绍管理
     *
     * @param id 航空新城-介绍管理主键
     * @return 航空新城-介绍管理
     */
    public HkxcIntroduce selectHkxcIntroduceById(Long id);

    /**
     * 查询航空新城-介绍管理列表
     *
     * @param hkxcIntroduce 航空新城-介绍管理
     * @return 航空新城-介绍管理集合
     */
    public List<HkxcIntroduce> selectHkxcIntroduceList(HkxcIntroduce hkxcIntroduce);

    /**
     * 新增航空新城-介绍管理
     *
     * @param hkxcIntroduce 航空新城-介绍管理
     * @return 结果
     */
    public int insertHkxcIntroduce(HkxcIntroduce hkxcIntroduce);

    /**
     * 修改航空新城-介绍管理
     *
     * @param hkxcIntroduce 航空新城-介绍管理
     * @return 结果
     */
    public int updateHkxcIntroduce(HkxcIntroduce hkxcIntroduce);

    /**
     * 批量删除航空新城-介绍管理
     *
     * @param ids 需要删除的航空新城-介绍管理主键集合
     * @return 结果
     */
    public int deleteHkxcIntroduceByIds(Long[] ids);

    /**
     * 删除航空新城-介绍管理信息
     *
     * @param id 航空新城-介绍管理主键
     * @return 结果
     */
    public int deleteHkxcIntroduceById(Long id);
}
