package com.wlhse.dto;

public class QualityYearManagerDto {
    private String quality_CompanyYearManagerSysElementTable_ID;
    private String companyCode;
    private String companyName;
    private String year;
    private String elementTableName;
    private String status;
    private String issuedID;

    public QualityYearManagerDto() {
    }

    public QualityYearManagerDto(String quality_CompanyYearManagerSysElementTable_ID, String companyCode, String companyName, String year, String elementTableName, String status, String issuedID) {
        this.quality_CompanyYearManagerSysElementTable_ID = quality_CompanyYearManagerSysElementTable_ID;
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.year = year;
        this.elementTableName = elementTableName;
        this.status = status;
        this.issuedID = issuedID;
    }

    public String getQuality_CompanyYearManagerSysElementTable_ID() {
        return quality_CompanyYearManagerSysElementTable_ID;
    }

    public void setQuality_CompanyYearManagerSysElementTable_ID(String quality_CompanyYearManagerSysElementTable_ID) {
        this.quality_CompanyYearManagerSysElementTable_ID = quality_CompanyYearManagerSysElementTable_ID;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getElementTableName() {
        return elementTableName;
    }

    public void setElementTableName(String elementTableName) {
        this.elementTableName = elementTableName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIssuedID() {
        return issuedID;
    }

    public void setIssuedID(String issuedID) {
        this.issuedID = issuedID;
    }

    @Override
    public String toString() {
        return "QualityYearManagerDto{" +
                "quality_CompanyYearManagerSysElementTable_ID='" + quality_CompanyYearManagerSysElementTable_ID + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", year='" + year + '\'' +
                ", elementTableName='" + elementTableName + '\'' +
                ", status='" + status + '\'' +
                ", issuedID='" + issuedID + '\'' +
                '}';
    }
}
