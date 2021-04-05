package com.wlhse.dto;

import org.springframework.stereotype.Component;

@Component
public class PoiProblemDto {
    //excel需要填写字段
    private String orderNumber;
    private String parentCompanyName;
    private String companyName;
    private String checkDate;

    private String checkType;
    private String checkItemName1;
    private String checkItemName2;
    private String checkItemName3;

    private String task;
    private String process;
    private String description;
    private String descriptionDetail;

    private String recommendRectiMeasure;

    private String responsePersonName;
    private String responsePersonParentCompanyName;
    private String responsePersonCompanyName;

    private Integer penalty;
    private double lostScore;

    //需要通过上述信息查询出的条件
    private Integer taskId;
    private String taskName;
    private String companyCode;
    private String checkItemCode;
    private String taskAndProcessCode;
    private String problemDescriptionCode;

    private String problemStation;
    private Integer responsePersonID;
    private String responseEmpGroup;


    //hash
    private String hash;

    private Integer publishID;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getParentCompanyName() {
        return parentCompanyName;
    }

    public void setParentCompanyName(String parentCompanyName) {
        this.parentCompanyName = parentCompanyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
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

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionDetail() {
        return descriptionDetail;
    }

    public void setDescriptionDetail(String descriptionDetail) {
        this.descriptionDetail = descriptionDetail;
    }

    public String getResponsePersonName() {
        return responsePersonName;
    }

    public void setResponsePersonName(String responsePersonName) {
        this.responsePersonName = responsePersonName;
    }

    public String getResponsePersonParentCompanyName() {
        return responsePersonParentCompanyName;
    }

    public void setResponsePersonParentCompanyName(String responsePersonParentCompanyName) {
        this.responsePersonParentCompanyName = responsePersonParentCompanyName;
    }

    public String getResponsePersonCompanyName() {
        return responsePersonCompanyName;
    }

    public void setResponsePersonCompanyName(String responsePersonCompanyName) {
        this.responsePersonCompanyName = responsePersonCompanyName;
    }

    public Integer getPenalty() {
        return penalty;
    }

    public void setPenalty(Integer penalty) {
        this.penalty = penalty;
    }

    public double getLostScore() {
        return lostScore;
    }

    public void setLostScore(double lostScore) {
        this.lostScore = lostScore;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCheckItemCode() {
        return checkItemCode;
    }

    public void setCheckItemCode(String checkItemCode) {
        this.checkItemCode = checkItemCode;
    }

    public String getProblemDescriptionCode() {
        return problemDescriptionCode;
    }

    public void setProblemDescriptionCode(String problemDescriptionCode) {
        this.problemDescriptionCode = problemDescriptionCode;
    }

    public String getProblemStation() {
        return problemStation;
    }

    public void setProblemStation(String problemStation) {
        this.problemStation = problemStation;
    }

    public Integer getResponsePersonID() {
        return responsePersonID;
    }

    public void setResponsePersonID(Integer responsePersonID) {
        this.responsePersonID = responsePersonID;
    }

    public String getResponseEmpGroup() {
        return responseEmpGroup;
    }

    public void setResponseEmpGroup(String responseEmpGroup) {
        this.responseEmpGroup = responseEmpGroup;
    }

    public String getTaskAndProcessCode() {
        return taskAndProcessCode;
    }

    public void setTaskAndProcessCode(String taskAndProcessCode) {
        this.taskAndProcessCode = taskAndProcessCode;
    }

    public String getRecommendRectiMeasure() {
        return recommendRectiMeasure;
    }

    public void setRecommendRectiMeasure(String recommendRectiMeasure) {
        this.recommendRectiMeasure = recommendRectiMeasure;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getPublishID() {
        return publishID;
    }

    public void setPublishID(Integer publishID) {
        this.publishID = publishID;
    }

    public void setManyAttribute(Integer taskId, String taskName, String companyCode, String checkItemCode,
                                 String taskAndProcessCode, String problemDescriptionCode, String problemStation,
                                 Integer responsePersonID, String responseEmpGroup) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.companyCode = companyCode;
        this.checkItemCode = checkItemCode;
        this.taskAndProcessCode = taskAndProcessCode;
        this.problemDescriptionCode = problemDescriptionCode;
        this.problemStation = problemStation;
        this.responsePersonID = responsePersonID;
        this.responseEmpGroup = responseEmpGroup;
    }
}
