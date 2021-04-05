package com.wlhse.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CheckRecordTreeDto {
    private Integer checkRecordID;
    private String checkListCode;
    private String checkListName;
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

    public CheckRecordTreeDto() {
    }

    public CheckRecordTreeDto(Integer checkRecordID, String checkListCode, String checkListName, String checkType, String companyName, String companyCode, String checkDate, String problems, String checkTypeCode, String pass, String checkPersonId, String checkPerson, String reason) {
        this.checkRecordID = checkRecordID;
        this.checkListCode = checkListCode;
        this.checkListName = checkListName;
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

    public String getCheckListCode() {
        return checkListCode;
    }

    public void setCheckListCode(String checkListCode) {
        this.checkListCode = checkListCode;
    }

    public String getCheckListName() {
        return checkListName;
    }

    public void setCheckListName(String checkListName) {
        this.checkListName = checkListName;
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
        return "CheckRecordTreeDto{" +
                "checkRecordID=" + checkRecordID +
                ", checkListCode='" + checkListCode + '\'' +
                ", checkListName='" + checkListName + '\'' +
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
