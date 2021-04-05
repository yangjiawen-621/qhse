package com.wlhse.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @Author tobing
 * @Date 2020/10/28 21:18
 * @Description 大屏、质量管理、质量报告计划进度实体类
 */
public class DashboardQualityManagement {

    @ExcelIgnore
    private Integer id;
    @ExcelProperty("单位代码")
    private String companyCode;
    @ExcelProperty("单位/部门名称")
    private String companyName;
    @ExcelProperty("月度报告编号发放数（计划数）")
    private Integer monthPlanNum;
    @ExcelProperty("月度报告完成数")
    private Integer monthFinishNum;
    @ExcelProperty("月度报告完成率")
    private Double monthFinishRate;
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

    public Integer getMonthPlanNum() {
        return monthPlanNum;
    }

    public void setMonthPlanNum(Integer monthPlanNum) {
        this.monthPlanNum = monthPlanNum;
    }

    public Integer getMonthFinishNum() {
        return monthFinishNum;
    }

    public void setMonthFinishNum(Integer monthFinishNum) {
        this.monthFinishNum = monthFinishNum;
    }

    public Double getMonthFinishRate() {
        return monthFinishRate;
    }

    public void setMonthFinishRate(Double monthFinishRate) {
        this.monthFinishRate = monthFinishRate;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "DashboardQualityManagement{" +
                "id=" + id +
                ", companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", monthPlanNum=" + monthPlanNum +
                ", monthFinishNum=" + monthFinishNum +
                ", monthFinishRate=" + monthFinishRate +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
