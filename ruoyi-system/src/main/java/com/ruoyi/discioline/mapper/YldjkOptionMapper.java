package com.ruoyi.discioline.mapper;

import com.ruoyi.discioline.domain.DisciplineSign;
import com.ruoyi.discioline.domain.YldjkOption;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author zsxx-0042
* @description 针对表【yldjk_option】的数据库操作Mapper
* @createDate 2025-06-16 18:41:46
* @Entity com.ruoyi.discioline.domain.YldjkOption
*/
public interface YldjkOptionMapper extends BaseMapper<YldjkOption> {
    List<YldjkOption> selectByEntity(YldjkOption disciplineSign);
    int updateByPrimaryKeySelective(YldjkOption disciplineSign);
}




