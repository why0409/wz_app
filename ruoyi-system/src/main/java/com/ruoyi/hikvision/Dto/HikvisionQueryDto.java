package com.ruoyi.hikvision.Dto;

import java.util.Map;

public class HikvisionQueryDto {


   private int pageNum;
   private int pageSize;


    /** 请求参数 */
    private Map<String, Object> params;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
