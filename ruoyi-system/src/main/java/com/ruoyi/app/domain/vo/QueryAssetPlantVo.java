package com.ruoyi.app.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 厂房模型
 */
@Data
@ApiModel(value = "QueryAssetPlantVo", description = "厂房参数模型")
public class QueryAssetPlantVo {
    @ApiModelProperty(value = "关键字")
    private String keyword;
    @ApiModelProperty(value = "面积头")
    private String areaStart;
    @ApiModelProperty(value = "面积尾")
    private String areaEnd;
    @ApiModelProperty(value = "楼高低端")
    private String heightStart;
    @ApiModelProperty(value = "楼高高端")
    private String heightEnd;
    @ApiModelProperty(value = "类型，1厂房，2门面房")
    private String factoryType;
}
