package com.wlhse.entity;

/**
 * Author:melon
 * Origin:2020/10/5
 **/
public class QualityInputAttachPojo {
    private Integer quality_CompanyYearManagerSysElementEvidence_ID;
    private Integer   quality_CompanyYearManagerSysElement_ID;
    private String code;
    private String  evidenceDescription;
    private Integer checkStaffID;
    private String  checkStaffName;
    private Integer approverStaffID;
    private String   approverStaffName;
    private String negativeOpinion;
    private String  attachDescrption;
    private String attach;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getQuality_CompanyYearManagerSysElementEvidence_ID() {
        return quality_CompanyYearManagerSysElementEvidence_ID;
    }

    public void setQuality_CompanyYearManagerSysElementEvidence_ID(Integer quality_CompanyYearManagerSysElementEvidence_ID) {
        this.quality_CompanyYearManagerSysElementEvidence_ID = quality_CompanyYearManagerSysElementEvidence_ID;
    }

    public Integer getQuality_CompanyYearManagerSysElement_ID() {
        return quality_CompanyYearManagerSysElement_ID;
    }

    public void setQuality_CompanyYearManagerSysElement_ID(Integer quality_CompanyYearManagerSysElement_ID) {
        this.quality_CompanyYearManagerSysElement_ID = quality_CompanyYearManagerSysElement_ID;
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

    public String getNegativeOpinion() {
        return negativeOpinion;
    }

    public void setNegativeOpinion(String negativeOpinion) {
        this.negativeOpinion = negativeOpinion;
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
}
