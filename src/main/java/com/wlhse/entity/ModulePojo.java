package com.wlhse.entity;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModulePojo {
    private Integer id;
    private String moduleCode;
    private String name;
    private String url;
    private String img;

    private String parentCode;
    private Integer lenth;

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public Integer getLenth() {
        return lenth;
    }

    public void setLenth(Integer lenth) {
        this.lenth = lenth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
