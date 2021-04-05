package com.wlhse.entity;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * tobing
 */
@Component
public class QulityCheckRecordPojo {
    private Integer qulity_CheckRecordID;
    private Integer qulity_CheckID;
    private String checkListCode;
    private String no;
    private String description;
    private String nature;
    private String nonConformityType;
    private String nonConformityNature;
    private String nonConformityStd;
    private String nonConformClauseNo;
    private String nonConformClauseContent;
    private String nonConformSource;
    private String nonConformCorrect;
    private String nonConformCorrectMeasure;
    private String nonConformCorrectMeasureVerify;
    private String punishmentBasis;
    private String violationClause;
    private String violationClauseContent;
    private Float violationDeduction;
    private Float violationScore;
    private String illegalPerson;
    private Integer illegalPersonID;
    private String post;
    private String postType;
    private String employmentProperty;
    private String workingYears;
    private String education;
    private String reformDate;
    private String reformLimit;
    private String isPush;
    private String problemAttach;
    private String problemPic;
    private String correctAttach;
    private String correctPic;
    // new add
    private String nonConformClause;
    private String nonConformityStdNo;
    private String nonConformityStdContent;
    private Integer resVerifierID;
    private String resVerifierName;
    private String resVerifyDate;
    private String resVerifyAdvice;
    private Integer cheVerifierID;
    private String cheVerifierName;
    private String cheVerifyDate;
    private String cheVerifyAdvice;
    private String responsiCompanyName;
    private String responsiCompanyCode;
    private Integer responsePersonID;
    private String responsePersonName;


    public Integer getQulity_CheckRecordID() {
        return qulity_CheckRecordID;
    }

    public void setQulity_CheckRecordID(Integer qulity_CheckRecordID) {
        this.qulity_CheckRecordID = qulity_CheckRecordID;
    }

    public Integer getQulity_CheckID() {
        return qulity_CheckID;
    }

    public void setQulity_CheckID(Integer qulity_CheckID) {
        this.qulity_CheckID = qulity_CheckID;
    }

    public String getCheckListCode() {
        return checkListCode;
    }

