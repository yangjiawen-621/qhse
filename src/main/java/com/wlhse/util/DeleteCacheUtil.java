package com.wlhse.util;

import com.wlhse.cache.JedisClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DeleteCacheUtil {
    @Resource
    private JedisClient jedisClient;

    public void deleteEmployeesCache() {
        String redisName1 = "employee_redis";
        String redisName2 = "EmployeeDto";
        String redisName3 = "employee_id";
        for (int i = 1; i < 16; i++) {
            jedisClient.delManyCahce(redisName1, i);
            jedisClient.delManyCahce(redisName2, i);
            jedisClient.delManyCahce(redisName3, i);
        }

    }

    public void deleteEmployeesCache1() {
        String redisName1 = "employee_redis";
        String redisName3 = "employee_id";
        for (int i = 1; i < 16; i++) {
            jedisClient.delManyCahce(redisName1, i);
            jedisClient.delManyCahce(redisName3, i);
        }
    }
}
