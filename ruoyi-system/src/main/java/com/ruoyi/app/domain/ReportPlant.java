package com.ruoyi.app.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("report_plant")
public class ReportPlant {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String plantId;
    private String plantName;
    private String plantLocation;
    private String floorName;
    private String filePath;
    private Double area;
    private Double height;
    private String status;
    private String content;
    private String contact;
    private String phone;
    private String address;
    private Date createTime;

}
