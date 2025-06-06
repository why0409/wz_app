package com.ruoyi.discioline.mapper;

import com.ruoyi.discioline.domain.DisciolineTemplate;
import com.ruoyi.discioline.domain.DisciplineSign;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author zsxx-0042
* @description 针对表【discipline_sign】的数据库操作Mapper
* @createDate 2025-06-05 11:42:55
* @Entity com.ruoyi.discioline.domain.DisciplineSign
*/
public interface DisciplineSignMapper extends BaseMapper<DisciplineSign> {
    List<DisciplineSign> selectByEntity(DisciplineSign disciplineSign);
    int updateByPrimaryKeySelective(DisciplineSign disciplineSign);
}




