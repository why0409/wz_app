package com.ruoyi.discioline.service;

import com.ruoyi.discioline.domain.DisciolineTemplate;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author zsxx-0042
* @description 针对表【discioline_template】的数据库操作Service
* @createDate 2025-06-05 11:13:17
*/
public interface DisciolineTemplateService extends IService<DisciolineTemplate> {

    public  List<DisciolineTemplate> selectByEntity(DisciolineTemplate disciolineTemplate);
    public int updateByPrimaryKeySelective(DisciolineTemplate disciolineTemplate);
}
