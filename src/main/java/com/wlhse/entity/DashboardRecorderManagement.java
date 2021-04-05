package com.wlhse.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @Author tobing
 * @Date 2020/10/28 21:20
 * @Description 大屏、记录仪管理、记录仪使用情况实体类
 */
public class DashboardRecorderManagement {
    @ExcelIgnore
    private Integer id;
    @ExcelProperty("单位代码")
    private String companyCode;
    @ExcelProperty("单位/部门名称")
    private String companyName;
    @ExcelProperty("工作记录仪台数（台）")
    private Integer totalNum;
    @ExcelProperty("本周领用数（台）")
    private Integer weeklyCollectNum;
    @ExcelProperty("正常开机数（台）")
    private Integer weeklyNormalNum;
    @ExcelProperty("使用比")
    private Double weeklyUsageRate;
    @ExcelProperty("监督监看数")
    private Integer weeklySupervisionNum;
    @ExcelProperty("填报日期")
    private String updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getWeeklyCollectNum() {
        return weeklyCollectNum;
    }

    public void setWeeklyCollectNum(Integer weeklyCollectNum) {
        this.weeklyCollectNum = weeklyCollectNum;
    }

    public Integer getWeeklyNormalNum() {
        return weeklyNormalNum;
    }

    public void setWeeklyNormalNum(Integer weeklyNormalNum) {
        this.weeklyNormalNum = weeklyNormalNum;
    }

    public Double getWeeklyUsageRate() {
        return weeklyUsageRate;
    }

    public void setWeeklyUsageRate(Double weeklyUsageRate) {
        this.weeklyUsageRate = weeklyUsageRate;
    }

    public Integer getWeeklySupervisionNum() {
        return weeklySupervisionNum;
    }

    public void setWeeklySupervisionNum(Integer weeklySupervisionNum) {
        this.weeklySupervisionNum = weeklySupervisionNum;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
