package com.wlhse.entity;

import java.util.Date;

/**
 * @Author tobing
 * @Date 2020/11/25 23:55
 * @Description
 */
public class DashboardQueryParam {
    private String companyCode;
    private Date minDate;
    private Date maxDate;

    public DashboardQueryParam(Date minDate, Date maxDate) {
        this.minDate = minDate;
        this.maxDate = maxDate;
    }

    public DashboardQueryParam(String companyCode, Date minDate, Date maxDate) {
        this.companyCode = companyCode;
        this.minDate = minDate;
        this.maxDate = maxDate;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Date getMinDate() {
        return minDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }
}