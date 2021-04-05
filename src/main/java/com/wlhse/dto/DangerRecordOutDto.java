package com.wlhse.dto;

import com.wlhse.dto.getDto.BaseGetDto;

/**
 * tobing
 */
public class DangerRecordOutDto extends BaseGetDto {
    private Integer safeStaff_ID;
    private String workItem;
    private String companyId;
    private String supervisionDate;
    private String type;
    private String description;
    private String reformPersonID;
    private String reformPerson;
    private String limitDate;
    private String receptionDate;
    private String reformCase;
    private Integer ok;
    private Integer consequenceID;
    private String consequence;
    private String rank;
    private String factorSource;
    private String profession;
    private String factorHSE;
    private String factorDepartment;
    private String location;
    private String qHSE_CheckCategory;
    private String qHSE_FileAudit_ID;
    private String qHSE_FileAuditRecord_ID;
    private String code;
    private String dangerSource;

    public Integer getSafeStaff_ID() {
        return safeStaff_ID;
    }

    public void setSafeStaff_ID(Integer safeStaff_ID) {
        this.safeStaff_ID = safeStaff_ID;
    }

    public String getWorkItem() {
        return workItem;
    }

    public void setWorkItem(String workItem) {
        this.workItem = workItem;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getSupervisionDate() {
        return supervisionDate;
    }

    public void setSupervisionDate(String supervisionDate) {
        this.supervisionDate = supervisionDate;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReformPersonID() {
        return reformPersonID;
    }

    public void setReformPersonID(String reformPersonID) {
        this.reformPersonID = reformPersonID;
    }

    public String getReformPerson() {
        return reformPerson;
    }

    public void setReformPerson(String reformPerson) {
        this.reformPerson = reformPerson;
    }

    public String getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(String limitDate) {
        this.limitDate = limitDate;
    }

    public String getReceptionDate() {
        return receptionDate;
    }

    public void setReceptionDate(String receptionDate) {
        this.receptionDate = receptionDate;
    }

    public String getReformCase() {
        return reformCase;
    }

    public void setReformCase(String reformCase) {
        this.reformCase = reformCase;
    }

    public Integer getOk() {
        return ok;
    }

    public void setOk(Integer ok) {
        this.ok = ok;
    }

    public Integer getConsequenceID() {
        return consequenceID;
    }

    public void setConsequenceID(Integer consequenceID) {
        this.consequenceID = consequenceID;
    }

    public String getConsequence() {
        return consequence;
    }

    public void setConsequence(String consequence) {
        this.consequence = consequence;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getFactorSource() {
        return factorSource;
    }

    public void setFactorSource(String factorSource) {
        this.factorSource = factorSource;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getFactorHSE() {
        return factorHSE;
    }

    public void setFactorHSE(String factorHSE) {
        this.factorHSE = factorHSE;
    }

    public String getFactorDepartment() {
        return factorDepartment;
    }

    public void setFactorDepartment(String factorDepartment) {
        this.factorDepartment = factorDepartment;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getqHSE_CheckCategory() {
        return qHSE_CheckCategory;
    }

    public void setqHSE_CheckCategory(String qHSE_CheckCategory) {
        this.qHSE_CheckCategory = qHSE_CheckCategory;
    }

    public String getqHSE_FileAudit_ID() {
        return qHSE_FileAudit_ID;
    }

    public void setqHSE_FileAudit_ID(String qHSE_FileAudit_ID) {
        this.qHSE_FileAudit_ID = qHSE_FileAudit_ID;
    }

    public String getqHSE_FileAuditRecord_ID() {
        return qHSE_FileAuditRecord_ID;
    }

    public void setqHSE_FileAuditRecord_ID(String qHSE_FileAuditRecord_ID) {
        this.qHSE_FileAuditRecord_ID = qHSE_FileAuditRecord_ID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDangerSource() {
        return dangerSource;
    }

    public void setDangerSource(String dangerSource) {
        this.dangerSource = dangerSource;
    }
}
