package com.ruoyi.discioline.mapper;

import com.ruoyi.discioline.domain.DisciolineTemplate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author zsxx-0042
* @description 针对表【discioline_template】的数据库操作Mapper
* @createDate 2025-06-05 11:13:17
* @Entity com.ruoyi.discioline.domain.DisciolineTemplate
*/
public interface DisciolineTemplateMapper extends BaseMapper<DisciolineTemplate> {
    List<DisciolineTemplate> selectByEntity(DisciolineTemplate disciolineTemplate);
    int updateByPrimaryKeySelective(DisciolineTemplate disciolineTemplate);
}




