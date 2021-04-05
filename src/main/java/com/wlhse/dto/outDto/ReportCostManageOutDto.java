package com.wlhse.dto.outDto;

import org.springframework.stereotype.Component;

@Component
public class ReportCostManageOutDto {
    private String costYear;
    private String  reportType;
    private Integer reportCount;
    private Integer auditorCount;
    private Integer approverCount;
    private Float auditorTotalCost;
    private Float approverTotalCost;
    private Float reportTypeTotalCost;
    private String url;

    public String getCostYear() {
        return costYear;
    }

    public void setCostYear(String costYear) {
        this.costYear = costYear;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public Integer getReportCount() {
        return reportCount;
    }

    public void setReportCount(Integer reportCount) {
        this.reportCount = reportCount;
    }

    public Integer getAuditorCount() { return auditorCount; }

    public void setAuditorCount(Integer auditorCount) { this.auditorCount = auditorCount; }

    public Integer getApproverCount() { return approverCount; }

    public void setApproverCount(Integer approverCount) { this.approverCount = approverCount; }

    public Float getAuditorTotalCost() { return auditorTotalCost; }

    public void setAuditorTotalCost(Float auditorTotalCost) { this.auditorTotalCost = auditorTotalCost; }

    public Float getApproverTotalCost() { return approverTotalCost; }

    public void setApproverTotalCost(Float approverTotalCost) { this.approverTotalCost = approverTotalCost; }

    public Float getReportTypeTotalCost() { return reportTypeTotalCost; }

    public void setReportTypeTotalCost(Float reportTypeTotalCost) { this.reportTypeTotalCost = reportTypeTotalCost; }

    public String getUrl() {
    return url;
}

    public void setUrl(String url) {
        this.url = url;
    }
}
