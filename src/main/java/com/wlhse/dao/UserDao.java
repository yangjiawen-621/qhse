package com.wlhse.dao;

import com.wlhse.dto.outDto.UserOutDto;
import com.wlhse.entity.UserPojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    int addUser(UserPojo userPojo);

    int deleteUser(UserPojo userPojo);

    int updateUser(UserPojo userPojo);

    UserOutDto getUserPojo(String username, String psw);

    UserPojo getByEmployeeId(int employeeId);

    int countUsername(String userName);

    UserPojo getUserByName(String username);

    int countEmployee(int employeeId);

    int countUserName(@Param("userName") String userName);

    List<String> getUserAuthMinCode(String userId);

    int bindWechat(int userId,String openId);
}
