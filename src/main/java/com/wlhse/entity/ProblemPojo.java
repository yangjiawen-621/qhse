package com.wlhse.entity;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ProblemPojo {
    private Integer problemId;// 问题标识
    private Integer taskId;// 任务标识
    private Integer planId;// 计划标识
    private Integer planContentId;// 计划内容标识
    private String companyCode;// 受检单位Id
    private String companyName;// 受检单位名称
    private String parentCompanyName;// 受检二级单位名称
    private Date checkDate;// 检查时间
    private String address;//地点
    private String checkItemCode;// 检查项编码
    private String taskAndProcessCode;// 任务工序编码
    private String problemDescriptionCode;
    private String description;// 问题描述
    private String isRectOntime;//是否立即整改
    private Date limtRectDate;//限定整改完成日期
    private String recommendRectiMeasure;//建议整改措施
    private String problemStation;//问题所属岗位
    private String responsePersonId;// 责任人
    private String responseEmpGroup;//责任人性质
    private String problemRank;// 问题级别
    private String problemClass1;// 问题类别1
    private String problemClass2;// 问题类别2
    private String problemRepeat;//问题重复性
    private String source;// 问题原因
    private String checkAttach1;//检查附件1
    private String checkAttach2;//检查附件2
    private String checkAttach3;//检查附件3
    private String checkAttach4;//检查附件4
    private String rectiMeasure;// 整改措施
    private String rectiStatus;// 整改状态
    private String isPostpone;//是否延期
    private Date rectiFinishDate;//整改完成时间
    private String rectiPerson;//整改负责人
    private Integer penalty;//违章扣款
    private Float lostScore;//违章记分
    private String rectAttach1;// 整改附件1
    private String rectAttach2;// 整改附件2
    private String rectAttach3;// 整改附件3
    private String rectAttach4;// 整改附件4
    private String verifyStatus;//验证状态
    private String verifyDescription;//验证结果描述
    private String rectEffect;//整改效果
    private Date verifyDate;//验证时间
    private String verifyPerson;//验证人
    private Integer verifyPersonEmpID;//验证人ID
    private String hash;//Hash值

    public ProblemPojo() {
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Integer getPlanContentId() {
        return planContentId;
    }

    public void setPlanContentId(Integer planContentId) {
        this.planContentId = planContentId;
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

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
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

    public String getTaskAndProcessCode() {
        return taskAndProcessCode;
    }

    public void setTaskAndProcessCode(String taskAndProcessCode) {
        this.taskAndProcessCode = taskAndProcessCode;
    }

    public String getProblemDescriptionCode() {
        return problemDescriptionCode;
    }

    public void setProblemDescriptionCode(String problemDescriptionCode) {
        this.problemDescriptionCode = problemDescriptionCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsRectOntime() {
        return isRectOntime;
    }

    public void setIsRectOntime(String isRectOntime) {
        this.isRectOntime = isRectOntime;
    }

    public Date getLimtRectDate() {
        return limtRectDate;
    }

    public void setLimtRectDate(Date limtRectDate) {
        this.limtRectDate = limtRectDate;
    }

    public String getRecommendRectiMeasure() {
        return recommendRectiMeasure;
    }

    public void setRecommendRectiMeasure(String recommendRectiMeasure) {
        this.recommendRectiMeasure = recommendRectiMeasure;
    }

    public String getProblemStation() {
        return problemStation;
    }

    public void setProblemStation(String problemStation) {
        this.problemStation = problemStation;
    }

    public String getResponsePersonId() {
        return responsePersonId;
    }

    public void setResponsePersonId(String responsePersonId) {
        this.responsePersonId = responsePersonId;
    }

    public String getResponseEmpGroup() {
        return responseEmpGroup;
    }

    public void setResponseEmpGroup(String responseEmpGroup) {
        this.responseEmpGroup = responseEmpGroup;
    }

    public String getProblemRank() {
        return problemRank;
    }

    public void setProblemRank(String problemRank) {
        this.problemRank = problemRank;
    }

    public String getProblemClass1() {
        return problemClass1;
    }

    public void setProblemClass1(String problemClass1) {
        this.problemClass1 = problemClass1;
    }

    public String getProblemClass2() {
        return problemClass2;
    }

    public void setProblemClass2(String problemClass2) {
        this.problemClass2 = problemClass2;
    }

    public String getProblemRepeat() {
        return problemRepeat;
    }

    public void setProblemRepeat(String problemRepeat) {
        this.problemRepeat = problemRepeat;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public String getRectiMeasure() {
        return rectiMeasure;
    }

    public void setRectiMeasure(String rectiMeasure) {
        this.rectiMeasure = rectiMeasure;
    }

    public String getRectiStatus() {
        return rectiStatus;
    }

    public void setRectiStatus(String rectiStatus) {
        this.rectiStatus = rectiStatus;
    }

    public String getIsPostpone() {
        return isPostpone;
    }

    public void setIsPostpone(String isPostpone) {
        this.isPostpone = isPostpone;
    }

    public Date getRectiFinishDate() {
        return rectiFinishDate;
    }

    public void setRectiFinishDate(Date rectiFinishDate) {
        this.rectiFinishDate = rectiFinishDate;
    }

    public String getRectiPerson() {
        return rectiPerson;
    }

    public void setRectiPerson(String rectiPerson) {
        this.rectiPerson = rectiPerson;
    }

    public Integer getPenalty() {
        return penalty;
    }

    public void setPenalty(Integer penalty) {
        this.penalty = penalty;
    }

    public Float getLostScore() {
        return lostScore;
    }

    public void setLostScore(Float lostScore) {
        this.lostScore = lostScore;
    }

    public String getRectAttach1() {
        return rectAttach1;
    }

    public void setRectAttach1(String rectAttach1) {
        this.rectAttach1 = rectAttach1;
    }

    public String getRectAttach2() {
        return rectAttach2;
    }

    public void setRectAttach2(String rectAttach2) {
        this.rectAttach2 = rectAttach2;
    }

    public String getRectAttach3() {
        return rectAttach3;
    }

    public void setRectAttach3(String rectAttach3) {
        this.rectAttach3 = rectAttach3;
    }

    public String getRectAttach4() {
        return rectAttach4;
    }

    public void setRectAttach4(String rectAttach4) {
        this.rectAttach4 = rectAttach4;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getVerifyDescription() {
        return verifyDescription;
    }

    public void setVerifyDescription(String verifyDescription) {
        this.verifyDescription = verifyDescription;
    }

    public String getRectEffect() {
        return rectEffect;
    }

    public void setRectEffect(String rectEffect) {
        this.rectEffect = rectEffect;
    }

    public Date getVerifyDate() {
        return verifyDate;
    }

    public void setVerifyDate(Date verifyDate) {
        this.verifyDate = verifyDate;
    }

    public String getVerifyPerson() {
        return verifyPerson;
    }

    public void setVerifyPerson(String verifyPerson) {
        this.verifyPerson = verifyPerson;
    }

    public Integer getVerifyPersonEmpID() {
        return verifyPersonEmpID;
    }

    public void setVerifyPersonEmpID(Integer verifyPersonEmpID) {
        this.verifyPersonEmpID = verifyPersonEmpID;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

}
