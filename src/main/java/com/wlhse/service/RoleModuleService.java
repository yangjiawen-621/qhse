package com.wlhse.service;

import com.wlhse.dto.inDto.RoleModuleInDto;
import com.wlhse.entity.RoleModulePojo;

import java.util.List;

public interface RoleModuleService {
    String updateSYSRoleModule(RoleModuleInDto roleModuleInDto);

    String getModuleTreeByRoleCode(String roleCode);

    String getModuleTree();

    // 返回选中节点的ID
    String getChooseNodeID(String roleCode);

    String getRoleModuleOutDto(String uName);
}
