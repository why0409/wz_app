package com.ruoyi.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.app.domain.WxAdmin;
import com.ruoyi.app.mapper.WxAdminMapper;
import com.ruoyi.app.service.IWxAdminService;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxAdminServiceImpl extends ServiceImpl<WxAdminMapper, WxAdmin> implements IWxAdminService {

    @Override
    public List<WxAdmin> selectWxAdminList(WxAdmin wxAdmin) {
        LambdaQueryWrapper<WxAdmin> lqw = new LambdaQueryWrapper<>();
        
        // 构建查询条件
        lqw.like(StringUtils.isNotBlank(wxAdmin.getName()), WxAdmin::getName, wxAdmin.getName());
        lqw.eq(StringUtils.isNotBlank(wxAdmin.getPhone()), WxAdmin::getPhone, wxAdmin.getPhone());
        
        // 排序 (可选，例如按创建时间倒序)
        lqw.orderByDesc(WxAdmin::getCreateTime);
        
        return this.list(lqw);
    }

    @Override
    public long countByPhone(String phone) {

        return this.count(new LambdaQueryWrapper<WxAdmin>().eq(WxAdmin::getPhone, phone));

    }
}