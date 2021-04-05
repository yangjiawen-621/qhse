package com.wlhse.entity;

import com.alibaba.excel.annotation.ExcelProperty;


public class MesSumData {
    private int mesCheckSumDataID;
    @ExcelProperty("日期")
    private String sumDate;
    private String companyCode;
    @ExcelProperty("基层单位")
    private String companyName;
    @ExcelProperty("开工项目数量")
    private int workNum;
    @ExcelProperty("日报数量")
    private int dayReportNum;
    @ExcelProperty("配备记录仪数量")
    private int recordDeviceNum;
    @ExcelProperty("出库数量")
    private int outStockNum;
    @ExcelProperty("开机使用数量")
    private int powerOnNum;
    @ExcelProperty("备用数量")
    private int backNum;
    @ExcelProperty("覆盖率")
    private float coverageRate;
    @ExcelProperty("利用率")
    private float availableRate;
    @ExcelProperty("使用率")
    private float useRate;

    public int getMesCheckSumDataID() {
        return mesCheckSumDataID;
    }

    public void setMesCheckSumDataID(int mesCheckSumDataID) {
        this.mesCheckSumDataID = mesCheckSumDataID;
    }

    public String getSumDate() {
        return sumDate;
    }

    public void setSumDate(String sumDate) {
        this.sumDate = sumDate;
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

    public int getWorkNum() {
        return workNum;
    }

    public void setWorkNum(int workNum) {
        this.workNum = workNum;
    }

    public int getDayReportNum() {
        return dayReportNum;
    }

    public void setDayReportNum(int dayReportNum) {
        this.dayReportNum = dayReportNum;
    }

    public int getRecordDeviceNum() {
        return recordDeviceNum;
    }

    public void setRecordDeviceNum(int recordDeviceNum) {
        this.recordDeviceNum = recordDeviceNum;
    }

    public int getOutStockNum() {
        return outStockNum;
    }

    public void setOutStockNum(int outStockNum) {
        this.outStockNum = outStockNum;
    }

    public int getPowerOnNum() {
        return powerOnNum;
    }

    public void setPowerOnNum(int powerOnNum) {
        this.powerOnNum = powerOnNum;
    }

    public int getBackNum() {
        return backNum;
    }

    public void setBackNum(int backNum) {
        this.backNum = backNum;
    }

    public float getCoverageRate() {
        return coverageRate;
    }

    public void setCoverageRate(float coverageRate) {
        this.coverageRate = coverageRate;
    }

    public float getAvailableRate() {
        return availableRate;
    }

    public void setAvailableRate(float availableRate) {
        this.availableRate = availableRate;
    }

    public float getUseRate() {
        return useRate;
    }

    public void setUseRate(float useRate) {
        this.useRate = useRate;
    }
}
