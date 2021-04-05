package com.wlhse.entity;

/**
 * @Author tobing
 * @Date 2020/11/27 10:05
 * @Description
 */
public class DashboardSecurityMillionDto {
    /**
     * 月度累计工时
     */
    private Double monthSubtotal;
    /**
     * 年度累计工时
     */
    private Double yearSubtotal;
    /**
     * 当前年份
     */
    private String year;
    /**
     * 当前月份
     */
    private String month;

    public Double getMonthSubtotal() {
        return monthSubtotal;
    }

    public void setMonthSubtotal(Double monthSubtotal) {
        this.monthSubtotal = monthSubtotal;
    }

    public Double getYearSubtotal() {
        return yearSubtotal;
    }

    public void setYearSubtotal(Double yearSubtotal) {
        this.yearSubtotal = yearSubtotal;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "DashboardSecurityMillionDto{" +
                "monthSubtotal=" + monthSubtotal +
                ", yearSubtotal=" + yearSubtotal +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                '}';
    }
}


