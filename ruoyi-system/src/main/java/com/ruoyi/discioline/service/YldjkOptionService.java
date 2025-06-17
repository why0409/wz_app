package com.ruoyi.discioline.service;

import com.ruoyi.discioline.domain.DisciolineTemplate;
import com.ruoyi.discioline.domain.DisciplineSign;
import com.ruoyi.discioline.domain.YldjkOption;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author zsxx-0042
* @description 针对表【yldjk_option】的数据库操作Service
* @createDate 2025-06-16 18:41:46
*/
public interface YldjkOptionService extends IService<YldjkOption> {

    public List<YldjkOption> selectByEntity(YldjkOption disciolineTemplate);
    public int updateByPrimaryKeySelective(YldjkOption disciplineSign);
}
