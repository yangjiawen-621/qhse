package com.wlhse.entity;

import org.springframework.stereotype.Component;

@Component
public class CompanyPojo {
    private Integer sysCompanyID; //ID
    private String companyCode; //组织机构编码
    private String name; //名称
    private Integer serialNO; //序号
    private String category1; //公司类别1
    private String category2; //公司类别2
    private String category3; //公司类别3
    private String description; //描述
    private String hash;  //hash值

    public CompanyPojo() {
    }

    public CompanyPojo(String companyCode, String name) {
        this.companyCode = companyCode;
        this.name = name;
    }

    public Integer getSysCompanyID() {
        return sysCompanyID;
    }

    public void setSysCompanyID(Integer sysCompanyID) {
        this.sysCompanyID = sysCompanyID;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSerialNO() {
        return serialNO;
    }

    public void setSerialNO(Integer serialNO) {
        this.serialNO = serialNO;
    }

    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }

    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    public String getCategory3() {
        return category3;
    }

    public void setCategory3(String category3) {
        this.category3 = category3;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

}
