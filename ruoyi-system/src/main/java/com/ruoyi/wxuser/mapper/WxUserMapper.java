package com.ruoyi.wxuser.mapper;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.wxuser.domain.WxUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户Mapper接口
 * 
 * @author lgh
 * @date 2022-11-22
 */
@DataSource(value = DataSourceType.MYDB)
public interface WxUserMapper
{
    /**
     * 查询用户
     * 
     * @param id 用户主键
     * @return 用户
     */
    public WxUser selectWxUserById(String id);

    /**
     * 查询用户列表
     * 
     * @param wxUser 用户
     * @return 用户集合
     */
    public List<WxUser> selectWxUserList(WxUser wxUser);

    /**
     * 新增用户
     * 
     * @param wxUser 用户
     * @return 结果
     */
    public int insertWxUser(WxUser wxUser);

    /**
     * 修改用户
     * 
     * @param wxUser 用户
     * @return 结果
     */
    public int updateWxUser(WxUser wxUser);

    /**
     * 删除用户
     * 
     * @param id 用户主键
     * @return 结果
     */
    public int deleteWxUserById(String id);

    /**
     * 批量删除用户
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxUserByIds(String[] ids);

    public List<WxUser> selectWxUserByPhoneList(@Param("phoneList") List<String> phoneList,
                                                @Param("start") int start,
                                                @Param("end") int end);

    public int countWxUserByPhoneList(@Param("phoneList") List<String> phoneList);

    WxUser selectWxUserByPhone(String mobile);
}
