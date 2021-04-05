package com.wlhse.entity;

import java.time.Year;

public class QHSECompanyYearManagerSysElementTablePojo {
   private Integer id;
   private String  companyCode;
    private String companyName;
    private String year;
    private String elementTableName;
    private String  status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
