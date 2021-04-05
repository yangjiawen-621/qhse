package com.wlhse.dto;

public class QualityProblemDescriptionDto {
    private Integer qquality_AuditProblemRecord_ID;
    private Integer qquality_FileAudit_ID;
    private Integer qquality_FileAuditRecord_ID;
    private String problemDescription;
    private String code;
    private String startDate;
    private String endDate;
    private String auditor;
    private String companyCode;
    private String companyName;
    private String status;
    private String situation;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getQquality_AuditProblemRecord_ID() {
        return qquality_AuditProblemRecord_ID;
    }

    public void setQquality_AuditProblemRecord_ID(Integer qquality_AuditProblemRecord_ID) {
        this.qquality_AuditProblemRecord_ID = qquality_AuditProblemRecord_ID;
    }

    public Integer getQquality_FileAudit_ID() {
        return qquality_FileAudit_ID;
    }

    public void setQquality_FileAudit_ID(Integer qquality_FileAudit_ID) {
        this.qquality_FileAudit_ID = qquality_FileAudit_ID;
    }

    public Integer getQquality_FileAuditRecord_ID() {
        return qquality_FileAuditRecord_ID;
    }

    public void setQquality_FileAuditRecord_ID(Integer qquality_FileAuditRecord_ID) {
        this.qquality_FileAuditRecord_ID = qquality_FileAuditRecord_ID;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
