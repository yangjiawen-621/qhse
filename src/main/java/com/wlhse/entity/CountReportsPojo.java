package com.wlhse.entity;

public class CountReportsPojo {
    private String companyCode;
    private String companyName;
    private Integer compelet;
    private Integer incompelet;
    private String completePrecent;
    private Integer total;

    public String getCompletePrecent() {
        return completePrecent;
    }

    public void setCompletePrecent(String completePrecent) {
        this.completePrecent = completePrecent;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) { this.companyCode = companyCode; }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public CountReportsPojo() {
    }

    public Integer getCompelet() {
        return compelet;
    }

    public void setCompelet(Integer compelet) {
        this.compelet = compelet;
    }

    public Integer getIncompelet() {
        return incompelet;
    }

    public void setIncompelet(Integer incompelet) {
        this.incompelet = incompelet;
    }
}
