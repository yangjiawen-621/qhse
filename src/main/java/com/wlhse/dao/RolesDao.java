package com.wlhse.dao;

import com.wlhse.entity.RolesPojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesDao {
    int addRoles(RolesPojo rolesPojo);

    int deleteRoles(@Param("roleID") Integer roleID);

    int updateRoles(RolesPojo rolesPojo);

    List<RolesPojo> queryRoles(@Param("roleName") String roleName);

    int queryTotal(@Param("roleName") String roleName);

    RolesPojo queryRoleById(@Param("roleID") Integer roleID);

    String queryMaxRoleCode();

    RolesPojo queryRoleByName(String name);
}
