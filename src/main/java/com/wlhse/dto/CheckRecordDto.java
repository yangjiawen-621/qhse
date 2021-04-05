package com.wlhse.dto;

public class CheckRecordDto {
    private Integer checkRecordID;
    private String code;
    private String name;
    private String checkType;
    private String companyName;
    private String companyCode;
    private String checkDate;
    private String problems;
    private String checkTypeCode;
    private String pass;
    private String checkPersonId;
    private String checkPerson;
    private String reason;

    public CheckRecordDto() {
    }

    public CheckRecordDto(Integer checkRecordID, String code, String name, String checkType, String companyName, String companyCode, String checkDate, String problems, String checkTypeCode, String pass, String checkPersonId, String checkPerson, String reason) {
        this.checkRecordID = checkRecordID;
        this.code = code;
        this.name = name;
        this.checkType = checkType;
        this.companyName = companyName;
        this.companyCode = companyCode;
        this.checkDate = checkDate;
        this.problems = problems;
        this.checkTypeCode = checkTypeCode;
        this.pass = pass;
        this.checkPersonId = checkPersonId;
        this.checkPerson = checkPerson;
        this.reason = reason;
    }

    public Integer getCheckRecordID() {
        return checkRecordID;
    }

    public void setCheckRecordID(Integer checkRecordID) {
        this.checkRecordID = checkRecordID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

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

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getProblems() {
        return problems;
    }

    public void setProblems(String problems) {
        this.problems = problems;
    }

    public String getCheckTypeCode() {
        return checkTypeCode;
    }

    public void setCheckTypeCode(String checkTypeCode) {
        this.checkTypeCode = checkTypeCode;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCheckPersonId() {
        return checkPersonId;
    }

    public void setCheckPersonId(String checkPersonId) {
        this.checkPersonId = checkPersonId;
    }

    public String getCheckPerson() {
        return checkPerson;
    }

    public void setCheckPerson(String checkPerson) {
        this.checkPerson = checkPerson;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    @Override
    public String toString() {
        return "CheckRecordDto{" +
                "checkRecordID=" + checkRecordID +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", checkType='" + checkType + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", checkDate='" + checkDate + '\'' +
                ", problems='" + problems + '\'' +
                ", checkTypeCode='" + checkTypeCode + '\'' +
                ", pass='" + pass + '\'' +
                ", checkPersonId='" + checkPersonId + '\'' +
                ", checkPerson='" + checkPerson + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
