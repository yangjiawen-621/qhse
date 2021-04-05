package com.wlhse.dao;

import com.wlhse.entity.UserRolePojo;

import java.util.List;

public interface UserRoleDao {
    int addSYSUSERRole(UserRolePojo sysuserRole);

    int deleteSYSUSERRole(Integer id);

    int updateSYSUSERRole(UserRolePojo sysuserRole);

    List<UserRolePojo> queryAllSYSUSERRoles();

    UserRolePojo queryById(Integer id);

    UserRolePojo queryByUName(String uName);

    int deleteByUName(String uName);
}
