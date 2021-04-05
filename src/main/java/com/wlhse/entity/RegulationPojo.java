package com.wlhse.entity;

import com.wlhse.dto.getDto.BaseGetDto;
import org.springframework.stereotype.Component;

@Component
public class RegulationPojo extends BaseGetDto {
    private Integer id;
    private String typeCode;
    private String typeName;
    private String regNameCode;
    private String regName;
    private String documentSymbol;
    private String publishComCode;
    private String publishComName;
    private String beginDate;
    private String uploadDate;
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getRegNameCode() {
        return regNameCode;
    }

    public void setRegNameCode(String regNameCode) {
        this.regNameCode = regNameCode;
    }

    public String getRegName() {
        return regName;
    }

    public void setRegName(String regName) {
        this.regName = regName;
    }

    public String getDocumentSymbol() {
        return documentSymbol;
    }

    public void setDocumentSymbol(String documentSymbol) {
        this.documentSymbol = documentSymbol;
    }

    public String getPublishComCode() {
        return publishComCode;
    }

    public void setPublishComCode(String publishComCode) {
        this.publishComCode = publishComCode;
    }

    public String getPublishComName() {
        return publishComName;
    }

    public void setPublishComName(String publishComName) {
        this.publishComName = publishComName;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}