
package com.wlhse.websocket;

import com.wlhse.cache.JedisClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

//2.拦截器
@Component
public class HandShake implements HandshakeInterceptor {

    private static JedisClient jedisClient;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public void setJedisClient(JedisClient jedisClient) {
        this.jedisClient = jedisClient;
    }

    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        ServletServerHttpRequest req = (ServletServerHttpRequest) request;
        String token = req.getServletRequest().getParameter("token");
        if (StringUtils.isBlank(token)) {
            return false;
        }
        try {
            String uid = jedisClient.hGetAll(token).get("employeeId");
            if (StringUtils.isNotBlank(uid)) {
                attributes.put("uid", Long.parseLong(uid));
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    //调用handler后处理方法
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
    }

}
