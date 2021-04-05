package com.wlhse.entity;


import java.time.Year;
import java.util.LinkedList;
import java.util.List;

/**
 * Author:melon
 * Origin:2020/10/5
 **/
public class QualityManergerSysElementPojo {
    private Integer quality_CompanyYearManagerSysElement_ID;//要素id
    private Integer quality_CompanyYearManagerSysElementTable_ID;//要素表id
    private String code;
    private String name;
    private String content;
    private String auditMode;
    private String initialScore;
    private String formula;
    private String totalCount;
    private String status;
    private String companyCode;
    private String  companyName;
    private String year;
    private String fileCheckStatus;
    private String configStatus;
    private String  checkStatus;
    private String schedule;
    private List<QualityManergerSysElementPojo> childNode = new LinkedList<>();

    public Integer getQuality_CompanyYearManagerSysElement_ID() {
        return quality_CompanyYearManagerSysElement_ID;
    }

    public void setQuality_CompanyYearManagerSysElement_ID(Integer quality_CompanyYearManagerSysElement_ID) {
        this.quality_CompanyYearManagerSysElement_ID = quality_CompanyYearManagerSysElement_ID;
    }

    public Integer getQuality_CompanyYearManagerSysElementTable_ID() {
        return quality_CompanyYearManagerSysElementTable_ID;
    }

    public void setQuality_CompanyYearManagerSysElementTable_ID(Integer quality_CompanyYearManagerSysElementTable_ID) {
        this.quality_CompanyYearManagerSysElementTable_ID = quality_CompanyYearManagerSysElementTable_ID;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuditMode() {
        return auditMode;
    }

    public void setAuditMode(String auditMode) {
        this.auditMode = auditMode;
    }

    public String getInitialScore() {
        return initialScore;
    }

    public void setInitialScore(String initialScore) {
        this.initialScore = initialScore;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getFileCheckStatus() {
        return fileCheckStatus;
    }

    public void setFileCheckStatus(String fileCheckStatus) {
        this.fileCheckStatus = fileCheckStatus;
    }

    public String getConfigStatus() {
        return configStatus;
    }

    public void setConfigStatus(String configStatus) {
        this.configStatus = configStatus;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public List<QualityManergerSysElementPojo> getChildNode() {
        return childNode;
    }

    public void setChildNode(List<QualityManergerSysElementPojo> childNode) {
        this.childNode = childNode;
    }
}
