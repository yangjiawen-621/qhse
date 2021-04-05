package com.wlhse.dto;

public class QualityCheckListDto {
    private Integer checkListID;
    private String checkListName;
    private String checkListCode;
    private String attribute;
    private String parentName;
    private String isChildNode;
    private String status;
    private String checkCategory;
    private String checkBasis;
    private String checkMethod;
    private Integer targetScore;
    private String scoreShows;
    private String standard;
    private String standardNo;
    private String standardContent;

    public Integer getCheckListID() {
        return checkListID;
    }

    public void setCheckListID(Integer checkListID) {
        this.checkListID = checkListID;
    }

    public String getCheckListName() {
        return checkListName;
    }

    public void setCheckListName(String checkListName) {
        this.checkListName = checkListName;
    }

    public String getCheckListCode() {
        return checkListCode;
    }

    public void setCheckListCode(String checkListCode) {
        this.checkListCode = checkListCode;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getIsChildNode() {
        return isChildNode;
    }

    public void setIsChildNode(String isChildNode) {
        this.isChildNode = isChildNode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCheckCategory() {
        return checkCategory;
    }

    public void setCheckCategory(String checkCategory) {
        this.checkCategory = checkCategory;
    }

    public String getCheckBasis() {
        return checkBasis;
    }

    public void setCheckBasis(String checkBasis) {
        this.checkBasis = checkBasis;
    }

    public String getCheckMethod() {
        return checkMethod;
    }

    public void setCheckMethod(String checkMethod) {
        this.checkMethod = checkMethod;
    }

    public Integer getTargetScore() {
        return targetScore;
    }

    public void setTargetScore(Integer targetScore) {
        this.targetScore = targetScore;
    }

    public String getScoreShows() {
        return scoreShows;
    }

    public void setScoreShows(String scoreShows) {
        this.scoreShows = scoreShows;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getStandardNo() {
        return standardNo;
    }

    public void setStandardNo(String standardNo) {
        this.standardNo = standardNo;
    }

    public String getStandardContent() {
        return standardContent;
    }

    public void setStandardContent(String standardContent) {
        this.standardContent = standardContent;
    }

    @Override
    public String toString() {
        return "QualityCheckListDto{" +
                "checkListID=" + checkListID +
                ", checkListName='" + checkListName + '\'' +
                ", checkListCode='" + checkListCode + '\'' +
                ", attribute='" + attribute + '\'' +
                ", parentName='" + parentName + '\'' +
                ", isChildNode='" + isChildNode + '\'' +
                ", status='" + status + '\'' +
                ", checkCategory='" + checkCategory + '\'' +
                ", checkBasis='" + checkBasis + '\'' +
                ", checkMethod='" + checkMethod + '\'' +
                ", targetScore=" + targetScore +
                ", scoreShows='" + scoreShows + '\'' +
                ", standard='" + standard + '\'' +
                ", standardNo='" + standardNo + '\'' +
                ", standardContent='" + standardContent + '\'' +
                '}';
    }
}
