package com.wlhse.entity;

import com.wlhse.dto.getDto.BaseGetDto;

public class QHSEAccidentPojo extends BaseGetDto {
    private Integer accidentID;
    private String companyCode;
    private String address;
    private String accidentNature;
    private String briefDescription;
    private String emergencyHandle;
    private String companyName;
    private String fileID;
    private String occurentTime;
    private String startDate;
    private String endDate;

    public String getCompanyCode() {
        return companyCode;
    }

    public String getOccurentTime() {
        return occurentTime;
    }

    public void setOccurentTime(String occurentTime) {
        this.occurentTime = occurentTime;
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

    public String getFileID() {
        return fileID;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }

    public Integer getAccidentID() {
        return accidentID;
    }

    public void setAccidentID(Integer accidentID) {
        this.accidentID = accidentID;
    }

    public String companyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccidentNature() {
        return accidentNature;
    }

    public void setAccidentNature(String accidentNature) {
        this.accidentNature = accidentNature;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    public String getEmergencyHandle() {
        return emergencyHandle;
    }

    public void setEmergencyHandle(String emergencyHandle) {
        this.emergencyHandle = emergencyHandle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
