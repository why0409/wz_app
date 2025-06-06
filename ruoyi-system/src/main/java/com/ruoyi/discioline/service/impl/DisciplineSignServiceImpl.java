package com.ruoyi.discioline.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.discioline.domain.DisciolineTemplate;
import com.ruoyi.discioline.domain.DisciplineSign;
import com.ruoyi.discioline.mapper.DisciolineTemplateMapper;
import com.ruoyi.discioline.service.DisciplineSignService;
import com.ruoyi.discioline.mapper.DisciplineSignMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author zsxx-0042
* @description 针对表【discipline_sign】的数据库操作Service实现
* @createDate 2025-06-05 11:42:55
*/
@Service
public class DisciplineSignServiceImpl extends ServiceImpl<DisciplineSignMapper, DisciplineSign>
    implements DisciplineSignService{
    @Autowired
    private DisciplineSignMapper disciplineSignMapper;

    public List<DisciplineSign> selectByEntity(DisciplineSign disciplineSign){
        return  disciplineSignMapper.selectByEntity(disciplineSign);
    };

    public int updateByPrimaryKeySelective(DisciplineSign disciplineSign){
        return  disciplineSignMapper.updateByPrimaryKeySelective(disciplineSign);
    };
}




