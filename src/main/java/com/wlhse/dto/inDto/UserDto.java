package com.wlhse.dto.inDto;

import com.alibaba.fastjson.annotation.JSONType;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@JSONType(orders = {"userName", "psw", "token"})
@Component
public class UserDto implements Serializable {
    private String userName;
    private String psw;
    private String token;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
