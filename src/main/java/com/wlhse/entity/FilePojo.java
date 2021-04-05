package com.wlhse.entity;

import org.springframework.stereotype.Component;


@Component
public class FilePojo {
    private Integer fileID;
    private String fileName;
    private String fileType;
    private String uploadDate;
    private String uploadPerson;
    private String appliComCode;
    private String summary;
    private String appliCom;

    private Integer uploadPersonId;

    public String getFileNameCode() {
        return fileNameCode;
    }

    public void setFileNameCode(String fileNameCode) {
        this.fileNameCode = fileNameCode;
    }

    private String fileNameCode;

    public Integer getUploadPersonId() {
        return uploadPersonId;
    }

    public void setUploadPersonId(Integer uploadPersonId) {
        this.uploadPersonId = uploadPersonId;
    }

    public Integer getFileID() {
        return fileID;
    }

    public void setFileID(Integer fileID) {
        this.fileID = fileID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getUploadPerson() {
        return uploadPerson;
    }

    public void setUploadPerson(String uploadPerson) {
        this.uploadPerson = uploadPerson;
    }

    public String getAppliComCode() {
        return appliComCode;
    }

    public void setAppliComCode(String appliComCode) {
        this.appliComCode = appliComCode;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAppliCom() {
        return appliCom;
    }

    public void setAppliCom(String appliCom) {
        this.appliCom = appliCom;
    }
}
