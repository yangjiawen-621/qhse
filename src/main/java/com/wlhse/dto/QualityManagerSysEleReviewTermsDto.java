package com.wlhse.dto;

public class QualityManagerSysEleReviewTermsDto {
    private Integer quality_ManagerSysEleReviewTerms_ID;

    private String code;

    private String basis;

    private String terms;

    private String content;

    public Integer getQuality_ManagerSysEleReviewTerms_ID() {
        return quality_ManagerSysEleReviewTerms_ID;
    }

    public void setQuality_ManagerSysEleReviewTerms_ID(Integer quality_ManagerSysEleReviewTerms_ID) {
        this.quality_ManagerSysEleReviewTerms_ID = quality_ManagerSysEleReviewTerms_ID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "QualityManagerSysEleReviewTermsDto{" +
                "quality_ManagerSysEleReviewTerms_ID=" + quality_ManagerSysEleReviewTerms_ID +
                ", code='" + code + '\'' +
                ", basis='" + basis + '\'' +
                ", terms='" + terms + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
