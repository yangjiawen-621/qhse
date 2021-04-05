package com.wlhse.entity;

import com.wlhse.dto.getDto.BaseGetDto;
import org.springframework.stereotype.Component;

@Component
public class EventRecordPojo extends BaseGetDto {
    private Integer id; //事件标识
    private String companyCode; //事件单位ID
    private String companyName; //事件单位名称
    private String eventRecordName; //事件名称
    private String eventRecordTypeCode; //事件类别编码
    private String eventRecordType; //事件类别
    private String address; //地点
    private String checkDate; //发生时间
    private String description; //事件描述
    private String attach1; //附件图片1
    private String attach2; //附件图片2
    private String attach3; //附件图片3
    private String attach4; //附件图片4
    private String attach; //文档附件

    private String startDate;
    private String endDate;

    private String hash;

    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEventRecordName() {
        return eventRecordName;
    }

    public void setEventRecordName(String eventRecordName) {
        this.eventRecordName = eventRecordName;
    }

    public String getEventRecordTypeCode() {
        return eventRecordTypeCode;
    }

    public void setEventRecordTypeCode(String eventRecordTypeCode) {
        this.eventRecordTypeCode = eventRecordTypeCode;
    }

    public String getEventRecordType() {
        return eventRecordType;
    }

    public void setEventRecordType(String eventRecordType) {
        this.eventRecordType = eventRecordType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttach1() {
        return attach1;
    }

    public void setAttach1(String attach1) {
        this.attach1 = attach1;
    }

    public String getAttach2() {
        return attach2;
    }

    public void setAttach2(String attach2) {
        this.attach2 = attach2;
    }

    public String getAttach3() {
        return attach3;
    }

    public void setAttach3(String attach3) {
        this.attach3 = attach3;
    }

    public String getAttach4() {
        return attach4;
    }

    public void setAttach4(String attach4) {
        this.attach4 = attach4;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

}
