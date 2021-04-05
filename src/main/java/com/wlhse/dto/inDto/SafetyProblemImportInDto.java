package com.wlhse.dto.inDto;

import com.wlhse.dto.getDto.BaseGetDto;
import org.springframework.stereotype.Component;

@Component
public class SafetyProblemImportInDto extends BaseGetDto {
    private String companyCode;//受检单位ID
    private String companyName;//受检单位名
    private Integer checkPersonID; //录入人员ID
    private String checkDate1;
    private String checkDate2;
    private String isRules;     //是否有制度
    private String isTrain;     //是否培训
    private String isCheck;     //是否考核
    private String url;
    private String isOrderBy = "false";

    private String status;

    //V2
    private String responsePersonName;//责任人姓名

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Integer getCheckPersonID() {
        return checkPersonID;
    }

    public void setCheckPersonID(Integer checkPersonID) {
        this.checkPersonID = checkPersonID;
    }

    public String getCheckDate1() {
        return checkDate1;
    }

    public void setCheckDate1(String checkDate1) {
        this.checkDate1 = checkDate1;
    }

    public String getCheckDate2() {
        return checkDate2;
    }

    public void setCheckDate2(String checkDate2) {
        this.checkDate2 = checkDate2;
    }

    public String getIsRules() {
        return isRules;
    }

    public void setIsRules(String isRules) {
        this.isRules = isRules;
    }

    public String getIsTrain() {
        return isTrain;
    }

    public void setIsTrain(String isTrain) {
        this.isTrain = isTrain;
    }

    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) { this.url = url; }

    public String getIsOrderBy() {
        return isOrderBy;
    }

    public void setIsOrderBy(String isOrderBy) {
        this.isOrderBy = isOrderBy;
    }

    public String getResponsePersonName() {
        return responsePersonName;
    }

    public void setResponsePersonName(String responsePersonName) {
        this.responsePersonName = responsePersonName;
    }
}
