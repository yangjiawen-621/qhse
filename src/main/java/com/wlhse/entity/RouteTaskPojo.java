package com.wlhse.entity;

import com.wlhse.dto.getDto.BaseGetDto;
import org.springframework.stereotype.Component;

@Component
public class RouteTaskPojo extends BaseGetDto {
    private Integer routeTaskId;
    private String taskName;
    private Integer routeEmpId;
    private String routeEmpName;
    private String beginDate;
    private String endDate;
    private String address;
    private String gPSX;
    private String gPSY;
    private Integer publishID;
    private String publishName;
    private String hash;

    private String searchStartDate;
    private String searchEndDate;

    private String isOrderBy = "false";

    public Integer getRouteTaskId() {
        return routeTaskId;
    }

    public void setRouteTaskId(Integer routeTaskId) {
        this.routeTaskId = routeTaskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getRouteEmpId() {
        return routeEmpId;
    }

    public void setRouteEmpId(Integer routeEmpId) {
        this.routeEmpId = routeEmpId;
    }

    public String getRouteEmpName() {
        return routeEmpName;
    }

    public void setRouteEmpName(String routeEmpName) {
        this.routeEmpName = routeEmpName;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getgPSX() {
        return gPSX;
    }

    public void setgPSX(String gPSX) {
        this.gPSX = gPSX;
    }

    public String getgPSY() {
        return gPSY;
    }

    public void setgPSY(String gPSY) {
        this.gPSY = gPSY;
    }

    public Integer getPublishID() {
        return publishID;
    }

    public void setPublishID(Integer publishID) {
        this.publishID = publishID;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSearchStartDate() {
        return searchStartDate;
    }

    public void setSearchStartDate(String searchStartDate) {
        this.searchStartDate = searchStartDate;
    }

    public String getSearchEndDate() {
        return searchEndDate;
    }

    public void setSearchEndDate(String searchEndDate) {
        this.searchEndDate = searchEndDate;
    }

    public String getPublishName() {
        return publishName;
    }

    public void setPublishName(String publishName) {
        this.publishName = publishName;
    }

    public String getIsOrderBy() {
        return isOrderBy;
    }

    public void setIsOrderBy(String isOrderBy) {
        this.isOrderBy = isOrderBy;
    }

}
