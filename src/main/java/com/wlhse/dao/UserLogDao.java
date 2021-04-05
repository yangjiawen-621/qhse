package com.wlhse.dao;

import com.wlhse.dto.inDto.UserLogInDto;
import com.wlhse.entity.UserLogPojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserLogDao {
    //查询所有用户登录信息
    List<UserLogPojo> getAllUserLogPojo();

    //获取某段时间，某用户的登录次数
    Integer getUserLogPojoCount(UserLogInDto userLogInDto);

    //根据用户id删除所有登录记录
    int deleteUserLogPojo(@Param("id") Integer id);

    //插入用户登录信息
    int insertUserLogPojo(UserLogPojo userLogPojo);

    int getAllUserLogPojoCount();
}
