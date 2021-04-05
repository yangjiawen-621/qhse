package com.wlhse.dto;


public class MonitorPlan {
    private int monitorPlanID;
    private String startDate;
    private String endDate;
    private String planName;
    private int planPersonID;
    private String planPersonName;
    private String companyCode;
    private String status;



    public MonitorPlan() {
    }

    public MonitorPlan(int monitorPlanID, String startDate, String endDate, String planName, int planPersonID, String planPersonName, String companyCode, String status) {
        this.monitorPlanID = monitorPlanID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.planName = planName;
        this.planPersonID = planPersonID;
        this.planPersonName = planPersonName;
        this.companyCode = companyCode;
        this.status = status;
    }

    public int getMonitorPlanID() {
        return monitorPlanID;
    }

    public void setMonitorPlanID(int monitorPlanID) {
        this.monitorPlanID = monitorPlanID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public int getPlanPersonID() {
        return planPersonID;
    }

    public void setPlanPersonID(int planPersonID) {
        this.planPersonID = planPersonID;
    }

    public String getPlanPersonName() {
        return planPersonName;
    }

    public void setPlanPersonName(String planPersonName) {
        this.planPersonName = planPersonName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return "MonitorPlan{" +
                "monitorPlanID=" + monitorPlanID +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", planName='" + planName + '\'' +
                ", planPersonID=" + planPersonID +
                ", planPersonName='" + planPersonName + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
