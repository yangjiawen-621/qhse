package com.wlhse.dto.inDto;

import org.springframework.stereotype.Component;


@Component
public class RoleModuleDto {
    private String moduleCode;
    private String roleCode;

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}
