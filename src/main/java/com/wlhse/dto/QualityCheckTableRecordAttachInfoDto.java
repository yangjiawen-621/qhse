package com.wlhse.dto;

public class QualityCheckTableRecordAttachInfoDto {
    private Integer tableRecordAttachInfoID;
    private String attachFilePath;
    private String attachOriginName;

    public Integer getTableRecordAttachInfoID() {
        return tableRecordAttachInfoID;
    }

    public void setTableRecordAttachInfoID(Integer tableRecordAttachInfoID) {
        this.tableRecordAttachInfoID = tableRecordAttachInfoID;
    }

    public String getAttachFilePath() {
        return attachFilePath;
    }

    public void setAttachFilePath(String attachFilePath) {
        this.attachFilePath = attachFilePath;
    }

    public String getAttachOriginName() {
        return attachOriginName;
    }

    public void setAttachOriginName(String attachOriginName) {
        this.attachOriginName = attachOriginName;
    }

    @Override
    public String toString() {
        return "QualityCheckTableRecordAttachInfoDto{" +
                "tableRecordAttachInfoID=" + tableRecordAttachInfoID +
                ", attachFilePath='" + attachFilePath + '\'' +
                ", attachOriginName='" + attachOriginName + '\'' +
                '}';
    }
}
