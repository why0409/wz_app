package com.ruoyi.app.domain.vo;

import lombok.Data;

@Data
public class QueryPlantVo {

    private Double minFloorArea;
    private Double maxFloorArea;
    private Double minFloorHeight;
    private Double maxFloorHeight;
    private String assetName;

}
