package com.wlhse.entity;

import com.wlhse.dto.getDto.BaseGetDto;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RouteTaskRecordPojo extends BaseGetDto {
    private Integer id;
    private Integer routeEmpID; //巡检人员ID
    private String routeEmpName; //巡检人员姓名
    private String companyCode; //巡检人员单位ID
    private String companyName; //巡检人员单位名
    private String parentCompanyName; //巡检人员二级单位名称
    private String routeTime; //巡检时间
    private String address; //巡检地点
    private String GPSX;
    private String GPSY;
    private String altitude; //海拔
    private String hash;

    private String inspectionAttach;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRouteEmpID() {
        return routeEmpID;
    }

    public void setRouteEmpID(Integer routeEmpID) {
        this.routeEmpID = routeEmpID;
    }

    public String getRouteEmpName() {
        return routeEmpName;
    }

    public void setRouteEmpName(String routeEmpName) {
        this.routeEmpName = routeEmpName;
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

    public String getParentCompanyName() {
        return parentCompanyName;
    }

    public void setParentCompanyName(String parentCompanyName) {
        this.parentCompanyName = parentCompanyName;
    }

    public String getRouteTime() {
        return routeTime;
    }

    public void setRouteTime(String routeTime) {
        this.routeTime = routeTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGPSX() {
        return GPSX;
    }

    public void setGPSX(String GPSX) {
        this.GPSX = GPSX;
    }

    public String getGPSY() {
        return GPSY;
    }

    public void setGPSY(String GPSY) {
        this.GPSY = GPSY;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getInspectionAttach() {
        return inspectionAttach;
    }

    public void setInspectionAttach(String inspectionAttach) {
        this.inspectionAttach = inspectionAttach;
    }

}
