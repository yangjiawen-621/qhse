package com.wlhse.entity;

public class FileAuditRecordPojo {
    private Integer fileAuditRecordId;
    private Integer fileAuditId;
    private String code;
    private String codeScore;
    private String pass;

    public Integer getFileAuditRecordId() {
        return fileAuditRecordId;
    }

    public void setFileAuditRecordId(Integer fileAuditRecordId) {
        this.fileAuditRecordId = fileAuditRecordId;
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
}
