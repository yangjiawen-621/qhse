package com.wlhse.dto.inDto;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class RoleModuleInDto {
    // planContent数据
    private List<RoleModuleDto> roleModule;

    public List<RoleModuleDto> getRoleModule() {
        return roleModule;
    }

    public void setRoleModule(List<RoleModuleDto> roleModule) {
        this.roleModule = roleModule;
    }
}
