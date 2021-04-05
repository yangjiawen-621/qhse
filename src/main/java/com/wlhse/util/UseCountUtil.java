package com.wlhse.util;

import com.wlhse.cache.JedisClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UseCountUtil {

    @Resource
    private JedisClient jedisClient;

    public void useCountSetCache(String redisName, Integer userId) {
        int path = HashUtil.getPath(redisName);
        if (userId == null) return;
        if (jedisClient.zCard(redisName, path) == 0) {
            jedisClient.zadd(redisName, 0, userId.toString(), path);
        } else {
            jedisClient.zIncrByOne(redisName, userId.toString(), path);
        }
    }
}