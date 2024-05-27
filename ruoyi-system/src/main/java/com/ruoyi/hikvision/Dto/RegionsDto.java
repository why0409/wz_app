package com.ruoyi.hikvision.Dto;

import java.util.List;

public class RegionsDto {

    private String indexCode;

    private String externalIndexCode;

    private String name;

    private String regionPathName;

    private List<HKVideoDto> hkVideoDtoList;


    public List<HKVideoDto> getHkVideoDtoList() {
        return hkVideoDtoList;
    }

    public void setHkVideoDtoList(List<HKVideoDto> hkVideoDtoList) {
        this.hkVideoDtoList = hkVideoDtoList;
    }

    public String getIndexCode() {
        return indexCode;
    }

    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode;
    }

    public String getExternalIndexCode() {
        return externalIndexCode;
    }

    public void setExternalIndexCode(String externalIndexCode) {
        this.externalIndexCode = externalIndexCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegionPathName() {
        return regionPathName;
    }

    public void setRegionPathName(String regionPathName) {
        this.regionPathName = regionPathName;
    }
}
