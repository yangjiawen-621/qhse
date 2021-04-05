package com.wlhse.dto.outDto;

import java.util.LinkedList;
import java.util.List;

public class QhseElementsOutDto {

    private Integer id;

    private String code;

    private String name;

    private String content;

    private String auditMode;

    private Integer initialScore;

    private String formula;

    private String problemDescription;

    private Integer totalCount;

    private String status;

    private List<QhseElementsOutDto> childNode = new LinkedList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<QhseElementsOutDto> getChildNode() {
        return childNode;
    }

    public void setChildNode(List<QhseElementsOutDto> childNode) {
        this.childNode = childNode;
    }


    @Override
    public String toString() {
        return "QhseElementsOutDto{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", auditMode='" + auditMode + '\'' +
                ", initialScore=" + initialScore +
                ", formula='" + formula + '\'' +
                ", problemDescription='" + problemDescription + '\'' +
                ", totalCount=" + totalCount +
                ", status='" + status + '\'' +
                ", childNode=" + childNode +
                '}';
    }
}
