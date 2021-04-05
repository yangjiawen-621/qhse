package com.wlhse.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @Author tobing
 * @Date 2020/10/28 21:22
 * @Description 大屏、标准进度管理实体类
 */
public class DashboardScheduleManagement {
    @ExcelIgnore
    private Integer id;
    @ExcelProperty("单位代码")
    private String companyCode;
    @ExcelProperty("单位/部门名称")
    private String companyName;
    @ExcelProperty("应编制数")
    private Integer planNum;
    @ExcelProperty("初稿完成数")
    private Integer firstDraftFinishNum;
    @ExcelProperty("评审通过数")
    private Integer reviewPassNum;
    @ExcelProperty("标准发布数")
    private Integer standardReleaseNum;
    @ExcelProperty("初稿完成率")
    private Double firstDraftFinishRate;
    @ExcelProperty("评审完成率")
    private Double reviewPassRate;
    @ExcelProperty("标准发布率")
    private Double standardReleaseRate;
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

    public Integer getPlanNum() {
        return planNum;
    }

    public void setPlanNum(Integer planNum) {
        this.planNum = planNum;
    }

    public Integer getFirstDraftFinishNum() {
        return firstDraftFinishNum;
    }

    public void setFirstDraftFinishNum(Integer firstDraftFinishNum) {
        this.firstDraftFinishNum = firstDraftFinishNum;
    }

    public Double getFirstDraftFinishRate() {
        return firstDraftFinishRate;
    }

    public void setFirstDraftFinishRate(Double firstDraftFinishRate) {
        this.firstDraftFinishRate = firstDraftFinishRate;
    }

    public Integer getReviewPassNum() {
        return reviewPassNum;
    }

    public void setReviewPassNum(Integer reviewPassNum) {
        this.reviewPassNum = reviewPassNum;
    }

    public Double getReviewPassRate() {
        return reviewPassRate;
    }

    public void setReviewPassRate(Double reviewPassRate) {
        this.reviewPassRate = reviewPassRate;
    }

    public Integer getStandardReleaseNum() {
        return standardReleaseNum;
    }

    public void setStandardReleaseNum(Integer standardReleaseNum) {
        this.standardReleaseNum = standardReleaseNum;
    }

    public Double getStandardReleaseRate() {
        return standardReleaseRate;
    }

    public void setStandardReleaseRate(Double standardReleaseRate) {
        this.standardReleaseRate = standardReleaseRate;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
