package com.ruoyi.activities.mapper;

import com.ruoyi.activities.domain.HomestayInfo;
import com.ruoyi.activities.domain.vo.HomestayInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 民宿信息Mapper接口
 *
 * @author ruoyi
 * @date 2024-06-20
 */
public interface HomestayInfoMapper
{
    /**
     * 查询民宿信息
     *
     * @param id 民宿信息主键
     * @return 民宿信息
     */
    public HomestayInfo selectHomestayInfoById(Long id);

    /**
     * 查询民宿信息列表
     *
     * @param homestayInfo 民宿信息
     * @return 民宿信息集合
     */
    public List<HomestayInfo> selectHomestayInfoList(HomestayInfo homestayInfo);

    /**
     * 新增民宿信息
     *
     * @param homestayInfo 民宿信息
     * @return 结果
     */
    public int insertHomestayInfo(HomestayInfo homestayInfo);

    /**
     * 修改民宿信息
     *
     * @param homestayInfo 民宿信息
     * @return 结果
     */
    public int updateHomestayInfo(HomestayInfo homestayInfo);

    /**
     * 删除民宿信息
     *
     * @param id 民宿信息主键
     * @return 结果
     */
    public int deleteHomestayInfoById(Long id);

    /**
     * 批量删除民宿信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHomestayInfoByIds(Long[] ids);

    public int checkVerifyPermission(@Param("phone") String phone,
                                     @Param("activitiesId") Long activitiesId);

    public List<HomestayInfo> getHomestayInfoListByVerifyPhone(@Param("phone") String phone,
                                                               @Param("activitiesId") Long activitiesId);

    public List<HomestayInfo> getHomestayInfoByIds(List<Long> ids);

    public List<HomestayInfoVo> getHomestayListOrderByWinstatus(Long activitiesId);

}
