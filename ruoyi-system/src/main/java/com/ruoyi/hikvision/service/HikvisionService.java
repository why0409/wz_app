package com.ruoyi.hikvision.service;

import com.ruoyi.hikvision.Dto.RegionsDto;

import java.util.List;
import java.util.Map;

public interface HikvisionService {
    Map<String,Object> getAllShiPingInfo(Integer pageNo,Integer pageSize);

//    Map<String,Object> camerasPreviewURLsFront(String indexCode);

    void TimingGetHIKList(Integer pageNo,Integer pageSize);

    List<RegionsDto> getListFromFront();

    void getCameraUrl();

    void receiveFromZD(Map<String, Object> map);
}
