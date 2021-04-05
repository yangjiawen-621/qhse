package com.wlhse.dto;

import com.wlhse.dto.getDto.BaseGetDto;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class ExcelUploadReportDto{
    private String reportID;//
    private String personID;//登录人ID，用于限制查询内容
    private String companyCode;
    private String count;//
    private String companyName;
    private String reportCode;
    private String reportType;
    private Integer reportCheckPersonID;
    private String reportCheckPersonName;
    private String reportPlanDate;
    private String beginTime;//
    private String endTime;//
    private List<ExcelUploadSampleDto> sampleDtoList=new LinkedList<>();
    private String auditorID;//
    private String  auditorIDs;
    private String auditorName;//
    private String auditorNames;
    private String auditorCategory;
    private String auditorDate;
    private Integer approverID;//
    private String approverIDs;
    private String approverName;//
    private String approverNames;
    private String approverCategory;
    private String approverDate;
    private String fileDate;
    private String senderID;
    private String senderName;
    private String sendDate;
    private String reportCount;
    private String seal1;
    private String seal2;
    private String seal3;
    private String seal4;
    private String seal5;
    private String seal6;
    private String note;
    private String sealPersonID;
    private String sealPersonName;
    private String authID;
    private String authName;
    private String sealDate;

    public String getAuditorIDs() {
        return auditorIDs;
    }

    public void setAuditorIDs(String auditorIDs) {
        this.auditorIDs = auditorIDs;
    }

    public String getApproverIDs() {
        return approverIDs;
    }

    public void setApproverIDs(String approverIDs) {
        this.approverIDs = approverIDs;
    }

    public String getAuditorNames() {
        return auditorNames;
    }

    public void setAuditorNames(String auditorNames) {
        this.auditorNames = auditorNames;
    }

    public String getApproverNames() {
        return approverNames;
    }

    public void setApproverNames(String approverNames) {
        this.approverNames = approverNames;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getAuditorDate() {
        return auditorDate;
    }

    public void setAuditorDate(String auditorDate) {
        this.auditorDate = auditorDate;
    }

    public String getApproverDate() {
        return approverDate;
    }

    public void setApproverDate(String approverDate) {
        this.approverDate = approverDate;
    }

    public ExcelUploadReportDto() {
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setSampleDtoList(List<ExcelUploadSampleDto> sampleDtoList) {
        this.sampleDtoList = sampleDtoList;
    }

    public List<ExcelUploadSampleDto> getSampleDtoList() {
        return sampleDtoList;
    }

    public void setReportID(String reportID) {
        this.reportID = reportID;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public void setReportCheckPersonID(Integer reportCheckPersonID) {
        this.reportCheckPersonID = reportCheckPersonID;
    }

    public void setReportCheckPersonName(String reportCheckPersonName) {
        this.reportCheckPersonName = reportCheckPersonName;
    }

    public void setReportPlanDate(String reportPlanDate) {
        this.reportPlanDate = reportPlanDate;
    }

    public void setAuditorID(String auditorID) {
        this.auditorID = auditorID;
    }

    public void setAuditorName(String  auditorName) {
        this.auditorName = auditorName;
    }

    public void setApproverID(Integer approverID) {
        this.approverID = approverID;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }

    public void setFileDate(String fileDate) {
        this.fileDate = fileDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public void setSealDate(String sealDate) {
        this.sealDate = sealDate;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }



    public void setReportCount(String reportCount) {
        this.reportCount = reportCount;
    }

    public void setSeal1(String seal1) {
        this.seal1 = seal1;
    }

    public void setSeal2(String seal2) {
        this.seal2 = seal2;
    }

    public void setSeal3(String seal3) {
        this.seal3 = seal3;
    }

    public void setSeal4(String seal4) {
        this.seal4 = seal4;
    }

    public void setSeal5(String seal5) {
        this.seal5 = seal5;
    }

    public void setSeal6(String seal6) {
        this.seal6 = seal6;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setSealPersonID(String sealPersonID) {
        this.sealPersonID = sealPersonID;
    }

    public void setSealPersonName(String sealPersonName) {
        this.sealPersonName = sealPersonName;
    }

    public void setAuthID(String authID) {
        this.authID = authID;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }



    public String getReportID() {
        return reportID;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getReportCode() {
        return reportCode;
    }

    public String getReportType() {
        return reportType;
    }

    public Integer getReportCheckPersonID() {
        return reportCheckPersonID;
    }

    public String getReportCheckPersonName() {
        return reportCheckPersonName;
    }

    public String getReportPlanDate() {
        return reportPlanDate;
    }

    public String getAuditorID() {
        return auditorID;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public Integer getApproverID() {
        return approverID;
    }

    public String getApproverName() {
        return approverName;
    }



    public String getSenderID() {
        return senderID;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getFileDate() {
        return fileDate;
    }

    public String getSendDate() {
        return sendDate;
    }

    public String getSealDate() {
        return sealDate;
    }

    public String getReportCount() {
        return reportCount;
    }

    public String getSeal1() {
        return seal1;
    }

    public String getSeal2() {
        return seal2;
    }

    public String getSeal3() {
        return seal3;
    }

    public String getSeal4() {
        return seal4;
    }

    public String getSeal5() {
        return seal5;
    }

    public String getSeal6() {
        return seal6;
    }

    public String getNote() {
        return note;
    }

    public String getSealPersonID() {
        return sealPersonID;
    }

    public String getSealPersonName() {
        return sealPersonName;
    }

    public String getAuthID() {
        return authID;
    }

    public String getAuthName() {
        return authName;
    }

    public String getAuditorCategory() {
        return auditorCategory;
    }

    public void setAuditorCategory(String auditorCategory) {
        this.auditorCategory = auditorCategory;
    }

    public String getApproverCategory() {
        return approverCategory;
    }

    public void setApproverCategory(String approverCategory) {
        this.approverCategory = approverCategory;
    }
}
