package com.ruoyi.system.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class WxSmsLog implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    private String id;
    /**
     * 接收人手机号
     */
    private String phone;
    /**
     * 短信内容
     */
    private String contents;
    /**
     * 发送时间
     */
    private Date sendTime;
    /**
     * 次数
     */
    private int flag;
}
