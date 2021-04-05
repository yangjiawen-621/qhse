package com.wlhse.util.token;

import java.util.UUID;


public class TokenUtil {
    public static String generateString() {
        return UUID.randomUUID().toString();
    }

}
