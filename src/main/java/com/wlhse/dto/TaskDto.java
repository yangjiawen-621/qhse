package com.wlhse.dto;

/**
 * Description:
 * Author:Coco
 * create:2020-08-27 4:31 PM
 **/
public class TaskDto {
    int qHSETaskID;
    String companyCode;
    String companyName;
    String taskName;
    int issuedID;
    String issuedName;
    String issuedDate;
    int receiveID;
    String receiveName;
    String receiveDate;
    String planDate;
    String trueDate;
    int tableID;
    String status;

    public int getqHSETaskID() {
        return qHSETaskID;
    }

    public void setqHSETaskID(int qHSETaskID) {
        this.qHSETaskID = qHSETaskID;
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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getIssuedID() {
        return issuedID;
    }

    public void setIssuedID(int issuedID) {
        this.issuedID = issuedID;
    }

    public String getIssuedName() {
        return issuedName;
    }

    public void setIssuedName(String issuedName) {
        this.issuedName = issuedName;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public int getReceiveID() {
        return receiveID;
    }

    public void setReceiveID(int receiveID) {
        this.receiveID = receiveID;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getPlanDate() {
        return planDate;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }

    public String getTrueDate() {
        return trueDate;
    }

    public void setTrueDate(String trueDate) {
        this.trueDate = trueDate;
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TaskDto{" +
                "qHSETaskID=" + qHSETaskID +
                ", companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", taskName='" + taskName + '\'' +
                ", issuedID=" + issuedID +
                ", issuedName='" + issuedName + '\'' +
                ", issuedDate='" + issuedDate + '\'' +
                ", receiveID=" + receiveID +
                ", receiveName='" + receiveName + '\'' +
                ", receiveDate='" + receiveDate + '\'' +
                ", planDate='" + planDate + '\'' +
                ", trueDate='" + trueDate + '\'' +
                ", tableID=" + tableID +
                ", status='" + status + '\'' +
                '}';
    }
}
