package com.wlhse.dto.outDto;

import java.util.Date;

public class QhseEvidenceAttatchDto {
    private Integer  qHSE_CompanyYearManagerSysElementEvidence_ID;
    private Integer  qHSE_CompanyYearManagerSysElement_ID;
    private String   code;
    private String   evidenceDescription;
    private Integer  checkStaffID;
    private String   checkStaffName;
    private Integer  approverStaffID;
    private String   approverStaffName;
    private Integer  qHSE_CompanyYearManagerSysElementEvidenceAttach_ID;
    private String   attachDescrption;
    private String   attach;
    private String   uploadTime;
    private String   url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getqHSE_CompanyYearManagerSysElementEvidence_ID() {
        return qHSE_CompanyYearManagerSysElementEvidence_ID;
    }

    public void setqHSE_CompanyYearManagerSysElementEvidence_ID(Integer qHSE_CompanyYearManagerSysElementEvidence_ID) {
        this.qHSE_CompanyYearManagerSysElementEvidence_ID = qHSE_CompanyYearManagerSysElementEvidence_ID;
    }

    public Integer getqHSE_CompanyYearManagerSysElement_ID() {
        return qHSE_CompanyYearManagerSysElement_ID;
    }

    public void setqHSE_CompanyYearManagerSysElement_ID(Integer qHSE_CompanyYearManagerSysElement_ID) {
        this.qHSE_CompanyYearManagerSysElement_ID = qHSE_CompanyYearManagerSysElement_ID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEvidenceDescription() {
        return evidenceDescription;
    }

    public void setEvidenceDescription(String evidenceDescription) {
        this.evidenceDescription = evidenceDescription;
    }

    public Integer getCheckStaffID() {
        return checkStaffID;
    }

    public void setCheckStaffID(Integer checkStaffID) {
        this.checkStaffID = checkStaffID;
    }

    public String getCheckStaffName() {
        return checkStaffName;
    }

    public void setCheckStaffName(String checkStaffName) {
        this.checkStaffName = checkStaffName;
    }

    public Integer getApproverStaffID() {
        return approverStaffID;
    }

    public void setApproverStaffID(Integer approverStaffID) {
        this.approverStaffID = approverStaffID;
    }

    public String getApproverStaffName() {
        return approverStaffName;
    }

    public void setApproverStaffName(String approverStaffName) {
        this.approverStaffName = approverStaffName;
    }

    public Integer getqHSE_CompanyYearManagerSysElementEvidenceAttach_ID() {
        return qHSE_CompanyYearManagerSysElementEvidenceAttach_ID;
    }

    public void setqHSE_CompanyYearManagerSysElementEvidenceAttach_ID(Integer qHSE_CompanyYearManagerSysElementEvidenceAttach_ID) {
        this.qHSE_CompanyYearManagerSysElementEvidenceAttach_ID = qHSE_CompanyYearManagerSysElementEvidenceAttach_ID;
    }

    public String getAttachDescrption() {
        return attachDescrption;
    }

    public void setAttachDescrption(String attachDescrption) {
        this.attachDescrption = attachDescrption;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

//    public void setUploadTime(Date uploadTime) {
//        this.uploadTime = uploadTime;
//    }
//
//    public Date getUploadTime() {
//        return uploadTime;
//    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Override
    public String toString() {
        return "QhseEvidenceAttatchDto{" +
                "qHSE_CompanyYearManagerSysElementEvidence_ID=" + qHSE_CompanyYearManagerSysElementEvidence_ID +
                ", qHSE_CompanyYearManagerSysElement_ID=" + qHSE_CompanyYearManagerSysElement_ID +
                ", code='" + code + '\'' +
                ", evidenceDescription='" + evidenceDescription + '\'' +
                ", checkStaffID=" + checkStaffID +
                ", checkStaffName='" + checkStaffName + '\'' +
                ", approverStaffID=" + approverStaffID +
                ", approverStaffName='" + approverStaffName + '\'' +
                ", qHSE_CompanyYearManagerSysElementEvidenceAttach_ID=" + qHSE_CompanyYearManagerSysElementEvidenceAttach_ID +
                ", attachDescrption='" + attachDescrption + '\'' +
                ", attach='" + attach + '\'' +
                ", uploadTime=" + uploadTime +
                '}';
    }
}
