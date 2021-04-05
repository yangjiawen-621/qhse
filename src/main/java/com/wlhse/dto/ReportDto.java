package com.wlhse.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.wlhse.dto.getDto.BaseGetDto;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class ReportDto extends BaseGetDto {
    private Integer reportID;
    private Integer personID;//登录人ID，用于限制查询内容
    private String companyCode;
    private Integer count;
    private Integer a;//编号区间开始
    private Integer b;//编号区间结束
    private Integer state;//判断查询类型
    private Integer start;//分页其实位置
    private String companyName;
    private String reportCode;
    private String reportType;
    private Integer reportCheckPersonID;
    private String reportCheckPersonName;
    private String reportPlanDate;
    private String beginTime;
    private String endTime;
    private List<SampleDto> sampleDtoList=new LinkedList<>();
    private Integer auditorID;
    private String  auditorIDs;
    private String auditorName;
    private String auditorNames;
    private String auditorCategory;
    private String auditorDate;
    private Integer approverID;
    private String approverIDs;
    private String approverName;
    private String approverNames;
    private String approverCategory;
    private String approverDate;
    private String fileDate;
    private Integer senderID;
    private String senderName;
    private String sendDate;
    private Integer reportCount;
    private Integer seal1;
    private Integer seal2;
    private Integer seal3;
    private Integer seal4;
    private Integer seal5;
    private Integer seal6;
    private String note;
    private Integer sealPersonID;
    private String sealPersonName;
    private Integer authID;
    private String authName;
    private String sealDate;

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPersonID() {
        return personID;
    }

    public void setPersonID(Integer personID) {
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

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public ReportDto() {
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public void setSampleDtoList(List<SampleDto> sampleDtoList) {
        this.sampleDtoList = sampleDtoList;
    }

    public List<SampleDto> getSampleDtoList() {
        return sampleDtoList;
    }

    public void setReportID(Integer reportID) {
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

    public void setAuditorID(Integer auditorID) {
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

    public void setSenderID(Integer senderID) {
        this.senderID = senderID;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }



    public void setReportCount(Integer reportCount) {
        this.reportCount = reportCount;
    }

    public void setSeal1(Integer seal1) {
        this.seal1 = seal1;
    }

    public void setSeal2(Integer seal2) {
        this.seal2 = seal2;
    }

    public void setSeal3(Integer seal3) {
        this.seal3 = seal3;
    }

    public void setSeal4(Integer seal4) {
        this.seal4 = seal4;
    }

    public void setSeal5(Integer seal5) {
        this.seal5 = seal5;
    }

    public void setSeal6(Integer seal6) {
        this.seal6 = seal6;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setSealPersonID(Integer sealPersonID) {
        this.sealPersonID = sealPersonID;
    }

    public void setSealPersonName(String sealPersonName) {
        this.sealPersonName = sealPersonName;
    }

    public void setAuthID(Integer authID) {
        this.authID = authID;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }



    public Integer getReportID() {
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

    public Integer getAuditorID() {
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



    public Integer getSenderID() {
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

    public Integer getReportCount() {
        return reportCount;
    }

    public Integer getSeal1() {
        return seal1;
    }

    public Integer getSeal2() {
        return seal2;
    }

    public Integer getSeal3() {
        return seal3;
    }

    public Integer getSeal4() {
        return seal4;
    }

    public Integer getSeal5() {
        return seal5;
    }

    public Integer getSeal6() {
        return seal6;
    }

    public String getNote() {
        return note;
    }

    public Integer getSealPersonID() {
        return sealPersonID;
    }

    public String getSealPersonName() {
        return sealPersonName;
    }

    public Integer getAuthID() {
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
