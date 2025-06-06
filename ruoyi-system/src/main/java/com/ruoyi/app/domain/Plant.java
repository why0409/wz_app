package com.ruoyi.app.domain;
import java.util.List;
import lombok.Data;

@Data
public class Plant {
    private String id;
    private String assetName;
    private String assetIntroduction;
    private List<IntroductionAttachment> introductionAttachmentList;
    private String useTypeStr;
    private double rentalArea;
    private double rentalIncome;
    private String assetCharacterStr;
    private String managementUnitStr;
    private String ownershipUnitStr;
    private String usingUnit;
    private String assetAreaStr;
    private String locationCommunityStr;
    private String houseTypeStr;
    private String regionStr;
    private String industryTypeStr;
    private String longitude;
    private String latitude;
    private String hasPropertyCertStr;
    private String propertyCertNum;
    private String plantTypeStr;
    private int totalFloors;
    private double ancestralLandArea;
    private double buildingArea;
    private String remark;
    private String createTime;
    private String updateTime;
    private List<Floor> floorList;

}
