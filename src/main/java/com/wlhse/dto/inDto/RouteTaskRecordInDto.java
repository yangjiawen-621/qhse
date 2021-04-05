package com.wlhse.dto.inDto;

import com.wlhse.dto.getDto.BaseGetDto;
import org.springframework.stereotype.Component;

@Component
public class RouteTaskRecordInDto extends BaseGetDto {
    private String companyCode;
    private String startDate;
    private String endDate;
    private Integer routeEmpID;
    private String url;

    private String isOrderBy = "false";

    public String getIsOrderBy() {
        return isOrderBy;
    }

    public void setIsOrderBy(String isOrderBy) {
        this.isOrderBy = isOrderBy;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getRouteEmpID() {
        return routeEmpID;
    }

    public void setRouteEmpID(Integer routeEmpID) {
        this.routeEmpID = routeEmpID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
