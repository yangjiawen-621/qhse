package com.wlhse.entity;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleModulePojo {

    private Integer id;
    private String moduleCode;
    private String RoleCode;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getRoleCode() {
        return RoleCode;
    }

    public void setRoleCode(String roleCode) {
        RoleCode = roleCode;
    }

}
