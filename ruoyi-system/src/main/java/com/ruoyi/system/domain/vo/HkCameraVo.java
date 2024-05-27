package com.ruoyi.system.domain.vo;

import lombok.Data;

@Data
public class HkCameraVo {
    /**
     * 相机索引
     */
    private String cameraIndexCode;
    /**
     * 相机名称
     */
    private String name;
    /**
     * 高度
     */
    private String altitude;
    /**
     * 经度
     */
    private String latitude;
    /**
     * 纬度
     */
    private String longitude;
    /**
     * 单位名称
     */
    private String unitIndexCode;
    /**
     * 状态
     */
    private String status;
}
