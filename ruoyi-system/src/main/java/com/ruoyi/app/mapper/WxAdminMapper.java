package com.ruoyi.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.app.domain.WxAdmin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WxAdminMapper extends BaseMapper<WxAdmin> {
    // MP 自动处理了 CRUD，此处无需手写方法
}