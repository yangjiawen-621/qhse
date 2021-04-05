package com.wlhse.dto.outDto;

/**
 * Description:
 * Author:Coco
 * create:2020-08-06 10:22 AM
 **/
public class ReadHistoryDto {
    private int pushCompanyCode;
    private String pushCompanyName;
    private int pushStaffId;
    private String pushStaffName;
    private String readDate;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPushCompanyCode() {
        return pushCompanyCode;
    }

    public void setPushCompanyCode(int pushCompanyCode) {
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



    public String getReadDate() {
        return readDate;
    }

    public void setReadDate(String readDate) {
        this.readDate = readDate;
    }

    @Override
    public String toString() {
        return "ReadHistoryDto{" +
                "pushCompanyCode=" + pushCompanyCode +
                ", pushCompanyName='" + pushCompanyName + '\'' +
                ", pushStaffId=" + pushStaffId +
                ", pushStaffName='" + pushStaffName + '\'' +
                ", readDate='" + readDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
