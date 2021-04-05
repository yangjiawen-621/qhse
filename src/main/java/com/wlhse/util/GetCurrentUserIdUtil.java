package com.wlhse.util;


import com.wlhse.cache.JedisClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component
public class GetCurrentUserIdUtil {

    @Resource
    private JedisClient jedisClient;

    public Integer getUserId(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            if (StringUtils.isNotBlank(token))
               // System.out.println(jedisClient.get(token));
                return Integer.parseInt(jedisClient.hGetAll(token).get("employeeId"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
