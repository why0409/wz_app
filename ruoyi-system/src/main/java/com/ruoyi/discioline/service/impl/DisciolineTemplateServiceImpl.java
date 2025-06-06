package com.ruoyi.discioline.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.discioline.domain.DisciolineTemplate;
import com.ruoyi.discioline.service.DisciolineTemplateService;
import com.ruoyi.discioline.mapper.DisciolineTemplateMapper;
import com.ruoyi.electricity.mapper.YdEnterpriseDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author zsxx-0042
* @description 针对表【discioline_template】的数据库操作Service实现
* @createDate 2025-06-05 11:13:17
*/
@Service
public class DisciolineTemplateServiceImpl extends ServiceImpl<DisciolineTemplateMapper, DisciolineTemplate>
    implements DisciolineTemplateService{
    @Autowired
    private DisciolineTemplateMapper disciolineTemplateMapper;

    public List<DisciolineTemplate> selectByEntity(DisciolineTemplate disciolineTemplate){
        return  disciolineTemplateMapper.selectByEntity(disciolineTemplate);
    };

    public int updateByPrimaryKeySelective(DisciolineTemplate disciolineTemplate){
        return  disciolineTemplateMapper.updateByPrimaryKeySelective(disciolineTemplate);
    };


}




