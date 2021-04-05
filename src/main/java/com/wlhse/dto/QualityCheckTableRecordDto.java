package com.wlhse.dto;

public class QualityCheckTableRecordDto {
    private Integer qualityCheckTableRecordID;
    private Integer qualityCheckID;
    private String qualityCheckName;
    private String checkListCode;
    private String description;
    private String checkResult;
    private String attach;
    private String pic;
    private Integer score;

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getQualityCheckTableRecordID() {
        return qualityCheckTableRecordID;
    }

    public void setQualityCheckTableRecordID(Integer qualityCheckTableRecordID) {
        this.qualityCheckTableRecordID = qualityCheckTableRecordID;
    }

    public Integer getQualityCheckID() {
        return qualityCheckID;
    }

    public void setQualityCheckID(Integer qualityCheckID) {
        this.qualityCheckID = qualityCheckID;
    }

    public String getQualityCheckName() {
        return qualityCheckName;
    }

    public void setQualityCheckName(String qualityCheckName) {
        this.qualityCheckName = qualityCheckName;
    }

    public String getCheckListCode() {
        return checkListCode;
    }

    public void setCheckListCode(String checkListCode) {
        this.checkListCode = checkListCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "QualityCheckTableRecordDto{" +
                "qualityCheckTableRecordID=" + qualityCheckTableRecordID +
                ", qualityCheckID=" + qualityCheckID +
                ", qualityCheckName='" + qualityCheckName + '\'' +
                ", checkListCode='" + checkListCode + '\'' +
                ", description='" + description + '\'' +
                ", checkResult='" + checkResult + '\'' +
                ", attach='" + attach + '\'' +
                ", pic='" + pic + '\'' +
                ", score=" + score +
                ", url='" + url + '\'' +
                '}';
    }
}
