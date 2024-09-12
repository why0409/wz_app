package com.ruoyi.onePicture.mapper;

import com.ruoyi.onePicture.domain.XcxFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author tonyJiang
 * @Date 2023 10 16 14 14
 **/
public interface FileMapper {
    void insert(XcxFile xcxFile);

    List<XcxFile> selectFileByIdList(@Param("idList") List<Integer> fileIdList);
}
