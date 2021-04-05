package com.wlhse.dto.inDto;

public class CompanyYearManagerDto {
    private String qHSE_CompanyYearManagerSysElementTable_ID;
    private String companyCode;
    private String companyName;
    private String year;
    private String elementTableName;
    private String status;
    private String issuedID;


    public String getQHSE_CompanyYearManagerSysElementTable_ID() {
        return qHSE_CompanyYearManagerSysElementTable_ID;
    }

    public void setQHSE_CompanyYearManagerSysElementTable_ID(String qHSE_CompanyYearManagerSysElementTable_ID) {
        this.qHSE_CompanyYearManagerSysElementTable_ID = qHSE_CompanyYearManagerSysElementTable_ID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getqHSE_CompanyYearManagerSysElementTable_ID() {
        return qHSE_CompanyYearManagerSysElementTable_ID;
    }

    public void setqHSE_CompanyYearManagerSysElementTable_ID(String qHSE_CompanyYearManagerSysElementTable_ID) {
        this.qHSE_CompanyYearManagerSysElementTable_ID = qHSE_CompanyYearManagerSysElementTable_ID;
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

    @Override
    public String toString() {
        return "CompanyYearManagerDto{" +
                "qHSE_CompanyYearManagerSysElementTable_ID='" + qHSE_CompanyYearManagerSysElementTable_ID + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", year='" + year + '\'' +
                ", elementTableName='" + elementTableName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
