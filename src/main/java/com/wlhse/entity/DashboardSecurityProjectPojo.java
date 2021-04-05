package com.wlhse.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

import java.util.Date;

/**
 * @Author tobing
 * @Date 2020/11/21 20:53
 * @Description 大屏-安技项目管理
 */
public class DashboardSecurityProjectPojo {

    @ExcelIgnore
    private Integer id;
    @ExcelProperty("单位代码")
    private String companyCode;
    @ExcelProperty("单位/部门名称")
    private String companyName;
    @ExcelProperty("项目级别")
    private String projectLevel;
    @ExcelProperty("项目名称")
    private String projectName;
    @ExcelProperty("项目经费")
    private Double projectFunds;
    @ExcelProperty("已入账经费（万元）")
    private Double recordedFunds;
    @ExcelProperty("入账率")
    private Double recordedFundsRate;
    @ExcelProperty("填报日期")
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

    public String getProjectLevel() {
        return projectLevel;
    }

    public void setProjectLevel(String projectLevel) {
        this.projectLevel = projectLevel;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Double getProjectFunds() {
        return projectFunds;
    }

    public void setProjectFunds(Double projectFunds) {
        this.projectFunds = projectFunds;
    }

    public Double getRecordedFunds() {
        return recordedFunds;
    }

    public void setRecordedFunds(Double recordedFunds) {
        this.recordedFunds = recordedFunds;
    }

    public Double getRecordedFundsRate() {
        return recordedFundsRate;
    }

    public void setRecordedFundsRate(Double recordedFundsRate) {
        this.recordedFundsRate = recordedFundsRate;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
