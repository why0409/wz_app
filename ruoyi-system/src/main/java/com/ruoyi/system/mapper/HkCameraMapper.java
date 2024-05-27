package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.vo.HkCameraVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HkCameraMapper {

    void insert(HkCameraVo hkCameraVo);

    void deleteAll();

    List<HkCameraVo> queryListByName(@Param("name") String name);

    Map<String, Object> staticsOnline(String indexCode);

    int staticNumByCode(String cameraIndexCode);

    void updateHkCamera(HkCameraVo hkCameraVo);
}
