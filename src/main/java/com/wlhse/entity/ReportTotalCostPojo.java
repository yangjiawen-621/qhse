package com.wlhse.entity;

import org.springframework.stereotype.Component;



@Component
public class ReportTotalCostPojo {
    private Integer costID;
    private String costYear;
    private Float totalCost;
    private Float surplusTotalCost;
    private String category; // 总金额类别，非ERP 还是 ERP
    private String personCategory;
    private String hash;
    private String url;

    public Integer getCostID() {
        return costID;
    }

    public void setCostID(Integer costID) {
        this.costID = costID;
    }

    public String getCostYear() {
        return costYear;
    }

    public void setCostYear(String costYear) { this.costYear = costYear; }

    public Float getTotalCost() { return totalCost; }

    public void setTotalCost(Float totalCost) { this.totalCost = totalCost; }

    public Float getSurplusTotalCost() { return surplusTotalCost; }

    public void setSurplusTotalCost(Float surplusTotalCost) { this.surplusTotalCost = surplusTotalCost; }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPersonCategory() {
        return personCategory;
    }

    public void setPersonCategory(String personCategory) {
        this.personCategory = personCategory;
    }

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
}
