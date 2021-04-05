package com.wlhse.dto;

import com.wlhse.dto.getDto.BaseGetDto;

public class ReportCodeRule extends BaseGetDto {
    private Integer reportCodeRuleID;
    private String companyCode;
    private String companyName;
    private String business;
    private String businessType;
    private String serviceContent;
    private String businessCode;
    private String reportType;

    public Integer getReportCodeRuleID() {
        return reportCodeRuleID;
    }

    public void setReportCodeRuleID(Integer reportCodeRuleID) {
        this.reportCodeRuleID = reportCodeRuleID;
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

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getServiceContent() {
        return serviceContent;
    }

    public void setServiceContent(String serviceContent) {
        this.serviceContent = serviceContent;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }
}
