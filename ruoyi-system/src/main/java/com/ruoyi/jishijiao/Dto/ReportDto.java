package com.ruoyi.jishijiao.Dto;

import java.util.List;

public class ReportDto {

    private String mobile;

    private String type;

    private String description;

    private String title;

    private String address;

    private String realName;

//    拍一拍来源类型
    private Integer paiYiPaiClient;


    /**
     * 经度
     */
    private String longitude ;
    /**
     * 维度
     */
    private String latitude;



    List<FileReturnDto> fileReturnDtoList;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<FileReturnDto> getFileReturnDtoList() {
        return fileReturnDtoList;
    }

    public void setFileReturnDtoList(List<FileReturnDto> fileReturnDtoList) {
        this.fileReturnDtoList = fileReturnDtoList;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Integer getPaiYiPaiClient() {
        return paiYiPaiClient;
    }

    public void setPaiYiPaiClient(Integer paiYiPaiClient) {
        this.paiYiPaiClient = paiYiPaiClient;
    }
}
