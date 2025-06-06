package com.ruoyi.discioline.service;

import com.ruoyi.discioline.domain.DisciolineTemplate;
import com.ruoyi.discioline.domain.DisciplineSign;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author zsxx-0042
* @description 针对表【discipline_sign】的数据库操作Service
* @createDate 2025-06-05 11:42:55
*/
public interface DisciplineSignService extends IService<DisciplineSign> {
    public List<DisciplineSign> selectByEntity(DisciplineSign disciplineSign);
    public int updateByPrimaryKeySelective(DisciplineSign disciplineSign);
}
