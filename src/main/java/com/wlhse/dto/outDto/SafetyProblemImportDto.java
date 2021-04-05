package com.wlhse.dto.outDto;

import com.wlhse.dto.getDto.BaseGetDto;
import org.springframework.stereotype.Component;

@Component
public class SafetyProblemImportDto extends BaseGetDto {
    private Integer problemID;
    private String status;
    private String companyCode;//受检单位ID
    private String companyName;//受检单位名称
    private String parentCompanyName;//受检二级单位名称
    private String checkDate;//检查时间
    private String address;
    private String checkItemCode; //检查项编码 叶子节点
    private String checkItemName1;
    private String checkItemName2;
    private String checkItemName3;
    private String descriptionDetail; //问题详细描述
    private String isRectOntime;//是否立即整改
    private String limtRectDate;//限定整改完成日期
    private String checkAttach1;//检查附件1
    private String checkAttach2;//检查附件2
    private String checkAttach3;//检查附件3
    private String checkAttach4;//检查附件4
    private String checkVideo;
    private Integer checkPersonID; //录入人员ID
    private String checkPersonName; //录入人员姓名
    private String isRules;//是否有制度
    private String isTrain;//是否有制度
    private String isCheck;//是否有制度

    private String hash;

    private String url;
    private String problemCategory;//问题类别,隐患|违章

    public Integer getProblemID() { return problemID; }

    public void setProblemID(Integer problemID) {
        this.problemID = problemID;
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

    public String getParentCompanyName() {
        return parentCompanyName;
    }

    public void setParentCompanyName(String parentCompanyName) {
        this.parentCompanyName = parentCompanyName;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCheckItemCode() {
        return checkItemCode;
    }

    public void setCheckItemCode(String checkItemCode) {
        this.checkItemCode = checkItemCode;
    }

    public String getCheckItemName1() {
        return checkItemName1;
    }

    public void setCheckItemName1(String checkItemName1) {
        this.checkItemName1 = checkItemName1;
    }

    public String getCheckItemName2() {
        return checkItemName2;
    }

    public void setCheckItemName2(String checkItemName2) {
        this.checkItemName2 = checkItemName2;
    }

    public String getCheckItemName3() {
        return checkItemName3;
    }

    public void setCheckItemName3(String checkItemName3) {
        this.checkItemName3 = checkItemName3;
    }

    public String getDescriptionDetail() {
        return descriptionDetail;
    }

    public void setDescriptionDetail(String descriptionDetail) {
        this.descriptionDetail = descriptionDetail;
    }

    public String getIsRectOntime() {
        return isRectOntime;
    }

    public void setIsRectOntime(String isRectOntime) {
        this.isRectOntime = isRectOntime;
    }

    public String getLimtRectDate() {
        return limtRectDate;
    }

    public void setLimtRectDate(String limtRectDate) {
        this.limtRectDate = limtRectDate;
    }

    public String getCheckAttach1() {
        return checkAttach1;
    }

    public void setCheckAttach1(String checkAttach1) {
        this.checkAttach1 = checkAttach1;
    }

    public String getCheckAttach2() {
        return checkAttach2;
    }

    public void setCheckAttach2(String checkAttach2) {
        this.checkAttach2 = checkAttach2;
    }

    public String getCheckAttach3() {
        return checkAttach3;
    }

    public void setCheckAttach3(String checkAttach3) {
        this.checkAttach3 = checkAttach3;
    }

    public String getCheckAttach4() {
        return checkAttach4;
    }

    public void setCheckAttach4(String checkAttach4) {
        this.checkAttach4 = checkAttach4;
    }

    public Integer getCheckPersonID() {
        return checkPersonID;
    }

    public void setCheckPersonID(Integer checkPersonID) {
        this.checkPersonID = checkPersonID;
    }

    public String getCheckPersonName() {
        return checkPersonName;
    }

    public void setCheckPersonName(String checkPersonName) {
        this.checkPersonName = checkPersonName;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsRules(){return isRules;}

    public void setIsRules(String isRules){this.isRules = isRules;}

    public String getIsTrain(){return isTrain;}

    public void setIsTrain(String isTrain){this.isTrain = isTrain;}

    public String getIsCheck(){return isCheck;}

    public void setIsCheck(String isCheck){this.isCheck = isCheck;}


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCheckVideo() {
        return checkVideo;
    }

    public void setCheckVideo(String checkVideo) {
        this.checkVideo = checkVideo;
    }

    public String getProblemCategory() {
        return problemCategory;
    }

    public void setProblemCategory(String problemCategory) {
        this.problemCategory = problemCategory;
    }
}
