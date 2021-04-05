package com.wlhse.dto.inDto;

import org.springframework.stereotype.Component;


@Component

public class InterfaceModuleInDto {
    private int empId;
    private String moduleCode;
    private String interfaceUrl;
    private String method;

    public InterfaceModuleInDto() {
    }

    public InterfaceModuleInDto(int empId, String interfaceUrl, String method) {
        this.empId = empId;
        this.interfaceUrl = interfaceUrl;
        this.method = method;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getInterfaceUrl() {
        return interfaceUrl;
    }

    public void setInterfaceUrl(String interfaceUrl) {
        this.interfaceUrl = interfaceUrl;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

}
