package com.wlhse.dto.inDto;

public class YearElementsDto {
    private Integer qhseCompanyYearManagerSysElementID;//主键，自增id
    private String codes;//选择的所有二级节点
    private Integer qhseCompanyYearManagerSysElementTableID;//tableid
    private String code;
    private String name;
    private String content;
    private String auditMode;
    private Integer initialScore;
    private String formula;
    private Integer totalCount;
    private String status;
    private String companyCode;
    private String companyName;
    private String year;
    private String fileCheckStatus;
    private String configStatus;
    private String  schedule;
    private Integer checkStatus;
    private int isInvolve;

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public YearElementsDto() {
    }

    public YearElementsDto(Integer qhseCompanyYearManagerSysElementID, String codes, Integer qhseCompanyYearManagerSysElementTableID, String code, String name, String content, String auditMode, Integer initialScore, String formula, Integer totalCount, String status, String companyCode, String companyName, String year, String fileCheckStatus, String configStatus, String schedule, Integer checkStatus, int isInvolve) {
        this.qhseCompanyYearManagerSysElementID = qhseCompanyYearManagerSysElementID;
        this.codes = codes;
        this.qhseCompanyYearManagerSysElementTableID = qhseCompanyYearManagerSysElementTableID;
        this.code = code;
        this.name = name;
        this.content = content;
        this.auditMode = auditMode;
        this.initialScore = initialScore;
        this.formula = formula;
        this.totalCount = totalCount;
        this.status = status;
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.year = year;
        this.fileCheckStatus = fileCheckStatus;
        this.configStatus = configStatus;
        this.schedule = schedule;
        this.checkStatus = checkStatus;
        this.isInvolve = isInvolve;
    }


    public Integer getQhseCompanyYearManagerSysElementID() {
        return qhseCompanyYearManagerSysElementID;
    }

    public void setQhseCompanyYearManagerSysElementID(Integer qhseCompanyYearManagerSysElementID) {
        this.qhseCompanyYearManagerSysElementID = qhseCompanyYearManagerSysElementID;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public Integer getQhseCompanyYearManagerSysElementTableID() {
        return qhseCompanyYearManagerSysElementTableID;
    }

    public void setQhseCompanyYearManagerSysElementTableID(Integer qhseCompanyYearManagerSysElementTableID) {
        this.qhseCompanyYearManagerSysElementTableID = qhseCompanyYearManagerSysElementTableID;
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

    public Integer getInitialScore() {
        return initialScore;
    }

    public void setInitialScore(Integer initialScore) {
        this.initialScore = initialScore;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
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

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public int getIsInvolve() {
        return isInvolve;
    }

    public void setIsInvolve(int isInvolve) {
        this.isInvolve = isInvolve;
    }

    @Override
    public String toString() {
        return "YearElementsDto{" +
                "qhseCompanyYearManagerSysElementID=" + qhseCompanyYearManagerSysElementID +
                ", codes='" + codes + '\'' +
                ", qhseCompanyYearManagerSysElementTableID=" + qhseCompanyYearManagerSysElementTableID +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", auditMode='" + auditMode + '\'' +
                ", initialScore=" + initialScore +
                ", formula='" + formula + '\'' +
                ", totalCount=" + totalCount +
                ", status='" + status + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", year='" + year + '\'' +
                ", fileCheckStatus='" + fileCheckStatus + '\'' +
                ", configStatus='" + configStatus + '\'' +
                ", schedule='" + schedule + '\'' +
                ", checkStatus=" + checkStatus +
                ", isInvolve=" + isInvolve +
                '}';
    }
}
