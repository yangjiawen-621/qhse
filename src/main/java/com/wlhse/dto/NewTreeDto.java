package com.wlhse.dto;

import java.util.List;

public class NewTreeDto {
    private String nodeCode;
    private String label;
    private List<TreeDto> children;

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<TreeDto> getChildren() {
        return children;
    }

    public void setChildren(List<TreeDto> children) {
        this.children = children;
    }
}
