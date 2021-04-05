package com.wlhse.dto.outDto;


public class MonitorInputCheckRecordOutDto {
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
    private String inputPersonID;
    private String inputPersonName;
    private String inputDate;
    private String checkPersonID;
    private String checkPersonName;
    private String checkDate;
    private String no;
    private String deviceNo;
    private String myNo;
    private String projectName;
    private String charger;
    private String tel;
    private String picLink;
    private String companyName;
    private String itemCategory;
    private String result;
    private String checkStatus;

    public MonitorInputCheckRecordOutDto() {
    }

    public MonitorInputCheckRecordOutDto(int monitorInputCheckRecordID, int monitorPlanID, int monitorPlanDetailID, String condition, String description, String picNo, String disposeIn, String closeIn, String check, String disposeCheck, String closeCheck, String inputPersonID, String inputPersonName, String inputDate, String checkPersonID, String checkPersonName, String checkDate, String no, String deviceNo, String myNo, String projectName, String charger, String tel, String picLink, String companyName, String itemCategory, String result, String checkStatus) {
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
        this.no = no;
        this.deviceNo = deviceNo;
        this.myNo = myNo;
        this.projectName = projectName;
        this.charger = charger;
        this.tel = tel;
        this.picLink = picLink;
        this.companyName = companyName;
        this.itemCategory = itemCategory;
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

    public String getInputPersonID() {
        return inputPersonID;
    }

    public void setInputPersonID(String inputPersonID) {
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

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getMyNo() {
        return myNo;
    }

    public void setMyNo(String myNo) {
        this.myNo = myNo;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCharger() {
        return charger;
    }

    public void setCharger(String charger) {
        this.charger = charger;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPicLink() {
        return picLink;
    }

    public void setPicLink(String picLink) {
        this.picLink = picLink;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
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
        return "MonitorInputCheckRecordOutDto{" +
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
                ", inputPersonID='" + inputPersonID + '\'' +
                ", inputPersonName='" + inputPersonName + '\'' +
                ", inputDate='" + inputDate + '\'' +
                ", checkPersonID='" + checkPersonID + '\'' +
                ", checkPersonName='" + checkPersonName + '\'' +
                ", checkDate='" + checkDate + '\'' +
                ", no='" + no + '\'' +
                ", deviceNo='" + deviceNo + '\'' +
                ", myNo='" + myNo + '\'' +
                ", projectName='" + projectName + '\'' +
                ", charger='" + charger + '\'' +
                ", tel='" + tel + '\'' +
                ", picLink='" + picLink + '\'' +
                ", companyName='" + companyName + '\'' +
                ", itemCategory='" + itemCategory + '\'' +
                ", result='" + result + '\'' +
                ", checkStatus='" + checkStatus + '\'' +
                '}';
    }
}
