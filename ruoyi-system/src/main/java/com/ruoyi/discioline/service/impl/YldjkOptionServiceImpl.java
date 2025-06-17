package com.ruoyi.discioline.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.discioline.domain.DisciplineSign;
import com.ruoyi.discioline.domain.YldjkOption;
import com.ruoyi.discioline.mapper.YldjkOptionMapper;
import com.ruoyi.discioline.service.YldjkOptionService;
import com.ruoyi.discioline.mapper.YldjkOptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author zsxx-0042
* @description 针对表【yldjk_option】的数据库操作Service实现
* @createDate 2025-06-16 18:41:46
*/
@Service
public class YldjkOptionServiceImpl extends ServiceImpl<YldjkOptionMapper, YldjkOption>
    implements YldjkOptionService{
    @Autowired
    private YldjkOptionMapper yldjkOptionMapper;
    public List<YldjkOption> selectByEntity(YldjkOption disciplineSign){
        return  yldjkOptionMapper.selectByEntity(disciplineSign);
    };

    public int updateByPrimaryKeySelective(YldjkOption disciplineSign){
        return  yldjkOptionMapper.updateByPrimaryKeySelective(disciplineSign);
    };
}




