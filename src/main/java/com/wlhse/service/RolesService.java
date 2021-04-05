package com.wlhse.service;


import com.wlhse.entity.RolesPojo;
import com.wlhse.util.R;

import java.util.List;

public interface RolesService {
    String getRolesByCondition(String roleName, int pageIdx, int pageSize, String type);//
    //根据id查
    String getRoleById(Integer roleID);
    //增
    String addRole(RolesPojo rolesPojo);
    //删
    String deleteRole(Integer roleID);
    //更新
    String updateRole(RolesPojo rolesPojo);

    R isExit(String name);
}
