package com.wlhse.dto;

import com.alibaba.fastjson.annotation.JSONType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@JSONType(orders = {"id","nodeCode","label","uRl","img","children"})
public class TreeDto implements Cloneable{
    private String nodeCode;
    private String label;
    private List<TreeDto> children;

    private String id;

    private String uRl;
    private String img;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getuRl() {
        return uRl;
    }

    public void setuRl(String uRl) {
        this.uRl = uRl;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    @Override
    public String toString() {
        return "TreeDto{" +
                "nodeCode='" + nodeCode + '\'' +
                ", label='" + label + '\'' +
                ", children=" + children +
                ", id='" + id + '\'' +
                ", uRl='" + uRl + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
