package com.ruoyi.app.domain;
import java.util.List;
import lombok.Data;

@Data
public class Floor {
    private long id;
    private long assetId;
    private String localFloor;
    private String useTypeStr;
    private double floorHeight;
    private double valuation;
    private double floorArea;
    private String leasedEnterprise;
    private String createTime;
    private List<FloorAttachment> floorAttachmentList;
}
