package com.wlhse.dto.inDto;




public class ElementReviewDto {
    private Integer qHSE_CompanyYearManagerSysElement_ID;
    private String code;
    private String companyName;
    private String companyCode;
    private String year;
    private String status;
    private Integer checkStaffID;
    private Integer approverStaffID;
    private String negativeOpinion;//不通过意见
    private int isInvolve;

    public ElementReviewDto(Integer qHSE_CompanyYearManagerSysElement_ID, String code, String companyName, String companyCode, String year, String status, Integer checkStaffID, Integer approverStaffID, String negativeOpinion, int isInvolve) {
        this.qHSE_CompanyYearManagerSysElement_ID = qHSE_CompanyYearManagerSysElement_ID;
        this.code = code;
        this.companyName = companyName;
        this.companyCode = companyCode;
        this.year = year;
        this.status = status;
        this.checkStaffID = checkStaffID;
        this.approverStaffID = approverStaffID;
        this.negativeOpinion = negativeOpinion;
        this.isInvolve = isInvolve;
    }

    public ElementReviewDto() {
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCheckStaffID() {
        return checkStaffID;
    }

    public void setCheckStaffID(Integer checkStaffID) {
        this.checkStaffID = checkStaffID;
    }

    public Integer getApproverStaffID() {
        return approverStaffID;
    }

    public void setApproverStaffID(Integer approverStaffID) {
        this.approverStaffID = approverStaffID;
    }

    public String getNegativeOpinion() {
        return negativeOpinion;
    }

    public void setNegativeOpinion(String negativeOpinion) {
        this.negativeOpinion = negativeOpinion;
    }

    public int getIsInvolve() {
        return isInvolve;
    }

    public void setIsInvolve(int isInvolve) {
        this.isInvolve = isInvolve;
    }

    @Override
    public String toString() {
        return "ElementReviewDto{" +
                "qHSE_CompanyYearManagerSysElement_ID=" + qHSE_CompanyYearManagerSysElement_ID +
                ", code='" + code + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", year='" + year + '\'' +
                ", status='" + status + '\'' +
                ", checkStaffID=" + checkStaffID +
                ", approverStaffID=" + approverStaffID +
                ", negativeOpinion='" + negativeOpinion + '\'' +
                ", isInvolve=" + isInvolve +
                '}';
    }
}
