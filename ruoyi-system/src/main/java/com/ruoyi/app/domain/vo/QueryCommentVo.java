package com.ruoyi.app.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "QueryCommentVo", description = "新建留言参数模型")
public class QueryCommentVo {
    @ApiModelProperty(value = "业务id",required = true)
    private Number businessId;
    @ApiModelProperty(value = "资产类型",required = true)
    private Integer assetType;
    @ApiModelProperty(value = "意向留言",required = true)
    private String comment;
    @ApiModelProperty(value = "留言人姓名",required = true)
    private String member;
    @ApiModelProperty(value = "留言号码",required = true)
    private String phone;
    @ApiModelProperty(value = "地址",required = true)
    private String address;
    @ApiModelProperty(value = "留言时间",required = true)
    private String commentTime;
}
