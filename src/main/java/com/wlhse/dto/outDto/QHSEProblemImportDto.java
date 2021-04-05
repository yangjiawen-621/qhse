package com.wlhse.dto.outDto;

import com.wlhse.dto.getDto.BaseGetDto;
import org.springframework.stereotype.Component;

@Component
public class QHSEProblemImportDto extends BaseGetDto {
    private Integer problemID;
    private String recordCode;//记录编码
    private String checkClass;//检查类别
    private String checkClassCode;//检查类别code
    private Integer checkPersonID; //检查人员ID
    private String checkPersonName; //检查人员姓名
    private String companyCode;//受检单位ID
    private String companyName;//受检单位名称
    private String checkItemCode; //检查项编码
    private String checkItemName;//检查项名字
    private String profession;//专业
    private String professionCode;//专业编码
    private String address;//地点
    private String operatePersonName;//操作人员姓名
    private Integer operatePersonID;//操作人员ID
    private String operatePersonClass;//操作人员类别
    private String operatePersonClassCode;//操作人员类别code
    private String checkDate;//检查时间
    private Integer responsePersonID;//责任人ID
    private String responsePersonName; //责任人姓名
    private String isConformRuleName;//是否符合规章制度
    private String isConformRuleCode;//规章制度code
    private String isConformRuleRank;//不符合规章制度级别
    private String isConformRuleRankCode;//不符合规章制度级别code
    private String breakRuleRank;//违章级别
    private String breakRuleRankCode;//违章级别code
    private String breakRuleItem;//违章项
    private String breakRuleItemCode;//违章项code
    private Integer penalty;//违章扣款
    private Double lostScore;//违章记分
    private String isConformRuleClass;//不符合类型
    private String isConformRuleClassCode;//不符合类型code
    private String isConformDescription; //不符合描述
    private String problemKey;//问题点
    private String problemKeyCode;//问题点code
    private String checkAttach1;//检查附件1
    private String checkAttach2;//检查附件2
    private String checkAttach3;//检查附件3
    private String checkAttach4;//检查附件4
    private String problemLevel;//问题层级
    private String problemLevelCode;//问题层级code
    private String responseDepartment;//责任部门
    private String responseDepartmentCode;//责任部门code
    private String limtRectDate;//限定整改完成日期
    private String remarks;//备注
    private String hash;
    private String url;
    private String problemCategory;//问题类型，隐患|违章


    public Integer getProblemID() { return problemID; }
    public void setProblemID(Integer problemID) {
        this.problemID = problemID;
    }

    public String getRecordCode() {
        return recordCode;
    }
    public void setRecordCode(String recordCode) {
        this.recordCode = recordCode;
    }

    public String getCheckClass() { return checkClass; }
    public void setCheckClass(String checkClass) {
        this.checkClass = checkClass;
    }

    public String getCheckClassCode() {
        return checkClassCode;
    }
    public void setCheckClassCode(String checkClassCode) { this.checkClassCode = checkClassCode; }

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

    public String getCheckItemCode() {
        return checkItemCode;
    }
    public void setCheckItemCode(String checkItemCode) {
        this.checkItemCode = checkItemCode;
    }

    public String getCheckItemName() {
        return checkItemName;
    }
    public void setCheckItemName(String checkItemName) {
        this.checkItemName = checkItemName;
    }

