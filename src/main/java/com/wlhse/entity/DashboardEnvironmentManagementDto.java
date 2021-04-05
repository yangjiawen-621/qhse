package com.wlhse.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

import java.util.Date;

/**
 * @Author tobing
 * @Date 2020/11/23 19:49
 * @Description 大屏-环保管理-结果数据
 */
public class DashboardEnvironmentManagementDto {
    private String companyCode;
    private String companyName;
    /**
     * 月度累计污水量
     */
    private Double sewageVolumeMonth;
    /**
     * 季度累计污水量
     */
    private Double sewageVolumeQuarter;
    /**
     * 年度累计污水量
     */
    private Double sewageVolumeYear;
    /**
     * 月度累计污水处理量
     */
    private Double sewageTransferMonth;
    /**
     * 季度累计污水处理量
     */
    private Double sewageTransferQuarter;
    /**
     * 年度累计污水处理量
     */
    private Double sewageTransferYear;
    /**
     * 月度水消耗量
     */
    private Double waterMonth;
    /**
     * 年度水消耗量
     */
    private Double waterYear;
    /**
     * 电月度消耗量
     */
    private Double electricityMonth;
    /**
     * 电年度消耗量
     */
    private Double electricityYear;
    /**
     * 气月度消耗量
     */
    private Double gasMonth;
    /**
     * 气年度消耗量
     */
    private Double gasYear;
    /**
     * 汽油月累计消耗量
     */
    private Double gasolineMonth;
    /**
     * 汽油年累计消耗量
     */
    private Double gasolineYear;
    /**
     * 柴油月累计消耗量
     */
    private Double dieselMonth;
    /**
     * 柴油年累计消耗量
     */
    private Double dieselYear;
    /**
     * 年度
     */
    private String year;
    /**
     * 月度
     */
    private String monthYear;

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

    public Double getSewageVolumeMonth() {
        return sewageVolumeMonth;
    }

    public void setSewageVolumeMonth(Double sewageVolumeMonth) {
        this.sewageVolumeMonth = sewageVolumeMonth;
    }

    public Double getSewageVolumeQuarter() {
        return sewageVolumeQuarter;
    }

    public void setSewageVolumeQuarter(Double sewageVolumeQuarter) {
        this.sewageVolumeQuarter = sewageVolumeQuarter;
    }

    public Double getSewageVolumeYear() {
        return sewageVolumeYear;
    }

    public void setSewageVolumeYear(Double sewageVolumeYear) {
        this.sewageVolumeYear = sewageVolumeYear;
    }

    public Double getSewageTransferMonth() {
        return sewageTransferMonth;
    }

    public void setSewageTransferMonth(Double sewageTransferMonth) {
        this.sewageTransferMonth = sewageTransferMonth;
    }

    public Double getSewageTransferQuarter() {
        return sewageTransferQuarter;
    }

    public void setSewageTransferQuarter(Double sewageTransferQuarter) {
        this.sewageTransferQuarter = sewageTransferQuarter;
    }

    public Double getSewageTransferYear() {
        return sewageTransferYear;
    }

    public void setSewageTransferYear(Double sewageTransferYear) {
        this.sewageTransferYear = sewageTransferYear;
    }

    public Double getWaterMonth() {
        return waterMonth;
    }

    public void setWaterMonth(Double waterMonth) {
        this.waterMonth = waterMonth;
    }

    public Double getWaterYear() {
        return waterYear;
    }

    public void setWaterYear(Double waterYear) {
        this.waterYear = waterYear;
    }

    public Double getElectricityMonth() {
        return electricityMonth;
    }

    public void setElectricityMonth(Double electricityMonth) {
        this.electricityMonth = electricityMonth;
    }

    public Double getElectricityYear() {
        return electricityYear;
    }

    public void setElectricityYear(Double electricityYear) {
        this.electricityYear = electricityYear;
    }

    public Double getGasMonth() {
        return gasMonth;
    }

    public void setGasMonth(Double gasMonth) {
        this.gasMonth = gasMonth;
    }

    public Double getGasYear() {
        return gasYear;
    }

    public void setGasYear(Double gasYear) {
        this.gasYear = gasYear;
    }

    public Double getGasolineMonth() {
        return gasolineMonth;
    }

    public void setGasolineMonth(Double gasolineMonth) {
        this.gasolineMonth = gasolineMonth;
    }

    public Double getGasolineYear() {
        return gasolineYear;
    }

    public void setGasolineYear(Double gasolineYear) {
        this.gasolineYear = gasolineYear;
    }

    public Double getDieselMonth() {
        return dieselMonth;
    }

    public void setDieselMonth(Double dieselMonth) {
        this.dieselMonth = dieselMonth;
    }

    public Double getDieselYear() {
        return dieselYear;
    }

    public void setDieselYear(Double dieselYear) {
        this.dieselYear = dieselYear;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    @Override
    public String toString() {
        return "DashboardEnvironmentManagementDto{" +
                "companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", sewageVolumeMonth=" + sewageVolumeMonth +
                ", sewageVolumeQuarter=" + sewageVolumeQuarter +
                ", sewageVolumeYear=" + sewageVolumeYear +
                ", sewageTransferMonth=" + sewageTransferMonth +
                ", sewageTransferQuarter=" + sewageTransferQuarter +
                ", sewageTransferYear=" + sewageTransferYear +
                ", waterMonth=" + waterMonth +
                ", waterYear=" + waterYear +
                ", electricityMonth=" + electricityMonth +
                ", electricityYear=" + electricityYear +
                ", gasMonth=" + gasMonth +
                ", gasYear=" + gasYear +
                ", gasolineMonth=" + gasolineMonth +
                ", gasolineYear=" + gasolineYear +
                ", dieselMonth=" + dieselMonth +
                ", dieselYear=" + dieselYear +
                ", year='" + year + '\'' +
                ", monthYear='" + monthYear + '\'' +
                '}';
    }
}