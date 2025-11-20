package com.ruoyi.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.app.domain.WxAdmin;

import java.util.List;

public interface IWxAdminService extends IService<WxAdmin> {
    /**
     * 查询列表（封装查询条件）
     */
    List<WxAdmin> selectWxAdminList(WxAdmin wxAdmin);

    long countByPhone(String phone);
}