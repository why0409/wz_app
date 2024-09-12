package com.ruoyi.onePicture.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Author tonyJiang
 * @Date 2023 10 16 14 06
 **/
@Data
public class XcxFile {
    private Integer id;
    private String path;
    private String suffix;
    private String fileName;
    private Date uploadTime;
    private Double size;
}
