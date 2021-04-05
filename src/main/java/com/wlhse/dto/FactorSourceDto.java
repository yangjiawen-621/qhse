package com.wlhse.dto;

public class FactorSourceDto {
    private Integer id;
    private String factorSourceCode;
    private String factorSourceName;
    private String category;
    private Integer factorSourceId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFactorSourceCode() {
        return factorSourceCode;
    }

    public void setFactorSourceCode(String factorSourceCode) {
        this.factorSourceCode = factorSourceCode;
    }

    public String getFactorSourceName() {
        return factorSourceName;
    }

    public void setFactorSourceName(String factorSourceName) {
        this.factorSourceName = factorSourceName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getFactorSourceId() {
        return factorSourceId;
    }

    public void setFactorSourceId(Integer factorSourceId) {
        this.factorSourceId = factorSourceId;
    }
}
