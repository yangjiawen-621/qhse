package com.wlhse.entity;

/**
 * @Author tobing
 * @Date 2020/11/30 22:04
 * @Description
 */
public class RecordDateQueryParam {
    private String startDate;
    private String endDate;

    public RecordDateQueryParam() {
    }

    public RecordDateQueryParam(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
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
}
