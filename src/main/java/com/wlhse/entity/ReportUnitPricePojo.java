package com.wlhse.entity;

import org.springframework.stereotype.Component;


@Component
public class ReportUnitPricePojo {
    private Integer unitPriceID;
    private String reportType;
    private Float unitPrice;
    private Float auditorProportion;
    private Float approverProportion;
    private String costYear;
    private String hash;
    private String url;
    private String type;

    public Integer getUnitPriceID() {
        return unitPriceID;
    }

    public void setUnitPriceID(Integer unitPriceID) {
        this.unitPriceID = unitPriceID;
    }

    public String getReportType() { return reportType; }

    public void setReportType(String reportType) { this.reportType = reportType; }

    public Float getUnitPrice() { return unitPrice; }

    public void setUnitPrice(Float unitPrice) { this.unitPrice = unitPrice; }

    public Float getAuditorProportion() { return auditorProportion; }

    public void setAuditorProportion(Float auditorProportion) { this.auditorProportion = auditorProportion; }

    public Float getApproverProportion() { return approverProportion; }

    public void setApproverProportion(Float approverProportion) { this.approverProportion = approverProportion; }

    public String getCostYear() {
        return costYear;
    }

    public void setCostYear(String costYear) { this.costYear = costYear; }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
