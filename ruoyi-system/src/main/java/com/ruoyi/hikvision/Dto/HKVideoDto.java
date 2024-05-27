package com.ruoyi.hikvision.Dto;

public class HKVideoDto {

    private String cameraIndexCode;

    private String unitIndexCode;//关联区域indexcode

    private String name;

    private String url;

    public String getCameraIndexCode() {
        return cameraIndexCode;
    }

    public void setCameraIndexCode(String cameraIndexCode) {
        this.cameraIndexCode = cameraIndexCode;
    }

    public String getUnitIndexCode() {
        return unitIndexCode;
    }

    public void setUnitIndexCode(String unitIndexCode) {
        this.unitIndexCode = unitIndexCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
