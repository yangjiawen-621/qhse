package com.wlhse.dto.inDto;

import com.wlhse.dto.getDto.BaseGetDto;
import org.springframework.stereotype.Component;

@Component
public class FileInDto extends BaseGetDto {
    private String fileName;
    private String fileType;
    private String startDate;
    private String endDate;
    private String uploadPerson;
    private String appliComCode;

    private String fileNameCode;
    private String url;

    private Integer uploadPersonId;

    public Integer getUploadPersonId() {
        return uploadPersonId;
    }

    public void setUploadPersonId(Integer uploadPersonId) {
        this.uploadPersonId = uploadPersonId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileNameCode() {
        return fileNameCode;
    }

    public void setFileNameCode(String fileNameCode) {
        this.fileNameCode = fileNameCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

}
