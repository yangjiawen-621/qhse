package com.wlhse.dto.outDto;

/**
 * Description:
 * Author:Coco
 * create:2020-08-27 5:16 PM
 **/
public class TaskOutDto {
    int qHSETaskID;
    String companyName;
    String taskName;
    String issuedName;
    String issuedDate;
    String receiveName;
    String receiveDate;
    String planDate;
    String trueDate;
    String status;

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public int getqHSETaskID() {
        return qHSETaskID;
    }

    public void setqHSETaskID(int qHSETaskID) {
        this.qHSETaskID = qHSETaskID;
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

    public String getIssuedName() {
        return issuedName;
    }

    public void setIssuedName(String issuedName) {
        this.issuedName = issuedName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TaskOutDto{" +
                "qHSETaskID=" + qHSETaskID +
                ", companyName='" + companyName + '\'' +
                ", taskName='" + taskName + '\'' +
                ", issuedName='" + issuedName + '\'' +
                ", receiveName='" + receiveName + '\'' +
                ", receiveDate='" + receiveDate + '\'' +
                ", planDate='" + planDate + '\'' +
                ", trueDate='" + trueDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
