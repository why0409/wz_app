package com.ruoyi.app.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("plant_permissions")
@Data
public class PlantPermissions {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String phone;
    private Integer isPlant;
    @TableField(exist = false)
    private String userId;
}
