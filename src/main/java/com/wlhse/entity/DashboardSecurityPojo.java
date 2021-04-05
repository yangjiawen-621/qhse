package com.wlhse.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

import java.util.Date;

/**
 * @Author tobing
 * @Date 2020/11/21 20:53
 * @Description 大屏-安全管理
 */
public class DashboardSecurityPojo {

    @ExcelIgnore
    private Integer id;
    @ExcelProperty("单位代码")
    private String companyCode;
    @ExcelProperty("单位/部门名称")
    private String companyName;
    @ExcelProperty("季度隐患指标")
    private Integer quarterDangerIndex;
    @ExcelProperty("实际完成查患")
    private Integer actualFinishDanger;
    @ExcelProperty("季度违章指标")
    private Integer quarterRegulationIndex;
    @ExcelProperty("实际完成纠违")
    private Integer actualFinishRegulation;
    @ExcelProperty("季度事件指标")
    private Integer quarterEventIndex;
    @ExcelProperty("实际完成事件")
    private Integer actualFinishEvent;
    @ExcelProperty("违章完成率")
    private Double finishRegulationRate;
    @ExcelProperty("查患完成率")
    private Double finishDangerRate;
    @ExcelProperty("事件完成率")
    private Double eventFinishRate;
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

    public Integer getQuarterDangerIndex() {
        return quarterDangerIndex;
    }

    public void setQuarterDangerIndex(Integer quarterDangerIndex) {
        this.quarterDangerIndex = quarterDangerIndex;
    }

    public Integer getActualFinishDanger() {
        return actualFinishDanger;
    }

    public void setActualFinishDanger(Integer actualFinishDanger) {
        this.actualFinishDanger = actualFinishDanger;
    }

    public Double getFinishDangerRate() {
        return finishDangerRate;
    }

    public void setFinishDangerRate(Double finishDangerRate) {
        this.finishDangerRate = finishDangerRate;
    }

    public Integer getQuarterRegulationIndex() {
        return quarterRegulationIndex;
    }

    public void setQuarterRegulationIndex(Integer quarterRegulationIndex) {
        this.quarterRegulationIndex = quarterRegulationIndex;
    }

    public Integer getActualFinishRegulation() {
        return actualFinishRegulation;
    }

    public void setActualFinishRegulation(Integer actualFinishRegulation) {
        this.actualFinishRegulation = actualFinishRegulation;
    }

    public Double getFinishRegulationRate() {
        return finishRegulationRate;
    }

    public void setFinishRegulationRate(Double finishRegulationRate) {
        this.finishRegulationRate = finishRegulationRate;
    }

    public Integer getQuarterEventIndex() {
        return quarterEventIndex;
    }

    public void setQuarterEventIndex(Integer quarterEventIndex) {
        this.quarterEventIndex = quarterEventIndex;
    }

    public Integer getActualFinishEvent() {
        return actualFinishEvent;
    }

    public void setActualFinishEvent(Integer actualFinishEvent) {
        this.actualFinishEvent = actualFinishEvent;
    }

    public Double getEventFinishRate() {
        return eventFinishRate;
    }

    public void setEventFinishRate(Double eventFinishRate) {
        this.eventFinishRate = eventFinishRate;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
