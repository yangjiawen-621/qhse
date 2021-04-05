package com.wlhse.dto.outDto;

/**
 * Description:package the attributes of employees' propagation
 * Author:Coco
 * create:2020-08-02 10:59 PM
 **/
public class FilePropagationDetailDto {
   private int filePropagationPlanDetailID;
   private Long filePropagationID;
   private String pushCompanyCode;
   private String pushCompanyName;
   private int pushStaffId;
   private String pushStaffName;
   private String status;
   private String readDate;
   private String department;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getFilePropagationPlanDetailID() {
        return filePropagationPlanDetailID;
    }

    public void setFilePropagationPlanDetailID(int filePropagationPlanDetailID) {
        this.filePropagationPlanDetailID = filePropagationPlanDetailID;
    }

    public Long getFilePropagationID() {
        return filePropagationID;
    }

    public void setFilePropagationID(Long filePropagationID) {
        this.filePropagationID = filePropagationID;
    }

    public String getPushCompanyCode() {
        return pushCompanyCode;
    }

    public void setPushCompanyCode(String pushCompanyCode) {
        this.pushCompanyCode = pushCompanyCode;
    }

    public String getPushCompanyName() {
        return pushCompanyName;
    }

    public void setPushCompanyName(String pushCompanyName) {
        this.pushCompanyName = pushCompanyName;
    }

    public int getPushStaffId() {
        return pushStaffId;
    }

    public void setPushStaffId(int pushStaffId) {
        this.pushStaffId = pushStaffId;
    }

    public String getPushStaffName() {
        return pushStaffName;
    }

    public void setPushStaffName(String pushStaffName) {
        this.pushStaffName = pushStaffName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReadDate() {
        return readDate;
    }

    public void setReadDate(String readDate) {
        this.readDate = readDate;
    }

    @Override
    public String toString() {
        return "FilePropagationDetailDto{" +
                "filePropagationPlanDetailID=" + filePropagationPlanDetailID +
                ", filePropagationID=" + filePropagationID +
                ", pushCompanyCode='" + pushCompanyCode + '\'' +
                ", pushCompanyName='" + pushCompanyName + '\'' +
                ", pushStaffId=" + pushStaffId +
                ", pushStaffName='" + pushStaffName + '\'' +
                ", status='" + status + '\'' +
                ", readDate='" + readDate + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
