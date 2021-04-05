package com.wlhse.dto.inDto;

import com.wlhse.dto.getDto.BaseGetDto;
import org.springframework.stereotype.Component;

@Component
public class ReportManageAnalysisInDto extends BaseGetDto {
    private Integer employeeID;
    private String costYear;
    private String companyCode;
    private String personCategory;
    private String startDate;
    private String endDate;
    private String isOrderBy;


    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public String getCostYear() {
        return costYear;
}

    public void setCostYear(String costYear) {
        this.costYear = costYear;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getPersonCategory() {
        return personCategory;
    }

    public void setPersonCategory(String personCategory) {
        this.personCategory = personCategory;
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

    public String getIsOrderBy() {
        return isOrderBy;
    }

    public void setIsOrderBy(String isOrderBy) {
        this.isOrderBy = isOrderBy;
    }
}
