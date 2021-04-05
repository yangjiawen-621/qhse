package com.wlhse.entity;

public class MonitorInputCheckRecord {

    private int monitorInputCheckRecordID;
    private int monitorPlanID;
    private int monitorPlanDetailID;
    private String condition;
    private String description;
    private String picNo;
    private String disposeIn;
    private String closeIn;
    private String check;
    private String disposeCheck;
    private String closeCheck;
    private int inputPersonID;
    private String inputPersonName;
    private String inputDate;
    private String checkPersonID;
    private String checkPersonName;
    private String checkDate;
    private String picLink;
    private String result;
    private String checkStatus;


    public MonitorInputCheckRecord() {
    }

    public MonitorInputCheckRecord(int monitorInputCheckRecordID, int monitorPlanID, int monitorPlanDetailID, String condition, String description, String picNo, String disposeIn, String closeIn, String check, String disposeCheck, String closeCheck, int inputPersonID, String inputPersonName, String inputDate, String checkPersonID, String checkPersonName, String checkDate, String picLink, String result, String checkStatus) {
        this.monitorInputCheckRecordID = monitorInputCheckRecordID;
        this.monitorPlanID = monitorPlanID;
        this.monitorPlanDetailID = monitorPlanDetailID;
        this.condition = condition;
        this.description = description;
        this.picNo = picNo;
        this.disposeIn = disposeIn;
        this.closeIn = closeIn;
        this.check = check;
        this.disposeCheck = disposeCheck;
        this.closeCheck = closeCheck;
        this.inputPersonID = inputPersonID;
        this.inputPersonName = inputPersonName;
        this.inputDate = inputDate;
        this.checkPersonID = checkPersonID;
        this.checkPersonName = checkPersonName;
        this.checkDate = checkDate;
        this.picLink = picLink;
        this.result = result;
        this.checkStatus = checkStatus;
    }

    public int getMonitorInputCheckRecordID() {
        return monitorInputCheckRecordID;
    }

    public void setMonitorInputCheckRecordID(int monitorInputCheckRecordID) {
        this.monitorInputCheckRecordID = monitorInputCheckRecordID;
    }

    public int getMonitorPlanID() {
        return monitorPlanID;
    }

    public void setMonitorPlanID(int monitorPlanID) {
        this.monitorPlanID = monitorPlanID;
    }

    public int getMonitorPlanDetailID() {
        return monitorPlanDetailID;
    }

    public void setMonitorPlanDetailID(int monitorPlanDetailID) {
        this.monitorPlanDetailID = monitorPlanDetailID;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicNo() {
        return picNo;
    }

    public void setPicNo(String picNo) {
        this.picNo = picNo;
    }

    public String getDisposeIn() {
        return disposeIn;
    }

    public void setDisposeIn(String disposeIn) {
        this.disposeIn = disposeIn;
    }

    public String getCloseIn() {
        return closeIn;
    }

    public void setCloseIn(String closeIn) {
        this.closeIn = closeIn;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getDisposeCheck() {
        return disposeCheck;
    }

    public void setDisposeCheck(String disposeCheck) {
        this.disposeCheck = disposeCheck;
    }

    public String getCloseCheck() {
        return closeCheck;
    }

    public void setCloseCheck(String closeCheck) {
        this.closeCheck = closeCheck;
    }

    public int getInputPersonID() {
        return inputPersonID;
    }

    public void setInputPersonID(int inputPersonID) {
        this.inputPersonID = inputPersonID;
    }

    public String getInputPersonName() {
        return inputPersonName;
    }

    public void setInputPersonName(String inputPersonName) {
        this.inputPersonName = inputPersonName;
    }

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }

    public String getCheckPersonID() {
        return checkPersonID;
    }

    public void setCheckPersonID(String checkPersonID) {
        this.checkPersonID = checkPersonID;
    }

    public String getCheckPersonName() {
        return checkPersonName;
    }

    public void setCheckPersonName(String checkPersonName) {
        this.checkPersonName = checkPersonName;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getPicLink() {
        return picLink;
    }

    public void setPicLink(String picLink) {
        this.picLink = picLink;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    @Override
    public String toString() {
        return "MonitorInputCheckRecord{" +
                "monitorInputCheckRecordID=" + monitorInputCheckRecordID +
                ", monitorPlanID=" + monitorPlanID +
                ", monitorPlanDetailID=" + monitorPlanDetailID +
                ", condition='" + condition + '\'' +
                ", description='" + description + '\'' +
                ", picNo='" + picNo + '\'' +
                ", disposeIn='" + disposeIn + '\'' +
                ", closeIn='" + closeIn + '\'' +
                ", check='" + check + '\'' +
                ", disposeCheck='" + disposeCheck + '\'' +
                ", closeCheck='" + closeCheck + '\'' +
                ", inputPersonID=" + inputPersonID +
                ", inputPersonName='" + inputPersonName + '\'' +
                ", inputDate='" + inputDate + '\'' +
                ", checkPersonID='" + checkPersonID + '\'' +
                ", checkPersonName='" + checkPersonName + '\'' +
                ", checkDate='" + checkDate + '\'' +
                ", picLink='" + picLink + '\'' +
                ", result='" + result + '\'' +
                ", checkStatus='" + checkStatus + '\'' +
                '}';
    }
}
