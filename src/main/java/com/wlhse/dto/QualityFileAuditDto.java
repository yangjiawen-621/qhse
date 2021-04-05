package com.wlhse.dto;

import com.wlhse.dto.getDto.BaseGetDto;

public class QualityFileAuditDto extends BaseGetDto {
    private Integer fileAuditId;
    private Integer tableId;
    private String auditName;
    private String auditType;
    private String additor;
    private String auditTime;
    private String companyCode;
    private String companyName;
    private String year;

    public Integer getFileAuditId() {
        return fileAuditId;
    }

    public void setFileAuditId(Integer fileAuditId) {
        this.fileAuditId = fileAuditId;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }

    public String getAdditor() {
        return additor;
    }

    public void setAdditor(String additor) {
        this.additor = additor;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
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
}
