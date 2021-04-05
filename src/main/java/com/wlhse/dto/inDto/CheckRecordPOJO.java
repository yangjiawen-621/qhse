package com.wlhse.dto.inDto;

/**
 * Description:package the parameters from clients
 * Author:Coco
 * create:2020-08-02 11:21 AM
 **/
public class CheckRecordPOJO {
    private String companyCode;
    private String companyName;
    private String checkListCode;
    private String checkType;
    private String checkDate;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    public String getCheckListCode() {
        return checkListCode;
    }

    public void setCheckListCode(String checkListCode) {
        this.checkListCode = checkListCode;
    }

    @Override
    public String toString() {
        return "CheckRecordPOJO{" +
                "companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", checkListCode='" + checkListCode + '\'' +
                ", checkType='" + checkType + '\'' +
                ", checkDate='" + checkDate + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
