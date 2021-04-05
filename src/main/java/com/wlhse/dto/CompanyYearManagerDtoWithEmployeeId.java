package com.wlhse.dto;

import com.wlhse.dto.inDto.CompanyYearManagerDto;

public class CompanyYearManagerDtoWithEmployeeId extends CompanyYearManagerDto {
    private int employeeId;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
