package com.ruoyi.system.mapper;


import com.ruoyi.system.domain.WxClickmoduleInfo;
import com.ruoyi.system.domain.vo.WxClickmoduleInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WxClickmoduleInfoMapper {
    public int insertWxClickmoduleInfo(WxClickmoduleInfo wxClickmoduleInfo);

    public List<WxClickmoduleInfo> selectWxClickmoduleInfoCount(WxClickmoduleInfoDto wxClickmoduleInfo);

    /**
     * 查询个人的点击数据
     * @param wxClickmoduleInfo
     * @return
     */
    public List<WxClickmoduleInfo> selectWxClickmoduleInfoRecord(WxClickmoduleInfoDto wxClickmoduleInfo);

    public void deletetWxClickmoduleInfoExpireDate(@Param("expireDate") int expireDate);
}
