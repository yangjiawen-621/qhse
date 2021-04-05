package com.wlhse.dto;

public class QualityAuditRecord {
    private Integer id;
    private Integer qualityCheckTableRecordID;
    private String reviewerName;
    private String reviewerTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQualityCheckTableRecordID() {
        return qualityCheckTableRecordID;
    }

    public void setQualityCheckTableRecordID(Integer qualityCheckTableRecordID) {
        this.qualityCheckTableRecordID = qualityCheckTableRecordID;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getReviewerTime() {
        return reviewerTime;
    }

    public void setReviewerTime(String reviewerTime) {
        this.reviewerTime = reviewerTime;
    }
}
