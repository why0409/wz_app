package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.FgwProProgress;

import java.util.List;

public class FgwProProgressDto extends FgwProProgress {

    private List<Integer> belongtodeptArr;

    public List<Integer> getBelongtodeptArr() {
        return belongtodeptArr;
    }

    public String deptNames;
    public String deptIds;


    public String getDeptNames() {
        return deptNames;
    }

    public void setDeptNames(String deptNames) {
        this.deptNames = deptNames;
    }

    public String getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(String deptIds) {
        this.deptIds = deptIds;
    }

    public void setBelongtodeptArr(List<Integer> belongtodeptArr) {
        this.belongtodeptArr = belongtodeptArr;
    }
}
