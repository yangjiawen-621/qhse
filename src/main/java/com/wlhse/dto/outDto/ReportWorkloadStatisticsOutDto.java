package com.wlhse.dto.outDto;

public class ReportWorkloadStatisticsOutDto {
    private String companyName;
    private String personName;
    private String personCategory;
    private String reportType;
    private float unitPrice;
    private String role;
    private int typeCount;
    private float rewardAmount;
    private int reportDelayReportNums;
    private int reportDelayDays;
    private String remarks;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) { this.personName = personName; }

    public String getPersonCategory() {
        return personCategory;
    }

    public void setPersonCategory(String personCategory) { this.personCategory = personCategory; }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getTypeCount() {
        return typeCount;
    }

    public void setTypeCount(int typeCount) {
        this.typeCount = typeCount;
    }

    public float getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(float rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public int getReportDelayReportNums() {
        return reportDelayReportNums;
    }

    public void setReportDelayReportNums(int reportDelayReportNums) { this.reportDelayReportNums = reportDelayReportNums; }

    public int getReportDelayDays() {
        return reportDelayDays;
    }

    public void setReportDelayDays(int reportDelayDays) {
        this.reportDelayDays = reportDelayDays;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
