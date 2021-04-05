package com.wlhse.dto.outDto;

import java.util.LinkedList;
import java.util.List;

public class QhseYearElementsOutDto {

    private Integer id;

    private Integer tableID;

    private String code;

    private String name;

    private String content;

    private String basis;

    private String auditMode;

    private Integer initialScore;

    private String formula;

    private String problemDescription;

    private Integer totalCount;

    private String status;

    private String companyCode;

    private String companyName;

    private String year;

    private String fileCheckStatus;
    private String  schedule;
    private List<QhseYearElementsOutDto> childNode = new LinkedList<>();
    private int checkStatus;
    private  int isInvolve;

    public QhseYearElementsOutDto() {
    }

    public QhseYearElementsOutDto(Integer id, Integer tableID, String code, String name, String content, String basis, String auditMode, Integer initialScore, String formula, String problemDescription, Integer totalCount, String status, String companyCode, String companyName, String year, String fileCheckStatus, String schedule, List<QhseYearElementsOutDto> childNode, int checkStatus, int isInvolve) {
        this.id = id;
        this.tableID = tableID;
        this.code = code;
        this.name = name;
        this.content = content;
        this.basis = basis;
        this.auditMode = auditMode;
        this.initialScore = initialScore;
        this.formula = formula;
        this.problemDescription = problemDescription;
        this.totalCount = totalCount;
        this.status = status;
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.year = year;
        this.fileCheckStatus = fileCheckStatus;
        this.schedule = schedule;
        this.childNode = childNode;
        this.checkStatus = checkStatus;
        this.isInvolve = isInvolve;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTableID() {
        return tableID;
    }

    public void setTableID(Integer tableID) {
        this.tableID = tableID;
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

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public List<QhseYearElementsOutDto> getChildNode() {
        return childNode;
    }

    public void setChildNode(List<QhseYearElementsOutDto> childNode) {
        this.childNode = childNode;
    }

    public int getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(int checkStatus) {
        this.checkStatus = checkStatus;
    }

    public int getIsInvolve() {
        return isInvolve;
    }

    public void setIsInvolve(int isInvolve) {
        this.isInvolve = isInvolve;
    }


    @Override
    public String toString() {
        return "QhseYearElementsOutDto{" +
                "id=" + id +
                ", tableID=" + tableID +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", basis='" + basis + '\'' +
                ", auditMode='" + auditMode + '\'' +
                ", initialScore=" + initialScore +
                ", formula='" + formula + '\'' +
                ", problemDescription='" + problemDescription + '\'' +
                ", totalCount=" + totalCount +
                ", status='" + status + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", year='" + year + '\'' +
                ", fileCheckStatus='" + fileCheckStatus + '\'' +
                ", schedule='" + schedule + '\'' +
                ", childNode=" + childNode +
                ", checkStatus=" + checkStatus +
                ", isInvolve=" + isInvolve +
                '}';
    }
}
