package com.wlhse.dto;

public class QualityFileAuditRecordDto {
    private Integer qualityFileAuditRecordID;
    private Integer fileAuditId;
    private String code;
    private String codeScore;
    private String pass;
    private String companyCode;
    private String companyName;
    private String auditTime;
    private String additor;
    /**
     * 不录入的原因描述
     */
    private String noPassReason;

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getAdditor() {
        return additor;
    }

    public void setAdditor(String additor) {
        this.additor = additor;
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

    public Integer getQualityFileAuditRecordID() {
        return qualityFileAuditRecordID;
    }

    public void setQualityFileAuditRecordID(Integer qualityFileAuditRecordID) {
        this.qualityFileAuditRecordID = qualityFileAuditRecordID;
    }

    public Integer getFileAuditId() {
        return fileAuditId;
    }

    public void setFileAuditId(Integer fileAuditId) {
        this.fileAuditId = fileAuditId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeScore() {
        return codeScore;
    }

    public void setCodeScore(String codeScore) {
        this.codeScore = codeScore;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNoPassReason() {
        return noPassReason;
    }

    public void setNoPassReason(String noPassReason) {
        this.noPassReason = noPassReason;
    }
}