    public String getProfession() {
        return profession;
    }
    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getProfessionCode() {
        return professionCode;
    }
    public void setProfessionCode(String professionCode) {
        this.professionCode = professionCode;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getOperatePersonName() {
        return operatePersonName;
    }
    public void setOperatePersonName(String operatePersonName) {
        this.operatePersonName = operatePersonName;
    }

    public Integer getOperatePersonID() {
        return operatePersonID;
    }
    public void setOperatePersonID(Integer operatePersonID) {
        this.operatePersonID = operatePersonID;
    }

    public String getOperatePersonClass() {
        return operatePersonClass;
    }
    public void setOperatePersonClass(String operatePersonClass) {
        this.operatePersonClass = operatePersonClass;
    }

    public String getOperatePersonClassCode() {
        return operatePersonClassCode;
    }
    public void setOperatePersonClassCode(String operatePersonClassCode) { this.operatePersonClassCode = operatePersonClassCode; }

    public String getCheckDate() {
        return checkDate;
    }
    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
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

    public String getIsConformRuleName() {
        return isConformRuleName;
    }
    public void setIsConformRuleName(String isConformRuleName) {
        this.isConformRuleName = isConformRuleName;
    }

    public String getIsConformRuleCode() {
        return isConformRuleCode;
    }
    public void setIsConformRuleCode(String isConformRuleCode) {
        this.isConformRuleCode = isConformRuleCode;
    }

    public String getIsConformRuleRank() {
        return isConformRuleRank;
    }
    public void setIsConformRuleRank(String isConformRuleRank) {
        this.isConformRuleRank = isConformRuleRank;
    }

    public String getIsConformRuleRankCode() {
        return isConformRuleRankCode;
    }
    public void setIsConformRuleRankCode(String isConformRuleRankCode) { this.isConformRuleRankCode = isConformRuleRankCode; }

    public String getBreakRuleRank() {
        return breakRuleRank;
    }
    public void setBreakRuleRank(String breakRuleRank) {
        this.breakRuleRank = breakRuleRank;
    }

    public String getBreakRuleRankCode() {
        return breakRuleRankCode;
    }
    public void setBreakRuleRankCode(String breakRuleRankCode) { this.breakRuleRankCode = breakRuleRankCode; }

    public String getBreakRuleItem() {
        return breakRuleItem;
    }
    public void setBreakRuleItem(String breakRuleItem) {
        this.breakRuleItem = breakRuleItem;
    }

    public String getBreakRuleItemCode() {
        return breakRuleItemCode;
    }
    public void setBreakRuleItemCode(String breakRuleItemCode) { this.breakRuleItemCode = breakRuleItemCode; }

    public Integer getPenalty() {
        return penalty;
    }
    public void setPenalty(Integer penalty) {
        this.penalty = penalty;
    }

    public Double getLostScore() {
        return lostScore;
    }
    public void setLostScore(Double lostScore) {
        this.lostScore = lostScore;
    }

    public String getIsConformRuleClass() {
        return isConformRuleClass;
    }
    public void setIsConformRuleClass(String isConformRuleClass) {
        this.isConformRuleClass = isConformRuleClass;
    }

    public String getIsConformRuleClassCode() {
        return isConformRuleClassCode;
    }
    public void setIsConformRuleClassCode(String isConformRuleClassCode) { this.isConformRuleClassCode = isConformRuleClassCode; }

    public String getIsConformDescription() {
        return isConformDescription;
    }
    public void setIsConformDescription(String isConformDescription) { this.isConformDescription = isConformDescription; }

    public String getProblemKey() {
        return problemKey;
    }
    public void setProblemKey(String problemKey) {
        this.problemKey = problemKey;
    }

    public String getProblemKeyCode() {
        return problemKeyCode;
    }
    public void setProblemKeyCode(String problemKeyCode) {
        this.problemKeyCode = problemKeyCode;
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

    public String getProblemLevel() {
        return problemLevel;
    }
    public void setProblemLevel(String problemLevel) {
        this.problemLevel = problemLevel;
    }

    public String getProblemLevelCode() {
        return problemLevelCode;
    }
    public void setProblemLevelCode(String problemLevelCode) { this.problemLevelCode = problemLevelCode; }

    public String getResponseDepartment() {
        return responseDepartment;
    }
    public void setResponseDepartment(String responseDepartment) {
        this.responseDepartment = responseDepartment;
    }

    public String getResponseDepartmentCode() {
        return responseDepartmentCode;
    }
    public void setResponseDepartmentCode(String responseDepartmentCode) { this.responseDepartmentCode = responseDepartmentCode; }

    public String getLimtRectDate() {
        return limtRectDate;
    }
    public void setLimtRectDate(String limtRectDate) {
        this.limtRectDate = limtRectDate;
    }

    public String getRemarks(){return remarks;}
    public void setRemarks(String remarks){this.remarks = remarks;}

    public String getHash() {
        return hash;
    }
    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getProblemCategory() {
        return problemCategory;
    }

    public void setProblemCategory(String problemCategory) {
        this.problemCategory = problemCategory;
    }

}
