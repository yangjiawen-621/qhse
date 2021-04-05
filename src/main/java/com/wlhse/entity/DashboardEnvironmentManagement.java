package com.wlhse.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

import java.util.Date;

/**
 * @Author tobing
 * @Date 2020/11/23 19:49
 * @Description 大屏-环保管理
 */
public class DashboardEnvironmentManagement {
    @ExcelIgnore
    private Integer id;
    @ExcelProperty("单位代码")
    private String companyCode;
    @ExcelProperty("单位/部门名称")
    private String companyName;
    @ExcelProperty("污水量（m3）")
    private Double sewageVolume;
    @ExcelProperty("污水转换处理量（m3）")
    private Double sewageTransfer;
    @ExcelProperty("水（m3）")
    private Double water;
    @ExcelProperty("电（度）")
    private Double electricity;
    @ExcelProperty("气（m3）")
    private Double gas;
    @ExcelProperty("汽油（L）")
    private Double gasoline;
    @ExcelProperty("柴油（L）")
    private Double diesel;
    @ExcelProperty("填报时间")
    private String updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Double getSewageVolume() {
        return sewageVolume;
    }

    public void setSewageVolume(Double sewageVolume) {
        this.sewageVolume = sewageVolume;
    }

    public Double getSewageTransfer() {
        return sewageTransfer;
    }

    public void setSewageTransfer(Double sewageTransfer) {
        this.sewageTransfer = sewageTransfer;
    }

    public Double getWater() {
        return water;
    }

    public void setWater(Double water) {
        this.water = water;
    }

    public Double getElectricity() {
        return electricity;
    }

    public void setElectricity(Double electricity) {
        this.electricity = electricity;
    }

    public Double getGas() {
        return gas;
    }

    public void setGas(Double gas) {
        this.gas = gas;
    }

    public Double getGasoline() {
        return gasoline;
    }

    public void setGasoline(Double gasoline) {
        this.gasoline = gasoline;
    }

    public Double getDiesel() {
        return diesel;
    }

    public void setDiesel(Double diesel) {
        this.diesel = diesel;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
