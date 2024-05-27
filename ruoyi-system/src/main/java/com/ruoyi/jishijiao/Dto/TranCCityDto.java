package com.ruoyi.jishijiao.Dto;

public class TranCCityDto {
    private String mobile;//手机号
    private Integer page;//页码
    private Integer size;//每页条数
    private Integer sourceFrom;//事件来源,参数不传默认全部，1-AI中台，3-区长信箱，4-随手拍，5-其他，6-12345热线
    private Integer eventTypeId;//事件类型id,参数不传默认全部
    private String startTime;//开始时间,格式：2022-11-01 00:00:00
    private String endTime;//结束时间,格式：2022-11-01 23:59:59
    private Integer priority;//优先级,参数不传默认全部,1:紧急,2:优先,3:一般
    private Integer eventStatus;//状态: 1:待研判(分拨)、4:待处置（处置中/办理）、6、已办结
    private Integer deptId;
    private Integer checkedUid;
    private Integer uid;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    private String deviceId;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(Integer sourceFrom) {
        this.sourceFrom = sourceFrom;
    }

    public Integer getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(Integer eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(Integer eventStatus) {
        this.eventStatus = eventStatus;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getCheckedUid() {
        return checkedUid;
    }

    public void setCheckedUid(Integer checkedUid) {
        this.checkedUid = checkedUid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
