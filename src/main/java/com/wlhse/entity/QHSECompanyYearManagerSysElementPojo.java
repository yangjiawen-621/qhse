package com.wlhse.entity;


public class QHSECompanyYearManagerSysElementPojo {
    private Integer qHSECompanyYearManagerSysElementID;
    private Integer qHSECompanyYearManagerSysElementTableID;
    private String code;
    private String name;
    private String content;
    private String basis;
    private String auditMode;
    private Integer initialScore;
    private Integer totalCount;
    private String formula;
    private String problemDescription;
    private String status;
    private String companyCode;
    private String companyName;
    private String  year;

    public Integer getqHSECompanyYearManagerSysElementID() {
        return qHSECompanyYearManagerSysElementID;
    }

    public void setqHSECompanyYearManagerSysElementID(Integer qHSECompanyYearManagerSysElementID) {
        this.qHSECompanyYearManagerSysElementID = qHSECompanyYearManagerSysElementID;
    }

    public Integer getqHSECompanyYearManagerSysElementTableID() {
        return qHSECompanyYearManagerSysElementTableID;
    }

    public void setqHSECompanyYearManagerSysElementTableID(Integer qHSECompanyYearManagerSysElementTableID) {
        this.qHSECompanyYearManagerSysElementTableID = qHSECompanyYearManagerSysElementTableID;
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

    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
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

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
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

    @Override
    public String toString() {
        return "QHSECompanyYearManagerSysElementPojo{" +
                "qHSECompanyYearManagerSysElementID=" + qHSECompanyYearManagerSysElementID +
                ", qHSECompanyYearManagerSysElementTableID=" + qHSECompanyYearManagerSysElementTableID +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", basis='" + basis + '\'' +
                ", auditMode='" + auditMode + '\'' +
                ", initialScore=" + initialScore +
                ", totalCount=" + totalCount +
                ", formula='" + formula + '\'' +
                ", problemDescription='" + problemDescription + '\'' +
                ", status='" + status + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
