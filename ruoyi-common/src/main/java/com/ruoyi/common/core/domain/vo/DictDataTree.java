package com.ruoyi.common.core.domain.vo;


import java.util.List;

public class DictDataTree {

    /** 节点ID */
    private String label;
    /** 节点名称 */
    private String value;
    private List<DictDataTree> children;

    public DictDataTree(){}

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<DictDataTree> getChildren() {
        return children;
    }

    public void setChildren(List<DictDataTree> children) {
        this.children = children;
    }
}
