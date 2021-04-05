package com.wlhse.dto;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SampleDto {
    private Integer sampleID;
    private Integer reportID;
    private String sampleName;
    private String sampleNO;
    private String sampleModel;
    private String sampleCode;
    private String entrustCompany;
    private String productCompany;
    private String customerCompany;
    private String arriveDate;
    private String checkDate;
    private String checkAddress;
    private String checkProject;
    private String checkGuist;
    private String checkResult;
    private String sampleCheckPersonName;

    public void setSampleID(Integer sampleID) {
        this.sampleID = sampleID;
    }

    public void setReportID(Integer reportID) {
        this.reportID = reportID;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public void setSampleNO(String sampleNO) {
        this.sampleNO = sampleNO;
    }

    public void setSampleModel(String sampleModel) {
        this.sampleModel = sampleModel;
    }

    public void setSampleCode(String sampleCode) {
        this.sampleCode = sampleCode;
    }

    public void setEntrustCompany(String entrustCompany) {
        this.entrustCompany = entrustCompany;
    }

    public void setProductCompany(String productCompany) {
        this.productCompany = productCompany;
    }

    public void setCustomerCompany(String customerCompany) {
        this.customerCompany = customerCompany;
    }

    public void setArriveDate(String arriveDate) {
        this.arriveDate = arriveDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public void setCheckAddress(String checkAddress) {
        this.checkAddress = checkAddress;
    }

    public void setCheckProject(String checkProject) {
        this.checkProject = checkProject;
    }

    public void setCheckGuist(String checkGuist) {
        this.checkGuist = checkGuist;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public void setSampleCheckPersonName(String sampleCheckPersonName) {
        this.sampleCheckPersonName = sampleCheckPersonName;
    }

    public Integer getSampleID() {
        return sampleID;
    }

    public Integer getReportID() {
        return reportID;
    }

    public String getSampleName() {
        return sampleName;
    }

    public String getSampleNO() {
        return sampleNO;
    }

    public String getSampleModel() {
        return sampleModel;
    }

    public String getSampleCode() {
        return sampleCode;
    }

    public String getEntrustCompany() {
        return entrustCompany;
    }

    public String getProductCompany() {
        return productCompany;
    }

    public String getCustomerCompany() {
        return customerCompany;
    }

    public String getArriveDate() {
        return arriveDate;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public String getCheckAddress() {
        return checkAddress;
    }

    public String getCheckProject() {
        return checkProject;
    }

    public String getCheckGuist() {
        return checkGuist;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public String getSampleCheckPersonName() {
        return sampleCheckPersonName;
    }
}