    public void setCheckListCode(String checkListCode) {
        this.checkListCode = checkListCode;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getNonConformityType() {
        return nonConformityType;
    }

    public void setNonConformityType(String nonConformityType) {
        this.nonConformityType = nonConformityType;
    }

    public String getNonConformityNature() {
        return nonConformityNature;
    }

    public void setNonConformityNature(String nonConformityNature) {
        this.nonConformityNature = nonConformityNature;
    }

    public String getNonConformityStd() {
        return nonConformityStd;
    }

    public void setNonConformityStd(String nonConformityStd) {
        this.nonConformityStd = nonConformityStd;
    }

    public String getNonConformClauseNo() {
        return nonConformClauseNo;
    }

    public void setNonConformClauseNo(String nonConformClauseNo) {
        this.nonConformClauseNo = nonConformClauseNo;
    }

    public String getNonConformClauseContent() {
        return nonConformClauseContent;
    }

    public void setNonConformClauseContent(String nonConformClauseContent) {
        this.nonConformClauseContent = nonConformClauseContent;
    }

    public String getNonConformSource() {
        return nonConformSource;
    }

    public void setNonConformSource(String nonConformSource) {
        this.nonConformSource = nonConformSource;
    }

    public String getNonConformCorrect() {
        return nonConformCorrect;
    }

    public void setNonConformCorrect(String nonConformCorrect) {
        this.nonConformCorrect = nonConformCorrect;
    }

    public String getNonConformCorrectMeasure() {
        return nonConformCorrectMeasure;
    }

    public void setNonConformCorrectMeasure(String nonConformCorrectMeasure) {
        this.nonConformCorrectMeasure = nonConformCorrectMeasure;
    }

    public String getNonConformCorrectMeasureVerify() {
        return nonConformCorrectMeasureVerify;
    }

    public void setNonConformCorrectMeasureVerify(String nonConformCorrectMeasureVerify) {
        this.nonConformCorrectMeasureVerify = nonConformCorrectMeasureVerify;
    }

    public String getPunishmentBasis() {
        return punishmentBasis;
    }

    public void setPunishmentBasis(String punishmentBasis) {
        this.punishmentBasis = punishmentBasis;
    }

    public String getViolationClause() {
        return violationClause;
    }

    public void setViolationClause(String violationClause) {
        this.violationClause = violationClause;
    }

    public String getViolationClauseContent() {
        return violationClauseContent;
    }

    public void setViolationClauseContent(String violationClauseContent) {
        this.violationClauseContent = violationClauseContent;
    }

    public Float getViolationDeduction() {
        return violationDeduction;
    }

    public void setViolationDeduction(Float violationDeduction) {
        this.violationDeduction = violationDeduction;
    }

    public Float getViolationScore() {
        return violationScore;
    }

    public void setViolationScore(Float violationScore) {
        this.violationScore = violationScore;
    }

    public String getIllegalPerson() {
        return illegalPerson;
    }

    public void setIllegalPerson(String illegalPerson) {
        this.illegalPerson = illegalPerson;
    }

    public Integer getIllegalPersonID() {
        return illegalPersonID;
    }

    public void setIllegalPersonID(Integer illegalPersonID) {
        this.illegalPersonID = illegalPersonID;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getEmploymentProperty() {
        return employmentProperty;
    }

    public void setEmploymentProperty(String employmentProperty) {
        this.employmentProperty = employmentProperty;
    }

    public String getWorkingYears() {
        return workingYears;
    }

    public void setWorkingYears(String workingYears) {
        this.workingYears = workingYears;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getReformDate() {
        return reformDate;
    }

    public void setReformDate(String reformDate) {
        this.reformDate = reformDate;
    }

    public String getReformLimit() {
        return reformLimit;
    }

    public void setReformLimit(String reformLimit) {
        this.reformLimit = reformLimit;
    }

    public String getIsPush() {
        return isPush;
    }

    public void setIsPush(String isPush) {
        this.isPush = isPush;
    }

    public String getProblemAttach() {
        return problemAttach;
    }

    public void setProblemAttach(String problemAttach) {
        this.problemAttach = problemAttach;
    }

    public String getProblemPic() {
        return problemPic;
    }

    public void setProblemPic(String problemPic) {
        this.problemPic = problemPic;
    }

    public String getCorrectAttach() {
        return correctAttach;
    }

    public void setCorrectAttach(String correctAttach) {
        this.correctAttach = correctAttach;
    }

    public String getCorrectPic() {
        return correctPic;
    }

    public void setCorrectPic(String correctPic) {
        this.correctPic = correctPic;
    }

    public String getNonConformClause() {
        return nonConformClause;
    }

    public void setNonConformClause(String nonConformClause) {
        this.nonConformClause = nonConformClause;
    }

    public String getNonConformityStdNo() {
        return nonConformityStdNo;
    }

    public void setNonConformityStdNo(String nonConformityStdNo) {
        this.nonConformityStdNo = nonConformityStdNo;
    }

    public String getNonConformityStdContent() {
        return nonConformityStdContent;
    }

    public void setNonConformityStdContent(String nonConformityStdContent) {
        this.nonConformityStdContent = nonConformityStdContent;
    }

    public Integer getResVerifierID() {
        return resVerifierID;
    }

    public void setResVerifierID(Integer resVerifierID) {
        this.resVerifierID = resVerifierID;
    }

    public String getResVerifierName() {
        return resVerifierName;
    }

    public void setResVerifierName(String resVerifierName) {
        this.resVerifierName = resVerifierName;
    }

    public String getResVerifyDate() {
        return resVerifyDate;
    }

    public void setResVerifyDate(String resVerifyDate) {
        this.resVerifyDate = resVerifyDate;
    }

    public String getResVerifyAdvice() {
        return resVerifyAdvice;
    }

    public void setResVerifyAdvice(String resVerifyAdvice) {
        this.resVerifyAdvice = resVerifyAdvice;
    }

    public Integer getCheVerifierID() {
        return cheVerifierID;
    }

    public void setCheVerifierID(Integer cheVerifierID) {
        this.cheVerifierID = cheVerifierID;
    }

    public String getCheVerifierName() {
        return cheVerifierName;
    }

    public void setCheVerifierName(String cheVerifierName) {
        this.cheVerifierName = cheVerifierName;
    }

    public String getCheVerifyDate() {
        return cheVerifyDate;
    }

    public void setCheVerifyDate(String cheVerifyDate) {
        this.cheVerifyDate = cheVerifyDate;
    }

    public String getCheVerifyAdvice() {
        return cheVerifyAdvice;
    }

    public void setCheVerifyAdvice(String cheVerifyAdvice) {
        this.cheVerifyAdvice = cheVerifyAdvice;
    }

    public String getResponsiCompanyName() {
        return responsiCompanyName;
    }

    public void setResponsiCompanyName(String responsiCompanyName) {
        this.responsiCompanyName = responsiCompanyName;
    }

    public String getResponsiCompanyCode() {
        return responsiCompanyCode;
    }

    public void setResponsiCompanyCode(String responsiCompanyCode) {
        this.responsiCompanyCode = responsiCompanyCode;
    }

    public Integer getResponsePersonID() {
        return responsePersonID;
    }

    public void setResponsePersonID(Integer responsePersonID) {
        this.responsePersonID = responsePersonID;
    }

    public String getResponsePersonName() {
        return responsePersonName;
    }

    public void setResponsePersonName(String responsePersonName) {
        this.responsePersonName = responsePersonName;
    }
}
